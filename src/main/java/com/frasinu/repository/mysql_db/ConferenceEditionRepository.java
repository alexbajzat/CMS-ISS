package com.frasinu.repository.mysql_db;

import com.frasinu.exception.InexistentException;
import com.frasinu.model.Conference;
import com.frasinu.model.ConferenceEdition;
import com.frasinu.repository.IConferenceEditionRepository;
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
public class ConferenceEditionRepository implements IConferenceEditionRepository {
    private final SessionFactory factory;

    @Autowired
    public ConferenceEditionRepository(SessionFactory sessionFactory) {
        factory = sessionFactory;
    }

    @Override
    public ConferenceEdition create(ConferenceEdition conferenceEdition) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(conferenceEdition);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return conferenceEdition;
    }

    @Override
    public void delete(int id) throws InexistentException {
        Session session = factory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ConferenceEdition conferenceEdition= (ConferenceEdition) session.get(ConferenceEdition.class, id);
            if (conferenceEdition.getId() == null)
                throw new InexistentException("Conference edition cannot be found!");

            session.delete(conferenceEdition);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public ConferenceEdition update(ConferenceEdition conferenceEdition) throws InexistentException {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ConferenceEdition conferenceEditionToUpdate = (ConferenceEdition) session.get(ConferenceEdition.class, conferenceEdition.getId());
            if (conferenceEditionToUpdate == null)
                throw new InexistentException("Conference edition cannot be found!");
            session.merge(conferenceEdition);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return conferenceEdition;
    }

    @Override
    public List<ConferenceEdition> getAll() {
        Session session = factory.openSession();

        List conferenceEditions = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            conferenceEditions = session.createQuery("FROM ConferenceEdition").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return conferenceEditions;
    }

    @Override
    public ConferenceEdition findById(int id) throws InexistentException {
        Session session = factory.openSession();

        ConferenceEdition conferenceEdition = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            conferenceEdition = session.get(ConferenceEdition.class, id);
            if(conferenceEdition==null)
                throw new InexistentException("Conference edition cannot be found!");
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return conferenceEdition;
    }
}
