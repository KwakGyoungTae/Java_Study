package Q3;

import java.util.*;


public class DfsTreeMap
{

	public void  DFS(Map<Character,String> map,char root,Set<Character> oldNode)
	{
		System.out.print(root);
		String children =map.get(root);

		for (char snode : children.toCharArray())
			if (oldNode.contains(snode) == false)
				DFS(map,snode,oldNode);

	}


	public static void main(String args[])
	{
		DfsTreeMap dtm = new DfsTreeMap();
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


		dtm.DFS(map,root,oldNode);

		//System.out.println(map);


	}
}