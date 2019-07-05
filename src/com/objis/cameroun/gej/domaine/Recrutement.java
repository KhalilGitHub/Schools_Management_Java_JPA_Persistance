package com.objis.cameroun.gej.domaine;


import java.sql.Blob;
import java.util.Date;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Recrutement implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int nombreHeure;
	private String matricule;
	
	@OneToOne(cascade=CascadeType.PERSIST) 
	private Enseignant enseignant;
    private BigDecimal fraisParHeure;
    private BigDecimal salaire;
    private Date date;
    private Blob image;
    private String  photo;
    
    
	public Recrutement() {
		super();
	}
	
	
	public Recrutement(String matricule, Enseignant enseignant, int nombreHeure, BigDecimal fraisParHeure, BigDecimal salaire, Date date) {
		super();
		this.nombreHeure = nombreHeure;
		this.matricule = matricule;
		this.enseignant = enseignant;
		this.fraisParHeure = fraisParHeure;
		this.salaire = salaire;
		this.date = date;
	}


	public Recrutement(String matricule, Enseignant enseignant, int nombreHeure,  BigDecimal fraisParHeure,
			BigDecimal salaire, Date date, String photo) {
		super();
		this.nombreHeure = nombreHeure;
		this.matricule = matricule;
		this.enseignant = enseignant;
		this.fraisParHeure = fraisParHeure;
		this.salaire = salaire;
		this.date = date;
		this.photo = photo;
	}


	public Recrutement(String matricule, Enseignant enseignant, int nombreHeure, BigDecimal fraisParHeure,
			BigDecimal salaire, Date date, Blob image) {
		super();
		this.nombreHeure = nombreHeure;
		this.matricule = matricule;
		this.enseignant = enseignant;
		this.fraisParHeure = fraisParHeure;
		this.salaire = salaire;
		this.date = date;
		this.image = image;
	}

	

	public Recrutement(long id, int nombreHeure, String matricule, Enseignant enseignant,
			BigDecimal fraisParHeure, BigDecimal salaire, Date date, Blob image) {
		super();
		this.id = id;
		this.nombreHeure = nombreHeure;
		this.matricule = matricule;
		this.enseignant = enseignant;
		this.fraisParHeure = fraisParHeure;
		this.salaire = salaire;
		this.date = date;
		this.image = image;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getNombreHeure() {
		return nombreHeure;
	}


	public void setNombreHeure(int nombreHeure) {
		this.nombreHeure = nombreHeure;
	}


	public String getMatricule() {
		return matricule;
	}


	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}


	public Enseignant getEnseignant() {
		return enseignant;
	}


	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}


	public BigDecimal getFraisParHeure() {
		return fraisParHeure;
	}


	public void setFraisParHeure(BigDecimal fraisParHeure) {
		this.fraisParHeure = fraisParHeure;
	}


	public BigDecimal getSalaire() {
		return salaire;
	}


	public void setSalaire(BigDecimal salaire) {
		
		
		
		this.salaire = salaire;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Blob getImage() {
		return image;
	}


	public void setImage(Blob image) {
		this.image = image;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	@Override
	public String toString() {
		return  "[matricule=" + matricule + ", enseignant=" + enseignant + ", nombreHeure=" + nombreHeure  + ", fraisParHeure=" + fraisParHeure + ", salaire=" + salaire + ", date="
				+ date + ", image=" + image + "]";
	}
	
}
