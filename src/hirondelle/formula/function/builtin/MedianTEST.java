package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class MedianTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(MedianTEST.class);
  }

  public void testCases(){
    Function func = new Median();
    
    testWith(func, "0");
    testWith(func, "0", "0");
    testWith(func, "1", "1");
    testWith(func, "-1", "-1");
    testWith(func, "2", "1", "2", "3");
    testWith(func, "2", "3", "2", "1");
    testWith(func, "2", "3", "1", "2");
    testWith(func, "2.5", "1", "2", "3", "4");
    testWith(func, "-2.5", "-1", "-2", "-3", "-4");
    testWith(func, "16", "20", "10", "15", "17");
    testWith(func, "16", "20.3213", "10.5", "15", "17");
    testWith(func, "16.0", "20", "10", "15.5", "16.5");
    
  }


}
