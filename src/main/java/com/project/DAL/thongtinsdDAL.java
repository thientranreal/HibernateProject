package com.project.DAL;

import com.project.models.thietbi;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.models.thongtinsd;
import com.project.utilities.HibernateUtil;
import org.hibernate.query.Query;

import java.util.List;

public class thongtinsdDAL {
    private static thongtinsdDAL instance;

    public static thongtinsdDAL getInstance() {
        if (instance == null) {
            instance = new thongtinsdDAL();
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

    public boolean create(thongtinsd usageInformation) {
        return executeTransaction(session -> {
            session.save(usageInformation);
            return true;
        });
    }

    public boolean update(thongtinsd usageInformation) {
        return executeTransaction(session -> {
            session.update(usageInformation);
            return true;
        });
    }

    public boolean delete(int id) {
        return executeTransaction(session -> {
            thongtinsd usageInformation = session.get(thongtinsd.class, id);
            if (usageInformation != null) {
                session.delete(usageInformation);
            }
            return true;
        });
    }

    public thongtinsd getById(int id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.get(thongtinsd.class, id);
        }
    }

    public List<thongtinsd> getUsageInformationList() {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.createQuery("from thongtinsd", thongtinsd.class).list();
        }
    }

    public List<thongtinsd> search(String keyword) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            String queryString = "from thongtinsd where LOWER(CONCAT(MaTB, TenTB, MoTaTB)) like :keyword";
            Query<thongtinsd> query = session.createQuery(queryString, thongtinsd.class);
            query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
            return query.list();
        }
    }
}