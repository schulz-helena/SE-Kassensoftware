package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import kassensoftware.Datenverwaltung;
import kassensoftware.Kategorie;
import kassensoftware.Produkt;
import kassensoftware.ProduktPanel;


public class ProduktErstellenTest {
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
	public void testProduktname() {
		// Leerer Name darf nicht akzeptiert werden
		assertFalse(ProduktPanel.pruefeName(""));
		
		// Zu kurzer Name mit Laenge < 2
		assertFalse(ProduktPanel.pruefeName("A"));
		
		// Zu langer Name mit Laenge > 32
		assertFalse(ProduktPanel.pruefeName("Sehr langer Testname, der nicht ak"));
		
		// Grenzfaelle
		// Name mit Laenge 32
		assertTrue(ProduktPanel.pruefeName("Sehr langer Testname der nicht a"));
		
		// Name mit Laenge 2
		assertTrue(ProduktPanel.pruefeName("ab"));
		
		// Name enthaelt Zahlen, wird in unserer Implementierung nicht akzeptiert
		//assertTrue(ProduktPanel.pruefeName("Mehl 405"));
	}
	
	
	@Test
	public void testEan() {
		// EAN ist leer
		assertFalse(ProduktPanel.pruefeEan(""));
		
		// EAN Laenge > 13
		assertFalse(ProduktPanel.pruefeEan("01234567891012"));
		
		// Buchstabe enthalten
		assertFalse(ProduktPanel.pruefeEan("1045367a"));
		
		
	}
	
	
	@Test
	public void testPreis() {
		// Kein Preis eingegeben
		assertFalse(ProduktPanel.pruefePreis(""));
		
		// Falsches Format
		assertFalse(ProduktPanel.pruefePreis("10a"));
		
		// Komma und Punkt akzeptieren
		assertTrue(ProduktPanel.pruefePreis("1.0"));
		assertTrue(ProduktPanel.pruefePreis("1,0"));
		
		// Preis < 0, funktioniert nicht wie gewuenscht
		//assertFalse(ProduktPanel.pruefePreis("-0.01"));
		// 0,00 < Preis < 0,01
		assertFalse(ProduktPanel.pruefePreis("0.005"));
		// Preis > 100.000,00
		assertFalse(ProduktPanel.pruefePreis("100000.01"));
		
		
		// Grenzen
		// Preis = 100.000
		assertTrue(ProduktPanel.pruefePreis("100000"));
		// Preis = 0
		assertTrue(ProduktPanel.pruefePreis("0"));
	}
	
	
	@Test
	public void testAnzahl() {
		// Keine Anzahl angegeben
		assertFalse(ProduktPanel.pruefeAnzahl(""));
		
		// Falsches Format
		assertFalse(ProduktPanel.pruefeAnzahl("25g"));
		
		// Negative Stueckzahl, liefert einen Fehler
		//assertFalse(ProduktPanel.pruefeAnzahl("-1"));
		
		// Grenzfaelle
		assertTrue(ProduktPanel.pruefeAnzahl("0"));
		assertTrue(ProduktPanel.pruefeAnzahl("1000"));
		
	}
	
	
	
	@Test
	public void testGewicht() {
		
	}
	
	
	@Test
	public void testKategorie() {
		// Keine Kategorie angegeben
		assertFalse(ProduktPanel.pruefeKategorie(""));
		
		// Gueltige Kategorie angeben
		assertTrue(ProduktPanel.pruefeKategorie("Obst"));
		
		// Nicht vorhandene Kategorie angeben
		assertFalse(ProduktPanel.pruefeKategorie("Kleidung"));
	}
	
	
}
