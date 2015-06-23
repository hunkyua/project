import java.util.ArrayList;

public class Division {
    static int divisionFigureIndex = 0;
    static int rest = 0;
    static int currentDivision = 0;

    ArrayList<String> divide(int[] division, int divider) {

        int multiply = 0;
        int[] divisionNumbers = division;
        int currentCalculationResult = 0;
        boolean flag = true;

        while (flag) {
            if (divisionFigureIndex < divisionNumbers.length) {
                if (rest > 0)
                    currentDivision = Integer.parseInt(String.valueOf(rest) + String.valueOf(divisionNumbers[divisionFigureIndex]));
                else
                    currentDivision = Integer.parseInt(String.valueOf(currentDivision) + String.valueOf(divisionNumbers[divisionFigureIndex]));

                if (currentDivision / divider > 0) {
                    currentCalculationResult = currentDivision / divider;
                    multiply = currentCalculationResult * divider;
                    rest = currentDivision % divider;
                    flag = false;
                } else
                    divisionFigureIndex++;
            }
        }

        ArrayList<String> resultParameters = new ArrayList<String>();
        resultParameters.add(String.valueOf(multiply));
        resultParameters.add((String.valueOf(rest)));
        resultParameters.add(String.valueOf(currentCalculationResult));

        divisionFigureIndex++;
        currentDivision = 0;

        return resultParameters;
    }

    public void printDivision(String division, int divider){

        String out = "  " + division + " |" + divider;
        System.out.println(out);
       

        String divisionResult = "";
        ArrayList<String> stringsToOutput = new ArrayList<String>();

        int [] nums=new int[division.length()];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(String.valueOf(division.charAt(i)));
        }

        while (divisionFigureIndex < division.length()) {
            ArrayList<String> res = divide(nums, divider);
            divisionResult += res.get(2);
            stringsToOutput.add("-" + res.get(0));
            stringsToOutput.add("--------");

            if (Integer.valueOf(res.get(1)) > 0 && divisionFigureIndex < division.length()) {
                stringsToOutput.add(String.valueOf(res.get(1)) + String.valueOf(nums[divisionFigureIndex]));

            } else if (divisionFigureIndex < division.length()) {
                stringsToOutput.add(String.valueOf(nums[divisionFigureIndex]));

            } else {
                stringsToOutput.add(res.get(1));
            }

        }
        String initialSpace = stringsToOutput.get(0);
        stringsToOutput.remove(0);
        stringsToOutput.add(0, initialSpace + "   |" + String.valueOf(divisionResult));

        String space = " ";
        for (int i = 0; i < stringsToOutput.size(); i++) {
            System.out.println(space + stringsToOutput.get(i));
            if (i % 3 == 0) {
                space += "   ";
            }
        }

    }

}
