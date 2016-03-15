/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author butwhole
 */
@Entity
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String street;
    private String additonalInfo;
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "hood")
    private ArrayList<InfoEntity> inHoes = new ArrayList();
    @ManyToOne
    private CityInfo shityInfo;

    public ArrayList<InfoEntity> getInHoes() {
        return inHoes;
    }

    public void setInHoes(ArrayList<InfoEntity> inHoes) {
        this.inHoes = inHoes;
    }

    public CityInfo getShityInfo() {
        return shityInfo;
    }

    public void setShityInfo(CityInfo shityInfo) {
        this.shityInfo = shityInfo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditonalInfo() {
        return additonalInfo;
    }

    public void setAdditonalInfo(String additonalInfo) {
        this.additonalInfo = additonalInfo;
    }

    public ArrayList<InfoEntity> getInhoes() {
        return inHoes;
    }

    public void addInhoes(InfoEntity inhoes) {
        this.inHoes.add(inhoes);
    }
    
    public void setInhoes(ArrayList<InfoEntity> inhoes) {
        this.inHoes = inhoes;
    }
    
    
    
    
}
