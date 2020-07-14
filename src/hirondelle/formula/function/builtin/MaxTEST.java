package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class MaxTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(MaxTEST.class);
  }

  public void testCases(){
    Function func = new Max();
    
    testWith(func, "0", "0");
    testWith(func, "0", "0", "0", "0");
    testWith(func, "1", "1", "0", "0");
    testWith(func, "1", "0", "1", "0");
    testWith(func, "1", "0", "0", "1");
    testWith(func, "0", "-1", "0", "0");
    testWith(func, "0", "0", "-1", "0");
    testWith(func, "0", "0", "0", "-1");
    
    testWith(func, "1", "-1", "0", "1");
    testWith(func, "1", "0", "-1", "1");
    testWith(func, "1", "1", "0", "-1");
    
    testWith(func, "100", "0", "1", "100");
    testWith(func, "100", "-3213", "100", "-32131.3213");
    testWith(func, "32131.3213", "-3213", "100", "32131.3213");
    
    testWith(func, "0");
    
  }


}
