package hirondelle.formula;

import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;
import hirondelle.formula.function.FunctionDefinedInText;

import java.util.LinkedHashMap;
import java.util.Map;

/**
  Examples of basic usage of the formula4j API.
*/
public final class Examples {
  
  /** Run the examples, and output to stdout. */
  public static void main(String... aArgs)  throws MalformedFormulaException{
    log("Starting...");
    
    Examples example = new Examples();
    example.simpleMath();
    example.useSomeBuiltInFunctions();
    example.listDefaultNamesOfBuiltInFunctions();
    example.listFunctionsUsedInFormula();
    example.listVariablesUsedInFormula();
    example.populateVariableValues();
    example.useGreekLettersAsVariables();
    example.defineCustomFunctionInText();
    example.defineCustomFunctionInCode();
    example.renameBuiltInFunction();
    example.failWhenVariablesArentPopulated();
    example.failWhenUnknownFunction();
    example.failWhenBadSyntax();
    
    log("Done.");
  }
  
  static void log(Object aMsg){
    System.out.println(String.valueOf(aMsg));
  }
  
  void simpleMath() throws MalformedFormulaException {
    log("Simple math.");
    Formula formula = new Formula("3.5 * (6 + 3)");
    log(formula.getAnswer()); //31.5
  }

  void useSomeBuiltInFunctions() throws MalformedFormulaException {
    log("Use some built-in functions.");
    Formula formula = new Formula("sqrt(floor(9.5))");
    log(formula.getAnswer()); // 3.0
    
    formula = new Formula("median(2,5,3,6)");
    log(formula.getAnswer()); // 4
    
    formula = new Formula("max(2,6,3,5)");
    log(formula.getAnswer()); // 6
    
    formula = new Formula("daysbetween(2012,12,25,  2013,12,25)");
    log(formula.getAnswer()); // 365.0
    
    formula = new Formula("sin(0.5)");
    log(formula.getAnswer()); // 0.479425538604203
  }

  void listDefaultNamesOfBuiltInFunctions(){
    log("Default names of built-in functions (" + Formula.getDefaultFunctions().keySet().size() + "):");
    for(String functionName : Formula.getDefaultFunctions().keySet()){
      log("  " + functionName);
    }
  }
  
  void listFunctionsUsedInFormula(){
    Formula formula = new Formula("sqrt(floor(9.5))");
    log("Functions used by formula: " + formula.getFunctionNames());//[sqrt, floor]
  }
  
  void listVariablesUsedInFormula(){
    Formula formula = new Formula("2 + a + x10");
    log("Variables used by formula: " + formula.getVariableNames()); // [a, x10]
  }

  void populateVariableValues() throws MalformedFormulaException{
    log("Populate variable names.");
    Map<String, Decimal> variables = new LinkedHashMap<String, Decimal>();
    variables.put("a", Decimal.from(7));
    variables.put("x10", Decimal.from(-6));
    Formula formula = new Formula("2 + a + x10", variables);
    log("Variables used by formula: " + formula.getVariableNames()); // [a, x10]
    log(formula.getAnswer()); //3
  }
  
  void useGreekLettersAsVariables() throws MalformedFormulaException{
    log("Use Greek letters for variable names.");
    Map<String, Decimal> variables = new LinkedHashMap<String, Decimal>();
    variables.put("φ", Decimal.from(7));
    variables.put("λ", Decimal.from(-6));
    Formula formula = new Formula("2 + φ + λ", variables);
    log("Variables used by formula: " + formula.getVariableNames()); // [φ, λ]
    log(formula.getAnswer()); //3
  }
  
  void defineCustomFunctionInText() throws MalformedFormulaException{
    log("Define a custom function in text.");
    Map<String, Decimal> variables = new LinkedHashMap<String, Decimal>(); //empty in this case
    
    Map<String, Function> customFunctions = Formula.getDefaultFunctions();
    Function bmi = new FunctionDefinedInText("bmi(m,h) = m/h^2");
    customFunctions.put("bmi", bmi);
    
    Formula formula = new Formula("bmi(64,8)", variables, customFunctions);
    log(formula.getAnswer()); // 1
    
    //a variation on the above, using variables this time
    variables.put("m", Decimal.from(64));
    variables.put("h", Decimal.from(8));
    formula = new Formula("5 * bmi(m,h)", variables, customFunctions);
    log(formula.getAnswer()); // 5
  }
  
  void defineCustomFunctionInCode() throws MalformedFormulaException{
    log("Define a custom function in code.");
    Map<String, Decimal> variables = new LinkedHashMap<String, Decimal>(); //empty in this case
    
    Map<String, Function> customFunctions = Formula.getDefaultFunctions();
    customFunctions.put("bmi", new Bmi()); // see below
    
    Formula formula = new Formula("bmi(64,8)", variables, customFunctions);
    log(formula.getAnswer()); // 1
    
    //a variation on the above, using variables this time
    variables.put("m", Decimal.from(64));
    variables.put("h", Decimal.from(8));
    formula = new Formula("5 * bmi(m,h)", variables, customFunctions);
    log(formula.getAnswer()); // 5
  }
  
  private final class Bmi implements Function {
    public Decimal calculate(Decimal... aArgs) {
      Check.numArgs(2, aArgs);
      Decimal mass = aArgs[0]; 
      Decimal height = aArgs[1]; 
      return mass.div(height.pow(2));
    }
  }
  
  void renameBuiltInFunction() throws MalformedFormulaException{
    log("Rename a built-in function.");
    Map<String, Decimal> variables = new LinkedHashMap<String, Decimal>(); //empty in this case

    //start with the built-in functions, then modify the map to suit your needs
    Map<String, Function> customFunctions = Formula.getDefaultFunctions();
    // get a reference to the function object itself
    Function sineFunction = customFunctions.get("sin");
    
    //remove it, then add it back in using the new name
    customFunctions.remove(sineFunction);    
    // this also allows for translating function names into different languages
    customFunctions.put("sine", sineFunction);
    
    Formula formula = new Formula("sine(0.5)", variables, customFunctions);
    log(formula.getAnswer()); // 0.479425538604203
  }
  
  void failWhenVariablesArentPopulated() throws MalformedFormulaException{
    Formula formula = new Formula("2 + a + x10");
    try {
      log(formula.getAnswer());
    }
    catch (UnpopulatedVariableException ex){
      //shows only 1 error at a time, not all errors at once
      log(ex);
    }
  }

  void failWhenUnknownFunction() throws MalformedFormulaException{
    Formula formula = new Formula("bmi(2,5)");
    try {
      log(formula.getAnswer());
    }
    catch(UnknownFunctionException ex){
      log(ex);
    }
  }
  
  void failWhenBadSyntax() {
    Formula formula = new Formula("(2 + 5");
    try {
      log(formula.getAnswer());
    }
    catch(MalformedFormulaException ex){
      log(ex);
    }
    
    formula = new Formula("3 * 5 +");
    try {
      log(formula.getAnswer());
    }
    catch(MalformedFormulaException ex){
      log(ex);
    }
  }

}
