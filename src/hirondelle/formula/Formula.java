package hirondelle.formula;

import hirondelle.formula.eval.JavaCharStream;
import hirondelle.formula.eval.MathematicalExpression;
import hirondelle.formula.eval.MathematicalExpressionConstants;
import hirondelle.formula.eval.MathematicalExpressionTokenManager;
import hirondelle.formula.eval.ParseException;
import hirondelle.formula.eval.Token;
import hirondelle.formula.eval.TokenMgrError;
import hirondelle.formula.function.Function;
import hirondelle.formula.function.builtin.BuiltInFunctions;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** 
 <b>Evaluate a mathematical formula.</b>

<p>If any variables are present in the formula, then they must all be assigned a value before the formula can 
 be successfully evaluated.
 
<p>If any custom functions are present in the formula, then they must all be defined before the formula can 
 be successfully evaluated.
*/
public final class Formula {
  
  /**
   Return all built-in functions, in a mutable Map, whose key is the name of the function as it appears in a formula.
    
   <P>The caller can alter the returned Map, and then pass the new Map to a constructor of this class.
   This allows you to add to the built-in functions, and even customize/replace the built-in functions themselves.
   In general, you may take the return value and :  
   <ul>
    <li>add your own custom functions 
    <li>substitute a different name for a built-in function (especially useful for multilingual applications)
    <li>substitute a different implementation for a built-in function (not usually necessary, but the option exists)
   </ul>
  */
  public static Map<String, Function> getDefaultFunctions(){
    return BuiltInFunctions.getAll();
  }

  /**
   Constructor for the simplest case.
   <P>As in the full constructor, but using an empty Map for the variables, and the default built-in functions.
   This constructor can only be used when there are no variables in the formula.
  */
  public Formula(String aFormula){
    this(aFormula, new LinkedHashMap<String, Decimal>(), getDefaultFunctions());
  }

  /**
   Constructor for a formula containing variables.
   As in the full constructor, but using default built-in functions.
  */
  public Formula(String aFormula, Map<String, Decimal> aVariableValues){
    this(aFormula, aVariableValues, getDefaultFunctions());
  }

  /**
   Full constructor, taking all possible parameters.
   
   @param aFormula the text of the formula; must have content; if the formula contains references to variables, 
   then values for all of those variables must be supplied in <tt>aVariableValues</tt> 
   @param aVariableValues maps all variable names to their corresponding values; possibly empty.
   @param aCustomFunctions maps all possible function names to their corresponding implementations of the {@link Function}  
   interface. Typically, callers will build this parameter by starting with {@link #getDefaultFunctions()}, and altering its return value.
  */
  public Formula(String aFormula, Map<String, Decimal> aVariableValues, Map<String, Function> aCustomFunctions){
    if (aFormula == null || aFormula.trim().length() == 0){
      throw new IllegalArgumentException("Formula has no content.");
    }
    fFormula = aFormula;
    fVariableValues = aVariableValues;
    fNamesAllVariables = getNamesFor(MathematicalExpressionConstants.VARIABLE);
    fNamesUnpopulatedVariables = listUnpopulatedVariableNames();
    
    fCustomFunctions = aCustomFunctions;
    fNamesAllFunctions = stripLastCharacter(getNamesFor(MathematicalExpressionConstants.FUNCTION));
    fNamesUnknownFunctions = listUnknownFunctionNames();
  }
  
  /** 
   Return the text of the formula passed to the constructor.
  */
  public String getFormula() { 
    return fFormula; 
  }
 
  /** 
   Return the Map of variable values passed to the constructor, if any.
   Maps variable name to a {@link Decimal} value. May return an empty Map.
  */
  public Map<String, Decimal> getVariableValues() {  
    return fVariableValues; 
  }
  
  /** 
   Return the Map of custom functions passed to the constructor, if any.
   Maps function name to a {@link Function} object. May return an empty Map.
  */
  public Map<String, Function> getCustomFunctions() {  
    return fCustomFunctions; 
  }
 
  /**
   Return true only if the formula has at least one variable.
  */
  public boolean hasVariables(){
    return ! fNamesAllVariables.isEmpty();
  }
  
  /**
   Return true only if the formula has at least one variable which has <em>not</em> been populated.
  */
  public boolean hasUnpopulatedVariables(){
    return ! fNamesUnpopulatedVariables.isEmpty();
  }
  
  /**
   Return the names of all variables appearing in the formula, if any.
  */
  public List<String> getVariableNames(){
    return fNamesAllVariables;
  }
  
  /**
   Return the names of all variables appearing in the formula which <em>aren't</em> populated, if any.
   Variables are populated by passing a Map to the constructor.
  */
  public List<String> getUnpopulatedVariableNames(){
    return fNamesUnpopulatedVariables;
  }
  
  /**
    Return the names of all functions appearing in the formula, if any.
  */
  public List<String> getFunctionNames(){
    return fNamesAllFunctions;
  }
  
  /**
   Return true only if the formula has at least one unknown function name.
   Any custom functions must be passed to the constructor.
  */
  public boolean hasUnknownFunctionNames(){
     return ! fNamesUnknownFunctions.isEmpty();
  }

  /**
   Return the names of all functions appearing in the formula which <em>aren't</em> recognized, if any.
  */
  public List<String> getUnknownFunctionNames(){
    return fNamesUnknownFunctions;
  }
  
  /** 
   Evaluate the formula, and return the answer as a number.
   Resolves variable names into corresponding values, using a Map passed to the constructor.
   
   @throws UnpopulatedVariableException if there are one or more variables in the formula that are 
   not in the Map of variable values passed to the constructor. 
   @throws UnknownFunctionException if there are one or more function names in the formula that are 
   not in the Map of functions passed to the constructor.
  */
  public Decimal getAnswer() throws MalformedFormulaException {
    Date EXPIRY_DATE = new Date(118, 10, 15); //year - 1900, 0-based month. 
    //Date EXPIRY_DATE = null;
    if (EXPIRY_DATE != null){
      Date now = new Date();
      if (now.after(EXPIRY_DATE)){
        throw new RuntimeException("The trial formula4j.jar is now passed its expiry date: " + EXPIRY_DATE);
      }
    }
    
    Decimal result = null;
    
    if ( hasUnpopulatedVariables() ){
      throw new UnpopulatedVariableException(fNamesUnpopulatedVariables.toArray(new String[0]));
    }
    else if ( hasUnknownFunctionNames() ){
      throw new UnknownFunctionException(fNamesUnknownFunctions.toArray(new String[0]));
    }
    else {
      MathematicalExpression parser = new MathematicalExpression(fFormula);
      parser.setVariables(fVariableValues);
      parser.setFunctions(fCustomFunctions);
      try {
        result = parser.Start();
      }
      catch(TokenMgrError ex){
        throw new MalformedFormulaException(fFormula, ex.getMessage());
      }
      catch (ParseException ex) {
        String msg = "Problem at position range: " + ex.currentToken.beginColumn + ".." + ex.currentToken.endColumn;
        throw new MalformedFormulaException(fFormula, msg + ": " + ex.getMessage());
      }
    }
    return result;
  }

  // PRIVATE
  
  private final String fFormula;
  
  private final Map<String, Decimal> fVariableValues;
  private final List<String> fNamesUnpopulatedVariables;
  private final List<String> fNamesAllVariables;
  
  private final Map<String, Function> fCustomFunctions;
  private final List<String> fNamesUnknownFunctions;
  private final List<String> fNamesAllFunctions;
  
  private List<String> getNamesFor(int aType){
    List<String> result = new ArrayList<String>();
    JavaCharStream scs = new JavaCharStream(new StringReader(fFormula));
    MathematicalExpressionTokenManager mgr = new MathematicalExpressionTokenManager(scs);
    try {
      for (Token t = mgr.getNextToken(); t.kind != MathematicalExpressionConstants.EOF;  t = mgr.getNextToken()){
        if (t.kind == aType){
          result.add(t.image);
        }
      }
    }
    catch (TokenMgrError ex){
      result = Collections.emptyList();
    }
    return result;
  }

  /* The function-name token includes the first paren appearing after the function name. */
  private List<String> stripLastCharacter(List<String> aList){
    List<String> result = new ArrayList<String>();
    for(String item : aList){
      result.add(item.substring(0, item.length()-1));
    }
    return result;
  }
  
  private List<String> listUnpopulatedVariableNames(){
    List<String> result = new ArrayList<String>();
    for (String variable : fNamesAllVariables){
      if (! fVariableValues.keySet().contains(variable) ){
        result.add(variable);
      }
    }
    return result;
  }
  
  private List<String> listUnknownFunctionNames(){
    List<String> result = new ArrayList<String>();
    for (String function : fNamesAllFunctions){
      if (! fCustomFunctions.keySet().contains(function) ){
        result.add(function);
      }
    }
    return result;
  }
}
