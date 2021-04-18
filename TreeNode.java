/**
 * This generic class represents a node in the tree.
 * @author rpard
 */
public class TreeNode<T>
{
	protected T data;
	protected TreeNode<T> left, right;
	
	/**
	 * Parameterized constructor that sets the data of a node
	 * @param data the data of node
	 */
	public TreeNode(T data)
	{
		this.data = data;
		this.left = null;
		this.right = null;
	}
	
	/**
	 * Parameterized constructor that sets the data of the node, and the left and right nodes to the original node.
	 * @param node the node to be set
	 */
	public TreeNode(TreeNode<T> node)
	{
		this.data = node.data;
		this.left = node.left;
		this.right = node.right;
	}
	
	/**
	 * This method returns the data within the TreeNode
	 * @return the data in the TreeNode
	 */
	public T getData()
	{
		return this.data;
	}
}
