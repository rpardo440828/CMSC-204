
public class InvalidNotationFormatException extends RuntimeException
{
	public InvalidNotationFormatException()
	{
		super("Notation format is incorrect");
	}
	
	public InvalidNotationFormatException(String message)
	{
		super(message);
	}
}
