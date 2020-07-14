package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class CombinationTEST extends BaseTEST{
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(CombinationTEST.class);
  }

  public void testCases(){
    Function func = new Combination();
    
    testWith(func, "4", "4", "3");
    testWith(func, "6", "4", "2");
    testWith(func, "4", "4", "1");
    testWith(func, "70", "8", "4");
  }

}
