import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class startsWith
{
	public static void main(String args[])
	{


	/*

		//3줄 코드
		for (int i = 0; i < 4; i++)
			if (str[i].startsWith("_"))
				System.out.println(str[i]);
	*/
		// 1줄코드  스트림 선언과 동시에 중간연산 최종연산
		Arrays.asList("aaa","bbb","_aaa","bb_").stream().filter(s -> s.startsWith("_")).forEach(s -> System.out.println(s));



	}
}