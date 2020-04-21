
package com.neu.dao;

import com.neu.pojo.ShippingLogin;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.util.ArrayList;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ShippingUserDao{
    // Configuration 負責管理 Hibernate 配置訊息,
    // 根據 config 建立 SessionFactory
    // SessionFactory 將用於建立 Session
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

    public ShippingLogin authenticateLogin(String username, String password) 
    {
        ShippingLogin loggedInUser = null;
        try {
            beginTransaction();
            Query q= getSession().createQuery("from ShippingLogin where username= :username AND password= :password");
            q.setString("username", username);
            q.setString("password", password);
            loggedInUser = (ShippingLogin)q.uniqueResult();
//            Session session = getSession();
//            loggedInUser = (Login) session.get(Login.class, username);
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } 
        finally {
            close();
        }
        return loggedInUser;
    }
    
    
    

    public List<ShippingLogin> getUsers(String searchString) {
        List<ShippingLogin> matchedUsers = new ArrayList<ShippingLogin>() ;
        try {
            beginTransaction();
            Query q= getSession().createQuery("from ShippingLogin where username= :username");
            q.setString("username", searchString);
            matchedUsers = q.list();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } 
        finally {
            close();
        }
        return matchedUsers;
    }

    public int addUser(String userName, String password) {
        ShippingLogin newUser = null;
        int registerSuccess = 0;
        try {
            beginTransaction();
            
            newUser = new ShippingLogin();
            newUser.setUsername(userName);
            newUser.setPassword(password);
            getSession().save(newUser);
            commit();
            registerSuccess = 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } 
        finally {
            close();
        }

        return registerSuccess;

    }
    
}
