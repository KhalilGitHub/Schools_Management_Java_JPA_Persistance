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
public class Comportement extends Mois implements Serializable{
	
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
	
	private String conduite;
	private long nbreHeureAbscence;
	private String sanction;

	public Comportement() {
		super();
	}

	public Comportement(String matricule, Date date, String annee, String conduite, long nbreHeureAbscence,
			String sanction, int janvier, int fevrier, int mars, int avril, int mai, int juin, int julliet, int aout, int septembre,
			int octobre, int november, int decembre) {
		super(janvier, fevrier, mars, avril, mai, juin, julliet, aout, septembre, octobre, november, decembre);
		this.matricule = matricule;
		this.date = date;
		Annee = annee;
		this.conduite = conduite;
		this.nbreHeureAbscence = nbreHeureAbscence;
		this.sanction = sanction;
	}
	
	public Comportement(int id,String matricule, Date date, String annee, String conduite, long nbreHeureAbscence,
			String sanction,  int janvier, int fevrier, int mars, int avril, int mai, int juin, int julliet, int aout, int septembre,
			int octobre, int november, int decembre) {
		super(janvier, fevrier, mars, avril, mai, juin, julliet, aout, septembre, octobre, november, decembre);
		this.id = id;
		this.matricule = matricule;
		this.date = date;
		Annee = annee;
		this.conduite = conduite;
		this.nbreHeureAbscence = nbreHeureAbscence;
		this.sanction = sanction;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	public Eleve getEleve() {
		return eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public String getAnnee() {
		return Annee;
	}

	public void setAnnee(String annee) {
		Annee = annee;
	}

	public String getConduite() {
		return conduite;
	}

	public void setConduite(String conduite) {
		this.conduite = conduite;
	}

	public long getNbreHeureAbscence() {
		return nbreHeureAbscence;
	}

	public void setNbreHeureAbscence(long nbreHeureAbscence) {
		this.nbreHeureAbscence = nbreHeureAbscence;
	}

	public String getSanction() {
		return sanction;
	}

	public void setSanction(String sanction) {
		this.sanction = sanction;
	}

	@Override
	public String toString() {
		return "Comportement [matricule=" + matricule + ", date=" + date + ", Annee=" + Annee + ", conduite=" + conduite
				+ ", nbreHeureAbscence=" + nbreHeureAbscence + ", sanction=" + sanction + ",janvier=" + janvier + ", fevrier=" + fevrier + ", mars=" + mars + ", avril=" + avril + ", mai="
						+ mai + ", juin=" + juin + ", julliet=" + julliet + ", aout=" + aout + ", septembre=" + septembre
						+ ", octobre=" + octobre + ", november=" + november + ", decembre=" + decembre + "]";
	}
	
	
	

}
