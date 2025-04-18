/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entitymanagerdemo2;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author LENOVO
 */
public class AddressTable {
    public static void insertAddress(Address dep) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemo2PU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(dep);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    public static void updateAddress(Address add) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemo2PU");
        EntityManager em = emf.createEntityManager();
        Address fromDb = em.find(Address.class, add.getId());
        fromDb.setStreet(add.getStreet());
        fromDb.setCity(add.getCity());
        fromDb.setZipcode(add.getZipcode());
        fromDb.setCountry(add.getCountry());
        em.getTransaction().begin();
        try {
            em.persist(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    public static Address findAddressById(Integer id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemo2PU");
        EntityManager em = emf.createEntityManager();
        Address dep = em.find(Address.class, id);
        em.close();
        return dep;
    }
    public static List<Address> findAllAddress() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemo2PU");
        EntityManager em = emf.createEntityManager();
        List<Address> depList = (List<Address>) em.createNamedQuery("Address.findAll").getResultList();
        em.close();
        return depList;
    }
    public static Address findAddressByName(String name) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemo2PU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Address.findByName");
        query.setParameter("name", name);
        Address dep = (Address) query.getSingleResult();
        em.close();
        return dep;
    }
    public static void removeAddress(Address add) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EntityManagerDemo2PU");
        EntityManager em = emf.createEntityManager();
        Address fromDb = em.find(Address.class, add.getId());
        em.getTransaction().begin();
        try {
            em.remove(fromDb);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
