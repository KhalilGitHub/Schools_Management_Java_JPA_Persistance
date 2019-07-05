package com.objis.cameroun.gej.domaine;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Bulletin implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	private Eleve eleve;
	
	@OneToOne(cascade=CascadeType.PERSIST)
	private Examen examen;
	
	public Bulletin() {
		super();
	}

	public Bulletin(long id, Eleve eleve, Examen examen) {
		super();
		this.id = id;
		this.eleve = eleve;
		this.examen = examen;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Eleve getEleve() {
		return eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}

	public Examen getExamen() {
		return examen;
	}

	public void setExamen(Examen examen) {
		this.examen = examen;
	}

	@Override
	public String toString() {
		return "Bulletin [id=" + id + ", eleve=" + eleve + ", examen=" + examen + "]";
	}
	
}
