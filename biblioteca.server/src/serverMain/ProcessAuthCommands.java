package serverMain;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dbLogic.AdministrareUtilizator;

public class ProcessAuthCommands {
	
	AdministrareUtilizator utilizator;
	
	public ProcessAuthCommands()
	{
		utilizator = new AdministrareUtilizator();
	}	

	public String processUserValidation(JSONObject jo)
	{
		String email =  (String) jo.get("Email");
		String parola = (String) jo.get("Parola");
		
		String rez = utilizator.ValidateUser(email, parola);
		
		JSONObject joResult = new JSONObject();
		
		boolean ok;
		if (rez.indexOf("Error")==0)
			ok = false;
		else
			ok = true;
		
		joResult.put("ok", ok);
		if (ok)
			joResult.put("nume", rez);
		else
			joResult.put("eroare", rez.substring(7)); // Error: Ceva
		
		return joResult.toJSONString();
	}
}
