import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.sun.jdi.Field;

public class Rechnung {

	public static void rechnungErstellen(ArrayList<Artikel> einkaufsliste) {
		
		System.out.println("\n\n\n         Rechnung \n-------------------------------- \n\n");
		System.out.println(String.format("%-9s %2d x %5.2f EUR", "Käse", 3, 1.59));
		System.out.println(String.format("%30.2f EUR", 3 * 1.59));

		System.out.println(String.format("%-9s %2d x %5.2f EUR", "Brot", 1, 4.99));
		System.out.println(String.format("%30.2f EUR", 1 * 4.99));

		System.out.println(String.format("%-9s %2d x %5.2f EUR", "DVD", 10, 9.99));
		System.out.println(String.format("%30.2f EUR", 10 * 9.99));

		System.out.println("__________________________________");

		System.out.println(String.format("%-9s %20.2f EUR", "Gesamt", 3*1.59+1*4.99+10*9.99));
		System.out.println(String.format("%-9s %20.2f EUR", "Gegeben", 200.00));
		System.out.println();
		System.out.println(String.format("%-9s %20.2f EUR", "Zurück", 200 - (3*1.59+1*4.99+10*9.99)));


		}

}
