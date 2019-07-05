package com.objis.cameroun.gej.domaine;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Mois {
	
	protected int janvier;
	protected int fevrier;
	protected int mars;
	protected int avril;
	protected int mai;
	protected int juin;
	protected int julliet;
	protected int aout;
	protected int septembre;
	protected int octobre;
	protected int november;
	protected int decembre;	
	
	public Mois() {
		super();
	}


	public Mois(int janvier, int fevrier, int mars, int avril, int mai, int juin, int julliet, int aout, int septembre,
			int octobre, int november, int decembre) {
		super();
		this.janvier = janvier;
		this.fevrier = fevrier;
		this.mars = mars;
		this.avril = avril;
		this.mai = mai;
		this.juin = juin;
		this.julliet = julliet;
		this.aout = aout;
		this.septembre = septembre;
		this.octobre = octobre;
		this.november = november;
		this.decembre = decembre;
	}


	public int getJanvier() {
		return janvier;
	}


	public void setJanvier(int janvier) {
		this.janvier = janvier;
	}


	public int getFevrier() {
		return fevrier;
	}


	public void setFevrier(int fevrier) {
		this.fevrier = fevrier;
	}


	public int getMars() {
		return mars;
	}


	public void setMars(int mars) {
		this.mars = mars;
	}


	public int getAvril() {
		return avril;
	}


	public void setAvril(int avril) {
		this.avril = avril;
	}


	public int getMai() {
		return mai;
	}


	public void setMai(int mai) {
		this.mai = mai;
	}


	public int getJuin() {
		return juin;
	}


	public void setJuin(int juin) {
		this.juin = juin;
	}


	public int getJulliet() {
		return julliet;
	}


	public void setJulliet(int julliet) {
		this.julliet = julliet;
	}


	public int getAout() {
		return aout;
	}


	public void setAout(int aout) {
		this.aout = aout;
	}


	public int getSeptembre() {
		return septembre;
	}


	public void setSeptembre(int septembre) {
		this.septembre = septembre;
	}


	public int getOctobre() {
		return octobre;
	}


	public void setOctobre(int octobre) {
		this.octobre = octobre;
	}


	public int getNovember() {
		return november;
	}


	public void setNovember(int november) {
		this.november = november;
	}


	public int getDecembre() {
		return decembre;
	}


	public void setDecembre(int decembre) {
		this.decembre = decembre;
	}


	@Override
	public String toString() {
		return "Mois [janvier=" + janvier + ", fevrier=" + fevrier + ", mars=" + mars + ", avril=" + avril + ", mai="
				+ mai + ", juin=" + juin + ", julliet=" + julliet + ", aout=" + aout + ", septembre=" + septembre
				+ ", octobre=" + octobre + ", november=" + november + ", decembre=" + decembre + "]";
	}
	
	

}
