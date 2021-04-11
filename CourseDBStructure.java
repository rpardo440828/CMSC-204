import java.io.IOException;
import java.util.LinkedList;

/**
 * This class structures the course data using hash tables with buckets.
 * @author rpard
 */
public class CourseDBStructure implements CourseDBStructureInterface
{
	protected LinkedList<CourseDBElement>[] hashTable;
	
	public CourseDBStructure(int numOfCourses)
	{
		hashTable = new LinkedList[numOfCourses];
	}
	
	
	public CourseDBStructure(String s, int numOfCourses)
	{
		hashTable = new LinkedList[numOfCourses];
	}
	
	/** 
	* Use the hashcode of the CourseDatabaseElement to see if it is 
	* in the hashtable.
	* 
	* If the CourseDatabaseElement does not exist in the hashtable,
	* add it to the hashtable.
	* 
	* @param element the CDE to be added
	*/
	@Override
	public void add(CourseDBElement element)
	{
		int index = getHashIndex(element);
		
		if(this.hashTable[index] == null)
		{
			this.hashTable[index] = new LinkedList<CourseDBElement>();
			this.hashTable[index].add(element);
		}
		else
		{
			if(hashTable[index].contains(element))
			{
				return;
			}
			else
			{
				hashTable[index].add(element);
			}
		}
	}
	
	/** 
	* Use the hashcode of the CourseDatabaseElement to see if it is 
	* in the hashtable.
	* 
	* If the CourseDatabaseElement is in the hashtable, return it
	* If not, throw an IOException
	* 
	* @param element the CDE to be added
	 * @throws IOException 
	*/
	@Override
	public CourseDBElement get(int crn) throws IOException
	{
		CourseDBElement temp = new CourseDBElement();
		
		temp.setCRN(crn);
		int i = getHashIndex(temp);
		LinkedList<CourseDBElement> list = this.hashTable[i];
		
		return list.stream().filter(c -> c.getCRN() == crn).findAny().orElseThrow(IOException::new);
	}

	/**
	* Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	* @return size of the hash table
	*/
	@Override
	public int getTableSize()
	{
		return this.hashTable.length;
	}
	
	/**
	 * This method computes the index where the element is located and returns it.
	 * @param cde the element to compare
	 * @return the index of the element
	 */
	private int getHashIndex(CourseDBElement cde)
	{
		int index = cde.hashCode() % this.hashTable.length;
		
		if(index < 0)
		{
			index += this.hashTable.length;
		}
		return index;
	}
}
