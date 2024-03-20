package com.project.DAL;

import com.project.Entity.Example;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ExampleDAL extends BaseDAL{

//    Insert into
    public void createDepartment(Example department) {
        Transaction transaction = null;
        try (Session session = instance().openSession()) {
            transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

//    Update
    public void updateDepartment(Example department) {
        Transaction transaction = null;
        try (Session session = instance().openSession()) {
            transaction = session.beginTransaction();
            session.update(department);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

//    Delete
    public void deleteDepartment(int id) {
        Transaction transaction = null;
        try (Session session = instance().openSession()) {
            transaction = session.beginTransaction();
            Example department = session.get(Example.class, id);
            if (department != null) {
                session.delete(department);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

//    Read
    public Example getDepartment(int id) {
        try (Session session = instance().openSession()) {
            return session.get(Example.class, id);
        }
    }
}
