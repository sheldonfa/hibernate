package com;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.junit.Test;
import util.HibernateUtil;

import java.util.List;

/**
 * @author fangxin
 * @date 2017/5/2.
 */
public class UserTest {

    @Test
    public void test1(){
        Session session = HibernateUtil.openSession();
        Query query = session.createQuery("from com.User");
        List<User> list = query.list();
        for(User user : list){
            System.out.println(user.getName());
        }
    }

}
