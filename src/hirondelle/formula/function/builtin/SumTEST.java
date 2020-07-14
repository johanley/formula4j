package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class SumTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(SumTEST.class);
  }

  public void testCases(){
    Function func = new Sum();
    
    testWith(func, "0"); // returns 0 even with 0 args
    testWith(func, "0", "0");
    testWith(func, "0.0", "0.0");
    testWith(func, "0", "1", "-1");
    testWith(func, "0.0", "1", "-1.0");
    testWith(func, "30.58", "30", "0.58");
    testWith(func, "29", "30", "-1");
    testWith(func, "-31", "-30", "-1");
    testWith(func, "-31.0", "-30", "-1.0");
    testWith(func, "0", "3", "8", "-11");
    testWith(func, "26", "3", "8", "15");
    testWith(func, "26.001", "3", "8.001", "15");
  }
  

}
