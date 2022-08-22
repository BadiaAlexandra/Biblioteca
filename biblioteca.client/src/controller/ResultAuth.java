package controller;

public class ResultAuth {
	boolean ok;
	String nume;
	String rol;
	
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	String eroare;
	
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getEroare() {
		return eroare;
	}
	public void setEroare(String eroare) {
		this.eroare = eroare;
	}
}
