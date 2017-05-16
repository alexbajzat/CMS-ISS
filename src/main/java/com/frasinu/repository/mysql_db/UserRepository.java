package com.frasinu.repository.mysql_db;

import com.frasinu.exception.InexistentException;
import com.frasinu.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import com.frasinu.repository.IUserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by aaaa on 15-May-17.
 */

@Repository
public class UserRepository implements IUserRepository {
    private final SessionFactory factory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        factory = sessionFactory;
    }

    @Override
    public User create(User user) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public void delete(int id) throws InexistentException {
        Session session = factory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            if (user.getId() == null)
                throw new InexistentException("User cannot be found!");

            session.delete(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public User update(User user) throws InexistentException {
        Session session = factory.openSession();
        Transaction tx = null;
        String username = user.getUsername();
        try {
            tx = session.beginTransaction();
            User userToUpdate = (User) session.get(User.class, user.getId());
            if (userToUpdate == null)
                throw new InexistentException("User cannot be found!");
            session.merge(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        Session session = factory.openSession();

        List users = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public User findById(int id) throws InexistentException {
        Session session = factory.openSession();

        User user = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            user = session.get(User.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public User findByUsername(String username){
        Session session = factory.openSession();

        List<User> users=null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            users = session.createQuery("from User where username = ?", User.class)
                    .setParameter(0, username)
                    .list();
            if(users.size()==0)
                throw new InexistentException("User cannot be found!");
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users.get(0);
    }

}
