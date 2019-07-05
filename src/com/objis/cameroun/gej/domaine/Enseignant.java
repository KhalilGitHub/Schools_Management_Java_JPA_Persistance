package com.objis.cameroun.gej.domaine;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Enseignant extends Personne implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int nombreMatieres;
	private String premiereMatiere;
	private String deuxiemeMatiere;
	
	
	public Enseignant() {
		
		super();
		
	}


	public Enseignant(int nombreMatieres, String premiereMatiere, String deuxiemeMatiere) {
		
		super();
		this.nombreMatieres = nombreMatieres;
		this.premiereMatiere = premiereMatiere;
		this.deuxiemeMatiere = deuxiemeMatiere;
	}

	
	public Enseignant(String nom, String prenom, String genre, String adresse, int nombreMatieres, String premiereMatiere, String deuxiemeMatiere) {
		
		super(nom,  prenom, genre, adresse);
		this.nombreMatieres = nombreMatieres;
		this.premiereMatiere = premiereMatiere;
		this.deuxiemeMatiere = deuxiemeMatiere;
	}
	
	
	
	public Enseignant(int id, String nom, String prenom, String genre, String adresse , int nombreMatieres, String premiereMatiere, String deuxiemeMatiere) {
		super(nom, prenom, genre, adresse);
		this.id = id;
		this.nombreMatieres = nombreMatieres;
		this.premiereMatiere = premiereMatiere;
		this.deuxiemeMatiere = deuxiemeMatiere;

	}

	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getNombreMatieres() {
		return nombreMatieres;
	}


	public void setNombreMatieres(int nombreMatieres) {
		this.nombreMatieres = nombreMatieres;
	}


	public String getPremiereMatiere() {
		return premiereMatiere;
	}


	public void setPremiereMatiere(String premiereMatiere) {
		this.premiereMatiere = premiereMatiere;
	}


	public String getDeuxiemeMatiere() {
		return deuxiemeMatiere;
	}


	public void setDeuxiemeMatiere(String deuxiemeMatiere) {
		this.deuxiemeMatiere = deuxiemeMatiere;
	}


	@Override
	public String toString() {
		return  "nom=" + nom + ", prenom=" + prenom + ", genre=" + genre + ", adresse=" + adresse + "; nombreMatieres=" + nombreMatieres + ", premiereMatiere=" + premiereMatiere + ", deuxiemeMatiere=" + deuxiemeMatiere + "]";
	}


	


	
	
	
}
