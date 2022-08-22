package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.ControllerCarti;
import controller.ControllerClienti;
import dataObject.Autor;
import dataObject.Carte;
import dataObject.Utilizator;

import java.awt.event.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class GestionareAdmin extends JFrame {

	JPanel contentPane;
	ControllerClienti controller;
	ControllerCarti controllerCarti;
	DataInputStream dis;
	DataOutputStream dos;
	String emailClient;
	JButton jb_afiseaza;
	DefaultTableModel model;
	DefaultTableModel model2;
	JScrollPane scrollPane;
	JScrollPane scrollPane2;
	JTable jt_carti;
	JTable jt_clienti;
	JButton jb_adaugaAdmin;
	JButton jb_afiseazaC;
	JButton jb_adaugareUtilizatorTexte;
	JButton jb_adaugareCarteTexte;
	JButton jb_adaugareCarte;
	JLabel jl_CNP;	
	JTextField jtf_editare_textCNP = new JTextField();
	JLabel jl_Nume;	
	JTextField jtf_editare_textNume = new JTextField();
	JLabel jl_Prenume;	
	JTextField jtf_editare_textPrenume = new JTextField();
	JLabel jl_Adresa;	
	JTextField jtf_editare_textAdresa = new JTextField();
	JLabel jl_Email;	
	JTextField jtf_editare_textEmail = new JTextField();
	JLabel jl_Parola;	
	JTextField jtf_editare_textParola = new JTextField();
	JLabel jl_Rol;	
	JTextField jtf_editare_textRol = new JTextField();
	JButton jb_adaugareUtilizator = new JButton();
	JLabel jl_ISBN;	
	JTextField jtf_editare_textISBN = new JTextField();
	JLabel jl_Titlu;	
	JTextField jtf_editare_textTitlu = new JTextField();
	JLabel jl_CodLocatie;	
	JTextField jtf_editare_textCodLocatie = new JTextField();
	JLabel jl_Format;	
	JTextField jtf_editare_textFormat = new JTextField();
	JLabel jl_NumarBucati;	
	JTextField jtf_editare_textNumarBucati = new JTextField();
	JLabel jl_AnulPublicarii;	
	JTextField jtf_editare_textAnulPublicarii = new JTextField();
	JLabel jl_Editura;	
	JTextField jtf_editare_textEditura = new JTextField();
	JLabel jl_NumarPagini;	
	JTextField jtf_editare_textNumarPagini = new JTextField();
	JLabel jl_NumeAutor;	
	JTextField jtf_editare_textNumeAutor = new JTextField();
	JLabel jl_PrenumeAutor;	
	JTextField jtf_editare_textPrenumeAutor = new JTextField();
	JLabel jl_Nationalitate;	
	JTextField jtf_editare_textNationalitate = new JTextField();
	JLabel jl_Domiciliul;	
	JTextField jtf_editare_textDomiciliul = new JTextField();
	JLabel jl_AnulNasterii;	
	JTextField jtf_editare_textAnulNasterii = new JTextField();
	public GestionareAdmin(DataInputStream dInput, DataOutputStream dOutput, String email) {
		dis = dInput;
		dos = dOutput;
		emailClient = email;
		InitInterface();
		controller = new ControllerClienti(dis, dos);
		controllerCarti = new ControllerCarti(dis, dos);
	}
	
	private void ScoateDateAdaugare()
	{
		try {
			contentPane.remove(jl_CNP);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textCNP);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_Nume);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textNume);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_Prenume);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textPrenume);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_Adresa);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textAdresa);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_Email);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textEmail);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_Parola);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textParola);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_Rol);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textRol);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jb_adaugareUtilizator);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}

	}

	private void ApasatAfisare() {
		model = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		ScoateDateAdaugare();
		
		jt_carti = new JTable(model);
		model.addColumn("ISBN");
		model.addColumn("Titlu");
		model.addColumn("Autor");
		model.addColumn("Nationalitate");
		model.addColumn("Editura");
		model.addColumn("Numar Pagini");
		model.addColumn("Anul Publicarii");
		
		scrollPane = new JScrollPane(jt_carti);
		scrollPane.setBounds(10, 42, 800, 600);
		
		try {
			contentPane.remove(scrollPane2);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		ScoateDateAdaugare();
		ScoateDateAdaugareCarte();
		this.repaint();
		
		contentPane.add(scrollPane, null);
		
		List<Carte> carti = controller.getCarti();
	
		model.setRowCount(0);

		for (int i = 0; i < carti.size(); i++) {
				Carte c = carti.get(i);

				String titlu = c.getTitlu();
				String ISBN = c.getISBN();
				Autor autor = c.getAutor();
				String editura = c.getEditura();
				int numarPagini = c.getNumarPagini();
				int anulPublicarii = c.getAnulPublicarii();
				String nationalitate = autor.getNationalitate();
				String numeCompletAutor = autor.getNume() + " " + autor.getPrenume();

				model.addRow(new Object[] { ISBN, titlu, numeCompletAutor, nationalitate, editura, numarPagini,
						anulPublicarii });	
		}
	}

	private void ApasatAfisareC() {
		model2 = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		ScoateDateAdaugare();
		
		jt_clienti = new JTable(model2);
		model2.addColumn("CNP");
		model2.addColumn("Nume");
		model2.addColumn("Prenume");
		model2.addColumn("Adresa");
		model2.addColumn("Email");
		model2.addColumn("Parola");
		model2.addColumn("Rol");
		
		scrollPane2 = new JScrollPane(jt_clienti);
		scrollPane2.setBounds(10, 42, 800, 600);
		
		try {
			contentPane.remove(scrollPane);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		ScoateDateAdaugare();
		ScoateDateAdaugareCarte();
		this.repaint();
		
		contentPane.add(scrollPane2, null);		
		
		List<Utilizator> utilizator = controller.getUtilizator();

		model2.setRowCount(0);

		for (int i = 0; i < utilizator.size(); i++) {
			Utilizator u = utilizator.get(i);

			long CNP = u.getCNP();
			String numeUtilizator = u.getNume();
			String prenumeUtilizator = u.getPrenume();
			String Adresa = u.getAdresa();
			String Email = u.getEmail();
			String Parola = u.getParola();
			String Rol = u.getRol();
				
			model2.addRow(new Object[] {CNP , numeUtilizator , prenumeUtilizator , Adresa , Email , Parola , Rol });
		}
	}
	
	private void ApasatAdaugareCarte(String ISBN, String Titlu, int CodLocatie, String Format, 
			int NumarBucati, int AnulPublicarii,  String Editura, int NumarPagini, String NumeAutor, String PrenumeAutor,
			String Nationalitate, String Domiciliul, int AnulNasterii)
	{
		Carte u = new Carte();
		u.setISBN(ISBN);
		u.setTitlu(Titlu);
		u.setCodLocatie(CodLocatie);
		u.setFormat(Format);
		u.setNumarBucati(NumarBucati);
		u.setAnulPublicarii(AnulPublicarii);
		u.setEditura(Editura);
		u.setNumarPagini(NumarPagini);
		
		Autor a = new Autor();
		a.setNume(NumeAutor);
		a.setPrenume(PrenumeAutor);
		a.setNationalitate(Nationalitate);
		a.setDomiciliul(Domiciliul);
		a.setAnulNasterii(AnulNasterii);
		
		boolean adaugatCuSucces =  controllerCarti.AdaugaUtilizator(u, a);
		
		if (adaugatCuSucces)
		{
			JOptionPane.showMessageDialog(null, "Ai adaugat o carte!","Succes",JOptionPane.INFORMATION_MESSAGE);
			ApasatAfisare(); 
			this.repaint();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "A intervenit o eroare la adaugare!","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void ApasatAdaugareUtilizator(Long CNP, String nume, String prenume
			, String adresa, String email, String parola, String rol)
	{
		Utilizator u = new Utilizator();
		u.setCNP(CNP);
		u.setNume(nume);
		u.setPrenume(prenume);
		u.setAdresa(adresa);
		u.setEmail(email);
		u.setParola(parola);
		u.setRol(rol);
		
		boolean adaugatCuSucces =  controller.AdaugaUtilizator(u);
		
		if (adaugatCuSucces)
		{
			JOptionPane.showMessageDialog(null, "Ai adaugat un utilizator!","Succes",JOptionPane.INFORMATION_MESSAGE);
			ApasatAfisareC(); // apelat pt a reincarca tabelul cu utilizatori
			//this.repaint();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "A intervenit o eroare la adaugare!","Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void ApasatAdaugare()
	{
		try {
			contentPane.remove(scrollPane);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		try {
			contentPane.remove(scrollPane2);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		ScoateDateAdaugare();
		ScoateDateAdaugareCarte();
		this.repaint();
		
		jl_CNP = new JLabel();	
		jl_CNP.setText("      CNP");
		jl_CNP.setBounds(10, 50, 130, 22);
		jl_CNP.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_CNP.setForeground(Color.black);
		
		jtf_editare_textCNP.setText("");
		jtf_editare_textCNP.setBounds(130, 50, 240, 22);
		jtf_editare_textCNP.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textCNP.setForeground(Color.black);
		
		jl_Nume = new JLabel();	
		jl_Nume.setText("      Nume");
		jl_Nume.setBounds(10, 75, 130, 22);
		jl_Nume.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_Nume.setForeground(Color.black);
		
		jtf_editare_textNume.setText("");
		jtf_editare_textNume.setBounds(130, 75, 240, 22);
		jtf_editare_textNume.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textNume.setForeground(Color.black);
		
		jl_Prenume = new JLabel();	
		jl_Prenume.setText("      Prenume");
		jl_Prenume.setBounds(10, 100, 130, 22);
		jl_Prenume.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_Prenume.setForeground(Color.black);
		
		jtf_editare_textPrenume.setText("");
		jtf_editare_textPrenume.setBounds(130, 100, 240, 22);
		jtf_editare_textPrenume.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textPrenume.setForeground(Color.black);
		
		jl_Adresa = new JLabel();	
		jl_Adresa.setText("      Adresa");
		jl_Adresa.setBounds(10, 125, 130, 22);
		jl_Adresa.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_Adresa.setForeground(Color.black);
		
		jtf_editare_textAdresa.setText("");
		jtf_editare_textAdresa.setBounds(130, 125, 240, 22);
		jtf_editare_textAdresa.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textAdresa.setForeground(Color.black);
		
		jl_Email= new JLabel();	
		jl_Email.setText("      Email");
		jl_Email.setBounds(10, 150, 130, 22);
		jl_Email.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_Email.setForeground(Color.black);
		
		jtf_editare_textEmail.setText("");
		jtf_editare_textEmail.setBounds(130, 150, 240, 22);
		jtf_editare_textEmail.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textEmail.setForeground(Color.black);
		
		jl_Parola= new JLabel();	
		jl_Parola.setText("      Parola");
		jl_Parola.setBounds(10, 175, 130, 22);
		jl_Parola.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_Parola.setForeground(Color.black);
		
		jtf_editare_textParola.setText("");
		jtf_editare_textParola.setBounds(130, 175, 240, 22);
		jtf_editare_textParola.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textParola.setForeground(Color.black);
		
		
		jl_Rol= new JLabel();	
		jl_Rol.setText("      Rol");
		jl_Rol.setBounds(10, 200, 130, 22);
		jl_Rol.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_Rol.setForeground(Color.black);
		
		jtf_editare_textRol.setText("");
		jtf_editare_textRol.setBounds(130, 200, 240, 22);
		jtf_editare_textRol.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textRol.setForeground(Color.black);
		
		jb_adaugareUtilizator = new JButton();
		jb_adaugareUtilizator.setText("Adauga utilizator");
		jb_adaugareUtilizator.setBounds(10, 250, 200, 22);
		jb_adaugareUtilizator.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jb_adaugareUtilizator.setForeground(Color.RED);
		jb_adaugareUtilizator.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String CNPAsString = jtf_editare_textCNP.getText();
				long CNP = Long.parseLong(CNPAsString);
			
				String nume = jtf_editare_textNume.getText();
				String prenume = jtf_editare_textPrenume.getText();
				String adresa = jtf_editare_textAdresa.getText();
				String email = jtf_editare_textEmail.getText();
				String parola = jtf_editare_textParola.getText();
				String rol = jtf_editare_textRol.getText();
				
				ApasatAdaugareUtilizator(CNP, nume, prenume, adresa, email, parola, rol); //,restul parametrilor
			}
		});
		
		contentPane.add(jl_CNP, null);	
		contentPane.add(jtf_editare_textCNP, null);
		contentPane.add(jl_Nume, null);	
		contentPane.add(jtf_editare_textNume, null);
		contentPane.add(jl_Prenume, null);	
		contentPane.add(jtf_editare_textPrenume, null);
		contentPane.add(jl_Adresa, null);	
		contentPane.add(jtf_editare_textAdresa, null);
		contentPane.add(jl_Email, null);	
		contentPane.add(jtf_editare_textEmail, null);
		contentPane.add(jl_Parola, null);	
		contentPane.add(jtf_editare_textParola, null);
		contentPane.add(jl_Rol, null);	
		contentPane.add(jtf_editare_textRol, null);
		contentPane.add(jb_adaugareUtilizator, null);
		this.repaint();
	}
	
	private void InitInterface()
	{
		setTitle("Administrator");
		
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		
		jb_afiseaza = new JButton();
		jb_afiseaza.setText("Afiseaza cartile");
		jb_afiseaza.setBounds(10, 10, 200, 22);
		jb_afiseaza.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jb_afiseaza.setForeground(Color.RED);
		jb_afiseaza.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApasatAfisare();
			}
		});
		
		jb_afiseazaC = new JButton();
		jb_afiseazaC.setText("Afiseaza toti utilizatorii");
		jb_afiseazaC.setBounds(250, 10, 200, 22);
		jb_afiseazaC.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jb_afiseazaC.setForeground(Color.RED);
		jb_afiseazaC.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ApasatAfisareC();					
			}
		});
		
		
		jb_adaugareUtilizatorTexte = new JButton();
		jb_adaugareUtilizatorTexte.setText("Adauga utilizator");
		jb_adaugareUtilizatorTexte.setBounds(750, 10, 200, 22);
		jb_adaugareUtilizatorTexte.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jb_adaugareUtilizatorTexte.setForeground(Color.RED);
		jb_adaugareUtilizatorTexte.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ApasatAdaugare();					
			}
		});
		
		jb_adaugareCarteTexte = new JButton();
		jb_adaugareCarteTexte.setText("Adauga carte");
		jb_adaugareCarteTexte.setBounds(500, 10, 200, 22);
		jb_adaugareCarteTexte.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jb_adaugareCarteTexte.setForeground(Color.RED);
		jb_adaugareCarteTexte.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				ApasatAdaugareCarte();					
			}
		});
		
		contentPane.add(jb_afiseaza, null);
		contentPane.add(jb_afiseazaC, null);
		contentPane.add(jb_adaugareUtilizatorTexte, null);
		contentPane.add(jb_adaugareCarteTexte, null);
	}
	
	private void ScoateDateAdaugareCarte()
	{
		try {
			contentPane.remove(jl_ISBN);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textISBN);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_Titlu);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textTitlu);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_CodLocatie);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textCodLocatie);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_Format);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textFormat);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_NumarBucati);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textNumarBucati);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_AnulPublicarii);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textAnulPublicarii);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_Editura);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textEditura);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_NumarPagini);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textNumarPagini);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_NumeAutor);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textNumeAutor);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_PrenumeAutor);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textPrenumeAutor);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_Nationalitate);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textNationalitate);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_Domiciliul);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textDomiciliul);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jl_AnulNasterii);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
		
		try {
			contentPane.remove(jtf_editare_textAnulNasterii);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}
			
		try {
			contentPane.remove(jb_adaugareCarte);
		}
		catch (Exception e)
		{
			// System.out.println(e);
		}

	}
	
	/**
	 * 
	 */
	private void ApasatAdaugareCarte()
	{
		try {
			contentPane.remove(scrollPane);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		try {
			contentPane.remove(scrollPane2);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		ScoateDateAdaugare();
		ScoateDateAdaugareCarte();
		this.repaint();
		
		jl_ISBN = new JLabel();	
		jl_ISBN.setText("      ISBN");
		jl_ISBN.setBounds(10, 50, 130, 22);
		jl_ISBN.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_ISBN.setForeground(Color.black);
		
		jtf_editare_textISBN.setText("");
		jtf_editare_textISBN.setBounds(130, 50, 240, 22);
		jtf_editare_textISBN.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textISBN.setForeground(Color.black);
		
		jl_Titlu = new JLabel();	
		jl_Titlu.setText("      Titlu");
		jl_Titlu.setBounds(10, 75, 130, 22);
		jl_Titlu.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_Titlu.setForeground(Color.black);
		
		jtf_editare_textTitlu.setText("");
		jtf_editare_textTitlu.setBounds(130, 75, 240, 22);
		jtf_editare_textTitlu.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textTitlu.setForeground(Color.black);
		
		jl_CodLocatie = new JLabel();	
		jl_CodLocatie.setText("      Cod Locatie");
		jl_CodLocatie.setBounds(10, 100, 130, 22);
		jl_CodLocatie.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_CodLocatie.setForeground(Color.black);
		
		jtf_editare_textCodLocatie.setText("");
		jtf_editare_textCodLocatie.setBounds(130, 100, 240, 22);
		jtf_editare_textCodLocatie.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textCodLocatie.setForeground(Color.black);
		
		jl_Format = new JLabel();	
		jl_Format.setText("      Format");
		jl_Format.setBounds(10, 125, 130, 22);
		jl_Format.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_Format.setForeground(Color.black);
		
		jtf_editare_textFormat.setText("");
		jtf_editare_textFormat.setBounds(130, 125, 240, 22);
		jtf_editare_textFormat.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textFormat.setForeground(Color.black);
	
		jl_NumarBucati= new JLabel();	
		jl_NumarBucati.setText("      Numar Bucati");
		jl_NumarBucati.setBounds(10, 150, 130, 22);
		jl_NumarBucati.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_NumarBucati.setForeground(Color.black);
		
		jtf_editare_textNumarBucati.setText("");
		jtf_editare_textNumarBucati.setBounds(130, 150, 240, 22);
		jtf_editare_textNumarBucati.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textNumarBucati.setForeground(Color.black);
		
		jl_AnulPublicarii= new JLabel();	
		jl_AnulPublicarii.setText("      Anul Publicarii");
		jl_AnulPublicarii.setBounds(10, 175, 130, 22);
		jl_AnulPublicarii.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_AnulPublicarii.setForeground(Color.black);
		
		jtf_editare_textAnulPublicarii.setText("");
		jtf_editare_textAnulPublicarii.setBounds(130, 175, 240, 22);
		jtf_editare_textAnulPublicarii.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textAnulPublicarii.setForeground(Color.black);
		
		
		jl_Editura= new JLabel();	
		jl_Editura.setText("      Editura");
		jl_Editura.setBounds(10, 200, 130, 22);
		jl_Editura.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_Editura.setForeground(Color.black);
		
		jtf_editare_textEditura.setText("");
		jtf_editare_textEditura.setBounds(130, 200, 240, 22);
		jtf_editare_textEditura.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textEditura.setForeground(Color.black);
		
		jl_NumarPagini= new JLabel();	
		jl_NumarPagini.setText("      Numar Pagini");
		jl_NumarPagini.setBounds(10, 225, 130, 22);
		jl_NumarPagini.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_NumarPagini.setForeground(Color.black);
		
    	jtf_editare_textNumarPagini.setText("");
    	jtf_editare_textNumarPagini.setBounds(130, 225, 240, 22);
    	jtf_editare_textNumarPagini.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
    	jtf_editare_textNumarPagini.setForeground(Color.black);
		
		jl_NumeAutor= new JLabel();	
		jl_NumeAutor.setText("      Nume Autor");
		jl_NumeAutor.setBounds(10, 250, 130, 22);
		jl_NumeAutor.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_NumeAutor.setForeground(Color.black);
		
		jtf_editare_textNumeAutor.setText("");
		jtf_editare_textNumeAutor.setBounds(130, 250, 240, 22);
		jtf_editare_textNumeAutor.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textNumeAutor.setForeground(Color.black);
	
    	jl_PrenumeAutor= new JLabel();	
		jl_PrenumeAutor.setText("      Prenume Autor");
		jl_PrenumeAutor.setBounds(10, 275, 130, 22);
		jl_PrenumeAutor.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_PrenumeAutor.setForeground(Color.black);
		
		jtf_editare_textPrenumeAutor.setText("");
		jtf_editare_textPrenumeAutor.setBounds(130, 275, 240, 22);
		jtf_editare_textPrenumeAutor.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textPrenumeAutor.setForeground(Color.black);
		
		
		jl_Nationalitate= new JLabel();	
		jl_Nationalitate.setText("      Nationalitate");
		jl_Nationalitate.setBounds(10, 300, 130, 22);
		jl_Nationalitate.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_Nationalitate.setForeground(Color.black);
		
		jtf_editare_textNationalitate.setText("");
		jtf_editare_textNationalitate.setBounds(130, 300, 240, 22);
		jtf_editare_textNationalitate.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textNationalitate.setForeground(Color.black);
		
		jl_Domiciliul= new JLabel();	
		jl_Domiciliul.setText("      Domiciliul");
    	jl_Domiciliul.setBounds(10, 325, 130, 22);
		jl_Domiciliul.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_Domiciliul.setForeground(Color.black);
	
		jtf_editare_textDomiciliul.setText("");
		jtf_editare_textDomiciliul.setBounds(130, 325, 240, 22);
		jtf_editare_textDomiciliul.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textDomiciliul.setForeground(Color.black);
		
		jl_AnulNasterii= new JLabel();	
		jl_AnulNasterii.setText("      AnulNasterii");
		jl_AnulNasterii.setBounds(10, 350, 130, 22);
		jl_AnulNasterii.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jl_AnulNasterii.setForeground(Color.black);
		
		jtf_editare_textAnulNasterii.setText("");
		jtf_editare_textAnulNasterii.setBounds(130, 350, 240, 22);
		jtf_editare_textAnulNasterii.setFont(new java.awt.Font("SansSerif", Font.ITALIC, 12));
		jtf_editare_textAnulNasterii.setForeground(Color.black);
		
		jb_adaugareCarte = new JButton();
		jb_adaugareCarte.setText("Adauga cartea");
		jb_adaugareCarte.setBounds(10, 390, 200, 22);
		jb_adaugareCarte.setFont(new java.awt.Font("SansSerif", Font.PLAIN, 12));
		jb_adaugareCarte.setForeground(Color.RED);
		jb_adaugareCarte.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ISBN =  jtf_editare_textISBN.getText();	
				//System.out.println("isbn" + ISBN);
				String Titlu = jtf_editare_textTitlu.getText();
				//System.out.println("titlu" + Titlu);
				String Format = jtf_editare_textFormat.getText();
				//System.out.println("format" + Format);
				String Editura = jtf_editare_textEditura.getText();
				//System.out.println("editura" + Editura);
				String NumeAutor = jtf_editare_textNumeAutor.getText();
				//System.out.println("nume a " + NumeAutor);
				String PrenumeAutor = jtf_editare_textPrenumeAutor.getText();
				//System.out.println("prenume a" + PrenumeAutor);
				String Nationalitate = jtf_editare_textNationalitate.getText();
				//System.out.println("Nationalitate" + Nationalitate);
				String Domiciliul = jtf_editare_textDomiciliul.getText();
				//System.out.println("Domiciliul " + Domiciliul );				
				
				String CodLocatieAsString = jtf_editare_textCodLocatie.getText();
				//System.out.println("cod loc" + CodLocatieAsString);
				int CodLocatie = Integer.parseInt(CodLocatieAsString);
				String NumarBucatiAsString = jtf_editare_textNumarBucati.getText();
				//System.out.println("nr bucati" + NumarBucatiAsString);
				int NumarBucati = Integer.parseInt(NumarBucatiAsString);
				String AnulPublicariiAsString = jtf_editare_textAnulPublicarii.getText();
				//System.out.println("anul pub" + AnulPublicariiAsString);
				int AnulPublicarii = Integer.parseInt(AnulPublicariiAsString);
				String NumarPaginiAsString = jtf_editare_textNumarPagini.getText();
				//System.out.println("numar pg " + NumarPaginiAsString);
				int NumarPagini = Integer.parseInt(NumarPaginiAsString);
				String AnulNasteriiAsString = jtf_editare_textAnulNasterii.getText();
				//System.out.println("anul nasterii:" + AnulNasteriiAsString);
				int AnulNasterii= Integer.parseInt(AnulNasteriiAsString);
				
				ApasatAdaugareCarte(ISBN, Titlu, CodLocatie, Format, NumarBucati, AnulPublicarii, 
						Editura, NumarPagini, NumeAutor, PrenumeAutor, Nationalitate, Domiciliul, AnulNasterii); 
			}
		});
			
		contentPane.add(jl_ISBN, null);
		contentPane.add(jtf_editare_textISBN, null);
		contentPane.add(jl_Titlu, null);
		contentPane.add(jtf_editare_textTitlu, null);
		contentPane.add(jl_CodLocatie, null);
		contentPane.add(jtf_editare_textCodLocatie, null);
		contentPane.add(jl_Format, null);
		contentPane.add(jtf_editare_textFormat, null);
		contentPane.add(jl_NumarBucati, null);
		contentPane.add(jtf_editare_textNumarBucati, null);
		contentPane.add(jl_AnulPublicarii, null);
		contentPane.add(jtf_editare_textAnulPublicarii, null);
		contentPane.add(jl_Editura, null);
		contentPane.add(jtf_editare_textEditura, null);
		contentPane.add(jl_NumarPagini, null);
		contentPane.add(jtf_editare_textNumarPagini, null);
		contentPane.add(jl_NumeAutor, null);
		contentPane.add(jtf_editare_textNumeAutor, null);
		contentPane.add(jl_PrenumeAutor, null);
		contentPane.add(jtf_editare_textPrenumeAutor, null);
		contentPane.add(jl_Nationalitate, null);
		contentPane.add(jtf_editare_textNationalitate, null);
		contentPane.add(jl_Domiciliul, null);
		contentPane.add(jtf_editare_textDomiciliul, null);
		contentPane.add(jl_AnulNasterii, null);
		contentPane.add(jtf_editare_textAnulNasterii, null);
		contentPane.add(jb_adaugareCarte, null);
		this.repaint();
	}
}
