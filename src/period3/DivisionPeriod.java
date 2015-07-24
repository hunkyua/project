package period3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DivisionPeriod {

    static List<Integer> list = new LinkedList<>(); //for checking period
    static StringBuilder resultForPrint = new StringBuilder();
    static List<Integer> separatedString = new ArrayList<>();
    static List<Integer> resultForDivision = new ArrayList<>();
    static List<Integer> underNum = new ArrayList<>();
    static List<Integer> underLine = new ArrayList<>();
    static List<Integer> temp = new ArrayList<>();

    public static String doResultInString(String division, int divider) {

        for (int i = 0; i < division.length(); i++) {
            separatedString.add(Integer.parseInt(String.valueOf(division.charAt(i))));
        }

        int tmp = separatedString.get(0);
        int iterForWhile = 1;

        while (tmp != 0) {
            for (int i = 0; i < separatedString.size() - 1; i++) {
                while (tmp < divider & iterForWhile < separatedString.size()) {
                    tmp = Integer.parseInt(tmp + separatedString.get(iterForWhile).toString());
                    temp.add(tmp);
                    resultForDivision.add(tmp / divider);
                    resultForPrint.append(resultForDivision.get(i));
                    underNum.add(divider * resultForDivision.get(i));
                    underLine.add(tmp % divider);
                    tmp = underLine.get(i);
                    iterForWhile++;
                    i++;
                }
            }

            //Divide more on less
            if (tmp >= divider) {
                if (divider == 0) {
                    throw new ArithmeticException("������ ������ �� 0");
                } else {
                    resultForPrint.append(tmp / divider);
                }
                tmp %= divider;
            } else {
                //Add coma
                if (resultForPrint.toString().equals("")) {
                    resultForPrint.append("0.");
                    underNum.add(0);
                }
                if (!resultForPrint.toString().contains(".")) {
                    resultForPrint.append(".");
                }

                //Divide after coma and find period
                tmp *= 10;
                temp.add(tmp);
                resultForDivision.add(tmp / divider);
                while (tmp < divider) {
                    list.add(tmp);
                    tmp *= 10;
                    resultForPrint.append(0);
                }
                if (list.contains(tmp)) {
                    int indexList = list.indexOf(tmp);
                    int indexSb = resultForPrint.indexOf(".");
                    resultForPrint.insert(indexList + indexSb + 1, "(");
                    resultForPrint.append(")");
                    return resultForPrint.toString();
                } else {
                    list.add(tmp);
                }

            }
        }
        return resultForPrint.toString();
    }


    public static void drawSolution(int division, int divider, String result) {

        System.out.println(division + "  |" + divider);
        while (division < divider) {
            division *= 10;
        }

        int g = underNum.get(0);
        int c = division / divider;
        int d = c * divider;
        System.out.println(-g + "   |" + result);

        for (int i = 1; i < separatedString.size() - 1; i++) {
            System.out.println("----");
            int g1 = temp.get(i);
            System.out.println(" " + g1);
            System.out.println("" + "-" + underNum.get(i));
        }

        String separator = "-";
        String space = " ";
        int countA = 0;        //count spaces for A
        int countD = 0;        //count spaces for D

        //Draw the line
        String sd = Integer.toString(g);
        for (int i = 0; i <= sd.length(); i++) {
            System.out.print(separator);
        }
        System.out.println();

        //Count of digits in resultForPrint
        int countDigits = 0;
        char[] chars = result.toCharArray();
        for (Character aChar : chars) {
            if (Character.isDigit(aChar)) {
                countDigits++;
            }
        }

        //Draw solution
        while (countDigits > 1) {
            countA++;
            division -= d;
            if (division == 0) {
                for (int i = 0; i < countA; i++) {
                    System.out.print(space);
                }
                System.out.println(division);
                break;
            }
            while (division < divider) {
                division *= 10;
            }
            for (int i = 0; i < countA; i++) {
                System.out.print(space);
            }
            System.out.println(division);
            c = division / divider;
            d = c * divider;
            for (int i = 0; i < countD; i++) {
                System.out.print(space);
            }
            System.out.println(-d);
            for (int i = 0; i < countD; i++) {
                System.out.print(space);
            }
            sd = Integer.toString(d);
            for (int i = 0; i <= sd.length(); i++) {
                System.out.print(separator);
            }
            System.out.println();
            countD++;
            countDigits--;
        }
    }
}
