
public class QueueOverflowException extends RuntimeException
{
	public QueueOverflowException()
	{
		super("Queue is empty");
	}
	
	public QueueOverflowException(String message)
	{
		super(message);
	}
}
