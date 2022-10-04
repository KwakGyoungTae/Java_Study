package Q3;


class TreeNode
{
	char data;
	//String nodeName;

	TreeNode left, right, mid;


	public TreeNode(char data)
	{
		this.data = data;
		this.left = null;
		this.right = null;
		this.mid = null;

	}


}

public class DfsTree
{
	TreeNode root;

	//재귀함수
	public void search(TreeNode root)
	{
		if (root == null) return;

		else
		{
			System.out.print(root.data + "");
			search(root.left);
			search(root.mid);
			search(root.right);
		}
	}


	//메인
	public static void main(String args[])
	{

		DfsTree tree = new DfsTree();

		tree.root = new TreeNode('A');

		tree.root.left = new TreeNode('B');
		tree.root.mid = new TreeNode('C');
		tree.root.right = new TreeNode('D');

		tree.root.left.left = new TreeNode('E');
		tree.root.left.right = new TreeNode('F');
		tree.root.mid.mid = new TreeNode('G');
		tree.root.right.left = new TreeNode('H');
		tree.root.right.right = new TreeNode('I');

		tree.root.left.left.left = new TreeNode('J');
		tree.root.mid.mid.mid = new TreeNode('K');
		tree.root.mid.mid.mid.mid = new TreeNode('L');

		tree.search(tree.root);
		System.out.println();


	}
}
