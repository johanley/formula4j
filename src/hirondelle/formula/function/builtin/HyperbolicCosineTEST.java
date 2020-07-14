package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class HyperbolicCosineTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(HyperbolicCosineTEST.class);
  }

  public void testCases(){
    Function func = new HyperbolicCosine();
    
    testWith(func, "1.0", "0");
    testWith(func, "1.543080634815244", "1");
    
  }
  

}
