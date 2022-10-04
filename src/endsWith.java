import java.util.Arrays;
import java.util.stream.Collectors;

public class endsWith
{
	public static void main(String args[])
	{

		//ArrayList<String> list = new ArrayList<String >(Arrays.asList(aaa,bbb,_aaa,bb_));

		/*
		//3줄코드
		for (int i = 0; i < 4; i++)
			if (str[i].endsWith("_"))
				System.out.println(str[i]);
		*/
		// 1줄코드  스트림 선언과 동시에 중간연산 최종연산
		Arrays.asList("aaa","bbb","_aaa","bb_").stream().filter(s -> s.endsWith("_")).forEach(s -> System.out.println(s));
		//Stream<String> stream = list.stream();
		//stream.filter(s -> s.endsWith("_")).forEach(s ->System.out.println(s));



	}

}