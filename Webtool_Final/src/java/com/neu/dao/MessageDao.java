
package com.neu.dao;

import com.neu.pojo.Login;
import com.neu.pojo.Message;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class MessageDao {

    private static final SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    private Session session = null;

    private Session getSession() {
        if (session == null || !session.isOpen()) {
            session = sf.openSession();
        }
        return session;
    }

    private void beginTransaction() {
        getSession().beginTransaction();
    }

    private void commit() {
        getSession().getTransaction().commit();;
    }

    private void close() {
        getSession().close();
    }

    private void rollbackTransaction() {
        getSession().getTransaction().rollback();
    }

    public List<Message> getMessages(String user) {
        List<Message> messages = new ArrayList<Message>();
        try {
            beginTransaction();
            Query q = getSession().createQuery("from Message where userName= :username");
            q.setString("username", user);
            messages = q.list();
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return messages;
    }

    public int addMessages(String to, String from, String message) {
        int result = 0;
        try {
            Message msg = new Message();
            msg.setFromUser(from);
            msg.setUserName(to);
            msg.setMessage(message);
            beginTransaction();
            getSession().save(msg);
            commit();
            result = 1;
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
        return result;
    }
    
    
    public void deleteMessageById(long id){
        try {
            beginTransaction();
            Query q = getSession().createQuery("from Message where id= :id");
            q.setLong("id", id);
            Message msgToDelete = (Message)q.uniqueResult();
            getSession().delete(msgToDelete);
            commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            rollbackTransaction();
        } finally {
            close();
        }
    }
}
