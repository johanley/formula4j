package hirondelle.formula.function.builtin;


import hirondelle.formula.function.Function;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 This class is public, but not published!
*/
public final class BuiltInFunctions {
  
  public static Map<String, Function> getAll(){
    Map<String, Function> result = new LinkedHashMap<String, Function>();
    result.put("abs", new Abs());
    result.put("arccos", new Arccosine()); 
    result.put("arcsin", new Arcsine()); 
    result.put("arctan", new Arctangent()); 
    result.put("arctan2", new Arctangent2()); 
    result.put("avg", new ArithmeticMean());
    result.put("bearing", new Bearing());    
    result.put("ceil", new Ceil());
    result.put("combination", new Combination()); 
    result.put("cos", new Cosine()); 
    result.put("cbrt", new CubeRoot());
    result.put("dayfromjd", new DayFromJD());   
    result.put("daysbetween", new DaysBetween());   
    result.put("daysminus", new DaysMinus());   
    result.put("daysplus", new DaysPlus());   
    result.put("dayssince", new DaysSince());   
    result.put("daysuntil", new DaysUntil());   
    result.put("deg", new Degrees()); 
    result.put("distance", new Distance());    
    result.put("exp", new Exp());
    result.put("factorial", new Factorial());
    result.put("floor", new Floor());
    result.put("cosh", new HyperbolicCosine()); 
    result.put("sinh", new HyperbolicSine()); 
    result.put("tanh", new HyperbolicTangent());  
    result.put("invest", new Investment());  
    result.put("jd", new JulianDate());  
    result.put("pmt", new LoanPayment());  
    result.put("log", new LogBase10());
    result.put("max", new Max());
    result.put("median", new Median());
    result.put("min", new Min());
    result.put("mjd", new ModifiedJulianDate());   
    result.put("ln", new NaturalLog());
    result.put("permutation", new Permutation());
    result.put("pstdev", new PopulationStandardDeviation());
    result.put("pow", new Power());
    result.put("rad", new Radians());
    result.put("randg", new RandomGaussian());
    result.put("rand", new RandomNumber());
    result.put("round", new Round());
    result.put("sstdev", new SampleStandardDeviation());
    result.put("sign", new Sign());
    result.put("sin", new Sine()); 
    result.put("sqrt", new SquareRoot());
    result.put("sum", new Sum());
    result.put("tan", new Tangent()); 
    result.put("weekday", new Weekday());   
    return result;
  }
}
