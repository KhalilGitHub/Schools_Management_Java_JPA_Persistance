package com.objis.cameroun.gej.domaine;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Eleve extends Personne implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;


	private int age;
	private String classe;
	private String matricule;
	
	public Eleve() {
		super();
	}


	public Eleve(long id, int age, String classe) {
		super();
		this.id = id;
		this.age = age;
		this.classe = classe;
	}
	
	public Eleve(long id, String matricule, String nom, String prenom, String genre, String adresse, int age, String classe) {
		super(nom, prenom, genre, adresse);
		this.id = id;
		this.age = age;
		this.classe = classe;
		this.matricule = matricule;
	}

	


	public Eleve(String matricule, String nom, String prenom, String genre, String adresse, int age, String classe) {
		super(nom, prenom, genre, adresse);
		this.age = age;
		this.classe = classe;
		this.matricule = matricule;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	
	
	public String getMatricule() {
		return matricule;
	}


	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getClasse() {
		return classe;
	}


	public void setClasse(String classe) {
		this.classe = classe;
	}


	@Override
	public String toString() {
		return "Eleve [id=" + id + ", age=" + age + ", classe=" + classe + ", nom=" + nom + ", prenom=" + prenom
				+ ", genre=" + genre + ", adresse=" + adresse + "]";
	}
			
}
