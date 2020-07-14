package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class ModifiedJulianDateTEST extends BaseTEST {

  public static void main(String args[]) {
    junit.textui.TestRunner.run(ModifiedJulianDateTEST.class);
  }

  public void testCases(){
    Function func = new ModifiedJulianDate();
    
    testWith(func, "0.0", "1858", "11", "17");
    testWith(func, "-1.0", "1858", "11", "16");
    testWith(func, "1.0", "1858", "11", "18");
    testWith(func, "43259.0", "1977", "4", "26");
    testWith(func, "36115.0", "1957", "10", "4");
    
    testFailWith(func, "-1", "1", "1");    
    testFailWith(func, "1", "-1", "1");    
    testFailWith(func, "1", "1", "-1");
    
    testFailWith(func, "1", "0", "0");    
    testFailWith(func, "1", "1.0", "1");    
    testFailWith(func, "1", "13", "1");    
    testFailWith(func, "1", "1", "32");    
    
  }


}
