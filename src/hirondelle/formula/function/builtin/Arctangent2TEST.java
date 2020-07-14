package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class Arctangent2TEST extends BaseTEST {

  public static void main(String args[]) {
    junit.textui.TestRunner.run(Arctangent2TEST.class);
  }
  
  public void testCases(){
    Function func = new Arctangent2();
    testWith(func, "0.0", "0", "0");
    testWith(func, QUARTER_PI, "1", "1");
    testWith(func, HALF_PI, "1", "0");
    testWith(func, PI, "0", "-1");
    
    testWith(func, "-" + QUARTER_PI, "-1", "1");
    testWith(func, "-" + HALF_PI, "-1", "0");
  }
  
  
}
