package ru.yandex.op.ua;

public class AnagramMaker {
	public static char[] result;
	public String reversedInput = "";
	

	public void addToTemp(String string) {
		result = new char[string.length()];
		char[] temp = string.toCharArray();
		for (int i = 0; i < temp.length; i++) {
			if (!Character.isAlphabetic(temp[i]))
				result[i] = temp[i];
		}
	}

	public int findIndex(char[] in) {
		int index = 0;
		boolean label = false;
		while (!label && index < in.length - 1) {
			if (in[index] == '\u0000')
				label = true;
			else
				index++;
		}
		return index;
	}

	public void addToIndex(char[] in) {
		int position;
		for (int i = result.length - 1; i >= 0; i--) {
			if (Character.isAlphabetic(in[i])) {
				position = findIndex(result);
				result[position] = in[i];
			}
		}
	}

	public String getReversed(String input) {
		String[] inputArray = input.split(" ");
		for (int i = 0; i < inputArray.length; i++) {
			this.addToTemp(inputArray[i]);
			char[] bufferArray = inputArray[i].toCharArray();
			this.addToIndex(bufferArray);
			for (int j = 0; j < result.length; j++) {
				reversedInput += (result[j]);
			}
			if (i == inputArray.length -1){
				  reversedInput += "";
			}else{
				reversedInput += " ";
			}

			 
		}
		return reversedInput;
	}

}
