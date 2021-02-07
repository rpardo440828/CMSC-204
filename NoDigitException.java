/**
 * A class of runtime exceptions thrown when password does not contain at least one digit.
 * @author rpard
 */
public class NoDigitException extends RuntimeException
{
	public NoDigitException()
	{
		super("The password must contain at least one digit");
	}
	
	public NoDigitException(String message)
	{
		super(message);
	}
}
