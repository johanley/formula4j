package hirondelle.formula.function.builtin;

import hirondelle.formula.function.Function;

public final class DistanceTEST extends BaseTEST {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(DistanceTEST.class);
  }

  public void testCases(){
    Function func = new Distance();
    
    //these results are very dependent on the exact algorithm
    //the large number of significant digits is often without any real 
    //physical significance; the caller should usually round the result.
    
    //on the equator
    testWith(func, "0.0", "0", "0", "0", "0");
    testWith(func, "111.19494409785126" , "0", "0", "0", "1");
    testWith(func, "111.19494409785126" , "0", "0", "0", "-1");
    testWith(func, "111.19494409785126" , "0", "1", "0", "0"); //permute the above positions
    testWith(func, "111.19494409785126" , "0", "-1", "0", "0");

    testWith(func, "111.19494409785142" , "0", "90", "0", "89"); //note the small, non-significant differences with previous
    testWith(func, "111.19494409785142" , "0", "90", "0", "91");
    testWith(func, "111.19494409785142" , "0", "-90", "0", "-91");
    testWith(func, "111.19494409785142" , "0", "-90", "0", "-89");
    testWith(func, "5003.772484403306" , "0", "0", "0", "45");
    testWith(func, "5003.772484403306" , "0", "0", "0", "-45");
    testWith(func, "10007.544968806613" , "0", "0", "0", "90");
    testWith(func, "10007.544968806613" , "0", "0", "0", "-90");
    
    //on the prime meridian
    testWith(func, "111.19494409785126", "0", "0", "1", "0");
    testWith(func, "111.19494409785126", "0", "0", "-1", "0");
    testWith(func, "5003.772484403306", "0", "0", "45", "0");
    testWith(func, "10007.544968806613", "0", "0", "90", "0");
    testWith(func, "111.19494409785142", "39", "0", "38", "0");
    testWith(func, "111.19494409785142", "-39", "0", "-38", "0");
    testWith(func, "62.1956243587711", "-39.1236546", "0", "-38.564316", "0");
    
    //permute two arbitrary positions, get the same distance
    testWith(func, "5035.461602279077", "45", "-63", "22", "-15");
    testWith(func, "5035.461602279077", "22", "-15", "45", "-63");
    
    testWith(func, "15577.95708568971", "45", "-63", "-22", "77");
    testWith(func, "15577.95708568971", "-22", "77", "45", "-63");
  }


}
