package com.objis.cameroun.gej.service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;

import com.objis.cameroun.gej.domaine.Eleve;
import com.objis.cameroun.gej.domaine.Enseignant;
import com.objis.cameroun.gej.dao.ExamDao;
import com.objis.cameroun.gej.dao.ExamIDao;
import com.objis.cameroun.gej.dao.InscriptionDao;
import com.objis.cameroun.gej.dao.InscriptionIDao;
import com.objis.cameroun.gej.dao.LoginDao;
//import com.objis.cameroun.gej.dao.LoginDao;
import com.objis.cameroun.gej.dao.LoginIDao;
import com.objis.cameroun.gej.dao.RecrutementDao;
import com.objis.cameroun.gej.dao.UserAccountDao;
import com.objis.cameroun.gej.dao.UserAccountIDao;
import com.objis.cameroun.gej.domaine.Examen;
import com.objis.cameroun.gej.domaine.Inscription;
import com.objis.cameroun.gej.domaine.Recrutement;
import com.objis.cameroun.gej.domaine.UserAccount;
import com.objis.cameroun.gej.dao.RecrutementIDao;

public class Service implements IService{
	
	
	private UserAccountIDao userAccountIDao;
	private InscriptionIDao inscriptionIDao;
	private RecrutementIDao recrutementIDao;
	private ExamIDao examIDao;
	private LoginIDao loginIDao;
	
	
	public Service() {
		this.userAccountIDao= new UserAccountDao();
		this.inscriptionIDao = new InscriptionDao();
		this.recrutementIDao = new RecrutementDao();
		this.examIDao = new ExamDao();
		this.loginIDao = new LoginDao();
		
	}
	
	public Service(EntityManager em) {
		this.userAccountIDao= new UserAccountDao(em);
		this.inscriptionIDao = new InscriptionDao(em);
		this.recrutementIDao = new RecrutementDao(em);
		this.examIDao = new ExamDao(em);
		this.loginIDao = new LoginDao(em);
	}
	
	
	public int saveUserAccountService(UserAccount user)
	{
		return userAccountIDao.saveUserAccountDao(user);
	}
	public List<UserAccount> getAllUserAccountService()
	{
		return userAccountIDao.getAllUserAccountDao();
	}

	@Override
	public List<Inscription> getAllInscriptionsService() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return inscriptionIDao.getAllInscriptions();
	}

	@Override
	public int insertInscriptionService(Inscription inscription) throws SQLException {
		// TODO Auto-generated method stub
		return inscriptionIDao.insertInscription(inscription);
	}

	@Override
	public Inscription findInscriptionService(String matricule) throws SQLException {
		// TODO Auto-generated method stub
		return inscriptionIDao.findInscription(matricule);
	}

	@Override
	public void updateInscriptionService(Eleve eleve, Inscription inscription) throws SQLException {
		// TODO Auto-generated method stub
		inscriptionIDao.updateInscription(eleve, inscription);
	}

	@Override
	public void deleteInscriptionService(String mat) throws SQLException {
		// TODO Auto-generated method stub
		inscriptionIDao.deleteInscription(mat);
		
	}

	@Override
	public Recrutement findRecrutementService(String matricule) throws SQLException {
		// TODO Auto-generated method stub
		return recrutementIDao.findRecrutement(matricule);
	}

	@Override
	public List<Recrutement> getAllRecrutementService() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return recrutementIDao.getAllRecrutement();
	}

	@Override
	public int insertRecrutementService(Recrutement recrutement) throws SQLException {
		// TODO Auto-generated method stub
		return recrutementIDao.insertRecrutement(recrutement);
	}

	@Override
	public void updateRecrutementService(Enseignant enseignant, Recrutement recrutement) throws SQLException {
		// TODO Auto-generated method stub
		recrutementIDao.updateRecrutement(enseignant, recrutement);
		
	}

	
	
	@Override
	public void deleteRecrutementService(String mat) throws SQLException {
		// TODO Auto-generated method stub
		recrutementIDao.deleteRecrutement(mat);
	}
	

	
	@Override
	public int insertExamenService(Examen examen) throws SQLException {
		// TODO Auto-generated method stub
		return examIDao.insertNotes(examen);
	}

	@Override
	public List<Examen> getAllNotesService() throws ClassNotFoundException {
		// TODO Auto-generated method stub
		return examIDao.getAllNotes();
	}

	@Override
	public Examen findNotesService(String matricule) throws SQLException {
		// TODO Auto-generated method stub
		return examIDao.findNotes(matricule);
	}

	@Override
	public void updateNotesService(Examen examen) throws SQLException {
		// TODO Auto-generated method stub
		examIDao.updateNotes(examen);
	}

	
	@Override
	public UserAccount findUserService(String userName, String passWord) throws SQLException {
		// TODO Auto-generated method stub
		return loginIDao.findUser(userName, passWord);
	}

	@Override
	public void deleteNotesService(String mat) throws SQLException {
		// TODO Auto-generated method stub
		
		examIDao.deleteNotes(mat);
		
	}

	
	
}
