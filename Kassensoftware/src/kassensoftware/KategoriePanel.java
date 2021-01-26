import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Font;

public class KategoriePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public KategoriePanel() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JTextArea txtrTestTestTest = new JTextArea();
		txtrTestTestTest.setFont(new Font("Verdana", Font.BOLD, 17));
		txtrTestTestTest.setText("Test Test Test");
		add(txtrTestTestTest);

	}

}
