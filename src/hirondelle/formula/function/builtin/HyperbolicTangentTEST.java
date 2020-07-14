package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class HyperbolicTangentTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(HyperbolicTangentTEST.class);
  }

  public void testCases(){
    Function func = new HyperbolicTangent();
    
    testWith(func, "0.0", "0");
    testWith(func, "0.7615941559557649", "1");
    testWith(func, "-0.7615941559557649", "-1");
  }
}
