package ru.yandex.op.ua;


public class StartReverse {

	public static void main(String[] args) {

	String str = "�1���� ���!�";
	
	ReverseString stroka = new ReverseString();
	String reversed = stroka.getReversed(str);
	System.out.println(reversed);
	}

}
