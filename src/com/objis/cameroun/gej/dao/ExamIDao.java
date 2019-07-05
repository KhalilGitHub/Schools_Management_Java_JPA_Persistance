package com.objis.cameroun.gej.dao;

import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

import com.objis.cameroun.gej.domaine.Examen;


public interface ExamIDao{
	
	
	public  int insertNotes(Examen examen) throws SQLException;
	public  void updateNotes(Examen examen) throws SQLException;
	public  void deleteNotes(String mat) throws SQLException;
	public  List<Examen> getAllNotes() throws ClassNotFoundException;
	public  Examen findNotes(String matricule) throws SQLException;
	

}
