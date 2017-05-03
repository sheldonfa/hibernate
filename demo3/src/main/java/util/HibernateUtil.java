package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

/**
 * @author fangxin
 * @date 2017/5/3.
 */
public class HibernateUtil {



    private static SessionFactory sessionFactory;

    static {
        Configuration configure = new Configuration().configure();
        sessionFactory = configure.buildSessionFactory();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                System.out.println("虚拟机关闭！释放资源");
                sessionFactory.close();
            }
        }));
    }

    public static Session openSession(){
        return sessionFactory.openSession();
    }

    public static Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
}
