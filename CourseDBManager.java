import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This data manager allows user to enter data in the GUI, print elements, and read courses from a text file.
 * @author rpard
 */
public class CourseDBManager implements CourseDBManagerInterface
{
	private CourseDBStructure cds;
	
	/**
	 * Default constructor
	 */
	public CourseDBManager()
	{
		cds = new CourseDBStructure(10);
	}

	/**
	 * This method calls the cds add method to create a cde element and add it to the data structure
	 * @param id the id of the course
	 * @param crn the crn number of the course
	 * @param credits the number of credits for the course
	 * @param roomNum the room number
	 * @param instructor the instructor's name
	 */
	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor)
	{
		CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
		cds.add(element);
	}

	/**
	 * This method calls the cds get method to retrieve the cde element and it handles an exception if it occurs
	 * @param crn the crn of the course
	 * @return the cde element if found, null otherwise
	 */
	@Override
	public CourseDBElement get(int crn)
	{
		try
		{
			return cds.get(crn);	
		}
		catch(IOException e)
		{
			System.out.println("Exception was thrown in get crn methond in Manager");
			e.getMessage();
		}
		return null;
	}

	/**
	 * This method reads courses from a text file and converts them to a course element,
	 * and it adds the element to the structure.
	 * @param input the file that contains the list of courses
	 * @throws FileNotFoundException if file is not found
	 */
	@Override
	public void readFile(File input) throws FileNotFoundException
	{
		InputStream in = new FileInputStream(input);
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));

	    List<String[]> list = br.lines().map(s -> s.split(" ")).collect(Collectors.toList());
	    for (String[] ar : list)
	    {
	      if (ar.length > 5)
	      {
	        StringBuilder instructor = new StringBuilder();
	        for (int i = 4; i < ar.length; i++)
	        {
	          instructor.append(ar[i] + " ");
	        }
	        cds.add(new CourseDBElement(ar[0], Integer.valueOf(ar[1]), Integer.valueOf(ar[2]), ar[3],
	            instructor.toString().trim()));
	      } 
	      else 
	      {
	        cds.add(new CourseDBElement(ar[0], Integer.valueOf(ar[1]), Integer.valueOf(ar[2]), ar[3],
	            ar[4]));
	      }
	    }
	}

	/**
	 * This method goes through each element and converts the list into an Array list.
	 * @return an ArrayList of all the elements in the data structure
	 */
	@Override
	public ArrayList<String> showAll()
	{
		ArrayList<CourseDBElement> temp = new ArrayList<>();
		ArrayList<String> returnList = new ArrayList<>();
		
		for(int i = 0; i < this.cds.getTableSize(); i++)
		{
			if(this.cds.hashTable[i] != null)
			{
				temp.addAll(this.cds.hashTable[i]);
			}
		}
		
		for(int j = 0; j < temp.size(); j++)
		{
			returnList.add(temp.get(j).toString());
		}
		
		return returnList;
	}
	
}
