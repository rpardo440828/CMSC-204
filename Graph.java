import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents a graph that is populated with
 * Towns (vertex) and roads (edge).
 * @author rpard
 */
public class Graph implements GraphInterface<Town, Road>
{
	private ArrayList<String> shortestPath = new ArrayList<>();
	private Set<Town> towns = new HashSet<>();
	private Set<Road> roads = new HashSet<>();
	private Town destination;

	/**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex)
	{
		if(sourceVertex == null || destinationVertex == null)
		{
			return null;
		}
		
		Road road = null;
		for(Road r: this.roads)
		{
			if(r.contains(sourceVertex) && r.contains(destinationVertex))
			{
				road = r;
			}
		}
		return road;
	}

	/**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * @param sourceVertex source vertex of the edge.
     * 
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     * @return The newly created edge if added to the graph, otherwise null.
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) 
													throws IllegalArgumentException, NullPointerException
	{
		if(sourceVertex == null || destinationVertex == null)
		{
			throw new NullPointerException();
		}
		if(!containsVertex(sourceVertex) || !containsVertex(destinationVertex))
		{
			throw new IllegalArgumentException();
		}
		
		Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
		this.roads.add(newRoad);
		return newRoad;
	}

	/**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     * @return true if this graph did not already contain the specified vertex.
     * @throws NullPointerException if the specified vertex is null.
     */
	@Override
	public boolean addVertex(Town v) throws NullPointerException
	{
		if(v == null)
		{
			throw new NullPointerException();
		}
		
		if(!towns.contains(v))
		{
			towns.add(v);
			return true;
		}
		return false;
	}

	/**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @return true if this graph contains the specified edge.
     */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex)
	{
		if(sourceVertex == null || destinationVertex == null)
		{
			return false;
		}
		
		for(Road road: this.roads)
		{
			if(road.contains(sourceVertex) && road.contains(destinationVertex))
			{
				return true;
			}
		}
		return false;
	}

	 /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     * @return true if this graph contains the specified vertex.
     */
	@Override
	public boolean containsVertex(Town v)
	{
		return this.towns.contains(v);
	}

	/**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	@Override
	public Set<Road> edgeSet()
	{
		return this.roads;
	}

	/**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to bereturned.
     * @return a set of all edges touching the specified vertex.
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	@Override
	public Set<Road> edgesOf(Town vertex) throws IllegalArgumentException, NullPointerException
	{
		if(vertex == null)
		{
			throw new NullPointerException();
		}
		if(!this.towns.contains(vertex))
		{
			throw new IllegalArgumentException();
		}
		
		Set<Road> roadSet = new HashSet<>();
		for(Road r: this.roads)
		{
			if(r.contains(vertex))
			{
				roadSet.add(r);
			}
		}
		return roadSet;
	}

	 /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     * @return The removed edge, or null if no edge removed.
     */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description)
	{
		Road road = null;
		for(Road r: this.roads)
		{
			if(r.contains(sourceVertex) && r.contains(destinationVertex) && (weight > -1) && description != null)
			{
				road = r;
			}
		}
		
		if(this.roads.remove(road))
		{
			return road;
		}
		return null;
	}

	/**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	@Override
	public boolean removeVertex(Town v)
	{
		if(v == null)
		{
			return false;
		}
		return this.towns.remove(v);
	}

	/**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     * @return a set view of the vertices contained in this graph.
     */
	@Override
	public Set<Town> vertexSet()
	{
		return this.towns;
	}

    /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * 
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */ 
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex)
	{
		this.destination = destinationVertex;
		dijkstraShortestPath(sourceVertex);
		ArrayList<Road> roadPath = new ArrayList<>();
		boolean anySource = false;
		boolean anyDestination = false;
		
		for(Road r: this.roads)
		{
			if(r.contains(sourceVertex))
			{
				anySource = true;
			}
		}
		for(Road j: this.roads)
		{
			if(j.contains(destinationVertex))
			{
				anyDestination = true;
			}
		}
		
		if(!anySource || !anyDestination)
		{
			return new ArrayList<>();
		}
		
		for(int i = 0; i < this.shortestPath.size() - 1; i++)
		{
			Town source = new Town(this.shortestPath.get(i));
			Town destination = new Town(this.shortestPath.get(i + 1));
			Road road = getEdge(source, destination);
			roadPath.add(new Road(source, destination, road.getWeight(), road.getName()));
		}
		this.shortestPath.clear();
		return roadPath.stream().map(Road::toString).collect(Collectors.toCollection(ArrayList::new));
	}

	/**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	@Override
	public void dijkstraShortestPath(Town sourceVertex)
	{
		List<Town> vertices = new ArrayList<>(towns);

	    int[][] adjacencyMatrix = new int[towns.size()][towns.size()];

	    for (int i = 0; i < adjacencyMatrix.length; i++)
	    {
	      for (int j = 0; j < adjacencyMatrix[i].length; j++)
	      {
	        if (i == j || !containsEdge(vertices.get(i), vertices.get(j)))
	        {
	          adjacencyMatrix[i][j] = 0;
	        }
	        else
	        {
	          int distance = getEdge(vertices.get(i), vertices.get(j)).getWeight();
	          adjacencyMatrix[i][j] = adjacencyMatrix[j][i] = distance;
	        }
	      }
	    }

	    int startTown = 0;
	    for (Town t : vertices)
	    {
	      if (!t.equals(sourceVertex))
	      {
	        startTown++;
	      }
	      else
	      {
	        break;
	      }
	    }

	    int endTown = 0;
	    for (Town t : vertices)
	    {
	      if (!t.equals(destination))
	      {
	        endTown++;
	      }
	      else
	      {
	        break;
	      }
	    }

	    int numTowns = adjacencyMatrix[0].length;
	    int[] shortDistances = new int[numTowns];
	    boolean[] visited = new boolean[numTowns];

	    for (int t = 0; t < numTowns; t++)
	    {
	      shortDistances[t] = Integer.MAX_VALUE;
	      visited[t] = false;
	    }

	    shortDistances[startTown] = 0;
	    int[] minPathLengths = new int[numTowns];
	    minPathLengths[startTown] = -1;

	    for (int i = 1; i < numTowns; i++)
	    {
	      int nearestTown = -1;
	      int minDistance = Integer.MAX_VALUE;
	      
	      for (int townIndex = 0; townIndex < numTowns; townIndex++)
	      {
	        if (!visited[townIndex] && shortDistances[townIndex] < minDistance)
	        {
	          nearestTown = townIndex;
	          minDistance = shortDistances[townIndex];
	        }
	      }

	      visited[nearestTown] = true;

	      for (int townIndex = 0; townIndex < numTowns; townIndex++)
	      {
	        int roadDistance = adjacencyMatrix[nearestTown][townIndex];
	        
	        if (roadDistance > 0 && ((minDistance + roadDistance) < shortDistances[townIndex]))
	        {
	          minPathLengths[townIndex] = nearestTown;
	          shortDistances[townIndex] = minDistance + roadDistance;
	        }
	      }
	      
	    }
	    findMinPath(endTown, minPathLengths);
	  }

	private void findMinPath(int source, int[] minPathLengths)
	{
		if(source == -1)
		{
			return;
		}
		findMinPath(minPathLengths[source], minPathLengths);
		
		int index = 0;
		for(Town town: this.towns)
		{
			if(index == source)
			{
				this.shortestPath.add(town.getName());
			}
			index++;
		}
	}
	
}
