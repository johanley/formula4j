package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;


public final class ArcsineTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(ArcsineTEST.class);
  }
  
  public void testCases(){
    Function func = new Arcsine();
    testWith(func, "0.0", "0");
    testWith(func, HALF_PI, "1");
    testWith(func, "-" + HALF_PI, "-1.0");
  }
}
