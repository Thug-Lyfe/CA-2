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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author butwhole
 */
public class Controller {
    
    public static void main(String[] args) {
        Persistence.generateSchema("PU", null);

    }
    EntityManagerFactory emf;
        EntityManager em;

    public Controller() {
        emf = Persistence.createEntityManagerFactory("PU");
        
        
    }
    
    public Person getPerson(int id){
        em = emf.createEntityManager();
        try {
        Person p = em.find(Person.class, id);
        return p;    
        } finally {
            em.close();
        }
       }
    
    public Company getCompany(int id){
        em = emf.createEntityManager();
        try {
        Company c = em.find(Company.class, id);
        return c;    
        } finally {
            em.close();
        }
       }
    
    public List<Person> getPersonByHobby(String hobby){
        em = emf.createEntityManager();
        try {
        Hobby h = em.find(Hobby.class, hobby);
        return h.getPersons();    
        } finally {
            em.close();
        }
       }
    
    public List<Person> getPersonByCity(int zip){
        em = emf.createEntityManager();
        try {
        CityInfo ci = em.find(CityInfo.class, zip);
        List<Person> persons = new ArrayList();
            for (Address a : ci.getHoods()) {
                for (InfoEntity ie : a.getInHoes()) {
                   if(em.find(Person.class, ie.getId()) != null){
                       persons.add(em.find(Person.class,ie.getId()));
                       
                   } 
                }
            }
        return persons;    
        } finally {
            em.close();
        }
       }
    
    public int getHobbyCount(String hobby){
        em = emf.createEntityManager();
        try {
        Hobby h = em.find(Hobby.class, hobby);
        return h.getPersons().size();
        } finally {
            em.close();
        }
       }
    
     public List<CityInfo> getHobbyCount(int zip){
        em = emf.createEntityManager();
        try {
        TypedQuery<CityInfo> tq = em.createQuery("Select c from CityInfo c",CityInfo.class);
        return tq.getResultList();
        
        } finally {
            em.close();
        }
       }
    
     public List<Company> getCompanyByEmployees(int employeeCount){
        em = emf.createEntityManager();
        try {
        Query q = em.createQuery("select c from Company c where c.numEmployees >= :num");
        q.setParameter("num", employeeCount);
        return q.getResultList();
        
        } finally {
            em.close();
        }
       }
     
     
}
