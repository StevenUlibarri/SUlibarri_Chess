package chess.model.board;

public class Location {
	
	private char column;
	private int row;
	
	public Location(char c, int r) {
		column = c;
		row = r;
	}

	public char getColumn() {
		return column;
	}

	public void setColumn(char column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}
	
	public String toString() {
		return "" + column + row;
	}

}
