/**
 * A class of runtime exceptions thrown when password is less than 6 characters.
 * @author rpard
 */
public class LengthException extends RuntimeException
{
	public LengthException()
	{
		super("The password must be at least 6 characters long");
	}
	
	public LengthException(String message)
	{
		super(message);
	}
}
