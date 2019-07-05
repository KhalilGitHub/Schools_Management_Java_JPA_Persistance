package com.objis.cameroun.gej.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.objis.cameroun.gej.domaine.Eleve;
import com.objis.cameroun.gej.domaine.Enseignant;
import com.objis.cameroun.gej.domaine.Examen;
import com.objis.cameroun.gej.domaine.Inscription;
import com.objis.cameroun.gej.domaine.Recrutement;
import com.objis.cameroun.gej.domaine.UserAccount;


public interface IService {
	
	
	public int saveUserAccountService(UserAccount user);
	public List<UserAccount> getAllUserAccountService();
	
	public  List<Inscription> getAllInscriptionsService() throws ClassNotFoundException;
	
	public  int insertInscriptionService(Inscription inscription) throws SQLException;
	
	public  Inscription findInscriptionService(String matricule) throws SQLException;
	public  void updateInscriptionService(Eleve eleve, Inscription inscription) throws SQLException;
	public  void deleteInscriptionService(String mat) throws SQLException;
	
	public  Recrutement findRecrutementService(String matricule) throws SQLException;
	public  List<Recrutement> getAllRecrutementService() throws ClassNotFoundException;
	public  int insertRecrutementService(Recrutement recrutement) throws SQLException;
	public  void updateRecrutementService(Enseignant enseignant, Recrutement recrutement) throws SQLException;
	public  void deleteRecrutementService(String mat) throws SQLException;
	
	public  int insertExamenService(Examen examen) throws SQLException;
	public  List<Examen> getAllNotesService() throws ClassNotFoundException;
	public  Examen findNotesService(String matricule) throws SQLException;
	public  void updateNotesService(Examen examen) throws SQLException;
	public  void deleteNotesService(String mat) throws SQLException;
	
	public  UserAccount findUserService(String userName, String password) throws SQLException;
	
	
	
	
}
