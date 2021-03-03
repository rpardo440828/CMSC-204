
public class StackOverflowException extends RuntimeException
{
	public StackOverflowException()
	{
		super("Stack is empty");
	}
	
	public StackOverflowException(String message)
	{
		super(message);
	}
}
