package com.project.DAL;

import com.project.Entity.Handle;
import com.project.Entity.Handle;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.project.DAL.BaseDAL.instance;

public class HandleDAL {
    public boolean createHandle(Handle Handle) {
        Transaction transaction = null;
        try (Session session = instance().openSession()) {
            transaction = session.beginTransaction();
            session.save(Handle);
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
    public boolean updateHandle(Handle Handle) {
        Transaction transaction = null;
        try (Session session = instance().openSession()) {
            transaction = session.beginTransaction();
            session.update(Handle);
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
    public boolean deleteHandle(int id) {
        Transaction transaction = null;
        try (Session session = instance().openSession()) {
            transaction = session.beginTransaction();
            Handle Handle = session.get(Handle.class, id);
            if (Handle != null) {
                session.delete(Handle);
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
    public Handle getHandle(int id) {
        try (Session session = instance().openSession()) {
            return session.get(Handle.class, id);
        }
    }

    public List<Handle> listHandle() {
        Transaction tx = null;
        List<Handle> handleList = new ArrayList<>();

        try (Session session = instance().openSession()){
            tx = session.beginTransaction();
            List list = session.createQuery("From Handle").list();
            for(Iterator iterator = list.iterator(); iterator.hasNext();){
                Handle handle = (Handle) iterator.next();
                handleList.add(handle);
            }
            tx.commit();
            return handleList;
        }catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
