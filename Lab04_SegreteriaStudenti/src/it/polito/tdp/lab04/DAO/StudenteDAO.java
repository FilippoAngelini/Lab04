package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	//CorsoDAO corsoDAO = new CorsoDAO();
	
	public Studente cercaStudente(int matricola){
		
		final String sql = "SELECT * FROM studente WHERE matricola=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();
			
			Studente s = null;

			while (rs.next()) {
				
				String cognome = rs.getString("cognome");
				String nome = rs.getString("nome");
				String cds = rs.getString("cds");
				
				s = new Studente (matricola,cognome,nome,cds);
			}
			
			//conn.close();

			return s;

		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Corso> getCorsi(int matricola) {
		
		CorsoDAO corsoDAO = new CorsoDAO();
		
		final String sql = "SELECT codins FROM iscrizione WHERE matricola=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();
			
			List<Corso> corsi = new LinkedList<Corso>();
			//TODO controllo
			 
			while (rs.next()) {
				
				String codins = rs.getString("codins");
				
				corsi.add(corsoDAO.getCorso(codins));
				
			}
			
			if(corsi.size()==0)
				return null;
			
			//conn.close();

			return corsi;

		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}

	}
	
	public String cercaIscrizione(String codIns, int matricola){
		
		final String sql = "SELECT * FROM iscrizione WHERE matricola=? AND codins=?";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, matricola);
			st.setString(2, codIns);

			ResultSet rs = st.executeQuery();
			
			//TODO controllo
			
			if(rs.next())
				return "Studente " + matricola + " iscritto al corso " + codIns;
			else
				return "Studente " + matricola + " NON iscritto al corso " + codIns;
			
			//conn.close();

		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}


}
