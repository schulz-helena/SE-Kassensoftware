import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Rechnung {

	String rechnungInhalt = "Kacka";
	Float brieftasche;
	Float gesamtSumme = 0f;
	Float zwischenSumme = 0f;
	Float rueckgeld;
	ArrayList<Produkt> einkaufsliste;

	public Rechnung(Float brieftascheNeu, Einkaufsliste einkaufsliste) {
		this.brieftasche = brieftascheNeu;
		this.einkaufsliste = einkaufsliste.einkaufsliste;

	}

	public void rechnungErstellen() {

		System.out.println(String.format("%-20s %-8s %-10s", "Produkt", "Anzahl", "Preis"));
		System.out.println(String.format("%-20s %-8s %-10s", "----------", "-------", "--------"));

		int i = 0;
		for (Produkt p : this.einkaufsliste) {
			System.out.println(
					String.format("%-20s %-4d %-3s %-6.2f EUR", p.getName(), p.getAnzahl(), "x", p.getPreis()));
			Float summeAktuell = p.getAnzahl() * p.getPreis();
			System.out.println(String.format("%35s %5.2f EUR\n", "Summe", summeAktuell));
			zwischenSumme = zwischenSumme + summeAktuell;
			i++;
			if (i == einkaufsliste.size()) {
				gesamtSumme = zwischenSumme;
				rueckgeld = brieftasche - gesamtSumme;
				System.out.println("---------------------------------------------");
				System.out.println(String.format("%34s %6.2f EUR", "Gesamtsumme: ", gesamtSumme));
				System.out.println(String.format("%34s %6.2f EUR", "Erhalten: ", brieftasche));
				System.out.println(String.format("%34s %6.2f EUR", "Rückgeld: ", rueckgeld));
				
			}
		}
		
		

//		JOptionPane.showMessageDialog(null, rechnungInhalt);
	}

	public void rechnungDrucken() {

	}

}
