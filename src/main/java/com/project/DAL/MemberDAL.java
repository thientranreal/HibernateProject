package com.project.DAL;

import com.project.Entity.Handle;
import com.project.Entity.Member;
import com.project.Entity.UsageInformation;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.project.DAL.BaseDAL.instance;

public class MemberDAL {
    public boolean createMember(Member member) {
        Transaction transaction = null;
        try (Session session = instance().openSession()) {
            transaction = session.beginTransaction();
            session.save(member);
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
    public boolean updateMember(Member member) {
        Transaction transaction = null;
        try (Session session = instance().openSession()) {
            transaction = session.beginTransaction();
            session.update(member);
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
    public boolean deleteMember(int id) {
        Transaction transaction = null;
        try (Session session = instance().openSession()) {
            transaction = session.beginTransaction();
            Member member = session.get(Member.class, id);
            if (member != null) {
                session.delete(member);
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
    public Member getMember(int id) {
        try (Session session = instance().openSession()) {
            return session.get(Member.class, id);
        }
    }

    public List<Member> listMember() {
        Transaction tx = null;
        List<Member> memberList = new ArrayList<>();

        try (Session session = instance().openSession()){
            tx = session.beginTransaction();
            List list = session.createQuery("From Member").list();
            for(Iterator iterator = list.iterator(); iterator.hasNext();){
                Member member = (Member) iterator.next();
                memberList.add(member);
            }
            tx.commit();
            return memberList;
        }catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }
}
