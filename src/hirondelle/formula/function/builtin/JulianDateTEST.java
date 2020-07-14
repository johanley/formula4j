package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class JulianDateTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(JulianDateTEST.class);
  }

  public void testCases(){
    Function func = new JulianDate();
    
    testWith(func, "2436116.31", "1957", "10", "4.81");
    testWith(func, "2451545.0", "2000", "1", "1.5");
    testWith(func, "2446822.5", "1987", "1", "27.0");
    testWith(func, "2415020.5", "1900", "1", "1");
    testWith(func, "2305447.5", "1600", "1", "1");
    
    testFailWith(func, "-1", "1", "1");    
    testFailWith(func, "1", "-1", "1");    
    testFailWith(func, "1", "1", "-1");
    
    testFailWith(func, "1", "0", "0");    
    testFailWith(func, "1", "1.0", "1");    
    testFailWith(func, "1", "13", "1");    
    testFailWith(func, "1", "1", "32");    
  }

  

}
