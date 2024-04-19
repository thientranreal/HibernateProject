package com.project.DAL;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.project.models.thietbi;
import com.project.utilities.HibernateUtil;
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

    public List<thietbi> search(String keyword) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            String queryString = "from thongtinsd where LOWER(CONCAT(MaTB, TenTB, MoTaTB)) like :keyword";
            Query<thietbi> query = session.createQuery(queryString, thietbi.class);
            query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
            return query.list();
        }
    }
    public List<thietbi> searchbyMonth(String monthName) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            String queryString =  "SELECT thietbi.MaTB, thietbi.TenTB, thietbi.MoTaTB, thongtinsd.MaTV, thanhvien.HoTen, thongtinsd.TGMuon, thongtinsd.TGTra " +
             "FROM thietbi " +
             "INNER JOIN thongtinsd ON thietbi.MaTB = thongtinsd.MaTB " +
             "WHERE MONTHNAME(thongtinsd.TGMuon) = :keyword";
            Query<thietbi> query = session.createQuery(queryString, thietbi.class);
            query.setParameter("keyword", monthName);
            return query.list();
        }
    }
    public List<thietbi> searchbyName(String keyword) {
        try (Session session = HibernateUtil.getInstance().openSession()) {
            String queryString = "SELECT thietbi.MaTB, thietbi.TenTB, thietbi.MoTaTB, thongtinsd.MaTV, thanhvien.HoTen, thongtinsd.TGMuon, thongtinsd.TGTra " +
             "FROM thietbi " +
             "WHERE thongtinsd.TenTB like :keyword";
            Query<thietbi> query = session.createQuery(queryString, thietbi.class);
            query.setParameter("keyword", "%" + keyword.toLowerCase() + "%");
            return query.list();
        }
    }
}
