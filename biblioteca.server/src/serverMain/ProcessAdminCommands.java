package serverMain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataObject.Autor;
import dataObject.Carte;
import dataObject.Utilizator;
import dbLogic.AdministrareC;
import dbLogic.AdministrareCarti;
import dbLogic.AdministrareUtilizator;
import utils.ErrorCode;

public class ProcessAdminCommands {

	AdministrareC adminC;
	AdministrareCarti adminCarti;
	
	public ProcessAdminCommands()
	{
		adminC = new AdministrareC();
		adminCarti= new AdministrareCarti();
	}
	
	public String processGetUsers() {
		
		List<Utilizator> utilizatorLista = adminC.getUtilizator();
		
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		 
		for (int i = 0; i<utilizatorLista.size(); i++)
		{
			Utilizator utilizatorActual = utilizatorLista.get(i);
			
			Map m = new LinkedHashMap(1);
			m.put("email", utilizatorActual.getEmail());
			m.put("userFirstName", utilizatorActual.getPrenume());
			m.put("userLastName", utilizatorActual.getNume());
			m.put("parola", utilizatorActual.getParola());
			m.put("rol", utilizatorActual.getRol());
			m.put("cnp", utilizatorActual.getCNP());
			m.put("adresa", utilizatorActual.getAdresa());
			ja.add(m);
		}
		
		jo.put("users", ja);
		
		return jo.toJSONString();
	}
	
	public String processAddUsers(Utilizator u) {
		boolean rezultat = adminC.processAddUsers(u);
				
		JSONObject jo = new JSONObject();
		
		jo.put("rezultat", rezultat);
		
		return jo.toJSONString();
	}
	
	public String processAddBooks(Carte c, Autor a) {
		boolean rezultat = adminCarti.processAddBooks(c, a);
				
		JSONObject jo = new JSONObject();
		
		jo.put("rezultat", rezultat);
		
		return jo.toJSONString();
	}
	
}
