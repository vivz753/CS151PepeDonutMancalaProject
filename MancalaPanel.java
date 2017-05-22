

/**
 * Panel that controls player interaction with the game
 * @author ThinkSquad
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class MancalaPanel extends JPanel implements MouseListener{
	
	//GUI Constants
	public static final int WIDTH = 1250;
	public static final int HEIGHT = 500;
	private static final int XSTARTA = 200;
	private static final int XSTARTB = 950;
	private static final int YSTARTA = 300;
	private static final int YSTARTB = 100;
	private static final int XGAP = 150;
	private static final int SIZE = 100;
	private static final int UNDOX = 200;
	private static final int UNDOY = 450;
	private static final int UNDO_WIDTH = 100;
	private static final int UNDO_HEIGHT = 35;
	
	//Game Constants
	public static final int MAX_UNDOS = 3;

	private int player, undosA, undosB;
	private boolean extraTurn;
	private Layout layout;
	private MancalaBoard mBoard;
	

	/**
	 * Constructor
	 * @param layout | Layout containing board images to be shown
	 * @param mBoard | MancalaBoard containing values of the board
	 */
	public MancalaPanel(Layout layout, MancalaBoard mBoard) {
		this.layout = layout;
		this.mBoard = mBoard;
		player = 1;
		undosA = 0;
		undosB = 0;
		setSize(WIDTH, HEIGHT);
		addMouseListener(this);
	}
	
	/**
	 * Starts the game
	 */
	public void startGame(){
		playerTurn();
		repaint();
	}

	/**
	 * Sets the panel size
	 */
	public void setSize(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}

	@Override
	/**
	 * Draws the GUI
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		layout.redraw(g, mBoard);
	}

	/**
	 * Checks which Button was pressed and changes the MancalaBoard's values accordingly
	 */
	public void mouseClicked(MouseEvent e) { 
		int xSquare = 0;
		int ySquare = 0;
		int xMouse = e.getX();
		int yMouse = e.getY(); 
		for (int j = 0; j < MancalaBoard.DEFAULT_PLAYERS; j++){ 
			if (j % 2 == 0) { //player 1 side check
				ySquare = YSTARTA;
				for (int i = 0; i < MancalaBoard.DEFAULT_PITS_PER_PLAYER; i++){
					xSquare = XSTARTA + (i * XGAP);
					if(xMouse >= xSquare && xMouse <= xSquare + SIZE && yMouse >= ySquare && yMouse <= ySquare + SIZE){
						checkMove(j, i);
						undosB = 0;
						return;
					}
				}
			} else{ //player 2 side check
				ySquare = YSTARTB;
				for (int i = 0; i < MancalaBoard.DEFAULT_PITS_PER_PLAYER; i++){
					xSquare = XSTARTB - (i * XGAP);
					if(xMouse >= xSquare && xMouse <= xSquare + SIZE && yMouse >= ySquare && yMouse <= ySquare + SIZE){
						checkMove(j, i);
						undosA = 0;
						return;
					}
				}
			}
		}//undo button
		if(xMouse >= UNDOX && xMouse <= UNDOX + UNDO_WIDTH && yMouse >= UNDOY && yMouse <= UNDOY + UNDO_HEIGHT && !mBoard.gameOver()){
			if(mBoard.undo() && undosA < MAX_UNDOS && undosB < MAX_UNDOS){
				repaint();
				if(player == 0) undosA++;
				else undosB++;
			}
			if(!extraTurn)
				playerTurn();
			printDebugBoard();
			
		}
	}
	
	/**
	 * Checks the validity of the players move and changes the turn if needed
	 * @param player | player attempting the move
	 * @param pit | pit that was clicked
	 */
	public void checkMove(int player, int pit){
		extraTurn = false;
		if(this.player == player){
			extraTurn = mBoard.move(player, pit);
			if(mBoard.gameOver())
				layout.changeBoardState(mBoard.getWinner(), true);
			else if(!extraTurn) playerTurn();
			repaint();
			printDebugBoard();
		}
		
	}
	
	/**
	 * Changes the active player
	 */
	public void playerTurn(){
		player = 1 - player;
		layout.changeBoardState(player, false);
	}
	
	/**
	 * Prints the MancalaBoard as a string
	 */
	public void printDebugBoard(){
		System.out.println(mBoard);
		System.out.println(player + " | " + extraTurn);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
