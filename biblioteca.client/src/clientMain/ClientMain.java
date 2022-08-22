package clientMain;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.Autentificare;
import gui.GestionareAdmin;
import gui.GestionareClienti;

public class ClientMain {
	
	final static int ServerPort = 55002;

	public static void main(String[] args) {
		
		InetAddress ip;
		try {
			ip = InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		Socket s;
		try {
			s = new Socket(ip, ServerPort);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		DataInputStream dis;
		DataOutputStream dos;
		
		try {
			dis = new DataInputStream(s.getInputStream());
			dos = new DataOutputStream(s.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} 
		
		Autentificare aut = new Autentificare(dis, dos);
		aut.setSize(400, 200);
		aut.setVisible(true);
		
		//GestionareAdmin admin = new GestionareAdmin(dis, dos, "marius@yahoo.com");
		//admin.setSize(1200, 1200);
		//admin.setVisible(true);
	}

}
