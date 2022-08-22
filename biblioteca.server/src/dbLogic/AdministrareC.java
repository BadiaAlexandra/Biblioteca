package dbLogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dataObject.Autor;
import dataObject.Carte;
import dataObject.Utilizator;
import utils.ErrorCode;

public class AdministrareC {
	
	DbConnector db;
	
	public AdministrareC() {
		db = new DbConnector();
	}
	
	public List<Utilizator> getUtilizator() {
		List<Utilizator> utilizatori = new ArrayList<Utilizator>();
		
		String sqlCommand = "SELECT * FROM Utilizator\r\n";
		
		try {
			ResultSet result = db.ExecuteCommand(sqlCommand);
			
			while (result.next())
			{
				Utilizator utilizator = new Utilizator();
				utilizator.setCNP(result.getLong("CNP"));
				utilizator.setNume(result.getString("Nume"));
				utilizator.setPrenume(result.getString("Prenume"));
				utilizator.setAdresa(result.getString("Adresa"));
				utilizator.setEmail(result.getString("Email"));
				utilizator.setParola(result.getString("Parola"));
				utilizator.setRol(result.getString("Rol"));
				utilizatori.add(utilizator);				
			}
			
			return utilizatori;
		} catch (Exception exp)
		{
			return utilizatori;
		}
	}
	
	public boolean processAddUsers(Utilizator u)
	{
		// validare date
		String rol = u.getRol();
		if (!rol.equals("Admin") && !rol.equals("Client"))
			return false;
			
		String sql = "INSERT INTO Utilizator (CNP, Nume, Prenume, Adresa, Email, Parola, Rol) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = db.conn.prepareStatement(sql);
			preparedStatement.setLong(1, u.getCNP());
			preparedStatement.setString(2, u.getNume());
			preparedStatement.setString(3, u.getPrenume());
			preparedStatement.setString(4, u.getAdresa());
			preparedStatement.setString(5, u.getEmail());
			preparedStatement.setString(6, u.getParola());
			preparedStatement.setString(7, u.getRol());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		try {
			preparedStatement.execute();
		} catch (Exception exp)
		{
			System.out.println(exp);
			return false;
		}
		
		return true;
	}
	
//	private String GetCNPForEmail(String email)
//	{
//		String sqlCommand = String.format("SELECT CNP FROM Utilizator WHERE Email=\"%s\"", email);
//		
//		try {
//			ResultSet result = db.ExecuteCommand(sqlCommand);
//			
//			while (result.next())
//			{
//				String CNP = result.getString("CNP");
//				return CNP;
//			}
//			
//			return "";
//		} catch (Exception exp)
//		{
//			System.out.println(exp);
//			return "";
//		}
//	}
}