package net.lab.orm;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by lab on 10.06.2014.
 */
public class HibernateUtil {
    private static SessionFactory sfactory;

static {
    try{
            sfactory = new Configuration().configure().buildSessionFactory();
    }
    catch(Exception e){
        e.printStackTrace();
    }
}

    public static SessionFactory getSessionFactory() {
        return sfactory;
    }
}
