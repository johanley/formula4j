package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;


public final class ArctangentTEST extends BaseTEST {

  public static void main(String args[]) {
    junit.textui.TestRunner.run(ArctangentTEST.class);
  }
  
  public void testCases(){
    Function func = new Arctangent();
    testWith(func, "0.0", "0");
    testWith(func, QUARTER_PI, "1");
    testWith(func, "-" + QUARTER_PI, "-1");
  }

}
