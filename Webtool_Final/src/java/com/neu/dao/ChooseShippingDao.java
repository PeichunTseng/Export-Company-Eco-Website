
package com.neu.dao;

import com.neu.pojo.ChooseShipping;

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

public class ChooseShippingDao {
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
    
    public List<ChooseShipping> getCourses(String destination){
        List<ChooseShipping> courses = new ArrayList<>();
        try{
            beginTransaction();
            Session session = getSession();
            Query q = session.createQuery("from ChooseShipping where destination=:destination");
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
                Logger.getLogger(ChooseShippingDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(ChooseShippingDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                //close();
            } catch (Exception ex) {
                Logger.getLogger(ChooseShippingDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courses;
    }
    
    public List<ChooseShipping> getByUsername(String username) {
        List<ChooseShipping> courses = new ArrayList<>();
        try {
            beginTransaction();
            Session session = getSession();
            Query q = session.createQuery("from ChooseShipping where username=:username ");
            System.out.println(q.toString());
            //System.out.println(productName);
            q.setString("username", username);
            //q.setString("type", searchString);
            courses = q.list();
            System.out.println(q.toString());
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            try {
                rollbackTransaction();
            } catch (Exception ex) {
                Logger.getLogger(BoughtProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(BoughtProductDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                //close();
            } catch (Exception ex) {
                Logger.getLogger(BoughtProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courses;
    }
    
    public void deleteProductById(long id) {
        try {
            beginTransaction();
            Query q = getSession().createQuery("from ChooseShipping where id=:id");
            q.setLong("id",id);
            ChooseShipping shiToDelete = (ChooseShipping) q.uniqueResult();
            getSession().delete(shiToDelete);
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
    }
    
    public int addShipping(String companyname, String destination,int price,String username){
        int result = 0;
        try {
            ChooseShipping cs = new ChooseShipping();
            cs.setCompanyName(companyname);
            cs.setDestination(destination);
            cs.setPrice(price);
            cs.setUsername(username);
            beginTransaction();
            Session session = getSession();
            session.save(cs);
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
