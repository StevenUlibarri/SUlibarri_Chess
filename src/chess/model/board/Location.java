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
	
	public boolean equals(Object obj) {
		boolean equal = false;
		if(obj != null && obj instanceof Location) {
			Location toComp = (Location)obj;
			if(this.column == toComp.column && this.row == toComp.row) {
				equal = true;
			}
		}
		return equal;
	}
	
	public int hashCode() {
		return (((column+1)*8) + (row+1) - 8);
		
	}

}
