package Q3;

import java.util.*;

public class BfsTreeMap
{

	public void  BFS(Map<Character,String> map,char root)
	{
		HashSet<Character> oldNode = new HashSet<>();
		Queue<Character> nextNode = new LinkedList<Character>();

		oldNode.add(root);
		nextNode.add(root);

		while (nextNode.isEmpty() == false)
		{
			char rnode = nextNode.remove();
			System.out.print(rnode);

			String bnodeList = map.get(rnode);

			for (char bnode : bnodeList.toCharArray())
			{
				if (oldNode.contains(bnode) == false)
				{
					oldNode.add(bnode);
					nextNode.add(bnode);
				}
			}
		}

	}
	public static void main(String args[])
	{
		BfsTreeMap btm = new BfsTreeMap();
		char root = 'A';


		Map<Character, String> map = new HashMap<Character,String>();
		Set<Character> oldNode = new HashSet<Character>();

		map.put('A', "BCD");
		map.put('B', "EF");
		map.put('C',"G");
		map.put('D',"HI");
		map.put('E',"J");
		map.put('F',"");
		map.put('G',"K");
		map.put('H',"");
		map.put('I',"");
		map.put('J',"");
		map.put('K',"L");
		map.put('L',"");


		btm.BFS(map,root);

		//System.out.println(map);


	}
}
