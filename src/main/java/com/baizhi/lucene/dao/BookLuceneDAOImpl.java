package com.baizhi.lucene.dao;

import com.baizhi.entity.Book;
import com.baizhi.util.LuceneUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookLuceneDAOImpl implements  BookLuceneDAO {



    @Override
    public List<Book> find(String keywords) {
        List<Book> books = new ArrayList<Book>();
        try {
            IndexSearcher indexSearcher = LuceneUtils.getIndexSearcher();
            MultiFieldQueryParser queryParser = new MultiFieldQueryParser(LuceneUtils.getVersion(), new String[]{"name", "author", "content"}, LuceneUtils.getAnalyzer());
            Query query = queryParser.parse(keywords);
            TopDocs topDocs = indexSearcher.search(query, Integer.MAX_VALUE);
            System.out.println("总记录数: "+topDocs.totalHits);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (ScoreDoc scoreDoc : scoreDocs) {
                Book book = new Book();
                int doc = scoreDoc.doc;
                Document docment = indexSearcher.doc(doc);
                book.setId(docment.get("id"));
                book.setPrice(Double.valueOf(docment.get("price")));
                Highlighter highlighter = new Highlighter(new SimpleHTMLFormatter("<span style='color:red;'>", "</span>"), new QueryScorer(query));
                String name = highlighter.getBestFragment(LuceneUtils.getAnalyzer(), "name", docment.get("name"));
                name=name!=null?name:docment.get("name");
                book.setName(name);
                String author = highlighter.getBestFragment(LuceneUtils.getAnalyzer(), "author", docment.get("author"));
                author = author!=null?author:docment.get("author");
                book.setAuthor(author);
                String content = highlighter.getBestFragment(LuceneUtils.getAnalyzer(), "content", docment.get("content"));
                content = content!=null?content:docment.get("content");
                book.setContent(content);
                books.add(book);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidTokenOffsetsException e) {
            e.printStackTrace();
        }
        return books;
    }
}
