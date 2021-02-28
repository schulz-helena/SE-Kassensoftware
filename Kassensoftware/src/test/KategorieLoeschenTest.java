package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import kassensoftware.Datenverwaltung;
import kassensoftware.Kategorie;
import kassensoftware.KategoriePanel;
import kassensoftware.Produkt;

public class KategorieLoeschenTest {

	@Before
	public void setUp() {
		// Beispielprodukte speichern
		Datenverwaltung.produktSpeichern(
				new Produkt("Brausepulver Zuckerfrei", "2913455551023", 3.99f, 75.0f, "27", new Kategorie("S��waren")));
		Datenverwaltung
				.produktSpeichern(new Produkt("Apfel Goldy", "4532", 2.50f, 100.0f, "68", new Kategorie("Obst")));
		Datenverwaltung
				.produktSpeichern(new Produkt("Tomate Savanna", "93780", 4.99f, 100.0f, "32", new Kategorie("Gem�se")));
		Datenverwaltung.produktSpeichern(
				new Produkt("Basilikum gerebelt", "89348842", 2.70f, 50.0f, "8", new Kategorie("Gew�rze")));
		Datenverwaltung.produktSpeichern(
				new Produkt("Erdbeer-Konfit�re", "8453267832680", 4.27f, 250.0f, "14", new Kategorie("Aufstrich")));
		Datenverwaltung.produktSpeichern(
				new Produkt("Mehl 405", "8597618758423", 0.99f, 1000.0f, "53", new Kategorie("Backwaren")));
		Datenverwaltung.produktSpeichern(
				new Produkt("Zitronensaft tr�b", "4686835148936", 2.49f, 250.0f, "21", new Kategorie("Getr�nke")));
		Datenverwaltung.produktSpeichern(
				new Produkt("Apfelsaft klar", "32135947", 1.65f, 1500.0f, "41", new Kategorie("Getr�nke")));
		Datenverwaltung.produktSpeichern(
				new Produkt("Zucker-Ganglien", "1151216951419", 45.32f, 1f, "15", new Kategorie("S��waren")));
		Datenverwaltung.produktSpeichern(
				new Produkt("Gr�ne Bohnen Eintopf", "12744532", 1.59f, 400.0f, "12", new Kategorie("Konserven")));

		// Zugeh�rige Kategorien speichern
		Datenverwaltung.kategorieSpeichern(new Kategorie("S��waren"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Obst"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Gem�se"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Aufstrich"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Backwaren"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Getr�nke"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Konserven"));
	}

	/**
	 * Testet, ob eine Kategorie, die einem Projekt zugeordnet ist, gelöscht werden kann.
	 */
	@Test
	public void test() {
		assertFalse(KategoriePanel.kategorieLoeschen("S��waren"));
	}

}
