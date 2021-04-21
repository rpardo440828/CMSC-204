import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class CarQueue
{
	Queue<Integer> direction;
	Random ran;
	
	public CarQueue()
	{
		direction = new LinkedList<>();
		ran = new Random();
		direction.add(0);
		direction.add(2);
		direction.add(1);
		direction.add(3);
		direction.add(0);
	}
	
	public void addToQueue()
	{
		class RandomDirection implements Runnable
		{
			public void run()
			{
				try
				{
					for(int i = 0; i < 1000; i++)
					{
						int rVal = ran.nextInt(4);
						direction.add(rVal);
						Thread.sleep(50);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
		Runnable run = new RandomDirection();
		Thread t = new Thread(run);
		t.start();
	}
	
	public int deleteQueue()
	{
		return direction.remove();
	}
}
