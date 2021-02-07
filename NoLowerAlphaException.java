/**
 * A class of runtime exceptions thrown when password does not contain at least one lowercase character.
 * @author rpard
 */
public class NoLowerAlphaException extends RuntimeException
{
	public NoLowerAlphaException()
	{
		super("The password must contain at least one lower case alphabetic character");
	}
	
	public NoLowerAlphaException(String message)
	{
		super(message);
	}
}
