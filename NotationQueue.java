import java.util.ArrayList;

/**
 * This generic class represents a queue
 * @author rpard
 * @param <T> generic type
 */
public class NotationQueue<T> implements QueueInterface<T>
{
	private int numElements;
	private int first;
	private int last;
	private int size;
	private Object[] queue;
	
	/**
	 * Default constructor
	 */
	public NotationQueue()
	{
		this.size = 20;
		queue = new Object[this.size];
	}
	
	/**
	 * Contructor which takes in the size
	 * @param size the size of the queue
	 */
	public NotationQueue(int size)
	{
		this.size = size;
		this.first = 0;
		this.last = -1;
		this.numElements = 0;
		queue = new Object[this.size];
	}
	
	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	@Override
	public boolean isEmpty()
	{
		if(this.numElements == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Determines of the Queue is full
	 * @return true if queue is full, false otherwise
	 */
	@Override
	public boolean isFull()
	{
		if(this.size == this.numElements)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 */
	@Override
	public T dequeue() throws QueueUnderflowException
	{
		if(isEmpty())
		{
			throw new QueueUnderflowException();
		}
		
		T front = (T) queue[this.first];
		if(front == null)
		{
			return null;
		}
		
		queue[this.first] = null;
		this.first++;
		this.numElements--;
		
		return front;
	}

	/**
	 * Number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	@Override
	public int size()
	{
		return this.numElements;
	}

	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful, false if not
	 */
	@Override
	public boolean enqueue(T e) throws QueueOverflowException
	{
		if(isFull())
		{
			throw new QueueOverflowException();
		}
		if(isEmpty())
		{
			this.first = 0;
			this.last = 0;
		}
		else
		{
			this.last++;
		}
		
		queue[this.last] = e;
		this.numElements++;
		return true;
	}

	/**
	 * Returns the string representation of the elements in the Queue, 
	 * the beginning of the string is the front of the queue
	 * @return string representation of the Queue with elements
	 */
	@Override
	public String toString()
	{
		StringBuilder text = new StringBuilder();
		
		for(int i = this.first; i <= this.last; i++)
		{
			text.append(queue[i]);
		}
		return text.toString();
	}
	
	/**
	 * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
	 * Place the delimiter between all elements of the Queue
	 * @return string representation of the Queue with elements separated with the delimiter
	 */
	@Override
	public String toString(String delimiter)
	{
		StringBuilder text = new StringBuilder();
		
		for(int i = this.first; i < this.last; i++)
		{
			text.append(queue[i] + delimiter);
		}
		text.append(queue[this.last]);
		
		return text.toString();
	}

	 /**
	  * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
	  * is the first element in the Queue
	  * @param list elements to be added to the Queue
	  */
	@Override
	public void fill(ArrayList<T> list)
	{
		ArrayList<T> copy = new ArrayList<>();
		
		for(int i = 0; i < list.size(); i++)
		{
			copy.add(list.get(i));
		}
		
		try
		{
			for(int j = 0; j < copy.size(); j++)
			{
				enqueue(copy.get(j));
			}
		}
		catch(QueueOverflowException e)
		{
			e.getMessage();
		}
	}

}
