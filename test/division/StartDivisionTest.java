package division;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class StartDivisionTest {

  @Test
  public void testResultOfDivision() {
    String division = "846";
    int divider = 4;
    Division div = new Division();

    List<String> listResult = div.printDivision(division, divider);
    int result = Integer.parseInt(div.getDivisionResult());

    assertEquals(211, result);
  }
}
