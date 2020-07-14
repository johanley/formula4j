package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class CosineTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(CosineTEST.class);
  }

  public void testCases(){
    Function func = new Cosine();
    
    testWith(func, "1.0", "0");
    testWith(func, "0.00000000000000006123233995736766", HALF_PI); //not 0.0
    testWith(func, "-1.0", PI);
    testWith(func, "-1.0", "-" + PI);
  }

}
