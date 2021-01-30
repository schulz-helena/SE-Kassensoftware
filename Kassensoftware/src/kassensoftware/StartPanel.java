package kassensoftware;


import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;

public class StartPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public StartPanel() {
		this.setSize(450, 400);
		setLayout(new BorderLayout(0, 0));
		setBackground(Color.WHITE);
		
		JLabel welcomeMessageLabel = new JLabel("Kassensystem ist bereit");
		welcomeMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeMessageLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
		welcomeMessageLabel.setForeground(new Color(0, 69, 129));
		add(welcomeMessageLabel, BorderLayout.CENTER);
		
		JLabel versionLabel = new JLabel("Version: 1.0.0");
		versionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		versionLabel.setForeground(Color.DARK_GRAY);
		versionLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		versionLabel.setBorder(new EmptyBorder(0, 0, 10, 10));
		add(versionLabel, BorderLayout.SOUTH);

	}

}
