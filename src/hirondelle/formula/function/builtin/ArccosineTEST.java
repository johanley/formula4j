package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;


public final class ArccosineTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(ArccosineTEST.class);
  }

  public void testCases(){
    Function func = new Arccosine();
    testWith(func, "0.0", "1" );
    testWith(func, HALF_PI, "0");
    testWith(func, PI, "-1");
  }
  
}
