package test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import kassensoftware.Datenverwaltung;
import kassensoftware.Produkt;
import kassensoftware.Kategorie;
import java.util.ArrayList;


/**
 * Die Klasse <code>SucheTest</code> testet die Produktsuche mithilfe der EAN und dem Namen.
 *
 */
public class SucheTest {
	@Before
	public void setUp() {
		// Beispielprodukte speichern
		Datenverwaltung.produktSpeichern(new Produkt("Brausepulver Zuckerfrei", "2913455551023", 3.99f, 75.0f, "27", new Kategorie("Süßwaren")));
		Datenverwaltung.produktSpeichern(new Produkt("Apfel Goldy", "4532", 2.50f, 100.0f, "68", new Kategorie("Obst")));
		Datenverwaltung.produktSpeichern(new Produkt("Tomate Savanna", "93780", 4.99f, 100.0f, "32", new Kategorie("Gemüse")));
		Datenverwaltung.produktSpeichern(new Produkt("Basilikum gerebelt", "89348842", 2.70f, 50.0f, "8", new Kategorie("Gewürze")));
		Datenverwaltung.produktSpeichern(new Produkt("Erdbeer-Konfitüre", "8453267832680", 4.27f, 250.0f, "14", new Kategorie("Aufstrich")));
		Datenverwaltung.produktSpeichern(new Produkt("Mehl 405", "8597618758423", 0.99f, 1000.0f, "53", new Kategorie("Backwaren")));
		Datenverwaltung.produktSpeichern(new Produkt("Zitronensaft trüb", "4686835148936", 2.49f, 250.0f, "21", new Kategorie("Getränke")));
		Datenverwaltung.produktSpeichern(new Produkt("Apfelsaft klar", "32135947", 1.65f, 1500.0f, "41", new Kategorie("Getränke")));
		Datenverwaltung.produktSpeichern(new Produkt("Zucker-Ganglien", "1151216951419", 45.32f, 1f, "15", new Kategorie("Süßwaren")));
		Datenverwaltung.produktSpeichern(new Produkt("Grüne Bohnen Eintopf", "12744532", 1.59f, 400.0f, "12", new Kategorie("Konserven")));
		
		// Zugehörige Kategorien speichern
		Datenverwaltung.kategorieSpeichern(new Kategorie("Süßwaren"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Obst"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Gemüse"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Aufstrich"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Backwaren"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Getränke"));
		Datenverwaltung.kategorieSpeichern(new Kategorie("Konserven"));
	}
	
	
	@Test
	public void testSucheNachName() {
		String name = "Zucker";
		
		ArrayList<Produkt> suchergebnisse = Datenverwaltung.produktSuchen(name);
		assertTrue(suchergebnisse.size() == 2);
		
		for (Produkt eintrag : suchergebnisse) {
			assertTrue(eintrag.getName().contains(name));
		}
	}
	
	
	@Test
	public void testSucheNachEAN() {
		String ean = "4532";
		
		ArrayList<Produkt> suchergebnisse = Datenverwaltung.produktSuchen(ean);
		assertTrue(suchergebnisse.size() == 1);
		
		for (Produkt eintrag : suchergebnisse) {
			assertTrue(eintrag.getEan().subSequence(0, 4).toString().equals(ean));
		}
	}
}
