package kassensoftware;

public class Produkt {
	
	private String name, ean, anzahl;
	private Float preis, gewicht, grundpreis;
	private Kategorie kategorie;
	
	Produkt(String name, String ean, Float preis, Float gewicht, String anzahl, Kategorie kategorie) {
		this.name = name;
		this.ean = ean;
		this.preis = preis;
		this.gewicht = gewicht; //in g bzw. ml
		Float faktor = 1000 / this.gewicht;
		this.grundpreis = this.preis * faktor;
		this.anzahl = anzahl;
		this.kategorie = kategorie;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setEan(String ean) {
		this.ean = ean;
	}
	
	public void setPreis(Float preis) {
		this.preis = preis;
	}
	
	public void setGewicht(Float gewicht) {
		this.gewicht = gewicht;
	}
	
	public void setAnzahl(String anzahl) {
		this.anzahl = anzahl;
	}
	
	public void setKategorie(Kategorie kategorie) {
		this.kategorie = kategorie;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEan() {
		return this.ean;
	}
	
	public Float getPreis() {
		return this.preis;
	}
	
	public Float getGewicht() {
		return this.gewicht;
	}
	
	public Float getGrundpreis() {
		return this.grundpreis;
	}
	
	public String getAnzahl() {
		return this.anzahl;
	}
	
	public Kategorie getKategorie() {
		return this.kategorie;
	}
	
}
