package com.objis.cameroun.gej.domaine;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Examen extends Notes implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String matricule;
	private Date date;
	private String annee;
	private int semestre ;
	private BigDecimal totalNotes;
	private BigDecimal moyenneNotes;

	
	
	public Examen() {
		super();
	}

	public Examen( String matricule, Date date, String annee, int semestre, BigDecimal francais, BigDecimal anglais, BigDecimal math, BigDecimal physique, BigDecimal biologie,
			BigDecimal histoire, BigDecimal geo, BigDecimal eps, BigDecimal totalNotes, BigDecimal moyenneNotes) {
		super(francais, anglais, math, physique, biologie, histoire, geo, eps);
		// TODO Auto-generated constructor stub
		this.matricule = matricule;
		this.date = date;
		this.annee = annee;
		this.semestre = semestre;
		this.totalNotes = totalNotes;
		this.moyenneNotes = moyenneNotes;
	}
	

	public Examen(int id, String matricule, Date date, String annee, int semestre, BigDecimal francais, BigDecimal anglais, BigDecimal math, BigDecimal physique, BigDecimal biologie,
			BigDecimal histoire, BigDecimal geo, BigDecimal eps, BigDecimal totalNotes, BigDecimal moyenneNotes) {
		super(francais, anglais, math, physique, biologie, histoire, geo, eps);
		// TODO Auto-generated constructor stub
		this.id = id;
		this.matricule = matricule;
		this.date = date;
		this.annee = annee;
		this.semestre = semestre;
		this.totalNotes = totalNotes;
		this.moyenneNotes = moyenneNotes;
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



	public String getAnnee() {
		return annee;
	}



	public void setAnnee(String annee) {
		this.annee = annee;
	}



	public int getSemestre() {
		return semestre;
	}



	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}



	public BigDecimal getTotalNotes() {
		return totalNotes;
	}



	public void setTotalNotes(BigDecimal totalNotes) {
		this.totalNotes = totalNotes;
	}



	public BigDecimal getMoyenneNotes() {
		return moyenneNotes;
	}



	public void setMoyenneNotes(BigDecimal moyenneNotes) {
		this.moyenneNotes = moyenneNotes;
	}



	@Override
	public String toString() {
		return "Examen [id=" + id + ", matricule=" + matricule + ", date=" + date + ", annee=" + annee + ", semestre="
				+ semestre + ", totalNotes=" + totalNotes + ", moyenneNotes=" + moyenneNotes + ", francais=" + francais
				+ ", anglais=" + anglais + ", math=" + math + ", physique=" + physique + ", biologie=" + biologie
				+ ", histoire=" + histoire + ", geo=" + geo + ", eps=" + eps + "]";
	}


	

}