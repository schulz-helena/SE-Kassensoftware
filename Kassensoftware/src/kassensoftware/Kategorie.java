package kassensoftware;

/**
 * Methode zum Erzeugen einer <code>Kategorie</code> und deren Verwaltung.
 * 
 * @author Nils Kohler
 *
 */
public class Kategorie {

	private String kategorieName;

	/**
	 * Konstruktor initialisiert das Attribut <code>kategorieName</code>.
	 * 
	 * @param kategorieNeu Name der neuen Kategorie
	 */
	public Kategorie(String kategorieNeu) {
		this.setKategorieName(kategorieNeu);
	}

	/**
	 * <code>Getter</code> f�r <code>kategorieName</code>.
	 * 
	 * @return kategorieName der Kategorie als <code>String</code>
	 */
	public String getKategorieName() {
		return kategorieName;
	}

	/**
	 * Setter f�r <code>kategorieName</code>.
	 * 
	 * @param kategorieName neuer Name einer <code>Kategorie</code>
	 */
	public void setKategorieName(String kategorieName) {
		this.kategorieName = kategorieName;
	}

	/**
	 * Overrides
	 * 
	 * @return kategorieName in Form eines <code>String</code> und keine
	 *         Speicheradresse
	 */
	public String toString() {
		return kategorieName;
	}

}