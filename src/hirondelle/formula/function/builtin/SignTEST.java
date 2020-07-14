package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class SignTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(SignTEST.class);
  }

  public void testCases(){
    Function func = new Sign();
    
    testWith(func, "1", "1");
    testWith(func, "-1", "-1");
    testWith(func, "1", "12");
    testWith(func, "-1", "-12");
    testWith(func, "0", "0");
    testWith(func, "0", "+0");
    testWith(func, "0", "-0");
  }


}
