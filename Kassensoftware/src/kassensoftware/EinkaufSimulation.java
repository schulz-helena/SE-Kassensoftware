
public class EinkaufSimulation {

	public static void main(String[] args) {
		Artikel a = new Artikel("Bierchen");
		Einkauf.produktEinkaufen(a);

		Artikel b = new Artikel("Snickers");
		Einkauf.produktEinkaufen(b);

		Artikel c = new Artikel("Fisch");
		Einkauf.produktEinkaufen(c);
		
		Artikel d = new Artikel("Brot");
		Einkauf.produktEinkaufen(d);
		Einkaufsliste.listeAusgeben();

		System.out.println("________________________________________");
		Einkaufsliste.produktStornieren("Fisch");
		Einkaufsliste.listeAusgeben();
		
		System.out.println("________________________________________");
		Einkaufsliste.rechnungErstellen();

	}
}
