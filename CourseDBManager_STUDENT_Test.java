import static org.junit.Assert.*;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CourseDBManager_STUDENT_Test
{
	private CourseDBManagerInterface dataManager = new CourseDBManager();
	
	@Before
	public void setUp() throws Exception
	{
		dataManager = new CourseDBManager();
	}
	
	@After
	public void tearDown() throws Exception
	{
		dataManager = null;
	}
	
	@Test
	public void testAddToDB()
	{
		try
		{
			dataManager.add("CMSC100",30169,3,"SC200","Jack Black");
			assertEquals(1, dataManager.showAll().size());
		}
		catch(Exception e)
		{
			fail("This should not have caused an Exception");
		}
	}
	
	@Test
	public void testShowAll()
	{
		dataManager.add("CMSC100",30169,3,"SC200","Jack Black");
		dataManager.add("CMSC140",34495,3,"SC350","Jonah Hill");
		dataManager.add("CMSC203",31420,4,"SC450","Benjamin Franklin");
		ArrayList<String> list = dataManager.showAll();
		
		assertEquals(list.get(0),"\nCourse:CMSC203 CRN:31420 Credits:4 Instructor:Benjamin Franklin Room:SC450");
		assertEquals(list.get(1),"\nCourse:CMSC140 CRN:34495 Credits:3 Instructor:Jonah Hill Room:SC350");
		assertEquals(list.get(2),"\nCourse:CMSC100 CRN:30169 Credits:3 Instructor:Jack Black Room:SC200");
	}
	
	@Test
	public void testRead()
	{
		try
		{
			File inputFile = new File("TestStudent.txt");
			PrintWriter inFile = new PrintWriter(inputFile);
			inFile.println("CMSC100 30169 3 SC200 Jack Black");
			inFile.print("CMSC140 34495 3 SC350 Jonah Hill");
			
			inFile.close();
			dataManager.readFile(inputFile);
		}
		catch (Exception e)
		{
			fail("Should not have thrown an exception");
		}
	}
}
