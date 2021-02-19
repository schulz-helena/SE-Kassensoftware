package Test;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import Main.Datenverwaltung;
import Main.EinkaufPanel;
import Main.Einkaufsliste;
import Main.Kategorie;
import Main.Produkt;
import Main.Rechnung;

public class EinkaufTest {
	HashMap<Produkt, String> h = new HashMap<Produkt, String>();
	Einkaufsliste testList = new Einkaufsliste();

	@Before
	public void setUp() {
		// Zugehörige Kategorien speichern
		Datenverwaltung.kategorieSpeichern(new Kategorie("Süßwaren"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Obst"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Gemüse"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Aufstrich"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Backwaren"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Getränke"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Konserven"));

		// Beispielprodukte speichern
		Datenverwaltung.produktSpeichern(
				new Produkt("Brausepulver Zuckerfrei", "2913455551023", 3.99f, 75.0f, "27", new Kategorie("Süßwaren")));
		Datenverwaltung
				.produktSpeichern(new Produkt("Apfel Goldy", "4532", 2.50f, 100.0f, "68", new Kategorie("Obst")));
		Datenverwaltung
				.produktSpeichern(new Produkt("Tomate Savanna", "93780", 4.99f, 100.0f, "32", new Kategorie("Gemüse")));
		Datenverwaltung.produktSpeichern(
				new Produkt("Basilikum gerebelt", "89348842", 2.70f, 50.0f, "8", new Kategorie("Gewürze")));
		Datenverwaltung.produktSpeichern(
				new Produkt("Erdbeer-Konfitüre", "8453267832680", 4.27f, 250.0f, "14", new Kategorie("Aufstrich")));
		Datenverwaltung.produktSpeichern(
				new Produkt("Mehl 405", "8597618758423", 0.99f, 1000.0f, "53", new Kategorie("Backwaren")));
		Datenverwaltung.produktSpeichern(
				new Produkt("Zitronensaft trüb", "4686835148936", 2.49f, 250.0f, "21", new Kategorie("Getränke")));
		Datenverwaltung.produktSpeichern(
				new Produkt("Apfelsaft klar", "32135947", 1.65f, 1500.0f, "41", new Kategorie("Getränke")));
		Datenverwaltung.produktSpeichern(
				new Produkt("Zucker-Ganglien", "1151216951419", 45.32f, 1f, "15", new Kategorie("Süßwaren")));
		Datenverwaltung.produktSpeichern(
				new Produkt("Grüne Bohnen Eintopf", "12744532", 1.59f, 400.0f, "12", new Kategorie("Konserven")));

		h.put(Datenverwaltung.getProdukt("93780"), "2");
		h.put(Datenverwaltung.getProdukt("2913455551023"), "4");
		h.put(Datenverwaltung.getProdukt("1151216951419"), "1");

		testList.produktHinzufuegen(Datenverwaltung.getProdukt("93780"));
		testList.produktHinzufuegen(Datenverwaltung.getProdukt("2913455551023"));
		testList.produktHinzufuegen(Datenverwaltung.getProdukt("1151216951419"));

	}

	@Test
	public void bestandTest() {

		int temp1 = Integer.parseInt(Datenverwaltung.getProdukt("93780").getAnzahl());
		int temp2 = Integer.parseInt(Datenverwaltung.getProdukt("2913455551023").getAnzahl());
		int temp3 = Integer.parseInt(Datenverwaltung.getProdukt("1151216951419").getAnzahl());

		// Konnte der Bestand aktualisiert werden?
		assertTrue(EinkaufPanel.bestandAktualisieren(h));

		// Stimmt der Bestand mit dem erwarteten Bestand überein?
		assertEquals(temp1 - 2, Integer.parseInt(Datenverwaltung.getProdukt("93780").getAnzahl()));
		assertEquals(temp2 - 4, Integer.parseInt(Datenverwaltung.getProdukt("2913455551023").getAnzahl()));
		assertEquals(temp3 - 1, Integer.parseInt(Datenverwaltung.getProdukt("1151216951419").getAnzahl()));

	}

	@Test
	public void endpreisTest() {
		Float f1 = Datenverwaltung.getProdukt("93780").getPreis();
		Float f2 = Datenverwaltung.getProdukt("2913455551023").getPreis();
		Float f3 = Datenverwaltung.getProdukt("1151216951419").getPreis();

		Rechnung test = new Rechnung(100000f, testList);
		test.rechnungErstellen(h);

		// Entspricht die Summe der Preise * Anzahl der erwarteten Endsumme?
		assertEquals(f1 * 2 + f2 * 4 + f3 * 1, Rechnung.gesamtSumme, 0);
	}

	@Test
	public void stornierenTest() {

		// Wird der Einkauf storniert?
		assertTrue(EinkaufPanel.einkaufStorno(testList));
	}

}
