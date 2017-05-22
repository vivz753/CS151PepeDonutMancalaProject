

/**
 * Default Layout implementation
 * @author ThinkSquad
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DefaultLayout implements Layout{

	private BufferedImage boardImage, stoneImage, playerATurn, playerBTurn, winA, winB, tieBoard;
	
	/**
	 * Default Constructor, fill instance variables as null
	 */
	public DefaultLayout(){
		boardImage = null;
		stoneImage = null; 
		playerATurn = null;
		playerBTurn = null;
		winA = null;
		winB = null;
		tieBoard = null;
	}
	
	/**
	 * Method that redraws the board and calls a stone method that draw the stones
	 * @param g graphics
	 * @param MancalaBoard the mancala board to redraw
	 */
	public void redraw(Graphics g, MancalaBoard mBoard) {
		g.drawImage(boardImage, 0, 0, null);
		drawStones(g, mBoard);
	}
	
	/**
	 * Changes the displayed image to the appropriate one
	 */
	public void changeBoardState(int player, boolean win){
		if(win){
			if(player == 0) boardImage = winA;
			else if(player == 1) boardImage = winB;
			else boardImage = tieBoard;
		}else{
			if(player == 0) boardImage = playerATurn;
			else boardImage = playerBTurn;
		}
	}
	
	/**
	 * Method that draws the stones in appropriate spot
	 * @param g graphics
	 * @param MancalaBoard the mancala board to redraw
	 */
	public void drawStones(Graphics g, MancalaBoard mBoard) {

		Random random = new Random();
		
		int margin = 20;
		int currentXpit = 200;
		int currentYpit = 300;
		int dX = 150;
		int dY = 200;

		//draw stones in appropiate pit
		for (int i = 0; i < mBoard.getBoardArray().length; i++) {
			for (int j = 0; j < mBoard.getBoardArray()[i].length; j++) {
				for (int s = mBoard.getBoardArray()[i][j]; s > 0; s--) {
					if(i == 0 && j == 6)
						g.drawImage(stoneImage, 1100 + random.nextInt(100 - margin),
								100 + random.nextInt( 300 - margin), null);
					else if(i == 1 && j == 6)
						g.drawImage(stoneImage, 50 + random.nextInt(100 - margin),
								100 + random.nextInt( 300 - margin), null);
					else
						g.drawImage(stoneImage, currentXpit + random.nextInt(100 - margin),
								currentYpit + random.nextInt(100 - margin), null);
				}
				if(i == 0)
					currentXpit += dX;
				if(i == 1)
					currentXpit -= dX;
			}
			currentYpit -= dY;
			currentXpit = 950;
		}

	}
	
	/**
	 * Mutator
	 * @param i | image to change to
	 */
	public void setBoardImage(BufferedImage i){
		boardImage = i;
	}
	
	/**
	 * Mutator
	 * @param i | image to change to
	 */
	public void setStoneImage(BufferedImage i){
		stoneImage = i;
	}
	
	/**
	 * Mutator
	 * @param i | image to change to
	 */
	public void setPlayerATurn(BufferedImage i){
		playerATurn = i;
	}
	
	/**
	 * Mutator
	 * @param i | image to change to
	 */
	public void setPlayerBTurn(BufferedImage i){
		playerBTurn = i;
	}
	
	/**
	 * Mutator
	 * @param i | image to change to
	 */
	public void setWinA(BufferedImage i){
		winA = i;
	}
	
	/**
	 * Mutator
	 * @param i | image to change to
	 */
	public void setWinB(BufferedImage i){
		winB = i;
	}
	
	/**
	 * Mutator
	 * @param i | image to change to
	 */
	public void setTieBoard(BufferedImage i){
		tieBoard = i;
	}
	
}
