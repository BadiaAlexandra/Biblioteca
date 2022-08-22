package serverMain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dataObject.Carte;
import dbLogic.AdministrareCarti;
import utils.ErrorCode;

public class ProcessClientCommands {
	
	AdministrareCarti carti;
	
	public ProcessClientCommands()
	{
		carti = new AdministrareCarti();
	}
	
	public String processGetBooks() {
		
		List<Carte> cartiLista = carti.getCarti();
		
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		 
		for (int i = 0; i<cartiLista.size(); i++)
		{
			Carte carteActuala = cartiLista.get(i);
			
			Map m = new LinkedHashMap(1);
			m.put("title", carteActuala.getTitlu());
			m.put("authorFirstName", carteActuala.getAutor().getPrenume());
			m.put("authorLastName", carteActuala.getAutor().getNume());
			m.put("ISBN", carteActuala.getISBN());
			m.put("publishingHouse", carteActuala.getEditura());
			m.put("numberOfPages", carteActuala.getNumarPagini());
			m.put("yearOfPublication", carteActuala.getAnulPublicarii());
			m.put("authorNationality", carteActuala.getAutor().getNationalitate());
			ja.add(m);
		}
		
		jo.put("books", ja);
		
		return jo.toJSONString();
	}
	
	public String processGetInchiriere(String email) {
		List<Carte> cartiLista = carti.getCartiInchiriate(email);
	
		JSONObject jo = new JSONObject();
		JSONArray ja = new JSONArray();
		 
		for (int i = 0; i<cartiLista.size(); i++)
		{
			Carte carteActuala = cartiLista.get(i);
			
			Map m = new LinkedHashMap(1);
			m.put("title", carteActuala.getTitlu());
			m.put("authorFirstName", carteActuala.getAutor().getPrenume());
			m.put("authorLastName", carteActuala.getAutor().getNume());
			m.put("ISBN", carteActuala.getISBN());
			m.put("publishingHouse", carteActuala.getEditura());
			m.put("numberOfPages", carteActuala.getNumarPagini());
			m.put("yearOfPublication", carteActuala.getAnulPublicarii());
			m.put("authorNationality", carteActuala.getAutor().getNationalitate());
			m.put("submissionDeadline", carteActuala.getTermenPredare());
			ja.add(m);
		}
		
		jo.put("books", ja);
		
		return jo.toJSONString();
	
	}
	
	public String processInchiriereCarte(String isbn, String email) {
		ErrorCode rezultat = carti.InchiriazaCarte(isbn, email);
		String rezultatString = "";
		
		if (rezultat==ErrorCode.Success)
			rezultatString = "Success";
		else if (rezultat==ErrorCode.CouldNotProcessRent)
			rezultatString = "Nu s-a realizat inchirierea";
		else if (rezultat==ErrorCode.BookAlreadyRent)
			rezultatString = "Carte deja inchiriata";
		else if (rezultat==ErrorCode.BookRentLimit)
			rezultatString = "Limita depasita";
		
		JSONObject jo = new JSONObject();
		
		jo.put("rezultat", rezultatString);
		
		return jo.toJSONString();
	}
	
	public String processReturnareCarte(String isbn, String email) {
		boolean returnatCuSucces = carti.ReturnareCarte(isbn, email);
		
		JSONObject jo = new JSONObject();
		
		jo.put("success", returnatCuSucces);
		
		return jo.toJSONString();
	}
	
}
