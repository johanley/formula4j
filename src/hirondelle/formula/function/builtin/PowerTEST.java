package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class PowerTEST extends BaseTEST {

  public static void main(String args[]) {
    junit.textui.TestRunner.run(PowerTEST.class);
  }

  public void testCases(){
    Function func = new Power();
    
    testWith(func, "1", "1", "1");
    testWith(func, "4", "2", "2");
    testWith(func, "8", "2", "3.0");
    testWith(func, "4.0", "16", "0.5");
    testWith(func, "0.25", "16", "-0.5");
    
    testWith(func, "4", "-2", "2");
    testWith(func, "-8", "-2", "3.0");
    
    testWith(func, "1", "0", "0");
    testWith(func, "1", "123", "0");
    testWith(func, "1", "-123", "0");
    testWith(func, "1", "-123.156", "0");
    testWith(func, "123.45", "123.45", "1");
    
    testWith(func, "0", "0", "1");
    testWith(func, "0", "0", "2");
    //testWith(func, "0", "0", "-2"); // this raises an exception
    
  }


  
}
