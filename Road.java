/**
 * This class represents a road as an edge on the graph.
 * @author rpard
 */
public class Road implements Comparable<Road>
{
	private Town source, destination;
	private int distance;
	private String name;
	
	/**
	 * Constructor sets all parameters
	 * @param source one town on the road
	 * @param destination another town on the road
	 * @param distance weight of the edge/distance from one town to the other
	 * @param name name of the road
	 */
	public Road(Town source, Town destination, int distance, String name)
	{
		this.source = source;
		this.destination = destination;
		this.distance = distance;
		this.name = name;
	}
	
	/**
	 * Constructor sets all parameters besides the distance
	 * @param source one town on the road
	 * @param destination another town on the road
	 * @param name the name of the road
	 */
	public Road(Town source, Town destination, String name)
	{
		this.source = source;
		this.destination = destination;
		this.distance = 1;
		this.name = name;
	}
	
	/**
	 * Returns true only if the edge contains the given town
	 * @param town a vertex on the graph
	 * @return true only if the edge is connected to the given vertex.
	 */
	public boolean contains(Town town)
	{
		return this.source.getName().equals(town.getName()) 
				|| this.destination.getName().equals(town.getName());
	}
	
	/**
	 * Returns the string representation of the road
	 */
	@Override
	public String toString()
	{
		return this.source.getName() + " via " + this.name + " to " + this.destination.getName() 
		+ " " + this.distance + " mi";
	}
	
	/**
	 * Returns the name of the road
	 * @return the name of the road
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Returns the second town on the road
	 * @return the second town on the road
	 */
	public Town getDestination()
	{
		return this.destination;
	}
	
	/**
	 * Returns the first town on the road
	 * @return the first town on the road
	 */
	public Town getSource()
	{
		return this.source;
	}
	
	/**
	 * Returns 0 if the road names are the same, positive or negative integer otherwise.
	 */
	@Override
	public int compareTo(Road o)
	{
		return this.name.compareTo(o.name);
	}
	
	/**
	 * Returns the distance of the road
	 * @return the distance of the road
	 */
	public int getWeight()
	{
		return this.distance;
	}
	
	/**
	 * Returns true if each of the ends of the road obj is the same as the ends of this road.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		if(obj == this)
		{
			return true;
		}
		if(!(obj instanceof Road))
		{
			return false;
		}
		
		Road r = (Road) obj;
		return (this.source.equals(r.source) && this.destination.equals(r.destination)
				|| this.source.equals(r.destination) && this.destination.equals(r.source));
	}
}
