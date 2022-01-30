package com.company.dao;

import com.company.model.Quadratic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public class QuadraticDaoImpl implements QuadraticDao {
    private final SessionFactory sessionFactory;

    public QuadraticDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Quadratic save(Quadratic quadratic) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(quadratic);
            transaction.commit();
            return quadratic;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save parameters " + quadratic, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
