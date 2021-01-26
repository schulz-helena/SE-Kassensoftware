import java.util.ArrayList;

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

	public Rechnung(Float brieftascheNeu, Einkaufsliste einkaufsliste) {
		this.brieftasche = brieftascheNeu;
		this.einkaufsliste = einkaufsliste.einkaufsliste;

	}

	public void rechnungErstellen() {

		temp = (String.format("%-20s %-8s %-10s", "Produkt", "Anzahl", "Preis"));
		rechnungHead = temp + "\n" + String.format("%-20s %-8s %-10s\n", "----------", "-------", "--------");

		int i = 0;
		for (Produkt p : this.einkaufsliste) {
			rechnungBody
					.add(String.format("%-20s %-4d %-3s %-6.2f EUR\n", p.getName(), p.getAnzahl(), "x", p.getPreis()));
			Float summeAktuell = p.getAnzahl() * p.getPreis();
			rechnungBody.add(String.format("%35s %5.2f EUR\n", "Summe", summeAktuell));
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

	public void rechnungDrucken() {
		System.out.println(rechnungHead);
		for (String s : rechnungBody) {
			System.out.println(s);
		}
		System.out.println(rechnungEnd);
	}

	public Float getZwischenSumme() {
		System.out.println(this.zwischenSumme);
		return this.zwischenSumme;

	}

}