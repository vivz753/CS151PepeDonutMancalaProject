

/**
 * Interface for Mancala GUI
 * @author ThinkSquad
 */

import java.awt.*;

public interface Layout{
	
	/**
	 * Redraw the image using the given board
	 * @param g - the graphics 
	 * @param mBoard the given board 
	 */
	public void redraw(Graphics g, MancalaBoard mBoard);
	
	/**
	 * Draws the stones in the appropriate spot
	 * @param g - the graphics 
	 * @param mBoard -  the board
	 */
	public void drawStones(Graphics g, MancalaBoard mBoard);
	
	/**
	 * Changes the board's image to reflect its current State
	 * @param player | current active player / winning player (2 if a tie)
	 * @param win | whether or not the game has been won
	 */
	public void changeBoardState(int player, boolean win);
}