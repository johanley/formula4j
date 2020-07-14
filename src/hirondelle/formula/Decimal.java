package hirondelle.formula;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;

/**
 Represent an immutable number, using a natural, compact syntax. 
 The number may have a decimal portion, or it may not.

<P>This class exists for these reasons:
<ul>
 <li>to simplify calculations, and build on top of what's available from the {@link BigDecimal} class
 <li>to allow your code to read at a higher level
 <li>to define a more natural, pleasing syntax
 <li>to help you avoid floating-point types, 
 which have many <a href='http://www.ibm.com/developerworks/java/library/j-jtp0114/'>pitfalls</a> 
</ul>

<P>This class is especially suited for representing money. 
For the most common operations, Decimal uses {@link BigDecimal} to perform calculations, and is thus not subject to the 
rounding errors which are very common when floating-point primitives are used. 

<P><tt>Decimal</tt> objects are immutable.  
Many operations in this class return new <tt>Decimal</tt> objects. 
 
 <P>As usual, operations can be performed on two items having a different number of decimal places. 
 For example, these  operations are valid (using an informal, <em>ad hoc</em> notation) : 
 <PRE>10 + 1.23 = 11.23
10.00 + 1.23 = 11.23
10 - 1.23 = 8.77
(10 > 1.23) => true </PRE> 
 This corresponds to typical user expectations.
  
 <P>The {@link #eq(Decimal)} is usually to be preferred over the {@link #equals(Object)} method. 
 The {@link #equals(Object)} is unusual, in that it's the only method sensitive to the exact 
 number of decimal places, while {@link #eq(Decimal)} is not. That is,  
 <PRE>10.equals(10.00) => false
10.eq(10.00) => true</PRE>
   
 <P>
 Various methods in this class have unusually terse names, such as 
 <tt>lt</tt> for 'less than',  and <tt>gt</tt> for 'greater than', and so on. 
 The intent of such names is to improve the legibility of mathematical expressions.
  
 <P>Example : 
 <PRE> if ( amount.lt(hundred) ) {
     cost = amount.times(price); 
 }</PRE>
 
 <P>Several methods in this class are overloaded to perform the same operation with various types:
<ul>
 <li>Decimal
 <li>long (which will also accept an int)
 <li>double  (which will also accept a float)
</ul>
  Usually, you should prefer the Decimal form. The long and double forms are usually convenience methods, which simply call the Decimal 
  version as part of their implementations; they're intended for cases when you simply wish to specify a hard-coded constant value. 
 
 <P>This class extends {@link Number}. This allows other parts of the JDK to treat a <tt>Decimal</tt> just like any other 
 <tt>Number</tt>.
*/
public final class Decimal extends Number implements Comparable<Decimal>, Serializable {

  /**
   The default rounding mode used by this class (half-even). 
   This rounding style results in the least bias.  
  */ 
  public static final RoundingMode ROUNDING = RoundingMode.HALF_EVEN;
  
  /**
   Constructor.
   @param aAmount required, any number of decimals. 
  */
  public Decimal(BigDecimal aAmount){
    fAmount = aAmount;
    validateState();
  }
  
  /**
   Convenience factory method. Leading zeroes are allowed.
   <P>Instead of  : 
   <PRE>Decimal decimal = new Decimal(new BigDecimal("100"));</PRE>
   one may instead use this more compact form: 
   <PRE>Decimal decimal = Decimal.from("100");</PRE>
   which is a bit more legible. 
  */
  public static Decimal from(String aAmount){
    return new Decimal(new BigDecimal(aAmount));
  }
  
  /** Convenience factory method.  */ 
  public static Decimal from(long aAmount){
    return new Decimal(new BigDecimal(aAmount));
  }
  
  /** Convenience factory method.  */ 
  public static Decimal from(double aAmount){
    return new Decimal(BigDecimal.valueOf(aAmount));
  }

  /**
   Renders this <tt>Decimal</tt> in a style suitable for debugging. 
   <em>Intended for debugging only.</em>
   
   <P>Returns the amount in the format defined by {@link BigDecimal#toPlainString()}. 
 */
  public String toString(){
    return fAmount.toPlainString();
  }
 
  /**
   Equals, sensitive to scale of the underlying BigDecimal. 
  
   <P>That is, <tt>10</tt> and <tt>10.00</tt> are <em>not</em> 
   considered equal by this method. <b>Such behavior is often undesired; in most 
   practical cases, it's likely best to use the {@link #eq(Decimal)} method instead.</b>, 
   which has no such monkey business.
  
   <P>This implementation imitates {@link BigDecimal#equals(java.lang.Object)}, 
   which is also sensitive to the number of decimals (or 'scale'). 
  */
  public boolean equals(Object aThat){
    if (this == aThat) return true;
    if (! (aThat instanceof Decimal) ) return false;
    Decimal that = (Decimal)aThat;
    //the object fields are never null :
    boolean result = (this.fAmount.equals(that.fAmount) );
    return result;
  }
 
  public int hashCode(){
    if ( fHashCode == 0 ) {
      fHashCode = HASH_SEED;
      fHashCode = HASH_FACTOR * fHashCode + fAmount.hashCode(); 
    }
    return fHashCode;
  }
 
  /**
   Implements the {@link Comparable} interface. 
  
   <P>It's possible to use this method as a general replacement for a large number of methods which compare numbers: 
   lt, eq, lteq, and so on. However, it's recommended that you use those other methods, since they have greater clarity 
   and concision.
  */
  public int compareTo(Decimal aThat) {
    final int EQUAL = 0;
    if ( this == aThat ) return EQUAL;
    //the object field is never null 
    int comparison = this.fAmount.compareTo(aThat.fAmount);
    if ( comparison != EQUAL ) return comparison;
    return EQUAL;
  }
  
  /** Return the amount as a BigDecimal. */
  public BigDecimal getAmount() { return fAmount; }
  
  /** The suffix is needed to distinguish from the public field.  Declared 'early' since compiler complains.*/
  private static final BigDecimal ZERO_BD = BigDecimal.ZERO;
  private static final BigDecimal ONE_BD = BigDecimal.ONE;
  private static final BigDecimal MINUS_ONE_BD = new BigDecimal("-1");

  /** 
   Zero <tt>Decimal</tt> amount, a simple convenience constant.
   
   <P>Like {@link BigDecimal#ZERO}, this item has no explicit decimal. 
   In most cases that will not matter, since only the {@link #equals(Object)} method is sensitive to 
   exact decimals. All other methods, including {@link #eq(Decimal)}, are not sensitive to exact decimals.
  */
  public static final Decimal ZERO = new Decimal(ZERO_BD);
  
  /** Convenience constant. */
  public static final Decimal ONE = new Decimal(ONE_BD);
  
  /** Convenience constant. */
  public static final Decimal MINUS_ONE = new Decimal(MINUS_ONE_BD);
  
  /**
    An approximation to the number pi, to 15 decimal places.
    Pi is the ratio of the circumference of a circle to its radius.
    It's also rumoured to <a href='http://en.wikipedia.org/wiki/Pi_Day'>taste good</a> as well.
   */
  public static final Decimal PI = new Decimal(new BigDecimal("3.141592653589793"));
  
  /** 
   An approximation to Euler's number, to 15 decimal places.
   Euler's number is the base of the natural logarithms. 
  */
  public static final Decimal E = new Decimal(new BigDecimal( "2.718281828459045"));
  
  /**
   Return the number of decimals in this value. More accurately, this returns the 'scale' of the 
   underlying BigDecimal. Negative scales are possible; they represent the number of zeros 
   added on to the end of an integer. 
  */
  public int getNumDecimals(){
    return fAmount.scale();
  }

  /**
   Return <tt>true</tt> only if this Decimal is an integer.
   For example, 2 and 2.00 are integers, but 2.01 is not.
  */
  public boolean isInteger(){
    return round().minus(this).eq(ZERO);
  }
  
  /** Return <tt>true</tt> only if the amount is positive. */
  public boolean isPlus(){
    return fAmount.compareTo(ZERO_BD) > 0;
  }
  
  /** Return <tt>true</tt> only if the amount is negative. */
  public boolean isMinus(){
    return fAmount.compareTo(ZERO_BD) <  0;
  }
  
  /** Return <tt>true</tt> only if the amount is zero. */
  public boolean isZero(){
    return fAmount.compareTo(ZERO_BD) ==  0;
  }
  
  /** 
   Equals (insensitive to number of decimals).
   That is, <tt>10</tt> and <tt>10.00</tt> are considered equal by this method.
   
   <P>Return <tt>true</tt> only if the amounts are equal.
   This method is <em>not</em> synonymous with the <tt>equals</tt> method, 
   since the {@link #equals(Object)} method is sensitive to the exact number of decimal places (or, more 
   precisely, the scale of the underlying BigDecimal.)
  */
  public boolean eq(Decimal aThat) {
    return compareAmount(aThat) == 0;
  }
  public boolean eq(long aThat) {
    return eq(Decimal.from(aThat));
  }
  public boolean eq(double aThat) {
    return eq(Decimal.from(aThat));
  }

  /** 
   Greater than.
   <P>Return <tt>true</tt> only if  'this' amount is greater than
   'that' amount. 
  */
  public boolean gt(Decimal aThat) { 
    return compareAmount(aThat) > 0;  
  }
  public boolean gt(long aThat) { 
    return gt(Decimal.from(aThat));  
  }
  public boolean gt(double aThat) { 
    return gt(Decimal.from(aThat));  
  }
  
  /** 
   Greater than or equal to.
   <P>Return <tt>true</tt> only if 'this' amount is 
   greater than or equal to 'that' amount. 
  */
  public boolean gteq(Decimal aThat) { 
    return compareAmount(aThat) >= 0;  
  }
  public boolean gteq(long aThat) { 
    return gteq(Decimal.from(aThat));  
  }
  public boolean gteq(double aThat) { 
    return gteq(Decimal.from(aThat));  
  }
  
  /** 
   Less than.
   <P>Return <tt>true</tt> only if 'this' amount is less than
   'that' amount. 
  */
  public boolean lt(Decimal aThat) { 
    return compareAmount(aThat) < 0;  
  }
  public boolean lt(long aThat) { 
    return lt(Decimal.from(aThat));  
  }
  public boolean lt(double aThat) { 
    return lt(Decimal.from(aThat));  
  }
  
  /** 
   Less than or equal to.
   <P>Return <tt>true</tt> only if 'this' amount is less than or equal to
   'that' amount.  
  */
  public boolean lteq(Decimal aThat) { 
    return compareAmount(aThat) <= 0;  
  }
  public boolean lteq(long aThat) { 
    return lteq(Decimal.from(aThat));  
  }
  public boolean lteq(double aThat) { 
    return lteq(Decimal.from(aThat));  
  }
  
  /** 
   Add <tt>aThat</tt> <tt>Decimal</tt> to this <tt>Decimal</tt>.
  */
  public Decimal plus(Decimal aThat){
    return new Decimal(fAmount.add(aThat.fAmount));
  }
  public Decimal plus(long aThat){
    return plus(Decimal.from(aThat));
  }
  public Decimal plus(double aThat){
    return plus(Decimal.from(aThat));
  }

  /** 
   Subtract <tt>aThat</tt> <tt>Decimal</tt> from this <tt>Decimal</tt>. 
  */
  public Decimal minus(Decimal aThat){
    return new Decimal(fAmount.subtract(aThat.fAmount));
  }
  public Decimal minus(long aThat){
    return minus(Decimal.from(aThat));
  }
  public Decimal minus(double aThat){
    return minus(Decimal.from(aThat));
  }

  /**
   Sum a collection of <tt>Decimal</tt> objects.
   
   @param aDecimals collection of <tt>Decimal</tt> objects.
   If the collection is empty, then a zero value is returned.
  */
  public static Decimal sum(Collection<Decimal> aDecimals){
    Decimal sum = new Decimal(ZERO_BD);
    for(Decimal decimal : aDecimals){
      sum = sum.plus(decimal);
    }
    return sum;
  }

  /**  Multiply this <tt>Decimal</tt> by a factor.  */
  public Decimal times(Decimal aFactor){
    BigDecimal newAmount = fAmount.multiply(aFactor.getAmount());
    return  new Decimal(newAmount);
  }
  public Decimal times(long aFactor){  
    return times(Decimal.from(aFactor));
  }
  public Decimal times(double aFactor){
    return times(Decimal.from(aFactor));
  }

  /**  
   Divide this <tt>Decimal</tt> by a divisor.
   <p>If the division results in a number which will never terminate, then this method 
   will round the result to 20 decimal places, using the default {@link #ROUNDING}.  
  */
  public Decimal div(Decimal aDivisor){
    BigDecimal newAmount = null;
    try {
      newAmount = fAmount.divide(aDivisor.fAmount);
    }
    catch(ArithmeticException  ex){
      // non-terminating decimal
      // need to apply a policy for where and how to round
      newAmount = fAmount.divide(aDivisor.fAmount, DECIMALS, ROUNDING);
    }
    return new Decimal(newAmount);
  }
  public Decimal div(long aDivisor){
    return div(Decimal.from(aDivisor));
  }
  public Decimal div(double aDivisor){  
    return div(Decimal.from(aDivisor));
  }

  /** Return the absolute value of the amount. */
  public Decimal abs(){
    return isPlus() ? this : times(-1);
  }
  
  /** Return this amount x (-1). */
  public Decimal negate(){ 
    return times(-1); 
  }
  
  /**  Round to an integer value, using the default {@link #ROUNDING} style.  */
  public Decimal round(){
    BigDecimal amount = fAmount.setScale(0, ROUNDING);
    return new Decimal(amount);
  }
  
  /** 
   Round to 0 or more decimal places, using the default {@link #ROUNDING} style.
   @param aNumberOfDecimals must 0 or more.  
  */
  public Decimal round(int aNumberOfDecimals){
    if( aNumberOfDecimals < 0 ){
      throw new IllegalArgumentException("Number of decimals is negative: " + quote(aNumberOfDecimals));
    }
    BigDecimal amount = fAmount.setScale(aNumberOfDecimals, ROUNDING);
    return new Decimal(amount);
  }
  
  /**  
   Round to 0 or more decimal places, using the given rounding style. 
   @param aNumberOfDecimals must 0 or more.  
  */
  public Decimal round(int aNumberOfDecimals, RoundingMode aRoundingMode){
    if( aNumberOfDecimals < 0 ){
      throw new IllegalArgumentException("Number of decimals is negative: " + quote(aNumberOfDecimals));
    }
    BigDecimal amount = fAmount.setScale(aNumberOfDecimals, aRoundingMode);
    return new Decimal(amount);
  }

  /**
   Round a number to the nearest multiple of the given interval.
   For example:
   <tt>
     Decimal amount = Decimal.from("1710.12");
     amount.round2(0.05); // 1710.10, to the nearest 5 cents
     amount.round2(100);  // 1700
   </tt>
   @param aInterval must be greater than zero
  */
  public Decimal round2(Decimal aInterval){
    if( ! aInterval.isPlus() ){
      throw new IllegalArgumentException("Interval is negative or zero : " + quote(aInterval));
    }
    BigDecimal result = fAmount.divide(aInterval.fAmount).setScale(0, ROUNDING).multiply(aInterval.fAmount);
    return new Decimal(result);
  }
  public Decimal round2(long aInterval){
    return round2(Decimal.from(aInterval));    
  }
  public Decimal round2(double aInterval){
    return round2(Decimal.from(aInterval));    
  }

  /**
   Raise this number to an <b>integral</b> power; the power can be of either sign.
   
   <P>Special cases regarding 0:
   <ul>
     <li> <tt>0^-n</tt> is undefined (<tt>n > 0</tt>).
     <li> <tt>x^0<tt/> always returns 1, even for <tt>x = 0</tt>.
   </ul>
   
   @param aPower is in the range -999,999,999..999,999,999, inclusive. (This reflects a restriction on 
   the underlying {@link BigDecimal#pow(int)} method.
  */
  public Decimal pow(int aPower){
    BigDecimal newAmount = null;
    if (aPower == 0){
      newAmount = ONE_BD;
    }
    else if (aPower == 1){
      newAmount = fAmount;
    }
    else if (aPower > 0){
      newAmount = fAmount.pow(aPower);
    }
    else if (aPower < 0 && this.eq(ZERO)){
      throw new RuntimeException("Raising 0 to a negative power is undefined.");
    }
    else if (aPower < 0){
      newAmount = fAmount.pow(-1 * aPower);
      newAmount = ONE_BD.divide(newAmount);
    }
    return new Decimal(newAmount);  
  }

  /** This implementation uses {@link Math#pow(double, double)}.   */
  public Decimal pow(double aPower){
    double value = Math.pow(fAmount.doubleValue(), aPower);
    return Decimal.from(value);
  }
  
  /**
   Raise this Decimal to a Decimal power.
   <P>This method calls either {@link #pow(int)} or {@link #pow(double)}, according to the return value of 
   {@link #isInteger()}.
  */
  public Decimal pow(Decimal aPower){
    Decimal result = ZERO;
    if (aPower.isInteger()){
      result = pow(aPower.intValue());
    }
    else {
      result = pow(aPower.doubleValue());
    }
    return result;
  }
  
  /** 
   Required by {@link Number}.
      
   <P><em>Use of floating point data is highly discouraged.</em> 
   This method is provided only because it's required by <tt>Number</tt>. 
  */
  @Override public double doubleValue() {
    return fAmount.doubleValue();
  }
  
  /** 
   Required by {@link Number}.
      
   <P><em>Use of floating point data is highly discouraged.</em> 
   This method is provided only because it's required by <tt>Number</tt>. 
  */
  @Override public float floatValue() {
    return fAmount.floatValue();
  }

  /** Required by {@link Number}. */
  @Override  public int intValue() {
    return fAmount.intValue();
  }
  
  /** Required by {@link Number}. */
  @Override public long longValue() {
    return fAmount.longValue();
  }
  
  // PRIVATE 
  
  /** 
   The decimal amount. 
   Never null. 
   @serial 
  */
  private BigDecimal fAmount;
  
  /** Number of decimals to use when a division operation blows up into a non-terminating decimal.  */
  private static final int DECIMALS = 20;
  
  /** @serial */
  private int fHashCode;
  private static final int HASH_SEED = 23;
  private static final int HASH_FACTOR = 37;

  /**
   Determines if a deserialized file is compatible with this class.
  
   Maintainers must change this value if and only if the new version
   of this class is not compatible with old versions. See Sun docs
   for <a href=http://java.sun.com/products/jdk/1.1/docs/guide
   /serialization/spec/version.doc.html> details. </a>
  
   Not necessary to include in first version of the class, but
   included here as a reminder of its importance.
  */
  private static final long serialVersionUID = 7526471155622776147L;

  /**
   Always treat de-serialization as a full-blown constructor, by
   validating the final state of the de-serialized object.
  */  
  private void readObject(
    ObjectInputStream aInputStream
  ) throws ClassNotFoundException, IOException {
    //always perform the default de-serialization first
    aInputStream.defaultReadObject();
    //defensive copy for mutable date field
    //BigDecimal is not technically immutable, since its non-final
    fAmount = new BigDecimal( fAmount.toPlainString() );
    //ensure that object state has not been corrupted or tampered with maliciously
    validateState();
  }

  private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
    //perform the default serialization for all non-transient, non-static fields
    aOutputStream.defaultWriteObject();
  }  

  private void validateState(){
    if( fAmount == null ) {
      throw new IllegalArgumentException("Amount cannot be null");
    }
  }
  
  /** Ignores scale: 0 same as 0.00 */
  private int compareAmount(Decimal aThat){
    return this.fAmount.compareTo(aThat.fAmount);
  }
  
  private static String quote(Object aText){
    return "'" + String.valueOf(aText) + "'";
  }
}
