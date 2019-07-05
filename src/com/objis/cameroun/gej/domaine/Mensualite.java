package com.objis.cameroun.gej.domaine;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Mensualite extends Mois implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String matricule;
	
	@OneToOne(cascade=CascadeType.PERSIST) 
	private Eleve eleve;
	private Date date;
	private String Annee;
	
	public Mensualite() {
		super();
	}

	public Mensualite(String matricule, Eleve eleve, Date date, String annee) {
		super();
		this.matricule = matricule;
		this.eleve = eleve;
		this.date = date;
		Annee = annee;
	}

	public Mensualite(String matricule, Eleve eleve, Date date, String annee, int janvier, int fevrier, int mars, int avril, int mai, int juin, int julliet, int aout,
			int septembre, int octobre, int november, int decembre) {
		super(janvier, fevrier, mars, avril, mai, juin, julliet, aout, septembre, octobre, november, decembre);

		this.matricule = matricule;
		this.eleve = eleve;
		this.date = date;
		Annee = annee;
	}
	
	public Mensualite(int id, String matricule, Eleve eleve, Date date, String annee, int janvier, int fevrier, int mars, int avril, int mai, int juin, int julliet, int aout,
			int septembre, int octobre, int november, int decembre) {
		super(janvier, fevrier, mars, avril, mai, juin, julliet, aout, septembre, octobre, november, decembre);

		this.id = id;
		this.matricule = matricule;
		this.eleve = eleve;
		this.date = date;
		Annee = annee;
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

	public Eleve getEleve() {
		return eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getAnnee() {
		return Annee;
	}

	public void setAnnee(String annee) {
		Annee = annee;
	}

	@Override
	public String toString() {
		return "Mensualite [id=" + id + ", matricule=" + matricule + ", eleve=" + eleve + ", date=" + date + ", Annee="
				+ Annee + ", janvier=" + janvier + ", fevrier=" + fevrier + ", mars=" + mars + ", avril=" + avril
				+ ", mai=" + mai + ", juin=" + juin + ", julliet=" + julliet + ", aout=" + aout + ", septembre="
				+ septembre + ", octobre=" + octobre + ", november=" + november + ", decembre=" + decembre + "]";
	}	

}
