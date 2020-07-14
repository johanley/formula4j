package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class TangentTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(TangentTEST.class);
  }

  public void testCases(){
    Function func = new Tangent();
    
    testWith(func, "0.0", "0");
    testWith(func, "-0.00000000000000012246467991473532", PI); // not 0.0
    testWith(func, "0.00000000000000012246467991473532", "-"  + PI); // not 0.0
    
    testWith(func, "0.9999999999999999", QUARTER_PI); // not 1.0
    testWith(func, "-0.9999999999999999", "-" + QUARTER_PI); // not -1.0
    
  }


}
