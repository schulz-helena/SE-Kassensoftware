import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Kassensystem extends Composite {
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;

	
	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		Kassensystem kasse1 = new Kassensystem(shell, SWT.None);
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Kassensystem(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(3, false));
		
		Label lblProdukteigenschaften = new Label(this, SWT.NONE);
		lblProdukteigenschaften.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		lblProdukteigenschaften.setText("Produkteigenschaften");
		new Label(this, SWT.NONE);
		
		Label lblProduktname = new Label(this, SWT.NONE);
		lblProduktname.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblProduktname.setText("Produktname");
		
		text = new Text(this, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("EAN/PLU");
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblGewicht = new Label(this, SWT.NONE);
		lblGewicht.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblGewicht.setText("Gewicht");
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Combo combo = new Combo(this, SWT.NONE);
		combo.setItems(new String[] {"Kg", "L"});
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblPreis = new Label(this, SWT.NONE);
		lblPreis.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPreis.setText("Preis");
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblEuro = new Label(this, SWT.NONE);
		lblEuro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblEuro.setText("EURO");
		
		Label lblGrundpreis = new Label(this, SWT.NONE);
		lblGrundpreis.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblGrundpreis.setText("Grundpreis");
		
		TextViewer textViewer = new TextViewer(this, SWT.BORDER);
		StyledText styledText = textViewer.getTextWidget();
		GridData gd_styledText = new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1);
		gd_styledText.heightHint = 39;
		styledText.setLayoutData(gd_styledText);
		
		Label lblAnzahl = new Label(this, SWT.NONE);
		lblAnzahl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAnzahl.setText("Anzahl");
		
		text_4 = new Text(this, SWT.BORDER);
		text_4.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Label lblKategorie = new Label(this, SWT.NONE);
		lblKategorie.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblKategorie.setText("Kategorie");
		
		text_5 = new Text(this, SWT.BORDER);
		text_5.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, true, true, 3, 1));
		
		Button btnOk = new Button(composite, SWT.NONE);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String result;
				float w = Float.parseFloat(lblGewicht.getText());
				float p = Float.parseFloat(lblPreis.getText());
				result = Float.toString(w*p);
				textViewer.setInput(result);
			}
		});
		btnOk.setBounds(52, 10, 105, 35);
		btnOk.setText("OK");
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(249, 10, 105, 35);
		btnCancel.setText("Cancel");

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
