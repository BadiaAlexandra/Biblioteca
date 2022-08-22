package dataObject;

public class Utilizator {
	String Nume, Prenume, Adresa, Email, Parola, Rol;
	long CNP;

	//constructor default
		public Utilizator()
		{
			this.Nume = "";
			this.Prenume = "";
			this.Adresa = "";
			this.Email = "";
			this.Parola = "";
			this.CNP = 0;
			this.Rol = "";
		}
	
	//constructor cu parametrii
	public Utilizator(String nume, String prenume, String adresa, String email, String parola, int cNP) {
		super();
		Nume = nume;
		Prenume = prenume;
		Adresa = adresa;
		Email = email;
		Parola = parola;
		CNP = cNP;
	}

	// metode de set si get pentru fiecare atribut al clasei
	public String getNume() {
		return Nume;
	}

	public void setNume(String nume) {
		Nume = nume;
	}

	public String getPrenume() {
		return Prenume;
	}

	public void setPrenume(String prenume) {
		Prenume = prenume;
	}

	public String getAdresa() {
		return Adresa;
	}

	public void setAdresa(String adresa) {
		Adresa = adresa;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getParola() {
		return Parola;
	}

	public void setParola(String parola) {
		Parola = parola;
	}

	public long getCNP() {
		return CNP;
	}

	public void setCNP(long l) {
		CNP = l;
	}		
	public String getRol() {
		return Rol;
	}

	public void setRol(String rol) {
		Rol = rol;
	}
	
	// metoda de afisare informatii despre utilizator
			public void Afisare()
			{
				System.out.println("===========================================");
				System.out.println("Utilizatorul " + this.Nume + " " + this.Prenume);
				System.out.println("identificat cu CNP-ul: " + this.CNP);
				System.out.println( "e-mailul: " + this.Email);
				System.out.println("si parola: " + this.Parola);
				System.out.println("avand adresa: " + this.Adresa);
				System.out.println("Rolul " + this.Rol);
				
			}
		
	
}
