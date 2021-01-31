
import java.util.ArrayList;

/**
 * Klasse zum Verwalten der Einkaufsliste <code>einkaufsliste</code> (Produkte
 * die gekauft werden sollen).
 * 
 * @author Nils Kohler
 *
 */
public class Einkaufsliste {

	ArrayList<Produkt> einkaufsliste;

	/**
	 * Konstruktor in dem die Einkaufsliste <code>einkaufsliste</code> als leere
	 * <code>ArrayList</code> initialisiert wird. Wird aus dem EinkaufPanel
	 * aufgerufen.
	 * 
	 */
	public Einkaufsliste() {
		einkaufsliste = new ArrayList<Produkt>();
	}

	/**
	 * Ein Produkt wird der <code>einkaufsliste</code> hinzugefügt.
	 * 
	 * @param produktNeu beinhaltet zu übergebendes Produkt von Typ
	 *                   <code>Produkt</code>
	 */
	public void produktHinzufuegen(Produkt produktNeu) {
		einkaufsliste.add(produktNeu);
	}

	/**
	 * Produkt wird in der <code>einkaufsliste</code> gesucht.
	 * 
	 * @param produktName des zu suchenden Produkts
	 * @return p <code>Produkt</code> mit dem Namen <code>produktName</code> oder
	 *         <code>null</code> falls das Produkt nicht gefunden wurde
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
	 * Suche ob das Produkt mit dem Parameter <code>produktName</code> in der
	 * <code>einkaufsliste</code> enthalten ist.
	 * 
	 * @param produktName des zu suchenden Produkts
	 * @return true wenn das Produkt mit dem <code>produktName</code> in der
	 *         <code>einkaufsliste</code> enthalten ist, sonst false
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
	 * Methode zum löschen eines Produkts aus der <code>einkaufsliste</code>.
	 * 
	 * @param produktName des zu löschenden Produkts
	 */
	public void produktStornieren(String produktName) {
		if (sucheProdukt(produktName) == true) {
			einkaufsliste.remove(findeProdukt(produktName));
		}
	}

	/**
	 * Methode zum löschen aller Elemente der <code>einkaufsliste</code>.
	 */
	public void einkaufStornieren() {
		einkaufsliste.clear();
	}

}