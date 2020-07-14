package hirondelle.formula;

import java.util.LinkedHashMap;
import java.util.Map;

import junit.framework.TestCase;

public final class FormulaTEST extends TestCase {
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(FormulaTEST.class);
  }

  public void testCtor(){
    failedCtor(null);
    failedCtor("");
    failedCtor(" ");
    failedCtor("  ");
  }

  public void testUsesDefaultItems(){
    Formula formula = new Formula("3+5");
    assertTrue(formula.getCustomFunctions().keySet().equals(Formula.getDefaultFunctions().keySet()));
    assertTrue(! formula.hasUnknownFunctionNames());
    assertTrue(! formula.hasUnpopulatedVariables());
    assertTrue( formula.getUnknownFunctionNames().isEmpty());
    assertTrue( formula.getUnpopulatedVariableNames().isEmpty());
    assertTrue( formula.getVariableNames().isEmpty());
    assertTrue( formula.getFunctionNames().isEmpty());
    
    formula = new Formula("3+5", new LinkedHashMap<String, Decimal>());
    assertTrue(formula.getCustomFunctions().keySet().equals(Formula.getDefaultFunctions().keySet()));
    assertTrue(! formula.hasUnknownFunctionNames());
    assertTrue(! formula.hasUnpopulatedVariables());
    assertTrue( formula.getUnknownFunctionNames().isEmpty());
    assertTrue( formula.getUnpopulatedVariableNames().isEmpty());
    assertTrue( formula.getVariableNames().isEmpty());
    assertTrue( formula.getFunctionNames().isEmpty());
  }
  
  public void testBasicMath() throws MalformedFormulaException{
    evalFormula("8", "3+5");
    evalFormula("-8", "-3-5");
    evalFormula("8.0", "3.0+5");
    evalFormula("8.01", "3.01+5");
    evalFormula("16", "(3+5)*2");
    evalFormula("-16", "-(3+5)*2");
    evalFormula("4", "((3+5)*2)/4");
    evalFormula("9", "99/11");
    evalFormula("10", "99/11 + 1");
    evalFormula("28", "(99/11 + 1) * 3 - 2");
    evalFormula("3", "8%5");
    evalFormula("27", "3^3");
  }
  
  public void testBasicFunctions() throws MalformedFormulaException{
    /* 
    Each function has its own separate test case.
    This is more about how functions work in a formula, in general. 
   */
    evalFormula("8", "sum(3,5)");
    evalFormula("23", "sum(3,5) + avg(10,20)");
    evalFormula("-23", "-(sum(3,5) + avg(10,20))");
    evalFormula("23", "sum(3,5) + avg(10,20)");
    evalFormula("23.0", "sum(3,5) + avg(10.0,20)");
    evalFormula("23.0", "sum(3.0,5) + avg(10,20)");
    evalFormula("73", "sum(3,5) + avg(10,20) + max(2,-100,50,9)" );
    evalFormula("-27", "sum(3,5) + avg(10,20) - max(2,-100,50,9)" );
    evalFormula("2.0", "sqrt(sqrt(16))");
    evalFormula("3", "round(e)");   
    evalFormula("2.7", "round(e,1)");   
    evalFormula("2.72", "round(e,2)");   
    evalFormula("2.718", "round(e,3)");   
    evalFormula("2.7183", "round(e,4)");   
  }
  
  public void testConstants() throws MalformedFormulaException{
    evalFormula("2.718281828459045", "e");   // e and pi can be either case (but not mixed case for pi)
    evalFormula("2.718281828459045", "E");     
    evalFormula("3.141592653589793", "pi");   
    evalFormula("3.141592653589793", "PI"); 
    evalFormula("5.859874482048838", "pi + e"); 
    evalFormula("5.859874482048838", "pi + E"); 
    evalFormula("5.859874482048838", "PI + e"); 
    evalFormula("5.859874482048838", "PI + E"); 
    evalFormula("5.141592653589793", "pi + 2");   
    evalFormula("6.283185307179586", "2*PI");
    
    // constants are treated separately from variables
    Map<String, Decimal> variableValues = new LinkedHashMap<String, Decimal>();
    variableValues.put("x", Decimal.from(2));
    evalFormula("8.283185307179586", "2*PI + x", variableValues);   
    evalFormula("8.283185307179586", "2 * pi + x", variableValues);   
    evalFormula("7.436563656918090", "2 * e + x", variableValues);
    
    evalFormula("3.14", "round(pi, 2)");   
    evalFormula("3.142", "round(pi, 3)");   
    evalFormula("3.1416", "round(pi, 4)");   
    evalFormula("3.14159", "round(pi, 5)");
    
    // use pi in trig functions
    evalFormula("1.0", "sin(pi/2)");   
    evalFormula("0.00000000000000012246467991473532", "sin(pi)"); // rounding, approximations can occur   
    evalFormula("0.00000000000000006123233995736766", "cos(pi/2)");   
    evalFormula("-1.0", "cos(pi)");   
    
  }
  
  public void testExponents() throws MalformedFormulaException{
    evalFormula("1.0", "1.0E0");
    
    evalFormula("1", "1E0");   
    evalFormula("1", "1E+0");   
    evalFormula("1", "1E-0");
    
    evalFormula("1.0", "1.0E0");   
    evalFormula("1.0", "1.0E+0");   
    evalFormula("1.0", "1.0E-0");
    
    evalFormula("1.0", "1.0e0");   
    evalFormula("1.0", "1.0e+0");   
    evalFormula("1.0", "1.0e-0");
    
    evalFormula("152.3", "1.523e2");   
    evalFormula("1523000", "1.523e6");    
    evalFormula("0.01523", "1.523e-2");   

  }
  
  public void testMissingVariable() throws MalformedFormulaException{
    missingVariable("3+a");
    missingVariable("a * 3");
    missingVariable("(6+a) * 3");
    missingVariable("(6+a) * avg(3,8,2,6,8,5,201.10)");
  }
  
  public void testUnknownFunction() throws MalformedFormulaException{
    missingFunction("3+bmi(3,5)");
    missingFunction("average(1285.2123,5)");
  }
  
  public void testBadSyntax() {
    badSyntax("3+5+");
    badSyntax("(3+5");
    badSyntax("(3+5))");
    badSyntax("3+5)");
    badSyntax("(3+5)*6)");
    badSyntax("avg(2,3,2,))");
    badSyntax("avg(2,3,2,");
  }
  
  // PRIVATE 
  
  private void failedCtor(String aFormula){
    try {
      @SuppressWarnings("unused")
      Formula formula = new Formula(aFormula);
      fail("Expected to fail with an empty formula.");
    }
    catch(RuntimeException ex){
      // this is the good branch
    }
  }

  private void evalFormula(String aExpected, String aFormula) throws MalformedFormulaException{
    Formula formula = new Formula(aFormula);
    Decimal actual = formula.getAnswer();
    Decimal expected = Decimal.from(aExpected);
    if (! expected.eq(actual) ){
      fail("Expected " + expected + " doesn't equal actual " + actual);
    }
  }
  
  private void evalFormula(String aExpected, String aFormula, Map<String, Decimal> aVariableValues) throws MalformedFormulaException{
    Formula formula = new Formula(aFormula, aVariableValues);
    Decimal actual = formula.getAnswer();
    Decimal expected = Decimal.from(aExpected);
    if (! expected.eq(actual) ){
      fail("Expected " + expected + " doesn't equal actual " + actual);
    }
  }

  private void missingVariable(String aFormula) throws MalformedFormulaException{
    Formula formula = new Formula(aFormula);
    assertTrue(formula.hasUnpopulatedVariables());
    assertTrue(formula.getUnpopulatedVariableNames().size() == 1);
    try {
      formula.getAnswer();
      fail("Should have had a problem with missing variable.");
    }
    catch (UnpopulatedVariableException ex){
      // this is the good branch
    }
  }
  
  private void missingFunction(String aFormula) throws MalformedFormulaException{
    Formula formula = new Formula(aFormula);
    assertTrue(formula.hasUnknownFunctionNames());
    assertTrue(formula.getUnknownFunctionNames().size() == 1);
    try {
      formula.getAnswer();
      fail("Should have had a problem with an unknown function.");
    }
    catch (UnknownFunctionException ex){
      // this is the good branch
    }
  }
  
  private void badSyntax(String aFormula) {
    Formula formula = new Formula(aFormula);
    try {
      formula.getAnswer();
      fail("Should have had a problem with bad syntax.");
    }
    catch (MalformedFormulaException ex){
      // this is the good branch
    }
  }
  
}
