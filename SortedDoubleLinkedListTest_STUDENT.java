import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertTrue;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SortedDoubleLinkedListTest_STUDENT
{
	SortedDoubleLinkedList<Employee> sortedLinkedEmployee;
	EmployeeComparator comparator;
	
	public Employee employee1 = new Employee("Bob Smith", 32, 1234);
	public Employee employee2 = new Employee("Jim Hoffer", 25, 4825);
	public Employee employee3 = new Employee("Daniel Clover", 45, 9701);
	public Employee employee4 = new Employee("Sarah Goldberg", 56, 2260);
	public Employee employee5 = new Employee("Emily Griffen", 28, 7536);

	@BeforeEach
	public void setUp() throws Exception
	{
		comparator = new EmployeeComparator();
		sortedLinkedEmployee = new SortedDoubleLinkedList<>(comparator);
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		comparator = null;
		sortedLinkedEmployee = null;
	}
	
	@Test
	public void testAddToEnd()
	{
		try
		{
			sortedLinkedEmployee.addToEnd(employee5);
			assertTrue("Did not throw an UnsupportedOperatioonException", false);
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
	public void testAddToFront()
	{
		try
		{
			sortedLinkedEmployee.addToFront(employee1);
			assertTrue("Did not throw an UnsupportedOperatioonException", false);
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
	public void testIteratorSuccessfulNext()
	{
		sortedLinkedEmployee.add(employee1);
		sortedLinkedEmployee.add(employee2);
		sortedLinkedEmployee.add(employee3);
		sortedLinkedEmployee.add(employee4);
		ListIterator<Employee> iterator = sortedLinkedEmployee.iterator();
		
		assertEquals(true, iterator.hasNext());
	    assertEquals(employee1, iterator.next());
	    assertEquals(employee3, iterator.next());
	    assertEquals(employee2, iterator.next());
	    assertEquals(true, iterator.hasNext());
	}
	
	@Test
	public void testIteratorSuccessfulPrevious()
	{
		sortedLinkedEmployee.add(employee1);
		sortedLinkedEmployee.add(employee2);
		sortedLinkedEmployee.add(employee3);
		sortedLinkedEmployee.add(employee4);
		ListIterator<Employee> iterator = sortedLinkedEmployee.iterator();
		
		assertEquals(true, iterator.hasNext());
	    assertEquals(employee1, iterator.next());
	    assertEquals(employee3, iterator.next());
	    assertEquals(employee2, iterator.next());
	    assertEquals(true, iterator.hasPrevious());
	    
	    assertEquals(employee2, iterator.previous());
	    assertEquals(employee3, iterator.previous());
	    assertEquals(employee1, iterator.previous());
	    assertEquals(false, iterator.hasPrevious());
	}
	
	@Test
	public void testIteratorNoSuchElementException()
	{
		sortedLinkedEmployee.add(employee2);
	    sortedLinkedEmployee.add(employee3);
	    sortedLinkedEmployee.add(employee4);
	    sortedLinkedEmployee.add(employee5);
	    ListIterator<Employee> iterator = sortedLinkedEmployee.iterator();
	    
	    assertEquals(true, iterator.hasNext());
	    assertEquals(employee3, iterator.next());
	    assertEquals(employee5, iterator.next());
	    assertEquals(employee2, iterator.next());
	    assertEquals(employee4, iterator.next());
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
	public void testIteratorUnsupportedOperationException()
	{
		sortedLinkedEmployee.add(employee2);
	    sortedLinkedEmployee.add(employee3);
	    sortedLinkedEmployee.add(employee4);
	    sortedLinkedEmployee.add(employee5);
	    ListIterator<Employee> iterator = sortedLinkedEmployee.iterator();
	    
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
	public void testAddEmployee()
	{
		sortedLinkedEmployee.add(employee1);
		sortedLinkedEmployee.add(employee2);
		sortedLinkedEmployee.add(employee3);
		assertEquals(employee1, sortedLinkedEmployee.getFirst());
		assertEquals(employee2, sortedLinkedEmployee.getLast());
		
		sortedLinkedEmployee.add(employee4);
		sortedLinkedEmployee.add(employee5);
		assertEquals(employee1, sortedLinkedEmployee.getFirst());
		assertEquals(employee4, sortedLinkedEmployee.getLast());
		
		assertEquals(employee4, sortedLinkedEmployee.retrieveLastElement());
		assertEquals(employee2, sortedLinkedEmployee.getLast());
	}
	@Test
	public void testRemoveFirstEmployee()
	{
		sortedLinkedEmployee.add(employee3);
		sortedLinkedEmployee.add(employee4);
		assertEquals(employee3, sortedLinkedEmployee.getFirst());
		assertEquals(employee4, sortedLinkedEmployee.getLast());
		
		sortedLinkedEmployee.add(employee1);
		assertEquals(employee1, sortedLinkedEmployee.getFirst());
		sortedLinkedEmployee.remove(employee1, comparator);
		assertEquals(employee3, sortedLinkedEmployee.getFirst());
	}
	
	@Test
	public void testRemoveEndEmployee()
	{
		sortedLinkedEmployee.add(employee1);
		sortedLinkedEmployee.add(employee2);
		assertEquals(employee1, sortedLinkedEmployee.getFirst());
		assertEquals(employee2, sortedLinkedEmployee.getLast());
		
		sortedLinkedEmployee.add(employee4);
		assertEquals(employee4, sortedLinkedEmployee.getLast());
		sortedLinkedEmployee.remove(employee4, comparator);
		assertEquals(employee2, sortedLinkedEmployee.getLast());
	}
	
	@Test
	public void testRemoveMiddleEmployee()
	{
		sortedLinkedEmployee.add(employee1);
		sortedLinkedEmployee.add(employee5);
		assertEquals(employee1, sortedLinkedEmployee.getFirst());
		assertEquals(employee5, sortedLinkedEmployee.getLast());
		
		sortedLinkedEmployee.add(employee2);
		assertEquals(employee1, sortedLinkedEmployee.getFirst());
		assertEquals(employee2, sortedLinkedEmployee.getLast());
		assertEquals(3, sortedLinkedEmployee.getSize());
		
		sortedLinkedEmployee.remove(employee5, comparator);
		assertEquals(employee1, sortedLinkedEmployee.getFirst());
		assertEquals(employee2, sortedLinkedEmployee.getLast());
		assertEquals(2, sortedLinkedEmployee.getSize());
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
