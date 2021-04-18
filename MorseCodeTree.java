import java.util.ArrayList;

/**
 * This is a MorseCodeTree which is specifically used for the conversion of morse code
 * to english It relies on a root (reference to root of the tree) The root is set to
 * null when the tree is empty. The class uses an external generic TreeNode class which 
 * consists of a reference to the data and a reference to the left and right child. The 
 * TreeNode is parameterized as a String, TreeNode This class uses a private member root 
 * (reference to a TreeNode) The constructor will call the buildTree method
 * @author rpard
 */
public class MorseCodeTree implements LinkedConverterTreeInterface<String>
{
	private TreeNode<String> root;
	
	/**
	 * Default constructor that calls the buildTree method
	 */
	public MorseCodeTree()
	{
		buildTree();
	}

	/**
	 * Returns a reference to the root node
	 * @return the reference to the root node
	 */
	@Override
	public TreeNode<String> getRoot()
	{
		return this.root;
	}
	
	/**
	 * Sets the root of the MorseCodeTree
	 * @param newNode the node that will set the root
	 */
	@Override
	public void setRoot(TreeNode<String> newNode)
	{
		this.root = new TreeNode<String>(newNode);
	}

	/**
	 * Adds element to the correct position in the tree based on the code 
	 * This method will call the recursive method addNode
	 * 
	 * @param code the code for the new node to be added
	 * @param result the letter for the corresponding code
	 * @return the MorseCodeTree with the new node added
	 */
	@Override
	public MorseCodeTree insert(String code, String letter)
	{
		addNode(this.root, code, letter);
		return this;
	}

	/**
	 * This is a recursive method that adds element to the correct position in the tree based on the code.
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of addNode
	 * @param letter the data of the new TreeNode to be added
	 */
	@Override
	public void addNode(TreeNode<String> root, String code, String letter)
	{
		if(code.length() == 1)
		{
			if(code.charAt(0) == '.')
			{
				root.left = new TreeNode<String>(letter);
			}
			else if(code.charAt(0) == '-')
			{
				root.right = new TreeNode<String>(letter);
			}
		}
		else
		{
			if(code.charAt(0) == '.')
			{
				addNode(root.left, code.substring(1), letter);
			}
			else if(code.charAt(0) == '-')
			{
				addNode(root.right, code.substring(1), letter);
			}
		}
	}

	/**
	 * Fetch the data in the tree based on the code This method will call the recursive method fetchNode
	 * @param code the code that describes the traversals to retrieve the string (letter)
	 * @return the string (letter) that corresponds to the code
	 */
	@Override
	public String fetch(String code)
	{
		return fetchNode(this.root, code);
	}

	/**
	 * This is the recursive method that fetches the data of the TreeNode that corresponds with the code
	 * @param root the root of the tree for this particular recursive instance of addNode
	 * @param code the code for this particular recursive instance of addNode
	 * @return the string (letter) corresponding to the code
	 */
	@Override
	public String fetchNode(TreeNode<String> root, String code)
	{
		if(code.length() == 1)
		{
			if(code.charAt(0) == '.')
			{
				return root.left.data;
			}
			else if(code.charAt(0) == '-')
			{
				return root.right.data;
			}
		}
		else
		{
			if(code.charAt(0) == '.')
			{
				return fetchNode(root.left, code.substring(1));
			}
			else if(code.charAt(0) == '-')
			{
				return fetchNode(root.right, code.substring(1));
			}
		}
		
		return null;
	}

	/**
	 * This operation is not supported in the MorseCodeTree
	 * @param data the data of node to be deleted
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException if the method is called
	 */
	@Override
	public MorseCodeTree delete(String data) throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * This operation is not supported in the MorseCodeTree
	 * @return reference to the current tree
	 * @throws UnsupportedOperationException if the method is called
	 */
	@Override
	public MorseCodeTree update() throws UnsupportedOperationException
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * This method builds the MorseCodeTree by inserting the nodes of the tree level by level based on the code
	 */
	@Override
	public void buildTree()
	{
		this.root = new TreeNode<String>("");
		
		insert(".", "e");
		insert("-", "t");
		
		insert("..", "i");
		insert(".-", "a");
		insert("-.", "n");
		insert("--", "m");
		
		insert("...", "s");
		insert("..-", "u");
		insert(".-.", "r");
		insert(".--", "w");
		insert("-..", "d");
		insert("-.-", "k");
		insert("--.", "g");
		insert("---", "o");
		
		insert("....", "h");
		insert("...-", "v");
		insert("..-.", "f");
		insert(".-..", "l");
		insert(".--.", "p");
		insert(".---", "j");
		insert("-...", "b");
		insert("-..-", "x");
		insert("-.-.", "c");
		insert("-.--", "y");
		insert("--..", "z");
		insert("--.-", "q");
	}

	/**
	 * Returns an ArrayList of the items in the linked Tree in LNR (Inorder) Traversal order Used for testing to make sure tree is built correctly
	 * @return an ArrayList of the items in the linked tree
	 */
	@Override
	public ArrayList<String> toArrayList()
	{
		ArrayList<String> list = new ArrayList<>();
		LNRoutputTraversal(this.root, list);
		return list;
	}

	/**
	 * The recursive method to put the contents of the tree in an ArrayList in LNR (Inorder)
	 * @param root the root of the tree for this particular recursive instance
	 * @param list the ArrayList that will hold the contents of the tree in LNR order
	 */
	@Override
	public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list)
	{
		if(root == null)
		{
			return;
		}
		
		if(root.left != null)
		{
			LNRoutputTraversal(root.left, list);
		}
		
		list.add(root.data);
		if(root.right != null)
		{
			LNRoutputTraversal(root.right, list);
		}
	}
}
