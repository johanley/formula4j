package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class PermutationTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(PermutationTEST.class);
  }

  public void testCases(){
    Function func = new Permutation();
    
    testWith(func, "6", "3", "3");
    testWith(func, "6", "3", "2");
    testWith(func, "3", "3", "1");
    testWith(func, "7", "7", "1");
    testWith(func, "42", "7", "2");
    testWith(func, "210", "7", "3");
    testWith(func, "840", "7", "4");
    testWith(func, "2520", "7", "5");
    testWith(func, "5040", "7", "6");
    testWith(func, "5040", "7", "7");
    
    testFailWith(func, "0","0");
    testFailWith(func, "5","0");
    testFailWith(func, "0","5");
    testFailWith(func, "12","13");
    testFailWith(func, "-12","5");
    testFailWith(func, "12","-5");
    testFailWith(func, "12.0","5");
    testFailWith(func, "12","5.0");
    
  }

}
