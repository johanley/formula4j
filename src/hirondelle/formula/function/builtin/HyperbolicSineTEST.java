package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class HyperbolicSineTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(HyperbolicSineTEST.class);
  }

  public void testCases(){
    Function func = new HyperbolicSine();
    
    testWith(func, "0.0", "0");
    testWith(func, "1.1752011936438014", "1");
    testWith(func, "-1.1752011936438014", "-1");
  }


}
