
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Klasse zum erzeugen der GUI für hinzufügen, löschen und Anzeigen von
 * <code>Kategorie</code>.
 * 
 * @author Nils Kohler
 *
 */
public class KategoriePanel extends JPanel {

	private Kategorie k1;
	private String kName;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public KategoriePanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Kategorien");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		lblNewLabel.setForeground(new Color(0, 69, 129));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.gridwidth = 11;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField();
		textField.setFont(new Font("Verdana", Font.PLAIN, 16));
		textField.setForeground(new Color(0, 69, 129));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 4;
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Kategorie hinzuf\u00FCgen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b = true;
				String name = textField.getText();
				for (int i = 0; i < name.length(); i++) {
					if (Character.isDigit(name.charAt(i))) {
						JOptionPane.showMessageDialog(null, "Die Kategorie darf keine Ziffern enthalten.",
								"Fehler bei Kategorie-Eingabe", JOptionPane.ERROR_MESSAGE);
						b = false;
						break;
					}
				}
				if (name.length() > 2 && name.length() < 32 && b == true) {
					k1 = new Kategorie(name);
					Datenverwaltung.kategorieSpeichern(k1);
					JOptionPane.showMessageDialog(null, "Kategorie erfolgreich hinzugefügt.");
					textField.setText(null);
				} else if (name.length() <= 2) {
					JOptionPane.showMessageDialog(null, "Die eingegebene Kategorie ist zu kurz.",
							"Fehler bei Namens-Eingabe", JOptionPane.ERROR_MESSAGE);
				} else if (name.length() >= 32) {
					JOptionPane.showMessageDialog(null, "Die eingegebene Kategorie ist zu lang.",
							"Fehler bei Namens-Eingabe", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnNewButton.setForeground(new Color(0, 69, 129));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 1;
		add(btnNewButton, gbc_btnNewButton);

		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Verdana", Font.PLAIN, 16));
		textArea.setForeground(new Color(0, 69, 129));
		textArea.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 6;
		gbc_textArea.gridwidth = 4;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 3;
		add(textArea, gbc_textArea);

		JButton btnKategorienAnzeigen = new JButton("Kategorien anzeigen");
		btnKategorienAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newLine = "";
				textArea.setText("");
				for (Kategorie kat : Datenverwaltung.getAlleKategorien()) {
					newLine = kat.getKategorieName() + "\n";
					textArea.setText(textArea.getText() + newLine);
				}
			}
		});
		btnKategorienAnzeigen.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnKategorienAnzeigen.setForeground(new Color(0, 69, 129));
		GridBagConstraints gbc_btnKategorienAnzeigen = new GridBagConstraints();
		gbc_btnKategorienAnzeigen.anchor = GridBagConstraints.NORTH;
		gbc_btnKategorienAnzeigen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnKategorienAnzeigen.insets = new Insets(0, 0, 5, 5);
		gbc_btnKategorienAnzeigen.gridx = 6;
		gbc_btnKategorienAnzeigen.gridy = 3;
		add(btnKategorienAnzeigen, gbc_btnKategorienAnzeigen);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Verdana", Font.PLAIN, 16));
		textField_1.setForeground(new Color(0, 69, 129));
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 4;
		gbc_textField_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 10;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JButton btnKategorienAnzeigen_1 = new JButton("Kategorie l\u00F6schen");
		btnKategorienAnzeigen_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = 0;
				kName = textField_1.getText();
				if (findKategorieInProdukt(kName) == true) {
					JOptionPane.showMessageDialog(null, "Die eingegebene Kategorie ist noch einem Produkt zugeordnet.",
							"Fehler bei Kategorie-Löschung", JOptionPane.ERROR_MESSAGE);
				} else {
					for (Kategorie kat : Datenverwaltung.getAlleKategorien()) {
						if (kat.getKategorieName().compareTo(kName) == 0) {
							Datenverwaltung.kategorieEntfernen(kat.getKategorieName());
							textField_1.setText(null);
							JOptionPane.showMessageDialog(null, "Die Kategorie wurde erfolgreich entfernt.");
						}
						i++;
					}
				}
				if (i == Datenverwaltung.getAlleKategorien().size()) {
					JOptionPane.showMessageDialog(null, "Die Kategorie konnte nicht gefunden werden.",
							"Fehler bei Kategorie-Löschung", JOptionPane.ERROR_MESSAGE);
					textField_1.setText(null);
				}

			}
		});
		btnKategorienAnzeigen_1.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnKategorienAnzeigen_1.setForeground(new Color(0, 69, 129));
		GridBagConstraints gbc_btnKategorienAnzeigen_1 = new GridBagConstraints();
		gbc_btnKategorienAnzeigen_1.fill = GridBagConstraints.BOTH;
		gbc_btnKategorienAnzeigen_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnKategorienAnzeigen_1.gridx = 6;
		gbc_btnKategorienAnzeigen_1.gridy = 10;
		add(btnKategorienAnzeigen_1, gbc_btnKategorienAnzeigen_1);

	}

	/**
	 * Methode zum finden einer <code>Kategorie</code> die noch einem
	 * <code>Produkt</code> zugeordnet ist.
	 * 
	 * @param katName Name der gesuchten Kategorie
	 * @return true falls die Kategorie <code>katName</code> noch einem
	 *         <code>Produkt</code> zugeordnet ist, sonst <code>false</code>
	 */
	public boolean findKategorieInProdukt(String katName) {
		for (Produkt p : Datenverwaltung.getAlleProdukte()) {
			Kategorie k = p.getKategorie();
			if (k.getKategorieName().compareTo(katName) == 0) {
				return true;
			}
		}
		return false;

	}
}