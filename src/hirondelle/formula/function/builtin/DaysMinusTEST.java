package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class DaysMinusTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(DaysMinusTEST.class);
  }

  public void testCases(){
    Function func = new DaysMinus();
    testWith(func, "19571004.81", "1957", "10", "4.81", "0");
    testWith(func, "19571003.81", "1957", "10", "4.81", "1");
    testWith(func, "19571003.71", "1957", "10", "4.81", "1.1");
    testWith(func, "19571005.81", "1957", "10", "4.81", "-1");
    testWith(func, "19571005.81", "1957", "10", "4.81", "-1");
    
    testWith(func, "19571004.0", "1957", "10", "4", "0");
    testWith(func, "19571003.0", "1957", "10", "4", "1");
    testWith(func, "19571005.0", "1957", "10", "4", "-1");
    
    testWith(func, "19571001.0", "1957", "10", "4", "3");
    testWith(func, "19570930.0", "1957", "10", "4", "4");
    testWith(func, "19571031.0", "1957", "10", "4", "-27");
    testWith(func, "19571101.0", "1957", "10", "4", "-28");
  }
  

}
