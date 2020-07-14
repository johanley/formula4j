package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;


public final class RandomNumberTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(RandomNumberTEST.class);
  }

  public void testCases(){
    Function func = new RandomNumber();
    
    for (int idx = 0;  idx <= 100; ++idx){
      testBetween(func, "0", "1");
    }
    
    for (int idx = 0;  idx <= 100; ++idx){
      testBetween(func, "0", "1", "0", "1");
      testBetween(func, "-10", "10", "-10", "10");
      testBetween(func, "-10", "-6", "-10", "-6");
      testBetween(func, "6", "10", "6", "10");
    }
    
    testFailWith(func, "10", "6");
    testFailWith(func, "6.0", "10");
    testFailWith(func, "-6", "-10");
  }


}
