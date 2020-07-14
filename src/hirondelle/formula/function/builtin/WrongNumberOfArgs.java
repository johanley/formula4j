package hirondelle.formula.function.builtin;

public final class WrongNumberOfArgs extends RuntimeException {
  
  public WrongNumberOfArgs(int aNumExpected, int aNumActual){
    super("Expecting " + aNumExpected + " arguments, but passed " + aNumActual);
  }

  public WrongNumberOfArgs(String aMsg){
    super(aMsg);
  }


  private static final long serialVersionUID = -5330690286500915264L;

}
