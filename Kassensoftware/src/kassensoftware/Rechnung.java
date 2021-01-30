import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Methode um die Rechnung eines Einkaufs zu erstellen und die zwischenschritte
 * abzubilden.
 * 
 * @author Nils
 *
 */
public class Rechnung {

	private String rechnungHead;
	ArrayList<String> rechnungBody = new ArrayList<String>();
	private String rechnungEnd;
	private String temp;
	private Float brieftasche;
	private Float gesamtSumme = 0f;
	private Float zwischenSumme = 0f;
	private Float rueckgeld;
	ArrayList<Produkt> einkaufsliste;

	/**
	 * Der Konstruktor macht initialisiert die Einkaufsliste und die brieftasche mit
	 * den Werten aus der GUI.
	 * 
	 * @param brieftascheNeu Geld des Kunden
	 * @param einkaufsliste  Liste der Produkte die einkgekauft werden
	 */
	public Rechnung(Float brieftascheNeu, Einkaufsliste einkaufsliste) {
		this.brieftasche = brieftascheNeu;
		this.einkaufsliste = einkaufsliste.einkaufsliste;

	}

	/**
	 * Das gegebene Geld des Kunden kann angepasst werden.
	 * 
	 * @param geldNeu
	 */
	public void setBrieftasche(Float geldNeu) {
		this.brieftasche = geldNeu;
	}

	/**
	 * Die Abschlussrechnung wird erstellt.
	 * 
	 * @param rech HashMap mit Produkt das gekauft werden soll und der dazugehörigen
	 *             Anzahl
	 */
	public void rechnungErstellen(HashMap<Produkt, String> rech) {
		rechnungHead = "";
		rechnungBody.clear();
		rechnungEnd = "";
		gesamtSumme = 0f;
		zwischenSumme = 0f;

		temp = (String.format("%-23s %-12s %-15s", "Produkt", "Anzahl", "Preis"));
		rechnungHead = temp + "\n" + String.format("%-25s %-15s %-15s\n", "-------------", "----------", "-----------");

		int i = 0;
		for (Produkt p : this.einkaufsliste) {
			String anzahl = getAnzahlForP(p, rech);
			if (anzahl.compareTo("n") == 0) {
				// Sonderzustand n
			} else {
				int anz = Integer.parseInt(anzahl);
				rechnungBody.add(String.format("%-21s %-8d %-5s %-6.2f EUR\n", p.getName(), anz, "x", p.getPreis()));
				Float summeAktuell = anz * p.getPreis();
				rechnungBody.add(String.format("%36s %5.2f EUR\n", "Summe", summeAktuell));
				zwischenSumme = zwischenSumme + summeAktuell;
				i++;
				if (i == einkaufsliste.size()) {
					gesamtSumme = zwischenSumme;
					rueckgeld = brieftasche - gesamtSumme;
					rechnungEnd = "---------------------------------------------\n"
							+ String.format("%34s %6.2f EUR\n", "Gesamtsumme: ", gesamtSumme)
							+ String.format("%34s %6.2f EUR\n", "Erhalten: ", brieftasche)
							+ String.format("%34s %6.2f EUR\n", "Rückgeld: ", rueckgeld);
				}
			}
		}

	}

	/**
	 * Die Rechnung kann aktualisiert werden, immer wenn ein neues Produkt dem
	 * Einkauf hinzugefügt wurde. Die Zwischensumme wird auch ausgegeben.
	 * 
	 * @param rech HashMap mit Produkt das gekauft werden soll und der dazugehörigen
	 *             Anzahl
	 * @return String: Zwischenrechnung wird als String zurückgegeben
	 */
	public String rechnungAktualisieren(HashMap<Produkt, String> rech) {
		temp = (String.format("%-23s %-12s %-15s", "Produkt", "Anzahl", "Preis"));
		rechnungHead = temp + "\n" + String.format("%-25s %-15s %-15s\n", "-------------", "----------", "-----------");

		int i = 0;
		for (Produkt p : this.einkaufsliste) {
			String anzahl = getAnzahlForP(p, rech);
			if (anzahl.compareTo("n") == 0) {
				// Sonderzustand n
			} else {
				int anz = Integer.parseInt(anzahl);
				rechnungBody.add(String.format("%-21s %-8d %-5s %-6.2f EUR\n", p.getName(), anz, "x", p.getPreis()));
				Float summeAktuell = anz * p.getPreis();
				rechnungBody.add(String.format("%36s %5.2f EUR\n", "Summe", summeAktuell));
				zwischenSumme = zwischenSumme + summeAktuell;
				i++;
				if (i == einkaufsliste.size()) {
					rechnungEnd = "---------------------------------------------\n"
							+ String.format("%34s %6.2f EUR\n", "Zwischensumme: ", zwischenSumme);
				}
			}
		}
		return rechnungHead + readRechnungBody(rechnungBody) + rechnungEnd;
	}

	/**
	 * Der mittlere Teil der Rechnung wird aus einer Liste in einen String überführt.
	 * 
	 * @param Liste mit Einkäufen, Anzahl und zwischensummen
	 * @return Liste im Stringformat
	 */
	private String readRechnungBody(ArrayList<String> r) {
		String res = "";
		for (String s : r) {
			res = res + s;
		}
		return res;
	}

	/**
	 * die finale Rechnung wird zurückgebene
	 * 
	 * @return Rechnung
	 */
	public String rechnungDrucken() {
		String r = rechnungHead;
		for (String s : rechnungBody) {
			r = r + s;
		}
		r = r + rechnungEnd;
		return r;

	}

	/**
	 * Methode um die Anzahl die gekauft werden soll dem exakten Produkt zuzuordnen
	 * 
	 * @param p
	 * @param rech
	 * @return Anzahl des gesuchten Produkts
	 */
	public String getAnzahlForP(Produkt p, HashMap<Produkt, String> rech) {
		for (Map.Entry<Produkt, String> entry : rech.entrySet()) {
			Produkt key = entry.getKey();
			String value = entry.getValue();

			if (key.getEan().compareTo(p.getEan()) == 0) {
				return value;
			}
		}
		// Darf nicht vorkommen
		return "1";

	}

}