package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class InvestmentTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(InvestmentTEST.class);
  }

  public void testCases(){
    Function func = new Investment();
    
    testWith(func, "0", "0", "0", "1", "1");
    
    testWith(func, "1", "1", "0", "1", "1");
    testWith(func, "1", "1", "0", "2", "1");
    testWith(func, "1", "1", "0", "150", "1");
    testWith(func, "1", "1", "0", "1", "100");
    
    testWith(func, "105.00", "100", "0.05", "1", "1");
    testWith(func, "110.2500", "100", "0.05", "2", "1");
    testWith(func, "115.762500", "100", "0.05", "3", "1");
    testWith(func, "121.55062500", "100", "0.05", "4", "1");
    
    testWith(func, "105.062500", "100", "0.05", "1", "2");
    
    testWith(func, "101.00", "100", ".01", "1", "1");
    testWith(func, "100.100", "100", ".001", "1", "1");
    testWith(func, "100.0100", "100", ".0001", "1", "1");
    
    testWith(func, "1296296284.50", "1234567890", "0.05", "1", "1");
    
    testFailWith(func, "-1", "0", "1", "1");    
    testFailWith(func, "1", "-1", "1", "1");    
    testFailWith(func, "1", "1", "-1", "1");    
    testFailWith(func, "1", "1", "1.1", "1");    
    testFailWith(func, "1", "1", "1.0", "1");    
    testFailWith(func, "1", "1", "1", "-1");    
    testFailWith(func, "1", "1", "1", "1.0");    
  }

  

}
