package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dataObject.Autor;
import dataObject.Carte;

public class ControllerCarti {
	
	DataInputStream dis;
	DataOutputStream dos;
	
	public ControllerCarti(DataInputStream dInput, DataOutputStream dOutput)
	{
		dis = dInput;
		dos = dOutput;
	}

	public boolean AdaugaUtilizator(Carte c, Autor a)
	{
		JSONObject jo = new JSONObject();
	    jo.put("command", "AddBooks");
	    jo.put("ISBN", c.getISBN());
	    jo.put("Titlu", c.getTitlu());
	    jo.put("CodLocatie", c.getCodLocatie());
	    jo.put("Format", c.getFormat());
	    jo.put("NumarBucati", c.getNumarBucati());
	    jo.put("AnulPublicarii", c.getAnulPublicarii());
	    jo.put("Editura", c.getEditura());
	    jo.put("NumarPagini", c.getNumarPagini());
	    jo.put("Nume", a.getNume());
	    jo.put("Prenume", a.getPrenume());
	    jo.put("Nationalitate", a.getNationalitate());
	    jo.put("Domiciliul", a.getDomiciliul());
	    jo.put("AnulNasterii", a.getAnulNasterii());
	    
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
}
