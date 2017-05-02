package com;


import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

/**
 * @author fangxin
 * @date 2017/5/2.
 */
public class UserTest {

    @Test
    public void insert(){
        User user = new User();
        user.setName("张三");
        user.setPassword("123456");

        Configuration configure = new Configuration().configure();
        SessionFactory sessionFactory = configure.buildSessionFactory();
        Session session = sessionFactory.openSession();
        // 手动开启事务
        Transaction transaction = session.beginTransaction();
        session.save(user);
        // 手动提交事务
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
