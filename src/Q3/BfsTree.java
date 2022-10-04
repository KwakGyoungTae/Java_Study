package Q3;

import java.util.LinkedList;
import java.util.Queue;

public class BfsTree
{
	TreeNode root;

	public void BFS(TreeNode root)
	{
		Queue<TreeNode> que = new LinkedList<>(); // 큐 생성
		que.offer(root); // 추가 offer를 쓰는 이유 false을 리턴 받아야 하기 때문이다.
		//int level = 0;

		while(!que.isEmpty())
		{
			TreeNode onode = que.poll(); // 큐에서 빼내기
			System.out.print(onode.data + "");

			if (onode.left != null)
				que.offer(onode.left);

			if (onode.mid != null)
				que.offer(onode.mid);

			if (onode.right != null)
				que.offer(onode.right);

		}

	}

	public static void main(String args[])
	{



		BfsTree tree = new BfsTree();

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

		System.out.println("검색 결과");
		tree.BFS(tree.root);




	}
}
