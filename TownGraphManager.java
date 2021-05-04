import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * This class will manage the graph class.
 * @author rpard
 */
public class TownGraphManager implements TownGraphManagerInterface
{
	private Graph graph = new Graph();
	
	/**
	 * Adds a road with 2 towns and a road name
	 * 
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName)
	{
		if(this.graph.addEdge(new Town(town1), new Town(town2), weight, roadName) != null)
		{
			return true;
		}
		return false;
	}

	/**
	 * Returns the name of the road that both towns are connected through
	 * 
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	@Override
	public String getRoad(String town1, String town2)
	{
		return this.graph.getEdge(new Town(town1), new Town(town2)).getName();
	}

	/**
	 * Adds a town to the graph
	 * 
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	@Override
	public boolean addTown(String v)
	{
		return this.graph.addVertex(new Town(v));
	}

	/**
	 * Gets a town with a given name
	 * 
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	@Override
	public Town getTown(String name)
	{
		return this.graph.vertexSet().stream().filter(town -> town.getName().equals(name)).findAny().orElse(null);
	}

	/**
	 * Determines if a town is already in the graph
	 * 
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String v)
	{
		return this.graph.containsVertex(new Town(v));
	}

	/**
	 * Determines if a road is in the graph
	 * 
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2)
	{
		return this.graph.containsEdge(new Town(town1), new Town(town2));
	}

	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads()
	{
		return this.graph.edgeSet().stream().map(Road::getName).sorted().collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Deletes a road from the graph
	 * 
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road)
	{
		return this.graph.removeEdge(new Town(town1), new Town(town2), 0, road) != null;
	}

	/**
	 * Deletes a town from the graph
	 * 
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	@Override
	public boolean deleteTown(String v)
	{
		return this.graph.removeVertex(new Town(v));
	}

	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	@Override
	public ArrayList<String> allTowns()
	{
		return this.graph.vertexSet().stream().map(Town::getName).sorted().collect(Collectors.toCollection(ArrayList::new));
	}

	/**
	 * Returns the shortest path from town 1 to town 2
	 * 
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2)
	{
		return this.graph.shortestPath(new Town(town1), new Town(town2));
	}
	
	public void populateTownGraph(File file) throws FileNotFoundException, IOException
	{
		InputStream in = new FileInputStream(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		br.lines().map(s -> s.split(";|\\,")).forEach(ar -> {addTown(ar[2]);
		addTown(ar[3]); addRoad(ar[2], ar[3], Integer.parseInt(ar[1]), ar[0]);});
		br.close();
	}
	
}
