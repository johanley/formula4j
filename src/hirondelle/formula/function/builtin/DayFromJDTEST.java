package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class DayFromJDTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(DayFromJDTEST.class);
  }

  public void testCases(){
    Function func = new DayFromJD();
    
    testWith(func, "19000101.0", "2415020.5");
    testWith(func, "19571004.81", "2436116.31");
    testWith(func, "20000101.5", "2451545.0");
    testWith(func, "19870127.0", "2446822.5");
    testWith(func,  "16000101.0", "2305447.5");
  }
}
