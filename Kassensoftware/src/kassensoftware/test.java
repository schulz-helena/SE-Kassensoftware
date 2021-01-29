package kassensoftware;

//package dem

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


public class test extends JFrame {

	private JPanel contentPane;
	private JPanel startPanel, neuesProduktPanel, kategoriePanel, einkaufPanel, produktAnzeigenPanel;
	
	
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
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		// Panel definieren
		startPanel = new StartPanel();
		neuesProduktPanel = new ProduktHinzufuegenPanel();
		kategoriePanel = new KategoriePanel();
		einkaufPanel = new EinkaufPanel();
		produktAnzeigenPanel = new ProduktAnzeigenPanel();
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		menuPanel.setBackground(new Color(0, 69, 129));
		contentPane.add(menuPanel, BorderLayout.WEST);
		GridBagLayout gbl_menuPanel = new GridBagLayout();
		gbl_menuPanel.columnWidths = new int[] {250, 0};
		gbl_menuPanel.rowHeights = new int[] {60, 48, 48, 48, 48, 48, 48, 48, 0};
		gbl_menuPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_menuPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		menuPanel.setLayout(gbl_menuPanel);
		
		JLabel lblNewLabel = new JLabel("Menü");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setBackground(new Color(0, 0, 128));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.ipady = 20;
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		menuPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		JButton startButton = new JButton("Start");
		startButton.setForeground(new Color(64, 116, 161));
		startButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		GridBagConstraints gbc_startButton = new GridBagConstraints();
		gbc_startButton.fill = GridBagConstraints.BOTH;
		gbc_startButton.gridx = 0;
		gbc_startButton.gridy = 1;
		menuPanel.add(startButton, gbc_startButton);
		
		
		startButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				aktualisieren(startPanel);
			}
		});
		
		JButton neueProdukteButton = new JButton("Neue Produkte");
		neueProdukteButton.setForeground(new Color(64, 116, 161));
		neueProdukteButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		GridBagConstraints gbc_neueProdukteButton = new GridBagConstraints();
		gbc_neueProdukteButton.fill = GridBagConstraints.BOTH;
		gbc_neueProdukteButton.gridx = 0;
		gbc_neueProdukteButton.gridy = 2;
		menuPanel.add(neueProdukteButton, gbc_neueProdukteButton);
		
		neueProdukteButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				enterWithPassword(neuesProduktPanel);
			}
		});
		
		JButton bestehendeProdukteButton = new JButton("Produkte einsehen");
		bestehendeProdukteButton.setForeground(new Color(64, 116, 161));
		bestehendeProdukteButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		GridBagConstraints gbc_bestehendeProdukteButton = new GridBagConstraints();
		gbc_bestehendeProdukteButton.fill = GridBagConstraints.BOTH;
		gbc_bestehendeProdukteButton.gridx = 0;
		gbc_bestehendeProdukteButton.gridy = 3;
		menuPanel.add(bestehendeProdukteButton, gbc_bestehendeProdukteButton);
		
		bestehendeProdukteButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				enterWithPassword(produktAnzeigenPanel);
			}
		});
		
		JButton kategorieButton = new JButton("Kategorien");
		kategorieButton.setForeground(new Color(64, 116, 161));
		kategorieButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		GridBagConstraints gbc_kategorieButton = new GridBagConstraints();
		gbc_kategorieButton.fill = GridBagConstraints.BOTH;
		gbc_kategorieButton.gridx = 0;
		gbc_kategorieButton.gridy = 4;
		menuPanel.add(kategorieButton, gbc_kategorieButton);
		
		kategorieButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				enterWithPassword(kategoriePanel);
			}
		});
		
		JButton einkaufButton = new JButton("Einkauf");
		einkaufButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		einkaufButton.setForeground(new Color(64, 116, 161));
		GridBagConstraints gbc_einkaufButton = new GridBagConstraints();
		gbc_einkaufButton.fill = GridBagConstraints.BOTH;
		gbc_einkaufButton.gridx = 0;
		gbc_einkaufButton.gridy = 5;
		menuPanel.add(einkaufButton, gbc_einkaufButton);
		
		einkaufButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				aktualisieren(einkaufPanel);
			}
		});
		
		JButton beendenButton = new JButton("Beenden");
		beendenButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		beendenButton.setForeground(new Color(64, 116, 161));
		GridBagConstraints gbc_beendenButton = new GridBagConstraints();
		gbc_beendenButton.fill = GridBagConstraints.BOTH;
		gbc_beendenButton.gridx = 0;
		gbc_beendenButton.gridy = 7;
		menuPanel.add(beendenButton, gbc_beendenButton);
		
		beendenButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				System.exit(0);
			}
		});
	}
	
	
	/** <code>panel</code> wird im Content Bereich des Fensters angezeigt.
	 * 
	 * @param panel Panel, das angezeigt werden soll
	 */
	public void aktualisieren(JPanel panel)
	{
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
	 * Schützt den Zugriff auf <code>panel</code>, indem vorher ein Passwort abgefragt wird,
	 * bevor <code>panel</code> angezeigt wird. Ist das Passwort falsch, wird dem Benutzer der
	 * Zutritt verwährt.
	 * 
	 * @param panel Panel, das nach erfolgreicher Eingabe eines Passwortes angezeigt wird
	 */
	public void enterWithPassword(JPanel panel) {
		JPasswordField passwordField = new JPasswordField(10);
		passwordField.requestFocus();
        passwordField.setEchoChar('•');
        JOptionPane.showMessageDialog(null, passwordField, "Passwort eingeben", JOptionPane.OK_OPTION);
        String passwort = "filialleiter"; // Passwort
        String s = String.valueOf(passwordField.getPassword());
        if (s.equals(passwort)) {
        	aktualisieren(panel);
        } else {
        	JOptionPane.showMessageDialog(null, "Das Passwort wurde falsch eingegeben!", "Zutritt verwährt", JOptionPane.ERROR_MESSAGE);
        }
	}
}

