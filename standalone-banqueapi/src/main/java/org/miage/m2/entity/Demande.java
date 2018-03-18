package org.miage.m2.entity;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name="DEMANDE")
public class Demande implements Serializable {
    /**
	 * Section des attributs
	 */
	@Id
	@JsonProperty ("id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	private String nom;
	private String prenom;
	private String adresse;
	@Column(name="DATENAISSANCE")
    private String dateNaissance;
	private int revenus;
	@Column(name="MONTANTCREDITDEMANDE")
	private int montantCreditDemande;
	@Column(name="DUREECREDIT")
	private int dureeCredit;
	@Enumerated(EnumType.STRING)
	@Column(name="ETATCOURANTDEMANDE")
	private statutDemande etatCourantDemande;
	@ElementCollection
	@JsonProperty ("actions")
	@OneToMany(cascade=CascadeType.ALL,mappedBy="demande")
	private Set<Action> actions;
    /** 
     * Section des getters/setters
     */
	public Set<Action> getActions() {
		return actions;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return this.prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getDatenaissance() {
		return this.dateNaissance;
	}

	public void setDatenaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public int getRevenus() {
		return this.revenus;
	}

	public void setRevenus(int revenus) {
		this.revenus = revenus;
	}

	public int getMontantcreditdemande() {
		return this.montantCreditDemande;
	}

	public void setMontantcreditdemande(int montantCreditDemande) {
		this.montantCreditDemande = montantCreditDemande;
	}

	public int getDureecredit() {
		return this.dureeCredit;
	}

	public void setDureecredit(int dureeCredit) {
		this.dureeCredit = dureeCredit;
	}

	public statutDemande getEtatcourantdemande() {
		return this.etatCourantDemande;
	}

	public void setEtatcourantdemande(statutDemande etatCourantDemande) {
		this.etatCourantDemande = etatCourantDemande;
	}
	public void addActions(Action action) {
        //Si une action n'est pas déja enregistrée, on la save
        this.actions.add(action);
	}
}
