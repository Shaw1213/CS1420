package demo;

import java.util.HashMap;
import java.util.Scanner;

public class Demo {

	public static void main(String[] args) {
		System.out.print(re(32));
		
	}
	public static String unscramble(String message, int[] indexes) {
		HashMap<Integer, Character> solving = new HashMap<>();
		for(int i = 0; i < indexes.length; i++) 
			solving.put(indexes[i], message.charAt(i));
		String result = "";
		for(int i = 0; i < indexes.length; i++)
			result += solving.get(i);
		return result;
	}
	public static int re (int input) {
		if(input<=1)
			return 0;
		return re(input/2)+1;
	}
}
