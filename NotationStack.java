import java.util.ArrayList;

/**
 * This  generic class represents a stack
 * @author rpard
 * @param <T> generic type
 */
public class NotationStack<T> implements StackInterface<T>
{
	private int numElements;
	private int first;
	private int last;
	private int size;
	private Object[] stack;
	
	/**
	 * Default constructor
	 */
	public NotationStack()
	{
		this.size = 20;
		this.stack = new Object[this.size];
	}
	
	/**
	 * Constructor that takes in the size
	 * @param size the size of the stack
	 */
	public NotationStack(int size)
	{
		this.size = size;
		this.first = 0;
		this.last = -1;
		this.numElements = 0;
		stack = new Object[this.size];
	}

	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	@Override
	public boolean isEmpty()
	{
		if(numElements == 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Determines if Stack is full
	 * @return true if Stack is full, false if not
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
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 */
	@Override
	public T pop() throws StackUnderflowException
	{
		if(isEmpty())
		{
			throw new StackUnderflowException();
		}
		
		T top = (T) stack[this.last];
		if(top == null)
		{
			return null;
		}
		
		stack[this.last] = null;
		this.last--;
		this.numElements--;
		
		return top;
	}

	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * @return the element at the top of the Stack
	 */
	@Override
	public T top() throws StackUnderflowException
	{
		if(isEmpty())
		{
			throw new StackUnderflowException();
		}
		
		T top = (T) stack[this.last];
		return top;
	}

	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	@Override
	public int size()
	{
		return this.numElements;
	}

	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 */
	@Override
	public boolean push(T e) throws StackOverflowException
	{
		if(isFull())
		{
			throw new StackOverflowException();
		}
		
		stack[++last] = e;
		this.numElements++;
		return true;
	}

	/**
	 * Returns the elements of the Stack in a string from bottom to top, the beginning 
	 * of the String is the bottom of the stack
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	@Override
	public String toString()
	{
		StringBuilder text = new StringBuilder();
		
		for(int i = this.first; i <= this.last; i++)
		{
			text.append(stack[i]);
		}
		return text.toString();
	}
	
	/**
	 * Returns the string representation of the elements in the Stack, the beginning of the 
	 * string is the bottom of the stack
	 * Place the delimiter between all elements of the Stack
	 * @return string representation of the Stack from bottom to top with elements 
	 * separated with the delimiter
	 */
	@Override
	public String toString(String delimiter)
	{
		StringBuilder text = new StringBuilder();
		
		for(int i = this.first; i < this.last; i++)
		{
			text.append(stack[i] + delimiter);
		}
		text.append(stack[this.last]);
		
		return text.toString();
	}

	/**
	  * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	  * is the first bottom element of the Stack
	  * @param list elements to be added to the Stack from bottom to top
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
				push(copy.get(j));
			}
		}
		catch(StackOverflowException e)
		{
			e.getMessage();
		}
	}

}
