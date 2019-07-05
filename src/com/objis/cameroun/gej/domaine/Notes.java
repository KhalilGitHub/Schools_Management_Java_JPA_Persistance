package com.objis.cameroun.gej.domaine;


import java.math.BigDecimal;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Notes {
	

	protected BigDecimal francais;
	protected BigDecimal anglais;
	protected BigDecimal math;
	protected BigDecimal physique;
	protected BigDecimal biologie;
	protected BigDecimal histoire;
	protected BigDecimal geo;
	protected BigDecimal eps;	
	
	public Notes() {
		super();
	}

	
	


	public Notes(BigDecimal francais, BigDecimal anglais, BigDecimal math, BigDecimal physique, BigDecimal biologie,
			BigDecimal histoire, BigDecimal geo, BigDecimal eps) {
		super();
		this.francais = francais;
		this.anglais = anglais;
		this.math = math;
		this.physique = physique;
		this.biologie = biologie;
		this.histoire = histoire;
		this.geo = geo;
		this.eps = eps;
	}





	public BigDecimal getFrancais() {
		return francais;
	}


	public void setFrancais(BigDecimal francais) {
		this.francais = francais;
	}


	public BigDecimal getAnglais() {
		return anglais;
	}


	public void setAnglais(BigDecimal anglais) {
		this.anglais = anglais;
	}


	public BigDecimal getMath() {
		return math;
	}


	public void setMath(BigDecimal math) {
		this.math = math;
	}


	public BigDecimal getPhysique() {
		return physique;
	}


	public void setPhysique(BigDecimal physique) {
		this.physique = physique;
	}


	public BigDecimal getBiologie() {
		return biologie;
	}


	public void setBiologie(BigDecimal biologie) {
		this.biologie = biologie;
	}


	public BigDecimal getHistoire() {
		return histoire;
	}


	public void setHistoire(BigDecimal histoire) {
		this.histoire = histoire;
	}


	public BigDecimal getGeo() {
		return geo;
	}


	public void setGeo(BigDecimal geo) {
		this.geo = geo;
	}


	public BigDecimal geteps() {
		return eps;
	}


	public void seteps(BigDecimal eps) {
		this.eps = eps;
	}

	
	
}
