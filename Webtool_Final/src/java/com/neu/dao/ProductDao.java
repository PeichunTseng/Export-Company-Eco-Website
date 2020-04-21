
package com.neu.dao;

import com.neu.pojo.Product;
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

public class ProductDao {
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
    
    public List<Product> getCourses(String productName){
        List<Product> courses = new ArrayList<>();
        try{
            beginTransaction();
            Session session = getSession();
            Query q = session.createQuery("from Product where productName=:productName ");
            System.out.println(q.toString());
            //System.out.println(productName);
            q.setString("productName", productName);
            //q.setString("type", searchString);
            courses = q.list();
            System.out.println(q.toString());
            commit();
        } catch(HibernateException e){
            e.printStackTrace();
            try {
                rollbackTransaction();
            } catch (Exception ex) {
                Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                //close();
            } catch (Exception ex) {
                Logger.getLogger(ProductDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return courses;
    }
    public List<Product> getCourses(String productName,String companyName) {
        List<Product> courses = new ArrayList<>();
        try {
            beginTransaction();
            Session session = getSession();
            Query q = session.createQuery("from Product where productName=:productName and companyName=:companyName");
            System.out.println(q.toString());
            //System.out.println(productName);
            q.setString("productName", productName);
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
    
    public int addProduct(String coursename, String courseCRN,int price,String username){
        int result = 0;
        try {
            Product pro = new Product();
            pro.setCompanyName(coursename);
            pro.setProductName(courseCRN);
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
    
    public void deleteProductById(long id) {
        try {
            beginTransaction();
            Query q = getSession().createQuery("from Product where id= :id");
            q.setLong("id", id);
            Product proToDelete = (Product) q.uniqueResult();
            getSession().delete(proToDelete);
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
    }
}
