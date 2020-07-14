package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class LoanPaymentTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(LoanPaymentTEST.class);
  }

  public void testCases(){
    Function func = new LoanPayment();
    
    testWith(func, "1.00083333333333333333", "1", "0.01", "1");
    testWith(func, "542.37809098350935641242", "25000", "0.02", "48");
    testWith(func, "4.38713897340684378120", "100", "0.05", "24");
    testWith(func, "7.33764573879376111258", "1000", "0.08", "360");
    
    testFailWith(func, "1.00083333333333333333", "0", "0.01", "1");
    testFailWith(func, "1.00083333333333333333", "1", "0", "1");
    testFailWith(func, "1.00083333333333333333", "1", "0.01", "0");
  }
  

}
