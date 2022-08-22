package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.ControllerClienti;
import dataObject.Autor;
import dataObject.Carte;

import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class GestionareClienti extends JFrame {
	
	JPanel contentPane;
	JButton jb_afiseaza;
	JButton jb_afiseazainchiriate;
	DefaultTableModel model;
	JScrollPane scrollPane;
	JTable jt_carti;
	JButton jb_inchiriaza;
	JButton jb_returneaza;
	

	ControllerClienti controller;
	
	DataInputStream dis;
	DataOutputStream dos;
	
	String emailClient;
	
	public GestionareClienti(DataInputStream dInput, DataOutputStream dOutput, String email)
	{
		dis = dInput;
		dos = dOutput;
		emailClient = email;
		InitInterface();
		controller = new ControllerClienti(dis, dos);
	}
	
	private void ApasatAfisare()
	{
		List<Carte> carti = controller.getCarti();
		
		model.setRowCount(0);
		for (int i=0; i<carti.size(); i++)
		{
			Carte c = carti.get(i);
			
			String titlu = c.getTitlu();
			String ISBN  = c.getISBN();
			Autor autor = c.getAutor();
			String editura = c.getEditura();
			int numarPagini = c.getNumarPagini();
			int anulPublicarii = c.getAnulPublicarii();
			String nationalitate = autor.getNationalitate();			
			String numeCompletAutor = autor.getNume() + " " + autor.getPrenume(); 
			
			model.addRow(new Object[]{ ISBN, titlu, numeCompletAutor, nationalitate, editura, numarPagini, anulPublicarii });
		}	
	}
	
	private void ApasatAfisareTermenPredare()
	{
		List<Carte> carti = controller.getCartiInchiriate(emailClient);
		
		model.setRowCount(0);
		for (int i=0; i<carti.size(); i++)
		{
			Carte c = carti.get(i);
			
			String titlu = c.getTitlu();
			String ISBN  = c.getISBN();
			Autor autor = c.getAutor();
			String editura = c.getEditura();
			int numarPagini = c.getNumarPagini();
			int anulPublicarii = c.getAnulPublicarii();
			String nationalitate = autor.getNationalitate();			
			String numeCompletAutor = autor.getNume() + " " + autor.getPrenume(); 
			String termenPredare = c.getTermenPredare();
			
			try {
			
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
				LocalDate datePredare = LocalDate.parse(termenPredare, formatter);
				LocalDate datePrezent = LocalDate.now();	
				
				if (datePredare.isBefore(datePrezent))
				{
					String msg = "Nu ai predat cartea: ";
					msg += titlu;
					msg += "!";
					
					JOptionPane.showMessageDialog(null, msg, "Atentie!!!",
						    JOptionPane.WARNING_MESSAGE);
				}					
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			model.addRow(new Object[]{ ISBN, titlu, numeCompletAutor, nationalitate, editura, numarPagini, anulPublicarii, termenPredare });
		}	
	}
	
	private void ApasatInchiriaza(String isbn, String email)
	{ 
		String rezultat = controller.InchiriazaCarte(isbn, email);
		
		if (rezultat.equals("Success"))
		{
			JOptionPane.showMessageDialog(null, "Ai inchiriat o carte!","Succes",JOptionPane.INFORMATION_MESSAGE);
			ApasatAfisare(); // apelat pt a reincarca tabelul cu cartile disponibile
		}
		else
		{
			String msg = "A intervenit o eroare la inchiriere! ";
			msg += rezultat;
			JOptionPane.showMessageDialog(null, msg,"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void ApasatReturneaza(String isbn, String email)
	{
		boolean returnatCuSucces = controller.ReturneazaCarte(isbn, email);
		
		if (returnatCuSucces)
		{
			JOptionPane.showMessageDialog(null, "Ai returnat o carte!","Succes",JOptionPane.INFORMATION_MESSAGE);
			ApasatAfisareTermenPredare(); // apelat pt a reincarca tabelul cu cartile inchiriate
		}
		else
		{
			JOptionPane.showMessageDialog(null, "A intervenit o eroare la returnare!","Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void InitInterface()
	{
		setTitle("Client");
		
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null); 
	    
		jb_afiseaza = new JButton();
		jb_afiseaza.setText("Afiseaza cartile disponibile");
		jb_afiseaza.setBounds(10, 10, 200, 22);
		jb_afiseaza.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jb_afiseaza.setForeground(Color.RED);
		jb_afiseaza.addActionListener(
				new java.awt.event.ActionListener() {
						public void actionPerformed(ActionEvent e)
						{
							ApasatAfisare();
							jb_inchiriaza.setEnabled(true);
							jb_returneaza.setEnabled(false);
						}
		});
		
		
		
		jb_inchiriaza = new JButton();
		jb_inchiriaza.setText("Inchiriaza cartea selectata");
		jb_inchiriaza.setBounds(500, 10, 200, 22);
		jb_inchiriaza.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jb_inchiriaza.setForeground(Color.RED);
		jb_inchiriaza.setEnabled(false);
		jb_inchiriaza.addActionListener(
				new java.awt.event.ActionListener() {
						public void actionPerformed(ActionEvent e)
						{
							int column = 0;
							int row = jt_carti.getSelectedRow();
							if (row == -1) {
								JOptionPane.showMessageDialog(null, "Nu ai selectat o carte!","Error",
									    JOptionPane.ERROR_MESSAGE);
							}
							else
							{
								String isbn = jt_carti.getModel().getValueAt(row, column).toString();
								ApasatInchiriaza(isbn, emailClient);
							}
						}
		});
		
		contentPane.add(jb_inchiriaza, null);
		
		model = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
	
		jt_carti = new JTable(model); 
		model.addColumn("ISBN");
		model.addColumn("Titlu");
		model.addColumn("Autor");
		model.addColumn("Nationalitate");
		model.addColumn("Editura");
		model.addColumn("Numar Pagini");
		model.addColumn("Anul Publicarii");
		model.addColumn("Termen predare");
		
		scrollPane = new JScrollPane(jt_carti);
		scrollPane.setBounds(10, 42, 800, 600);
		
		contentPane.add(jb_afiseaza, null);
		contentPane.add(scrollPane, null);
		
		jb_afiseazainchiriate = new JButton();
		jb_afiseazainchiriate.setText("Afiseaza cartile inchiriate");
		jb_afiseazainchiriate.setBounds(250, 10, 200, 22);
		jb_afiseazainchiriate.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jb_afiseazainchiriate.setForeground(Color.RED);
		jb_afiseazainchiriate.addActionListener(
				new java.awt.event.ActionListener() {
						public void actionPerformed(ActionEvent e)
						{
							ApasatAfisareTermenPredare();
							jb_inchiriaza.setEnabled(false);
							jb_returneaza.setEnabled(true);
						}
		});
		contentPane.add(jb_afiseazainchiriate, null);
		
		contentPane.add(scrollPane, null);
	
	jb_returneaza = new JButton();
	jb_returneaza.setText("Returneaza cartea selectata");
	jb_returneaza.setBounds(760, 10, 200, 22);
	jb_returneaza.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
	jb_returneaza.setForeground(Color.RED);
	jb_returneaza.setEnabled(false);
	jb_returneaza.addActionListener(
			new java.awt.event.ActionListener() {
					public void actionPerformed(ActionEvent e)
					{
						int column = 0;
						int row = jt_carti.getSelectedRow();
						if (row == -1) {
							JOptionPane.showMessageDialog(null, "Nu ai selectat o carte!","Error",
								    JOptionPane.ERROR_MESSAGE);
						}
						else
						{
							String isbn = jt_carti.getModel().getValueAt(row, column).toString();
							ApasatReturneaza(isbn, emailClient);
						}
					}
	});
	
	contentPane.add(jb_returneaza, null);
	contentPane.add(scrollPane, null);
	}
}
