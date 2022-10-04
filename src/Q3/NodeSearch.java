package Q3;

import java.util.*;

//노드 클래스
class Node{
	String data;

	Node left = null;
	Node right = null;
	Node mid = null;

	Node(String data)
	{
		this.data = data;
	}
}
public class NodeSearch
{
	Node root;

	//경로 출력
	public static void PrintPath(Node node,Deque<String> path)
	{
		Iterator<String> itr = path.iterator();

		System.out.print(node.data+"노드의 경로는 : ");
		while (itr.hasNext())
		{
			System.out.print("/"+itr.next());

		}

		if(node.left == null && node.mid == null && node.right == null)
		{
			System.out.print("이며 리프노드 입니다.");
			System.out.println();
		}
		else
		{
			System.out.print(" 입니다.");
			System.out.println();
		}
	}

	public static void RootPath(Node snode,Deque<String> path)
	{

		//자식노드가 없으면 리턴
		if (snode == null)
			return;

		//자신노드가 있다면 덱 추가한다
		path.addLast(snode.data);

		//선택된데이터 가지고 메서드 이동
		PrintPath(snode,path);

		//각각의 자식이 있으면 재귀함
		RootPath(snode.left, path);
		RootPath(snode.mid, path);
		RootPath(snode.right, path);


		//처리된 노드는 덱큐에서 삭제
		path.removeLast();
	}

	public static void RootPath(Node node)
	{
		Deque<String> path = new ArrayDeque<>();

		RootPath(node,path);
	}

	public static void main(String args[])
	{
		Node root = new Node("A");

		root.left = new Node("B");
		root.mid = new Node("C");
		root.right = new Node("D");

		root.left.left = new Node("E");
		root.left.right = new Node("F");
		root.mid.mid = new Node("G");
		root.right.left = new Node("H");
		root.right.right = new Node("I");

		root.left.left.left = new Node("J");
		root.mid.mid.mid = new Node("K");
		root.mid.mid.mid.mid = new Node("L");

		System.out.println("검색 결과");
		RootPath(root);





	}
}
