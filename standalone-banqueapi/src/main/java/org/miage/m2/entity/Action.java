package org.miage.m2.entity;
import java.io.Serializable;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author Rémy
 */
@Entity
public class Action implements Serializable {
    /**
	 * Section des attributs
	 */
    @Id
    @JsonProperty ("id")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private String id;
    private Integer numero;
    private String nom;
    private String personnecharge;
    private String etat;
    private String date;
    @ManyToOne
    @JoinColumn(name="iddemande")
    private Demande demande;
    /** 
     * Section des getters/setters
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
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

    public Demande getDemande() {
        return demande;
    }

    public void setDemande(Demande demande) {
        this.demande = demande;
    }
<<<<<<< HEAD
    public Action(String id, Integer numero, String nom, String personnecharge, String etat, String date) {
        this.id = id;
        this.numero = numero;
        this.nom = nom;
        this.personnecharge = personnecharge;
        this.etat = etat;
        this.date = date;
        
}

	public Action() {
	}

=======
>>>>>>> 6ae0c142831669d650a51cc6c257650338e165a9
}