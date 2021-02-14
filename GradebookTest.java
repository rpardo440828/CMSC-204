import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradebookTest
{
	private GradeBook g1;
	private GradeBook g2;

	@BeforeEach
	void setUp() throws Exception
	{
		g1 = new GradeBook(5);
		g1.addScore(79);
		g1.addScore(95);
		g1.addScore(55);
		
		g2 = new GradeBook(5);
		g2.addScore(88);
		g2.addScore(92);
		g2.addScore(64);
	}

	@AfterEach
	void tearDown() throws Exception
	{
		g1 = null;
		g2 = null;
	}

	@Test
	void testAddScore()
	{
		assertTrue(g1.toString().equals("79.0 95.0 55.0 "));
		assertEquals(3, g1.getScoreSize(), .001);
		
		assertTrue(g2.toString().equals("88.0 92.0 64.0 "));
		assertEquals(3, g2.getScoreSize(), .001);
	}

	@Test
	void testSum()
	{
		assertEquals(229, g1.sum(), .0001);
		assertEquals(244, g2.sum(), .0001);
	}

	@Test
	void testMinimum()
	{
		assertEquals(55, g1.minimum(), .001);
		assertEquals(64, g2.minimum(), .001);
	}

	@Test
	void testFinalScore()
	{
		assertEquals(174, g1.finalScore(), .0001);
		assertEquals(180, g2.finalScore(), .0001);
	}


}
