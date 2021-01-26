import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JTextArea;

public class EinkaufPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public EinkaufPanel() {
		setLayout(new GridLayout(1, 0, 0, 0));
		
		JTextArea txtrTestTestTest = new JTextArea();
		txtrTestTestTest.setText("Test Test Test");
		add(txtrTestTestTest);

	}

}
