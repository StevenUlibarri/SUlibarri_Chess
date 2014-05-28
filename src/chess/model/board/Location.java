package chess.model.board;

public class Location {
	
	private int column;
	private int row;
	
	private static final int ARRAY_INDEX_COLUMN_OFFSET = 97;
	private static final int ARRAY_INDEX_ROW_OFFSET = 1;
	
	public Location(char c, int r) {
		column = (int)c - ARRAY_INDEX_COLUMN_OFFSET;
		row = r - ARRAY_INDEX_ROW_OFFSET;;
	}
	
	public Location(int c, int r) {
		column = c;
		row = r;
	}

	public int getColumnIndex() {
		return column;
	}

	public int getRowIndex() {
		return row;
	}
	
	public String toString() {
		return "" + (char)(column + ARRAY_INDEX_COLUMN_OFFSET) + (row + ARRAY_INDEX_ROW_OFFSET);
	}

}
