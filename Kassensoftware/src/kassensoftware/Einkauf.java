package kassensoftware;

public class Einkauf {
	private Rechnung rechnung;
	private float erhalten;
	
	// Konstruktor
	public Einkauf() {
		
	}
	
	public void setRechnung() {
		
	}
	
	public Rechnung getRechnung() {
		return this.rechnung;
	}
	
	public void einkaufStornieren() {
		
	}
	
	public void einkaufAbschließen() {
		
	}
	
	public void produktHinzufuegen(Produkt produkt) {
		
	}
	
	public void produktStornieren(Produkt produkt) {
		
	}
	
	public float rueckgeld() {
		return erhalten - rechnung.getEndsumme();
	}
}
