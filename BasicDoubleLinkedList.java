import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * This generic double-linked list relies on a head (reference to first element of the list)
 * and tail (reference to the last element of the list). Both are set to null when the list
 * is empty. Both point to the same element when there is only one element in the list.
 * @author rpard
 * @param <T> generic double linked list
 */
public class BasicDoubleLinkedList<T> implements Iterable<T>
{
	protected Node head;
	protected Node tail;
	protected int size;
	
	/**
	 * Default constructor
	 */
	public BasicDoubleLinkedList()
	{
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	/**
	 * Adds an element to the end of the list. Do not use iterators to implement this method.
	 * @param data the data for the Node
	 * @return reference to the object
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data)
	{
		Node newNode = new Node(data);
		
		if(this.size == 0)
		{
			this.head = newNode;
			this.tail = this.head;
		}
		else
		{
			tail.next = newNode;
			newNode.previous = this.tail;
			this.tail = newNode;
		}
		
		size++;
		return this;
	}
	
	/**
	 * Adds an element to the beginning of the list. Do not use iterators to implement this method.
	 * @param data the data for the Node
	 * @return reference to the object
	 */
	public BasicDoubleLinkedList<T> addToFront(T data)
	{
		Node newNode = new Node(data);
		
		if(this.size == 0)
		{
			this.head = newNode;
			this.tail = this.head;
		}
		else
		{
			head.previous = newNode;
			newNode.next = this.head;
			this.head = newNode;
		}
		
		size++;
		return this;
	}
	
	/**
	 * Returns but does not remove the first element from the list, null otherwise.
	 * @return the data element or null
	 */
	public T getFirst()
	{
		if(this.size == 0)
		{
			return null;
		}
		else
		{
			return this.head.data;
		}
	}
	
	/**
	 * Returns but does not remove the last element from the list. 
	 * @return the data element or null
	 */
	public T getLast()
	{
		if(this.size == 0)
		{
			return null;
		}
		else
		{
			return this.tail.data;
		}
	}
	
	/**
	 * Returns the size of linked list
	 * @return the size of the linked list
	 */
	public int getSize()
	{
		return this.size;
	}
	
	/**
	 * Iterator method which creates an instance of MyIterator class
	 * @throws NoSuchElementException occurs during methods in MyIterator class
	 * @throws UnsupportedOperationException occurs during methods in MyIterator class
	 */
	@Override
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException
	{
		return new MyIterator();	
	}
	
	/**
	 * Removes the first instance of the targetData from the list
	 * @param targetData the data element to be removed
	 * @param comparator the comparator to determine equality of data elements
	 * @return data element or null
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator)
	{
		Node current = this.head;
		
		while(current != null)
		{
			if(comparator.compare(targetData, current.data) == 0)
			{
				if(current == this.head)
				{
					this.head = this.head.next;
				}
				else if(current == this.tail)
				{
					this.tail = this.tail.previous;
				}
				else
				{
					current.previous.next = current.next;
				}
				
				this.size--;
				return this;
			}
			current = current.next;
		}
		
		return this;
	}
	
	/**
	 * Removes and returns the first element from the list. If there are no elements the method returns null.
	 * @return data element or null
	 */
	public T retrieveFirstElement()
	{
		if(this.size == 0)
		{
			return null;
		}
		
		T firstElement = this.head.data;
		if(this.size == 1)
		{
			this.head = null;
			this.tail = null;
		}
		else
		{
			this.head = head.next;
			this.head.previous = null;
		}
		
		this.size--;
		return firstElement;
	}
	
	/**
	 * Removes and returns the last element from the list. If there are no elements the method returns null.
	 * @return data element or null
	 */
	public T retrieveLastElement()
	{
		if(this.size == 0)
		{
			return null;
		}
		
		T lastElement = this.tail.data;
		if(this.size == 1)
		{
			this.head = null;
			this.tail = null;
		}
		else
		{
			this.tail = this.tail.previous;
			this.tail.next = null;
		}
		
		this.size--;
		return lastElement;
	}
	
	/**
	 * Returns an arraylist of the items in the list from head of list to tail of list
	 * @return an arraylist of the items in the list
	 */
	public ArrayList<T> toArrayList()
	{
		ArrayList<T> result = new ArrayList<>(this.size);
		Node current = this.head;
		
		for(int i = 0; ((i < this.size) && (current != null)); i++)
		{
			result.add(i, current.data);
			current = current.next;
		}
		return result;
	}
	
	/**
	 * Inner iterator class
	 * @author rpard
	 */
	class MyIterator implements ListIterator<T>
	{
		protected Node current = head;
		protected Node tailNode;

		/**
		 * Returns true if object has a next node, false otherwise
		 * @return boolean value determining if there is a next node
		 */
		@Override
		public boolean hasNext()
		{
			return (current != null);
		}

		/**
		 * Moves to the next data node and returns the data
		 * @throws NoSuchElementException if there are no more elements 
		 * @return the data of next node
		 */
		@Override
		public T next() throws NoSuchElementException
		{
			if(hasNext())
			{
				tailNode = current;
				current = current.next;
				return tailNode.data;
			}
			else
			{
				throw new NoSuchElementException();
			}
		}

		/**
		 * Returns true if object has a previous node, false otherwise
		 * @return boolean value determining if there is a previous node
		 */
		@Override
		public boolean hasPrevious()
		{
			return (tailNode != null);
		}

		/**
		 * Moves to the previous data node and returns the data
		 * @throws NoSuchElementException if there are no more elements 
		 * @return the data of previous node
		 */
		@Override
		public T previous() throws NoSuchElementException
		{
			if(hasPrevious())
			{
				current = tailNode;
				tailNode = tailNode.previous;
				return current.data;
			}
			else
			{
				throw new NoSuchElementException();
			}
		}

		/**
		 * Throws exception if method is called
		 * @throws UnsupportedOperationException if method is called
		 */
		@Override
		public int nextIndex() throws UnsupportedOperationException
		{
			throw new UnsupportedOperationException();
		}

		/**
		 * Throws exception if method is called
		 * @throws UnsupportedOperationException if method is called
		 */
		@Override
		public int previousIndex() throws UnsupportedOperationException
		{
			throw new UnsupportedOperationException();
		}

		/**
		 * Throws exception if method is called
		 * @throws UnsupportedOperationException if method is called
		 */
		@Override
		public void remove() throws UnsupportedOperationException
		{
			throw new UnsupportedOperationException();
		}

		/**
		 * Throws exception if method is called
		 * @throws UnsupportedOperationException if method is called
		 */
		@Override
		public void set(T e) throws UnsupportedOperationException
		{
			throw new UnsupportedOperationException();
		}

		/**
		 * Throws exception if method is called
		 * @throws UnsupportedOperationException if method is called
		 */
		@Override
		public void add(T e) throws UnsupportedOperationException
		{
			throw new UnsupportedOperationException();
		}
		
	}
	
	/**
	 * Inner node class that has a data point, previous and next node
	 * @author rpard
	 */
	class Node
	{
		protected T data;
		protected Node next;
		protected Node previous;
		
		/**
		 * Constructor that initilizes data element and all others to null
		 * @param data the data element
		 */
		public Node(T data)
		{
			this.data = data;
			this.next = null;
			this.previous = null;
		}
	}
}
