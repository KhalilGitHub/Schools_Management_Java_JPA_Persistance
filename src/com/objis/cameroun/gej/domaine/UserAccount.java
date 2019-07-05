package com.objis.cameroun.gej.domaine;


import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserAccount extends Personne implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String userName; 
	private String password;
	private String level;   
	private String tel;
	private String fonction;
	        
	
	public UserAccount() {}
	
	

	public UserAccount(String userName, String level, String password) {
		super();
		this.userName = userName;
		this.level = level;
		this.password = password;
	}
	
	

	public UserAccount(int id, String userName, String level, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.level = level;
		this.password = password;
	}

	public UserAccount(String nom, String prenom,  String fonction, String genre, String adresse, String userName, String level, String password, String tel) {
		super(nom, prenom, genre, adresse);
		this.userName = userName;
		this.level = level;
		this.password = password;
		this.fonction = fonction;
		this.tel = tel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public String getFonction() {
		return fonction;
	}



	public void setFonction(String fonction) {
		this.fonction = fonction;
	}



	@Override
	public String toString() {
		return "UserAccount [id=" + id + ", userName=" + userName + ", password=" + password + ", level=" + level
				+ ", tel=" + tel + ", fonction=" + fonction + ", nom=" + nom + ", prenom=" + prenom + ", genre="
				+ genre + ", adresse=" + adresse + "]";
	}	
	
}

