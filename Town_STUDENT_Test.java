import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Town_STUDENT_Test
{
	private Town town1, town2;

	@BeforeEach
	public void setUp() throws Exception
	{
		town1 = new Town("Town 1");
		town2 = new Town("Town 2");
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		town1 = town2 = null;
	}

	@Test
	public void testHashCode()
	{
		assertEquals(town1.hashCode(), new Town("Town 1").hashCode());
	    assertNotEquals(town1.hashCode(), new Town("Town 2").hashCode());
	}
	
	@Test
	public void testCompareTo()
	{
		assertEquals(0, town1.compareTo(town1));
	    assertEquals(0, town1.compareTo(new Town("Town 1")));
	    assertEquals(-1, town1.compareTo(town2));
	}
	
	@Test
	public void testEquals()
	{
		assertEquals(true, town1.equals(town1));
	    assertEquals(true, town1.equals(new Town("Town 1")));
	    assertEquals(false, town1.equals(town2));
	}
	
	@Test
	public void testGetName()
	{
		assertEquals("Town 1", town1.getName());
	    assertEquals("Town 2", town2.getName());
	}
	
	@Test
	public void testToString()
	{
		assertEquals("Town 1", town1.toString());
	    assertEquals("Town 2", town2.toString());
	}

}
