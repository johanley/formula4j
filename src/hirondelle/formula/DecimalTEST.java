package hirondelle.formula;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public final class DecimalTEST extends TestCase{
  
  public static void main(String args[]) {
    junit.textui.TestRunner.run(DecimalTEST.class);
  }

  public void testCtor(){
    testCtorSuccess("0");
    testCtorSuccess("-0");
    testCtorSuccess("0.00");
    testCtorSuccess("4.432672E+7");
    
    testCtorSuccess("1" );
    testCtorSuccess("1.0");
    testCtorSuccess("1.00");
    testCtorSuccess("10.00");
    testCtorSuccess("-10.00");
    testCtorSuccess("123456.78");
    testCtorSuccess("9999.99");
    testCtorSuccess("10000000000000000000000.00");
    testCtorSuccess("-10000000000000000000000.00");

    testCtorSuccess("1.23");
    testCtorSuccess("123213");
    testCtorSuccess("10.0032131");
    testCtorSuccess("1654640.0");
    testCtorSuccess("3132.321313");
    
    testCtorSuccess("03132.321313"); // leading zero
    testCtorSuccess("01");
    testCtorSuccess("010");
    testCtorSuccess("01.0");
    testCtorSuccess("10.0");
    
    testCtorSuccess(0);
    testCtorSuccess(+0);
    testCtorSuccess(-0);
    testCtorSuccess(1);
    testCtorSuccess(+1);
    testCtorSuccess(-1);
    testCtorSuccess(123);
    testCtorSuccess(0123);
    testCtorSuccess(123456789);
    testCtorSuccess(-123456789);

    testCtorSuccess(0.0);
    testCtorSuccess(+0.0);
    testCtorSuccess(-0.0);
    testCtorSuccess(1.0);
    testCtorSuccess(+1.0);
    testCtorSuccess(-1.0);
    testCtorSuccess(0123.0);
    testCtorSuccess(123456789.0);
    testCtorSuccess(-123456789.0);

    testCtorBDSuccess("0");
    testCtorBDSuccess("-0");
    testCtorBDSuccess("0.00");
    testCtorBDSuccess("4.432672E+7");
    
    testCtorBDSuccess("1" );
    testCtorBDSuccess("1.0");
    testCtorBDSuccess("1.00");
    testCtorBDSuccess("010.00");
    testCtorBDSuccess("-10.00");
    testCtorBDSuccess("123456.78");
    testCtorBDSuccess("9999.99");
    testCtorBDSuccess("10000000000000000000000.00");
    testCtorBDSuccess("-10000000000000000000000.00");

    testCtorBDSuccess("1.23");
    testCtorBDSuccess("123213");
    testCtorBDSuccess("10.0032131");
    testCtorBDSuccess("1654640.0");
    testCtorBDSuccess("3132.321313");
    
    testMoneyCtorFail(null);
  }

  public void testEquals(){
    Decimal us = Decimal.from("9.89");
    Decimal us2 = Decimal.from("9.89");
    Decimal us3 = Decimal.from("+9.89");
    Decimal lz = Decimal.from("09.89");
    
    Decimal us4 = Decimal.from("9.88");

    Decimal us5 = Decimal.from("10");
    Decimal us6 = Decimal.from("10.00");
    
    assertTrue( us.equals(us2) );
    assertTrue( us.equals(us3) );
    assertTrue( us.equals(lz) );
    
    assertFalse( us.equals(us4) );
    assertFalse( us5.equals(us6) );
  }
  
  public void testCompareTo(){
    Decimal us = Decimal.from("9.89");
    Decimal us2 = Decimal.from("9.89");
    Decimal us4 = Decimal.from("9.88");
    Decimal lz = Decimal.from("09.89");

    Decimal us5 = Decimal.from("10");
    Decimal us6 = Decimal.from("10.00");
    
    assertTrue( us.compareTo(us2) == 0);
    assertTrue( us.compareTo(lz) == 0);
    assertTrue( us.compareTo(us4) > 0);
    assertTrue( us5.compareTo(us6) == 0);
  }
  
  public void testAmount(){
    testAmount("1", "1");
    testAmount("0", "0");
    testAmount("0.00", "0.00");
    testAmount("00.00", "00.00");
    testAmount("-1", "-1");
    testAmount("1.0", "1.0");
    testAmount("23", "23");
    testAmount("123000", "123000");
    testAmount("123000.0001", "123000.0001");
    testAmount("-123000", "-123000");
  }
  
  public void testNumOfDecimals(){
    testNumOfDecimals("12.13", 2);
    testNumOfDecimals("12", 0);
    testNumOfDecimals("123456", 0);
    testNumOfDecimals("12.1", 1);
    testNumOfDecimals("12.0", 1);
    testNumOfDecimals("-12.9846", 4);
    testNumOfDecimals("-123456", 0);
    testNumOfDecimals("-12.1", 1);
    testNumOfDecimals("-12.0", 1);
    testNumOfDecimals("-12.9846", 4);
    testNumOfDecimals("123000", 0);
  }
  
  public void testIsInteger(){
    testIsInteger("0", PASS);
    testIsInteger("0.0", PASS);
    testIsInteger("0.000", PASS);
    testIsInteger("100", PASS);
    testIsInteger("100.0", PASS);
    testIsInteger("100.00000000", PASS);
    testIsInteger("-100", PASS);
    testIsInteger("-100.0", PASS);
    testIsInteger("-100.00000000", PASS);
    
    testIsInteger("0.0000000001", FAIL);
    testIsInteger("100.0000000001", FAIL);
    testIsInteger("-100.0000000001", FAIL);
    testIsInteger("2.3216549873211", FAIL);
  }
  
  public void testIsPlus(){
    testIsPlusFor("9.89", PASS);
    testIsPlusFor("989", PASS);
    testIsPlusFor("98900", PASS);
    testIsPlusFor("0.01", PASS);

    testIsPlusFor("+9.89", PASS);
    testIsPlusFor("+989", PASS);
    testIsPlusFor("+98900", PASS);
    testIsPlusFor("+0.01", PASS);
    
    testIsPlusFor("0.00", FAIL);
    testIsPlusFor("-0.01", FAIL);
    testIsPlusFor("-9.89", FAIL);
    testIsPlusFor("9", PASS);
  }
  
  public void testIsMinus(){
    testIsMinusFor("9.89", FAIL);
    testIsMinusFor("-989", PASS);
    testIsMinusFor("-989000", PASS);
    testIsMinusFor("0.01", FAIL);
    testIsMinusFor("0.00", FAIL);
    testIsMinusFor("-0.01", PASS);
    testIsMinusFor("-9.89", PASS);
    testIsMinusFor("-100", PASS);
  }
  
  public void testIsZero(){
    testIsZeroFor("0.00", PASS);
    testIsZeroFor("0", PASS);
    testIsZeroFor("0.0", PASS);
    testIsZeroFor("00.00", PASS);
    testIsZeroFor(".00", PASS);
    testIsZeroFor(".0", PASS);

    testIsZeroFor("+0.00", PASS);
    testIsZeroFor("+0", PASS);
    testIsZeroFor("+0.0", PASS);
    testIsZeroFor("+00.00", PASS);
    testIsZeroFor("+.00", PASS);
    testIsZeroFor("+.0", PASS);
    
    testIsZeroFor("0.01", FAIL);
    testIsZeroFor("1.01", FAIL);
  }

  public void testEq(){
    Decimal us1 = Decimal.from("10.00");
    Decimal us2 = Decimal.from("10.20");
    Decimal us3 = Decimal.from("10.20");
    Decimal us4 = Decimal.from("99");
    Decimal us5 = Decimal.from("99.00");
    Decimal us6 = Decimal.from("00099.00");
    Decimal val1 = Decimal.from("-5.00");
    Decimal val2 = Decimal.from("-5");
    assertTrue( us2.eq(us3) );
    assertTrue( us4.eq(us5) );
    assertTrue( us4.eq(us6) );
    assertTrue( val1.eq(val2) );
    assertFalse( us1.eq(us2) );
  }

  public void testGt(){
    Decimal us1 = Decimal.from("1.00");
    Decimal us2 = Decimal.from("10.20");
    Decimal us3 = Decimal.from("10.200");
    Decimal us4 = Decimal.from("1");
    Decimal val1 = Decimal.from("-5");
    Decimal val2 = Decimal.from("-5.00");
    Decimal val3 = Decimal.from("-5.50");
    
    assertTrue( us2.gt(us1) );
    assertTrue( us3.gt(us1) );
    assertTrue( val1.gt(val3) );
    assertTrue( val1.gt(val3) );
    assertTrue( us1.gt(val1) );
    
    assertFalse( us2.gt(us3) );
    assertFalse( us1.gt(us4) );
    assertFalse( us4.gt(us1) );
    assertFalse( val2.gt(val1) );
  }
  
  public void testGteq(){
    Decimal us1 = Decimal.from("1.00");
    Decimal us2 = Decimal.from("10.20");
    Decimal us3 = Decimal.from("10.200");
    Decimal us4 = Decimal.from("1");
    Decimal val1 = Decimal.from("-53");
    Decimal val2 = Decimal.from("-53.0000001");
    Decimal val3 = Decimal.from("-54");
    assertTrue( us2.gteq(us1) );
    assertTrue( us2.gteq(us3) );
    assertTrue( us3.gteq(us1) );
    assertTrue( us1.gteq(us4) );
    assertTrue( val1.gteq(val2) );
    assertTrue( val1.gteq(val3) );
    assertTrue( us3.gteq(us2) );
    
    assertFalse( val2.gteq(val1) );
    assertFalse( val3.gteq(val1) );
  }
  
  public void testLt(){
    Decimal us1 = Decimal.from("1.00");
    Decimal us2 = Decimal.from("10.20");
    Decimal us3 = Decimal.from("10.200");
    Decimal us4 = Decimal.from("1");
    Decimal val1 = Decimal.from("-23");
    Decimal val2 = Decimal.from("-023");
    Decimal val3 = Decimal.from("-23.00");
    Decimal val4 = Decimal.from("-23.001");
    assertTrue(val4.lt(val1));
    assertFalse(val3.lt(val2));
    assertFalse( us2.lt(us1) );
    assertFalse( us2.lt(us3) );
    assertFalse( us3.lt(us1) );
    assertFalse( us1.lt(us4) );
  }
  
  public void testLteq(){
    Decimal us1 = Decimal.from("1.00");
    Decimal us2 = Decimal.from("10.20");
    Decimal us3 = Decimal.from("10.20");
    Decimal us4 = Decimal.from("1");
    Decimal val1 = Decimal.from("-16");
    Decimal val2 = Decimal.from("-16.00");
    Decimal val3 = Decimal.from("-16.001");
    assertTrue( us1.lteq(us2) );
    assertTrue( us2.lteq(us3) );
    assertTrue( val3.lteq(val2) );
    assertTrue( val1.lteq(val2) );
    assertTrue( val3.lteq(val1) );
    assertTrue( us1.lteq(us4) );

    assertFalse( us3.lteq(us1) );
    assertFalse( us3.lteq(us1) );
    assertFalse( val1.lteq(val3) );
  }

  public void testPlus(){
    testPlus("1", "2", "3");
    testPlus("1.0", "2", "3.0");
    testPlus("0", "55.5", "55.5");
    testPlus("-1", "5", "4");
    testPlus("-1", "5.0", "4.0");
    testPlus("00010", "6", "16");
    testPlus("00010", "0006", "00016");
    testPlus("-11", "11", "0");
    testPlus("123", "400", "523");
  }
  
  public void testMinus(){
    testMinus("1", "2", "-1");
    testMinus("1.0", "2", "-1.0");
    testMinus("0", "55.5", "-55.5");
    testMinus("-1", "5", "-6");
    testMinus("-1", "5.0", "-6.0");
    testMinus("00010", "6", "4");
    testMinus("00010", "0006", "0004");
    testMinus("-11", "11", "-22");
    testMinus("523", "123", "400");
    testMinus("523", "123.0", "400.0");
    testMinus("523", "123.00", "400.00");
  }

  public void testSum(){
    testSum("0", "0");
    testSum("0.0", "0.0");
    testSum("00.0", "00.0");
    testSum("1", "0", "1");
    testSum("1.0", "0", "1.0");
    testSum("1.00", "0", "1.00");
    testSum("0.00", "-1", "1.00");
    testSum("0.000", "-1.000", "1.00");
    testSum("0.00000", "12.12345", "-12.12345");
    testSum("90", "12", "-12", "45", "45");
    testSum("66", "-12", "-12", "45", "45");
    testSum("66.0001", "-12", "-12", "45", "45.0001");
  }
  
  public void testTimes(){
    testTimes("10", "2", "20");
    testTimes("10", "3", "30");
    testTimes("25", "2", "50");
    testTimes("-25", "10", "-250");
    testTimes("+12", "3", "36");
    testTimes("8", "7", "56");

    testTimes("10.00", "2", "20.00");
    testTimes("10.00", "3", "30.00");
    testTimes("10.00", "9", "90.00");
    testTimes("10.00", "-2", "-20.00");
    testTimes("100000.00", "-2", "-200000.00");
    testTimes("10.0000", "2", "20.0000");

    testTimes("10", "2.0", "20.0");
    testTimes("10", "3.0", "30.0");
    testTimes("25", "2.0", "50.0");
    testTimes("-25", "10.0", "-250.0");
    testTimes("+12", "3.0", "36.0");
    testTimes("8", "7.0", "56.0");
    
    testTimes("987.65", "7.0", "6913.550");
    testTimes("987.65", "7.123", "7035.03095");
    testTimes("987.65", "-0.0125", "-12.345625");
    
    testTimes("0008", "7.0", "56.0");
    testTimes("0008", "07.0", "56.0");
    testTimes("0008", "7.0", "0056.0");
    testTimes("-8", "-7.0", "56.0");
    testTimes("-8000000", "-70.0", "560000000.0");
  }

  public void testDiv(){
    testDiv("10", "2", "5");    
    testDiv("10.00", "2", "5.00");    
    testDiv("-10", "2", "-5");    
    testDiv("10.0", "2", "5.0");    
    testDiv("10", "3", "3.33333333333333333333");    
    testDiv("-10", "3", "-3.33333333333333333333");    
    testDiv("10.0", "3", "3.33333333333333333333");    
    testDiv("10.00", "3", "3.33333333333333333333");
    
    testDiv("100.00", "3", "33.33333333333333333333");
    testDiv("1025.25", "1", "1025.25");
    testDiv("1025.25", "2", "512.625");
    testDiv("1025.25", "3", "341.75");
    testDiv("1025.25", "4", "256.3125");
    testDiv("1025.25", "5", "205.05");
    testDiv("1025.25", "6", "170.875");
    testDiv("1025.25", "7", "146.46428571428571428571");
    testDiv("1025.25", "8", "128.15625");
    testDiv("1025.25", "9", "113.91666666666666666667");
    testDiv("1025.25", "10", "102.525");
    
    testDiv("10", "2.0", "5");    
    testDiv("10.00", "2.0", "5.0");    
    testDiv("-10", "2.0", "-5");    
    testDiv("10.0", "2.0", "5");    
    testDiv("10", "3.0", "3.33333333333333333333");    
    testDiv("-10", "3.00", "-3.33333333333333333333");    
    testDiv("10.0", "3.000", "3.33333333333333333333");    
    testDiv("10.00", "3.0000", "3.33333333333333333333");

    testDiv("123456", "0.00003213", "3842390289.44911297852474323063");    

    testDiv("100.00", "3.0", "33.33333333333333333333");
    testDiv("1025.25", "1.0", "1025.25");
    testDiv("1025.25", "2.0", "512.625");
    testDiv("1025.25", "3.0", "341.75");
    testDiv("1025.25", "4.0", "256.3125");
    testDiv("1025.25", "5.0", "205.05");
    testDiv("1025.25", "6.0", "170.875");
    testDiv("1025.25", "7.0", "146.46428571428571428571");
    testDiv("1025.25", "8.0000", "128.15625");
    testDiv("1025.25", "9.0", "113.91666666666666666667");
    testDiv("1025.25", "10.00", "102.525");
    
    testDiv("1025.25", "0.125", "8202");
    testDiv("1025.25", "10.125", "101.25925925925925925926");
    
    testDiv("1025.25", "0.001", "1025250"); 
 }
  
  public void testAbs(){
    testAbs("0","0");
    testAbs("0","+0");
    testAbs("0","-0");
    testAbs("12","-12");
    testAbs("12.753","-12.753");
    testAbs("12.753","-12.753");
    testAbs("12.753","012.753");
    testAbs("12.753","12.753");
  }
  
  public void testNegate(){
    testNegate("0","0");
    testNegate("-1","1");
    testNegate("-1.0","1.0");
    testNegate("1","-1");
    testNegate("1.00","-1.00");
    testNegate("-1.00","01.00");
  }
  
  public void testTypicalOperations(){
    Decimal price = Decimal.from("125.48");
    Decimal expectedCost = Decimal.from("627.40");
    Decimal expectedCostWithTax = Decimal.from("658.77");
    Decimal factor1 = Decimal.from("10");
    Decimal expectedProduct1 = Decimal.from("1254.80");
    Decimal factor2 = Decimal.from("0.125");
    Decimal expectedProduct2 = Decimal.from("15.685");

    Decimal denom = Decimal.from("0.125");
    Decimal expectedQuotient = Decimal.from("1003.84");

    Decimal cost = price.times(5);
    assertTrue(cost.equals(expectedCost));
    
    Decimal costWithTax = cost.times(1.05); 
    assertTrue(costWithTax.eq(expectedCostWithTax));
    
    Decimal product = price.times(factor1);
    assertTrue(product.eq(expectedProduct1));
    
    product = price.times(factor2);
    assertTrue(product.eq(expectedProduct2));

    Decimal quotient = price.div(denom);
    assertTrue(quotient.eq(expectedQuotient));
  }
  
  public void testToString(){
    Decimal us1 = Decimal.from("1000.00");
    assertTrue( us1.toString().equals("1000.00"));
  }
  
  public void testRoundToInteger(){
    testRound("10.25", "10");
    testRound("10", "10");
    testRound("10.45", "10");
    testRound("10.50", "10");
    testRound("10.51", "11");
    testRound("10.52", "11");
    testRound("10.99", "11");
    testRound("11.00", "11");
    testRound("1000000", "1000000");
    
    testRound("-10.25", "-10");
    testRound("-10", "-10");
    testRound("-10.45", "-10");
    testRound("-10.50", "-10");
    testRound("-10.51", "-11");
    testRound("-10.52", "-11");
    testRound("-10.99", "-11");
  }
  
  public void testRoundToNDecimals(){
    testRound("10.25", 0, "10");
    testRound("10.25", 1, "10.2");
    testRound("10.25", 2, "10.25");
    testRound("10.25", 3, "10.250");
  }
  
  public void testRound2(){
    testRound2("1710.10", "0.05", "1710.10");
    testRound2("1710.11", "0.05", "1710.10");
    testRound2("1710.12", "0.05", "1710.10");
    testRound2("1710.13", "0.05", "1710.15");
    testRound2("1710.14", "0.05", "1710.15");
    testRound2("1710.15", "0.05", "1710.15");
    testRound2("1710.16", "0.05", "1710.15");
    testRound2("1710.17", "0.05", "1710.15");
    testRound2("1710.18", "0.05", "1710.20");
    testRound2("1710.19", "0.05", "1710.20");
    testRound2("1710.20", "0.05", "1710.20");

    testRound2("1710.101", "0.05", "1710.10");
    testRound2("1710.1099999", "0.05", "1710.10");
    testRound2("1710.121", "0.05", "1710.10");
    testRound2("1710.124", "0.05", "1710.10");
    testRound2("1710.125", "0.05", "1710.10");
    
    testRound2("1710.1250000000001", "0.05", "1710.15");
    testRound2("1710.126", "0.05", "1710.15");
    testRound2("1710.129", "0.05", "1710.15");
    
    testRound2("1710.20", "100", "1700");
    testRound2("1710.20", "1000", "2000");
    testRound2("171256789", "100", "171256800");
    testRound2("171256789", "1000", "171257000");
    testRound2("171256789", "1000000", "171000000");
    
    //same, but using overloads
    
    testRound2("1710.10", 0.05, "1710.10");
    testRound2("1710.11", 0.05, "1710.10");
    testRound2("1710.12", 0.05, "1710.10");
    testRound2("1710.13", 0.05, "1710.15");
    testRound2("1710.14", 0.05, "1710.15");
    testRound2("1710.15", 0.05, "1710.15");
    testRound2("1710.16", 0.05, "1710.15");
    testRound2("1710.17", 0.05, "1710.15");
    testRound2("1710.18", 0.05, "1710.20");
    testRound2("1710.19", 0.05, "1710.20");
    testRound2("1710.20", 0.05, "1710.20");

    testRound2("1710.101", 0.05, "1710.10");
    testRound2("1710.1099999", 0.05, "1710.10");
    testRound2("1710.121", 0.05, "1710.10");
    testRound2("1710.124", 0.05, "1710.10");
    testRound2("1710.125", 0.05, "1710.10");
    
    testRound2("1710.1250000000001", 0.05, "1710.15");
    testRound2("1710.126", 0.05, "1710.15");
    testRound2("1710.129", 0.05, "1710.15");
    
    testRound2("1710.20", 100, "1700");
    testRound2("1710.20", 1000, "2000");
    testRound2("171256789", 100, "171256800");
    testRound2("171256789", 1000, "171257000");
    testRound2("171256789", 1000000, "171000000");
  }
  
  public void testPow(){
    testPow("1", 1, "1");
    testPow("1", 0, "1");
    testPow("1", -1, "1");
    
    testPow("0", 0, "1");
    testPow("0", 1, "0");
    
    testPow("10", 0, "1");
    testPow("10.00", 0, "1");
    testPow("010.00", 0, "1");
    testPow("10", 1, "10");
    testPow("10.00", 1, "10.00");
    testPow("-10.00", 1, "-10.00");
    testPow("-10.123", 1, "-10.123");
    testPow("10", 2, "100");
    testPow("10.0", 2, "100.0");
    testPow("5", 2, "25");
    testPow("5.0", 2, "25.0");
    testPow("10", -1, "0.1");
    testPow("10", -2, "0.01");
    testPow("10", -20, "0.00000000000000000001");
    testPow("-123456.789", 0, "1");
    testPow("-123456.789", 1, "-123456.789");
    
    testPow("2", 4, "16");
    testPow("2.000", 4, "16.000");
    testPow("2.0001", 4, "16.0032002400080001");
    testPow("2", -4, "0.0625");
    
    testPow("1", "0" ,"1");
    testPow("1", "0.0" ,"1");
    testPow("1", "2" ,"1");
    testPow("1", "3" ,"1");
    testPow("1", "2.0" ,"1");
    testPow("1", "2.00" ,"1");
    testPow("1", "-1" ,"1");
    testPow("1", "0.5" ,"1");
    
    testPow("2", "0" ,"1");
    testPow("2", "0.0" ,"1");
    testPow("2", "1" ,"2");
    testPow("2", "1.0" ,"2");
    testPow("2", "2.0" ,"4");
    testPow("2", "2.5" ,"5.65685424949238");
    testPow("2", "-2.5" ,"0.17677669529663687");
    testPow("25", "0.5" ,"5");
    testPow("25", "-0.5" ,"0.2");
    testPow("81", "0.5" ,"9");
    testPow("81.0000", "0.5" ,"9");
    
  }
  
  // PRIVATE 
  
  private void testCtorSuccess(String aAmount){
    @SuppressWarnings("unused")
    Decimal  decimal = Decimal.from(aAmount);
  }
  
  private void testCtorBDSuccess(String aAmount){
    @SuppressWarnings("unused")
    Decimal  decimal = new Decimal(new BigDecimal(aAmount));
  }
  
  private void testCtorSuccess(long aAmount){
    @SuppressWarnings("unused")
    Decimal decimal = Decimal.from(aAmount);
  }
  
  private void testCtorSuccess(double aAmount){
    @SuppressWarnings("unused")
    Decimal decimal = Decimal.from(aAmount);
  }
  
  private static final boolean PASS = true;
  private static final boolean FAIL = false;

  private void testMoneyCtorFail(String aAmount){
    BigDecimal amount = aAmount == null ? null : new BigDecimal(aAmount);
    try {
      @SuppressWarnings("unused")
      Decimal  money = new Decimal(amount);
      fail("Should have failed.");
    }
    catch (IllegalArgumentException ex){
      //expected
      //log("Problemo : " + ex);
    }
  }
  
  private void testIsPlusFor(String aAmount, boolean aSucceed){
    Decimal money = Decimal.from(aAmount);
    if ( aSucceed ) {
      assertTrue( money.isPlus() );
    }
    else {
      assertFalse( money.isPlus() );
    }
  }
  
  private void testIsMinusFor(String aAmount, boolean aSucceed){
    Decimal money = Decimal.from(aAmount);
    if ( aSucceed ) {
      assertTrue( money.isMinus() );
    }
    else {
      assertFalse( money.isMinus() );
    }
  }
  
  private void testIsZeroFor(String aAmount, boolean aSucceed){
    Decimal money = Decimal.from(aAmount);
    if ( aSucceed ) {
      assertTrue( money.isZero() );
    }
    else {
      assertFalse( money.isZero() );
    }
  }

  private void testTimes(String aValue, String aFactor, String aExpectedAnswer){
    Decimal expected = Decimal.from(aExpectedAnswer);
    Decimal value = Decimal.from(aValue);
    Decimal factor = Decimal.from(aFactor);
    Decimal actual = value.times(factor);
    if (! actual.equals(expected) ){
      throw new RuntimeException("Actual " +  actual + " Expected: " + expected);
    }
  }
  
  private void testDiv(String aTop, String aBottom, String aExpectedAnswer){
    Decimal expected = Decimal.from(aExpectedAnswer);
    Decimal top = Decimal.from(aTop);
    Decimal bottom = Decimal.from(aBottom);
    Decimal actual = top.div(bottom);
    /* Uses eq(), not equals()!
    * Found this case in which the values are the same, but the scales differ.
    * actual 102525, with scale -1 (that is, add a zero to the end of the integer)
    * expec  1025250, with scale 0
    */
    if (! actual.eq(expected) ){
      throw new RuntimeException("Actual " +  actual + " Expected: " + expected);
    }
  }
  
  private void testRound(String aInput, String aExpectedAnswer){
    Decimal expected = Decimal.from(aExpectedAnswer);
    Decimal money = Decimal.from(aInput);
    Decimal calc = money.round();
    assertTrue(calc.equals(expected));
    assertTrue(calc.getAmount().scale() == 0);
  }
  
  private void testRound(String aInput, int aNumDecimals, String aExpectedAnswer){
    Decimal expected = Decimal.from(aExpectedAnswer);
    Decimal money = Decimal.from(aInput);
    Decimal calc = money.round(aNumDecimals);
    assertTrue(calc.equals(expected));
    assertTrue(calc.getAmount().scale() == aNumDecimals);
  }
  
  private void testRound2(String aInput, String aInterval, String aExpectedAnswer){
    Decimal input = Decimal.from(aInput);
    Decimal interval = Decimal.from(aInterval);
    Decimal actual = input.round2(interval);
    Decimal expected = Decimal.from(aExpectedAnswer);
    assertTrue(actual.equals(expected));
  }
  
  private void testRound2(String aInput, long aInterval, String aExpectedAnswer){
    Decimal input = Decimal.from(aInput);
    Decimal actual = input.round2(aInterval);
    Decimal expected = Decimal.from(aExpectedAnswer);
    assertTrue(actual.equals(expected));
  }
  
  private void testRound2(String aInput, double aInterval, String aExpectedAnswer){
    Decimal input = Decimal.from(aInput);
    Decimal actual = input.round2(aInterval);
    Decimal expected = Decimal.from(aExpectedAnswer);
    assertTrue(actual.equals(expected));
  }
  
  private void testNumOfDecimals(String aAmount, int aNumDecimals){
    Decimal money = Decimal.from(aAmount);
    assertTrue(money.getNumDecimals() == aNumDecimals);
  }
  
  private void testIsInteger(String aValue, boolean aPass){
    if(aPass){
      assertTrue(Decimal.from(aValue).isInteger());
    }
    else {
      assertFalse(Decimal.from(aValue).isInteger());
    }
  }
  
  private void testAmount(String aInput, String aExpectedAnswer){
    BigDecimal expected = new BigDecimal(aExpectedAnswer);
    Decimal input = Decimal.from(aInput);
    assertTrue(input.getAmount().equals(expected));
  }
  
  private void testPlus(String a, String b, String aExpected){
    Decimal expected = Decimal.from(aExpected);
    Decimal actual = Decimal.from(a).plus(Decimal.from(b));
    assertTrue( actual.equals( expected) );
  }
  
  private void testMinus(String a, String b, String aExpected){
    Decimal expected = Decimal.from(aExpected);
    Decimal actual = Decimal.from(a).minus(Decimal.from(b));
    assertTrue( actual.equals( expected) );
  }

  public void testSum(String aExpected, String... aValues){
    List<Decimal> values = new ArrayList<Decimal>();
    for(String value : aValues){
      values.add(Decimal.from(value));
    }
    Decimal expected = Decimal.from(aExpected);
    Decimal actual = Decimal.sum(values);
    assertTrue( actual.equals(expected) );
  }
  
  public void testAbs(String aExpected, String aValue){
    Decimal value = Decimal.from(aValue);
    Decimal actual = value.abs();
    Decimal expected = Decimal.from(aExpected);
    assertTrue( actual.equals(expected) );
  }
  
  public void testNegate(String aExpected, String aValue){
    Decimal value = Decimal.from(aValue);
    Decimal actual = value.negate();
    Decimal expected = Decimal.from(aExpected);
    assertTrue( actual.equals(expected) );
  }
  
  private void testPow(String aBase, int aExponent, String aExpected){
    Decimal base = Decimal.from(aBase);
    Decimal actual = base.pow(aExponent);
    Decimal expected = Decimal.from(aExpected);
    if (! actual.eq(expected) ){
      throw new RuntimeException("Actual " +  actual + " Expected: " + expected);
    }
  }

  private void testPow(String aBase, String aExponent, String aExpected){
    Decimal base = Decimal.from(aBase);
    Decimal exponent = Decimal.from(aExponent);
    Decimal actual = base.pow(exponent);
    Decimal expected = Decimal.from(aExpected);
    if (! actual.eq(expected) ){
      throw new RuntimeException("Actual " +  actual + " Expected: " + expected);
    }
  }
  
  
}
