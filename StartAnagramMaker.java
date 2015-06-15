package ru.yandex.op.ua;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class StartAnagramMaker {

	public static void main(String[] args) throws IOException {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	String str = reader.readLine();
	
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
