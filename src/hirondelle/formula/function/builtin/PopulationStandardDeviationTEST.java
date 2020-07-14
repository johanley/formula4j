package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class PopulationStandardDeviationTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(PopulationStandardDeviationTEST.class);
  }

  public void testCases(){
    Function func = new PopulationStandardDeviation();
    
    testWith(func, "0.0", "0", "0");
    testWith(func, "0.0", "1", "1");
    testWith(func, "0.0", "1.0", "1.0");
    testWith(func, "0.0", "-1.0", "-1.0");
    testWith(func, "0.0", "5123", "5123");
    testWith(func, "0.0", "5123.3213", "5123.3213");
    testWith(func, "2.0", "2", "4", "4", "4", "5", "5", "7", "9");
    testWith(func, "1.0", "100", "102");
    testWith(func, "323.0", "-323", "323");
    
    testFailWith(func, "1");
    testFailWith(func);
  }


}
