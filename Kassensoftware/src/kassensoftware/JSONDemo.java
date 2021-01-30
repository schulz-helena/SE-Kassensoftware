package kassensoftware;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;

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
	 * Die Datenbank wird in der Datei <code>dataFile</code> mit dem Namen data.json im Verzeichnis daten gespeichert.
	 */
	private static final String dataFilename = "data.json";
	private static final String dataDirectory = "daten";
	private static final String dataPath = dataDirectory + "/" + dataFilename;
	private static String dataContent = "{\n\t\"products\": [],\n\t\"categories\": []\n}";
	private static final File dataFile = new File(dataPath);
	
	
	/**
	 * Speichert ein nicht leeres Produkt in der Datenbank ab.
	 * Wenn bereits ein Produkt mit der gleichen EAN existiert, wird es überschrieben.
	 * 
	 * @param 	produkt das Produkt, das gespeichert werden soll
	 * @return 	<code>true</code>, falls das Produkt erfolgreich gespeichert wurde;
	 * 			sonst <code>false</code>
	 */	
	public static boolean produktSpeichern(Produkt produkt) {
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
	    map.put("Anzahl", produkt.getAnzahl());
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
    	
    	// Falls Änderungen vorgenommen wurden
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
	 * Speichert eine nicht-leere Kategorie in der Datenbank.
	 * 
	 * @param 	kategorie	die Kategorie, die gespeichert werden soll
	 * @return 	<code>true</code>, falls die Kategorie erfolgreich gespeichert wurde;
	 * 			sonst <code>false</code> 
	 */	
	public static boolean kategorieSpeichern(Kategorie kategorie) {
		// Keine leeren Kategorien speichern
		if (kategorie == null) {
			return false;
		}
		
		Map<String, String> map = new HashMap<>();
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
	 * Sucht nach einem Produkt mit der EAN <code>ean</code>
	 * und gibt bei Erfolg das zugehörige Produkt zurück.
	 * 
	 * @param	ean	EAN des gesuchten Produktes
	 * @return	<code>Produkt</code> zur zugehörigen <code>ean</code>;
	 * 			<code>null</code>, falls kein Produkt mit dieser EAN gefunden wurde
	 */
	public static Produkt getProdukt(String ean) {
		JSONArray list = readData("products");
		
	    try {	    	
	    	// Suche nach passenden Eintrag
	    	Iterator<JSONObject> iter = list.iterator();
	    	
	    	while (iter.hasNext()) {
	    		JSONObject item = iter.next();
	    		
	    		// Falls ein Produkt mit der gleichen EAN gefunden wurde
	    		if (ean.compareTo(item.get("EAN").toString()) == 0) {
	    			String name = item.get("Name").toString();
	    			Float preis = Float.parseFloat(item.get("Preis").toString());
	    			Float gewicht = Float.parseFloat(item.get("Gewicht").toString());
	    			String anzahl = item.get("Anzahl").toString();
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
	 * Sucht nach allen Produkten deren Name oder EAN gleich <code>suchString</code> ist
	 * oder ein Teilwort davon bildet.
	 * 
	 * @param	suchString	
	 * @return	<code>ArrayList</code> mit den Produkten, die die Suchbedingungen erfüllen;
	 * 			sonst ist die <code>ArrayList</code> leer
	 */
	public static ArrayList<Produkt> produktSuchen(String suchString) {
		ArrayList<Produkt> produkte = getAllProducts();
		ArrayList<Produkt> list = new ArrayList<>();
		
		for (Produkt element : produkte) {
			if (element.getName().contains(suchString)) {
				list.add(element);
				continue;
			}
			
			// die Anfänge der EAN mit dem suchString vergleichen
			if (element.getEan().contains(suchString)) {
				if (element.getEan().substring(0, suchString.length()).compareTo(suchString) == 0) {
					list.add(element);
				}
			}
		}
		
		return list;
	}
	
	
	/**
	 * Erstellt eine Liste mit allen Produkten aus der Datenbank und gibt diese zurück.
	 * Sind keine Produkte gespeichert, wird eine leere Produktliste zurückgegeben.
	 * 
	 * @return	<code>ArrayList</code> mit allen Produkten
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
	    		String anzahl = item.get("Anzahl").toString();
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
	 * Sucht in der Datenbank nach der Kategorie mit dem Namen <code>name</code>
	 * und gibt bei Erfolg diese zurück.
	 * 
	 * @param	name	Name der gesuchten Kategorie
	 * @return	die Kategorie mit dem Namen <code>name</code>;
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
	 * @return	<code>ArrayList<code> mit den Kategorien
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
	 * @param ean	EAN des Produktes, das aus der Datenbank entfernt werden soll
	 * @return		<code>true</code>, falls das Produkt erfolgreich entfernt wurde;
	 * 				<code>false</code>, falls das Produkt nicht entfernt wurde
	 */
	public static boolean produktEntfernen(String ean) {
		JSONArray list = readData("products");
    	
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
	 * @param name	Name der Kategorie, die entfernt werden soll
	 * @return			<code>true</code>, falls die Kategorie erfolgreich entfernt wurde;
	 * 					<code>false</code>, falls die Kategorie nicht entfernt wurde
	 */
	public static boolean kategorieEntfernen(String name) {
		JSONArray list = readData("categories");
		
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
	 * Die Datenbank <code>dataFile</code> wird auf den Startzustand zurückgesetzt oder neu erzeugt, falls sie vorher noch nicht vorhanden war.
	 * Im Verzeichnis daten wird die Datei data.json abgelegt. Zu Beginn befindet sich in der Datei ein JSONObject mit einer leeren Produkt- und Kategorieliste.
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
		if (!dataFile.exists()) {
			createDataFile();
		}
		
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
	 * Schreibt Werte in die Datenbank zum zugehörigen <code>key</code>.
	 * 
	 * @param key	Schlüssel für die Daten
	 * @param list	Werte, die zum <code>key</code> geschrieben werden sollen
	 * @return		<code>true</code>, falls die Daten erfolgreich in die Datenbank
	 * 				geschrieben wurden;
	 * 				<code>false</code>, falls die Daten nicht geschrieben werden konnten
	 */
	private static boolean writeData(String key, JSONArray list) {
		if (!dataFile.exists()) {
			createDataFile();
		}
		
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
