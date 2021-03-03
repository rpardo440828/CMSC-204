
public class StackUnderflowException extends RuntimeException
{
	public StackUnderflowException()
	{
		super("Stack is full");
	}
	
	public StackUnderflowException(String message)
	{
		super(message);
	}
}
