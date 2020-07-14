package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;


public final class RadiansTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(RadiansTEST.class);
  }

  public void testCases(){
    Function func = new Radians();
    
    testWith(func, "0.0", "0");
    testWith(func, "0.0", "-0");
    testWith(func, HALF_PI, "90");
    testWith(func, PI, "180");
    testWith(func, QUARTER_PI, "45");
    testWith(func, TWO_PI, "360");
    
    testWith(func, "-" + HALF_PI, "-90");
    testWith(func, "-" + PI, "-180");
    testWith(func, "-" + QUARTER_PI, "-45");
    testWith(func, "-" + TWO_PI, "-360");

  }


}
