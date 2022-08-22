package serverMain;

import java.io.*;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dataObject.Autor;
import dataObject.Carte;
import dataObject.Utilizator;
import dbLogic.AdministrareCarti;

public class ClientHandler implements Runnable{

	DataInputStream input;
	DataOutputStream output;
	Socket socket;
	ProcessClientCommands clientCommands;
	ProcessAuthCommands authCommands;
	ProcessAdminCommands adminCommands;
	
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
	{
		input = dis;
		output = dos;
		socket =s;
		clientCommands = new ProcessClientCommands();
		authCommands = new ProcessAuthCommands();
		adminCommands = new ProcessAdminCommands();
	}
	
	public ClientHandler()
	{
		clientCommands = new ProcessClientCommands();
		authCommands = new ProcessAuthCommands();
		adminCommands = new ProcessAdminCommands();
	}
	
	@Override
	public void run() {
		String received = ""; 
		
		while (true)
		{
            try {
				received = input.readUTF();
			    
	            System.out.println("Received: " + received);
	            
	            Object obj;
				try {
					obj = new JSONParser().parse(received);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					continue;
				}
	            JSONObject jo = (JSONObject) obj;
	            String command =  (String) jo.get("command");
	            
	            String response = "";
	            if (command.equals("getBooks"))
	            {
	            	response = clientCommands.processGetBooks();
	            }
	            else if (command.equals("validateUser"))
	            {
	            	response = authCommands.processUserValidation(jo);
	            }
	            else if(command.equals("getBooksI")) {
	            	String email = (String) jo.get("email");
	            	response = clientCommands.processGetInchiriere(email);
	            }
	            else if(command.equals("rentBook")) {
	            	String email = (String) jo.get("email");
	            	String isbn = (String) jo.get("isbn");
	            	response = clientCommands.processInchiriereCarte(isbn, email);
	            }
	            else if(command.equals("returnedBook"))
	            {
	            	String email = (String) jo.get("email");
	            	String isbn = (String) jo.get("isbn");
	            	response = clientCommands.processReturnareCarte(isbn, email);
	            }
	            else if (command.equals("getUsers"))
	            {
	            	response = adminCommands.processGetUsers();
	            }
	            else if(command.equals("AddUsers"))
	            {
	            	Long CNP = (Long) jo.get("CNP");
	            	String Nume = (String) jo.get("Nume");
	            	String Prenume = (String) jo.get("Prenume");
	            	String Adresa = (String) jo.get("Adresa");
	            	String Parola = (String) jo.get("Parola");
	            	String Email = (String) jo.get("Email");
	            	String Rol = (String) jo.get("Rol");
	            	
	            	Utilizator u = new Utilizator();
	            	u.setCNP(CNP);
	            	u.setNume(Nume);
	            	u.setPrenume(Prenume);
	            	u.setEmail(Email);
	            	u.setParola(Parola);
	            	u.setRol(Rol);
	            	u.setAdresa(Adresa);
	            	
	            	response = adminCommands.processAddUsers(u);
	            }
	            else if(command.equals("AddBooks"))
	            {
	            	String ISBN = (String) jo.get("ISBN");
	            	String Titlu = (String) jo.get("Titlu");
	            	int CodLocatie= ((Long) jo.get("CodLocatie")).intValue();
	            	String Format = (String) jo.get("Format");
	            	int NumarBucati = ((Long) jo.get("NumarBucati")).intValue();
	            	int AnulPublicarii = ((Long) jo.get("AnulPublicarii")).intValue();
	            	String Editura = (String) jo.get("Editura");
	            	int NumarPagini = ((Long) jo.get("NumarPagini")).intValue();
	            	String Nume = (String) jo.get("Nume");
	            	String Prenume = (String) jo.get("Prenume");
	              	String Nationalitate = (String) jo.get("Nationalitate");
	              	String Domiciliul = (String) jo.get("Domiciliul");
	              	int AnulNasterii = ((Long) jo.get("AnulNasterii")).intValue();
	              	
	            	
	        		Carte c = new Carte();
	        		c.setISBN(ISBN);
	        		c.setTitlu(Titlu);
	        		c.setCodLocatie(CodLocatie);
	        		c.setFormat(Format);
	        		c.setNumarBucati(NumarBucati);
	        		c.setAnulPublicarii(AnulPublicarii);
	        		c.setEditura(Editura);
	        		c.setNumarPagini(NumarPagini);
	        		
	        		Autor a = new Autor();
	        		a.setNume(Nume);
	        		a.setPrenume(Prenume);
	        		a.setNationalitate(Nationalitate);
	        		a.setDomiciliul(Domiciliul);
	        		a.setAnulNasterii(AnulNasterii);
	            	
	            	response = adminCommands.processAddBooks(c, a);
	            }
	            System.out.println("response: " + response);
	            this.output.writeUTF(response);
            } catch (IOException e) {
				//e.printStackTrace();
				System.out.println("Client disconnected");
            	break;
			} 
		}	
	}

}
