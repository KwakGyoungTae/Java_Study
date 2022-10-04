import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class NormalCalc
{

	public static void main(String args[])
	{
		System.out.print("문자열 수식을 입력해주세요. :  ");
		postfixCalc(postfix(new Scanner(System.in).nextLine()));
	}

	private static String[] postfix(String input)
	{
		/*String text = "";
		text=input.replace("( ","").replace(" )","");
		System.out.println(text);*/

		String[] splitStr = input.split(" ");

		List<String> splitList = new ArrayList<>();
		Stack<String> opStack = new Stack<>();

		for (int i=0; i<splitStr.length;i++)
		{
			String target = splitStr[i];

			switch (target)
			{
				case "+" :
				case "-" :
				case "*" :
				case "/" :
					while (!opStack.isEmpty())
						splitList.add(opStack.pop());
					opStack.push(target);break;
				case "(" :
				case ")" :
					break;

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

	public static void postfixCalc(String[] strings)
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
}
