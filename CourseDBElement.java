
/**
 * Class implements comparable, and represents the course object
 * @author rpard
 *
 */
public class CourseDBElement implements Comparable<CourseDBElement>
{
	private String courseId;
	private int crn;
	private int numOfCredits;
	private String roomNum;
	private String instructor;
	
	/**
	 * Parameterized constructor
	 * @param courseId the id of the course
	 * @param crn the crn of the course
	 * @param numOfCredits the number of credits for the course
	 * @param roomNum the room number
	 * @param instructor the instructor's name
	 */
	public CourseDBElement(String courseId, int crn, int numOfCredits, String roomNum, String instructor)
	{
		this.courseId = courseId;
		this.crn = crn;
		this.numOfCredits = numOfCredits;
		this.roomNum = roomNum;
		this.instructor = instructor;
	}
	
	/**
	 * Default constructor
	 */
	public CourseDBElement()
	{
		this.courseId = "";
		this.crn = 0;
		this.numOfCredits = 0;
		this.roomNum = "";
		this.instructor = "";
	}
	
	/**
	 * Sets the id of the course
	 * @param courseId the id of the course
	 */
	public void setCourseId(String courseId)
	{
		this.courseId = courseId;
	}
	
	/**
	 * Returns the id of the course
	 * @return the id of the course
	 */
	public String getCourseId()
	{
		return this.courseId;
	}
	
	/**
	 * Sets the crn of the course
	 * @param crn the crn of the course
	 */
	public void setCRN(int crn)
	{
		this.crn = crn;
	}
	
	/**
	 * Returns the crn of the course
	 * @return the crn of the course
	 */
	public int getCRN()
	{
		return this.crn;
	}
	
	/**
	 * Sets the number of credits for the course
	 * @param numOfCredits the number of credits for the course
	 */
	public void setNumOfCredits(int numOfCredits)
	{
		this.numOfCredits = numOfCredits;
	}
	
	/**
	 * Returns the number of credits for the course
	 * @return the number of credits for the course
	 */
	public int getNumOfCredits()
	{
		return this.numOfCredits;
	}
	
	/**
	 * Sets the room number
	 * @param roomNum the room number
	 */
	public void setRoomNum(String roomNum)
	{
		this.roomNum = roomNum;
	}
	
	/**
	 * Returns the room number
	 * @return the room number
	 */
	public String getRoomNum()
	{
		return this.roomNum;
	}
	
	/**
	 * Sets the instructor's name
	 * @param instructor the instructor's name
	 */
	public void setInstructor(String instructor)
	{
		this.instructor = instructor;
	}
	
	/**
	 * Returns the instructor's name
	 * @return the instructor's name
	 */
	public String getInstructor()
	{
		return this.instructor;
	}
	
	/**
	 * Returns the element as a string
	 * @return the String representation of the element
	 */
	@Override
	public String toString()
	{
		return "\nCourse:" + this.courseId + " CRN:" + this.crn + " Credits:" + this.numOfCredits
				+ " Instructor:" + this.instructor + " Room:" + this.roomNum;
	}
	
	/**
	 * Compares CRN to determine if objects are equal.
	 * @param obj object to compare
	 * @return true if crn is equal, false otherwise
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
		if(!(obj instanceof CourseDBElement))
		{
			return false;
		}
		
		CourseDBElement cde = (CourseDBElement) obj;
		return this.getCRN() == cde.getCRN();
	}

	/**
	 * Compares crn of two courses.
	 * @param cde element to compare
	 */
	@Override
	public int compareTo(CourseDBElement cde)
	{
		return Integer.compare(this.getCRN(), cde.getCRN());
	}
	
	/**
	 * Computes hashcode and returns it
	 * @return the hashcode computation
	 */
	@Override
	public int hashCode()
	{
		String s = String.valueOf(getCRN());
		long hash = 0;
		int prime = 31;
		
		for(int i = 0; i < s.length(); i++)
		{
			hash = prime * hash + s.charAt(i);
		}
		return (int) hash;
	}
}
