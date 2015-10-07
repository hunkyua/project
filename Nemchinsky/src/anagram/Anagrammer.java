package anagram;

import java.util.Scanner;

public class Anagrammer {
 public static void main(String[] args) {
  Scanner scn = new Scanner(System.in);
  String str = scn.nextLine();
  scn.close();
  String strnew = "";
  String strold = str;
   String[]strs = str.split(" "); // разворачиваем строку (по словам), создаем новую строку
   for (int i=0; i<strs.length; i++){ 
    String n = new StringBuilder(strs[i]).reverse().toString();
    if (i == strs.length -1){
    	strnew += n;
    }else{
    	strnew += n +" ";
    }
   }   
   
   for (int i=0; i < strnew.length(); i++){ // удаляем из новой строки символы с неправильных мест
    char ch = strnew.charAt(i); 
     if (ch < 65 && ch != 32){
     strnew = strnew.substring(0, i) + strnew.substring(i+1, strnew.length());       
     }
    }
      
   for (int i=0; i < strold.length(); i++){ // добавляем в новую строку символы из старой строки
    char ch = strold.charAt(i); 

     if (ch < 65 && ch != 32){
     strnew = strnew.substring(0, i) + ch + strnew.substring(i, strnew.length()); 
     
     }
    }
 System.out.println(strnew);
 if ((strnew).equals("д1гвба изж!е"))
		System.out.println("passed");
	else  	
		System.out.println("failed");
 }   
}
