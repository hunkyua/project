package period3;

public class StartDivisionPeriod {
    public static void main(String[] args) {
        String division = "84686";
        int divider = 24;
        String result = DivisionPeriod.doResultInString(division, divider);
        DivisionPeriod.drawSolution(Integer.parseInt(division), divider, result);
        int div = Integer.parseInt(division);

        //Only for division without period
        boolean isResultTrue = Double.toString((double)div / divider).equals(result) ;
        if (isResultTrue) {
            System.out.println("true");
        } else {
            System.out.println("false");
            System.out.println(Double.toString((double)div / divider));
            System.out.println(result);
        }
    }
}