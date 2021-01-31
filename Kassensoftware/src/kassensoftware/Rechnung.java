import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Methode zur Erstellung des Typs <code>Rechnung</code>, sowie des Kassenbongs.
 * 
 * @author Nils Kohler
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
	 * Der Konstruktor initialisiert die <code>einkaufsliste</code> und die
	 * <code>brieftasche</code> mit den Werten aus der GUI.
	 * 
	 * @param brieftascheNeu Geld des Kunden
	 * @param einkaufsliste  Liste der Produkte die einkgekauft werden
	 */
	public Rechnung(Float brieftascheNeu, Einkaufsliste einkaufsliste) {
		this.brieftasche = brieftascheNeu;
		this.einkaufsliste = einkaufsliste.einkaufsliste;

	}

	/**
	 * Das gegebene Geld des Kunden wird hier im späteren Programmablauf
	 * aktualisiert.
	 * 
	 * @param geldNeu wird aus der GUI übergeben
	 */
	public void setBrieftasche(Float geldNeu) {
		this.brieftasche = geldNeu;
	}

	/**
	 * Die Abschlussrechnung wird erstellt. Unterschied zur Zwischenrechnung ist der
	 * Bezahlvorgang des Kunden. Die <code>einkaufliste</code> wird anschließend
	 * wieder zurückgesetzt und ein neuer Einkauf kann gestartet werden.
	 * 
	 * @param rech HashMap mit <code>produkt p</code> das gekauft werden soll und
	 *             der dazugehörigen <code>anzahl</code>
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

			if (p.getAnzahl().compareTo("n") == 0) {
				Float anz = (Float.parseFloat(anzahl) / 1000);
				rechnungBody
						.add(String.format("%-21s %-8.2f %-5s %-6.2f EUR\n", p.getName(), anz, "x", p.getGrundpreis()));
				Float summeAktuell = anz * p.getGrundpreis();
				rechnungBody.add(String.format("%36s %5.2f EUR\n", "Summe", summeAktuell));
				zwischenSumme = zwischenSumme + summeAktuell;
				i++;
			} else {
				int anz = Integer.parseInt(anzahl);
				rechnungBody.add(String.format("%-21s %-8d %-5s %-6.2f EUR\n", p.getName(), anz, "x", p.getPreis()));
				Float summeAktuell = anz * p.getPreis();
				rechnungBody.add(String.format("%36s %5.2f EUR\n", "Summe", summeAktuell));
				zwischenSumme = zwischenSumme + summeAktuell;
				i++;
			}
		}
		if (i == einkaufsliste.size()) {
			gesamtSumme = zwischenSumme;
			rueckgeld = brieftasche - gesamtSumme;
			rechnungEnd = "---------------------------------------------\n"
					+ String.format("%34s %6.2f EUR\n", "Gesamtsumme: ", gesamtSumme)
					+ String.format("%34s %6.2f EUR\n", "Erhalten: ", brieftasche)
					+ String.format("%34s %6.2f EUR\n", "Rückgeld: ", rueckgeld);

		}

	}

	/**
	 * Die Rechnung wird aktualisiert werden, immer wenn ein neues
	 * <code>produkt</code> der <code>einkaufsliste</code> hinzugefügt wurde. Die
	 * <code>zwischensumme</code> wird auch ausgegeben.
	 * 
	 * @param rech <code>HashMap</code> mit <code>produkt p</code> die gekauft
	 *             werden sollen und der dazugehörigen <code>anzahl</code>
	 * @return String: Zwischenrechnung wird als String zurückgegeben
	 */
	public String rechnungAktualisieren(HashMap<Produkt, String> rech) {
		temp = (String.format("%-23s %-12s %-15s", "Produkt", "Anzahl", "Preis"));
		rechnungHead = temp + "\n" + String.format("%-25s %-15s %-15s\n", "-------------", "----------", "-----------");

		int i = 0;
		for (Produkt p : this.einkaufsliste) {
			String anzahl = getAnzahlForP(p, rech);
			if (p.getAnzahl().compareTo("n") == 0) {
				Float anz = (Float.parseFloat(anzahl) / 1000);
				rechnungBody
						.add(String.format("%-21s %-8.2f %-5s %-6.2f EUR\n", p.getName(), anz, "x", p.getGrundpreis()));
				Float summeAktuell = anz * p.getGrundpreis();
				rechnungBody.add(String.format("%36s %5.2f EUR\n", "Summe", summeAktuell));
				zwischenSumme = zwischenSumme + summeAktuell;
				i++;
				if (i == einkaufsliste.size()) {
					rechnungEnd = "---------------------------------------------\n"
							+ String.format("%34s %6.2f EUR\n", "Zwischensumme: ", zwischenSumme);
				}
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

		if (rech.isEmpty()) {
			return (rechnungHead + "Keine Produkte auf der Einkaufsliste.");
		}
		return rechnungHead + readRechnungBody(rechnungBody) + rechnungEnd;
	}

	/**
	 * Der <code>rechnungBody</code> der Rechnung wird aus einer
	 * <code>ArrayList</code> in einen String überführt.
	 * 
	 * @param r <code>ArrayList</code> mit <code>produkten</code>,
	 *          <code>anzahl</code> und <code>zwischensumme</code>
	 * @return res als String
	 */
	private String readRechnungBody(ArrayList<String> r) {
		String res = "";
		for (String s : r) {
			res = res + s;
		}
		return res;
	}

	/**
	 * Methode umd den finalen Kassenbong zu erstellen. <code>rechnungHead</code>,
	 * <code>rechnungBody</code> und <code>rechnungEnd</code> wird zusammengefügt.
	 * 
	 * @return r Gesamtrechnung
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
	 * Methode um die <code>anzahl</code> die gekauft werden soll dem exakten
	 * <code>produkt</code> zuzuordnen.
	 * 
	 * @param p    <code>Produkt</code>
	 * @param rech <code>HashMap</code> mit <code>produkt p</code> die gekauft
	 *             werden sollen und der dazugehörigen <code>anzahl</code>
	 * @return value <code>anzahl</code> die von <code>Produkt p</code> gekauft
	 *         werden soll
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