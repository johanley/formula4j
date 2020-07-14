package hirondelle.formula.function;

import hirondelle.formula.Decimal;

/** 
 The fundamental interface implemented by all functions. 
 
  <P>All functions present in your formulas, either built-in or custom, must be represented by an 
 implementation of this interface.
 
 <P>Implementations must:
 <ul>
  <li>be declared as public
  <li>have a no-argument constructor
 </ul>
 
 <P>Most functions are not hard to implement. It's highly recommended that you 
 implement your custom functions as 
 <a href='http://www.javapractices.com/topic/TopicAction.do?Id=29'>immutable</a> objects.

<P>Here are some examples.
 Note that these classes have no fields, and are declared as final. 
 Therefore, these classes are immutable.

 <P>Example 1.
<pre>
public final class Abs implements Function {
  public Decimal calculate(Decimal... aArgs) {
    Check.oneArg(aArgs); //ensure there's a single arg
    return aArgs[0].abs(); //return its absolute value
  }
}
</pre>

<P>Example 2.
<pre>
public final class LoanPayment implements Function {
  public Decimal calculate(Decimal... aArgs) {
    Check.numArgs(3, aArgs); 
    // principal (>0), annual interest rate (>0), num payments (int, > 0)
    Decimal principal = aArgs[0];
    Decimal interest = aArgs[1];
    Decimal numPayments = aArgs[2];
    
    Check.positive(principal, "Principal");
    Check.positive(interest, "Annual interest rate");
    Check.positiveInteger(numPayments, "Number of monthly payments");
    
    Decimal monthlyInterest = interest.div(12);
    Decimal a = Decimal.ONE.plus(monthlyInterest).pow(numPayments.intValue());
    Decimal result = a.times(principal).times(monthlyInterest);
    result = result.div(a.minus(1));
    return result;
  }
}
</pre>
*/
public interface Function {

  /**
    Calculate the value of a function.
    
    <P>It's highly recommended that the implementation validate the number of arguments 
    passed to the function. The {@link Check} class can be used for doing the most common 
    argument validations. 
    
    @param aArgs 0 or more arguments to the function. 
  */
  Decimal calculate(Decimal... aArgs);
}
