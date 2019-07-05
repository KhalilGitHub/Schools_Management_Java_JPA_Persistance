package com.objis.cameroun.gej.dao;

import java.util.List;

import com.objis.cameroun.gej.domaine.Eleve;
import com.objis.cameroun.gej.domaine.Inscription;

import java.sql.Connection;
import java.sql.SQLException;


public interface InscriptionIDao {
	
	
	//public  List<Inscription> queryInscription(Connection conn) throws SQLException;
	//public  void updateInscription(Connection conn, Inscription Inscription) throws SQLException;
	//public  void insertInscription(Connection conn, Inscription inscription) throws SQLException;
	//public  void deleteInscription(Connection conn, String mat) throws SQLException;
	
	public  Inscription findInscription(String matricule) throws SQLException;
	public  List<Inscription> getAllInscriptions() throws ClassNotFoundException;
	
	public  int insertInscription(Inscription inscription) throws SQLException;
	public  void updateInscription(Eleve eleve, Inscription inscription) throws SQLException;
	public  void deleteInscription(String mat) throws SQLException;
	
}
