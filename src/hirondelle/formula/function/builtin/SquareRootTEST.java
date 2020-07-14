package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class SquareRootTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(SquareRootTEST.class);
  }

  public void testCases(){
    Function func = new SquareRoot();
    
    testWith(func, "0.0", "0");
    testWith(func, "1.0", "1");
    testWith(func, "2.0", "4");
    testWith(func, "4.0", "16");
    testWith(func, "10.0", "100");
    testWith(func, "1.4142135623730951", "2");
    
    testFailWith(func,  "-1");
    testFailWith(func,  "-5656.32");
  }

}
