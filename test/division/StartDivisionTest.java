package division;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StartDivisionTest {

  @Test
  public void testResultOfDivision() {
    int[] division = {8,4,6};
    int divider = 4;
    Division div = new Division();

    List<String> resultParameters = div.divide(division, divider);
    int result;
    String res = "";
    for (String element : resultParameters) {
       res += element;
    }
    result = Integer.parseInt(res);

    assertEquals(211, result);
  }
}