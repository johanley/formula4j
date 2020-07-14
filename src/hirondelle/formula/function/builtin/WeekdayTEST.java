package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class WeekdayTEST extends BaseTEST {
  public static void main(String args[]) {
    junit.textui.TestRunner.run(WeekdayTEST.class);
  }

  public void testCases(){
    Function func = new Weekday();
    
    testWith(func, "1", "2012", "12", "16");
    testWith(func, "2", "2012", "12", "17");
    testWith(func, "3", "2012", "12", "18");
    testWith(func, "4", "2012", "12", "19");
    testWith(func, "5", "2012", "12", "20");
    testWith(func, "6", "2012", "12", "21");
    testWith(func, "7", "2012", "12", "22");
    
    testWith(func, "4", "1954", "6", "30");
    testWith(func, "5", "1962", "3", "1");
    
  }

  

}
