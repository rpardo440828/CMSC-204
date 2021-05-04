import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Graph_STUDENT_Test
{
	private GraphInterface<Town, Road> graph;
	private Town[] town;

	@BeforeEach
	public void setUp() throws Exception
	{
		graph = new Graph();
		town = new Town[9];
		
		for (int i = 1; i < 9; i++)
		{
		      town[i] = new Town("Town " + i);
		      graph.addVertex(town[i]);
		}

		    graph.addEdge(town[1], town[5], 3, "Road 1");
		    graph.addEdge(town[1], town[7], 4, "Road 2");
		    graph.addEdge(town[2], town[4], 6, "Road 3");
		    graph.addEdge(town[2], town[5], 4, "Road 4");
		    graph.addEdge(town[3], town[4], 2, "Road 5");
		    graph.addEdge(town[3], town[6], 2, "Road 6");
		    graph.addEdge(town[3], town[8], 3, "Road 7");
		    graph.addEdge(town[6], town[8], 5, "Road 8");
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		graph = null;
	}

	@Test
	public void testGetEdge()
	{
		assertEquals(new Road(town[2], town[4], 6, "Road 3"), graph.getEdge(town[2], town[4]));
	    assertEquals(new Road(town[6], town[8], 5, "Road 8"), graph.getEdge(town[6], town[8]));
	}
	
	@Test
	public void testAddEdge()
	{
		assertEquals(false, graph.containsEdge(town[2], town[6]));
	    graph.addEdge(town[2], town[6], 1, "Road_10");
	    assertEquals(true, graph.containsEdge(town[2], town[6]));
	}
	
	@Test
	public void testAddVertex()
	{
		Town newTown = new Town("Town 10");
	    assertEquals(false, graph.containsVertex(newTown));
	    graph.addVertex(newTown);
	    assertEquals(true, graph.containsVertex(newTown));
	}
	
	@Test
	public void testContainsEdge()
	{
		assertEquals(true, graph.containsEdge(town[2], town[5]));
	    assertEquals(false, graph.containsEdge(town[2], town[6]));
	}
	
	@Test
	public void testContainsVertex()
	{
		assertEquals(true, graph.containsVertex(new Town("Town 7")));
	    assertEquals(false, graph.containsVertex(new Town("Town 10")));
	}
	
	@Test
	public void testEdgeSet()
	{
		Set<Road> roads = graph.edgeSet();
	    ArrayList<String> roadArrayList = new ArrayList<String>();
	    
	    for (Road road : roads)
	    {
	      roadArrayList.add(road.getName());
	    }
	    
	    Collections.sort(roadArrayList);
	    assertEquals("Road 1", roadArrayList.get(0));
	    assertEquals("Road 2", roadArrayList.get(1));
	    assertEquals("Road 3", roadArrayList.get(2));
	    assertEquals("Road 6", roadArrayList.get(5));
	    assertEquals("Road 7", roadArrayList.get(6));
	    assertEquals("Road 8", roadArrayList.get(7));
	}
	
	@Test
	public void testEdgesOf()
	{
		Set<Road> roads = graph.edgesOf(town[1]);
	    ArrayList<String> roadArrayList = new ArrayList<String>();
	    
	    for (Road road : roads)
	    {
	      roadArrayList.add(road.getName());
	    }
	    
	    Collections.sort(roadArrayList);
	    assertEquals("Road 1", roadArrayList.get(0));
	    assertEquals("Road 2", roadArrayList.get(1));
	}
	
	@Test
	public void testRemoveEdge()
	{
		assertEquals(true, graph.containsEdge(town[2], town[5]));
	    graph.removeEdge(town[2], town[5], 4, "Road 4");
	    assertEquals(false, graph.containsEdge(town[2], town[5]));
	}
	
	@Test
	public void testRemoveVertex()
	{
		assertEquals(true, graph.containsVertex(town[6]));
	    graph.removeVertex(town[6]);
	    assertEquals(false, graph.containsVertex(town[6]));
	}
	
	@Test
	public void testVertexSet()
	{
		Set<Town> roads = graph.vertexSet();
	    assertEquals(true, roads.contains(town[2]));
	    assertEquals(true, roads.contains(town[4]));
	    assertEquals(true, roads.contains(town[7]));
	}
	
	@Test
	public void testTown_1ToTown_6()
	{
		String beginTown = "Town 1", endTown = "Town 6";
	    Town beginIndex = null, endIndex = null;
	    Set<Town> towns = graph.vertexSet();
	    Iterator<Town> iterator = towns.iterator();
	    
	    while (iterator.hasNext())
	    {
	      Town town = iterator.next();
	      if (town.getName().equals(beginTown))
	      {
	        beginIndex = town;
	      }
	      if (town.getName().equals(endTown))
	      {
	        endIndex = town;
	      }
	    }
	    
	    if (beginIndex != null && endIndex != null)
	    {

	      ArrayList<String> path = graph.shortestPath(beginIndex, endIndex);
	      assertNotNull(path);
	      assertTrue(path.size() > 0);
	      assertEquals("Town 1 via Road 1 to Town 5 3 mi", path.get(0).trim());
	      assertEquals("Town 5 via Road 4 to Town 2 4 mi", path.get(1).trim());
	      assertEquals("Town 2 via Road 3 to Town 4 6 mi", path.get(2).trim());
	      assertEquals("Town 4 via Road 5 to Town 3 2 mi", path.get(3).trim());
	      assertEquals("Town 3 via Road 6 to Town 6 2 mi", path.get(4).trim());
	    }
	    else
	    {
	      fail("Town names are not valid");
	    }
	}
	
	@Test
	public void testTown_1ToTown_2()
	{
		String beginTown = "Town 1", endTown = "Town 2";
	    Town beginIndex = null, endIndex = null;
	    Set<Town> towns = graph.vertexSet();
	    Iterator<Town> iterator = towns.iterator();
	    
	    while (iterator.hasNext())
	    {
	      Town town = iterator.next();
	      if (town.getName().equals(beginTown))
	      {
	        beginIndex = town;
	      }
	      if (town.getName().equals(endTown))
	      {
	        endIndex = town;
	      }
	    }
	    
	    if (beginIndex != null && endIndex != null)
	    {

	      ArrayList<String> path = graph.shortestPath(beginIndex, endIndex);
	      assertNotNull(path);
	      assertTrue(path.size() > 0);
	      assertEquals("Town 1 via Road 1 to Town 5 3 mi", path.get(0).trim());
	      assertEquals("Town 5 via Road 4 to Town 2 4 mi", path.get(1).trim());
	    }
	    else
	    {
	      fail("Town names are not valid");
	    }
	}
	
	@Test
	public void testTown_6ToTown_2()
	{
		String beginTown = "Town 6", endTown = "Town 2";
	    Town beginIndex = null, endIndex = null;
	    Set<Town> towns = graph.vertexSet();
	    Iterator<Town> iterator = towns.iterator();
	    
	    while (iterator.hasNext())
	    {
	      Town town = iterator.next();
	      if (town.getName().equals(beginTown))
	      {
	        beginIndex = town;
	      }
	      if (town.getName().equals(endTown))
	      {
	        endIndex = town;
	      }
	    }
	    
	    if (beginIndex != null && endIndex != null)
	    {

	      ArrayList<String> path = graph.shortestPath(beginIndex, endIndex);
	      assertNotNull(path);
	      assertTrue(path.size() > 0);
	      assertEquals("Town 6 via Road 6 to Town 3 2 mi", path.get(0).trim());
	      assertEquals("Town 3 via Road 5 to Town 4 2 mi", path.get(1).trim());
	      assertEquals("Town 4 via Road 3 to Town 2 6 mi", path.get(2).trim());
	    }
	    else
	    {
	      fail("Town names are not valid");
	    }
	}
}
