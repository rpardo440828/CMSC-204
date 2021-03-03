/**
 * This utility class converts infix to postfix notation and vice versa, it also evaluates the expression
 * @author rpard
 *
 */
public class Notation
{
	private static String operators = "+-*/";
	private static NotationQueue<String> queue;
	private static NotationStack<String> stack;
	
	/**
	 * Handles exception during push call
	 * @param expression element being added to the top of the stack
	 * @return true if it was added, false otherwise
	 */
	private static boolean pushHandler(String expression)
	{
		try
		{
			return stack.push(expression);
		}
		catch(StackOverflowException e)
		{
			e.getMessage();
		}
		return false;
	}
	
	/**
	 * Handles exception during top call
	 * @return the element at the top of the stack
	 */
	private static String topHandler()
	{
		try
		{
			return stack.top();
		}
		catch(StackUnderflowException e)
		{
			e.getMessage();
		}
		return null;
	}
	
	/**
	 * Handles exception during pop call
	 * @return the element at the top of the stack
	 */
	private static String popHandler()
	{
		try
		{
			return stack.pop();
		}
		catch(StackUnderflowException e)
		{
			e.getMessage();
		}
		return null;
	}
	
	/**
	 * Handles exception during dequeue call
	 * @return the element at the front of the queue
	 */
	private static String dequeueHandler()
	{
		try
		{
			return queue.dequeue();
		}
		catch(QueueUnderflowException e)
		{
			e.getMessage();
		}
		return null;
	}
	
	/**
	 * Handles exception during enqueue call
	 * @param expression element at the end of the queue
	 * @return true if the element was added, false otherwise
	 */
	private static boolean enqueueHandler(String expression)
	{
		try
		{
			return queue.enqueue(expression);
		}
		catch(QueueOverflowException e)
		{
			e.getMessage();
		}
		return false;
	}
	
	/**
	 * Calculates the precedence
	 * @param op the operator
	 * @return the precedence of the operator
	 */
	private static int calcPrecedence(char op)
	{
		switch(op)
		{
			case '*':
			case '/':
				return 1;
			case '+':
			case '-':
				return 0;
			default:
				return -1;
		}
	}
	
	/**
	 * Calculates expression of two numbers and one operator
	 * @param first the first number
	 * @param second the second number
	 * @param operator the operator
	 * @return the result of calculation as a string
	 * @throws InvalidNotationFormatException if the format of notation is incorrect
	 */
	private static String applyOperator(String first, String second, char operator) throws InvalidNotationFormatException
	{
		double x = Double.parseDouble(first);
		double y = Double.parseDouble(second);
		switch (operator)
		{
			case '+':
				return Double.toString(x + y);
		    case '-':
		        return Double.toString(x - y);
		    case '*':
		        return Double.toString(x * y);
		    case '/':
		        if (y == 0)
		        {
		          throw new InvalidNotationFormatException();
		        }
		        return Double.toString(x / y);
		    }
		    return null;
		  }
	
	/**
	 * Converts an infix expression into a postfix expression
	 * @param infix the expression
	 * @return the expression after conversion
	 * @throws InvalidNotationFormatException if the postfix format is invalid
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException
	{
		queue = new NotationQueue<String>();
		stack = new NotationStack<String>();
		
		for(int i = 0; i < infix.length(); i++)
		{
			char current = infix.charAt(i);
			
			if(current == ' ')
			{
				continue;
			}
			
			else if(Character.isDigit(current))
			{
				enqueueHandler(Character.toString(current));
			}
			
			else if(current == '(')
			{
				pushHandler(Character.toString(current));
			}
			
			else if(operators.indexOf(current) >= 0)
			{
				try
				{
					while(calcPrecedence(topHandler().charAt(0)) >= calcPrecedence(current) && !stack.isEmpty())
					{
						enqueueHandler(popHandler());
					}
				}
				catch(Exception e)
				{
					e.getMessage();
				}
				
				pushHandler(Character.toString(current));
			}
			
			else if(current == ')')
			{
				char topOfStack = popHandler().charAt(0);
				
				while(topOfStack != '(')
				{
					enqueueHandler(Character.toString(topOfStack));
					if(stack.isEmpty())
					{
						throw new InvalidNotationFormatException();
					}
					else
					{
						topOfStack = popHandler().charAt(0);
					}
				}
			}
			
		}
		
		while(!stack.isEmpty())
		{
			enqueueHandler(popHandler());
		}
		return queue.toString();
	}
	
	/**
	 * Converts postfix notation to infix notation
	 * @param postfix the postfix expression
	 * @return the conversion to infix notation
	 * @throws InvalidNotationFormatException if the infix format is incorrect
	 */
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException
	{
		stack = new NotationStack<String>();
		for(int i = 0; i < postfix.length(); i++)
		{
			char current = postfix.charAt(i);
			
			if(current == ' ')
			{
				continue;
			}
			
			else if(Character.isDigit(current))
			{
				pushHandler(Character.toString(current));
			}
			
			else if(operators.indexOf(current) >= 0)
			{
				String x = popHandler().toString(), y, temp;
				if(stack.isEmpty())
				{
					throw new InvalidNotationFormatException();
				}
				
				else
				{
					y = popHandler().toString();
					temp = '(' + y + current + x + ')';
					pushHandler(temp);
				}
			}
		}
		
		if(stack.size() != 1)
		{
			throw new InvalidNotationFormatException();
		}
		
		return popHandler();
	}
	
	/**
	 * Evaluates the postfix expression
	 * @param postfix the postfix expression
	 * @return The result of the evaluation
	 * @throws InvalidNotationFormatException if the postfix format is incorrect
	 */
	public static double evaluatePostfixExpression(String postfix) throws InvalidNotationFormatException
	{
		stack = new NotationStack<String>();
		for(int i = 0; i < postfix.length(); i++)
		{
			char current = postfix.charAt(i);
			
			if(current == ' ')
			{
				continue;
			}
			
			else if(Character.isDigit(current) || current == '(')
			{
				pushHandler(Character.toString(current));
			}
			
			else if(operators.indexOf(current) >= 0)
			{
				String x = popHandler().toString();
				String y, result;
				
				if(stack.isEmpty())
				{
					throw new InvalidNotationFormatException();
				}
				else
				{
					y = popHandler().toString();
					result = applyOperator(y, x, current);
					pushHandler(result);
				}
			}
		}
		if(stack.size() != 1)
		{
			throw new InvalidNotationFormatException();
		}
		return Double.parseDouble(popHandler());
	}
}
