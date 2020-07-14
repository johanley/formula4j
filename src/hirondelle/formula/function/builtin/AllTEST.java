package hirondelle.formula.function.builtin;

import junit.framework.Test;
import junit.framework.TestSuite;

/** Run all JUnit tests for functions. */
public final class AllTEST {

  public static void main(String args[]) {
    System.out.println("Running tests for functions...");
    String[] testCaseName = { AllTEST.class.getName()};
    junit.textui.TestRunner.main(testCaseName);
 }
  
  public static Test suite ( ) {
    TestSuite suite= new TestSuite("All tests for functions.");

    suite.addTest(new TestSuite(AbsTEST.class));
    suite.addTest(new TestSuite(ArccosineTEST.class));
    suite.addTest(new TestSuite(ArcsineTEST.class));
    suite.addTest(new TestSuite(ArctangentTEST.class));
    suite.addTest(new TestSuite(Arctangent2TEST.class));
    suite.addTest(new TestSuite(ArithmeticMeanTEST.class));
    suite.addTest(new TestSuite(BearingTEST.class));
    suite.addTest(new TestSuite(CeilTEST.class));
    suite.addTest(new TestSuite(CombinationTEST.class)); 
    suite.addTest(new TestSuite(CosineTEST.class)); 
    suite.addTest(new TestSuite(CubeRootTEST.class));
    suite.addTest(new TestSuite(DayFromJDTEST.class));
    suite.addTest(new TestSuite(DaysBetweenTEST.class));
    suite.addTest(new TestSuite(DaysMinusTEST.class));
    suite.addTest(new TestSuite(DaysPlusTEST.class));
    suite.addTest(new TestSuite(DaysSinceTEST.class)); //'since' and 'until' are hard-coded to a certain current date.
    suite.addTest(new TestSuite(DaysUntilTEST.class)); 
    suite.addTest(new TestSuite(DegreesTEST.class));
    suite.addTest(new TestSuite(DistanceTEST.class));
    suite.addTest(new TestSuite(ExpTEST.class));
    suite.addTest(new TestSuite(FactorialTEST.class));
    suite.addTest(new TestSuite(FloorTEST.class)); 
    suite.addTest(new TestSuite(HyperbolicCosineTEST.class)); 
    suite.addTest(new TestSuite(HyperbolicSineTEST.class)); 
    suite.addTest(new TestSuite(HyperbolicTangentTEST.class)); 
    suite.addTest(new TestSuite(InvestmentTEST.class)); 
    suite.addTest(new TestSuite(JulianDateTEST.class));
    suite.addTest(new TestSuite(LoanPaymentTEST.class));
    suite.addTest(new TestSuite(LogBase10TEST.class)); 
    suite.addTest(new TestSuite(MaxTEST.class)); 
    suite.addTest(new TestSuite(MedianTEST.class)); 
    suite.addTest(new TestSuite(MinTEST.class)); 
    suite.addTest(new TestSuite(ModifiedJulianDateTEST.class)); 
    suite.addTest(new TestSuite(NaturalLogTEST.class)); 
    suite.addTest(new TestSuite(PermutationTEST.class));  
    suite.addTest(new TestSuite(PopulationStandardDeviationTEST.class));
    suite.addTest(new TestSuite(PowerTEST.class));
    suite.addTest(new TestSuite(RadiansTEST.class));  
    // RandomGaussian has no tests
    suite.addTest(new TestSuite(RandomNumberTEST.class));  
    suite.addTest(new TestSuite(RoundTEST.class));   
    suite.addTest(new TestSuite(SampleStandardDeviationTEST.class));   
    suite.addTest(new TestSuite(SignTEST.class));   
    suite.addTest(new TestSuite(SineTEST.class));   
    suite.addTest(new TestSuite(SquareRootTEST.class));   
    suite.addTest(new TestSuite(SumTEST.class));   
    suite.addTest(new TestSuite(TangentTEST.class));    
    suite.addTest(new TestSuite(WeekdayTEST.class));    
    
    // add more here
    
    return suite;
  }
}
