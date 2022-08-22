package dbLogic;

import java.sql.ResultSet;

public class AdministrareUtilizator {
	DbConnector db;
	
	public AdministrareUtilizator() {
		db = new DbConnector();
	}
	
	public String ValidateUser(String email, String parola) 
	{
		String sqlCommand = "SELECT * FROM Utilizator";
		
		try {
			ResultSet result = db.ExecuteCommand(sqlCommand);
			
			while (result.next())
			{
				String emailCurent = result.getString("Email");
				if (emailCurent.equalsIgnoreCase(email))
				{
					String parolaCurenta = result.getString("Parola");
					if(parolaCurenta.equals(parola))
					{
						String rol = result.getString("Rol");
						String nume = result.getString("Nume");
						
						return nume + "@" + rol; // va returna de exemplu Alina@Client
					}
					else
						return "Error: Parola invalida";
				}
			}
		} catch (Exception e) {
			return "Error: General";
		}
		
		return "Error: Lipsa utilizator";
	}
}
