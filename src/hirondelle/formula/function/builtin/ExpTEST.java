package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class ExpTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(ExpTEST.class);
  }

  public void testCases(){
    Function func = new Exp();
    
    testWith(func, E, "1");
    testWith(func, "1.0", "0");
    testWith(func, "0.36787944117144233", "-1");
    testWith(func, "7.38905609893065", "2");
    
  }



}
