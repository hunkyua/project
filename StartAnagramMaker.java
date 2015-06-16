package ru.yandex.op.ua;

public class StartAnagramMaker {

	public static void main(String[] args) {
	String str = "а1бвгд ежз!и";
	AnagramMaker stroka = new AnagramMaker();
	String reversed = stroka.getReversed(str);
	System.out.println(reversed);
	AnagramMaker anagrammer = new AnagramMaker();
	
	if (anagrammer.getReversed("а1бвгд ежз!и").equals("д1гвба изж!е"))
		System.out.println("passed");
	else  	
		System.out.println("failed");
	
	}
	
}
