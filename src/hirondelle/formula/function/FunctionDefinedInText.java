package hirondelle.formula.function;

import hirondelle.formula.Decimal;
import hirondelle.formula.Formula;
import hirondelle.formula.MalformedFormulaException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 Define a custom function at runtime, using simple text.
 
 <P>The main reason this class exists is to let the end user define custom 
 functions at runtime, without the need to write any code.
 
 <P>In addition, this class can also be used in regular code, which can
 be useful for quick experiments. Another advantage is 
 that it's compact, and reads at a higher level of abstraction.
 
 <P>The main disadvantage of using this class, of course, is that the implementation 
 uses the formula4j parser, and therefore may be a bit slower than an class 
 which implements the <tt>Function</tt> interface directly. 
 As usual, this may or may not be significant, depending on the context of your application.
*/
public final class FunctionDefinedInText implements Function {
  
  /**
   Constructor.
   
   <P>The single parameter passed to this constructor is a complete function definition.
   An example:
   <pre>
Function bmi = new FunctionDefinedInText("bmi(m,h) = m/h^2");
   </pre>
   
   <P>The left-hand side of the equation defines both the name of the function, and the ordered sequence of its expected parameters.
    
   <P>The right-hand side of the equation defines how the parameter values are used to calculate the function's result.
   The right-hand side uses the same syntax as used elsewhere by formula4j.
   
   <P>A function can have 0..N parameters.
   */
  public FunctionDefinedInText(String aFunctionDefinition) {
    fName = aFunctionDefinition.substring(0, aFunctionDefinition.indexOf("(")).trim();
    String args = aFunctionDefinition.substring(aFunctionDefinition.indexOf("(") + 1, aFunctionDefinition.indexOf(")")).trim();
    fArgNames = Arrays.asList(args.split(","));
    fFormula = aFunctionDefinition.substring(aFunctionDefinition.indexOf("=") + 1).trim();
  }
  
  /**
   Return the result of evaluating the function.
   
   <P>The number of arguments must match the number of arguments as defined in the function definition 
   passed to the constructor. There can be no optional arguments.
   
   <P>As usual, the order of the passed arguments must have the exact same order as the arguments as defined in 
   the function definition passed to the constructor. 
   
   <P>In this case, no further validations are performed on the arguments. 
  */
  public Decimal calculate(Decimal... aArgs) {
    Check.numArgs(fArgNames.size(), aArgs);
    
    Map<String, Decimal> values = new LinkedHashMap<String, Decimal>(); // preserves the order
    int idx = 0;
    for(Decimal arg : aArgs){
      values.put(fArgNames.get(idx), arg);
      ++idx;
    }
    
    Decimal result = null;
    Formula formula = new Formula(fFormula, values);
    try {
      result = formula.getAnswer();
    }
    catch (MalformedFormulaException ex) {
      throw new RuntimeException(ex.getMessage(), ex);
    }
    return result;
  }
  
  /**
   Return the name of the function.
   <p>The name is extracted from the left-hand side of the function definition passed to the constructor.
  */
  public String getName(){
    return fName;
  }
  
  // PRIVATE 
  
  private String fName;
  private List<String> fArgNames = new ArrayList<String>();
  private String fFormula;

}
