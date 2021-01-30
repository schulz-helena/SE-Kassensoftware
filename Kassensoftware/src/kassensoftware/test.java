package kassensoftware;

//package de

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


public class test extends JFrame {

	private JPanel contentPane;
	private StartPanel startPanel;
	private ProduktHinzufuegenPanel neuesProduktPanel;
	private KategoriePanel kategoriePanel;
	private EinkaufPanel einkaufPanel;
	private ProduktAnzeigenPanel produktAnzeigenPanel;
	
	// Schriftart und Schriftfarbe festlegen
	private Color panelBackgroundColor = new Color(0, 69, 129);
	private Color buttonClickedColor = new Color(65, 131, 215);
	private Color buttonClickedColor2 = new Color(242, 121, 53);
	private Color buttonBackgroundColor = new Color(75, 119, 190);
	private Color buttonBackgroundColor2 = new Color(255, 140, 78);
	
	private boolean passwordValid = false;
	
	/**
	 * Startet die Anwendung.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test frame = new test();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Erzeugt die GUI des Kassensystems.
	 */
	public test() {
		setTitle("Kassensoftware");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		//setUndecorated(true);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// Panel definieren
		startPanel = new StartPanel();
		neuesProduktPanel = new ProduktHinzufuegenPanel();
		kategoriePanel = new KategoriePanel();
		einkaufPanel = new EinkaufPanel();
		produktAnzeigenPanel = new ProduktAnzeigenPanel();
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout(0, 0));
		topPanel.setPreferredSize(new Dimension(600, 100));
		topPanel.setBackground(panelBackgroundColor);
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel("Kassensystem");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 30));
		topPanel.add(titleLabel, BorderLayout.CENTER);
		
		JPanel menuPanel = new JPanel();
		contentPane.add(menuPanel, BorderLayout.WEST);
		menuPanel.setBackground(panelBackgroundColor);
		menuPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(250, 350));
		panel.setBackground(panelBackgroundColor);
		panel.setLayout(null);
		menuPanel.add(panel, BorderLayout.NORTH);
		
		// Label der Menüleiste erstellen
		JLabel menuLabel = new JLabel("Navigation");
		menuLabel.setBounds(0, 0, 250, 50);
		panel.add(menuLabel);
		menuLabel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		menuLabel.setForeground(new Color(255, 255, 255));
		menuLabel.setBackground(new Color(0, 0, 128));
		menuLabel.setFont(new Font("Verdana", Font.BOLD, 26));
		menuLabel.setVerticalAlignment(SwingConstants.TOP);
		menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		// Buttons zur Menüleiste hinzufügen
		JButton startButton = new JButton("Start");
		decorateButton(startButton, 0, 60, buttonClickedColor, buttonBackgroundColor);
		panel.add(startButton);
		
		startButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				aktualisieren(startPanel);
			}
		});
		
		JButton neueProdukteButton = new JButton("Neue Produkte");
		decorateButton(neueProdukteButton, 0, 120, buttonClickedColor, buttonBackgroundColor);
		panel.add(neueProdukteButton);
		
		neueProdukteButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				if (!passwordValid) {
					enterWithPassword(neuesProduktPanel);
				}
				else {
					aktualisieren(neuesProduktPanel);
				}
				
				neuesProduktPanel.refresh();
			}
		});
		
		JButton produkteAnzeigenButton = new JButton("Produkte einsehen");
		decorateButton(produkteAnzeigenButton, 0, 180, buttonClickedColor, buttonBackgroundColor);
		panel.add(produkteAnzeigenButton);
		
		produkteAnzeigenButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				if (!passwordValid) {
					enterWithPassword(produktAnzeigenPanel);
				}
				else {
					aktualisieren(produktAnzeigenPanel);
				}
				
				produktAnzeigenPanel.refresh();
			}
		});
		
		JButton kategorieButton = new JButton("Kategorien");
		decorateButton(kategorieButton, 0, 240, buttonClickedColor, buttonBackgroundColor);
		panel.add(kategorieButton);
		
		kategorieButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				if (!passwordValid) {
					enterWithPassword(kategoriePanel);
				}
				else {
					aktualisieren(kategoriePanel);
				}
			}
		});
		
		JButton einkaufButton = new JButton("Einkauf");
		decorateButton(einkaufButton, 0, 300, buttonClickedColor, buttonBackgroundColor);
		panel.add(einkaufButton);
		
		einkaufButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				aktualisieren(einkaufPanel);
			}
		});
		
		JButton beendenButton = new JButton("Beenden");
		decorateButton(beendenButton, 0, 0, buttonBackgroundColor2, buttonClickedColor2);
		beendenButton.setPreferredSize(new Dimension(250, 50));
		menuPanel.add(beendenButton, BorderLayout.SOUTH);
		
		beendenButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				System.exit(0);
			}
		});
	}
	
	
	/** Zeigt <code>panel</code> auf der Oberfläche an.
	 * 
	 * @param panel das Panel, das angezeigt werden soll
	 */
	public void aktualisieren(JPanel panel) {
		// alle aktiven Panels entfernen
		contentPane.remove(startPanel);
		contentPane.remove(neuesProduktPanel);
		contentPane.remove(produktAnzeigenPanel);
		contentPane.remove(kategoriePanel);
		contentPane.remove(einkaufPanel);
		
		// das neue Panel hinzufügen
		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	
	/**
	 * Schützt den Zugriff auf <code>panel</code> durch eine Passwortabfrage,
	 * bevor <code>panel</code> angezeigt wird.
	 * Ist das Passwort falsch, wird dem Benutzer der Zutritt verwährt.
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
