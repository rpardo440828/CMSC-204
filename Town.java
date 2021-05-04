import java.util.ArrayList;

/**
 * This class represents a town as a node of the graph.
 * @author rpard
 */
public class Town implements Comparable<Town>
{
	private String name;
	private ArrayList<Town> adjTowns;
	
	/**
	 * Constructor that sets the name of the town.
	 * @param name the name of the town
	 */
	public Town(String name)
	{
		this.name = name;
	}
	
	/**
	 * Constructor that copies another town.
	 * @param templateTown the town to copy
	 */
	public Town(Town templateTown)
	{
		this.name = templateTown.name;
	}
	
	/**
	 * Returns the name of the town.
	 * @return the name of the town
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Returns 0 if the two towns are equal, and a positive or negative integer otherwise.
	 */
	@Override
	public int compareTo(Town o)
	{
		return this.name.compareTo(o.name);
	}
	
	/**
	 * Returns the String representation of the town.
	 */
	@Override
	public String toString()
	{
		return this.name;
	}
	
	/**
	 * Returns the hashcode for the name of the town.
	 */
	@Override
	public int hashCode()
	{
		return this.name.hashCode();
	}
	
	/**
	 * Compares an object to the current instance of town. 
	 * Returns true if the town names are equal, false otherwise.
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
		if(!(obj instanceof Town))
		{
			return false;
		}
		
		Town t = (Town) obj;
		return this.name.equals(t.name);
	}
}
