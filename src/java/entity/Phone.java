/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author butwhole
 */
@Entity
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private int number;
    private String disc;
    @ManyToOne
    private InfoEntity hoe;

    public Phone(int number, String disc) {
        this.number = number;
        this.disc = disc;
    }

    public Phone() {
    }

    public InfoEntity getHoe() {
        return hoe;
    }

    public void setHoe(InfoEntity hoe) {
        this.hoe = hoe;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public int getNumber() {
        return number;
    }

    public void setId(int id) {
        this.number = id;
    }

}
