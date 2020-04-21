
package com.neu.dao;

import com.neu.pojo.Login;
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


public class UserDao{
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

    public Login authenticateLogin(String username, String password) 
    {
        Login loggedInUser = null;
        try {
            beginTransaction();
            Query q= getSession().createQuery("from Login where username= :username AND password= :password");
            q.setString("username", username);
            q.setString("password", password);
            loggedInUser = (Login)q.uniqueResult();
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
    
    
    

    public List<Login> getUsers(String searchString) {
        List<Login> matchedUsers = new ArrayList<Login>() ;
        try {
            beginTransaction();
            Query q= getSession().createQuery("from Login where username= :username");
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
        Login newUser = null;
        int registerSuccess = 0;
        try {
            beginTransaction();
            
            newUser = new Login();
            newUser.setUsername(userName);
            newUser.setPassword(password);
            getSession().save(newUser);
            commit();
            registerSuccess = 1;
        } 
        catch (HibernateException e) 
        {
            e.printStackTrace();
            rollbackTransaction();
        } 
        finally {
            close();
        }

        return registerSuccess;

    }
    
}
