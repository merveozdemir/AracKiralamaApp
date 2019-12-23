package com.vaadin.dao;

import com.vaadin.domain.Arac;
import com.vaadin.domain.Kiralama;
import com.vaadin.domain.Kullanici;
import com.vaadin.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KiralamaDao {

    public Kiralama kiralamaKaydet(Kiralama kiralama) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();
            kiralama = (Kiralama) session.merge(kiralama);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return kiralama;
    }

    public List<Kiralama> tumKiralamalariGetir() {
        List<Kiralama> kiralamaList = null;
        Map<String, Kiralama> kiralamaMap = new HashMap();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            String hql = "Select kiralama from Kiralama as kiralama left join fetch kiralama.arac arac left join fetch kiralama.kullanici kullanici";
            Query query = session.createQuery(hql);
            kiralamaList = query.list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return kiralamaList;
    }

    public List<Kiralama> uyeyeGoreKiralamalariGetir(Long kullaniciId){
        List<Kiralama> kiralamaList = null;
        Map<String, Kiralama> kiralamaMap = new HashMap();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            String hql = "Select kiralama from Kiralama as kiralama left join fetch kiralama.arac arac left join fetch kiralama.kullanici kullanici where kullanici.id =:kullaniciId";
            Query query = session.createQuery(hql);
            query.setParameter("kullaniciId", kullaniciId);
            kiralamaList = query.list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return kiralamaList;
    }

    public void deleteKiralama(Kiralama kiralama) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();
            String hql = "delete from Kiralama kiralama where kiralama.id=:id";
            Query query = session.createQuery(hql);
            query.setLong("id", kiralama.getId());
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
