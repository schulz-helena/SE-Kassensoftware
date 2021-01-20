import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class StartPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public StartPanel() {
		this.setSize(450, 400);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel titelLabel = new JLabel("Kassensoftware");
		titelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titelLabel.setFont(new Font("Lucida Grande", Font.BOLD, 30));
		titelLabel.setForeground(new Color(64, 116, 161));
		add(titelLabel);
		
		JLabel label2 = new JLabel("Wilkommen im Kassensystem!");
		label2.setVerticalAlignment(SwingConstants.TOP);
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label2.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		label2.setForeground(new Color(64, 116, 161));
		add(label2);

	}

}
