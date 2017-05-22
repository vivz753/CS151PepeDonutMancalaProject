
/**
 * Frame for Mancala game elements to be placed in
 * @author ThinkSquad
 */

import javax.swing.*;


public class MancalaFrame extends JFrame{
	
	private MancalaPanel mPanel;
	
	/**
	 * Constructor
	 * @param mPanel | Panel to be placed into the Frame
	 */
	public MancalaFrame(MancalaPanel mPanel){
		this.mPanel = mPanel;
	}
	
	/**
	 * Play the game and open game window
	 */
	public void play(){
		setContentPane(mPanel);
		mPanel.startGame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setResizable(false);
	}
	
}
