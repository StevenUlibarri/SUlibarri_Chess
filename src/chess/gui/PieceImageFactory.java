package chess.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class PieceImageFactory {
	
	private static final HashMap<String, IImageFactory> imageMap = new HashMap<String, IImageFactory>();

	static {
		try {
			BufferedImage whitePawn = ImageIO.read(new File("ChessIcons/White P.png"));
			BufferedImage whiteRook = ImageIO.read(new File("ChessIcons/White R.png"));
			BufferedImage whiteBishop = ImageIO.read(new File("ChessIcons/White B.png"));
			BufferedImage whiteKnight = ImageIO.read(new File("ChessIcons/White N.png"));
			BufferedImage whiteKing = ImageIO.read(new File("ChessIcons/White K.png"));
			BufferedImage whiteQueen = ImageIO.read(new File("ChessIcons/White Q.png"));
			BufferedImage blackPawn = ImageIO.read(new File("ChessIcons/Black P.png"));
			BufferedImage blackRook = ImageIO.read(new File("ChessIcons/Black R.png"));
			BufferedImage blackBishop = ImageIO.read(new File("ChessIcons/Black B.png"));
			BufferedImage blackKnight = ImageIO.read(new File("ChessIcons/Black N.png"));
			BufferedImage blackKing = ImageIO.read(new File("ChessIcons/Black K.png"));
			BufferedImage blackQueen = ImageIO.read(new File("ChessIcons/Black Q.png"));
			
			imageMap.put("Pawn", ((color) -> {return (color)? whitePawn:blackPawn;}));
			imageMap.put("Rook", ((color) -> {return (color)? whiteRook:blackRook;}));
			imageMap.put("Knight", ((color) -> {return (color)? whiteKnight:blackKnight;}));
			imageMap.put("Bishop", ((color) -> {return (color)? whiteBishop:blackBishop;}));
			imageMap.put("Queen", ((color) -> {return (color)? whiteQueen:blackQueen;}));
			imageMap.put("King", ((color) -> {return (color)? whiteKing:blackKing;}));
		}
		catch (Exception e) {
			
		}
	}
	
	public static BufferedImage getImage(String pieceString, boolean color) {
		return (imageMap.get(pieceString)).getImage(color);
	}
	
	private interface IImageFactory {
		public BufferedImage getImage(boolean color);
	}
}
