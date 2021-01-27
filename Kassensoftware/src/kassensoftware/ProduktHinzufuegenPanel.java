package kassensoftware;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Window;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import static javax.swing.JOptionPane.*;
/**
 * Über das ProduktHinzufuegenPanel kann der Nutzer die Produkteigenschaften selbst wählen
 * und mit diesen Eigenschaften ein Produkt erstellen
 * @author Helena Schulz
 */
public class ProduktHinzufuegenPanel extends JPanel {

	private JTextField name_txt;
	private JTextField ean_txt;
	private JTextField gewicht_txt;
	private JTextField preis_txt;
	private JTextField anzahl_txt;

	/**
	 * Create the panel.
	 */
	public ProduktHinzufuegenPanel() {
		this.setSize(637, 447);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{110, 0, 40, 0, -1, 0, 22, 0, 0, 0, 0, 0, 0, 49, 0, 0};
		gridBagLayout.rowHeights = new int[]{71, 34, 34, 34, 34, 34, 52, 92, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel ueberschrift_lbl = new JLabel("Produkt hinzufügen");
		ueberschrift_lbl.setFont(new Font("Verdana", Font.BOLD, 20));
		GridBagConstraints gbc_ueberschrift_lbl = new GridBagConstraints();
		gbc_ueberschrift_lbl.gridwidth = 15;
		gbc_ueberschrift_lbl.insets = new Insets(0, 0, 5, 0);
		gbc_ueberschrift_lbl.gridx = 0;
		gbc_ueberschrift_lbl.gridy = 0;
		add(ueberschrift_lbl, gbc_ueberschrift_lbl);

		
		JLabel name_lbl = new JLabel("Produktname:");
		name_lbl.setFont(new Font("Verdana", Font.PLAIN, 13));
		GridBagConstraints gbc_name_lbl = new GridBagConstraints();
		gbc_name_lbl.anchor = GridBagConstraints.EAST;
		gbc_name_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_name_lbl.gridx = 0;
		gbc_name_lbl.gridy = 1;
		add(name_lbl, gbc_name_lbl);
		
		name_txt = new JTextField();
		name_txt.setFont(new Font("Verdana", Font.PLAIN, 13));
		name_txt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				
	        }
			@Override
			public void focusLost(FocusEvent e) {
				String name = name_txt.getText();
				for (int i = 0; i < name.length(); i++) {
					if(Character.isDigit(name.charAt(i))) {
				        JOptionPane.showMessageDialog(null, "Der eingegebene Name darf keine Ziffern enthalten.", "Fehler bei Namens-Eingabe", JOptionPane.ERROR_MESSAGE);
				        name_txt.requestFocus();
				        break;
				    }
				}
				if (name.length() >0 && name.length() < 2) {
					JOptionPane.showMessageDialog(null, "Der eingegebene Name ist zu kurz.", "Fehler bei Namens-Eingabe", JOptionPane.ERROR_MESSAGE);
					name_txt.requestFocus();
				}
				else if (name.length() > 32) {
					name_txt.requestFocus();
				}
			}
		});
		GridBagConstraints gbc_name_txt = new GridBagConstraints();
		gbc_name_txt.fill = GridBagConstraints.HORIZONTAL;
		gbc_name_txt.gridwidth = 12;
		gbc_name_txt.insets = new Insets(0, 0, 5, 5);
		gbc_name_txt.gridx = 1;
		gbc_name_txt.gridy = 1;
		add(name_txt, gbc_name_txt);
		name_txt.setColumns(10);
		
		JLabel ean_lbl = new JLabel("EAN:");
		ean_lbl.setFont(new Font("Verdana", Font.PLAIN, 13));
		GridBagConstraints gbc_ean_lbl = new GridBagConstraints();
		gbc_ean_lbl.anchor = GridBagConstraints.EAST;
		gbc_ean_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_ean_lbl.gridx = 0;
		gbc_ean_lbl.gridy = 2;
		add(ean_lbl, gbc_ean_lbl);
		
		JComboBox ean_box = new JComboBox();
		ean_box.setFont(new Font("Verdana", Font.PLAIN, 13));
		ean_box.setModel(new DefaultComboBoxModel(new String[] {"EAN-8", "EAN-13"}));
		GridBagConstraints gbc_ean_box = new GridBagConstraints();
		gbc_ean_box.insets = new Insets(0, 0, 5, 5);
		gbc_ean_box.fill = GridBagConstraints.HORIZONTAL;
		gbc_ean_box.gridx = 13;
		gbc_ean_box.gridy = 2;
		add(ean_box, gbc_ean_box);
		
		ean_txt = new JTextField();
		ean_txt.setFont(new Font("Verdana", Font.PLAIN, 13));
		ean_txt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				
	        }
			@Override
			public void focusLost(FocusEvent e) {
				String ean = ean_txt.getText();
				for (int i = 0; i < ean.length(); i++) {
			        if(Character.isDigit(ean.charAt(i)) == false) {
			        	JOptionPane.showMessageDialog(null, "Die eingegebene EAN darf nur Ziffern enthalten.", "Fehler bei EAN-Eingabe", JOptionPane.ERROR_MESSAGE);
			        	ean_txt.requestFocus();
			        	break;
			        }
			    }
				ArrayList<Produkt> produkte = JSONDemo.getAllProducts();
				int size = produkte.size();
				String produktListe[] = new String[size];
				for (int i = 0; i < size; i++) { 
		            produktListe[i] = produkte.get(i).getEan();
		            if (produktListe[i].equals(ean)) {
		            	JOptionPane.showMessageDialog(null, "Es ist bereits ein Produkt mit der eingegebenen EAN vorhanden.", "Fehler bei EAN-Eingabe", JOptionPane.ERROR_MESSAGE);
		            	ean_txt.requestFocus();
		            	break;
		            }
				}
				if (ean_box.getSelectedItem() == "EAN-8" && ean_txt.getText().length() > 8) {
					JOptionPane.showMessageDialog(null, "Die eingegebene EAN ist zu lang.", "Fehler bei EAN-Eingabe", JOptionPane.ERROR_MESSAGE);
					ean_txt.requestFocus();
				}
				else if (ean_box.getSelectedItem() == "EAN-13" && ean_txt.getText().length() > 13) {
					JOptionPane.showMessageDialog(null, "Die eingegebene EAN ist zu lang.", "Fehler bei EAN-Eingabe", JOptionPane.ERROR_MESSAGE);
					ean_txt.requestFocus();
				}
			}
		});
		ean_txt.setColumns(10);
		GridBagConstraints gbc_ean_txt = new GridBagConstraints();
		gbc_ean_txt.gridwidth = 12;
		gbc_ean_txt.insets = new Insets(0, 0, 5, 5);
		gbc_ean_txt.fill = GridBagConstraints.HORIZONTAL;
		gbc_ean_txt.gridx = 1;
		gbc_ean_txt.gridy = 2;
		add(ean_txt, gbc_ean_txt);
		
		
		JLabel gewicht_lbl = new JLabel("Gewicht:");
		gewicht_lbl.setFont(new Font("Verdana", Font.PLAIN, 13));
		GridBagConstraints gbc_gewicht_lbl = new GridBagConstraints();
		gbc_gewicht_lbl.anchor = GridBagConstraints.EAST;
		gbc_gewicht_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_gewicht_lbl.gridx = 0;
		gbc_gewicht_lbl.gridy = 3;
		add(gewicht_lbl, gbc_gewicht_lbl);
		
		JComboBox gewicht_box = new JComboBox();
		gewicht_box.setFont(new Font("Verdana", Font.PLAIN, 13));
		gewicht_box.setModel(new DefaultComboBoxModel(new String[] {"g", "kg", "ml", "l"}));
		GridBagConstraints gbc_gewicht_box = new GridBagConstraints();
		gbc_gewicht_box.insets = new Insets(0, 0, 5, 5);
		gbc_gewicht_box.fill = GridBagConstraints.HORIZONTAL;
		gbc_gewicht_box.gridx = 13;
		gbc_gewicht_box.gridy = 3;
		add(gewicht_box, gbc_gewicht_box);
		
		gewicht_txt = new JTextField();
		gewicht_txt.setFont(new Font("Verdana", Font.PLAIN, 13));
		gewicht_txt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				
	        }
			@Override
			public void focusLost(FocusEvent e) {
				Float gewicht = 0.0f;
				String gewichtsString = gewicht_txt.getText();
				if (gewichtsString.indexOf(',') != -1) {
					gewichtsString = gewichtsString.replace(',', '.');
				}
				if (gewichtsString.length() > 0) {
					try {
						gewicht = Float.parseFloat(gewichtsString);
					} catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Keine gültige Zahl eingegeben.", "Fehler bei Gewichts-Eingabe", JOptionPane.ERROR_MESSAGE);
						gewicht_txt.requestFocus();
					}
					if (gewicht_box.getSelectedItem() == "kg" && gewicht < 0.001 && gewicht > 0) {
						JOptionPane.showMessageDialog(null, "Das eingegebene Gewicht ist zu niedrig.", "Fehler bei Gewichts-Eingabe", JOptionPane.ERROR_MESSAGE);
						gewicht_txt.requestFocus();
					}
					else if (gewicht_box.getSelectedItem() == "kg" && gewicht > 100) {
						JOptionPane.showMessageDialog(null, "Das eingegebene Gewicht ist zu hoch.", "Fehler bei Gewichts-Eingabe", JOptionPane.ERROR_MESSAGE);
						gewicht_txt.requestFocus();
					}
					else if (gewicht_box.getSelectedItem() == "g" && gewicht < 1 && gewicht > 0) {
						JOptionPane.showMessageDialog(null, "Das eingegebene Gewicht ist zu niedrig.", "Fehler bei Gewichts-Eingabe", JOptionPane.ERROR_MESSAGE);
						gewicht_txt.requestFocus();
					}
					else if (gewicht_box.getSelectedItem() == "g" && gewicht > 100000) {
						JOptionPane.showMessageDialog(null, "Das eingegebene Gewicht ist zu hoch.", "Fehler bei Gewichts-Eingabe", JOptionPane.ERROR_MESSAGE);
						gewicht_txt.requestFocus();
					}
					else if (gewicht_box.getSelectedItem() == "l" && gewicht < 0.001 && gewicht > 0) {
						JOptionPane.showMessageDialog(null, "Das eingegebene Gewicht ist zu niedrig.", "Fehler bei Gewichts-Eingabe", JOptionPane.ERROR_MESSAGE);
						gewicht_txt.requestFocus();
					}
					else if (gewicht_box.getSelectedItem() == "l" && gewicht > 10) {
						JOptionPane.showMessageDialog(null, "Das eingegebene Gewicht ist zu hoch.", "Fehler bei Gewichts-Eingabe", JOptionPane.ERROR_MESSAGE);
						gewicht_txt.requestFocus();
					}
					else if (gewicht_box.getSelectedItem() == "ml" && gewicht < 1 && gewicht > 0) {
						JOptionPane.showMessageDialog(null, "Das eingegebene Gewicht ist zu niedrig.", "Fehler bei Gewichts-Eingabe", JOptionPane.ERROR_MESSAGE);
						gewicht_txt.requestFocus();
					}
					else if (gewicht_box.getSelectedItem() == "ml" && gewicht > 10000) {
						JOptionPane.showMessageDialog(null, "Das eingegebene Gewicht ist zu hoch.", "Fehler bei Gewichts-Eingabe", JOptionPane.ERROR_MESSAGE);
						gewicht_txt.requestFocus();
					}
				}
			}
		});
		gewicht_txt.setColumns(10);
		GridBagConstraints gbc_gewicht_txt = new GridBagConstraints();
		gbc_gewicht_txt.gridwidth = 12;
		gbc_gewicht_txt.insets = new Insets(0, 0, 5, 5);
		gbc_gewicht_txt.fill = GridBagConstraints.HORIZONTAL;
		gbc_gewicht_txt.gridx = 1;
		gbc_gewicht_txt.gridy = 3;
		add(gewicht_txt, gbc_gewicht_txt);
		
		
		JLabel preis_lbl = new JLabel("Preis:");
		preis_lbl.setFont(new Font("Verdana", Font.PLAIN, 13));
		GridBagConstraints gbc_preis_lbl = new GridBagConstraints();
		gbc_preis_lbl.anchor = GridBagConstraints.EAST;
		gbc_preis_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_preis_lbl.gridx = 0;
		gbc_preis_lbl.gridy = 4;
		add(preis_lbl, gbc_preis_lbl);
		
		preis_txt = new JTextField();
		preis_txt.setFont(new Font("Verdana", Font.PLAIN, 13));
		preis_txt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				
	        }
			@Override
			public void focusLost(FocusEvent e) {
				Float preis = 0.0f;
				String preisString = preis_txt.getText();
				if (preisString.indexOf(',') != -1) {
					preisString = preisString.replace(',', '.');
				}
				if (preisString.length() > 0) {
					try {
						preis = Float.parseFloat(preisString);
					} catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Keine gültige Zahl eingegeben.", "Fehler bei Preis-Eingabe", JOptionPane.ERROR_MESSAGE);
						preis_txt.requestFocus();
					}
					if (preis > 0 && preis < 0.01) {
						JOptionPane.showMessageDialog(null, "Der eingegebene Preis ist zu niedrig.", "Fehler bei Preis-Eingabe", JOptionPane.ERROR_MESSAGE);
						preis_txt.requestFocus();
					}
					else if (preis > 100000) {
						JOptionPane.showMessageDialog(null, "Der eingegebene Preis ist zu hoch.", "Fehler bei Preis-Eingabe", JOptionPane.ERROR_MESSAGE);
						preis_txt.requestFocus();
					}
				}
			}
		});
		preis_txt.setColumns(10);
		GridBagConstraints gbc_preis_txt = new GridBagConstraints();
		gbc_preis_txt.gridwidth = 12;
		gbc_preis_txt.insets = new Insets(0, 0, 5, 5);
		gbc_preis_txt.fill = GridBagConstraints.HORIZONTAL;
		gbc_preis_txt.gridx = 1;
		gbc_preis_txt.gridy = 4;
		add(preis_txt, gbc_preis_txt);
		
		JLabel euro_lbl = new JLabel("€");
		euro_lbl.setFont(new Font("Verdana", Font.PLAIN, 13));
		GridBagConstraints gbc_euro_lbl = new GridBagConstraints();
		gbc_euro_lbl.anchor = GridBagConstraints.WEST;
		gbc_euro_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_euro_lbl.gridx = 13;
		gbc_euro_lbl.gridy = 4;
		add(euro_lbl, gbc_euro_lbl);
		
		JLabel anzahl_lbl = new JLabel("Anzahl:");
		anzahl_lbl.setFont(new Font("Verdana", Font.PLAIN, 13));
		GridBagConstraints gbc_anzahl_lbl = new GridBagConstraints();
		gbc_anzahl_lbl.anchor = GridBagConstraints.EAST;
		gbc_anzahl_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_anzahl_lbl.gridx = 0;
		gbc_anzahl_lbl.gridy = 5;
		add(anzahl_lbl, gbc_anzahl_lbl);
		
		JComboBox anzahl_box = new JComboBox();
		anzahl_box.setFont(new Font("Verdana", Font.PLAIN, 13));
		anzahl_box.setModel(new DefaultComboBoxModel(new String[] {"Stück", "Gesamtgewicht"}));
		GridBagConstraints gbc_anzahl_box = new GridBagConstraints();
		gbc_anzahl_box.insets = new Insets(0, 0, 5, 5);
		gbc_anzahl_box.fill = GridBagConstraints.HORIZONTAL;
		gbc_anzahl_box.gridx = 13;
		gbc_anzahl_box.gridy = 5;
		add(anzahl_box, gbc_anzahl_box);
		
		anzahl_txt = new JTextField();
		anzahl_txt.setFont(new Font("Verdana", Font.PLAIN, 13));
		anzahl_txt.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				
	        }
			@Override
			public void focusLost(FocusEvent e) {
				Long anzahl = (long)0;
				if (anzahl_txt.getText().length() > 0) {
					try {
						anzahl = Long.parseLong(anzahl_txt.getText());
					} catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Keine Nummer eingegeben.", "Fehler bei Anzahl-Eingabe", JOptionPane.ERROR_MESSAGE);
						anzahl_txt.requestFocus();
					}
					if (anzahl_box.getSelectedItem() == "Stück" && anzahl > 1000) {
						JOptionPane.showMessageDialog(null, "Die eingegebene Anzahl ist zu groß.", "Fehler bei Anzahl-Eingabe", JOptionPane.ERROR_MESSAGE);
						anzahl_txt.requestFocus();
					}
					else if (anzahl_box.getSelectedItem() == "Gesamtgewicht" && anzahl > 100000) {
						JOptionPane.showMessageDialog(null, "Das eingegebene Gesamtgewicht ist zu groß.", "Fehler bei Anzahl-Eingabe", JOptionPane.ERROR_MESSAGE);
						anzahl_txt.requestFocus();
					}
				}
			}
		});
		anzahl_txt.setColumns(10);
		GridBagConstraints gbc_anzahl_txt = new GridBagConstraints();
		gbc_anzahl_txt.gridwidth = 12;
		gbc_anzahl_txt.insets = new Insets(0, 0, 5, 5);
		gbc_anzahl_txt.fill = GridBagConstraints.HORIZONTAL;
		gbc_anzahl_txt.gridx = 1;
		gbc_anzahl_txt.gridy = 5;
		add(anzahl_txt, gbc_anzahl_txt);
		
		JLabel kategorie_lbl = new JLabel("Kategorie:");
		kategorie_lbl.setFont(new Font("Verdana", Font.PLAIN, 13));
		GridBagConstraints gbc_kategorie_lbl = new GridBagConstraints();
		gbc_kategorie_lbl.anchor = GridBagConstraints.EAST;
		gbc_kategorie_lbl.insets = new Insets(0, 0, 5, 5);
		gbc_kategorie_lbl.gridx = 0;
		gbc_kategorie_lbl.gridy = 6;
		add(kategorie_lbl, gbc_kategorie_lbl);
		
		ArrayList<Kategorie> kategorien = JSONDemo.getAllCategories();
		int size = kategorien.size();
		String kategorieListe[] = new String[size];
		for (int i = 0; i < size; i++) { 
            kategorieListe[i] = kategorien.get(i).getKategorieName();
		}
		Arrays.sort(kategorieListe);
		JComboBox kategorie_box = new JComboBox(kategorieListe);
		kategorie_box.setFont(new Font("Verdana", Font.PLAIN, 13));
		GridBagConstraints gbc_kategorie_box = new GridBagConstraints();
		gbc_kategorie_box.gridwidth = 12;
		gbc_kategorie_box.insets = new Insets(0, 0, 5, 5);
		gbc_kategorie_box.fill = GridBagConstraints.HORIZONTAL;
		gbc_kategorie_box.gridx = 1;
		gbc_kategorie_box.gridy = 6;
		add(kategorie_box, gbc_kategorie_box);
		
		JButton bestaetigen_btn = new JButton("Hinzufügen bestätigen");
		bestaetigen_btn.setFont(new Font("Verdana", Font.PLAIN, 13));
		bestaetigen_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent a) {
				//Name
				String name = "";
				if (name_txt.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Kein Name eingegeben.", "Fehler bei Bestätigung", JOptionPane.ERROR_MESSAGE);
				} else {
					name = name_txt.getText();
				}
				//EAN
				String ean = "";
				if (ean_txt.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Keine EAN eingegeben.", "Fehler bei Bestätigung", JOptionPane.ERROR_MESSAGE);
				}
				else if (ean_box.getSelectedItem() == "EAN-8" && ean_txt.getText().length() > 0 && ean_txt.getText().length() < 8) {
					int leng = ean_txt.getText().length();
					ean = ean_txt.getText();
					for( ; leng < 8; leng++) {
		                ean = "0" + ean;
		            }
				}
				else if (ean_box.getSelectedItem() == "EAN-13" && ean_txt.getText().length() > 0 && ean_txt.getText().length() < 13) {
					int leng = ean_txt.getText().length();
					ean = ean_txt.getText();
					for( ; leng < 13; leng++) {
		                ean = "0" + ean;
		            }
				}
				else {
					ean = ean_txt.getText();
				}
				//Gewicht
				Float gewicht = 0.0f;
				if (gewicht_txt.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Kein Gewicht eingegeben.", "Fehler bei Bestätigung", JOptionPane.ERROR_MESSAGE);
				} else {
					String gewichtsString = gewicht_txt.getText();
					if (gewichtsString.indexOf(',') != -1) {
						gewichtsString = gewichtsString.replace(',', '.');
						if (gewicht_box.getSelectedItem() == "kg" || gewicht_box.getSelectedItem() == "l") {
							gewicht = Float.parseFloat(gewichtsString);
							gewicht = gewicht * 1000;
						} else {
							gewicht = Float.parseFloat(gewichtsString);
						}
					} else {
						gewicht = Float.parseFloat(gewichtsString);
					}
				}
				//Preis
				Float preis = 0.0f;
				if (preis_txt.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Kein Preis eingegeben.", "Fehler bei Bestätigung", JOptionPane.ERROR_MESSAGE);
				} else {
					String preisString = preis_txt.getText();
					if (preisString.indexOf(',') != -1) {
						preisString = preisString.replace(',', '.');
						preis = Float.parseFloat(preisString);
					}
					else {
						preis = Float.parseFloat(preisString);
					}
				}
				//Anzahl
				Integer anzahl = 0;
				if (anzahl_txt.getText().length() > 0) {
					anzahl = Integer.parseInt(anzahl_txt.getText());
				}
				//Kategorie
				String kategoriename = "";
				Kategorie kategorie = null;
				if(kategorie_box.getItemCount() == 0) {
					JOptionPane.showMessageDialog(null, "Dem Produkt konnte keine Kategorie zugeordnet werden. Bitte zunächst eine Kategorie erstellen.", "Fehler bei Bestätigung", JOptionPane.ERROR_MESSAGE);
				} else {
					kategoriename = kategorie_box.getSelectedItem().toString();
					kategorie = JSONDemo.getKategorie(kategoriename);
				}
				//Hinzufügen
				if (name != "" && ean != "" && gewicht != 0 && preis != 0 && kategorie != null) {
					//Produkt hinzufügen
					Produkt neuesProdukt = new Produkt(name, ean, preis, gewicht, anzahl, kategorie);
					JSONDemo.produktSpeichern(neuesProdukt);
					JOptionPane.showMessageDialog(null, "Das Hinzufügen war erfolgreich", "Bestätigung", JOptionPane.INFORMATION_MESSAGE);
					name_txt.setText(null);
					ean_txt.setText(null);
					gewicht_txt.setText(null);
					preis_txt.setText(null);
					anzahl_txt.setText(null);
					ean_box.setSelectedIndex(0);
					gewicht_box.setSelectedIndex(0);
					anzahl_box.setSelectedIndex(0);
					if(kategorie_box.getItemCount() != 0){
						kategorie_box.setSelectedIndex(0);  
					}
				}
			}
		});
		GridBagConstraints gbc_bestaetigen_btn = new GridBagConstraints();
		gbc_bestaetigen_btn.fill = GridBagConstraints.BOTH;
		gbc_bestaetigen_btn.gridwidth = 13;
		gbc_bestaetigen_btn.insets = new Insets(0, 0, 0, 5);
		gbc_bestaetigen_btn.gridx = 0;
		gbc_bestaetigen_btn.gridy = 7;
		add(bestaetigen_btn, gbc_bestaetigen_btn);
		
		JButton zuruecksetzen_btn = new JButton("Zurücksetzen");
		zuruecksetzen_btn.setFont(new Font("Verdana", Font.PLAIN, 13));
		zuruecksetzen_btn.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				name_txt.setText(null);
				ean_txt.setText(null);
				gewicht_txt.setText(null);
				preis_txt.setText(null);
				anzahl_txt.setText(null);
				ean_box.setSelectedIndex(0);
				gewicht_box.setSelectedIndex(0);
				anzahl_box.setSelectedIndex(0);
				if(kategorie_box.getItemCount() != 0){
					kategorie_box.setSelectedIndex(0);  
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		GridBagConstraints gbc_zuruecksetzen_btn = new GridBagConstraints();
		gbc_zuruecksetzen_btn.fill = GridBagConstraints.BOTH;
		gbc_zuruecksetzen_btn.gridwidth = 2;
		gbc_zuruecksetzen_btn.gridx = 13;
		gbc_zuruecksetzen_btn.gridy = 7;
		add(zuruecksetzen_btn, gbc_zuruecksetzen_btn);	
	}
}
