package Test;

import static org.junit.Assert.*;
import Main.KategoriePanel;

public class KategorieLoeschenTest {

	/**
	 * @Test
	 * 
	 *       Methode versucht verwendete Kategorie zu löschen. Rückgabewert
	 *       <code>false</code> , wenn Kategorie noch einem Produkt zugeordnet ist,
	 *       sonst <code>true</code>
	 * 
	 * 
	 */
	public void test() {

		assertFalse(KategoriePanel.kategorieLoeschen("Süßwaren"));

	}

}
