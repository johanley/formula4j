package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;



public final class AbsTEST  extends BaseTEST {

  public static void main(String args[]) {
    junit.textui.TestRunner.run(AbsTEST.class);
  }

  public void testCases(){
    Function abs = new Abs();
    testWith(abs, "1", "-1");
    testWith(abs, "1", "+1");
    testWith(abs, "1", "1");
    testWith(abs, "0", "0");
    testWith(abs, "0", "+0");
    testWith(abs, "0", "-0");
    testWith(abs, "10", "10");
    testWith(abs, "10", "-10");
    testWith(abs, "100000000", "-100000000");
    testWith(abs, "12345678901234567890", "-12345678901234567890");
  }
}
