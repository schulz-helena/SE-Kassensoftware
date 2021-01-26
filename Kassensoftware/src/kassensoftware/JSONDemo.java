package kassensoftware;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Die Klasse JSONDemo beinhaltet Methoden zur Speicherverwaltung der
 * Produkt- und Kategorie-Objekte. Die Datenbank wird in einer JSON-Datei gespeichert.
 * Die Klasse kann nicht instanziiert werden.
 * 
 */

public final class JSONDemo {
	
	/**
	 * Die Daten werden standardmäßig in der Datei <code>dataFile</code> mit dem Namen data.json im Verzeichnis daten gespeichert.
	 */
	private static String dataFilename = "data.json";
	private static String dataDirectory = "daten";
	private static String dataPath = dataDirectory + "/" + dataFilename;
	private static String dataContent = "{\n\t\"products\": [],\n\t\"categories\": []\n}";
	private static File dataFile = new File(dataPath);
	
	
	/**
	 * Speichert ein nicht leeres Produkt in <code>dataFile</code> ab.
	 * Wenn bereits ein Produkt mit der gleichen EAN existiert, wird es überschrieben.
	 * Falls die Datenbank noch nicht existiert, wird sie erzeugt.
	 * 
	 * @param 	produkt das Produkt, das gespeichert werden soll
	 * @return 	<code>true</code>, falls das Produkt erfolgreich gespeichert wurde;
	 * 			sonst <code>false</code> 
	 */	
	public static boolean produktSpeichern(Produkt produkt) {
		if (!dataFile.exists()) {
			createDataFile();
		}
		
		// Keine leeren Produkte speichern
		if (produkt == null) {
			return false;
		}
		
	    Map<String, String> map = new HashMap<>();
	    
	    // Produkteigenschaften eintragen
	    map.put("EAN", produkt.getEan());
	    map.put("Name", produkt.getName());
	    map.put("Preis", produkt.getPreis().toString());
	    map.put("Gewicht", produkt.getGewicht().toString());
	    map.put("Grundpreis", produkt.getGrundpreis().toString());
	    map.put("Anzahl", produkt.getAnzahl().toString());
	    map.put("Kategorie", produkt.getKategorie().getKategorieName());
	    
	    // JSONObject erzeugen
	    JSONObject neuesProdukt = new JSONObject(map);

	    // JSONObject speichern
	    JSONArray list = readData("products");
	    boolean modified = false;
	    int index = 0;
	    
	    Iterator<JSONObject> iter = list.iterator();
    	
    	while (iter.hasNext()) {
    		JSONObject item = iter.next();
    		
    		// Ist das Produkt bereits eingetragen?
    		if (neuesProdukt.get("EAN").toString().compareTo(item.get("EAN").toString()) == 0) {
    			list.set(index, neuesProdukt);
            	modified = true;
    		}
    		
    		index++;
    	}
    	
    	// Falls das Produkt noch nicht in der Datenbank ist
	    if (!modified) {
    		list.add(neuesProdukt);
    		modified = true;
	    }
	    
	    if (writeData("products", list) && modified) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	
	/**
	 * Speichert eine nicht leeres Kategorie in <code>dataFile</code> ab.
	 * Falls die Datei noch nicht existiert, wird sie erzeugt.
	 * 
	 * @param 	kategorie	Kategorie, die gespeichert werden soll
	 * @return 	<code>true</code>, falls die Kategorie erfolgreich gespeichert wurde;
	 * 			sonst <code>false</code> 
	 */	
	public static boolean kategorieSpeichern(Kategorie kategorie) {
		if (!dataFile.exists()) {
			createDataFile();
		}
		
		// Keine leeren Kategorien speichern
		if (kategorie == null) {
			return false;
		}
		
		Map<String, String> map = new HashMap<>();
		
		// Eigenschaften übernehmen
		map.put("Name", kategorie.getKategorieName());
		
		// neues JSONObject erzeugen
		JSONObject neueKategorie = new JSONObject(map);
		
	    // JSONObject speichern
	    JSONArray list = readData("categories");
	    
	    if (!list.contains(neueKategorie)) {
    		list.add(neueKategorie);
	    }
	    
	    if (writeData("categories", list)) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
	
	
	/**
	 * Sucht in <code>dataFile</code> nach einem Eintrag mit der EAN <code>ean</code>
	 * und gibt bei Erfolg das zugehörige Produkt zurück.
	 * 
	 * @param	ean	EAN des gesuchten Produktes
	 * @return	das <code>Produkt</code> zur zugehörigen <code>ean</code>;
	 * 			<code>null</code>, falls kein Produkt mit dieser EAN gefunden wurde
	 */
	public static Produkt getProdukt(String ean) {
		JSONArray list = readData("products");
		
	    try {	    	
	    	// Suche nach passenden Eintrag
	    	Iterator<JSONObject> iter = list.iterator();
	    	
	    	while (iter.hasNext()) {
	    		JSONObject item = iter.next();
	    		
	    		// Falls das Produkt mit der gleichen EAN gefunden wurde
	    		if (ean.compareTo(item.get("EAN").toString()) == 0) {
	    			String name = item.get("Name").toString();
	    			Float preis = Float.parseFloat(item.get("Preis").toString());
	    			Float gewicht = Float.parseFloat(item.get("Gewicht").toString());
	    			Integer anzahl = Integer.parseInt(item.get("Anzahl").toString());
	    			Kategorie kategorie = new Kategorie(item.get("Kategorie").toString());
	    			return new Produkt(name, ean, preis, gewicht, anzahl, kategorie);
	    		}
	    	}
	    }
	    catch (NumberFormatException e) {
	    	System.out.println(e.getMessage());
	    }
		
		return null;
	}
	
	
	/**
	 * Erstellt eine Liste mit allen Produkten aus <code>dataFile</code> und gibt diese zurück.
	 * Sind keine Produkte gespeichert, wird eine leere Produktliste zurückgegeben.
	 * 
	 * @return	Produktliste mit den Produkten aus der Datenbank
	 */
	public static ArrayList<Produkt> getAllProducts() {
		ArrayList<Produkt> produktListe = new ArrayList<>();
		JSONArray list = readData("products");
		
	    try {
	    	// Füge nacheinander alle Produkte aus der Datenbank hinzu
	    	Iterator<JSONObject> iter = list.iterator();
	    	
	    	while (iter.hasNext()) {
	    		JSONObject item = iter.next();
	    		
	    		String ean = item.get("EAN").toString();
	    		String name = item.get("Name").toString();
	    		Float preis = Float.parseFloat(item.get("Preis").toString());
	    		Float gewicht = Float.parseFloat(item.get("Gewicht").toString());
	    		Integer anzahl = Integer.parseInt(item.get("Anzahl").toString());
	    		Kategorie kategorie = new Kategorie(item.get("Kategorie").toString());
	    		
	    		produktListe.add(new Produkt(name, ean, preis, gewicht, anzahl, kategorie));
	    	}
	    }
	    catch (NumberFormatException e) {
	    	System.out.println(e.getMessage());
	    }
		
		return produktListe;
	}
	
	
	/**
	 * Sucht in <code>dataFile</code> nach der Kategorie mit dem Namen <code>name</code>
	 * und gibt bei Erfolg eine Kategorie zurück.
	 * 
	 * @param	name	Name der gesuchten Kategorie
	 * @return	die Kategorie mit dem Name <code>name</code>;
	 * 			<code>null</code>, falls keine Kategorie mit dem Namen gefunden wurde
	 */
	public static Kategorie getKategorie(String name) {
		JSONArray list = readData("categories");
	    	
	    Iterator<JSONObject> iter = list.iterator();
	    	
	    while (iter.hasNext()) {
	    	JSONObject item = iter.next();
	    		
	    	// Falls die Kategorie mit dem Namen gefunden wurde
	    	if (name.compareTo(item.get("Name").toString()) == 0) {
	    		return new Kategorie(item.get("Name").toString());
	    	}
	    }
	    
	    return null;
	}
	
	
	/**
	 * Erstellt eine Liste mit allen Kategorien aus <code>dataFile</code> und gibt diese zurück.
	 * Sind keine Kategorien gespeichert, wird eine leere Kategorienliste zurückgegeben.
	 * 
	 * @return	Kategorienliste mit den Kategorien aus der Datenbank
	 */
	public static ArrayList<Kategorie> getAllCategories() {
		ArrayList<Kategorie> kategorieListe = new ArrayList<>();
		JSONArray list = readData("categories");
		
	    // Iteriere und füge nacheinander alle Kategorien aus der Datenbank hinzu
	    Iterator<JSONObject> iter = list.iterator();
	    	
	    while (iter.hasNext()) {
	    	kategorieListe.add(new Kategorie(iter.next().get("Name").toString()));
	    }
		
		return kategorieListe;
	}
	
	
	/**
	 * Entfernt ein Produkt aus der Datenbank, falls vorhanden.
	 * 
	 * @param produkt	Produkt, das aus der Datenbank entfernt werden soll
	 * @return			<code>true</code>, falls das Produkt erfolgreich entfernt wurde;
	 * 					<code>false</code>, falls das Produkt nicht entfernt wurde
	 */
	public static boolean produktEntfernen(Produkt produkt) {
		JSONArray list = readData("products");
    	String ean = produkt.getEan();
    	
    	int index = 0;
    	boolean modified = false;
    	
    	Iterator<JSONObject> iter = list.iterator();
    	
    	while (iter.hasNext() && !modified) {
    		JSONObject item = iter.next();
    		
    		if (ean.compareTo(item.get("EAN").toString()) == 0) {
    			list.remove(index);
    			modified = true;
    		}
    		
    		index++;
    	}
    	
    	if (modified && writeData("products", list)) {
		    return true;
    	}
		
		return false;
	}
	
	
	/**
	 * Entfernt eine Kategorie aus der Datenbank, falls vorhanden.
	 * 
	 * @param kategorie	Kategorie, die entfernt werden soll
	 * @return			<code>true</code>, falls die Kategorie erfolgreich entfernt wurde;
	 * 					<code>false</code>, falls die Kategorie nicht entfernt wurde
	 */
	public static boolean kategorieEntfernen(Kategorie kategorie) {
		JSONArray list = readData("categories");
		String name = kategorie.getKategorieName();
		
		boolean modified = false;
    	int index = 0;
	    	
	    	
	    Iterator<JSONObject> iter = list.iterator();
	    	
	    while (iter.hasNext() && !modified) {
	    	JSONObject item = iter.next();
	    		
	    	if (name.compareTo(item.get("Name").toString()) == 0) {
	    		list.remove(index);
	    		modified = true;
	    	}
	    		
	    	index++;
	    }
	    	
	    if (modified && !writeData("categories", list)) {
	    	return true;
	    }
		
		return false;
	}
	
	
	/**
	 * Die Datei <code>dataFile</code> wird auf den Startzustand zurückgesetzt oder neu erzeugt, falls sie vorher noch nicht vorhanden war.
	 * Die Datei wird im Verzeichnis daten abgelegt. Zu Beginn befindet sich in der Datei ein JSONObject mit einer leeren Produkt- und Kategorieliste.
	 * Falls das Verzeichnis nicht existiert, wird es ebenfalls erzeugt.
	 */
	
	public static void createDataFile() {
		File directory = new File(dataDirectory);
		
		// Falls das Verzeichnis nicht existiert, versuche es zu erzeugen
		try {
			if (!directory.exists()) {
				directory.mkdir();
		    }
		}
		catch (Exception e) {
			System.out.println("Das Verzeichnis " + dataDirectory + " konnte nicht erzeugt werden.");
		}
	    
		// Versuche die Datei zu erstellen
	    try {
	    	FileWriter writer = new FileWriter(dataPath);
	    	writer.write(dataContent);
		    writer.close();
	    }
	    catch (IOException e) {
	    	System.out.println("Datei " + dataFilename + " konnte nicht erzeugt werden.");
	    }
	}
	
	
	/**
	 * Liest aus <code>dataFile</code> ein <code>JSONArray</code> zum zugehörigen 
	 * <code>key</code> und gibt es zurück.
	 * 
	 * @param key	ist der Schlüssel zu dem die Daten gelesen werden
	 * @return		gibt den ausgelesenen Datensatz zurück;
	 * 				<code>null</code>, falls kein Eintrag vorhanden war
	 */
	private static JSONArray readData(String key) {
		try {
			JSONParser parser = new JSONParser();
	    	JSONObject obj = (JSONObject) parser.parse(new FileReader(dataPath));
	    	return (JSONArray) obj.get(key);
		}
		catch (ParseException e) {
	    	System.out.println(e.getMessage());
	    }
	    catch (IOException e) {
	    	System.out.println(e.getMessage());
	    }
		
		return null;
	}
	
	
	/**
	 * Schreibt Daten in die Datenbank zum zugehörigen <code>key</code>.
	 * 
	 * @param key	Schlüssel für die Daten
	 * @param list	Daten, die zum <code>key</code> geschrieben werden sollen
	 * @return		<code>true</code>, falls die Daten erfolgreich in die Datenbank
	 * 				geschrieben wurden;
	 * 				<code>false</code>, falls die Daten nicht geschrieben werden konnten
	 */
	private static boolean writeData(String key, JSONArray list) {
		try {
			JSONParser parser = new JSONParser();
	    	JSONObject obj = (JSONObject) parser.parse(new FileReader(dataPath));
	    	
	    	obj.put(key, list);
		    BufferedWriter writer = new BufferedWriter(new FileWriter(dataPath));
		    obj.writeJSONString(writer);
		    writer.close();
		    return true;
		}
		catch (ParseException e) {
	    	System.out.println(e.getMessage());
	    }
	    catch (IOException e) {
	    	System.out.println(e.getMessage());
	    }
		
		return false;
	}
}
