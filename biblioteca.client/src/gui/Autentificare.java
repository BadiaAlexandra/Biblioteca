package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.ControllerAutentificare;
import controller.ControllerClienti;
import controller.ResultAuth;

public class Autentificare extends JFrame{
	
		JPanel contentPane;
		JLabel jl_first_text = new JLabel();
		JLabel jl_second_text = new JLabel();
		JTextField jtf_editare_text1 = new JTextField();
		JPasswordField jpf_editare_text2 = new JPasswordField();
		JTextField jtf_autentificare = new JTextField();
		JButton jb_autentificare = new JButton();
		
		ControllerAutentificare controller;

		DataInputStream dis;
		DataOutputStream dos;
	
	
	public Autentificare(DataInputStream dInput, DataOutputStream dOutput)
	{
		dis = dInput;
		dos = dOutput;
		InitInterface();
		controller = new ControllerAutentificare(dis, dos);
	}
	
	private void InitInterface()
	{
		setTitle("Client");
		
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		jl_first_text.setText("      Email");
		jl_first_text.setBounds(10, 10, 130, 22);
		jl_first_text.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
	    jl_first_text.setForeground(Color.black);
		
	    jl_second_text.setText("      Parola");
		jl_second_text.setBounds(10, 40, 130, 22);
		jl_second_text.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_second_text.setForeground(Color.black);
				
		jtf_editare_text1.setText("");
		jtf_editare_text1.setBounds(130, 10, 240, 22);
		jtf_editare_text1.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_text1.setForeground(Color.black);
				
		jpf_editare_text2.setText("");
		jpf_editare_text2.setEchoChar('*');
		jpf_editare_text2.setBounds(130, 40, 240, 22);
		jpf_editare_text2.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jpf_editare_text2.setForeground(Color.black);
	    
	    jb_autentificare.setText("Autentificare");
	    jb_autentificare.setBounds(150, 70, 200, 22);
	    jb_autentificare.setFont(new java.awt.Font("SansSerif", Font.BOLD, 12));
	    jb_autentificare.setForeground(Color.red);
	    jb_autentificare.addActionListener(
				new java.awt.event.ActionListener() {
						public void actionPerformed(ActionEvent e)
						{
							ApasatAfisare();
						}
		});
	    
	    contentPane.add(jl_first_text, null);
	    contentPane.add(jl_second_text, null);
	    contentPane.add(jtf_editare_text1, null);
	    contentPane.add(jpf_editare_text2, null);
	    contentPane.add(jb_autentificare, null);
	}
	
	void ApasatAfisare()
	{
		String email = jtf_editare_text1.getText();
		
		String password = new String(jpf_editare_text2.getPassword());
		ResultAuth res = controller.VerificaUser(email, password);
		
		if (res.isOk())
		{
			String msg = "Te-ai autentificat cu succes! ";
			msg += "Bine ai venit, ";
			msg += res.getNume();
			msg += "!";
			
			String rol = res.getRol();
			
			JOptionPane.showMessageDialog(null, msg);
			
			this.dispose();
			
			if (rol.equals("Client"))
			{
				GestionareClienti client = new GestionareClienti(dis, dos, email);
				
				client.setSize(1000, 1000);
				client.setVisible(true);
			}
			else
			{
				GestionareAdmin admin = new GestionareAdmin(dis, dos, email);
				
				admin.setSize(1000, 1000);
				admin.setVisible(true);
			}		
		}
		else
		{
			String msg = "Nu te-ai autentificat cu succes! ";
			msg += res.getEroare();
			
			JOptionPane.showMessageDialog(null, msg, "Eroare",
				    JOptionPane.ERROR_MESSAGE);
		}
	}

}
