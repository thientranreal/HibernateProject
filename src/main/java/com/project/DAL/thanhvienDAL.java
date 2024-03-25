package com.project.DAL;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.project.utilities.HibernateUtil;
import com.project.models.thanhvien;
import java.util.List;

public class thanhvienDAL {
    private static thanhvienDAL instance;

    public static thanhvienDAL getInstance() {
        if (instance == null) {
            instance = new thanhvienDAL();
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

    public boolean create(thanhvien member) {
        return executeTransaction(session -> {
            session.save(member);
            return true;
        });
    }

    public boolean update(thanhvien member) {
        return executeTransaction(session -> {
            session.update(member);
            return true;
        });
    }

    public boolean delete(int id) {
        return executeTransaction(session -> {
            thanhvien member = session.get(thanhvien.class, id);
            if (member != null) {
                session.delete(member);
            }
            return true;
        });
    }

    public thanhvien getById(int id) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.get(thanhvien.class, id);
        }
    }

    public List<thanhvien> getMemberList() {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            return session.createQuery("from thanhvien", thanhvien.class).list();
        }
    }

}
