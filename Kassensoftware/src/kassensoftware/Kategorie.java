
public class Kategorie {

	private String kategorieName;

	public Kategorie(String kategorieNeu) {
		this.setKategorieName(kategorieNeu);
	}

	public String getKategorieName() {
		return kategorieName;
	}

	public void setKategorieName(String kategorieName) {
		this.kategorieName = kategorieName;
	}

	@Override
	public String toString() {
	    return kategorieName;
	}

	
}
