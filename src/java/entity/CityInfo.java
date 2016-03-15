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
import javax.persistence.OneToMany;

/**
 *
 * @author butwhole
 */
@Entity
public class CityInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private int zipCode;
    private String city;
    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "shityInfo")
    private ArrayList<Address> hoods = new ArrayList();

    public CityInfo(int zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    public CityInfo() {
    }

    public ArrayList<Address> getHoods() {
        return hoods;
    }

    public void setHoods(ArrayList<Address> hoods) {
        this.hoods = hoods;
    }

    public void addHoods(Address hood) {
        this.hoods.add(hood);
    }
    
    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
}
