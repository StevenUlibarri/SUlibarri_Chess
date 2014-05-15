package chess.model.commands;

public class CastleCommand implements Executable {
	
	private MoveCommand move1;
	private MoveCommand move2;
	
	public CastleCommand(MoveCommand m1, MoveCommand m2) {
		this.move1 = m1;
		this.move2 = m2;
	}

	public MoveCommand getM1() {
		return move1;
	}

	public void setM1(MoveCommand m1) {
		this.move1 = m1;
	}

	public MoveCommand getM2() {
		return move2;
	}

	public void setM2(MoveCommand m2) {
		this.move2 = m2;
	}

	public String toString() {
		return move1 + " and " + move2;
	}

	@Override
	public void execute(String[][] board) {
		// TODO Auto-generated method stub
		
	}
	
}
