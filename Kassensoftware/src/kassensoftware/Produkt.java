package kassensoftware;

/**
 * Die Klasse <code> Produkt </code> kann ein Produkt mit all seinen Eigenschaften erzeugen 
 * 
 * @author Helena
 *
 */
public class Produkt {
	
	private String name, ean, anzahl; //anzahl als String, damit Sonderzustand n gespeichert werden kann
	private Float preis, gewicht, grundpreis;
	private Kategorie kategorie;
	
	/**
	 * Konstruktor zur Initialisierung eines Produkts mit allen Eigenschaften
	 * 
	 * @param name		Produktname
	 * @param ean		EAN des Produkts
	 * @param preis		Preis des Produkts
	 * @param gewicht	Gewicht des Produkts
	 * @param anzahl	Bestand des Produkts im Lager
	 * @param kategorie	Kategorie, die dem Produkt zugeordnet wird
	 */
	public Produkt(String name, String ean, Float preis, Float gewicht, String anzahl, Kategorie kategorie) {
		this.name = name;
		this.ean = ean;
		this.preis = preis;
		this.gewicht = gewicht; //in g bzw. ml
		Float faktor = 1000 / this.gewicht;
		this.grundpreis = this.preis * faktor;
		this.anzahl = anzahl;
		this.kategorie = kategorie;
	}
	
	/**
	 * Setter fuer <code>name</code>.
	 * 
	 * @param name	neuer Name von einem <code>Produkt</code>
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Setter fuer <code>ean</code>.
	 * 
	 * @param ean	neue EAN von einem <code>Produkt</code>
	 */
	public void setEan(String ean) {
		this.ean = ean;
	}
	
	/**
	 * Setter fuer <code>preis</code>.
	 * 
	 * @param preis	neuer Preis von einem <code>Produkt</code>
	 */
	public void setPreis(Float preis) {
		this.preis = preis;
	}
	
	/**
	 * Setter fuer <code>gewicht</code>.
	 * 
	 * @param gewicht	neues Gewicht von einem <code>Produkt</code>
	 */
	public void setGewicht(Float gewicht) {
		this.gewicht = gewicht;
	}
	
	/**
	 * Setter fuer <code>anzahl</code>.
	 * 
	 * @param anzahl	neuer Bestand von einem <code>Produkt</code>
	 */
	public void setAnzahl(String anzahl) {
		this.anzahl = anzahl;
	}
	
	/**
	 * Setter fuer <code>kategorie</code>.
	 * 
	 * @param kategorie	neue Kategorie von einem <code>Produkt</code>
	 */
	public void setKategorie(Kategorie kategorie) {
		this.kategorie = kategorie;
	}
	
	/**
	 * Getter fuer <code>name</code>.
	 * 
	 * @return Produktname als <code>String</code>
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Getter fuer <code>ean</code>.
	 * 
	 * @return EAN des Produkts als <code>String</code>
	 */
	public String getEan() {
		return this.ean;
	}
	
	/**
	 * Getter fuer <code>preis</code>.
	 * 
	 * @return Preis des Produkts als <code>Float</code>
	 */
	public Float getPreis() {
		return this.preis;
	}
	
	/**
	 * Getter fuer <code>gewicht</code>.
	 * 
	 * @return Gewicht des Produkts als <code>Float</code>
	 */
	public Float getGewicht() {
		return this.gewicht;
	}
	
	/**
	 * Getter fuer <code>grundpreis</code>.
	 * 
	 * @return Grundpreis des Produkts als <code>Float</code>
	 */
	public Float getGrundpreis() {
		return this.grundpreis;
	}
	
	/**
	 * Getter fuer <code>anzahl</code>.
	 * 
	 * @return Bestand des Produkts als <code>String</code>
	 */
	public String getAnzahl() {
		return this.anzahl;
	}
	
	/**
	 * Getter fuer <code>kategorie</code>.
	 * 
	 * @return Kategorie des Produkts als <code>Kategorie</code>
	 */
	public Kategorie getKategorie() {
		return this.kategorie;
	}
}
