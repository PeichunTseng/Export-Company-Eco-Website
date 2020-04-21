
package com.neu.dao;

import com.neu.pojo.Shipping;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.hibernate.metamodel.MetadataSources;

public class ShippingDao {
     private static final SessionFactory sf = new  Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private Session session = null;
    
    private Session getSession()
    {
        if (session == null || !session.isOpen()){
            session = sf.openSession(); //取得Session
        }
        return session;
    }
   
    
    private void beginTransaction(){
        getSession().beginTransaction();// 開啟交易
    }
    
    private void commit(){
        getSession().getTransaction().commit();;// 送出交易
    }
    
    
    private void close(){
        if (session !=null)
        {
            getSession().close();
        }
    }
    
    private void rollbackTransaction(){
        getSession().getTransaction().rollback();
    }
    
    public List<Shipping> getCourses(String destination){
        List<Shipping> courses = new ArrayList<>();
        try{
            beginTransaction();
            Session session = getSession();
            Query q = session.createQuery("from Shipping where destination=:destination");
            System.out.println(q.toString());
            q.setString("destination", destination);

            courses = q.list();
            System.out.println(q.toString());
            commit();
        } catch(HibernateException e){
            e.printStackTrace();
            try {
                rollbackTransaction();
            } catch (Exception ex) {
                Logger.getLogger(ShippingDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(ShippingDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                //close();
            } catch (Exception ex) {
                Logger.getLogger(ShippingDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courses;
    }
    
    public List<Shipping> getCourses(String destination, String companyName) {
        List<Shipping> courses = new ArrayList<>();
        try {
            beginTransaction();
            Session session = getSession();
            Query q = session.createQuery("from Shipping where destination=:destination and companyName=:companyName");
            System.out.println(q.toString());
            //System.out.println(productName);
            q.setString("destination", destination);
            q.setString("companyName", companyName);
            courses = q.list();
            System.out.println(q.toString());
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                rollbackTransaction();
            } catch (Exception ex) {
                Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //close();
            } catch (Exception ex) {
                Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courses;
    }
    
    public int addShipping(String coursename, String courseCRN,int price,String username){
        int result = 0;
        try {
            Shipping pro = new Shipping();
            pro.setCompanyName(coursename);
            pro.setDestination(courseCRN);
            pro.setPrice(price);
            pro.setUsername(username);
            beginTransaction();
            Session session = getSession();
            session.save(pro);
            commit();
            result = 1;

        } 
        catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
//        catch (HibernateException e) 
//        {
//            e.printStackTrace();
//            try 
//            {
//                rollbackTransaction();
//            } 
//            catch (Exception ex) 
//            {
//                Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } 
//        catch (Exception ex) 
//        {
//            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//        finally
//        {
//            try 
//            {
//                //close();
//            } 
//            catch (Exception ex) 
//            {
//                Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        
        return result;
    }
}
