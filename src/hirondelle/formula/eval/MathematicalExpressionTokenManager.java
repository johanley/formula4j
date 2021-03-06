/* Generated By:JavaCC: Do not edit this line. MathematicalExpressionTokenManager.java */
package hirondelle.formula.eval;
import java.io.*;
import java.util.*;
import hirondelle.formula.function.Function;
import hirondelle.formula.Decimal;
import hirondelle.formula.UnpopulatedVariableException;
import hirondelle.formula.UnknownFunctionException;
import java.math.BigDecimal;

/** Token Manager. */
public class MathematicalExpressionTokenManager implements MathematicalExpressionConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 37:
         return jjStopAtPos(0, 21);
      case 40:
         return jjStopAtPos(0, 13);
      case 41:
         return jjStopAtPos(0, 14);
      case 42:
         return jjStopAtPos(0, 18);
      case 43:
         return jjStopAtPos(0, 16);
      case 44:
         return jjStopAtPos(0, 15);
      case 45:
         return jjStopAtPos(0, 17);
      case 47:
         return jjStopAtPos(0, 19);
      case 94:
         return jjStopAtPos(0, 20);
      default :
         return jjMoveNfa_0(1, 0);
   }
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0x0L, 0x1L
};
static final long[] jjbitVec1 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec3 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 28;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if ((0x5c0040d4ffffffffL & l) != 0L)
                  {
                     if (kind > 10)
                        kind = 10;
                     jjCheckNAddStates(0, 2);
                  }
                  else if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 2)
                        kind = 2;
                     jjCheckNAddStates(3, 6);
                  }
                  if ((0x3fe000000000000L & l) != 0L)
                  {
                     if (kind > 2)
                        kind = 2;
                     jjCheckNAdd(2);
                  }
                  else if ((0x100003600L & l) != 0L)
                  {
                     if (kind > 1)
                        kind = 1;
                     jjCheckNAdd(0);
                  }
                  else if (curChar == 46)
                     jjCheckNAdd(4);
                  break;
               case 0:
                  if ((0x100003600L & l) == 0L)
                     break;
                  if (kind > 1)
                     kind = 1;
                  jjCheckNAdd(0);
                  break;
               case 2:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 2)
                     kind = 2;
                  jjCheckNAdd(2);
                  break;
               case 3:
                  if (curChar == 46)
                     jjCheckNAdd(4);
                  break;
               case 4:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 2)
                     kind = 2;
                  jjCheckNAddTwoStates(4, 5);
                  break;
               case 6:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(7);
                  break;
               case 7:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 2)
                     kind = 2;
                  jjCheckNAdd(7);
                  break;
               case 13:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 2)
                     kind = 2;
                  jjCheckNAddStates(3, 6);
                  break;
               case 14:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 2)
                     kind = 2;
                  jjCheckNAddTwoStates(14, 15);
                  break;
               case 16:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(17);
                  break;
               case 17:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 2)
                     kind = 2;
                  jjCheckNAdd(17);
                  break;
               case 18:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(18, 19);
                  break;
               case 19:
                  if (curChar != 46)
                     break;
                  if (kind > 2)
                     kind = 2;
                  jjCheckNAddTwoStates(20, 21);
                  break;
               case 20:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 2)
                     kind = 2;
                  jjCheckNAddTwoStates(20, 21);
                  break;
               case 22:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(23);
                  break;
               case 23:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 2)
                     kind = 2;
                  jjCheckNAdd(23);
                  break;
               case 24:
                  if ((0x5c0040d4ffffffffL & l) == 0L)
                     break;
                  if (kind > 10)
                     kind = 10;
                  jjCheckNAddStates(0, 2);
                  break;
               case 25:
                  if ((0x5fff40d4ffffffffL & l) != 0L)
                     jjCheckNAddTwoStates(25, 26);
                  break;
               case 26:
                  if (curChar == 40)
                     kind = 9;
                  break;
               case 27:
                  if ((0x5fff40d4ffffffffL & l) == 0L)
                     break;
                  if (kind > 10)
                     kind = 10;
                  jjCheckNAdd(27);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if ((0xaffffffeafffffffL & l) != 0L)
                  {
                     if (kind > 10)
                        kind = 10;
                     jjCheckNAddStates(0, 2);
                  }
                  if ((0x2000000020L & l) != 0L)
                  {
                     if (kind > 6)
                        kind = 6;
                  }
                  else if (curChar == 112)
                     jjstateSet[jjnewStateCnt++] = 11;
                  else if (curChar == 80)
                     jjstateSet[jjnewStateCnt++] = 9;
                  break;
               case 5:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(7, 8);
                  break;
               case 8:
                  if ((0x2000000020L & l) != 0L && kind > 6)
                     kind = 6;
                  break;
               case 9:
                  if (curChar == 73 && kind > 6)
                     kind = 6;
                  break;
               case 10:
                  if (curChar == 80)
                     jjstateSet[jjnewStateCnt++] = 9;
                  break;
               case 11:
                  if (curChar == 105 && kind > 6)
                     kind = 6;
                  break;
               case 12:
                  if (curChar == 112)
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 15:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(9, 10);
                  break;
               case 21:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(11, 12);
                  break;
               case 24:
                  if ((0xaffffffeafffffffL & l) == 0L)
                     break;
                  if (kind > 10)
                     kind = 10;
                  jjCheckNAddStates(0, 2);
                  break;
               case 25:
                  if ((0xaffffffeafffffffL & l) != 0L)
                     jjCheckNAddTwoStates(25, 26);
                  break;
               case 27:
                  if ((0xaffffffeafffffffL & l) == 0L)
                     break;
                  if (kind > 10)
                     kind = 10;
                  jjCheckNAdd(27);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 1:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                  {
                     if (kind > 6)
                        kind = 6;
                  }
                  if (jjCanMove_1(hiByte, i1, i2, l1, l2))
                  {
                     if (kind > 10)
                        kind = 10;
                     jjCheckNAddStates(0, 2);
                  }
                  break;
               case 8:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 6)
                     kind = 6;
                  break;
               case 24:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 10)
                     kind = 10;
                  jjCheckNAddStates(0, 2);
                  break;
               case 25:
                  if (jjCanMove_1(hiByte, i1, i2, l1, l2))
                     jjCheckNAddTwoStates(25, 26);
                  break;
               case 27:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 10)
                     kind = 10;
                  jjCheckNAdd(27);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 28 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   25, 26, 27, 14, 15, 18, 19, 6, 7, 16, 17, 22, 23, 
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 3:
         return ((jjbitVec0[i2] & l2) != 0L);
      default :
         return false;
   }
}
private static final boolean jjCanMove_1(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec3[i2] & l2) != 0L);
      default :
         if ((jjbitVec1[i1] & l1) != 0L)
            return true;
         return false;
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, null, 
"\50", "\51", "\54", "\53", "\55", "\52", "\57", "\136", "\45", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x3fe645L, 
};
static final long[] jjtoSkip = {
   0x2L, 
};
protected JavaCharStream input_stream;
private final int[] jjrounds = new int[28];
private final int[] jjstateSet = new int[56];
protected char curChar;
/** Constructor. */
public MathematicalExpressionTokenManager(JavaCharStream stream){
   if (JavaCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public MathematicalExpressionTokenManager(JavaCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(JavaCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 28; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(JavaCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
