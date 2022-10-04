package Q2;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;


public class Java_OS
{
	public static void main(String args[])
	{
		System.out.println(System.getProperties());

		System.out.println(new TreeMap(System.getProperties()));

		for(Map.Entry<Object, Object> e:new TreeMap<>(System.getProperties()).entrySet())
			System.out.println(e.getKey()+"="+e.getValue());

		for(Map.Entry<String, String> e:new TreeMap<>(System.getenv()).entrySet())
			System.out.println(e.getKey()+"="+e.getValue());



	}
}
