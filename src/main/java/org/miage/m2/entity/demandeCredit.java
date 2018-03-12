package org.miage.m2.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

@Entity(name="DEMANDECREDIT")
public class demandeCredit implements Serializable {
	@Id
	private String id;
	@JsonProperty ("id")
    private String idDemande;
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
    
	public String getId()
	{
		return this.id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getIddemande()
	{
		return id;
	}
	public String getNom()
	{
		return this.nom;
	}

	public void setNom(String nom)
	{
		this.nom = nom;
	}

	public String getPrenom()
	{
		return this.prenom;
	}

	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	public String getAdresse()
	{
		return this.adresse;
	}

	public void setAdresse(String adresse)
	{
		this.adresse = adresse;
	}

	public String getDatenaissance()
	{
		return this.dateNaissance;
	}

	public void setDatenaissance(String dateNaissance)
	{
		this.dateNaissance = dateNaissance;
	}

	public int getRevenus()
	{
		return this.revenus;
	}

	public void setRevenus(int revenus)
	{
		this.revenus = revenus;
	}

	public int getMontantcreditdemande()
	{
		return this.montantCreditDemande;
	}

	public void setMontantcreditdemande(int montantCreditDemande)
	{
		this.montantCreditDemande = montantCreditDemande;
	}

	public int getDureecredit()
	{
		return this.dureeCredit;
	}

	public void setDureecredit(int dureeCredit)
	{
		this.dureeCredit = dureeCredit;
	}

	public statutDemande getEtatcourantdemande()
	{
		return this.etatCourantDemande;
	}

	public void setEtatcourantdemande(statutDemande etatCourantDemande)
	{
		this.etatCourantDemande = etatCourantDemande;
	}

    public demandeCredit() {
    }
    public demandeCredit(String nom, String prenom, String adresse, String dateNaissance, int revenus, int montantCreditDemande, int dureeCredit, statutDemande etatCourantDemande) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.dateNaissance = dateNaissance;
        this.revenus = revenus;
        this.montantCreditDemande = montantCreditDemande;
        this.dureeCredit = dureeCredit;
        this.etatCourantDemande = etatCourantDemande;
    }

}
