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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author butwhole
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;

    @OneToMany(mappedBy = "hoe", cascade = CascadeType.PERSIST)
    private List<Phone> phonies = new ArrayList();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Address hood;

    public List<Phone> getPhonies() {
        return phonies;
    }

    public void setPhonies(List<Phone> phonies) {
        this.phonies = phonies;
    }

    public void addPhonies(Phone phonies) {
        this.phonies.add(phonies);
    }

    public Address getHood() {
        return hood;
    }

    public void setHood(Address hood) {
        this.hood = hood;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
