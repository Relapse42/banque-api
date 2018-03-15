package org.miage.m2.entity;
import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class Action implements Serializable {
    @Id
    @JsonProperty ("id")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private String id;
    private String nom;
    private String personnecharge;
    private String etat;
    private String date;
    
    public Action() {
    }

    
    public Action(String id, String nom, String personnecharge, String etat, String date) {
        this.id = id;
        this.nom = nom;
        this.personnecharge = personnecharge;
        this.etat = etat;
        this.date = date;
    }
   

    
    public String getId() {
        return id;
    }
    

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPersonnecharge() {
        return personnecharge;
    }

    public void setPersonnecharge(String personneEnCharge) {
        this.personnecharge = personneEnCharge;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}