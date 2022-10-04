import java.util.*;


public class Calc {

	public static void main(String[] args)
	{
		calculation(postfixConverter(new Scanner(System.in).nextLine()));
	}
	public static String[] postfixConverter(String input)
	{
		String[] splitStr = input.split(" ");

		List<String> splitList = new ArrayList<>();
		Stack<String> opStack = new Stack<>();

		for (int i=0,iend=splitStr.length; i<iend;i++)
		{
			String target = splitStr[i];

			switch (target)
			{
				case "+" :
				case "-" :
				case "*" :
				case "/" :
					while (!opStack.isEmpty() && priority(opStack.peek()) >= priority(target))
						splitList.add(opStack.pop());
					opStack.push(target);break;
				case "(" : opStack.push(target);break;
				case ")" :
					while (!opStack.isEmpty() && !opStack.peek().equals("("))
						splitList.add(opStack.pop());
					opStack.pop();break;

				default: splitList.add(target);
			}
		}

		while (!opStack.isEmpty())
			splitList.add(opStack.pop());

		System.out.println(splitList);

		String[] result = new String[splitList.size()];

		for (int i=0; i< splitList.size(); i++)
			result[i]=splitList.get(i);

		return result;
	}

	public static void calculation(String[] strings)
	{
		Stack<Integer> stack = new Stack<>();

		for (String s : strings)
		{
			if (!s.equals("+") && !s.equals("-") && !s.equals("*") && !s.equals("/"))
				stack.push(Integer.parseInt(s));

			else
			{
				int stackData1 = stack.pop();
				int stackData2 = stack.pop();

				switch (s)
				{
					case "+" : stack.push(stackData2+stackData1);break;
					case "-" : stack.push(stackData2-stackData1);break;
					case "*" : stack.push(stackData2*stackData1);break;
					case "/" : stack.push(stackData2/stackData1);break;
				}
			}
		}
		System.out.println(stack.pop());
	}
	public static int priority(String operator)
	{
		switch (operator)
		{
			case "*" :
			case "/" :
				return 3;
			case "-" :
			case "+" :
				return 2;
			case "(" :
			case ")" :
				return 1;

			default: return 0;
		}
	}
}