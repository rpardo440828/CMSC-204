/**
 * A class of runtime exceptions thrown when password is less than ten characters.
 * @author rpard
 */
public class WeakPasswordException extends RuntimeException
{
	public WeakPasswordException()
	{
		super("The password is OK but weak - it contains fewer than 10 characters");
	}
	
	public WeakPasswordException(String message)
	{
		super(message);
	}
}
