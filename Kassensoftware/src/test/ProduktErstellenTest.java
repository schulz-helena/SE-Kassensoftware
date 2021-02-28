package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import kassensoftware.ProduktPanel;


/**
 * 
 * Die Klasse <code>ProduktErstellenTest</code> testet die Funktion Produkte erstellen.
 *
 */
public class ProduktErstellenTest {
	/**
	 * Testet verschiedene Eingaben fuer die Eigenschaft Preis.
	 */
	@Test
	public void testPreis() {
		// Preis < 0,01, funktioniert nicht
		// Fall wird nicht abgefangen.
		assertFalse(ProduktPanel.pruefePreis("0"));
		
		// Gueltiger Wert: Preis = 1,15
		assertTrue(ProduktPanel.pruefePreis("1,15"));
		
		// Wert zu groß: Preis > 100.000,00
		assertFalse(ProduktPanel.pruefePreis("100000,01"));
		
		// Grenzfaelle
		assertTrue(ProduktPanel.pruefePreis("0,01"));
		assertTrue(ProduktPanel.pruefePreis("100000"));
	}
	
	
	/**
	 * Testet, ob keine Eingabe fuer den Preis erfolgt ist.
	 */
	@Test
	public void testLeererPreis() {
		assertFalse(ProduktPanel.pruefePreis(""));
	}
	
	
	/**
	 * Testet, ob der Preis ein gueltiges Format hat.
	 */
	@Test
	public void testPreisformat() {
		// Falsches Zeichen
		assertFalse(ProduktPanel.pruefePreis("10a"));
				
		// 3 Nachkommastellen, funktioniert nicht
		// Beim Preis wird nicht geprueft, wie viele Nachkommastellen angegeben wurden.
		assertFalse(ProduktPanel.pruefePreis("1.153"));
			
				
		// Komma oder Punkt akzeptieren
		assertTrue(ProduktPanel.pruefePreis("1.0"));
		assertTrue(ProduktPanel.pruefePreis("1,0"));
	}
}
