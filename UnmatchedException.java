/**
 * A class of runtime exceptions thrown when password does not match the re-entered password.
 * @author rpard
 */
public class UnmatchedException extends RuntimeException
{
	public UnmatchedException()
	{
		super("Passwords do not match");
	}
	
	public UnmatchedException(String message)
	{
		super(message);
	}
}
