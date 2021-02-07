/**
 * A class of runtime exceptions thrown when password does not contain at least one uppercase character.
 * @author rpard
 */
public class NoUpperAlphaException extends RuntimeException
{
	public NoUpperAlphaException()
	{
		super("The password must contain at least one uppercase alphabetic character");
	}
	
	public NoUpperAlphaException(String message)
	{
		super(message);
	}
}
