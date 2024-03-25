package com.project.DAL;

import com.project.Entity.UsageInformation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.project.DAL.BaseDAL.instance;

public class UsageInformationDAL {
    public boolean createUsageInformation(UsageInformation info) {
        Transaction transaction = null;
        try (Session session = instance().openSession()) {
            transaction = session.beginTransaction();
            session.save(info);
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
    }

    //    Update
    public boolean updateUsageInformation(UsageInformation info) {
        Transaction transaction = null;
        try (Session session = instance().openSession()) {
            transaction = session.beginTransaction();
            session.update(info);
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return false;
    }

    //    Delete
    public boolean deleteUsageInformation(int id) {
        Transaction transaction = null;
        try (Session session = instance().openSession()) {
            transaction = session.beginTransaction();
            UsageInformation info = session.get(UsageInformation.class, id);
            if (info != null) {
                session.delete(info);
            }
            transaction.commit();

            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return false;
    }

    //    Read
    public UsageInformation getUsageInformation(int id) {
        try (Session session = instance().openSession()) {
            return session.get(UsageInformation.class, id);
        }
    }

    public List<UsageInformation> listUsageInformation() {
        Transaction tx = null;
        List<UsageInformation> usageList = new ArrayList<>();

        try (Session session = instance().openSession()){
            tx = session.beginTransaction();
            List info = session.createQuery("From UsageInformation").list();
            for(Iterator iterator = info.iterator(); iterator.hasNext();){
                UsageInformation currentInfo = (UsageInformation) iterator.next();
                usageList.add(currentInfo);
            }
            tx.commit();
            return usageList;
        }catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
