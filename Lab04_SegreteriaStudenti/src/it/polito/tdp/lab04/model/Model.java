package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	CorsoDAO corsoDAO = new CorsoDAO();
	StudenteDAO studenteDAO = new StudenteDAO();

	public List<Corso> getTuttiICorsi() {
		
		return corsoDAO.getTuttiICorsi();
	}

	public String cercaNomeStudente(int matricola) {
		
		Studente s = studenteDAO.cercaStudente(matricola);
		
		if(s == null)
			return null;
		
		String nome = s.getNome();
		
		return nome;
	}

	public String cercaCognomeStudente(int matricola) {
		
		String cognome = studenteDAO.cercaStudente(matricola).getCognome();
		
		return cognome;
	}

	public String getStudentiIscrittiAlCorso(Corso corso) {
		
		String ris = "";
		
		for(Studente s : corsoDAO.getStudentiIscrittiAlCorso(corso))
			ris += s.toString() + "\n";
		
		return ris.trim();
	}

	public String cercaCorsi(int matricola) {
		
		String ris = "";
		
		if(studenteDAO.getCorsi(matricola)==null)
			return "Lo studente non è iscritto a nessun corso!";
		
		for(Corso c : studenteDAO.getCorsi(matricola))
			ris += c.toString() + "\n";
		
		return ris;

	}

	public String cercaIscrizione(String codIns, int matricola) {
		
		return studenteDAO.cercaIscrizione(codIns, matricola);
	}
	
	

}
