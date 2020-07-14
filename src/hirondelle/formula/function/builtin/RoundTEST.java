package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class RoundTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(RoundTEST.class);
  }

  public void testCases(){
    Function func = new Round();
    
    testWith(func, "1","1.0");    
    testWith(func, "1","1");    
    testWith(func, "0","0");    
    testWith(func, "0","-0");    
    testWith(func, "1","1.000000000001");    
    testWith(func, "-1","-1.0");    
    testWith(func, "-1","-1.00000000000001");    
    testWith(func, "12345","12345.1");    
    testWith(func, "12345","12345");    
    testWith(func, "12345","12345.12345678901234567890");    
    testWith(func, "123450000","123450000.12345678901234567890");
    
    testWith(func, "1.0","1.0", "1");    
    testWith(func, "1.0","1.00", "1");    
    testWith(func, "1.0","1.000", "1");
    testWith(func, "1.0","1.0123", "1");
    
    testWith(func, "1.01","1.0126", "2");    
    testWith(func, "1.013","1.0126", "3");    
    testWith(func, "1.0126","1.0126", "4");    
    testWith(func, "1.01260","1.0126", "5");    
    testWith(func, "1.012600","1.0126", "6");    

    testWith(func, "1.01","1.0125", "2");    
    testWith(func, "1.012","1.0125", "3"); //rounds towards the even neighbour    
    testWith(func, "1.014","1.0135", "3"); //rounds towards the even neighbour    
    testWith(func, "1.0125","1.0125", "4");    
    testWith(func, "1.01250","1.0125", "5");    
    testWith(func, "1.012500","1.0125", "6");    

    testWith(func, "-1.01","-1.0125", "2");    
    testWith(func, "-1.012","-1.0125", "3"); //rounds towards the even neighbour    
    testWith(func, "-1.014","-1.0135", "3"); //rounds towards the even neighbour    
    testWith(func, "-1.0125","-1.0125", "4");    
    testWith(func, "-1.01250","-1.0125", "5");    
    testWith(func, "-1.012500","-1.0125", "6");    

    testWith(func, "101","101.123456", "0");    
    testWith(func, "101.1","101.123456", "1");    
    testWith(func, "101.12","101.123456", "2");    
    testWith(func, "101.123","101.123456", "3");    
    testWith(func, "101.1235","101.123456", "4");    
    testWith(func, "101.12346","101.123456", "5");    

    testFailWith(func, "1", "-1");
    testFailWith(func, "1", "1.0");
    
  }


}
