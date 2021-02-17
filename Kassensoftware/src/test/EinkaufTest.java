package Test;

import static org.junit.Assert.*;

import java.util.HashMap;
import Main.Datenverwaltung;
import Main.EinkaufPanel;
import Main.Produkt;

public class EinkaufTest {
	HashMap<Produkt, String> h = new HashMap<Produkt, String>();

	/**
	 * @Test
	 * 
	 *       Bestand Zucker-Ganglien nach Einkauf: 14 Bestand Brausepulver nach
	 *       EInkauf: 23 Bestand Tomaten-Savanna nach EInkauf: 32
	 */
	public void test() {
		h.put(Datenverwaltung.getProdukt("93780"), "2");
		h.put(Datenverwaltung.getProdukt("2913455551023"), "4");
		h.put(Datenverwaltung.getProdukt("1151216951419"), "1");

		assertTrue(EinkaufPanel.bestandAktualisieren(h));
	}

}
