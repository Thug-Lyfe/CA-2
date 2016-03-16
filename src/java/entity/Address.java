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

    public Address(String street, String additonalInfo, CityInfo shityInfo) {
        this.street = street;
        this.additonalInfo = additonalInfo;
        this.shityInfo = shityInfo;
    }

    @OneToMany(mappedBy = "hood")
    private List<InfoEntity> inHoes = new ArrayList();

    @ManyToOne(cascade = CascadeType.PERSIST)
    private CityInfo shityInfo;

    public List<InfoEntity> getInHoes() {
        return inHoes;
    }

    public void setInHoes(List<InfoEntity> inHoes) {
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

   
    public void addInHoes(InfoEntity inhoes) {
        this.inHoes.add(inhoes);
    }
    
    
    
    
}
