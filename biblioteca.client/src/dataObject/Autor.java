package dataObject;

public class Autor {
	String Nume, Prenume, Nationalitate, Domiciliul;
	int ID, AnulNasterii;
	
	//constructor default
	public Autor()
	{
		this.Nume = "";
		this.Prenume = "";
		this.Nationalitate = "";
		this.Domiciliul = "";
		this.ID = 0;
		this.AnulNasterii = 0;
	}

	//constructor cu parametrii
	public Autor(String nume, String prenume, String nationalitate, String domiciliul, int iD, int anulNasterii) {
		super();
		Nume = nume;
		Prenume = prenume;
		Nationalitate = nationalitate;
		Domiciliul = domiciliul;
		ID = iD;
		AnulNasterii = anulNasterii;
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

	public String getNationalitate() {
		return Nationalitate;
	}

	public void setNationalitate(String nationalitate) {
		Nationalitate = nationalitate;
	}

	public String getDomiciliul() {
		return Domiciliul;
	}

	public void setDomiciliul(String domiciliul) {
		Domiciliul = domiciliul;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getAnulNasterii() {
		return AnulNasterii;
	}

	public void setAnulNasterii(int anulNasterii) {
		AnulNasterii = anulNasterii;
	}
	
	
	// metoda de afisare informatii despre autor
		public void Afisare()
		{
			System.out.println("===========================================");
			System.out.println("Autorul " + this.Nume + " " + this.Prenume);
			System.out.println("are ID-ul: " + this.ID);
			System.out.println("este de nationalitate: " + this.Nationalitate);
			System.out.println("cu domiciliul in: " + this.Domiciliul);
			System.out.println("fiind nascut in anul: " + this.AnulNasterii);
	
		}
	
}
