package hirondelle.formula;

/** 
 A formula doesn't have the proper syntax.
 For example, an unmatched parenthesis would result in this exception. 
*/
public final class MalformedFormulaException extends Exception {

  MalformedFormulaException(String aFormula, String aMsg){
    super("Formula is malformed: '" + aFormula + "'. " + aMsg);
  }
  
  private static final long serialVersionUID = 4450503915190268400L;

}
