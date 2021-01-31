
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class EinkaufPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private Einkaufsliste einkaufsliste = new Einkaufsliste();
	private Rechnung rechnung;
	private JTextField textField_2;
	private ArrayList<Produkt> gefProdukte;
	private HashMap<Produkt, String> rech = new HashMap<Produkt, String>();

	/**
	 * Erzeugt das Einkaufen Panel.
	 */
	public EinkaufPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Einkauf");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.gridwidth = 4;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 5;
		add(scrollPane, gbc_scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setEditable(false);

		JButton btnNewButton_1 = new JButton("Produkt stornieren");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produkt p = einkaufsliste.findeProdukt(textField.getText());
				if (textField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Welches Produkt soll storniert werden? Geben Sie einen Namen ein.", "Fehler",
							JOptionPane.ERROR_MESSAGE);
				} else if (p == null) {
					JOptionPane.showMessageDialog(null, "Produkt befindet sich nicht auf der Einkaufsliste.", "Fehler",
							JOptionPane.ERROR_MESSAGE);
					textField.setText("");
				} else {
					einkaufsliste.produktStornieren(einkaufsliste.findeProdukt(textField.getText()).getName());

					rech.remove(p);
					rechnung = new Rechnung(0f, einkaufsliste);
					textArea.setText(rechnung.rechnungAktualisieren(rech));
					textField_1.setText(null);
					textField.setText(null);
					JOptionPane.showMessageDialog(null, "Das Produkt wurde storniert.", "Best�tigung",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		JLabel lblNewLabel_3 = new JLabel("Name/EAN");
		lblNewLabel_3.setFont(new Font("Verdana", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 2;
		gbc_lblNewLabel_3.gridy = 1;
		add(lblNewLabel_3, gbc_lblNewLabel_3);
		btnNewButton_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 1;
		add(btnNewButton_1, gbc_btnNewButton_1);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Anzahl");
		lblNewLabel_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 2;
		gbc_lblNewLabel_1.gridy = 2;
		add(lblNewLabel_1, gbc_lblNewLabel_1);

		JButton btnNewButton = new JButton("Produkt einkaufen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gefProdukte = new ArrayList<Produkt>();
				for (Produkt p : JSONDemo.produktSuchen(textField.getText())) {
					gefProdukte.add(p);
				}
				if (gefProdukte.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Es konnte kein Produkt mit dieser Bezeichnung gefunden werden.", "Fehler",
							JOptionPane.ERROR_MESSAGE);
					textField_1.setText(null);
				} else if (gefProdukte.size() > 1) {
					JOptionPane.showMessageDialog(null,
							"Es wurden mehrere Produkte mit dieser Bezeichnung gefunden. Bitte geben Sie die eindeutige EAN ein.",
							"Fehler", JOptionPane.ERROR_MESSAGE);
					gefProdukteAnzeigen(gefProdukte);
					textField_1.setText(null);
				} else if (gefProdukte.size() == 1) {
					if (textField_1.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Geben Sie eine Anzahl an.", "Fehler",
								JOptionPane.ERROR_MESSAGE);
					} else {
						Produkt p = gefProdukte.get(0);
						einkaufsliste.produktHinzufuegen(p);
						rech.put(p, textField_1.getText());
						rechnung = new Rechnung(0f, einkaufsliste);
						textArea.setText(rechnung.rechnungAktualisieren(rech));
						textField_1.setText(null);
						textField.setText(null);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Das sollte nicht passieren. Merk dir was du eingegeben hast.",
							"Fehler", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 13));

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 2;

		add(btnNewButton, gbc_btnNewButton);

		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.gridx = 1;
		gbc_textField_2.gridy = 3;
		add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Kunde Geld");
		lblNewLabel_2.setFont(new Font("Verdana", Font.PLAIN, 13));
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 2;
		gbc_lblNewLabel_2.gridy = 3;
		add(lblNewLabel_2, gbc_lblNewLabel_2);

		JButton btnNewButton_3 = new JButton("Einkauf stornieren");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				einkaufsliste.einkaufStornieren();
				String temp = (String.format("%-23s %-12s %-15s", "Produkt", "Anzahl", "Preis"));
				String rechnungHead = temp + "\n"
						+ String.format("%-25s %-15s %-15s\n", "----------", "-------", "--------");
				textArea.setText(rechnungHead + "Aktuell werden keine Produkte gekauft.");

				JOptionPane.showMessageDialog(null, "Der Einkauf wurde storniert.", "Best�tigung",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});

		JButton btnNewButton_2_1 = new JButton("Einkauf abschlie\u00DFen");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (textField_2.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Der Kunde hat noch kein Geld gegeben.", "Fehler",
							JOptionPane.ERROR_MESSAGE);
				} else {
					Float geldNeu = Float.parseFloat(textField_2.getText());
					if (geldNeu < 0) {
						JOptionPane.showMessageDialog(null, "Keine negativen Eingaben m�glich.", "Fehler",
								JOptionPane.ERROR_MESSAGE);
						textField_2.setText(null);
					} else {
						rechnung.setBrieftasche(geldNeu);
						rechnung.rechnungErstellen(rech);
						textArea.setText(null);
						textArea.setText(rechnung.rechnungDrucken());
						bestandAktualisieren(rech);

						textField_2.setText(null);
					}
				}

			}
		});
		btnNewButton_2_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		GridBagConstraints gbc_btnNewButton_2_1 = new GridBagConstraints();
		gbc_btnNewButton_2_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2_1.gridx = 3;
		gbc_btnNewButton_2_1.gridy = 5;
		add(btnNewButton_2_1, gbc_btnNewButton_2_1);

		JButton btnNewButton_2_1_1 = new JButton("Rechnung drucken");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText(null);
				textArea.setText("Rechnung wird gedruckt...");
				rech.clear();
				gefProdukte.clear();
				einkaufsliste.einkaufStornieren();
				textArea.setText(null);
			}
		});
		btnNewButton_2_1_1.setFont(new Font("Verdana", Font.PLAIN, 13));
		GridBagConstraints gbc_btnNewButton_2_1_1 = new GridBagConstraints();
		gbc_btnNewButton_2_1_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_2_1_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2_1_1.gridx = 3;
		gbc_btnNewButton_2_1_1.gridy = 6;
		add(btnNewButton_2_1_1, gbc_btnNewButton_2_1_1);
		btnNewButton_3.setFont(new Font("Verdana", Font.PLAIN, 13));
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton_3.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButton_3.gridx = 3;
		gbc_btnNewButton_3.gridy = 7;
		add(btnNewButton_3, gbc_btnNewButton_3);

	}

	/**
	 * Methode um den Bestand zu aktualisieren.
	 * 
	 * @param rech2
	 */
	public void bestandAktualisieren(HashMap<Produkt, String> rech2) {
		for (Map.Entry<Produkt, String> entry : rech2.entrySet()) {
			Produkt key = entry.getKey();
			String value = entry.getValue();
			if (key.getAnzahl().compareTo("n") == 0) {
				Float anzAlt = key.getGewicht();
				Float anzAenderUm = Float.parseFloat(value);
				Float anzNeu = anzAlt - anzAenderUm;
				key.setGewicht(anzNeu);
				JSONDemo.produktSpeichern(key);
			} else {
				int anzAlt = Integer.parseInt(key.getAnzahl());
				int anzAenderUm = Integer.parseInt(value);
				int anzNeu = anzAlt - anzAenderUm;
				String anzahlNeu = Integer.toString(anzNeu);
				key.setAnzahl(anzahlNeu);
				JSONDemo.produktSpeichern(key);
			}

		}
	}

	/**
	 * Methode um ein Produkt zu finden.
	 * 
	 * @param s Name bzw. Ean des gesuchten Produkts
	 */
	public ArrayList<Produkt> findProdukt(String s) {
		return JSONDemo.produktSuchen(s);
	}

	/**
	 * Gibt eine Message aus wenn mehrere Produkte mit dem gleichen Name gefunden
	 * wurde.
	 * 
	 */
	public void gefProdukteAnzeigen(ArrayList<Produkt> gef) {
		String s = "";
		for (Produkt p : gef) {
			s = s + ("Produktname: " + p.getName() + "; EAN: " + p.getEan() + "; Grundpreis: " + p.getGrundpreis()
					+ "; Kategorie: " + p.getKategorie() + "\n");
		}
		JOptionPane.showMessageDialog(null, s, "Gefundenen Produkte", JOptionPane.INFORMATION_MESSAGE);

	}
}