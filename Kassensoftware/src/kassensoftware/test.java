package kassensoftware;

//package demo

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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class test extends JFrame {

	private JPanel contentPane;
	private JPanel startPanel, neuesProduktPanel, bestehendesProduktPanel, kategoriePanel, einkaufPanel;
	private int active = 0; // aktives Panel: 0: kein Panel; 1: StartPanel; 2: ProduktPanel; ...

	/**
	 * Launch the application.
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
	 * Create the frame.
	 */
	public test() {
		setTitle("Kassensoftware");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 650, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(0, 69, 129));
		contentPane.add(menuPanel, BorderLayout.WEST);
		
		JLabel lblNewLabel = new JLabel("Menü");
		lblNewLabel.setBackground(new Color(0, 0, 128));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton startButton = new JButton("Start");
		startButton.setForeground(new Color(64, 116, 161));
		startButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JButton neueProdukteButton = new JButton("Neue Produkte");
		neueProdukteButton.setForeground(new Color(64, 116, 161));
		neueProdukteButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JButton bestehendeProdukteButton = new JButton("Bestehende Produkte");
		bestehendeProdukteButton.setForeground(new Color(64, 116, 161));
		bestehendeProdukteButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JButton kategorieButton = new JButton("Kategorien");
		kategorieButton.setForeground(new Color(64, 116, 161));
		kategorieButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		
		JButton einkaufButton = new JButton("Einkauf");
		einkaufButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		einkaufButton.setForeground(new Color(64, 116, 161));
		
		JButton sucheButton = new JButton("Suchen");
		sucheButton.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		sucheButton.setForeground(new Color(64, 116, 161));
		
		GroupLayout gl_menuPanel = new GroupLayout(menuPanel);
		gl_menuPanel.setHorizontalGroup(
			gl_menuPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_menuPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(startButton, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addComponent(neueProdukteButton, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addComponent(bestehendeProdukteButton, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addComponent(kategorieButton, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addComponent(einkaufButton, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addComponent(sucheButton, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_menuPanel.setVerticalGroup(
			gl_menuPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(neueProdukteButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bestehendeProdukteButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(kategorieButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(einkaufButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(sucheButton, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED))
		);
		menuPanel.setLayout(gl_menuPanel);
		
		// Ansichten hinzufügen
		startPanel = new StartPanel();
		neuesProduktPanel = new ProduktHinzufuegenPanel();
		bestehendesProduktPanel = new ProduktBearbeitenPanel();
		kategoriePanel = new KategoriePanel();
		einkaufPanel = new EinkaufPanel();
		
		startButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				active = 1;
				aktualisieren(startPanel);
			}
		});
		
		neueProdukteButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				JPasswordField passwordField = new JPasswordField(10);
		        passwordField.setEchoChar('•');
		        JOptionPane.showMessageDialog(null, passwordField, "Bitte Passwort eingeben", JOptionPane.OK_OPTION);
		        String passwort = "filialleiter";
		        String s = String.valueOf(passwordField.getPassword());
		        if (s.equals(passwort)) {
		        	active = 2;
					aktualisieren(neuesProduktPanel);
		        } else {
		        	JOptionPane.showMessageDialog(null, "Falsches Passwort", "Zutritt verwährt", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		
		bestehendeProdukteButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				JPasswordField passwordField = new JPasswordField(10);
		        passwordField.setEchoChar('•');
		        JOptionPane.showMessageDialog(null, passwordField, "Bitte Passwort eingeben", JOptionPane.OK_OPTION);
		        String passwort = "filialleiter";
		        String s = String.valueOf(passwordField.getPassword());
		        if (s.equals(passwort)) {
					aktualisieren(bestehendesProduktPanel);
		        } else {
		        	JOptionPane.showMessageDialog(null, "Falsches Passwort", "Zutritt verwährt", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		
		kategorieButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				JPasswordField passwordField = new JPasswordField(10);
		        passwordField.setEchoChar('•');
		        JOptionPane.showMessageDialog(null, passwordField, "Bitte Passwort eingeben", JOptionPane.OK_OPTION);
		        String passwort = "filialleiter";
		        String s = String.valueOf(passwordField.getPassword());
		        if (s.equals(passwort)) {
		        	aktualisieren(kategoriePanel);
		        } else {
		        	JOptionPane.showMessageDialog(null, "Falsches Passwort", "Zutritt verwährt", JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		
		einkaufButton.addActionListener(new ActionListener ()
		{ 
			public void actionPerformed(ActionEvent e)
			{
				aktualisieren(einkaufPanel);
			}
		});
	}
	
	/** Methode aktualisiert das Menü, indem ein neues Panel sichtbar wird
	 * 
	 * @param panel Panel das angezeigt werden soll
	 */
	public void aktualisieren(JPanel panel)
	{
		// alle aktiven Panels entfernen
		contentPane.remove(startPanel);
		contentPane.remove(neuesProduktPanel);
		contentPane.remove(bestehendesProduktPanel);
		contentPane.remove(kategoriePanel);
		contentPane.remove(einkaufPanel);
		
		// das neue Panel hinzufügen
		contentPane.add(panel, BorderLayout.CENTER);
		contentPane.revalidate();
		contentPane.repaint();
	}
}
