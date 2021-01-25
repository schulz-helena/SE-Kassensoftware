

public class Einkauf {


	public static void main(String[] args) {
		
		Kategorie gemuese = new Kategorie("Gemuese");
		Kategorie obst = new Kategorie("Obst");
		
		Produkt a = new Produkt("Gurke", "286643728", 0.99f, 155f, 2, gemuese);
		Produkt b = new Produkt("Tomate", "286643728", 0.70f, 20f, 6, gemuese);
		Produkt c = new Produkt("Banane", "286643728", 1.23f, 350f, 9, obst);
		Produkt d = new Produkt("Apfel", "286643728", 0.34f, 200f, 10, obst);
		
		Einkaufsliste einkaufsliste = new Einkaufsliste();		
		einkaufsliste.produktHinzufuegen(a);
		einkaufsliste.produktHinzufuegen(b);
		einkaufsliste.produktHinzufuegen(c);
		einkaufsliste.produktHinzufuegen(d);
		
		Rechnung rechnung = new Rechnung(500f, einkaufsliste);
		rechnung.rechnungErstellen();
		
		

	}
}
