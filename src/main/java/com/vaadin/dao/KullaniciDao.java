package com.vaadin.dao;

import com.vaadin.domain.Kullanici;
import com.vaadin.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KullaniciDao {

    public Kullanici kullaniciyiKaydet(Kullanici kullanici){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();
            kullanici = (Kullanici) session.merge(kullanici);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return kullanici;
    }

    public List<Kullanici> tumKullanicilariGetir(){
        List<Kullanici> kullaniciList = null;
        Map<String, Kullanici> kullaniciMap = new HashMap();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            String hql = "Select kullanici From Kullanici kullanici";
            Query query = session.createQuery(hql);
            kullaniciList = query.list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return kullaniciList;
    }

    public Kullanici kullaniciyiGetir(String email, String sifre){
        Kullanici kullanici = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            String hql = "Select kullanici From Kullanici kullanici where  kullanici.email =:email and kullanici.sifre=:sifre";
            Query query = session.createQuery(hql);
            query.setParameter("email", email);
            query.setParameter("sifre", sifre);
            kullanici = (Kullanici) query.uniqueResult();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return kullanici;
    }
}
