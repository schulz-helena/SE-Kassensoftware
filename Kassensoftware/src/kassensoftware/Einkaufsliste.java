import java.util.ArrayList;

/**
 * In dieser Methode wird eine neue Einkaufsliste erstellt.
 * 
 * @author Nils
 *
 */
public class Einkaufsliste {
	public static ArrayList<Artikel> einkaufsliste = new ArrayList<Artikel>();

	/**
	 * Ein neuer Artikel wird der EInkaufsliste hinzugefügt.
	 * 
	 * @param newArtikel
	 */
	public static void neuerArtikel(Artikel newArtikel) {
		einkaufsliste.add(newArtikel);
	}

	/**
	 * Methode um Artikel der sicher in der Liste ist zu finden.
	 * 
	 * @param pName
	 * @return Speicheradresse des gefundenen Artikels
	 */
	public static Artikel findeArtikel(String pName) {
		for (Artikel a : einkaufsliste) {
			if (a.getArtikelname().equals(pName)) {
				return a;
			}
		}
		return null;
	}

	/**
	 * Suche ob der gesuchte Artikel auf der Einkaufsliste steht.
	 * 
	 * @param pName
	 * @return true, wenn Artikel auf Einkaufsliste. Sonst false.
	 */
	public static boolean sucheArtikel(String pName) {
		for (Artikel a : einkaufsliste) {
			if (a.getArtikelname().equals(pName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Methode zum löschen eines Artikels von der Einkaufsliste.
	 * 
	 * @param pName
	 */
	public static void produktStornieren(String pName) {
		if (sucheArtikel(pName) == true) {
			einkaufsliste.remove(findeArtikel(pName));
			System.out.println("Artikel storniert: " + pName);
		} else {
			System.out.println("Artikel nicht auf der Einkaufsliste gefunden!");
		}
	}

	/**
	 * Einkaufsliste wird ausgegeben.
	 * 
	 */
	public static void listeAusgeben() {
		int i = 0;
        for (Artikel a : einkaufsliste) {
        	i++;
            System.out.println("Artikel " + i + ": " + a.getArtikelname());
        }
    }
	
	public static void rechnungErstellen() {
		Rechnung.rechnungErstellen(einkaufsliste);
	}
}
