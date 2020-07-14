package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class DaysBetweenTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(DaysBetweenTEST.class);
  }

  public void testCases(){
    Function func = new DaysBetween();
    
    testWith(func, "1.0", "1950", "1", "1", "1950", "1", "2");
    testWith(func, "-1.0", "1950", "1", "1", "1949", "12", "31");
    testWith(func, "-2.0", "1950", "1", "1", "1949", "12", "30");
    testWith(func, "-365.0", "1950", "1", "1", "1949", "1", "1");
    testWith(func, "18547.0", "1962", "3", "1", "2012", "12", "10");
    testWith(func, "27689.0", "1910", "4", "20", "1986", "2", "9");
    testWith(func, "10000.0", "1991", "7", "11", "2018", "11", "26");
    testWith(func, "10000.1", "1991", "7", "11", "2018", "11", "26.1");
    testWith(func, "9999.9", "1991", "7", "11.1", "2018", "11", "26");
  }

  

}
