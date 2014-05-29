package chess.model.customExceptions;

@SuppressWarnings("serial")
public class InvalidCommandException extends Exception {
	
	public InvalidCommandException(String message) {
		super(message);
	}

}
