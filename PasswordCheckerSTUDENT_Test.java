import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerSTUDENT_Test
{
	ArrayList<String> invalidPasswords;
	ArrayList<String> validPasswords;
	String password = "Geez";
	String passwordConfirm = "geez";
	String allCaps = "YELLOW";
	String withDigit = "Yellow6";
	String withSpecialChar = "Yellow1!";
	String withNoDuplicate = "Desktop&1";
	String between6And9Chars = "Wild12&";
	String longPassword = "EveryoneLovesFootball";
	
	@Before
	public void setUp() throws Exception
	{
		String[] hasInvalidPasswords = {"AAAbc12%", "ABC123%", "geez134%", "Tea1%", "Abc%Common", 
				"Germany123"};
		invalidPasswords = new ArrayList<String>();
		invalidPasswords.addAll(Arrays.asList(hasInvalidPasswords));		
		
		String[] hasValidPasswords = {"Password15@", "AAbc178?", "59Dfgjkdk(@"};
		validPasswords = new ArrayList<String>();
		validPasswords.addAll(Arrays.asList(hasValidPasswords));
	}

	@After
	public void tearDown() throws Exception
	{
		invalidPasswords = null;
		validPasswords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.isValidLength("Wildcats"));
		} 
		catch (LengthException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInvalidPasswordTooShort()
	{
		Throwable exception = Assertions.assertThrows(LengthException.class, new Executable()
				{
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isValidLength(password);
			}			
		});
		assertEquals("The password must be at least 6 characters long", exception.getMessage());
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.hasUpperAlpha("Wildcats"));
		} 
		catch (NoUpperAlphaException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInvalidPasswordNoUpperAlpha()
	{
		Throwable exception = assertThrows(NoUpperAlphaException.class, new Executable()
				{
			@Override
			public void execute() throws Throwable
			{
				PasswordCheckerUtility.hasUpperAlpha(passwordConfirm);
			}			
		});
		assertEquals("The password must contain at least one uppercase alphabetic character", exception.getMessage());	
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.hasLowerAlpha(password));
		} catch (NoLowerAlphaException e)
		{
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testInvalidPasswordNoLowerAlpha()
	{
		Throwable exception = assertThrows(NoLowerAlphaException.class, new Executable()
				{
			@Override
			public void execute() throws Throwable
			{
				PasswordCheckerUtility.hasLowerAlpha(allCaps);
			}			
		});
		assertEquals("The password must contain at least one lower case alphabetic character", exception.getMessage());
	}
	
	@Test
	public void testHasDigit() {
		try
		{
			assertTrue(PasswordCheckerUtility.hasDigit(withDigit));
		} 
		catch (NoDigitException e)
		{
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testDoesNotHaveDigit()
	{
		Throwable exception = assertThrows(NoDigitException.class, new Executable()
				{
			@Override
			public void execute() throws Throwable
			{
				PasswordCheckerUtility.hasDigit(password);
			}			
		});
		assertEquals("The password must contain at least one digit", exception.getMessage());		
	}
	
	@Test
	public void testHasSpecialChar()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.hasSpecialChar(withSpecialChar));
		} 
		catch (NoSpecialCharacterException e)
		{
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testDoesNotHaveSpecialChar()
	{
		Throwable exception = assertThrows(NoSpecialCharacterException.class, new Executable()
				{
			@Override
			public void execute() throws Throwable
			{
				PasswordCheckerUtility.hasSpecialChar(password);
			}			
		});
		assertEquals("The password must contain at least one special character", exception.getMessage());		
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.isWeakPassword(between6And9Chars));	
			assertTrue(PasswordCheckerUtility.isWeakPassword(withSpecialChar));	
			assertFalse(PasswordCheckerUtility.isWeakPassword(longPassword));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.hasSpecialChar(withNoDuplicate));
		} 
		catch (NoSpecialCharacterException e)
		{
			e.printStackTrace();
		}	
	}
	
	@Test
	public void testInvalidPasswordInvalidSequence()
	{
		Throwable exception = assertThrows(InvalidSequenceException.class, new Executable()
				{
			@Override
			public void execute() throws Throwable
			{
				PasswordCheckerUtility.hasSameCharInSequence("CCC32%dh");
			}			
		});
		assertEquals("The password cannot contain more than two of the same character in sequence", exception.getMessage());
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordDigit()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.hasDigit(withDigit));
		} 
		catch (NoDigitException e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInvalidPasswordNoDigit()
	{
		Throwable exception = assertThrows(NoDigitException.class, new Executable()
				{
			@Override
			public void execute() throws Throwable
			{
				PasswordCheckerUtility.hasDigit(password);
			}			
		});
		assertEquals("The password must contain at least one digit", exception.getMessage());	
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try
		{
			assertTrue(PasswordCheckerUtility.isValidPassword(between6And9Chars));	
			assertTrue(PasswordCheckerUtility.isValidPassword(withSpecialChar));	
			assertFalse(PasswordCheckerUtility.isValidPassword(longPassword));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords()
	{
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(invalidPasswords);

		assertEquals(results.size(), 6);
		assertEquals(results.get(0), "AAAbc12% -> The password cannot contain more than two of the same character in sequence");
		assertEquals(results.get(1), "ABC123% -> The password must contain at least one lower case alphabetic character");
		assertEquals(results.get(2), "geez134% -> The password must contain at least one uppercase alphabetic character");
		assertEquals(results.get(3), "Tea1% -> The password must be at least 6 characters long");
		assertEquals(results.get(4), "Abc%Common -> The password must contain at least one digit"); 
		assertEquals(results.get(5), "Germany123 -> The password must contain at least one special character");
	}
	
	@Test
	public void testValidPasswords()
	{
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(validPasswords);
		assertTrue(results.isEmpty());
	}
}