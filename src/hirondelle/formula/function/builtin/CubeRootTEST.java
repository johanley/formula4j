package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class CubeRootTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(CubeRootTEST.class);
  }

  public void testCases(){
    Function func = new CubeRoot();
    
    testWith(func, "0.0", "0");
    testWith(func, "0.0", "+0");
    testWith(func, "-0.0", "-0");
    testWith(func, "3.0", "27");
    testWith(func, "-3.0", "-27");
    testWith(func, "100.0", "1000000");
    testWith(func, "-100.0", "-1000000");
  }
}
