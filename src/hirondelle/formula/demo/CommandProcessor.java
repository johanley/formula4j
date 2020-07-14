package hirondelle.formula.demo;

import hirondelle.formula.Decimal;
import hirondelle.formula.Formula;
import hirondelle.formula.MalformedFormulaException;
import hirondelle.formula.UnknownFunctionException;
import hirondelle.formula.UnpopulatedVariableException;
import hirondelle.formula.function.Function;
import hirondelle.formula.function.FunctionDefinedInText;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JTextField;
import javax.swing.JTextPane;

/** Process each line of user input.*/
final class CommandProcessor extends KeyAdapter {
  
  /** Constructor. */
  CommandProcessor(JTextField aInput, JTextPane aOutput) {
    fInput = aInput;
    fOutput = aOutput;
    COMMANDS.add(new ClearScreen());
    COMMANDS.add(new Stats());
    COMMANDS.add(new Define());
    COMMANDS.add(new Load());
    COMMANDS.add(new Unload());
    COMMANDS.add(new SetVariable());
    COMMANDS.add(new UnsetVariable());
    COMMANDS.add(new Help());
  }
  
  /**
   React to a key being pressed in the input area.
   Process each command, and update the output and input.
  */
  public void keyPressed(KeyEvent aKeyEvent) {
    if (aKeyEvent.getKeyCode() == KeyEvent.VK_ESCAPE){
      fInput.setText("");
      fCommandsCursor = -1;
    }
    else if ( aKeyEvent.getKeyCode() == KeyEvent.VK_UP){
      if (!fCommands.isEmpty()){
        if (fCommandsCursor == -1){
          fCommandsCursor = fCommands.size() - 1;
        }
        else {
          fCommandsCursor = fCommandsCursor > 0 ? fCommandsCursor - 1 : fCommandsCursor;
        }
        fInput.setText(fCommands.get(fCommandsCursor));
      }
    }
    else if ( aKeyEvent.getKeyCode() == KeyEvent.VK_DOWN){
      if (!fCommands.isEmpty()){
        if (fCommandsCursor == -1){
          fCommandsCursor = fCommands.size() - 1;
        }
        else {
          fCommandsCursor = fCommandsCursor < (fCommands.size() - 1) ? fCommandsCursor + 1 : fCommandsCursor;
          fInput.setText(fCommands.get(fCommandsCursor));
        }
      }
    }
    else if ( aKeyEvent.getKeyCode() == KeyEvent.VK_ENTER){
      if (fInput.getText() != null && fInput.getText().trim().length() > 0){
        fCommands.add(fInput.getText());
        fCommandsCursor = -1;
        Command specialCommand = specialCommand();
        if (specialCommand == null){
          ++fLineCount;
          fOutput.setText(fOutput.getText() + fLineCount + " : formula>"+ fInput.getText() + NL);
          ++fLineCount;
          EvalResult result = evaluate(fInput.getText());
          fOutput.setText(fOutput.getText() + fLineCount + " : result>" + result.TEXT + NL);
          ++fLineCount;
          fOutput.setText(fOutput.getText() + fLineCount + " : timing>" + result.TIMING + "s" + NL + NL);
          fInput.setText("");
        }
        else {
          process(specialCommand);
        }
      }
    }
  }

  // PRIVATE 
 
  private JTextField fInput;
  private JTextPane fOutput;
  
  private List<String> fCommands = new ArrayList<String>();
  private int fCommandsCursor = -1;  //usually -1
  private List<Decimal> fTimings = new ArrayList<Decimal>();
  private int fLineCount = 0;
  private final List<Command> COMMANDS = new ArrayList<Command>();
  private final Map<String, Decimal> fValues = new LinkedHashMap<String, Decimal>();
  private final Map<String, Function> fFunctions = Formula.getDefaultFunctions(); //default, initial state

  private final String NL = System.getProperty("line.separator");
  private final double BILLION = 1000000000.0;
  private final Decimal BILLION_DECIMAL = Decimal.from("1000000000");

  private EvalResult evaluate(String inputFormula){
    EvalResult result = new EvalResult();
    Formula formula = new Formula(inputFormula, fValues, fFunctions);
    try {
      double start = System.nanoTime();
      result.TEXT = formula.getAnswer().toString();
      double finish = System.nanoTime();
      Double timing = finish - start;
      result.TIMING = timing / BILLION;
      Decimal decimalTiming = new Decimal (new BigDecimal(timing.toString()));
      fTimings.add(decimalTiming);
    }
    catch (MalformedFormulaException ex){
      result.TEXT = "Malformed: " + ex;
    }
    catch(UnpopulatedVariableException ex){
      result.TEXT = "Not all variables have been populated: " + ex;
    }
    catch(UnknownFunctionException ex){
      result.TEXT = "Not all functions have been defined: " + ex;
    }
    return result;
  }

  private static final class EvalResult {
    Double TIMING = 0.0;
    String TEXT = "";
  }
  
  private abstract class Command {
    Command(String aName){
      NAME = aName;
    }
    abstract void execute(String aBody);
    String NAME;
  }
  
  private class ClearScreen extends Command {
    ClearScreen(){ super(":cls");  }
    void execute(String aBody){
      fOutput.setText("");
      fLineCount = 1;
    }
  }
  
  private class Stats extends Command {
    Stats(){  super(":stats");  }
    void execute(String aBody){
      Decimal min = calc("min");
      Decimal max = calc("max");
      Decimal median = calc("median");
      ++fLineCount;
      StringBuilder stats = new StringBuilder();
      stats.append(fLineCount + " : response time stats>" + NL);
      stats.append("  Min: " + min + "s" + NL);
      stats.append("  Max: " + max + "s" + NL);
      stats.append("  Median: " + median +"s" +  NL);
      if (fTimings.size() > 1){
        Decimal stddev = calc("pstdev"); 
        stats.append("  Standard Dev: " + stddev +"s" +  NL);
      }
      fOutput.setText(fOutput.getText() + stats);
    }
    private Decimal calc(String aName){
      Function function = Formula.getDefaultFunctions().get(aName);
      return function.calculate(fTimings.toArray(new Decimal[0])).div(BILLION_DECIMAL);
    }
  }

  private class Define extends Command {
    Define(){ super(":define");  }
    void execute(String aBody){
      FunctionDefinedInText function = new FunctionDefinedInText(aBody);
      fFunctions.put(function.getName(), function);
      ++fLineCount;
      fOutput.setText(fOutput.getText() + fLineCount + " : function added: '" + aBody + "'" +  NL + NL);
    }
  }

  private class Load extends Command {
    Load(){ super(":load");  }
    void execute(String aBody){
      ++fLineCount;
      try {
        fFunctions.clear();
        fFunctions.putAll(Formula.getDefaultFunctions());
        processLineByLine(aBody);
        fOutput.setText(fOutput.getText() + fLineCount + " : function(s) (re)loaded from file : " + NL + "'" + aBody + "'" +  NL + NL);
        //show the new ones?
      }
      catch (FileNotFoundException ex){
        fOutput.setText(fOutput.getText() + fLineCount + " : FAILED to load function(s) from file : '" + aBody + "'" +  NL + NL);
      }
    }
    private void processLineByLine(String aFileName) throws FileNotFoundException {
      Scanner scanner = new Scanner(new FileReader(aFileName)); // the default encoding
      try {
        while ( scanner.hasNextLine() ){
          processLine( scanner.nextLine() );
        }
      }
      finally {
        scanner.close();
      }
    }
    private void processLine(String aLine){
      //this could use some improvement in handling wacky data
      if (aLine.contains("=")){
        processFormulaInText(aLine);  
      }
      else {
        processClassName(aLine);
      }
    }
    private void processClassName(String aLine){
      //this could use some improvement in handling wacky data
      String[] parts = aLine.split(" ");
      String name = parts[0];
      String className = parts[1];
      try {
        @SuppressWarnings("rawtypes")
        Class functionClass = Class.forName(className);
        Function function = (Function)functionClass.newInstance();
        fFunctions.put(name, function);
      }
      catch (Throwable ex) {
        String msg = "Cannot find Function class, using '" + className + "' as the fully-qualified class name.";
        throw new RuntimeException(msg, ex);
      }
    }
    private void processFormulaInText(String aLine){
      FunctionDefinedInText function = new FunctionDefinedInText(aLine);
      fFunctions.put(function.getName(), function);
    }
  }

  private class Unload extends Command {
    Unload(){ super(":unload");  }
    void execute(String aBody){
      fFunctions.clear();
      fFunctions.putAll(Formula.getDefaultFunctions());
      fOutput.setText(fOutput.getText() + fLineCount + " : functions reset to defaults only. " + NL + NL);
    }
  }

  private class SetVariable extends Command {
    SetVariable(){ super(":set"); }
    void execute(String aBody){
      String[] parts = aBody.split("=");
      fValues.put(parts[0].trim(), Decimal.from(parts[1].trim()));
      ++fLineCount;
      fOutput.setText(fOutput.getText() + fLineCount + " : value added "  + aBody +  NL + NL);
    }
  }

  private class UnsetVariable extends Command {
    UnsetVariable(){ super(":unset");  }
    void execute(String aBody){
      if (aBody.length() > 0){
        fValues.remove(aBody.trim());
        ++fLineCount;
        fOutput.setText(fOutput.getText() + fLineCount + " : value of '" + aBody  + "' removed."  +  NL + NL);
      }
      else {
        fValues.clear();
        ++fLineCount;
        fOutput.setText(fOutput.getText() + fLineCount + " : all values removed."  +  NL + NL);
      }
    }
  }
  
  private class Help extends Command {
    Help(){ super(":help"); }
    void execute(String aBody){
      ++fLineCount;
      fOutput.setText(fOutput.getText() + fLineCount + " : help information "  + NL + NL + helpFile() +  NL + NL);
    }
    String helpFile(){
      StringBuilder text = new StringBuilder();
      Scanner scanner = null;
      try {
        scanner = new Scanner(getClass().getClassLoader().getResourceAsStream("hirondelle/formula/demo/help.txt"), "UTF-8");
        while (scanner.hasNextLine()){
          text.append(scanner.nextLine() + NL);
        }
      }
      finally{
        scanner.close();
      }
      return text.toString();
    }
  }
  
  private Command specialCommand(){
    Command result = null;
    for (Command command : COMMANDS){
      if (fInput.getText().trim().startsWith(command.NAME)){
        result = command;
      }
    }
    return result;
  }
  
  private void process(Command aCommand){
    String body = restOfCommand(aCommand.NAME);
    aCommand.execute(body);
    fInput.setText("");
  }

  private String restOfCommand(String aCommandName){
    return fInput.getText().substring(aCommandName.length()).trim();
  }
  
}
