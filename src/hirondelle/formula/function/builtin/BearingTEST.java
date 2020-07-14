package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public class BearingTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(BearingTEST.class);
  }

  public void testCases(){
    Function func = new Bearing();
    
    //on the equator to start with
    testWith(func, "0.0", "0", "0", "1", "0"); // due north
    testWith(func, "90.0", "0", "0", "0", "1"); // due east
    testWith(func, "180.0", "0", "0", "-1", "0"); // due south
    testWith(func, "270.0", "0", "0", "0", "-1"); // due west
    
    //88 deg east
    testWith(func, "0.0", "0", "88", "1", "88"); // due north
    testWith(func, "90.0", "0", "88", "0", "89"); // due east
    testWith(func, "180.0", "0", "88", "-1", "88"); // due south
    testWith(func, "270.0", "0", "88", "0", "87"); // due west

    //130 deg east
    testWith(func, "0.0", "0", "130", "1", "130"); // due north
    testWith(func, "90.0", "0", "130", "0", "131"); // due east
    testWith(func, "180.0", "0", "130", "-1", "130"); // due south
    testWith(func, "270.0", "0", "130", "0", "129"); // due west

    //88 deg west
    testWith(func, "0.0", "0", "-88", "1", "-88"); // due north
    testWith(func, "90.0", "0", "-88", "0", "-87"); // due east
    testWith(func, "180.0", "0", "-88", "-1", "-88"); // due south
    testWith(func, "270.0", "0", "-88", "0", "-89"); // due west

    //130 deg west
    testWith(func, "0.0", "0", "-130", "1", "-130"); // due north
    testWith(func, "90.0", "0", "-130", "0", "-129"); // due east
    testWith(func, "180.0", "0", "-130", "-1", "-130"); // due south
    testWith(func, "270.0", "0", "-130", "0", "-131"); // due west

    //antipodes - direction is indeterminate - you can go in any direction 
    testWith(func, "90.0", "0", "0", "0", "180"); //says east 
    testWith(func, "90.0", "0", "1", "0", "180"); //keep going east...
    testWith(func, "90.0", "0", "10", "0", "180"); //keep going east...
    
    testWith(func, "270.0", "0", "0", "0", "-180");  //says west
    testWith(func, "270.0", "0", "-1", "0", "-180");  //keep going west...
    testWith(func, "270.0", "0", "-10", "0", "-180");  //keep going west...
    
    //when you move off the equator, then the numbers aren't 90 deg
    testWith(func, "0.0", "44", "63", "45", "63"); // due north
    testWith(func, "89.65266625244158", "44", "63", "44", "64"); // due east
    testWith(func, "180.0", "44", "63", "43", "63"); // due south
    testWith(func, "270.3473337475584", "44", "63", "44", "62"); // due west

    testWith(func, "0.0", "-44", "-63", "-43", "-63"); // due north
    testWith(func, "90.34733374755848", "-44", "-63", "-44", "-62"); // due east
    testWith(func, "180.0", "-44", "-63", "-45", "-63"); // due south
    testWith(func, "269.6526662524415", "-44", "-63", "-44", "-64"); // due west
  }
}
