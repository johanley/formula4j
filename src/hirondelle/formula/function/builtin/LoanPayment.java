package hirondelle.formula.function.builtin;

import hirondelle.formula.Decimal;
import hirondelle.formula.function.Check;
import hirondelle.formula.function.Function;

/** pmt(principal,interest,months): monthly payment needed to pay back a loan or mortgage. */
public final class LoanPayment implements Function {
  
  /**
   Monthly payment. No rounding of the result is performed.
   
   <P>This method takes 3 parameters, in this order:
   <ul>
    <li>principal, &gt; 0
    <li>annual interest rate, &gt; 0 
    <li>number of monthly payments, integer &gt; 0
   </ul>
   
   <P>Example: this loan of 100 has an annual interest rate of 5%, and is 
   paid back over 2 years (24 months):
   <ul>
    <li>principal = 100
    <li>interest rate = 0.05 (that is, 5%)
    <li>number of monthly payments = 24
   </ul>
    <P>Then
<pre>pmt(100, 0.05, 24) = 4.38713897340684378120</pre>
  */
  public Decimal calculate(Decimal... aArgs) {
    Check.numArgs(3, aArgs); // principal (>0), annual interest rate (>0), num payments (int, > 0)
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
