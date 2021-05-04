import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Road_STUDENT_Test
{
	private Town town1, town2, town3;
	private Road road1, road2, road3;

	@BeforeEach
	public void setUp() throws Exception
	{
		town1 = new Town("Town 1");
		town2 = new Town("Town 2");
		town3 = new Town("Town 3");
		road1 = new Road(town1, town2, "Road 1");
		road2 = new Road(town2, town1, "Road 1");
		road3 = new Road(town2, town3, "Road 2");
		
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		town1 = town2 = town3 = null;
		road1 = road2 = road3 = null;
	}

	@Test
	public void testCompareTo()
	{
		assertEquals(0, road1.compareTo(road1));
	    assertEquals(0, road1.compareTo(road2));
	    assertEquals(-1, road2.compareTo(road3));
	}
	
	@Test
	public void testContains()
	{
		assertTrue(road1.contains(town1));
	    assertTrue(road1.contains(town2));
	    assertFalse(road1.contains(town3));
	}
	
	@Test
	public void testEquals()
	{
		assertTrue(road1.equals(road1));
	    assertTrue(road1.equals(road2));
	    assertFalse(road1.equals(road3));
	}
	
	@Test
	public void testToString()
	{
		assertEquals("Town 1 via Road 1 to Town 2 1 mi", road1.toString());
	    assertEquals("Town 2 via Road 1 to Town 1 1 mi", road2.toString());
	}
	
	@Test
	public void testGetSource()
	{
		assertEquals(town1, road1.getSource());
	    assertEquals(town2, road2.getSource());
	    assertEquals(town2, road3.getSource());
	}
	
	@Test
	public void testGetDestination()
	{
		assertEquals(town2, road1.getDestination());
	    assertEquals(town1, road2.getDestination());
	    assertEquals(town3, road3.getDestination());
	}
	
	@Test
	public void testGetWeight()
	{
		assertEquals(1, road1.getWeight());
	    assertEquals(500, new Road(town3, town2, 500, "Long Road").getWeight());
	}
	
	@Test
	public void testGetName()
	{
		assertEquals("Road 1", road1.getName());
	    assertEquals("Road 1", road2.getName());
	    assertEquals("Road 2", road3.getName());
	}
}
