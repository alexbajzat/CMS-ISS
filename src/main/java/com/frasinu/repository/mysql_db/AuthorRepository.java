package com.frasinu.repository.mysql_db;

import com.frasinu.exception.InexistentException;
import com.frasinu.model.Author;
import com.frasinu.repository.IAuthorRepository;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Ericqw on 15.05.2017.
 */
@Repository
public class AuthorRepository implements IAuthorRepository {
    private final SessionFactory factory;

    @Autowired
    public AuthorRepository(SessionFactory sessionFactory) {
        factory = sessionFactory;
    }

    @Override
    public Author create(Author author) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(author);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return author;
    }

    @Override
    public void delete(int id) throws InexistentException {
        Session session = factory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Author author = (Author) session.get(Author.class, id);
            if (author.getId() == null)
                throw new InexistentException("Author cannot be found!");

            session.delete(author);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public Author update(Author author) throws InexistentException {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Author authorToUpdate = (Author) session.get(Author.class, author.getId());
            if (authorToUpdate == null)
                throw new InexistentException("Author cannot be found!");
            session.merge(author);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return author;
    }

    @Override
    public List<Author> getAll() {
        Session session = factory.openSession();

        List authors = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            authors = session.createQuery("FROM author").list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return authors;
    }

    @Override
    public Author findById(int id) throws InexistentException {
        Session session = factory.openSession();

        Author author = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            author = session.get(Author.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return author;
    }
}
