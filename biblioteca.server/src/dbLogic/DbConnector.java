package dbLogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DbConnector {
	
	public static Connection conn = null;
	private String urlConnect = "jdbc:sqlite:F:/stickk/tot/bibN/finalizat/biblioteca.db";
	
	public DbConnector()
	{
		try {
			conn = DriverManager.getConnection(urlConnect);
		} catch (SQLException exp)
		{
			System.out.println("Nu am reusit sa ne conectam la baza de data!");
		}
	}
	
	public ResultSet ExecuteCommand(String sqlCommand) throws Exception
	{
		if (conn==null)
			throw new Exception("no connection");
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rSet = stmt.executeQuery(sqlCommand);
			
			return rSet;
		} catch (SQLException exp)
		{
			System.out.println("Nu am reusit sa executam: " + sqlCommand);
			throw new Exception("sql command error");
		}
	}
}
