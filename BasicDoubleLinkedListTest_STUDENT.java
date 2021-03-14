import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BasicDoubleLinkedListTest_STUDENT
{
	BasicDoubleLinkedList<Employee> linkedEmployee;
	EmployeeComparator comparator;
	
	public Employee employee1 = new Employee("Bob Smith", 32, 1234);
	public Employee employee2 = new Employee("Jim Hoffer", 25, 4825);
	public Employee employee3 = new Employee("Daniel Clover", 45, 9701);
	public Employee employee4 = new Employee("Sarah Goldberg", 56, 2260);
	public Employee employee5 = new Employee("Emily Griffen", 28, 7536);
	
	@BeforeEach
	public void setUp() throws Exception
	{
		linkedEmployee = new BasicDoubleLinkedList<Employee>();
		linkedEmployee.addToEnd(employee2);
		linkedEmployee.addToEnd(employee3);
		linkedEmployee.addToEnd(employee4);
		comparator = new EmployeeComparator();
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		linkedEmployee = null;
		comparator = null;
	}

	@Test
	public void testGetSize()
	{
		assertEquals(3, linkedEmployee.getSize());
	}
	
	@Test
	public void testAddToEnd()
	{
		assertEquals(employee4, linkedEmployee.getLast());
		linkedEmployee.addToEnd(employee5);
		assertEquals(employee5, linkedEmployee.getLast());
	}
	
	@Test
	public void testAddToFront()
	{
		assertEquals(employee2, linkedEmployee.getFirst());
		linkedEmployee.addToFront(employee1);
		assertEquals(employee1, linkedEmployee.getFirst());
	}
	
	@Test
	public void testGetFirst()
	{
		assertEquals(employee2, linkedEmployee.getFirst());
		linkedEmployee.addToFront(employee1);
		assertEquals(employee1, linkedEmployee.getFirst());
	}
	
	@Test
	public void testGetLast()
	{
		assertEquals(employee4, linkedEmployee.getLast());
		linkedEmployee.addToEnd(employee5);
		assertEquals(employee5, linkedEmployee.getLast());
	}
	
	@Test
	public void testToArrayList()
	{
		linkedEmployee.addToFront(employee1);
		linkedEmployee.addToEnd(employee5);
		ArrayList<Employee> list = linkedEmployee.toArrayList();
		
		assertEquals(employee1, list.get(0));
	    assertEquals(employee2, list.get(1));
	    assertEquals(employee3, list.get(2));
	    assertEquals(employee4, list.get(3));
	    assertEquals(employee5, list.get(4));
	}
	
	@Test
	public void testIteratorSuccessfulNext()
	{
		linkedEmployee.addToFront(employee1);
		linkedEmployee.addToEnd(employee5);
		ListIterator<Employee> iterator = linkedEmployee.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(employee1, iterator.next());
		assertEquals(employee2, iterator.next());
		assertEquals(employee3, iterator.next());
		assertEquals(employee4, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(employee5, iterator.next());
		assertEquals(false, iterator.hasNext());
	}
	
	@Test
	public void testIteratorSuccessfulPrevious()
	{
		linkedEmployee.addToFront(employee1);
		linkedEmployee.addToEnd(employee5);
		ListIterator<Employee> iterator = linkedEmployee.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(false, iterator.hasPrevious());
		assertEquals(employee1, iterator.next());
		assertEquals(employee2, iterator.next());
		assertEquals(employee3, iterator.next());
		assertEquals(employee4, iterator.next());
		assertEquals(employee5, iterator.next());
		
		assertEquals(true, iterator.hasPrevious());
		assertEquals(employee5, iterator.previous());
		assertEquals(employee4, iterator.previous());
		assertEquals(employee3, iterator.previous());
		assertEquals(employee2, iterator.previous());
		assertEquals(employee1, iterator.previous());
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionNext()
	{
		linkedEmployee.addToFront(employee1);
		linkedEmployee.addToEnd(employee5);
		ListIterator<Employee> iterator = linkedEmployee.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(employee1, iterator.next());
		assertEquals(employee2, iterator.next());
		assertEquals(employee3, iterator.next());
		assertEquals(employee4, iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals(employee5, iterator.next());
		assertEquals(false, iterator.hasNext());
		
		try
		{
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException", false);
		}
		catch(NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionPrevious()
	{
		linkedEmployee.addToFront(employee1);
		linkedEmployee.addToEnd(employee5);
		ListIterator<Employee> iterator = linkedEmployee.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(false, iterator.hasPrevious());
		assertEquals(employee1, iterator.next());
		assertEquals(employee2, iterator.next());
		assertEquals(employee3, iterator.next());
		assertEquals(employee4, iterator.next());
		assertEquals(employee5, iterator.next());
		
		assertEquals(true, iterator.hasPrevious());
		assertEquals(employee5, iterator.previous());
		assertEquals(employee4, iterator.previous());
		assertEquals(employee3, iterator.previous());
		assertEquals(employee2, iterator.previous());
		assertEquals(employee1, iterator.previous());
		
		try
		{
			iterator.previous();
			assertTrue("Did not throw a NoSuchElementException", false);
		}
		catch(NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	@Test
	public void testIteratorUnsupportedOperationException()
	{
		linkedEmployee.addToFront(employee1);
		ListIterator<Employee> iterator = linkedEmployee.iterator();
		
		assertEquals(true, iterator.hasNext());
		assertEquals(employee1, iterator.next());
		assertEquals(employee2, iterator.next());
		
		try
		{
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException", false);
		}
		catch(UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a NoSuchElementException", true);
		}
		catch(Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
	}
	
	@Test
	public void testRemove()
	{
		assertEquals(3, linkedEmployee.getSize());
	    assertEquals(employee2, linkedEmployee.getFirst());
	    assertEquals(employee4, linkedEmployee.getLast());
	    linkedEmployee.remove(employee2, comparator);
	    
	    assertEquals(2, linkedEmployee.getSize());
	    assertEquals(employee3, linkedEmployee.getFirst());
	    assertEquals(employee4, linkedEmployee.getLast());
	    linkedEmployee.addToFront(employee1);
	    
	    assertEquals(employee1, linkedEmployee.getFirst());
	    assertEquals(employee4, linkedEmployee.getLast());
	    linkedEmployee.remove(employee4, comparator);
	    
	    assertEquals(2, linkedEmployee.getSize());
	    assertEquals(employee1, linkedEmployee.getFirst());
	    assertEquals(employee3, linkedEmployee.getLast());
	}
	
	@Test
	public void testRetrieveFirstElement()
	{
		assertEquals(3, linkedEmployee.getSize());
	    assertEquals(employee2, linkedEmployee.getFirst());
	    assertEquals(employee4, linkedEmployee.getLast());
	    linkedEmployee.addToFront(employee1);
	    
	    assertEquals(employee1, linkedEmployee.getFirst());
	    assertEquals(employee1, linkedEmployee.retrieveFirstElement());
	    assertEquals(employee2, linkedEmployee.getFirst());
	    assertEquals(3, linkedEmployee.getSize());
	}
	
	@Test
	public void testRetrieveLastElement()
	{
		assertEquals(3, linkedEmployee.getSize());
	    assertEquals(employee2, linkedEmployee.getFirst());
	    assertEquals(employee4, linkedEmployee.getLast());
	    linkedEmployee.addToEnd(employee5);
	    
	    assertEquals(employee5, linkedEmployee.getLast());
	    assertEquals(employee5, linkedEmployee.retrieveLastElement());
	    assertEquals(employee4, linkedEmployee.getLast());
	    assertEquals(3, linkedEmployee.getSize());
	}
	
	private class EmployeeComparator implements Comparator<Employee>
	{
		@Override
		public int compare(Employee first, Employee second)
		{
			return first.toString().compareTo(second.toString());
		}
	}
	
	private class Employee
	{
		String name;
		int age;
		int idNumber;
		
		public Employee(String name, int age, int idNumber)
		{
			this.name = name;
			this.age = age;
			this.idNumber = idNumber;
		}
		
		public String getName()
		{
			return this.name;
		}
		
		public int getAge()
		{
			return this.age;
		}
		
		public int getIdNumber()
		{
			return this.idNumber;
		}
		
		public String toString()
		{
			return getName() + " " + getAge() + " " + getIdNumber();
		}
	}

}
