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
public class Inscription implements Serializable{
	
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
    private BigDecimal frais;
    private Date date;
    private Blob image;
    
    
    public Inscription() {
		super();
	}

	public Inscription(String matricule, Eleve eleve, BigDecimal frais, Date date) {
		super();
		this.matricule = matricule;
		this.eleve = eleve;
		this.frais = frais;
		this.date = date;
	}



	public Inscription(String matricule, Eleve eleve, BigDecimal frais, Date date, Blob image) {
		super();
		this.matricule = matricule;
		this.eleve = eleve;
		this.frais = frais;
		this.date = date;
		this.image = image;
	}
		
	
	public Inscription(long id, String matricule, Eleve eleve, BigDecimal frais, Date date, Blob image) {
		super();
		this.id = id;
		this.matricule = matricule;
		this.eleve = eleve;
		this.frais = frais;
		this.date = date;
		this.image = image;
		
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

	public BigDecimal getFrais() {
		return frais;
	}

	public void setFrais(BigDecimal frais) {
		this.frais = frais;
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

	@Override
	public String toString() {
		return "Inscription [id=" + id + ", matricule=" + matricule + ", eleve=" + eleve + ", frais=" + frais
				+ ", date=" + date + ", image=" + image + "]";
	}

}
