package com.project.DAL;

import com.project.models.thanhvien;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.models.xuly;
import com.project.utilities.HibernateUtil;

import java.util.List;
import org.hibernate.query.Query;

public class xulyDAL {
    private static xulyDAL instance;

    public static xulyDAL getInstance() {
        if (instance == null) {
            instance = new xulyDAL();
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

    public boolean create(xuly Handle) {
        return executeTransaction(session -> {
            session.save(Handle);
            return true;
        });
    }

    public boolean update(xuly Handle) {
        return executeTransaction(session -> {
            session.update(Handle);
            return true;
        });
    }

    public boolean delete(int id) {
        return executeTransaction(session -> {
            xuly Handle = session.get(xuly.class, id);
            if (Handle != null) {
                session.delete(Handle);
            }
            return true;
        });
    }

    public xuly getHandleById(int id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.get(xuly.class, id);
        }
    }

    public List<xuly> getHandleList() {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.createQuery("from xuly", xuly.class).list();
        }
    }
     public List<xuly> search(String keyword) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            String queryString = "from xuly where LOWER(CONCAT(MaXL, MaTV, HinhThucXL, SoTien, NgayXL,TrangThaiXL)) like :keyword";
            Query<xuly> query = session.createQuery(queryString, xuly.class);
            query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
            return query.list();
        }
    }
}