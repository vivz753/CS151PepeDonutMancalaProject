
/**
 * Launcher window to Play Mancala and change settings
 * @author ThinkSquad
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class MancalaLauncher extends JFrame implements ActionListener{
	
	private MancalaBoard mBoard;
	private BoardNames selectedBoard;
	private Layout layout;
	private int fillAmount;

	/**
	 * Default Constructor
	 */
	public MancalaLauncher() {
		super("Mancala Launcher");
		mBoard = new MancalaBoard();
		layout = new ClassicLayout();
		selectedBoard = BoardNames.Classic;
		fillAmount = 3;
	}

	/**
	 * Opens a window containing button to toggle elements of the game
	 */
	public void launch(){
		//Declare Options Buttons
		JRadioButton b1 = new JRadioButton("Classic");
		b1.setActionCommand("b1");
		b1.setSelected(true);
		
		JRadioButton b2 = new JRadioButton("Fun");
		b2.setActionCommand("b2");
		
		JRadioButton n1 = new JRadioButton("3");
		n1.setActionCommand("n1");
		n1.setSelected(true);
		
		JRadioButton n2 = new JRadioButton("4");
		n2.setActionCommand("n2");
			
		//Make Button Groups
		ButtonGroup boards = new ButtonGroup();
		boards.add(b1);
		boards.add(b2);
			
		ButtonGroup fills = new ButtonGroup();
		fills.add(n1);
		fills.add(n2);
			
		//Add Listener
		b1.addActionListener(this);
		b2.addActionListener(this);
		n1.addActionListener(this);
		n2.addActionListener(this);
		
		//Launch Button
		JButton launchButton = new JButton("Play!");
		launchButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				if(selectedBoard == BoardNames.Classic) 
					layout = new ClassicLayout();
				else
					layout = new FunLayout();
				mBoard.fillBoard(fillAmount);
				MancalaPanel mPanel = new MancalaPanel(layout, mBoard);
				MancalaFrame mancalaFrame = new MancalaFrame(mPanel);
				mancalaFrame.play();
				System.out.println("Board: " + selectedBoard + "\n" + mBoard);
				dispose();
			}
		});
		
		//Panel
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		JLabel title = new JLabel("Select Mancala Board Settings: ");
		panel.add(title);
		panel.add(b1);
		panel.add(b2);
		panel.add(n1);
		panel.add(n2);
		panel.add(launchButton);
		
		//Add to Frame
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(true);
	}
		
	/**
	 * Parses the button that was pressed and changes the appropriate value
	 */
	public void actionPerformed(ActionEvent e){
	    if ("b".equals(e.getActionCommand().substring(0,1))) {
	        if(e.getActionCommand().substring(1).equals("1"))
	        	selectedBoard = BoardNames.Classic;
	        else
	        	selectedBoard = BoardNames.Fun;
	    } else {
	    	if(e.getActionCommand().substring(1).equals("1"))
	        	fillAmount = 3;
	        else
	        	fillAmount = 4;
	    }
	}

}