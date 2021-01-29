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
		
		JLabel titleLabel = new JLabel("Kassensoftware");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Verdana", Font.BOLD, 30));
		titleLabel.setForeground(new Color(0, 69, 129));
		titleLabel.setBorder(new EmptyBorder(50, 0, 0, 0));
		add(titleLabel, BorderLayout.NORTH);
		
		JLabel label2 = new JLabel("Kassensystem ist bereit");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(new Font("Verdana", Font.PLAIN, 20));
		label2.setForeground(new Color(0, 69, 129));
		add(label2, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Version: 1.0.0");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblNewLabel.setBorder(new EmptyBorder(0, 0, 10, 10));
		add(lblNewLabel, BorderLayout.SOUTH);

	}

}
