package hirondelle.formula.demo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

/** 
 Simple harness for testing formulas.

 <P>To launch this demo, navigate to a directory containing formula4j.jar, and enter:
 <pre>
 &gt;java -cp formula4j.jar hirondelle.formula.demo.MainGui
 </pre>

 <P>This demo app acts much like a simple console, with input and output.  
 The basic commands are:
   <ul>
    <li>the enter key processes the current input line
    <li>the escape key erases the current input line 
    <li>the up and down arrow keys run through the command history
   </ul>
   
 <P>Usually, the input is simply an expression to be evaluated.
  
 <P>This tool also defines the following special commands, all of which start with a leading ':' character : 
<pre>
:define &lt;function-definition&gt;
Define a custom function on the fly.
Example:
  :define bmi(m,h) = m/h^2

:set &lt;variable-value&gt;
Defines a variable as a name-value pair.
Defined variables can be referenced in formulas.
Setting a variable a second time simply overwrites the value.
Example:
  :set a = 321.2

:unset [&lt;variable-name&gt;]
Remove a variable value. 
If the variable name is omitted, then all variables are removed.
  
:stats
Show timing statistics.

:load &lt;file-name&gt;
Load a file defining custom functions.
The file name should be absolute, not relative.
The file should be encoded in UTF-8.
The file must contain content that looks like this:

bmi(m,h) = m/h^2
area(r) = 2 * π * r^2
circum   my.package.Circumference

There are two kinds of entries in these files:
  - explicit functions (bmi and area)
  - mapping of function names to implementation classes (circum)

:unload
Revert to the default set of functions, with 
no customizations. Undoes both the :load and 
the :define command.
  
:cls
Clear the output area by removing all of its text.

:help
Show this help information.
</pre>
*/
public final class MainGui {
  
  /** Run this tool (no parameters are required). */
  public static void main(String... aArgs){
    MainGui app = new MainGui();
    app.buildAndDisplayGui();
  }

  /** 
   Build and display a simple GUI for entering formulas, and seeing the result of their evaluation.
  */
  void buildAndDisplayGui(){
    useNativeLookAndFeel();
    setApplicationFont();
    buildContent(fFrame);
    fFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    centerAndShow(fFrame);
  }
  
  /* Basic look used by the app: Verdana, amber on black. Alter to taste. */
  static final Font FONT = new Font("Verdana", Font.BOLD,12 );
  static final Color TEXT_COLOR = new Color(185,128,0); //amber 
  static final Color BACKGROUND = Color.BLACK;
  
  // PRIVATE 

  private JFrame fFrame = new JFrame("Formula Console");
  
  private void useNativeLookAndFeel() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Throwable ex){
      throw new RuntimeException("Cannot set the look and feel.", ex);
    }
  }
  
  private void setApplicationFont() {
    FontUIResource fontResource = new FontUIResource(FONT);
    @SuppressWarnings("rawtypes")
    Enumeration keys = UIManager.getDefaults().keys();
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get (key);
      if (value instanceof FontUIResource) {
        UIManager.put (key, fontResource);
      }
    }
  }
  
  private void buildContent(JFrame aFrame){
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    
    JLabel label = new JLabel("Enter a formula :");
    align(label);
    panel.add(label);
    
    JTextField input = new JTextField();
    JTextPane output = new JTextPane();
    
    input.addKeyListener(new CommandProcessor(input, output));
    input.setPreferredSize(new Dimension(500, 30));
    input.setMaximumSize(new Dimension(500, 30));
    align(input);
    setColors(input);
    input.setCaretColor(TEXT_COLOR);
    panel.add(input);
    
    label = new JLabel("Output :");
    align(label);
    panel.add(label);
    align(output);
    output.setPreferredSize(new Dimension(100, 400));
    setColors(output);
    output.setEditable(false);
    JScrollPane outputScrollPane = new JScrollPane(output);
    align(outputScrollPane);
    panel.add(outputScrollPane);
    
    aFrame.getContentPane().add(panel);      
  }
  
  private void align(JComponent aComp){
    aComp.setAlignmentX(Component.LEFT_ALIGNMENT);
  }
  
  private void setColors(Component aComp){
    aComp.setBackground(BACKGROUND);
    aComp.setForeground(TEXT_COLOR);
    aComp.setFont(FONT);
  }

  private void centerAndShow(Window aWindow){
    //note that the order here is important
    aWindow.pack();
    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension window = aWindow.getSize();
    //ensure that no parts of aWindow will be off-screen
    if (window.height > screen.height) {
      window.height = screen.height;
    }
    if (window.width > screen.width) {
      window.width = screen.width;
    }
    int xCoord = (screen.width/2 - window.width/2);
    int yCoord = (screen.height/2 - window.height/2);
    aWindow.setLocation( xCoord, yCoord );
    aWindow.setVisible(true);
  }
}
