package kassensoftware;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Die Klasse JSONDemo beinhaltet Methoden zur Speicherverwaltung der
 * Produkt- und Kategorie-Objekte. Die Daten werden in einer JSON-Datei gespeichert.
 * Sie kann nicht instanziiert werden.
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
	 * Speichert ein nicht leeres Produkt-Objekt in <code>dataFile</code> ab.
	 * Falls die Datei noch nicht existiert, wird sie erzeugt.
	 * 
	 * @param 	p das Produkt, das gespeichert werden soll
	 * @return 	<code>true</code>, falls das Produkt erfolgreich gespeichert wurde;
	 * 			sonst <code>false</code> 
	 */	
	public static boolean produktSpeichern(Produkt p) {
		if (!dataFile.exists()) {
			createDataFile();
		}
		
		// Falls das Produkt null ist
		if (p == null) {
			return false;
		}
		
	    Map<String, String> map = new HashMap<>();
	    
	    // Produkteigenschaften eintragen
	    map.put("EAN", p.getEan());
	    map.put("Name", p.getName());
	    map.put("Preis", p.getPreis().toString());
	    map.put("Gewicht", p.getGewicht().toString());
	    map.put("Grundpreis", p.getGrundpreis().toString());
	    map.put("Anzahl", p.getAnzahl().toString());
	    map.put("Kategorie", p.getKategorie().getKategorieName());
	    
	    // JSONObject erzeugen
	    JSONObject jsonObject = new JSONObject(map);

	    // JSONObject speichern
	    try {
	    	JSONParser parser = new JSONParser();
	    	JSONObject obj = (JSONObject) parser.parse(new FileReader(dataPath));
	    	JSONArray list = (JSONArray) obj.get("products");
	    	
	    	if (!list.contains(jsonObject)) {
	    		list.add(jsonObject);
		    	obj.put("products", list);
		    	
		    	BufferedWriter writer = new BufferedWriter(new FileWriter(dataPath));
		    	obj.writeJSONString(writer);
		    	writer.close();
	    	}
	    }
	    catch (ParseException e) {
	    	System.out.println(e.getMessage());
	    	return false;
	    }
	    catch (IOException e) {
	    	System.out.println(e.getMessage());
	    	return false;
	    }
	    
	    System.out.println("Produkt erfolgreich eingefügt.");
	    
	    return true;
	}
	
	
	/**
	 * Speichert ein nicht leeres Kategorie-Objekt in <code>dataFile</code> ab.
	 * Falls die Datei noch nicht existiert, wird sie erzeugt.
	 * 
	 * @param 	k die Kategorie, die gespeichert werden soll
	 * @return 	<code>true</code>, falls die Kategorie erfolgreich gespeichert wurde;
	 * 			sonst <code>false</code> 
	 */	
	public static boolean kategorieSpeichern(Kategorie k) {
		if (!dataFile.exists()) {
			createDataFile();
		}
		
		// Keine leeren Kategorien speichern
		if (k == null) {
			return false;
		}
		
		Map<String, String> map = new HashMap<>();
		
		// Eigenschaften übernehmen
		map.put("Name", k.getKategorieName());
		
		// neues JSONObject erzeugen
		JSONObject jsonObject = new JSONObject(map);
		
	    // JSONObject speichern
	    try {
	    	JSONParser parser = new JSONParser();
	    	JSONObject obj = (JSONObject) parser.parse(new FileReader(dataPath));
	    	JSONArray list = (JSONArray) obj.get("categories");
	    	
	    	if (!list.contains(jsonObject)) {
	    		list.add(jsonObject);
		    	obj.put("categories", list);
		    	
		    	BufferedWriter writer = new BufferedWriter(new FileWriter(dataPath));
		    	obj.writeJSONString(writer);
		    	writer.close();
	    	}
	    }
	    catch (ParseException e) {
	    	System.out.println(e.getMessage());
	    	return false;
	    	
	    }
	    catch (IOException e) {
	    	System.out.println(e.getMessage());
	    	return false;
	    	
	    }
	    
	    System.out.println("Kategorie erfolgreich eingefügt.");
		
		return true;
	}
	
	
	/**
	 * Sucht in <code>dataFile</code> nach einem Eintrag mit der EAN <code>ean</code>
	 * und gibt bei Erfolg ein Produkt-Objekt zurück.
	 * 
	 * @param	ean	die EAN des gesuchten Produkt-Objekts
	 * @return	das <code>Produkt</code> zur zugehörigen <code>ean</code>;
	 * 			<code>null</code>, falls kein Produkt mit dieser EAN gefunden wurde
	 */
	public static Produkt getProdukt(String ean) {
	    try {
	    	JSONParser parser = new JSONParser();
	    	JSONObject obj = (JSONObject) parser.parse(new FileReader(dataPath));
	    	JSONArray list = (JSONArray) obj.get("products");
	    	
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
	    catch (ParseException e) {
	    	System.out.println(e.getMessage());
	    	
	    }
	    catch (IOException e) {
	    	System.out.println(e.getMessage());
	    	
	    }
		
		return null;
	}
	
	
	/**
	 * Sucht in <code>dataFile</code> nach der Kategorie mit dem Namen <code>name</code>
	 * und gibt bei Erfolg eine Kategorie zurück.
	 * 
	 * @param	name	der Name der gesuchten Kategorie
	 * @return	die Kategorie mit dem Name <code>name</code>;
	 * 			<code>null</code>, falls keine Kategorie mit dem Namen gefunden wurde
	 */
	public static Kategorie getKategorie(String name) {
		
	    try {
	    	JSONParser parser = new JSONParser();
	    	JSONObject obj = (JSONObject) parser.parse(new FileReader(dataPath));
	    	JSONArray list = (JSONArray) obj.get("categories");
	    	
	    	Iterator<JSONObject> iter = list.iterator();
	    	
	    	while (iter.hasNext()) {
	    		JSONObject item = iter.next();
	    		
	    		// Falls die Kategorie mit dem Namen gefunden wurde
	    		if (name.compareTo(item.get("Name").toString()) == 0) {
	    			return new Kategorie(item.get("Name").toString());
	    		}
	    	}
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
	 *  
	 * 
	 * @param produkt	das Produkt, das aus der Datei entfernt werden soll
	 * @return			<code>true</code>, falls das Produkt aus dem Datenbestand entfernt wurde;
	 * 					<code>false</code>, falls das Produkt nicht entfernt wurde
	 */
	public static boolean produktEntfernen(Produkt produkt) {
	    try {
	    	JSONParser parser = new JSONParser();
	    	JSONObject obj = (JSONObject) parser.parse(new FileReader(dataPath));
	    	JSONArray list = (JSONArray) obj.get("products");
	    	boolean modified = false;
	    	String ean = produkt.getEan();
	    	int index = 0;
	    	
	    	Iterator<JSONObject> iter = list.iterator();
	    	
	    	while (iter.hasNext() && !modified) {
	    		JSONObject item = iter.next();
	    		
	    		if (ean.compareTo(item.get("EAN").toString()) == 0) {
	    			list.remove(index);
	    			modified = true;
	    		}
	    		
	    		index++;
	    	}
	    	
	    	if (modified) {
	    		obj.put("products", list);
			    BufferedWriter writer = new BufferedWriter(new FileWriter(dataPath));
			    obj.writeJSONString(writer);
			    writer.close();
			    return true;
	    	}
	    }
	    catch (ParseException e) {
	    	System.out.println(e.getMessage());
	    	
	    }
	    catch (IOException e) {
	    	System.out.println(e.getMessage());
	    	
	    }
	    
	    System.out.println("Produkt erfolgreich entfernt.");
		
		return false;
	}
	
	
	
	/**
	 *  
	 * 
	 * @param kategorie	die Kategorie, die entfernt werden soll
	 * @return			<code>true</code>, falls die Kategorie aus dem Datenbestand entfernt wurde;
	 * 					<code>false</code>, falls die Kategorie nicht entfernt wurde
	 */
	public static boolean kategorieEntfernen(Kategorie kategorie) {
	    try {
	    	JSONParser parser = new JSONParser();
	    	JSONObject obj = (JSONObject) parser.parse(new FileReader(dataPath));
	    	JSONArray list = (JSONArray) obj.get("categories");
	    	boolean modified = false;
	    	String name = kategorie.getKategorieName();
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
	    	
	    	if (modified) {
	    		obj.put("categories", list);
			    BufferedWriter writer = new BufferedWriter(new FileWriter(dataPath));
			    obj.writeJSONString(writer);
			    writer.close();
			    return true;
	    	}
	    }
	    catch (ParseException e) {
	    	System.out.println(e.getMessage());
	    	
	    }
	    catch (IOException e) {
	    	System.out.println(e.getMessage());
	    	
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
}

