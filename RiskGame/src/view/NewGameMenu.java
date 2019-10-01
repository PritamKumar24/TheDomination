package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


/**
 * The Class NewGameMenuScreen.
 *
 * @author Manpreet Singh
 */
public class NewGameMenu extends JFrame implements ActionListener {

	/** The button select file. */
	private JButton selectButtonFile;

	/** The file path. */
	private String filePath;

	/** The file name. */
	private String fileName;

	/** The text field map. */
	private JTextField textFieldMap;

	/** The players list. */
	private String[] playersList = new String[] { "3", "4", "5" };

	/** The button start game. */
	private JButton buttonStartGame;

	/** The combo box select player. */
	private JComboBox<String> comboBoxSelectPlayer;

	/** The no of players. */
	private String noOfPlayers;
	
	
	
	public NewGameMenu() {
		
		
		JLabel selectMap= new JLabel();
		selectMap.setText("Select Map");
		selectMap.setBounds(640 / 2 - 150, 50, 100, 30);
		add(selectMap);
		
		textFieldMap = new JTextField();
		textFieldMap.setBounds(640 / 2 - 30, 50, 100, 30);
		add(textFieldMap);

		selectButtonFile = new JButton();
		selectButtonFile.setText("Select File");
		selectButtonFile.setBounds(640 / 2 + 90, 50, 100, 30);
		selectButtonFile.addActionListener(this);
		add(selectButtonFile);
		
		JLabel selectPlayer = new JLabel();
		selectPlayer.setText("Select Players");
		selectPlayer.setBounds(640 / 2 - 150, 100, 100, 30);
		add(selectPlayer);
		
		comboBoxSelectPlayer = new JComboBox<>(playersList);
		comboBoxSelectPlayer.setBounds(640 / 2 - 30, 100, 100, 30);
		add(comboBoxSelectPlayer);
		
		buttonStartGame = new JButton("Start Game");
		buttonStartGame.setBounds(640 / 2 - 50, (640/12)*9 - 200, 100, 30);
		buttonStartGame.addActionListener(this);
		add(buttonStartGame);
		
		setTitle("New Game Menu");
		setResizable(false);
		setSize(640,(640/12)*9);
		setLayout(null);
		setLocationRelativeTo(null);
		
		
		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
