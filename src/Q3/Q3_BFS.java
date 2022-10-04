package Q3;

import java.util.*;

public class Q3_BFS
{
	public static ArrayList<String> bfs(HashMap<String, List<String>> map, String startNode)
	{
		ArrayList<String> visited = new ArrayList<>();
		ArrayList<String> needVisited = new ArrayList<>();

		needVisited.add(startNode);

		while (needVisited.size() > 0)
		{
			String node = needVisited.remove(0);

			if (!visited.contains(node))
			{
				visited.add(node);
				needVisited.addAll(map.get(node));
			}
		}
		return visited;
	}


	public static void main(String[] args)
	{
		Map<String, List<String>> tree = makeTree();
		Map<String, String> parents = makeParents(tree);

		Set<String> nodes = new HashSet<>(tree.keySet());
		nodes.addAll(parents.keySet());

		for (String node : nodes)
			System.out.println(String.join("/", findPath(parents, node)));
		//System.out.println(bfs(tree, "A"));
	}

	private static Map<String, String> makeParents(Map<String, List<String>> tree)
	{
		Map<String, String> parents = new HashMap<>();
		for (Map.Entry<String, List<String>> e : tree.entrySet())
			for (String child : e.getValue())
				parents.put(child, e.getKey());

		return parents;
	}

	private static Map<String, List<String>> makeTree()
	{
		Map<String, List<String>> tree = new HashMap<>();

		tree.put("A", Arrays.asList("B", "C", "D"));
		tree.put("B", Arrays.asList("E", "F"));
		tree.put("C", Arrays.asList("G"));
		tree.put("D", Arrays.asList("H", "I"));
		tree.put("E", Arrays.asList("J"));
		tree.put("G", Arrays.asList("K"));
		tree.put("K", Arrays.asList("L"));

		return tree;
	}

	public static List<String> findPath(Map<String, String> parents, String node)
	{
		List<String> path = new ArrayList<>();

		path.add(node);

		while ((node = parents.get(node)) != null)
			path.add(0, node);

		// Collections.reverse()

		return path;
	}
}


