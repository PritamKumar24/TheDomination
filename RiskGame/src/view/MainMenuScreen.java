package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import MapEditorPackage.MapView;


/**
 * MainMenuScreen Class of Game
 * Contains all commands given
 *  to user once user starts game
 *
 * @author Aditi
 */
public class MainMenuScreen extends JFrame implements ActionListener {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *  The button new game. 
	 */
	private JButton btnNewGame;

	/** The button map editor. */
	private JButton btnMapEditor;

	/** The button exit. */

	private JButton btnExit;

	/**
	 * MainMenuScreen Constructor Instantiates a new main menu screen.
	 */
	public MainMenuScreen() {
		btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(640 / 2 - 50, 50, 100, 30);
		btnNewGame.addActionListener(this);
		add(btnNewGame);
		btnMapEditor = new JButton("Map Editor");
		btnMapEditor.setBounds(640 / 2 - 50, 120, 100, 30);
		btnMapEditor.addActionListener(this);
		add(btnMapEditor);
		btnExit = new JButton("Exit");
		btnExit.setBounds(640 / 2 - 50, 190, 100, 30);
		btnExit.addActionListener(this);
		add(btnExit);
		setTitle("Main Menu");
		setResizable(false);
		setSize(640, 640 / 12 * 9);
		setLayout(null);
		setLocationRelativeTo(null);
		   JFrame frame = new JFrame();
		    frame.getContentPane().setLayout(new BorderLayout());
	        //JLabel label = new JLabel("Enter Command");
	        JTextField textField = new JTextField(20);
	        JPanel CreationPanel = new JPanel();
	        CreationPanel.setLayout(new BorderLayout());

	        JLabel  instructionlabel = new JLabel("Enter Command");

	        CreationPanel.add(instructionlabel,BorderLayout.NORTH);

	        JTextArea  inputUML = new JTextArea("",7,15);
	        JTextArea  output = new JTextArea("",7,15);
	        // very important next 2 lines
	        inputUML.setLineWrap(true);
	        inputUML.setWrapStyleWord(true);
	        // add it to a scrollpane

	        CreationPanel.add(new JScrollPane(inputUML),BorderLayout.CENTER);

	        frame.pack();   // assume the natural size!
	        frame.setVisible(true);
	        frame.getContentPane().add(CreationPanel, BorderLayout.WEST);
	        JScrollPane scrollPane = new JScrollPane(output);

	        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

	        JTextArea   textArea = new JTextArea(10, 20); //Rows and cols to be displayed
	        JScrollPane   scroll = new JScrollPane(textArea);
//	      scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	        frame.add(scroll); //We add the scroll, since the scroll already contains the textArea
	        frame.pack();
	        frame.setVisible(true);
		     addWindowListener(new WindowAdapter() {
			/*
			 * (non-Javadoc)
			 *
			 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
			 */
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}
		@Override
		public void actionPerformed(ActionEvent event) {
			
			if(event.getSource()==btnNewGame)
			{
				NewGameMenu gameMenu= new NewGameMenu();
				gameMenu.setVisible(true);
				dispose();
			}
			
			else if(event.getSource()==btnMapEditor)
			{
				
				MapView mapView = new MapView();
				mapView.setVisible(true);
				dispose();
				
			}
			
			else {
				System.exit(0);
			}
			
		}
	}




