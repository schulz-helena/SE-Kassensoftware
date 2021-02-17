package Test;

import static org.junit.Assert.*;
import Main.KategoriePanel;

public class KategorieLoeschenTest {

	/**
	 * @Test
	 * 
	 *       Methode versucht verwendete Kategorie zu l�schen. R�ckgabewert
	 *       <code>false</code> , wenn Kategorie noch einem Produkt zugeordnet ist,
	 *       sonst <code>true</code>
	 * 
	 * 
	 */
	public void test() {

		assertFalse(KategoriePanel.kategorieLoeschen("S��waren"));

	}

}
