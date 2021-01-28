
import java.util.ArrayList;

public class Einkaufsliste {

	ArrayList<Produkt> einkaufsliste;

	public Einkaufsliste() {
		einkaufsliste = new ArrayList<Produkt>();
	}

	public void produktHinzufuegen(Produkt produktNeu) {
		einkaufsliste.add(produktNeu);
	}

	public Produkt findeProdukt(String produktName) {
		for (Produkt p : einkaufsliste) {
			if (p.getName().equals(produktName)) {
				return p;
			}
		}
		return null;
	}

	public boolean sucheProdukt(String produktName) {
		for (Produkt p : einkaufsliste) {
			if (p.getName().equals(produktName)) {
				return true;
			}
		}
		return false;
	}

	public void produktStornieren(String produktName) {
		if (sucheProdukt(produktName) == true) {
			einkaufsliste.remove(findeProdukt(produktName));
		}
	}

	public void einkaufStornieren() {
		einkaufsliste.clear();
	}

//	public void listeAusgeben() {
//		if (einkaufsliste.isEmpty() == true) {
//		}
//		for (Produkt p : einkaufsliste) {
//			System.out.println(
//					"Produktname: " + p.getName() + "; EAN: " + p.getEan() + " Preis: " + p.getPreis() + "; Gewicht: "
//							+ p.getGewicht() + "; Anzahl: " + p.getAnzahl() + "; Kategorie: " + p.getKategorie());
//		}
//
//	}

}