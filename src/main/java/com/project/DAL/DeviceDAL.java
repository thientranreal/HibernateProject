package com.project.DAL;

import com.project.Entity.Device;
import com.project.Entity.Device;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.project.DAL.BaseDAL.instance;

public class DeviceDAL extends BaseDAL{
    public boolean createDevice(Device device) {
        Transaction transaction = null;
        try (Session session = instance().openSession()) {
            transaction = session.beginTransaction();
            session.save(device);
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
    public boolean updateDevice(Device device) {
        Transaction transaction = null;
        try (Session session = instance().openSession()) {
            transaction = session.beginTransaction();
            session.update(device);
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
    public boolean deleteDevice(int id) {
        Transaction transaction = null;
        try (Session session = instance().openSession()) {
            transaction = session.beginTransaction();
            Device device = session.get(Device.class, id);
            if (device != null) {
                session.delete(device);
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
    public Device getDevice(int id) {
        try (Session session = instance().openSession()) {
            return session.get(Device.class, id);
        }
    }

    public List<Device> listDevice() {
        Transaction tx = null;
        List<Device> DeviceList = new ArrayList<>();

        try (Session session = instance().openSession()) {
            tx = session.beginTransaction();
            List list = session.createQuery("From Device").list();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                Device Device = (Device) iterator.next();
                DeviceList.add(Device);
            }
            tx.commit();
            return DeviceList;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

}
