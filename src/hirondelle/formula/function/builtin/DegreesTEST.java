package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class DegreesTEST extends BaseTEST {

  public static void main(String args[]) {
    junit.textui.TestRunner.run(DegreesTEST.class);
  }

  public void testCases(){
    Function func = new Degrees();
    
    testWith(func, "0.0", "0");
    testWith(func, "90.0", HALF_PI);
    testWith(func, "-90.0", "-" + HALF_PI);
    testWith(func, "180.0", PI);
    testWith(func, "-180.0", "-"+PI);
    testWith(func, "45.0", QUARTER_PI);
    testWith(func, "-45.0", "-" + QUARTER_PI);
    testWith(func, "360.0", TWO_PI);
    testWith(func, "-360.0", "-" + TWO_PI);
    
  }
  
}
