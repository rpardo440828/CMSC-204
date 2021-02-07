import java.util.ArrayList;
import java.util.regex.Matcher; 
import java.util.regex.Pattern;

/**
 * This class determines if a password is valid with set requirements, as well is if the password is weak or strong.
 * @author rpard
 */
public class PasswordCheckerUtility
{
	/**
	 * Default Constructor
	 */
	public PasswordCheckerUtility()
	{
		
	}
	
	/**
	 * Compare equality of two passwords
	 * @param password Password string to be checked for
	 * @param passwordConfirm String to be checked against password for
	 * @throws UnmatchedException Thrown if passwords do not match
	 */
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException
	{
			boolean equal = comparePasswordsWithReturn(password, passwordConfirm);
			if(equal == false)
			{
				throw new UnmatchedException();
			}
	}
	
	/**
	 * Compare equality of two passwords with a return
	 * @param password Password string to be checked for
	 * @param passwordConfirm String to be checked against password for
	 * @return true if passwords are equal, false otherwise
	 */
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm)
	{
		boolean status = false;
		
		if(password.equals(passwordConfirm))
		{
			status = true;
		}
		
		return status;
	}
	
	/**
	 * Reads a file of passwords and the passwords that failed the check will be added to an invalidPasswords with the reason
	 * @param passwords List of passwords
	 * @return invalidPasswords ArrayList of invalid passwords in the correct format
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords)
	{
		ArrayList<String> invalidPasswords = new ArrayList<>();
		
		for(String s : passwords)
		{
			try
			{
				isValidPassword(s);
			}
			catch(Exception e)
			{
				invalidPasswords.add(s + " -> " + e.getMessage());
			}
		}
		
		return invalidPasswords;
	}
	
	/**
	 * Password contains 6 to 9 characters , still considers valid, just weak
	 * @param password Password to be checked
	 * @return true if password is between 6 to 9 characters, false otherwise
	 */
	public static boolean hasBetweenSixAndNineChars(String password)
	{
		boolean status = false;
		
		if(password.length() <= 9)
		{
			status = true;
		}
		
		return status;
	}
	
	/**
	 * Checks the password Digit requirement, password must contain a numeric character
	 * @param password Passwords to be checked
	 * @return true if digit requirement is met
	 * @throws NoDigitException Thrown if there is not at least one digit
	 */
	public static boolean hasDigit(String password) throws NoDigitException
	{
			char[] ch = new char[password.length()];
			
			for(int i = 0; i < password.length(); i++)
			{
				ch[i] = password.charAt(i);
			}
			
			for(Character c : ch)
			{
				if(Character.isDigit(c))
				{
					return true;
				}
			}
			
			throw new NoDigitException();
	}
	
	/**
	 * Checks the password lower case requirement, password must contain a lower case alpha character
	 * @param password Password to be checked
	 * @return true if there is at least one lower case character
	 * @throws NoLowerAlphaException Thrown if lower case requirement is not met
	 */
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException
	{
			char[] ch = new char[password.length()];
			
			for(int i = 0; i < password.length(); i++)
			{
				ch[i] = password.charAt(i);
			}
			
			for(Character c : ch)
			{
				if(Character.isLowerCase(c))
				{
					return true;
				}
			}
			
			throw new NoLowerAlphaException();
		
	}
	
	/**
	 * Checks the password Sequence requirement, password should not contain more than 2 of the same character in sequence
	 * @param password Password to be checked
	 * @return true if password does not contain more than 2 of the same character in sequence
	 * @throws InvalidSequenceException Thrown if sequence requirement is not met
	 */
	public static boolean hasSameCharInSequence(String password) throws InvalidSequenceException
	{
		for (int i = 0; i < password.length() - 2; i++)
		{
		      if (password.charAt(i) == password.charAt(i + 1))
		      {
		        if (password.charAt(i + 1) == password.charAt(i + 2))
		        {
					throw new InvalidSequenceException();
		        }
		      }
		}
			
			return true;
	}
	
	/**
	 * Checks the password SpecialCharacter requirement, password must contain a Special Character
	 * @param password Password to be checked
	 * @return true if password contains at least one special character
	 * @throws NoSpecialCharacterException Thrown if special character requirement is not met
	 */
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException
	{
		Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(password);
        boolean status = matcher.find();
        
        if(status)
        {
            return true;
        }
        else    
        {
            throw new NoSpecialCharacterException();
        }
	}
	
	/**
	 * Checks the password alpha character requirement, password must contain an upper case alpha character
	 * @param password Password to be checked
	 * @return true if password contains at least one upper case character
	 * @throws NoUpperAlphaException Thrown if upper case requirement is not met
	 */
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException
	{
		
			char[] ch = new char[password.length()];
			
			for(int i = 0; i < password.length(); i++)
			{
				ch[i] = password.charAt(i);
			}
			
			for(Character c : ch)
			{
				if(Character.isUpperCase(c))
				{
					return true;
				}
			}
			
			throw new NoUpperAlphaException();
	}
	
	/**
	 * Checks the password length requirement, password must be at least 6 characters long
	 * @param password Password to be checked
	 * @return true if password is greater than or equal to 6 characters
	 * @throws LengthException Thrown if length requirement is not met
	 */
	public static boolean isValidLength(String password) throws LengthException
	{
		if(password.length() >= 6)
		{
			return true;
		}
		else
		{
			throw new LengthException();
		}
	}
	
	/**
	 * Return true if valid password, returns false if an invalid password
	 * @param password Password to be checked
	 * @return true if valid password, false otherwise
	 * @throws LengthException Thrown if length requirement is not met
	 * @throws NoUpperAlphaException Thrown if upper case requirement is not met
	 * @throws NoLowerAlphaException Thrown if lower case requirement is not met
	 * @throws NoDigitException Thrown if digit requirement is not met
	 * @throws NoSpecialCharacterException Thrown if special character requirement is not met
	 * @throws InvalidSequenceException Thrown if sequence requirement is not met
	 */
	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException
	{
		return isValidLength(password) && hasUpperAlpha(password) && hasLowerAlpha(password)
				&& hasDigit(password) && hasSpecialChar(password) && hasSameCharInSequence(password);
	}
	
	/**
	 * 
	 * @param password Password to be checked
	 * @return true if password is weak, false otherwise
	 * @throws WeakPasswordException Thrown if password is weak
	 */
	public static boolean isWeakPassword(String password) throws WeakPasswordException
	{
		return isValidPassword(password) && hasBetweenSixAndNineChars(password);
		
	}
	
}
