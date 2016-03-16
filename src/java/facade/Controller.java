/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Address;
import entity.CityInfo;
import entity.Company;
import entity.Hobby;
import entity.InfoEntity;
import entity.Person;
import entity.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author butwhole
 */
public class Controller {

    public static void main(String[] args) {
        Controller cc = new Controller();
        Persistence.generateSchema("PU", null);
        Person p1 = new Person("Marco", "Blum");
        Person p2 = new Person("David", "Blum");
        Hobby h1 = new Hobby("yeaaaassss23456s", "asdfghjk");
        cc.addPerson(p1);
        cc.addPerson(p2);
        cc.addHobby(h1);
        cc.hobbifyPerson(1, "yeaaaassss23456s");
        cc.hobbifyPerson(2, "yeaaaassss23456s");

    }
    EntityManagerFactory emf;
    EntityManager em;

    public Controller() {
        emf = Persistence.createEntityManagerFactory("PU");

    }

    public List<Person> getPersonlist() {

        em = emf.createEntityManager();
        try {
            List<Person> persons;
            Query q = em.createQuery("SELECT c from Person c");
            persons = q.getResultList();
            return persons;
        } finally {
            em.close();
        }
    }

    public Person getPerson(int id) {
        em = emf.createEntityManager();
        try {
            Person p = em.find(Person.class, id);
            return p;
        } finally {
            em.close();
        }
    }

    public Company getCompany(int id) {
        em = emf.createEntityManager();
        try {
            Company c = em.find(Company.class, id);
            return c;
        } finally {
            em.close();
        }
    }

    public List<Person> getPersonByHobby(String hobby) {
        em = emf.createEntityManager();
        try {
            Hobby h = em.find(Hobby.class, hobby);
            List<Person> people = h.getPersons();
            return people;
        } finally {
            em.close();
        }
    }

    public List<Person> getPersonByCity(int zip) {
        em = emf.createEntityManager();
        try {
            CityInfo ci = em.find(CityInfo.class, zip);
            List<Person> persons = new ArrayList();
            for (Address a : ci.getHoods()) {
                for (InfoEntity ie : a.getInHoes()) {
                    if (em.find(Person.class, ie.getId()) != null) {
                        persons.add(em.find(Person.class, ie.getId()));

                    }
                }
            }
            return persons;
        } finally {
            em.close();
        }
    }

    public int getHobbyCount(String hobby) {
        em = emf.createEntityManager();
        try {
            Hobby h = em.find(Hobby.class, hobby);
            return h.getPersons().size();
        } finally {
            em.close();
        }
    }

    public List<CityInfo> getCities() {
        em = emf.createEntityManager();
        try {
            TypedQuery<CityInfo> tq = em.createQuery("Select c from CityInfo c", CityInfo.class);
            return tq.getResultList();

        } finally {
            em.close();
        }
    }

    public List<Company> getCompanyByEmployees(int employeeCount) {
        em = emf.createEntityManager();
        try {
            Query q = em.createQuery("select c from Company c where c.numEmployees >= :num");
            q.setParameter("num", employeeCount);
            return q.getResultList();

        } finally {
            em.close();
        }
    }

    public Person addPerson(Person p) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }

    }

    public Person editPerson(Person p) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Person edited = em.find(Person.class, p.getId());
            edited.setFirstName(p.getFirstName());
            edited.setLastName(p.getLastName());
            edited.setEmail(p.getEmail());
            edited.setHobbies(p.getHobbies());
            edited.setHood(p.getHood());
            edited.setPhonies(p.getPhonies());
            em.merge(edited);
            em.getTransaction().commit();
            return edited;
        } finally {
            em.close();
        }

    }

    public void deletePerson(int id) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Person p = em.find(Person.class, id);
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Company addCompany(Company c) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return c;
        } finally {
            em.close();
        }

    }

    public Company editCompany(Company c) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Company edited = em.find(Company.class, c.getId());
            edited.setName(c.getName());
            edited.setDisc(c.getDisc());
            edited.setCvr(c.getCvr());
            edited.setNumEmployees(c.getNumEmployees());
            edited.setMarketValue(c.getMarketValue());
            edited.setEmail(c.getEmail());
            edited.setPhonies(c.getPhonies());
            edited.setHood(c.getHood());
            em.merge(edited);
            em.getTransaction().commit();
            return edited;
        } finally {
            em.close();
        }

    }

    public void deleteCompany(int id) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Company c = em.find(Company.class, id);
            em.remove(c);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Hobby addHobby(Hobby h) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(h);
            em.getTransaction().commit();
            return h;
        } finally {
            em.close();
        }

    }

    public Hobby editHobby(Hobby h) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Hobby edited = em.find(Hobby.class, h.getName());
            edited.setName(h.getName());
            edited.setDisc(h.getDisc());
            edited.setPersons(h.getPersons());
            em.merge(edited);
            em.getTransaction().commit();
            return edited;
        } finally {
            em.close();
        }

    }

    public void deleteHobby(int id) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Hobby p = em.find(Hobby.class, id);
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Phone addPhone(InfoEntity i, Phone p) {
        i.addPhonies(p);
        p.setHoe(i);
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }

    }

    public Phone editPhone(Phone p) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Phone edited = em.find(Phone.class, p.getNumber());
            edited.setDisc(p.getDisc());
            edited.setHoe(p.getHoe());
            em.merge(edited);
            em.getTransaction().commit();
            return edited;
        } finally {
            em.close();
        }

    }

    public void deletePhone(int number) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Phone p = em.find(Phone.class, number);
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Address addAddress(Address p) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
            return p;
        } finally {
            em.close();
        }

    }

    public Address editAddress(Address p) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Address edited = em.find(Address.class, p.getStreet());
            edited.setAdditonalInfo(p.getAdditonalInfo());
            edited.setInHoes(p.getInHoes());
            edited.setShityInfo(p.getShityInfo());
            em.merge(edited);
            em.getTransaction().commit();
            return edited;
        } finally {
            em.close();
        }

    }

    public void deleteAddress(String street) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Address p = em.find(Address.class, street);
            em.remove(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void hobbifyPerson(int id, String hobby) {
        em = emf.createEntityManager();
        try {
            Person p = em.find(Person.class, id);
            Hobby h = em.find(Hobby.class, hobby);
            em.getTransaction().begin();
            p.addHobby(h);
            h.addPersons(p);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

}
