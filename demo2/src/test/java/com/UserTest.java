package com;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;

import java.util.List;

/**
 * @author fangxin
 * @date 2017/5/2.
 */
public class UserTest {

    /**
     * getCurrentSession()
     *      获取到的session，同一个线程总是同一个session
     */
    @Test
    public void test1(){
        Configuration configure = new Configuration().configure();
        SessionFactory sessionFactory = configure.buildSessionFactory();
        // open一个session
        Session session = sessionFactory.openSession();
        // 使用getCurrentSession方式获取session
        Session session1 = sessionFactory.getCurrentSession();
        Session session2 = sessionFactory.getCurrentSession();
        System.out.println(session==session1);
        System.out.println(session1==session2);
    }

    /**
     * 查：get和load
     *
     * load查询的方式，是延迟加载的方式，只有使用到了，才会查询。下面两个例子，一个查询数据库了，一个没有
     */
    @Test
    public void test2(){
        Configuration configure = new Configuration().configure();
        SessionFactory sessionFactory = configure.buildSessionFactory();
        Session session = sessionFactory.openSession();
        //get方式
        User user = (User) session.get(User.class, 1);
    }
    @Test
    public void test3(){
        Configuration configure = new Configuration().configure();
        SessionFactory sessionFactory = configure.buildSessionFactory();
        Session session = sessionFactory.openSession();
        //load方式
        User user = (User) session.load(User.class, 1);
    }

    /**
     * createQuery简单使用
     */
    @Test
    public void test4(){
        Configuration configure = new Configuration().configure();
        SessionFactory sessionFactory = configure.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from com.User");
        List<User> list = query.list();

        for(User user : list){
            System.out.println(user.getName());
        }
    }

    /**
     * createCriteria简单使用
     */
    @Test
    public void test5(){
        Configuration configure = new Configuration().configure();
        SessionFactory sessionFactory = configure.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(User.class);
        List<User> list = criteria.list();
        for(User user : list){
            System.out.println(user.getName());
        }
    }

    /**
     * API精讲：事务关闭时，当前线程的session也关闭
     */
    @Test
    public void test6(){
        Configuration configure = new Configuration().configure();
        SessionFactory sessionFactory = configure.buildSessionFactory();
        Session session1 = sessionFactory.getCurrentSession();
        // 开启并提交事务，触发session自动关闭
        Transaction transaction = session1.beginTransaction();
        transaction.commit();
        Session session2 = sessionFactory.getCurrentSession();
        // 结果两个用getCurrentSession获得的session不一样了
        System.out.println(session1==session2);
    }

    /**
     * API精讲：Query对象方法 --》由createQuery返回的对象
     *          list() 返回多行结果
     *          uniqueResult() 返回一行结果
     *          setFirstResult() 对应limit index，count
     *          setMaxResult()
     */

    /**
     * API精讲：Criteria --》由createCriteria返回的对象
     *          add(Restrictions.eq) 添加查询条件
     *
     */

}
