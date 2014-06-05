package scratch;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import chess.model.board.Location;
import chess.model.pieces.ChessPiece;

public class ScratchCode {

	public ScratchCode() {
		
//		
//		String regex = "^(?<placeCommand>[kqbnrp][dl][a-h][1-8])|(?<moveCommand>([a-h][1-8] [a-h][1-8]\\*?)( [a-h][1-8] [a-h][1-8])?)&";
//		String regex2 = "^(?<placeCommand>(?<piece>[kqbnrp])(?<color>[dl])(?<location>[a-h][1-8]))|(?<moveCommand>(\\k<location> \\k<location>\\*?)( \\k<location> \\k<location>)?)&";
//		String text1 = "h1 g3";
//		
//		Pattern pattern = Pattern.compile(regex2, Pattern.CASE_INSENSITIVE);
//		Matcher matcher = pattern.matcher(text1);
//		
//		Boolean matches = matcher.matches();
//		System.out.println(matcher.group("placeCommand"));
//		System.out.println(matcher.group("moveCommand"));
//		System.out.println(matcher.group("piece"));
//		
//		
//		
//		System.out.println(matches);
		
//		
//		String[][] boardArray = new String[8][8];
//		
//		for (int i = 7; i >= 0; i--) {
//			for (int j = 0; j < boardArray[i].length; j++) {
//				boardArray[i][j] = ""+i+j;
//				if (j == 7) {
//					System.out.println(boardArray[i][j]);
//				}
//				else {
//					System.out.print(boardArray[i][j]);
//				}
//				
//			}
//		}
//		
//		char a = 'a';
//		System.out.println(a);
//		System.out.println(Character.toUpperCase(a));
//		
//		System.out.println(boardArray[0][0]);
//		
//		System.out.println((int)'a'-97);
//		System.out.println((int)'b'-97);
//		
//		System.out.println(boardArray[0][1]);
		
//		ChessPiece[][] test = new ChessPiece[8][8];
//		System.out.println(test[0][0]);
		
//		HashSet<Integer> tempSet = new HashSet<Integer>();
//		tempSet.add(1);
//		tempSet.add(2);
//		tempSet.add(1);
//		tempSet.add(1);
//		
//		if(tempSet.size() == 1) {
//			System.out.println("this works?");
//		}
//		else {
//			System.out.println("nope");
//		}
		
//		Location a = new Location(0,0);
//		Location b = new Location(0,1);
//		Location c = new Location(0,0);
//		
//		System.out.println(a.equals(b));
//		System.out.println(a.equals(c));
//		System.out.println(a.hashCode() == b.hashCode());
//		System.out.println(a.hashCode() == c.hashCode());
//		System.out.println(a.hashCode());
//		System.out.println(b.hashCode());
//		System.out.println(new Location(7,7).hashCode());
	}
}
