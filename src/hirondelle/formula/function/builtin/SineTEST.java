package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class SineTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(SineTEST.class);
  }

  public void testCases(){
    Function func = new Sine();
    
    testWith(func, "0.0", "0");
    testWith(func, "1.0", HALF_PI); 
    testWith(func, "-1.0", "-" + HALF_PI); 
    testWith(func, "0.00000000000000012246467991473532", PI); // not 0.0
    testWith(func, "-0.00000000000000012246467991473532", "-" + PI); // not 0.0
    testWith(func, "-0.00000000000000024492935982947064", TWO_PI); // not 0.0 
    
  }
  

}
