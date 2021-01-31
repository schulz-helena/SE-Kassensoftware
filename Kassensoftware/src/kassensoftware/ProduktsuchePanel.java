package kassensoftware;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


/**
 * Die Klasse <code>ProduktsuchePanel</code> erstellt die GUI-Oberflaeche 
 * mit der nach Produkten mit Hilfe ihres Namen oder ihrer EAN gesucht werden kann.
 * Die gefundenen Produkte werden dann in einer Tabelle gelistet.
 *
 * @author Andreas Niemann
 */
public class ProduktsuchePanel extends JPanel {
	private JTable table;
	private JLabel emptyLabel;
	private JTextField suchfeld;
	JScrollPane scrollPane;
	
	// Datenmodell für die Tabelle
	private DefaultTableModel model;
	
	private String spaltenNamen[] = {"EAN", "Name", "Preis", "Gewicht", "Grundpreis", "Bestand", "Kategorie"};
	
	
	/**
	 * Standardkonstruktor für das <code>ProduktsuchePanel</code>.
	 */
	public ProduktsuchePanel() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		setBorder(new EmptyBorder(20, 20, 20, 20));
		
		// Schriftart und Schriftfarbe festlegen
		Font schriftart = new Font("Verdana", 0, 20);
		Color schriftfarbe = new Color(0, 69, 129);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(20, 0, 0, 0));
		add(scrollPane, BorderLayout.CENTER);
		
		// alle Tabellenzellen sind nicht editierbar
		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		model.setColumnIdentifiers(spaltenNamen); // Spaltenueberschriften festlegen
		
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
		
		// Suchfeld definieren
		suchfeld = new JTextField();
		add(suchfeld, BorderLayout.NORTH);
		suchfeld.setBorder(new LineBorder(Color.LIGHT_GRAY));
		suchfeld.setHorizontalAlignment(SwingConstants.CENTER);
		suchfeld.setFont(schriftart);
		suchfeld.setForeground(Color.GRAY);
		suchfeld.setText("Suche nach Name oder EAN");
		suchfeld.setColumns(10);
		suchfeld.setPreferredSize(new Dimension(500, 36));
		
		
		// Suchfeld reagiert auf Eingaben und zeigt unmittelbar Suchergebnisse
		suchfeld.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e) {
				produktsucheAnzeigen(suchfeld.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				produktsucheAnzeigen(suchfeld.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				produktsucheAnzeigen(suchfeld.getText());
			}
		});
		
		suchfeld.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (suchfeld.getText().trim().equals("Suche nach Name oder EAN")) {
					suchfeld.setText("");
					suchfeld.setForeground(schriftfarbe);
				}
			}
		});
	}
	
	
	/**
	 * Sucht in der Datenbank nach Produkten mit dem Namen oder der EAN, die im <code>sucheTextfeld</code> eingegeben wurde.
	 * 
	 * @param suchString	Suchbegriff mit dem in der Datenbank nach einem Produkt gesucht wird,
	 * 						kann der Name oder die EAN sein
	 */
	public void produktsucheAnzeigen(String suchString) {
		
		ArrayList<Produkt> daten = Datenverwaltung.produktSuchen(suchString);
		String eintrag[] = new String[7];
		
		// Entferne alle Zeilen aus der Tabelle
		model.setRowCount(0);
		
		// Falls Produkte gefunden wurden
		if (daten.size() != 0) {
			// Fuer jedes Produkt fuege eine Zeile in der Tabelle hinzu
			for (Produkt element : daten) {
				eintrag[0] = element.getEan().toString();
				eintrag[1] = element.getName();
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
	 * Aktualisiert das Panel.
	 */
	public void refresh() {
		produktsucheAnzeigen(suchfeld.getText());
	}
	
	
	/**
	 * Zeigt <code>table</code> an und laesst <code>emptyLabel</code> verschwinden.
	 */
	private void showTable() {
		this.add(scrollPane);
		this.remove(emptyLabel);
		this.revalidate();
		this.repaint();
	}
	
	
	/**
	 * Laesst <code>table</code> verschwinden und zeigt das <code>emptyLabel</code> an.
	 */
	private void disappearTable() {
		this.add(emptyLabel);
		this.remove(scrollPane);
		this.revalidate();
		this.repaint();
	}
}
