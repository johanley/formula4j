package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class NaturalLogTEST extends BaseTEST {
  

  public static void main(String args[]) {
    junit.textui.TestRunner.run(NaturalLogTEST.class);
  }

  public void testCases(){
    Function func = new NaturalLog();
    
    testWith(func, "0.0", "1");
    testWith(func, "1.0", E);
    testWith(func, "2.302585092994046", "10");
    
  }



}
