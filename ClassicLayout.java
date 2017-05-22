
/**
 * Classic Mancala Board Images
 * @author ThinkSquad
 */

import java.io.File;
import javax.imageio.*;

public class ClassicLayout extends DefaultLayout{

	/**
	 * Constructor that defines the images used for the board
	 */
	public ClassicLayout() {
		super();
		try{
			setBoardImage(ImageIO.read(new File("mancala.png")));
			setStoneImage(ImageIO.read(new File("pebble.png")));
			setPlayerATurn(ImageIO.read(new File("mancalaTURNA.png")));
			setPlayerBTurn(ImageIO.read(new File("mancalaTURNB.png")));
			setWinA(ImageIO.read(new File("mancalaWINA.png")));
			setWinB(ImageIO.read(new File("mancalaWINB.png")));
			setTieBoard(ImageIO.read(new File("mancalaTIE.png")));
		}
		catch(Exception e){ 
			System.out.println("Files not found for layout and stone images: " + e.getMessage());
		}
	}
	
}
