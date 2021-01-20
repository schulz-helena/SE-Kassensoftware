package kassensoftware;

public class Kategorie {

	private String kategorie;
	
	
	Kategorie(String kategorie) {
		this.kategorie = kategorie;
	}
	
	public void setKategorie(String neueKategorie) {
		this.kategorie = neueKategorie;
	}
	
	public String getKategorie() {
		return this.kategorie;
	}

}