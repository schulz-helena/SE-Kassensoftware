package kassensoftware;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import javax.swing.border.MatteBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


/**
 * Die Klasse <code>Main</code> definiert das Hauptfenster des Kassensystems und
 * beinhaltet neben der Navigation auch einen Bereich zum Anzeigen der jeweiligen Panel
 * fuer die unterschiedlichen Funktionen.
 */
public class Main extends JFrame {

	private JPanel contentPane;
	private EinkaufPanel einkaufPanel;
	private ProduktsuchePanel produktsuchePanel;
	private ProduktPanel produktPanel;
	private KategoriePanel kategoriePanel;
	
	// Schriftart und Schriftfarbe festlegen
	private Color panelBackgroundColor = new Color(0, 69, 129);
	private Color buttonClickedColor = new Color(65, 131, 215);
	private Color buttonClickedColor2 = new Color(242, 121, 53);
	private Color buttonBackgroundColor = new Color(75, 119, 190);
	private Color buttonBackgroundColor2 = new Color(255, 140, 78);
	
	// Passwort eingegeben?
	private boolean passwordValid = false;
	
	
	/**
	 * Startet die Anwendung.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Erzeugt die Grafische Oberfläche des Kassensystems.
	 */
	public Main() {
		setTitle("Kassensoftware");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		// Panel fuer die Funktionen definieren
		einkaufPanel = new EinkaufPanel();
		produktsuchePanel = new ProduktsuchePanel();
		produktPanel = new ProduktPanel();
		kategoriePanel = new KategoriePanel();
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout(0, 0));
		topPanel.setPreferredSize(new Dimension(600, 100));
		topPanel.setBackground(panelBackgroundColor);
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel("Kassensystem");
		titleLabel.setVerticalAlignment(SwingConstants.TOP);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 30));
		topPanel.add(titleLabel, BorderLayout.CENTER);
		
		JLabel versionLabel = new JLabel("Version 1.0");
		versionLabel.setForeground(Color.WHITE);
		versionLabel.setHorizontalAlignment(SwingConstants.TRAILING);
		versionLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
		topPanel.add(versionLabel, BorderLayout.NORTH);
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(panelBackgroundColor);
		menuPanel.setLayout(new BorderLayout(0, 0));
		contentPane.add(menuPanel, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(250, 350));
		panel.setBackground(panelBackgroundColor);
		panel.setLayout(null);
		menuPanel.add(panel, BorderLayout.NORTH);
		
		// Label der Navigation erstellen
		JLabel menuLabel = new JLabel("Navigation");
		menuLabel.setBounds(0, 0, 250, 50);
		menuLabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		menuLabel.setForeground(new Color(255, 255, 255));
		menuLabel.setBackground(new Color(0, 0, 128));
		menuLabel.setFont(new Font("Verdana", Font.BOLD, 26));
		menuLabel.setVerticalAlignment(SwingConstants.TOP);
		menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(menuLabel);
		
		// Buttons zur Menüleiste hinzufügen
		JButton einkaufButton = new JButton("Einkauf");
		decorateButton(einkaufButton, 0, 60, buttonClickedColor, buttonBackgroundColor);
		panel.add(einkaufButton);
		
		einkaufButton.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				aktualisieren(einkaufPanel);
			}
		});
		
		JButton produktsucheButton = new JButton("Produktsuche");
		decorateButton(produktsucheButton, 0, 120, buttonClickedColor, buttonBackgroundColor);
		panel.add(produktsucheButton);
		
		produktsucheButton.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				aktualisieren(produktsuchePanel);
				produktsuchePanel.refresh();
			}
		});
		
		JButton produkteButton = new JButton("Produktverwaltung");
		decorateButton(produkteButton, 0, 180, buttonClickedColor, buttonBackgroundColor);
		panel.add(produkteButton);
		
		produkteButton.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!passwordValid) {
					enterWithPassword(produktPanel);
				}
				else {
					aktualisieren(produktPanel);
				}
				
				produktPanel.refresh();
			}
		});
		
		JButton kategorieButton = new JButton("Kategorien");
		decorateButton(kategorieButton, 0, 240, buttonClickedColor, buttonBackgroundColor);
		panel.add(kategorieButton);
		
		kategorieButton.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!passwordValid) {
					enterWithPassword(kategoriePanel);
				}
				else {
					aktualisieren(kategoriePanel);
				}
			}
		});
		
		JButton beendenButton = new JButton("Beenden");
		decorateButton(beendenButton, 0, 0, buttonBackgroundColor2, buttonClickedColor2);
		beendenButton.setPreferredSize(new Dimension(250, 50));
		menuPanel.add(beendenButton, BorderLayout.SOUTH);
		
		beendenButton.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		
		aktualisieren(einkaufPanel);
	}
	
	
	/** Zeigt das <code>panel</code> auf der Oberflaeche an.
	 * 
	 * @param panel das Panel, das angezeigt werden soll
	 */
	public void aktualisieren(JPanel panel) {
		// alle aktiven Panels entfernen
		contentPane.remove(einkaufPanel);
		contentPane.remove(produktsuchePanel);
		contentPane.remove(kategoriePanel);
		contentPane.remove(produktPanel);
		
		// das neue Panel hinzufuegen
		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	
	/**
	 * Schuetzt den Zugriff auf <code>panel</code> durch eine Passwortabfrage,
	 * bevor <code>panel</code> angezeigt wird.
	 * Ist das Passwort falsch, wird dem Benutzer der Zutritt nicht gestattet.
	 * 
	 * @param panel das Panel, welches nach erfolgreicher Eingabe des Passwortes angezeigt wird
	 */
	public void enterWithPassword(JPanel panel) {
		String passwort = "filialleiter"; // Passwort
		
		JPasswordField passwordField = new JPasswordField(10);
		passwordField.requestFocus();
        passwordField.setEchoChar('•');
        JOptionPane.showMessageDialog(null, passwordField, "Passwort eingeben", JOptionPane.OK_OPTION);
        String s = String.valueOf(passwordField.getPassword());
        if (s.equals(passwort)) {
        	aktualisieren(panel);
        	passwordValid = true;
        } else {
        	JOptionPane.showMessageDialog(null, "Das Passwort wurde falsch eingegeben!", "Zutritt verwährt", JOptionPane.ERROR_MESSAGE);
        }
	}
	
	
	/**
	 * Dekoriert einen Button für die Navigationsleiste.
	 * 
	 * @param button			der Button, der dekoriert werden soll
	 * @param posX				X-Koordinate des Buttons
	 * @param posY				Y-Koordinate des Buttons
	 * @param backgroundColor	die Hintegrundfarbe des Buttons
	 * @param mouseClickedColor	die Hintergrundfarbe, die der Button annimmt, wenn er angeklickt wird
	 */
	private void decorateButton(JButton button, int posX, int posY, Color backgroundColor, Color mouseClickedColor) {
		button.setBounds(posX, posY, 250, 50);
		button.setOpaque(true);
		button.setForeground(Color.WHITE);
		button.setBackground(backgroundColor);
		button.setBorderPainted(false);
		button.setFont(new Font("Verdana", Font.BOLD, 16));
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				button.setBackground(mouseClickedColor);
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				button.setBackground(backgroundColor);
			}
		});
	}
}
