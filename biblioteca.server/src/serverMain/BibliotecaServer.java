package serverMain;

import java.io.*;
import java.net.*;
import java.util.List;
import java.util.Vector;

import dataObject.Carte;
import dbLogic.AdministrareCarti;

public class BibliotecaServer {

	static Vector<ClientHandler> ar = new Vector<>();
	static int i = 0;

	public static void main(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(55002);
		Socket s;

		while (true) {
			System.out.println("Waiting for new authentification!");
			s = ss.accept(); 

			System.out.println("New client request received : " + s);

			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());

			System.out.println("Creating a new handler for this client...");

			ClientHandler mtch = new ClientHandler(s, dis, dos);

			Thread t = new Thread(mtch);

			System.out.println("Adding this client to active client list");

			ar.add(mtch);

			t.start();

			i++;
		}
	}

}
