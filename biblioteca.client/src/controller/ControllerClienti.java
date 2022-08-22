package controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

import dataObject.Autor;
import dataObject.Carte;
import dataObject.Utilizator;

public class ControllerClienti {

	DataInputStream dis;
	DataOutputStream dos;
	
	public ControllerClienti(DataInputStream dInput, DataOutputStream dOutput)
	{
		dis = dInput;
		dos = dOutput;
	}
	
	public List<Carte> getCarti()
	{
		List<Carte> carti = new ArrayList<Carte>();
		
		JSONObject jo = new JSONObject();
        jo.put("command", "getBooks");
        
		String input = jo.toJSONString();
		
		try {
			dos.writeUTF(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return carti;
		}
		
		String result;
		try {
			result = dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return carti;
		}
		
		System.out.println(result);
		
		 Object obj;
			try {
				obj = new JSONParser().parse(result);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return carti;
			}
         jo = (JSONObject)obj;
         
         JSONArray ja = (JSONArray) jo.get("books");     
         
         // iterating books
         Iterator itr2 = ja.iterator(); 
         while (itr2.hasNext()) 
         {
        	 Map mapCarte = ((Map) itr2.next());
             
        	 Carte c = new Carte();
        	 
        	 String numeAutor = (String)mapCarte.get("authorLastName");
        	 String prenumeAutor = (String)mapCarte.get("authorFirstName");
        	 String nationalitateAutor = (String)mapCarte.get("authorNationality");
        	 
        	 Autor autor = new Autor();
        	 autor.setNume(numeAutor);
        	 autor.setPrenume(prenumeAutor);
        	 autor.setNationalitate(nationalitateAutor);
        	 
        	 c.setAutor(autor);
        	 
        	 autor.setNationalitate(nationalitateAutor);
        	 
        	 String numeComplet = numeAutor + " " + prenumeAutor;
        	 
        	 c.setTitlu((String)mapCarte.get("title"));
        	 c.setISBN((String)mapCarte.get("ISBN"));
        	 c.setEditura((String)mapCarte.get("publishingHouse"));
        	 c.setNumarPagini(((Long)mapCarte.get("numberOfPages")).intValue());
        	 c.setAnulPublicarii(((Long)mapCarte.get("yearOfPublication")).intValue());

        	 carti.add(c);
         }
		
		return carti;
	}
	
	public List<Carte> getCartiInchiriate(String email)
	{
		List<Carte> carti = new ArrayList<Carte>();
		
		JSONObject jo = new JSONObject();
        jo.put("command", "getBooksI");
        jo.put("email", email);

		String input = jo.toJSONString();
		
		try {
			dos.writeUTF(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return carti;
		}
		
		String result;
		try {
			result = dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return carti;
		}
		
		System.out.println(result);
		
		 Object obj;
			try {
				obj = new JSONParser().parse(result);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return carti;
			}
         jo = (JSONObject)obj;
         
         JSONArray ja = (JSONArray) jo.get("books");     
         
         // iterating books
         Iterator itr2 = ja.iterator(); 
         while (itr2.hasNext()) 
         {
        	 Map mapCarte = ((Map) itr2.next());
             
        	 Carte c = new Carte();
        	 
        	 String numeAutor = (String)mapCarte.get("authorLastName");
        	 String prenumeAutor = (String)mapCarte.get("authorFirstName");
        	 String nationalitateAutor = (String)mapCarte.get("authorNationality");
        	 
        	 Autor autor = new Autor();
        	 autor.setNume(numeAutor);
        	 autor.setPrenume(prenumeAutor);
        	 autor.setNationalitate(nationalitateAutor);
        	 
        	 c.setAutor(autor);
        	 
        	 autor.setNationalitate(nationalitateAutor);
        	 
        	 String numeComplet = numeAutor + " " + prenumeAutor;
        	 
        	 c.setTitlu((String)mapCarte.get("title"));
        	 c.setISBN((String)mapCarte.get("ISBN"));
        	 c.setEditura((String)mapCarte.get("publishingHouse"));
        	 c.setNumarPagini(((Long)mapCarte.get("numberOfPages")).intValue());
        	 c.setAnulPublicarii(((Long)mapCarte.get("yearOfPublication")).intValue());
        	 c.setTermenPredare((String)mapCarte.get("submissionDeadline"));

        	 carti.add(c);
         }
		
		return carti;
	}
	
	public String InchiriazaCarte(String isbn, String email) {
		
		JSONObject jo = new JSONObject();
        jo.put("command", "rentBook");
        jo.put("email", email);
        jo.put("isbn", isbn);

		String input = jo.toJSONString();
		
		try {
			dos.writeUTF(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Nu s-a realizat inchirierea";
		}
		
		String result;
		try {
			result = dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Nu s-a realizat inchirierea";
		}
		
		System.out.println(result);
		
		Object obj;
		try {
			obj = new JSONParser().parse(result);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Nu s-a realizat inchirierea";
		}
		jo = (JSONObject)obj;

		String rezultat = (String) jo.get("rezultat"); 
		
		return rezultat;
	}
	
	public boolean ReturneazaCarte(String isbn, String email) {
		
		JSONObject jo = new JSONObject();
        jo.put("command", "returnedBook");
        jo.put("email", email);
        jo.put("isbn", isbn);

		String input = jo.toJSONString();
		
		try {
			dos.writeUTF(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		String result;
		try {
			result = dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		System.out.println(result);
		
		Object obj;
		try {
			obj = new JSONParser().parse(result);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		jo = (JSONObject)obj;

		boolean ReturnatCuSuccess = (boolean) jo.get("success"); 
		
		return ReturnatCuSuccess;
	}
	
public boolean AdaugaUtilizator(Utilizator u)
{
	JSONObject jo = new JSONObject();
    jo.put("command", "AddUsers");
    jo.put("CNP", u.getCNP());
    jo.put("Nume", u.getNume());
    jo.put("Prenume", u.getPrenume());
    jo.put("Adresa", u.getAdresa());
    jo.put("Email", u.getEmail());
    jo.put("Parola", u.getParola());
    jo.put("Rol", u.getRol());

	String input = jo.toJSONString();
	
	try {
		dos.writeUTF(input);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	
	String result;
	try {
		result = dis.readUTF();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	
	System.out.println(result);
	
	Object obj;
	try {
		obj = new JSONParser().parse(result);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	jo = (JSONObject)obj;

	boolean rezultat = (boolean) jo.get("rezultat"); 
	
	return rezultat;
}

	

	
	public List<Utilizator> getUtilizator()
	{
		List<Utilizator> utilizator = new ArrayList<Utilizator>();
		
		JSONObject jo = new JSONObject();
        jo.put("command", "getUsers");
        
		String input = jo.toJSONString();
		
		try {
			dos.writeUTF(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return utilizator;
		}
		
		String result;
		try {
			result = dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return utilizator;
		}
		
		System.out.println("Raspuns: " + result);
		
		 Object obj;
			try {
				obj = new JSONParser().parse(result);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return utilizator;
			}
         jo = (JSONObject)obj;
         
         JSONArray ja = (JSONArray) jo.get("users");     
         
         // iterating users
         Iterator itr2 = ja.iterator(); 
         while (itr2.hasNext()) 
         {
        	 Map mapUtilizator = ((Map) itr2.next());
             
        	 Utilizator u = new Utilizator();
        	 
        	 Long CNP = (Long)mapUtilizator.get("cnp");
        	 String numeUtilizator = (String)mapUtilizator.get("userLastName");
        	 String prenumeUtilizator = (String)mapUtilizator.get("userFirstName");
        	 String Adresa = (String)mapUtilizator.get("adresa");
        	 String Email = (String)mapUtilizator.get("email");
        	 String Parola = (String)mapUtilizator.get("parola"); 
        	 String Rol = (String)mapUtilizator.get("rol");
        	        	 
        	 u.setNume(numeUtilizator);
        	 u.setPrenume(prenumeUtilizator);
        	 u.setAdresa(Adresa);
        	 u.setCNP(CNP);
        	 u.setParola(Parola);
        	 u.setRol(Rol);
        	 u.setEmail(Email);

        	 utilizator.add(u);
         }
		
		return utilizator;
	}
}
