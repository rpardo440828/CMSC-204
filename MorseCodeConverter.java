import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * This class converts morse code into english
 * @author rpard
 */
public class MorseCodeConverter
{
	private static MorseCodeTree morseTree = new MorseCodeTree();
	
	/**
	 * Returns a string with all the data in the tree in LNR order with an 
	 * space in between them. Uses the toArrayList method in MorseCodeTree
	 * @return the data in the tree in LNR order separated by a space.
	 */
	public static String printTree()
	{
		ArrayList<String> morseTreeList = morseTree.toArrayList();
		String result = "";
		
		for(String letters: morseTreeList)
		{
			result += letters + " ";
		}
		
		return result;
	}
	
	/**
	 * Converts Morse code into English Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	 * @param code the morse code
	 * @return the English translation
	 */
	public static String convertToEnglish(String code)
	{
		String[] letter;
		String[] word = code.split(" / ");
		String result = "";
		
		for(String ch: word)
		{
			letter = ch.split(" ");
			for(String temp: letter)
			{
				result += morseTree.fetch(temp);
			}
			result += " ";
		}
		
		return result.trim();
	}
	
	/**
	 * Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	 * @param codeFile name of the File that contains Morse Code
	 * @return the English translation of the file
	 * @throws FileNotFoundException if the file is not found
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException
	{
		StringBuilder sb = new StringBuilder();
	    InputStream in = new FileInputStream(codeFile);
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));

	    br.lines().forEach(s -> sb.append(convertToEnglish(s)).append("\n"));
	    try
	    {
	      br.close();
	    }
	    catch (IOException e)
	    {
	      e.printStackTrace();
	    }
	    return sb.toString().trim();
	}
}
