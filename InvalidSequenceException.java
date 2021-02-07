/**
 * A class of runtime exceptions thrown when password contains two of the same characters in sequence.
 * @author rpard
 */
public class InvalidSequenceException extends RuntimeException
{
	public InvalidSequenceException()
	{
		super("The password cannot contain more than two of the same character in sequence");
	}
	
	public InvalidSequenceException(String message)
	{
		super(message);
	}
}
