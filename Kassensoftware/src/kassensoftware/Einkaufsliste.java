
import java.util.ArrayList;

/**
 * Klasse zum Verwalten der Einkaufsliste (Produkte die gekauft werden sollen).
 * 
 * @author Nils
 *
 */
public class Einkaufsliste {

	ArrayList<Produkt> einkaufsliste;

	/**
	 * Konstruktor in dem die Einkaufsliste als leere ArrayList initialisiert wird.
	 * Wird aus dem EinkaufPanel aufgerufen.
	 * 
	 */
	public Einkaufsliste() {
		einkaufsliste = new ArrayList<Produkt>();
	}

	/**
	 * Produkt wird der Einkaufsliste hinzugef�gt.
	 * 
	 * @param produktNeu beinhaltet zu �bergebendes Produkt von Typ Produkt
	 */
	public void produktHinzufuegen(Produkt produktNeu) {
		einkaufsliste.add(produktNeu);
	}

	/**
	 * Produkt wird in der Einkaufsliste gesucht.
	 * 
	 * @param Produktname des zu suchenden Produkts
	 * @return Produkt p mit dem Namen produktName oder null falls das Produkt nicht
	 *         gefunden wurde
	 */
	public Produkt findeProdukt(String produktName) {
		for (Produkt p : einkaufsliste) {
			if (p.getName().equals(produktName)) {
				return p;
			}
		}
		return null;
	}

	/**
	 * Suche ob das Produkt mit dem Parameter produktName in der Einkaufsliste
	 * enthalten ist.
	 * 
	 * @param Produktname des zu suchenden Produkts
	 * @return true wenn das Produkt mit dem produktName in der Einkaufsliste
	 *         enthalten ist, sonst false
	 */
	public boolean sucheProdukt(String produktName) {
		for (Produkt p : einkaufsliste) {
			if (p.getName().equals(produktName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Mehode zum l�schen eines Produkts aus der Einkaufsliste.
	 * 
	 * @param Produktname des zu l�schenden Produkts
	 */
	public void produktStornieren(String produktName) {
		if (sucheProdukt(produktName) == true) {
			einkaufsliste.remove(findeProdukt(produktName));
		}
	}

	/**
	 * Alle Elemente der Einkaufsliste werden gel�scht.
	 */
	public void einkaufStornieren() {
		einkaufsliste.clear();
	}

}