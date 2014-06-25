package net.lab.dao;

import net.lab.entity.Streets;
import net.lab.orm.HibernateUtil;
import org.hibernate.Session;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class StreetsDaoImplementation implements StreetsDao {
    @Override
    public void addStreet(Streets s) throws SQLException {

    }
    @Override
    public void delStreet(Streets s) throws SQLException {
    Session session = null;
        try {
           session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(s);
            session.getTransaction().commit();
        }
        catch (Exception e){}
        finally {
            if(session!=null && session.isOpen()){session.close();}
        }
    }
    @Override
    public Streets getStreetById(Integer in) throws SQLException {
        Streets street = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            street = (Streets)session.load(Streets.class,in);
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR I/O", JOptionPane.OK_OPTION);
        }
        finally {
            if(session!=null && session.isOpen()){session.close();}
        }
        return street;
    }
    @Override
    public List getAllStreets() throws SQLException {
        Session session = null;
        List result = null;
        try{
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        result = session.createQuery("from Streets order by name").list();
        session.getTransaction().commit();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR I/O", JOptionPane.OK_OPTION);
        }
        finally {
            if(session!=null && session.isOpen()){session.close();}
        }
        return result;
    }
}
