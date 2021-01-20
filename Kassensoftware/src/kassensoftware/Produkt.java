package kassensoftware;

public class Produkt {
	
	private String name, ean;
	private Float preis, gewicht, grundpreis;
	private Integer anzahl;
	private Kategorie kategorie;
	
	Produkt(String name, String ean, Float preis, Float gewicht, Float grundpreis, Integer anzahl, Kategorie kategorie) {
		this.name = name;
		this.ean = ean;
		this.preis = preis;
		this.gewicht = gewicht; //in g bzw. ml
		this.grundpreis = grundpreis; 
		this.anzahl = anzahl;
		this.kategorie = kategorie;
	}
	
	public void setName(String name) {
		this.name = name;
		//Alle conditions wohl besser in GUI?
		/*if (name.length() < 2) {
			return "Der eingegebene Name ist zu kurz.";
		}
		if (name.length() > 32) {
			return "Der eingegebene Name ist zu lang.";
		}*/
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
	
	private void setGrundpreis(Float gewicht, Float preis) {
		Float faktor = 1000 / gewicht;
		this.grundpreis = preis * faktor;
	}
	
	public void setAnzahl(Integer anzahl) {
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
	
	public Integer getAnzahl() {
		return this.anzahl;
	}
	
	public Kategorie getKategorie() {
		return this.kategorie;
	}
	
}
