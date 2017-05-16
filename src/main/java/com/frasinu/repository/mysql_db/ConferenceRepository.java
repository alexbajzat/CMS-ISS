package com.frasinu.repository.mysql_db;

import com.frasinu.exception.InexistentException;
import com.frasinu.model.Conference;
import com.frasinu.model.User;
import com.frasinu.repository.IConferenceRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cory_ on 16-May-17.
 */
@Repository
public class ConferenceRepository implements IConferenceRepository {
    private final SessionFactory factory;

    @Autowired
    public ConferenceRepository(SessionFactory sessionFactory) {
        factory = sessionFactory;
    }

    @Override
    public Conference create(Conference conference) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(conference);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return conference;
    }

    @Override
    public void delete(int id) throws InexistentException {
        Session session = factory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Conference conference= (Conference) session.get(Conference.class, id);
            if (conference.getId() == null)
                throw new InexistentException("Conference cannot be found!");

            session.delete(conference);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Conference update(Conference conf) throws InexistentException {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Conference conferenceToUpdate = (Conference) session.get(Conference.class, conf.getId());
            if (conferenceToUpdate == null)
                throw new InexistentException("Conference cannot be found!");
            session.merge(conf);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return conf;
    }

    @Override
    public List<Conference> getAll() {
        Session session = factory.openSession();

        List conferences = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            conferences = session.createQuery("FROM Conference").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return conferences;
    }

    @Override
    public Conference findById(int id) throws InexistentException {
        Session session = factory.openSession();

        Conference conf = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            conf = session.get(Conference.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return conf;
    }
}
