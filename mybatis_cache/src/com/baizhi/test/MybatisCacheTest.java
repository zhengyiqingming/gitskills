package com.baizhi.test;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.List;

public class MybatisCacheTest {

    /**
     * 一级缓存: 同一个会话有效
     *
     * 二级缓存: namespace 跨会话
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 连接对象
        SqlSession sqlSession = sessionFactory.openSession();

        // 测试mybatis的一级缓存
        UserDao userDAO = sqlSession.getMapper(UserDao.class);

        List<User> users1 = userDAO.findAll();

        for (int i = 0; i < users1.size(); i++) {
            System.out.println(users1.get(i));
        }



        sqlSession.commit();
        sqlSession.close();

        System.out.println("==============================================");

       /* SqlSession sqlSession1 = sessionFactory.openSession();

        UserDao userDAO1 = sqlSession1.getMapper(UserDao.class);

        List<User> user2 = userDAO1.findAll();

        sqlSession1.commit();
        sqlSession1.close();*/
    }
}
