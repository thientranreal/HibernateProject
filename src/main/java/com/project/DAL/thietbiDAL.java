package com.project.DAL;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.models.thietbi;
import com.project.utilities.HibernateUtil;
import org.hibernate.query.Query;

import java.util.List;

public class thietbiDAL {

    private static thietbiDAL instance;

    public static thietbiDAL getInstance() {
        if (instance == null) {
            instance = new thietbiDAL();
        }
        return instance;
    }

    private interface TransactionFunction {
        boolean execute(Session session);
    }

    private boolean executeTransaction(TransactionFunction function) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            Transaction transaction = session.beginTransaction();
            boolean result = function.execute(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean create(thietbi device) {
        return executeTransaction(session -> {
            session.save(device);
            return true;
        });
    }

    public boolean update(thietbi device) {
        return executeTransaction(session -> {
            session.update(device);
            return true;
        });
    }

    public boolean delete(int id) {
        return executeTransaction(session -> {
            thietbi device = session.get(thietbi.class, id);
            if (device != null) {
                session.delete(device);
            }
            return true;
        });
    }

    public thietbi getById(int id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.get(thietbi.class, id);
        }
    }

    public List<thietbi> getDeviceList() {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.createQuery("from thietbi", thietbi.class).list();
        }
    }

}
