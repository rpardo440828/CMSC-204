
public class ArraySum
{
	public int sumOfArray(Integer[] a, int index)
	{
		int result = 0;
		
		if(index < 0)
		{
			return result;
		}
		else if(index <= a.length)
		{
			result = a[index] + sumOfArray(a, index - 1);
		}
		
		return result;
	}
}
