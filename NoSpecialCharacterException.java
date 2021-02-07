/**
 * A class of runtime exceptions thrown when password does not contain at least one special character.
 * @author rpard
 */
public class NoSpecialCharacterException extends RuntimeException
{
	public NoSpecialCharacterException()
	{
		super("The password must contain at least one special character");
	}
	
	public NoSpecialCharacterException(String message)
	{
		super(message);
	}
}
