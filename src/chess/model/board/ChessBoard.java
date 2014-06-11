package chess.model.board;

import java.util.ArrayList;

import chess.model.pieces.ChessPiece;
import chess.model.pieces.King;

public class ChessBoard {
	
	private ChessPiece[][] boardArray;
	public static final int ROWS = 8;
	public static final int COLUMNS = 8;
	public static final int MIN_INDEX = 0;
	public static final int MAX_INDEX = 7;
	
	private boolean lightKingInCheck = false;
	private boolean darkKingInCheck = false;
	private boolean mate = false;
	private boolean isLightTurn = true;
	
	public ChessBoard() {
		boardArray  = new ChessPiece[COLUMNS][ROWS];
	}
	
	public ChessBoard(ChessBoard boardToCopy) {
		boardArray = new ChessPiece[COLUMNS][ROWS];
		
		for (int i = 0; i <= MAX_INDEX; i++) {
			for (int j = 0; j <= MAX_INDEX; j++) {
				boardArray[i][j] = boardToCopy.getBoardArray()[i][j];
			}
		}
	}
	
	public void swapTurn() {
		isLightTurn = !isLightTurn;
	}
	
	public boolean getTurn() {
		return this.isLightTurn;
	}

	public void displayBoard() {
		
		for (int i = MAX_INDEX; i >= MIN_INDEX; i--) {
			
			System.out.print(i+1 + " ");
			for (int j = MIN_INDEX; j < boardArray[i].length; j++) {
				
				String s = (boardArray[j][i] == null)? "-" : boardArray[j][i].toString();

				if (j == 7) {
					System.out.println("| " + s + " |");
				}
				else {
					System.out.print("| " + s + " ");
				}
				
			}
		}
		System.out.println("    A   B   C   D   E   F   G   H");
		System.out.println();
	}
	
	public ChessPiece[][] getPieceArray() {
		return this.boardArray;
	}
	
	public ChessPiece getPieceAt(Location l) {
		return boardArray[l.getColumnIndex()][l.getRowIndex()];
	}
	
	public ChessPiece removePieceAt(Location l) {
		ChessPiece c = boardArray[l.getColumnIndex()][l.getRowIndex()];
		boardArray[l.getColumnIndex()][l.getRowIndex()] = null;
		return c;	
	}
	
	public void setPieceAt(ChessPiece p, Location l) {
		boardArray[l.getColumnIndex()][l.getRowIndex()] = p;
	}
	
	protected ChessPiece[][] getBoardArray() {
		return boardArray;
	}

	public boolean isMate() {
		return mate;
	}

	public void setMate(boolean mate) {
		this.mate = mate;
	}
	
	public void setKingInCheck(boolean color, boolean check) {
		if(color) {
			lightKingInCheck = check;
		}
		else {
			darkKingInCheck = check;
		}
	}
	
	public boolean isKinginCheck(boolean color) {
		if(color) {
			return lightKingInCheck;
		}
		else {
			return darkKingInCheck;
		}
	}
	
	public void upDateCheckStatus(boolean currentTurn) {
		this.setKingInCheck(!currentTurn, this.locationAttacked(this.getKingLocation(!currentTurn), currentTurn));
	}

	public boolean locationAttacked(Location l, boolean attackingColor) {
		boolean attacked = false;
		
		for (int i = 0; i <= ChessBoard.MAX_INDEX && !attacked; i++) {
			for (int j = 0; j <= ChessBoard.MAX_INDEX && !attacked; j++) {
				Location tempLocation = new Location(i,j);
				ChessPiece tempPiece = this.getPieceAt(tempLocation);
				
				if(tempPiece != null && tempPiece.isLight() == attackingColor) {
					ArrayList<Location> validMoves = tempPiece.getValidMoves(tempLocation, this);
					if (validMoves.contains(l)) {
						attacked = true;
					}
				}
			}
		}
		return attacked;	
	}
	
	public Location getKingLocation(boolean color) {
		boolean found = false;
		
		Location kingLocation = null;
		
		for (int i = 0; i <= ChessBoard.MAX_INDEX && !found; i++) {
			for (int j = 0; j <= ChessBoard.MAX_INDEX && !found; j++) {
				ChessPiece piece = this.getPieceAt(new Location(i,j));
				if(piece instanceof King && piece.isLight() == color) {
					found = true;
					kingLocation= new Location(i,j);
				}
			}
		}
		return kingLocation;
	}
	
	public boolean moveAllowsFriendlyCheck(Location begin, Location end, boolean turn) {
		ChessBoard tempBoard = new ChessBoard(this);
		tempBoard.setPieceAt(tempBoard.removePieceAt(begin), end);
		
		return tempBoard.locationAttacked(tempBoard.getKingLocation(turn), !turn);
	}

	public boolean isMate(boolean color) {

		return getPiecesWithValidMoves(color).isEmpty();
	}
	
	public ArrayList<Location> getPiecesWithValidMoves(boolean color) {
		ArrayList<Location> movablePieces = new ArrayList<Location>();
		
		for(int i = 0; i <= ChessBoard.MAX_INDEX; i++) {
			for(int j = 0; j <= ChessBoard.MAX_INDEX; j++) {
				Location temp = new Location(i,j);
				ChessPiece p = getPieceAt(temp);
				
				if(p != null && p.isLight() == color) {
					if(!mateFilter(temp, p.getValidMoves(temp, this)).isEmpty()) {
						movablePieces.add(temp);
					}
				}
			}
		}
		
		return movablePieces;
	}
	
	public ArrayList<Location> mateFilter(Location begin, ArrayList<Location> moves) {
		ArrayList<Location> trimmedList = new ArrayList<Location>();
		ChessPiece p = getPieceAt(begin);
		
		for(Location move: moves) {
			if(!moveAllowsFriendlyCheck(begin, move, p.isLight())) {
				trimmedList.add(move);
			}
		}
		
		return trimmedList;
	}
	
	public boolean isLongCastleClear(Location kingLocation) {
		boolean clear = true;
		
		for(int i = 3; i >= 1 && clear; i--) {
			Location temp = new Location(i, kingLocation.getRowIndex());
			if(getPieceAt(temp) != null || locationAttacked(temp, !getPieceAt(kingLocation).isLight()) || getPieceAt(kingLocation).hasMoved()) {
				clear = false;
			}
		}
		return clear;
	}
	
	public boolean isShortCastleClear(Location kingLocation) {
		boolean clear = true;
		
		for(int i = 5; i <= 6 && clear; i++) {
			Location temp = new Location(i, kingLocation.getRowIndex());
			if(getPieceAt(temp) != null || locationAttacked(temp, !getPieceAt(kingLocation).isLight())  || getPieceAt(kingLocation).hasMoved()) {
				clear = false;
			}
		}
		return clear;
	}
	
	public boolean isCastlePathClear(Location kingLocation, Location castleLocation) {
		boolean clear = (castleLocation.getColumnIndex() == 6)? 
				isShortCastleClear(kingLocation) : isLongCastleClear(kingLocation);
		return clear;
		
	}
}
