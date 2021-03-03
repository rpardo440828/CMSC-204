
public class QueueUnderflowException extends RuntimeException
{
	public QueueUnderflowException()
	{
		super("Queue is full");
	}
	
	public QueueUnderflowException(String message)
	{
		super(message);
	}
}
