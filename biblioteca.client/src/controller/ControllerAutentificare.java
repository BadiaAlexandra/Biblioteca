package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ControllerAutentificare {
	
	DataInputStream dis;
	DataOutputStream dos;
	
	public ControllerAutentificare(DataInputStream dInput, DataOutputStream dOutput)
	{
		dis = dInput;
		dos = dOutput;
	}
	
	public ResultAuth VerificaUser(String email, String parola)
	{
		JSONObject jo = new JSONObject();
		jo.put("command", "validateUser");
		jo.put("Email", email);
		jo.put("Parola", parola);
		
        ResultAuth resultAuth = new ResultAuth();
        resultAuth.setOk(false);
        resultAuth.setEroare("internal error");
    
		String input = jo.toJSONString();
	
		try {
			dos.writeUTF(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return resultAuth;
		}
		
		String result;
		try {
			result = dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return resultAuth;
		}
		
		System.out.println(result);
		
		 Object obj;
			try {
				obj = new JSONParser().parse(result);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return resultAuth;
			}
        jo = (JSONObject)obj;
		
     
        boolean ok = (boolean)jo.get("ok");
        resultAuth.setOk(ok);
        
        if (ok)
        {
        	String nume = (String)jo.get("nume");
        	
        	List<String> numeSiRol = Arrays.asList(nume.split("@"));
        	
        	resultAuth.setNume(numeSiRol.get(0));
        	resultAuth.setRol(numeSiRol.get(1));
        }
        else
        {
        	String eroare = (String)jo.get("eroare");
        	resultAuth.setEroare(eroare);
        }
        
		return resultAuth;
	}
}