import java.util.Comparator;
import java.util.ListIterator;

/**
 * Implements a generic sorted double list using a provided Comparator. It extends BasicDoubleLinkedList class.
 * @author rpard
 * @param <T>
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> implements Iterable<T>
{
	private Comparator<T> comparator;
	
	/**
	 * Creates an empty list that is associated with the specified comparator.
	 * @param comparator Comparator to compare data elements
	 */
	public SortedDoubleLinkedList(Comparator<T> comparator)
	{
		this.comparator = comparator;
	}
	
	/**
	 * Inserts the specified element at the correct position in the sorted list.
	 * @param data the data to be added
	 * @return a reference to the current object
	 */
	public SortedDoubleLinkedList<T> add(T data)
	{
		Node newNode = new Node(data);
	    Node current = this.head;

	    
	    if (this.size == 0)
	    {
	      this.head = newNode;
	      this.tail = this.head;
	      this.size++;
	      return this;
	    } 
	    else if (comparator.compare(this.head.data, data) > 0)
	    {
	      newNode.next = this.head;
	      this.head.previous = newNode;
	      this.head = newNode;
	      this.size++;
	      return this;
	    } 
	    else
	    {
	      while (comparator.compare(current.data, data) < 0)
	      {
	        if (current.next == null)
	        {
	          current.next = newNode;
	          newNode.previous = current;
	          this.tail = newNode;
	          this.size++;
	          return this;
	        } 
	        
	        else
	        {
	          current = current.next;
	        }
	      }
	      
	      current.previous.next = newNode;
	      newNode.previous = current.previous;
	      current.previous = newNode;
	      newNode.next = current;
	      this.size++;
	      return this;
	    }
	}
	
	/**
	 * Implements the remove operation by calling the super class remove method.
	 * @param data the data element to be removed
	 * @param comparator Comparator to determine equality of data elements
	 * @return data element or null
	 */
	@Override
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator)
	{
		return (SortedDoubleLinkedList<T>) super.remove(data, comparator);
	}
	
	/**
	 * Implements the iterator by calling the super class iterator method.
	 * @return an iterator positioned at the head of the list
	 */
	@Override
	public ListIterator<T> iterator()
	{
		return super.iterator();
	}
	
	/**
	 * This method is invalid. Will throw exception if called.
	 * @param data data element
	 * @return reference to the current object
	 * @throws UnsupportedOperationException if the method is called
	 */
	@Override
	public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	/**
	 * This method is invalid. Will throw exception if called.
	 * @param data data element
	 * @return reference to the current object
	 * @throws UnsupportedOperationException if the method is called
	 */
	@Override
	public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
}
