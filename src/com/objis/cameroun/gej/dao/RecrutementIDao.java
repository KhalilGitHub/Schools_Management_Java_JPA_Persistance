package com.objis.cameroun.gej.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

import com.objis.cameroun.gej.domaine.Enseignant;
import com.objis.cameroun.gej.domaine.Recrutement;

public interface RecrutementIDao {

	//public  List<Recrutement> queryRecrutement(Connection conn) throws SQLException;
	public  Recrutement findRecrutement(String matricule) throws SQLException;
	public  void updateRecrutement(Enseignant enseignant, Recrutement recrutement) throws SQLException;
	public  int insertRecrutement(Recrutement recrutement) throws SQLException;
	public  void deleteRecrutement(String mat) throws SQLException;
	public  List<Recrutement> getAllRecrutement() throws ClassNotFoundException;
	
}
