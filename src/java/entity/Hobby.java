/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author butwhole
 */
@Entity
public class Hobby implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String name;
    private String disc;

    @ManyToMany(mappedBy = "hobbies")
    private List<Person> persons = new ArrayList();


    public Hobby(String name, String disc) {
        this.name = name;
        this.disc = disc;
    }
    

    public Hobby() {
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void addPersons(Person pers) {
        this.persons.add(pers);
    }
    
    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }
    
    
    
}
