package com.baizhi.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;

public class LuceneUtils {


    private static Directory fsDirectory;
    private static Analyzer analyzer;
    private static IndexWriterConfig indexWriterConfig;
    private static Version version;
    private static DirectoryReader directoryReader;

    static{
        try {
            File directory = new File("index");
            if(!directory.exists()){
                directory.mkdirs();
            }
            fsDirectory = FSDirectory.open(directory);
            version =  Version.LUCENE_44;
            analyzer = new IKAnalyzer();
            indexWriterConfig = new IndexWriterConfig(version, analyzer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建IndexWriter
     */

    public static IndexWriter getIndexWriter(){
        try {
            IndexWriter indexWriter = new IndexWriter(fsDirectory, indexWriterConfig);
            return  indexWriter;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建IndexSearcher
     */
    public static IndexSearcher getIndexSearcher(){
        IndexSearcher indexSearcher = null;
        try {
            directoryReader = DirectoryReader.open(fsDirectory);
            indexSearcher = new IndexSearcher(directoryReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  indexSearcher;
    }


    public static Version  getVersion(){
        return version;
    }

    public static Analyzer getAnalyzer(){
        return analyzer;
    }

}
