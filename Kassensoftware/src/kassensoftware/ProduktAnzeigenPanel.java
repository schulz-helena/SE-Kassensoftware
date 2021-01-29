package kassensoftware;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.ListSelectionModel;


/**
 * Die Klasse <code>ProduktAnzeigenPanel</code> erstellt die GUI-Oberfläche 
 * mit der nach Produkten mit Hilfe ihres Namen oder ihrer EAN gesucht werden kann.
 *
 */
public class ProduktAnzeigenPanel extends JPanel {
	private JTextField suchfeld;
	private JTable table;
	private JLabel emptyLabel;
	JPanel bottomPanel;
	JScrollPane scrollPane;
	
	// Datenmodell für die Tabelle
	private DefaultTableModel model;
	
	private String spaltenNamen[] = {"Name", "EAN", "Preis", "Gewicht", "Grundpreis", "Bestand", "Kategorie"};
	
	
	/**
	 * Standardkonstruktor für das <code>ProduktAnzeigenPanel</code>.
	 */
	public ProduktAnzeigenPanel() {
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(10, 10, 10, 10));
		
		// Schriftart und Schriftfarbe festlegen
		Font schriftart = new Font("Verdana", 0, 20);
		Color schriftfarbe = new Color(0, 69, 129);
		Color schriftfarbe2 = new Color(255, 140, 78);
		
		JPanel suchleiste = new JPanel();
		add(suchleiste, BorderLayout.NORTH);
		suchleiste.setLayout(new BorderLayout(10, 0));
		suchleiste.setBorder(new EmptyBorder(0, 0, 10, 0));
		
		suchfeld = new JTextField();
		suchfeld.setHorizontalAlignment(SwingConstants.CENTER);
		suchfeld.setFont(schriftart);
		suchfeld.setForeground(schriftfarbe2);
		suchfeld.setColumns(10);
		suchfeld.setPreferredSize(new Dimension(500, 36));
		suchleiste.add(suchfeld);
		
		JButton suchenButton = new JButton("Suchen");
		suchenButton.setPreferredSize(new Dimension(100, 36));
		suchenButton.setFont(schriftart);
		suchenButton.setForeground(schriftfarbe);
		suchleiste.add(suchenButton, BorderLayout.EAST);
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		model = new DefaultTableModel();
		model.setColumnIdentifiers(spaltenNamen); // Spaltenüberschriften festlegen
		
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Verdana", Font.PLAIN, 16));
		table.setForeground(schriftfarbe);
		table.setGridColor(Color.DARK_GRAY);
		table.setFont(schriftart);
		table.setRowHeight(36);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setPreferredSize(new Dimension(100, 36));
		table.getTableHeader().setFont(new Font("Verdana", 1, 20));
		table.getTableHeader().setForeground(schriftfarbe);
		
		DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		
	
		scrollPane.setViewportView(table);
		
		emptyLabel = new JLabel("Keine Suchergebnisse");
		emptyLabel.setForeground(Color.DARK_GRAY);
		emptyLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
		emptyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(emptyLabel, BorderLayout.CENTER);
		
		bottomPanel = new JPanel();
		bottomPanel.setVisible(false);
		add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));
		
		JButton bearbeitenButton = new JButton("Bearbeiten");
		bearbeitenButton.setPreferredSize(new Dimension(150, 48));
		bearbeitenButton.setFont(schriftart);
		bearbeitenButton.setForeground(schriftfarbe);
		bottomPanel.add(bearbeitenButton);
		
		JButton entfernenButton = new JButton("Löschen");
		entfernenButton.setPreferredSize(new Dimension(150, 48));
		entfernenButton.setFont(schriftart);
		entfernenButton.setForeground(schriftfarbe);
		bottomPanel.add(entfernenButton);
		
		suchenButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				produktsucheAnzeigen(suchfeld.getText());
				suchfeld.setText("");
			}
		});
		
		entfernenButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int selectedRowIndex = table.getSelectedRow();
				
				if (selectedRowIndex != -1) {
					String ean = model.getValueAt(selectedRowIndex, 1).toString();
					JSONDemo.produktEntfernen(ean);
					model.removeRow(selectedRowIndex);
					
					if (model.getRowCount() == 0) {
						disappearTable();
					}
				}
			}
		});
	}
	
	
	/**
	 * Sucht in der Datenbank nach Produkten mit dem Namen oder der EAN, die im <code>sucheTextfeld</code> eingegeben wurde.
	 * 
	 * @param suchString
	 * @return	
	 */
	public void produktsucheAnzeigen(String suchString) {
		
		ArrayList<Produkt> daten = JSONDemo.produktSuchen(suchString);
		String eintrag[] = new String[7];
		
		// Entferne alle Zeilen aus der Tabelle
		model.setRowCount(0);
		
		// Falls Produkte gefunden wurden
		if (daten.size() != 0) {
			// Für jedes Produkt füge eine Zeile in der Tabelle hinzu
			for (Produkt element : daten) {
				eintrag[0] = element.getName();
				eintrag[1] = element.getEan().toString();
				eintrag[2] = element.getPreis().toString();
				eintrag[3] = element.getGewicht().toString();
				eintrag[4] = element.getGrundpreis().toString();
				eintrag[5] = element.getAnzahl().toString();
				eintrag[6] = element.getKategorie().getKategorieName();
				model.addRow(eintrag);
			}
			
			showTable();
		}
		else {
			disappearTable();
		}
	}
	
	
	/**
	 * Setzt das Suchpanel auf den Initialzustand zurück.
	 */
	public void refresh() {
		suchfeld.setText("");
		disappearTable();
	}
	
	
	/**
	 * Zeigt <code>table</code> an und lässt <code>emptyLabel</code> verschwinden.
	 */
	private void showTable() {
		this.add(scrollPane);
		this.remove(emptyLabel);
		bottomPanel.setVisible(true);
		this.revalidate();
		this.repaint();
	}
	
	
	/**
	 * Lässt <code>table</code> verschwinden und zeigt das <code>emptyLabel</code> an.
	 */
	private void disappearTable() {
		this.add(emptyLabel);
		this.remove(scrollPane);
		bottomPanel.setVisible(false);
		this.revalidate();
		this.repaint();
	}
}
