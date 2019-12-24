package com.vaadin.dao;

import com.vaadin.domain.Arac;
import com.vaadin.domain.Kullanici;
import com.vaadin.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AracDao {

    public Arac araciKaydet(Arac arac){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();
            arac = (Arac) session.merge(arac);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return arac;
    }

    public List<Arac> tumAraclariGetir(){
        List<Arac> aracList = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            String hql = "Select arac From Arac arac";
            Query query = session.createQuery(hql);
            aracList = query.list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return aracList;
    }

    public void deleteArac(Arac arac) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();
            session.delete(arac);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
