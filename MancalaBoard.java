
/**
 * Class that contains the Mancala Board's data
 * @author ThinkSquad
 */

public class MancalaBoard {

	public static final int DEFAULT_PLAYERS = 2;
	public static final int DEFAULT_PITS_PER_PLAYER = 6;

	private int[][] boardArray;
	private int[][] undoArray;
	private int pitsPerPlayer;

	/**
	 * Constructor
	 */
	public MancalaBoard() {
		boardArray = new int[DEFAULT_PLAYERS][DEFAULT_PITS_PER_PLAYER + 1];
		undoArray = new int[boardArray.length][boardArray[0].length];
		pitsPerPlayer = boardArray[0].length - 1;
	}

	public MancalaBoard(int players, int pits) {
		boardArray = new int[players][pits + 1];
		undoArray = new int[boardArray.length][boardArray[0].length];
		pitsPerPlayer = boardArray[0].length - 1;
	}

	/**
	 * Fills the boards with the given value (excluding mancalas)
	 */
	public void fillBoard(int fill) {
		for (int i = 0; i < boardArray.length; i++)
			for (int j = 0; j < pitsPerPlayer; j++)
				boardArray[i][j] = fill;
		saveBoardState();
	}

	/**
	 * Fills a specific row for a player
	 * @param player | must be 0 or 1
	 */
	public void fillRow(int player, int val) {
		for (int j = 0; j < pitsPerPlayer; j++) {
			boardArray[player][j] = val;
		}
	}

	/**
	 * Distribute the stones as needed Do not pass the index of the mancala,
	 * player should not be able to select it
	 * 
	 * @param player | player that is making the move (0 or 1)
	 * @param index | pit that was clicked
	 * @return | if the turn must be swapped
	 */
	public boolean move(int player, int pit) {
		if(boardArray[player][pit] == 0) return true;
		saveBoardState();
		int pebbles = boardArray[player][pit];
		boardArray[player][pit] = 0;
		int playerI = player; // player iterator
		for (pit++; pebbles > 0; pit++) {
			if (pit > pitsPerPlayer) { // flip sides of the board
				playerI = 1 - playerI;
				pit = -1;
			} else {
				if (pit != pitsPerPlayer || playerI == player) { // add pebble, skips opponenet's mancala
					boardArray[playerI][pit]++;
					pebbles--;
					if (pit != pitsPerPlayer && boardArray[playerI][pit] == 1 && playerI == player && pebbles == 0) // steal from opponent
						steal(playerI, pit);
				}
			}
		}
		return (pit - 1) == pitsPerPlayer;
	}

	/**
	 * Steal contents of opposing mancala and add both to own mancala
	 * 
	 * @param player | the player that is stealing
	 * @param pit | pit of the player
	 */
	public void steal(int player, int pit) {
		int oppPlayer = 1 - player;
		int oppPit = (pitsPerPlayer - 1) - pit;
		boardArray[player][pit] += boardArray[oppPlayer][oppPit];
		boardArray[player][pitsPerPlayer] += boardArray[player][pit];
		boardArray[player][pit] = 0;
		boardArray[oppPlayer][oppPit] = 0;
	}

	/**
	 * Runs the game over procedure if the game is over
	 * 
	 * @return whether the game is over
	 */
	public boolean gameOver() {
		for (int i = 0; i < boardArray.length; i++) {
			if (sideClear(i)) {
				int oppPlayer = 1 - i;
				sumRow(oppPlayer);
				fillRow(oppPlayer, 0);
				return true;
			}
		}
		return false;
	}

	/**
	 * Check's whether a side of the board is clear
	 * 
	 * @param player
	 *            | number 0 or 1
	 * @return whether or not the player's board is clear
	 */
	private boolean sideClear(int player) {
		for (int i = 0; i < pitsPerPlayer; i++)
			if (boardArray[player][i] != 0)
				return false;
		return true;
	}

	/**
	 * Sums for a row
	 */
	private void sumRow(int row) {
		for (int j = 0; j < pitsPerPlayer; j++) {
			boardArray[row][pitsPerPlayer] += boardArray[row][j];
		}
	}

	/**
	 * Save's the board state to undoArray
	 */
	public void saveBoardState() {
		for (int i = 0; i < boardArray.length; i++)
			for (int j = 0; j < boardArray[0].length; j++)
				undoArray[i][j] = boardArray[i][j];
	}
	

	/**
	 * Undo's the last board change
	 * @return | if a change was made
	 */
	public boolean undo() {
		if(!sameBoard()){
			for (int i = 0; i < boardArray.length; i++)
				for (int j = 0; j < boardArray[0].length; j++)
					boardArray[i][j] = undoArray[i][j];
			return true;
		} return false;
	}
	
	/**
	 * @return | if the undoArray and boardArray are the same
	 */
	private boolean sameBoard(){
		for (int i = 0; i < boardArray.length; i++)
			for (int j = 0; j < boardArray[0].length; j++)
				if (boardArray[i][j] != undoArray[i][j])
					return false;
		return true;
	}

	/**
	 * @return | String of the current board
	 */
	public String toString() {
		return boardToString(boardArray);
	}

	/**
	 * Prints the given board as a String. Player1 is at the bottom, from left
	 * to right. Player2 is at the top, from right to left.
	 */
	public String boardToString(int[][] board) {
		String text = "-----------------\n";
		for (int i = boardArray.length - 1; i >= 0; i--) {
			text += "   ";
			if (i % 2 == 1) { //second player
				for (int j = pitsPerPlayer - 1; j >= 0; j--)
					text += board[i][j] + " ";
				text += "\n"; //print mancalas
				for (int j = boardArray.length - 1; j >= 0; j--) {
					text += board[j][pitsPerPlayer] + "               ";
				}
			} else { //first player
				for (int j = 0; j < pitsPerPlayer; j++)
					text += board[i][j] + " ";
			}
			text += "\n";
		}
		return text + "-----------------";
	}

	/**
	 * @return | index of the winner, returns 2 if a tie
	 */
	public int getWinner(){
		int[] mancalas = getMancalas();
		if(mancalas[0] > mancalas[1]) return 0;
		else if(mancalas[1] > mancalas[0]) return 1;
		else return 2;
	}

	
	/**
	 * @return | The values in the Mancala's as a 2D array
	 */
	public int[] getMancalas() {
		int[] mancalas = new int[2];
		for (int i = 0; i < boardArray.length; i++)
			mancalas[i] = boardArray[i][pitsPerPlayer];
		return mancalas;
	}
	
	/**
	 * @return | Returns the array of the Board
	 */
	public int[][] getBoardArray() {
		return boardArray;
	}

	/**
	 * @return | Returns the array of the Board before the last move
	 */
	public int[][] getUndoArray() {
		return undoArray;
	}


}
