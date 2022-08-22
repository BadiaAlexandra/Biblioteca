package dataObject;

public class Carte {
	String ISBN, Titlu, Format, Editura, TermenPredare;
	int CodLocatie, NumarBucati, AnulPublicarii, NumarPagini;
	Autor AutorCarte;

	// constructor default
		public Carte()
		{
			this.ISBN = "";
			this.Titlu = "";
			this.Format = "";
			this.CodLocatie = 0;
			this.NumarBucati = 0;
			this.AnulPublicarii = 0;
			this.Editura = "";
			this.NumarPagini = 0;
			this.TermenPredare = "";
		}

	// constructor cu parametrii
		public Carte(String iSBN, String titlu, String format, Autor autor, int codLocatie, int numarBucati,
				int anulPublicarii, int numarPagini, String editura) {
			super();
			ISBN = iSBN;
			Titlu = titlu;
			Format = format;
			AutorCarte = autor;
			CodLocatie = codLocatie;
			NumarBucati = numarBucati;
			AnulPublicarii = anulPublicarii;
			NumarPagini = numarPagini;
			Editura = editura;
		}

	// metode de set si get pentru fiecare atribut al clasei
		public String getISBN() {
			return ISBN;
		}

		public void setISBN(String iSBN) {
			ISBN = iSBN;
		}

		public String getTitlu() {
			return Titlu;
		}

		public void setTitlu(String titlu) {
			Titlu = titlu;
		}

		public String getFormat() {
			return Format;
		}

		public void setFormat(String format) {
			Format = format;
		}

		public Autor getAutor() {
			return AutorCarte;
		}

		public void setAutor(Autor autor) {
			AutorCarte = autor;
		}

		public int getCodLocatie() {
			return CodLocatie;
		}

		public void setCodLocatie(int codLocatie) {
			CodLocatie = codLocatie;
		}

		public int getNumarBucati() {
			return NumarBucati;
		}

		public void setNumarBucati(int numarBucati) {
			NumarBucati = numarBucati;
		}

		public int getAnulPublicarii() {
			return AnulPublicarii;
		}

		public void setAnulPublicarii(int anulPublicarii) {
			AnulPublicarii = anulPublicarii;
		}
		
		public String getEditura() {
			return Editura;
		}

		public void setEditura(String editura) {
			Editura = editura;
		}

		public int getNumarPagini() {
			return NumarPagini;
		}

		public void setNumarPagini(int numarPagini) {
			NumarPagini = numarPagini;
		}

		public String getTermenPredare() {
			return TermenPredare;
		}

		public void setTermenPredare(String termenPredare) {
			TermenPredare = termenPredare;
		}

		// metoda de afisare informatii despre carte
		public void Afisare()
		{
			System.out.println("===========================================");
			System.out.println("Cartea: " + this.Titlu) ;
			System.out.println("scrisa de autorul: " + this.AutorCarte.getNume() + " " + this.AutorCarte.getPrenume());
			System.out.println("este publicata in: " + this.AnulPublicarii);
			System.out.println("in formatul: " + this.Format);
			System.out.println("avand: " + this.NumarPagini + " de pagini");
			System.out.println("de la editura: " + this.Editura);
			System.out.println("avand ISBN-ul: " + this.ISBN);
			System.out.println("si codul de locatie: " + this.CodLocatie);
			System.out.println("fiind disponibila in numar de " + this.NumarBucati +" bucati");	
		}

}
