import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TownGraphManager_STUDENT_Test
{
	private TownGraphManagerInterface graph;
	private String[] town;

	@BeforeEach
	public void setUp() throws Exception
	{
		graph = new TownGraphManager();
		town = new String[9];
		
		for(int i = 1; i < 9; i++)
		{
			town[i] = "Town " + i;
			graph.addTown(town[i]);
		}
		graph.addRoad(town[1], town[5], 3, "Road 1");
	    graph.addRoad(town[1], town[7], 4, "Road 2");
	    graph.addRoad(town[2], town[4], 6, "Road 3");
	    graph.addRoad(town[2], town[5], 4, "Road 4");
	    graph.addRoad(town[3], town[4], 2, "Road 5");
	    graph.addRoad(town[3], town[6], 2, "Road 6");
	    graph.addRoad(town[3], town[8], 3, "Road 7");
	    graph.addRoad(town[6], town[8], 5, "Road 8");
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		graph = null;
	}

	@Test
	public void testAddRoad()
	{
		ArrayList<String> roads = graph.allRoads();
	    assertEquals("Road 1", roads.get(0));
	    assertEquals("Road 2", roads.get(1));
	    graph.addRoad(town[2], town[6], 5, "Road 10");
	    roads = graph.allRoads();
	    assertEquals("Road 1", roads.get(0));
	    assertEquals("Road 10", roads.get(1));
	    assertEquals("Road 2", roads.get(2));
	}
	
	@Test
	public void testGetRoad()
	{
		assertEquals("Road 3", graph.getRoad(town[2], town[4]));
	    assertEquals("Road 8", graph.getRoad(town[6], town[8]));
	}
	
	@Test
	public void testAddTown()
	{
		assertEquals(false, graph.containsTown("Town 9"));
	    graph.addTown("Town 9");
	    assertEquals(true, graph.containsTown("Town 9"));
	}
	
	@Test
	public void testDisjointGraph()
	{
		assertEquals(false, graph.containsTown("Town 10"));
	    graph.addTown("Town 10");
	    ArrayList<String> path = graph.getPath(town[1], "Town 10");
	    assertFalse(path.size() > 0);
	}
	
	@Test
	public void testContainsTown()
	{
		assertEquals(true, graph.containsTown("Town 2"));
	    assertEquals(false, graph.containsTown("Town 10"));
	}
	
	@Test
	public void testContainsRoadConnection()
	{
		assertEquals(true, graph.containsRoadConnection(town[3], town[8]));
	    assertEquals(false, graph.containsRoadConnection(town[2], town[6]));
	}
	
	@Test
	public void testAllRoads()
	{
		ArrayList<String> roads = graph.allRoads();
	    assertEquals("Road 1", roads.get(0));
	    assertEquals("Road 2", roads.get(1));
	    assertEquals("Road 3", roads.get(2));
	    assertEquals("Road 6", roads.get(5));
	    assertEquals("Road 7", roads.get(6));
	}
	
	@Test
	public void testDeleteRoadConnection()
	{
		assertEquals(true, graph.containsRoadConnection(town[1], town[7]));
	    graph.deleteRoadConnection(town[1], town[7], "Road 2");
	    assertEquals(false, graph.containsRoadConnection(town[1], town[7]));
	}
	
	@Test
	public void testDeleteTown()
	{
		assertEquals(true, graph.containsTown("Town 6"));
	    graph.deleteTown(town[6]);
	    assertEquals(false, graph.containsTown("Town 6"));
	}
	
	@Test
	public void testAllTowns()
	{
		ArrayList<String> roads = graph.allTowns();
	    assertEquals("Town 1", roads.get(0));
	    assertEquals("Town 2", roads.get(1));
	    assertEquals("Town 3", roads.get(2));
	    assertEquals("Town 4", roads.get(3));
	    assertEquals("Town 8", roads.get(7));
	}
	
	@Test
	public void testGetPath()
	{
		ArrayList<String> path = graph.getPath(town[1], town[8]);
	    assertNotNull(path);
	    assertTrue(path.size() > 0);
	    assertEquals("Town 1 via Road 1 to Town 5 3 mi", path.get(0).trim());
	    assertEquals("Town 5 via Road 4 to Town 2 4 mi", path.get(1).trim());
	    assertEquals("Town 2 via Road 3 to Town 4 6 mi", path.get(2).trim());
	    assertEquals("Town 4 via Road 5 to Town 3 2 mi", path.get(3).trim());
	    assertEquals("Town 3 via Road 7 to Town 8 3 mi", path.get(4).trim());
	}
	
	 @Test
	  public void testGetPathA()
	  {
	    ArrayList<String> path = graph.getPath(town[1], town[7]);
	    assertNotNull(path);
	    assertTrue(path.size() > 0);
	    assertEquals("Town 1 via Road 2 to Town 7 4 mi", path.get(0).trim());
	  }

	  @Test
	  public void testGetPathB()
	  {
	    ArrayList<String> path = graph.getPath(town[2], town[6]);
	    assertNotNull(path);
	    assertTrue(path.size() > 0);
	    assertEquals("Town 2 via Road 3 to Town 4 6 mi", path.get(0).trim());
	    assertEquals("Town 4 via Road 5 to Town 3 2 mi", path.get(1).trim());
	    assertEquals("Town 3 via Road 6 to Town 6 2 mi", path.get(2).trim());

	  }

}
