package dbLogic;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dataObject.Autor;
import dataObject.Carte;
import utils.ErrorCode;

public class AdministrareCarti {
	
	DbConnector db;
	
	public AdministrareCarti() {
		db = new DbConnector();
	}
	
	public List<Carte> getCarti() {
		List<Carte> carti = new ArrayList<Carte>();
		
		String sqlCommand = "SELECT * FROM Carti\r\n"
				+ "INNER JOIN Autori ON Carti.IdAutor=Autori.ID";
		
		try {
			ResultSet result = db.ExecuteCommand(sqlCommand);
			
			while (result.next())
			{
				Autor autor = new Autor();
				autor.setNume(result.getString("Nume"));
				autor.setPrenume(result.getString("Prenume"));
				autor.setID(result.getInt("ID"));
				autor.setNationalitate(result.getString("Nationalitate"));
				autor.setDomiciliul(result.getString("Domiciliul"));
				autor.setAnulNasterii(result.getInt("AnulNasterii"));

	
				Carte carte = new Carte();
				carte.setAutor(autor);
				carte.setAnulPublicarii(result.getInt("AnulPublicarii"));
				carte.setFormat(result.getString("Format"));
				carte.setISBN(result.getString("ISBN"));
				carte.setCodLocatie(result.getInt("CodLocatie"));
				carte.setTitlu(result.getString("Titlu"));
				carte.setNumarBucati(result.getInt("NumarBucati"));
				carte.setEditura(result.getString("Editura"));
				carte.setNumarPagini(result.getInt("NumarPagini"));

				if (carte.getNumarBucati() > 0) // doar cartile disponibile
					carti.add(carte);				
			}
			
			return carti;
		} catch (Exception exp)
		{
			return carti;
		}
	}
	
	public List<Carte> getCartiInchiriate(String email) {
		List<Carte> carti = new ArrayList<Carte>();
		
		String sqlCommand = "SELECT Autori.Nume, Autori.Prenume, Autori.ID, Autori.Nationalitate, Autori.Domiciliul, Autori.AnulNasterii,\r\n"
				+ " Carti.Titlu, Carti.AnulPublicarii, Carti.CodLocatie, Carti.Editura, Carti.Format, Carti.ISBN, Carti.IdAutor, \r\n"
				+ " Carti.NumarBucati, Carti.NumarPagini, Utilizator.Email, Inchiriere.TermenPredare, Returnat FROM Inchiriere\r\n"
				+ "INNER JOIN Carti ON Carti.ISBN=Inchiriere.ISBN\r\n"
				+ "INNER JOIN Utilizator ON Utilizator.CNP=Inchiriere.CNP\r\n"
		        + "INNER JOIN Autori ON Carti.IdAutor=Autori.ID";
		
		try {
			ResultSet result = db.ExecuteCommand(sqlCommand);
			
			while (result.next())
			{
				String currentEmail = result.getString("Email");
				String returnat = result.getString("Returnat");

				if (currentEmail.equalsIgnoreCase(email)&&(returnat.equals("nu")))
				{					
					Autor autor = new Autor();
					autor.setNume(result.getString("Nume"));
					autor.setPrenume(result.getString("Prenume"));
					autor.setID(result.getInt("ID"));
					autor.setNationalitate(result.getString("Nationalitate"));
					autor.setDomiciliul(result.getString("Domiciliul"));
					autor.setAnulNasterii(result.getInt("AnulNasterii"));
	
		
					Carte carte = new Carte();
					carte.setAutor(autor);
					carte.setAnulPublicarii(result.getInt("AnulPublicarii"));
					carte.setFormat(result.getString("Format"));
					carte.setISBN(result.getString("ISBN"));
					carte.setCodLocatie(result.getInt("CodLocatie"));
					carte.setTitlu(result.getString("Titlu"));
					carte.setNumarBucati(result.getInt("NumarBucati"));
					carte.setEditura(result.getString("Editura"));
					carte.setNumarPagini(result.getInt("NumarPagini"));
					carte.setTermenPredare(result.getString("TermenPredare"));

					carti.add(carte);	
				}
			}
			
			return carti;
		} catch (Exception exp) {
			System.out.println(exp);
			return carti;
		}
	}
	
	// Success
	// LimitaDepasita
	// CarteDejaInchiriata
	// EroareGenerica
	public ErrorCode InchiriazaCarte(String isbn, String email)
	{
		// verificam daca poate inchiria (maxim 3 carti si nu poate lua aceeasi carte inca odata)
		List<Carte> cartiDejaInchiriate = getCartiInchiriate(email);
		
		if (cartiDejaInchiriate.size()>=3)
			return ErrorCode.BookRentLimit;
		
		for( int i=0; i<cartiDejaInchiriate.size(); i++ ) {
			if(cartiDejaInchiriate.get(i).getISBN().equals(isbn))
				return ErrorCode.BookAlreadyRent;
		}
		
		String cnp = GetCNPForEmail(email);
		System.out.println("CNP obtinut: " + cnp);
		if (cnp.isEmpty())
			return ErrorCode.CouldNotProcessRent;
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-YYYY"); 
		LocalDateTime now = LocalDateTime.now();  
		LocalDateTime predare = now.plusWeeks(2);
		String stringPredare = dtf.format(predare);
				
		String sql = "INSERT INTO Inchiriere (CNP, ISBN, TermenPredare, Returnat) VALUES (?, ?, ?, ?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = db.conn.prepareStatement(sql);
			preparedStatement.setString(1, cnp);
			preparedStatement.setString(2, isbn);
			preparedStatement.setString(3, stringPredare);
			preparedStatement.setString(4, "nu");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ErrorCode.CouldNotProcessRent;
		}
		
		try {
			preparedStatement.execute();
		} catch (Exception exp)
		{
			System.out.println(exp);
			return ErrorCode.CouldNotProcessRent;
		}
		
		String sql2 = "UPDATE Carti\r\n"
				+ "SET NumarBucati = NumarBucati -1\r\n"
				+ "WHERE ISBN=\"";
		sql2 += isbn;
		sql2 += "\";";
		
		System.out.println(sql2);
		try {
			Statement stmt = db.conn.createStatement();
			 stmt.executeUpdate(sql2);			
		} catch (Exception exp)
		{
			System.out.println(exp);
			return ErrorCode.CouldNotProcessRent;
		}
		
		return ErrorCode.Success;
	}
	
	private String GetCNPForEmail(String email)
	{
		String sqlCommand = String.format("SELECT CNP FROM Utilizator WHERE Email=\"%s\"", email);
		
		try {
			ResultSet result = db.ExecuteCommand(sqlCommand);
			
			while (result.next())
			{
				String CNP = result.getString("CNP");
				return CNP;
			}
			
			return "";
		} catch (Exception exp)
		{
			System.out.println(exp);
			return "";
		}
	}
	public boolean ReturnareCarte(String isbn, String email)
	{
		String cnp = GetCNPForEmail(email);
		System.out.println("CNP obtinut: " + cnp);
		if (cnp.isEmpty())
			return false;
			
		String sql = "UPDATE Inchiriere SET Returnat=\"da\" WHERE ISBN=? AND CNP=?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = db.conn.prepareStatement(sql);
			preparedStatement.setString(1, isbn);
			preparedStatement.setString(2, cnp);
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
		
		String sql2 = "UPDATE Carti\r\n"
				+ "SET NumarBucati = NumarBucati + 1\r\n"
				+ "WHERE ISBN=\"";
		sql2 += isbn;
		sql2 += "\";";
		
		System.out.println(sql2);
		try {
			Statement stmt = db.conn.createStatement();
			 stmt.executeUpdate(sql2);			
		} catch (Exception exp)
		{
			System.out.println(exp);
			return false;
		}
		
		return true;
	}
	
	public boolean processAddBooks(Carte c, Autor a)
	{
		int idAutor = GetIdAutor(a);
		if (idAutor == -1)
		{
			System.out.println("Autorul nu a fost gasit -> il adaugam");
			if (!AdaugaAutor(a))
				return false;
			
			idAutor = GetIdAutor(a);
			System.out.println("Autorul nou adaugat are id-ul: " + idAutor);
		}
		else
		{
			System.out.println("Autorul l-am gasit, are id-ul: " + idAutor);
		}
		
		boolean result = AdaugaCarte(c, idAutor);
		if (!result)
			System.out.println("Nu am reusit sa adaugam cartea.");
		
		return result;
	}
	
	private boolean AdaugaCarte(Carte c, int idAutor)
	{
		String sql = "INSERT INTO Carti (ISBN, Titlu, CodLocatie, Format, NumarBucati, AnulPublicarii, Editura, NumarPagini, IdAutor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = db.conn.prepareStatement(sql);
			preparedStatement.setString(1, c.getISBN());
			preparedStatement.setString(2, c.getTitlu());
			preparedStatement.setInt(3, c.getCodLocatie());
			preparedStatement.setString(4, c.getFormat());
			preparedStatement.setInt(5, c.getNumarBucati());
			preparedStatement.setInt(6, c.getAnulPublicarii());
			preparedStatement.setString(7, c.getEditura());
			preparedStatement.setInt(8, c.getNumarPagini());
			preparedStatement.setInt(9, idAutor);
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
	
	
	private boolean AdaugaAutor(Autor a)
	{
		String sql = "INSERT INTO Autori (Nume, Prenume, Nationalitate, Domiciliul, AnulNasterii) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = db.conn.prepareStatement(sql);
			preparedStatement.setString(1, a.getNume());
			preparedStatement.setString(2, a.getPrenume());
			preparedStatement.setString(3,a.getNationalitate());
			preparedStatement.setString(4,a.getDomiciliul());
			preparedStatement.setInt(5, a.getAnulNasterii());
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
	
	private int GetIdAutor(Autor a)
	{
		String sqlCommand = "SELECT * FROM Autori\r\n";
		
		try {
			ResultSet result = db.ExecuteCommand(sqlCommand);
			
			while (result.next())
			{
				String numeCurent = result.getString("Nume");
				String prenumeCurent = result.getString("Prenume");
				String nationalitateCurenta = result.getString("Nationalitate");
				String domiciliulCurent = result.getString("Domiciliul");
				int anulNasteriiCurent = result.getInt("AnulNasterii");			
				
				Autor autorCurent = new Autor(numeCurent, prenumeCurent, nationalitateCurenta, domiciliulCurent, 0, anulNasteriiCurent);
				
				if (EsteAutorAcelasi(autorCurent, a))
					return  result.getInt("ID");	
			}
			
			return -1;
		} catch (Exception exp)
		{
			return -1;
		}
	}
	
	private boolean EsteAutorAcelasi(Autor a1, Autor a2)
	{
		if (!a1.getNume().equals(a2.getNume()))
			return false;
		
		if (!a1.getPrenume().equals(a2.getPrenume()))
			return false;
		
		if (!a1.getNationalitate().equals(a2.getNationalitate()))
			return false;
		
		if (!a1.getDomiciliul().equals(a2.getDomiciliul()))
			return false;
		
		if (a1.getAnulNasterii() != a2.getAnulNasterii())
			return false;
		
		return true;
	}
}
