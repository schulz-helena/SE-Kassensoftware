package kassensoftware;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewLabel = new JLabel("Kategorie");
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.gridwidth = 10;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);

		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 3;
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Kategorie hinzuf\u00FCgen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				k1 = new Kategorie(textField.getText());
//				for (Kategorie kat : JSONDemo.getAllCategories()) {
//					if (k.getKategorieName() == kat.getKategorieName()) {
//						JOptionPane.showMessageDialog(null, "Kategorie bereits vorhanden");
//					}
//				}
				JSONDemo.kategorieSpeichern(k1);
			}
		});
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 5;
		gbc_btnNewButton.gridy = 1;
		add(btnNewButton, gbc_btnNewButton);

		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 6;
		gbc_textArea.gridwidth = 3;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 3;
		add(textArea, gbc_textArea);

		JButton btnKategorienAnzeigen = new JButton("Kategorien anzeigen");
		btnKategorienAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Kategorie kat : JSONDemo.getAllCategories()) {
					textArea.setText(textArea.getText() + "\n" + kat.getKategorieName());
				}
			}
		});
		btnKategorienAnzeigen.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_btnKategorienAnzeigen = new GridBagConstraints();
		gbc_btnKategorienAnzeigen.anchor = GridBagConstraints.NORTH;
		gbc_btnKategorienAnzeigen.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnKategorienAnzeigen.insets = new Insets(0, 0, 5, 5);
		gbc_btnKategorienAnzeigen.gridx = 5;
		gbc_btnKategorienAnzeigen.gridy = 3;
		add(btnKategorienAnzeigen, gbc_btnKategorienAnzeigen);

		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 3;
		gbc_textField_1.insets = new Insets(0, 0, 0, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 10;
		add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);

		JButton btnKategorienAnzeigen_1 = new JButton("Kategorie l\u00F6schen");
		btnKategorienAnzeigen_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kName = textField_1.getText();
				for (Kategorie kat : JSONDemo.getAllCategories()) {
					if (kat.getKategorieName().compareTo(kName) == 0) {
						JSONDemo.kategorieEntfernen(kat);
					}
				}

			}
		});
		btnKategorienAnzeigen_1.setFont(new Font("Verdana", Font.PLAIN, 15));
		GridBagConstraints gbc_btnKategorienAnzeigen_1 = new GridBagConstraints();
		gbc_btnKategorienAnzeigen_1.fill = GridBagConstraints.BOTH;
		gbc_btnKategorienAnzeigen_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnKategorienAnzeigen_1.gridx = 5;
		gbc_btnKategorienAnzeigen_1.gridy = 10;
		add(btnKategorienAnzeigen_1, gbc_btnKategorienAnzeigen_1);

	}

}