package MapEditorPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import view.MainMenuScreen;



/**
 * The Class MapView.
 * 
 * @author Manpreet Singh
 */
public class MapView extends JFrame implements ActionListener {

	/** The button new map. */
	private JButton btnNewMap;

	/** The button load map. */
	private JButton btnLoadMap;

	/** The file name. */
	private String filePath = "", fileName = "";
	
	
	public MapView() {
		
		setResizable(false);
		setSize(640,(640/12)*9);
		setLayout(null);
		setLocationRelativeTo(null);
		
		btnNewMap = new JButton("New Map");
		btnNewMap.setBounds(640 / 2 - 50, 50, 150, 30);
		btnNewMap.addActionListener(this);
		add(btnNewMap);
		
		
		btnLoadMap = new JButton("Load Existing Map");
		btnLoadMap.setBounds(640 / 2 - 50, 120, 150, 30);
		btnLoadMap.addActionListener(this);
		add(btnLoadMap);
		
		addWindowListener(new WindowAdapter() {
			/*
			 * (non-Javadoc)
			 * 
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				MainMenuScreen initialScreen = new MainMenuScreen();
				initialScreen.setVisible(true);
			}
		});
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
