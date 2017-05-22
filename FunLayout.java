
/**
 * Fun Mancala Board Images
 * @author ThinkSquad
 */


import java.io.File;
import javax.imageio.ImageIO;

public class FunLayout extends DefaultLayout{

	/**
	 * Constructor that defines the images used for the board
	 */

	public FunLayout() {
		super();
		try{
			setBoardImage(ImageIO.read(new File("funMancala.png")));
			setStoneImage(ImageIO.read(new File("funPebble.png")));
			setPlayerATurn(ImageIO.read(new File("funmancalaTURNA.png")));
			setPlayerBTurn(ImageIO.read(new File("funmancalaTURNB.png")));
			setWinA(ImageIO.read(new File("funmancalaWINA.png")));
			setWinB(ImageIO.read(new File("funmancalaWINB.png")));
			setTieBoard(ImageIO.read(new File("funmancalaTIE.png")));
		}
		catch(Exception e){ 
			System.out.println("Files not found for layout and stone images: " + e.getMessage());
		}
	}

}