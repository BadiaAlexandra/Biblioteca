package dataObject;

public class Inchiriere {
	String ISBN;
	int ID,CNP;
	
//constructor default
	public Inchiriere()
	{
		this.ISBN = "";
		this.ID = 0;
		this.CNP = 0;
	}
	
// constructor cu parametrii
	public Inchiriere(String iSBN, int iD, int cNP) {
	super();
	ISBN = iSBN;
	ID = iD;
	CNP = cNP;
}
	
// metode de set si get pentru fiecare atribut al clasei
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getCNP() {
		return CNP;
	}

	public void setCNP(int cNP) {
		CNP = cNP;
	}
	
    // metoda de afisare informatii despre inchiriere
	public void Afisare()
	{
		System.out.println("===========================================");
		System.out.println("Inchirierea cu ID-ul " + this.ID) ;
		System.out.println("a cartii cu ISBN-ul: " + this.ISBN);
		System.out.println("facuta pe baza CNP-ului: " + this.CNP);
	
	}
}
