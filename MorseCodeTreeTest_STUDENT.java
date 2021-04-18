import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MorseCodeTreeTest_STUDENT
{
	File inputFile;
	MorseCodeTree tree;
	
	@BeforeEach
	public void setUp() throws Exception
	{
		tree = new MorseCodeTree();
	}

	@AfterEach
	public void tearDown() throws Exception
	{
		tree = null;
	}

	@Test
	public void testInsertAndAdd()
	{
		tree.insert("....", "h");
		assertEquals(tree.fetch("...."), "h");
		
		tree.insert(".", "e");
		assertEquals(tree.fetch("."), "e");
	}
	
	@Test
	public void testFetchAndFetchNode()
	{
		String s1;
		String s2;
		
		tree.insert("....", "h");
		s1 = tree.fetch("....");
		assertEquals(s1, "h");
		
		tree.insert(".", "e");
		s2 = tree.fetch(".");
		assertEquals(s2, "e");
	}

}
