package chess.model.customExceptions;

@SuppressWarnings("serial")
public class InvalidCastleException extends Exception{
	public InvalidCastleException(String message) {
		super(message);
	}

}
