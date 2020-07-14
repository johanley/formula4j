package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class ArithmeticMeanTEST extends BaseTEST{

  public static void main(String args[]) {
    junit.textui.TestRunner.run(ArithmeticMeanTEST.class);
  }
  
  public void testCases(){
    Function func = new ArithmeticMean();
    testWith(func, "0", "0", "0");
    testWith(func, "0", "1", "-1");
    testWith(func, "0", "88", "-88");
    testWith(func, "-2", "-1", "-3");
    testWith(func, "2", "1", "3");
    testWith(func, "2", "1", "2", "3");
    testWith(func, "1.5", "1.0", "2.0");
    testWith(func, "-1.5", "-1.0", "-2.0");
    testWith(func, "2.5", "1.0", "2.0", "3", "4");
  }
  
}
