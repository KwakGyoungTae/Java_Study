import java.util.*;

public class RootLoad
{
	public void findPath(Map<Character,String> map, char root, String target, List<Character> ret, Boolean[] isVisited)
	{

		Character node = (Character)root; //캐스팅 변환
		String children = map.get(root); // 자식 노드


		Character tg = target.charAt(0); // 스트링으로 받은 문자 캐릭터 변환

		if(node != tg)
		{
			for(char snode : children.toCharArray())
			{
				isVisited[snode-'A'] = true;
				findPath(map, snode, target, ret,isVisited);
				isVisited[snode-'A'] = false;
			}
		}

		else
		{
			for(char c = 'A';c <='Z';c++)
				if(isVisited[c-'A'])
					ret.add(c);

		}

	}

	public static void main(String args[])
	{
		RootLoad rl = new RootLoad();
		char root = 'A';
		List<Character> result = new LinkedList<Character>();
		Map<Character,String> map = new HashMap<Character,String>();
		Scanner sc = new Scanner(System.in);
		Boolean isVisited[] = new Boolean[100];


		Arrays.fill(isVisited,false); // boolean 배열 false 초기화


		extracted(map);

		result.add('A');//루트 노드 리스트에 저장

		String searchNode = sc.nextLine();// 검색할 노드 입력 받기

		rl.findPath(map,root,searchNode,result,isVisited);

		/*//리스트 출력
		for(char ret : result)
			System.out.print("/"+ret);

		//
		Map<String,String> parents = new HashMap<>();

		Set<Character> nodes = new HashSet<>(map.keySet());
		nodes.addAll(parents.keySet());

		for (String node : nodes)
		{
			System.out.println(String.join());
		}

		for (Map.Entry<String,List<String>> e: map.entrySet() )
		{
			for (String child : e.getValue())
				parents.put(child,e.getKey());
			for (String node : parents.keySet())
				System.out.println("/"+String.join("/",kindPath(L)));

			System.out.println(parents);
			System.out.println("/"+String.join("/",kindPath(L)));

		}*/

	}

	private static void extracted(Map<Character, String> map)
	{
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
	}

	/*public static List<String> kindPath(Map<String,String> parents,String node)
	{
		List<String> path = new ArrayList<>();

		path.add(node);
		while ((node == parents.get(node)) != null)
		{
			path.add(0,node);

			//collect list
		}
		return path;
	}*/


}