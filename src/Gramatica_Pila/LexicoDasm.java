/* The following code was generated by JFlex 1.4.3 on 7/11/18 09:37 AM */

package Gramatica_Pila;
import java.util.LinkedList;
import java_cup.runtime.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 7/11/18 09:37 AM from the specification file
 * <tt>src/INTERPRETE_PILA/GRAMATICA/Lexico.flex</tt>
 */
public class LexicoDasm implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\7\1\3\1\2\1\0\1\3\1\1\16\7\4\0\1\3\3\0"+
    "\1\10\1\41\7\0\1\12\1\5\1\11\12\4\7\0\1\13\1\40"+
    "\1\35\1\14\1\33\1\16\1\32\1\6\1\15\2\6\1\21\1\17"+
    "\1\31\1\25\1\24\1\36\1\30\1\27\1\22\1\20\1\23\2\6"+
    "\1\34\1\37\4\0\1\26\1\0\1\13\1\40\1\35\1\14\1\33"+
    "\1\16\1\32\1\6\1\15\2\6\1\21\1\17\1\31\1\25\1\24"+
    "\1\36\1\30\1\27\1\22\1\20\1\23\2\6\1\34\1\37\1\0"+
    "\1\7\2\0\41\7\2\0\4\6\4\0\1\6\2\0\1\7\7\0"+
    "\1\6\4\0\1\6\5\0\27\6\1\0\37\6\1\0\u01ca\6\4\0"+
    "\14\6\16\0\5\6\7\0\1\6\1\0\1\6\21\0\160\7\5\6"+
    "\1\0\2\6\2\0\4\6\10\0\1\6\1\0\3\6\1\0\1\6"+
    "\1\0\24\6\1\0\123\6\1\0\213\6\1\0\5\7\2\0\236\6"+
    "\11\0\46\6\2\0\1\6\7\0\47\6\7\0\1\6\1\0\55\7"+
    "\1\0\1\7\1\0\2\7\1\0\2\7\1\0\1\7\10\0\33\6"+
    "\5\0\3\6\15\0\5\7\6\0\1\6\4\0\13\7\5\0\53\6"+
    "\25\7\12\4\4\0\2\6\1\7\143\6\1\0\1\6\10\7\1\0"+
    "\6\7\2\6\2\7\1\0\4\7\2\6\12\4\3\6\2\0\1\6"+
    "\17\0\1\7\1\6\1\7\36\6\33\7\2\0\131\6\13\7\1\6"+
    "\16\0\12\4\41\6\11\7\2\6\4\0\1\6\5\0\26\6\4\7"+
    "\1\6\11\7\1\6\3\7\1\6\5\7\22\0\31\6\3\7\104\0"+
    "\1\6\1\0\13\6\67\0\33\7\1\0\4\7\66\6\3\7\1\6"+
    "\22\7\1\6\7\7\12\6\2\7\2\0\12\4\1\0\7\6\1\0"+
    "\7\6\1\0\3\7\1\0\10\6\2\0\2\6\2\0\26\6\1\0"+
    "\7\6\1\0\1\6\3\0\4\6\2\0\1\7\1\6\7\7\2\0"+
    "\2\7\2\0\3\7\1\6\10\0\1\7\4\0\2\6\1\0\3\6"+
    "\2\7\2\0\12\4\4\6\7\0\1\6\5\0\3\7\1\0\6\6"+
    "\4\0\2\6\2\0\26\6\1\0\7\6\1\0\2\6\1\0\2\6"+
    "\1\0\2\6\2\0\1\7\1\0\5\7\4\0\2\7\2\0\3\7"+
    "\3\0\1\7\7\0\4\6\1\0\1\6\7\0\12\4\2\7\3\6"+
    "\1\7\13\0\3\7\1\0\11\6\1\0\3\6\1\0\26\6\1\0"+
    "\7\6\1\0\2\6\1\0\5\6\2\0\1\7\1\6\10\7\1\0"+
    "\3\7\1\0\3\7\2\0\1\6\17\0\2\6\2\7\2\0\12\4"+
    "\1\0\1\6\17\0\3\7\1\0\10\6\2\0\2\6\2\0\26\6"+
    "\1\0\7\6\1\0\2\6\1\0\5\6\2\0\1\7\1\6\7\7"+
    "\2\0\2\7\2\0\3\7\10\0\2\7\4\0\2\6\1\0\3\6"+
    "\2\7\2\0\12\4\1\0\1\6\20\0\1\7\1\6\1\0\6\6"+
    "\3\0\3\6\1\0\4\6\3\0\2\6\1\0\1\6\1\0\2\6"+
    "\3\0\2\6\3\0\3\6\3\0\14\6\4\0\5\7\3\0\3\7"+
    "\1\0\4\7\2\0\1\6\6\0\1\7\16\0\12\4\11\0\1\6"+
    "\7\0\3\7\1\0\10\6\1\0\3\6\1\0\27\6\1\0\12\6"+
    "\1\0\5\6\3\0\1\6\7\7\1\0\3\7\1\0\4\7\7\0"+
    "\2\7\1\0\2\6\6\0\2\6\2\7\2\0\12\4\22\0\2\7"+
    "\1\0\10\6\1\0\3\6\1\0\27\6\1\0\12\6\1\0\5\6"+
    "\2\0\1\7\1\6\7\7\1\0\3\7\1\0\4\7\7\0\2\7"+
    "\7\0\1\6\1\0\2\6\2\7\2\0\12\4\1\0\2\6\17\0"+
    "\2\7\1\0\10\6\1\0\3\6\1\0\51\6\2\0\1\6\7\7"+
    "\1\0\3\7\1\0\4\7\1\6\10\0\1\7\10\0\2\6\2\7"+
    "\2\0\12\4\12\0\6\6\2\0\2\7\1\0\22\6\3\0\30\6"+
    "\1\0\11\6\1\0\1\6\2\0\7\6\3\0\1\7\4\0\6\7"+
    "\1\0\1\7\1\0\10\7\22\0\2\7\15\0\60\6\1\7\2\6"+
    "\7\7\4\0\10\6\10\7\1\0\12\4\47\0\2\6\1\0\1\6"+
    "\2\0\2\6\1\0\1\6\2\0\1\6\6\0\4\6\1\0\7\6"+
    "\1\0\3\6\1\0\1\6\1\0\1\6\2\0\2\6\1\0\4\6"+
    "\1\7\2\6\6\7\1\0\2\7\1\6\2\0\5\6\1\0\1\6"+
    "\1\0\6\7\2\0\12\4\2\0\4\6\40\0\1\6\27\0\2\7"+
    "\6\0\12\4\13\0\1\7\1\0\1\7\1\0\1\7\4\0\2\7"+
    "\10\6\1\0\44\6\4\0\24\7\1\0\2\7\5\6\13\7\1\0"+
    "\44\7\11\0\1\7\71\0\53\6\24\7\1\6\12\4\6\0\6\6"+
    "\4\7\4\6\3\7\1\6\3\7\2\6\7\7\3\6\4\7\15\6"+
    "\14\7\1\6\1\7\12\4\4\7\2\0\46\6\1\0\1\6\5\0"+
    "\1\6\2\0\53\6\1\0\u014d\6\1\0\4\6\2\0\7\6\1\0"+
    "\1\6\1\0\4\6\2\0\51\6\1\0\4\6\2\0\41\6\1\0"+
    "\4\6\2\0\7\6\1\0\1\6\1\0\4\6\2\0\17\6\1\0"+
    "\71\6\1\0\4\6\2\0\103\6\2\0\3\7\40\0\20\6\20\0"+
    "\125\6\14\0\u026c\6\2\0\21\6\1\0\32\6\5\0\113\6\3\0"+
    "\3\6\17\0\15\6\1\0\4\6\3\7\13\0\22\6\3\7\13\0"+
    "\22\6\2\7\14\0\15\6\1\0\3\6\1\0\2\7\14\0\64\6"+
    "\40\7\3\0\1\6\3\0\2\6\1\7\2\0\12\4\41\0\3\7"+
    "\2\0\12\4\6\0\130\6\10\0\51\6\1\7\1\6\5\0\106\6"+
    "\12\0\35\6\3\0\14\7\4\0\14\7\12\0\12\4\36\6\2\0"+
    "\5\6\13\0\54\6\4\0\21\7\7\6\2\7\6\0\12\4\46\0"+
    "\27\6\5\7\4\0\65\6\12\7\1\0\35\7\2\0\1\7\12\4"+
    "\6\0\12\4\15\0\1\6\130\0\5\7\57\6\21\7\7\6\4\0"+
    "\12\4\21\0\11\7\14\0\3\7\36\6\15\7\2\6\12\4\54\6"+
    "\16\7\14\0\44\6\24\7\10\0\12\4\3\0\3\6\12\4\44\6"+
    "\122\0\3\7\1\0\25\7\4\6\1\7\4\6\3\7\2\6\11\0"+
    "\300\6\47\7\25\0\4\7\u0116\6\2\0\6\6\2\0\46\6\2\0"+
    "\6\6\2\0\10\6\1\0\1\6\1\0\1\6\1\0\1\6\1\0"+
    "\37\6\2\0\65\6\1\0\7\6\1\0\1\6\3\0\3\6\1\0"+
    "\7\6\3\0\4\6\2\0\6\6\4\0\15\6\5\0\3\6\1\0"+
    "\7\6\16\0\5\7\32\0\5\7\20\0\2\6\23\0\1\6\13\0"+
    "\5\7\5\0\6\7\1\0\1\6\15\0\1\6\20\0\15\6\3\0"+
    "\33\6\25\0\15\7\4\0\1\7\3\0\14\7\21\0\1\6\4\0"+
    "\1\6\2\0\12\6\1\0\1\6\3\0\5\6\6\0\1\6\1\0"+
    "\1\6\1\0\1\6\1\0\4\6\1\0\13\6\2\0\4\6\5\0"+
    "\5\6\4\0\1\6\21\0\51\6\u0a77\0\57\6\1\0\57\6\1\0"+
    "\205\6\6\0\4\6\3\7\2\6\14\0\46\6\1\0\1\6\5\0"+
    "\1\6\2\0\70\6\7\0\1\6\17\0\1\7\27\6\11\0\7\6"+
    "\1\0\7\6\1\0\7\6\1\0\7\6\1\0\7\6\1\0\7\6"+
    "\1\0\7\6\1\0\7\6\1\0\40\7\57\0\1\6\u01d5\0\3\6"+
    "\31\0\11\6\6\7\1\0\5\6\2\0\5\6\4\0\126\6\2\0"+
    "\2\7\2\0\3\6\1\0\132\6\1\0\4\6\5\0\51\6\3\0"+
    "\136\6\21\0\33\6\65\0\20\6\u0200\0\u19b6\6\112\0\u51cd\6\63\0"+
    "\u048d\6\103\0\56\6\2\0\u010d\6\3\0\20\6\12\4\2\6\24\0"+
    "\57\6\1\7\4\0\12\7\1\0\31\6\7\0\1\7\120\6\2\7"+
    "\45\0\11\6\2\0\147\6\2\0\4\6\1\0\4\6\14\0\13\6"+
    "\115\0\12\6\1\7\3\6\1\7\4\6\1\7\27\6\5\7\20\0"+
    "\1\6\7\0\64\6\14\0\2\7\62\6\21\7\13\0\12\4\6\0"+
    "\22\7\6\6\3\0\1\6\4\0\12\4\34\6\10\7\2\0\27\6"+
    "\15\7\14\0\35\6\3\0\4\7\57\6\16\7\16\0\1\6\12\4"+
    "\46\0\51\6\16\7\11\0\3\6\1\7\10\6\2\7\2\0\12\4"+
    "\6\0\27\6\3\0\1\6\1\7\4\0\60\6\1\7\1\6\3\7"+
    "\2\6\2\7\5\6\2\7\1\6\1\7\1\6\30\0\3\6\2\0"+
    "\13\6\5\7\2\0\3\6\2\7\12\0\6\6\2\0\6\6\2\0"+
    "\6\6\11\0\7\6\1\0\7\6\221\0\43\6\10\7\1\0\2\7"+
    "\2\0\12\4\6\0\u2ba4\6\14\0\27\6\4\0\61\6\u2104\0\u016e\6"+
    "\2\0\152\6\46\0\7\6\14\0\5\6\5\0\1\6\1\7\12\6"+
    "\1\0\15\6\1\0\5\6\1\0\1\6\1\0\2\6\1\0\2\6"+
    "\1\0\154\6\41\0\u016b\6\22\0\100\6\2\0\66\6\50\0\15\6"+
    "\3\0\20\7\20\0\7\7\14\0\2\6\30\0\3\6\31\0\1\6"+
    "\6\0\5\6\1\0\207\6\2\0\1\7\4\0\1\6\13\0\12\4"+
    "\7\0\32\6\4\0\1\6\1\0\32\6\13\0\131\6\3\0\6\6"+
    "\2\0\6\6\2\0\6\6\2\0\3\6\3\0\2\6\3\0\2\6"+
    "\22\0\3\7\1\0\1\7\2\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\1\1\2\3\3\1\4\1\2\2\5\1\2\1\6"+
    "\15\5\1\2\1\0\1\1\4\7\1\0\6\5\1\10"+
    "\2\5\1\11\2\5\1\12\4\5\1\13\1\14\1\15"+
    "\3\7\1\3\1\16\1\17\1\5\1\20\2\5\1\21"+
    "\1\22\1\23\2\5\1\24\1\25\1\5\1\26\1\27"+
    "\2\5\6\7\1\30\1\7\1\31\1\5\1\32\3\5"+
    "\1\33\1\5\7\7\1\34\1\5\1\35\4\5\1\36"+
    "\7\7\5\5\10\7\5\5\1\7\1\37\1\40\5\7"+
    "\1\41\4\5\3\7\1\42\1\7\1\43\1\44\1\45"+
    "\1\46\1\5\1\47\1\5\3\7\1\50\1\51\1\52"+
    "\6\7\1\53\1\54\1\55";

  private static int [] zzUnpackAction() {
    int [] result = new int[170];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\42\0\104\0\146\0\42\0\210\0\252\0\314"+
    "\0\356\0\u0110\0\42\0\u0132\0\u0154\0\u0176\0\u0198\0\u01ba"+
    "\0\u01dc\0\u01fe\0\u0220\0\u0242\0\u0264\0\u0286\0\u02a8\0\u02ca"+
    "\0\u02ec\0\252\0\252\0\u030e\0\u0330\0\u0352\0\u0374\0\u0396"+
    "\0\u03b8\0\u03da\0\u03fc\0\u041e\0\u0440\0\u0462\0\u0484\0\u04a6"+
    "\0\u04c8\0\314\0\u04ea\0\u050c\0\u052e\0\u0550\0\u0572\0\u0594"+
    "\0\u05b6\0\u05d8\0\42\0\42\0\u05fa\0\u061c\0\u063e\0\u0396"+
    "\0\314\0\314\0\u0660\0\314\0\u0682\0\u06a4\0\314\0\314"+
    "\0\314\0\u06c6\0\u06e8\0\314\0\314\0\u070a\0\314\0\314"+
    "\0\u072c\0\u074e\0\u0770\0\u0792\0\u07b4\0\u07d6\0\u07f8\0\u081a"+
    "\0\u030e\0\u083c\0\314\0\u085e\0\314\0\u0880\0\u08a2\0\u08c4"+
    "\0\314\0\u08e6\0\u0908\0\u092a\0\u094c\0\u096e\0\u0990\0\u09b2"+
    "\0\u09d4\0\u030e\0\u09f6\0\314\0\u0a18\0\u0a3a\0\u0a5c\0\u0a7e"+
    "\0\314\0\u0aa0\0\u0ac2\0\u0ae4\0\u0b06\0\u0b28\0\u0b4a\0\u0b6c"+
    "\0\u0b8e\0\u0bb0\0\u0bd2\0\u0bf4\0\u0c16\0\u0c38\0\u0c5a\0\u0c7c"+
    "\0\u0c9e\0\u0cc0\0\u0ce2\0\u0d04\0\u0d26\0\u0d48\0\u0d6a\0\u0d8c"+
    "\0\u0dae\0\u0dd0\0\u0df2\0\u0e14\0\u0e36\0\u0e58\0\u0e7a\0\u0e9c"+
    "\0\u0ebe\0\u0ee0\0\314\0\u0f02\0\u0f24\0\u0f46\0\u0f68\0\u0f8a"+
    "\0\u0fac\0\u0fce\0\u030e\0\u0ff0\0\u030e\0\u030e\0\u030e\0\314"+
    "\0\u1012\0\314\0\u1034\0\u1056\0\u1078\0\u109a\0\u030e\0\314"+
    "\0\314\0\u10bc\0\u10de\0\u1100\0\u1122\0\u1144\0\u1166\0\u030e"+
    "\0\u030e\0\u030e";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[170];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\2"+
    "\1\11\1\12\1\13\1\14\1\15\1\10\1\16\1\17"+
    "\1\10\1\20\2\10\1\21\1\22\1\10\1\23\1\10"+
    "\1\24\1\25\1\26\1\10\1\27\2\10\1\30\1\31"+
    "\44\0\1\5\40\0\1\5\44\0\1\6\1\32\40\0"+
    "\1\33\41\0\1\10\1\0\3\10\2\0\26\10\5\0"+
    "\1\10\1\0\1\34\1\10\1\35\2\0\15\34\1\36"+
    "\4\34\1\37\3\34\12\0\1\40\34\0\1\10\1\0"+
    "\3\10\2\0\1\10\1\41\14\10\1\42\7\10\5\0"+
    "\1\10\1\0\3\10\2\0\2\10\1\43\23\10\5\0"+
    "\1\10\1\0\3\10\2\0\5\10\1\44\20\10\5\0"+
    "\1\10\1\0\3\10\2\0\5\10\1\45\4\10\1\46"+
    "\13\10\5\0\1\10\1\0\3\10\2\0\7\10\1\47"+
    "\16\10\5\0\1\10\1\0\3\10\2\0\12\10\1\50"+
    "\2\10\1\51\10\10\5\0\1\10\1\0\3\10\2\0"+
    "\15\10\1\52\10\10\5\0\1\10\1\0\3\10\2\0"+
    "\20\10\1\53\5\10\5\0\1\10\1\0\3\10\2\0"+
    "\12\10\1\54\13\10\5\0\1\10\1\0\3\10\2\0"+
    "\7\10\1\55\10\10\1\56\5\10\5\0\1\10\1\0"+
    "\3\10\2\0\16\10\1\57\4\10\1\60\2\10\5\0"+
    "\1\10\1\0\3\10\2\0\1\61\25\10\5\0\1\10"+
    "\1\0\3\10\2\0\15\10\1\62\10\10\15\0\1\63"+
    "\20\0\1\64\10\0\1\34\1\0\3\34\2\0\26\34"+
    "\5\0\1\34\1\0\3\34\2\0\13\34\1\65\12\34"+
    "\5\0\1\34\1\0\3\34\2\0\20\34\1\66\5\34"+
    "\5\0\1\34\1\0\3\34\2\0\1\67\25\34\1\0"+
    "\1\70\2\0\37\70\4\0\1\10\1\0\3\10\2\0"+
    "\1\10\1\71\24\10\5\0\1\10\1\0\3\10\2\0"+
    "\1\10\1\72\24\10\5\0\1\10\1\0\3\10\2\0"+
    "\3\10\1\73\4\10\1\74\15\10\5\0\1\10\1\0"+
    "\3\10\2\0\16\10\1\75\7\10\5\0\1\10\1\0"+
    "\3\10\2\0\6\10\1\76\17\10\5\0\1\10\1\0"+
    "\3\10\2\0\1\10\1\77\24\10\5\0\1\10\1\0"+
    "\3\10\2\0\20\10\1\100\5\10\5\0\1\10\1\0"+
    "\3\10\2\0\7\10\1\101\16\10\5\0\1\10\1\0"+
    "\3\10\2\0\2\10\1\102\23\10\5\0\1\10\1\0"+
    "\3\10\2\0\7\10\1\103\16\10\5\0\1\10\1\0"+
    "\3\10\2\0\7\10\1\104\16\10\5\0\1\10\1\0"+
    "\3\10\2\0\20\10\1\105\5\10\5\0\1\10\1\0"+
    "\3\10\2\0\7\10\1\106\16\10\5\0\1\10\1\0"+
    "\3\10\2\0\1\10\1\107\24\10\5\0\1\10\1\0"+
    "\3\10\2\0\24\10\1\110\1\10\5\0\1\10\1\0"+
    "\3\10\2\0\6\10\1\111\17\10\5\0\1\10\1\0"+
    "\3\10\2\0\13\10\1\112\12\10\5\0\1\34\1\0"+
    "\3\34\2\0\2\34\1\113\1\34\1\114\5\34\1\115"+
    "\3\34\1\116\1\117\2\34\1\120\3\34\5\0\1\34"+
    "\1\0\3\34\2\0\7\34\1\121\16\34\5\0\1\34"+
    "\1\0\3\34\2\0\6\34\1\122\17\34\5\0\1\10"+
    "\1\0\3\10\2\0\3\10\1\123\22\10\5\0\1\10"+
    "\1\0\3\10\2\0\22\10\1\124\3\10\5\0\1\10"+
    "\1\0\3\10\2\0\7\10\1\125\16\10\5\0\1\10"+
    "\1\0\3\10\2\0\16\10\1\126\7\10\5\0\1\10"+
    "\1\0\3\10\2\0\13\10\1\127\12\10\5\0\1\10"+
    "\1\0\3\10\2\0\13\10\1\130\12\10\5\0\1\10"+
    "\1\0\3\10\2\0\6\10\1\131\17\10\5\0\1\10"+
    "\1\0\3\10\2\0\2\10\1\132\23\10\5\0\1\34"+
    "\1\0\3\34\2\0\17\34\1\133\6\34\5\0\1\34"+
    "\1\0\3\34\2\0\1\134\17\34\1\135\5\34\5\0"+
    "\1\34\1\0\3\34\2\0\5\34\1\136\20\34\5\0"+
    "\1\34\1\0\3\34\2\0\12\34\1\137\13\34\5\0"+
    "\1\34\1\0\3\34\2\0\20\34\1\140\5\34\5\0"+
    "\1\34\1\0\3\34\2\0\12\34\1\141\13\34\5\0"+
    "\1\34\1\0\3\34\2\0\22\34\1\142\3\34\5\0"+
    "\1\10\1\0\3\10\2\0\7\10\1\143\16\10\5\0"+
    "\1\10\1\0\3\10\2\0\7\10\1\144\16\10\5\0"+
    "\1\10\1\0\3\10\2\0\6\10\1\145\10\10\1\146"+
    "\6\10\5\0\1\10\1\0\3\10\2\0\6\10\1\147"+
    "\10\10\1\150\6\10\5\0\1\10\1\0\3\10\2\0"+
    "\3\10\1\151\22\10\5\0\1\34\1\0\3\34\2\0"+
    "\5\34\1\152\20\34\5\0\1\34\1\0\3\34\2\0"+
    "\21\34\1\153\4\34\5\0\1\34\1\0\3\34\2\0"+
    "\16\34\1\154\7\34\5\0\1\34\1\0\3\34\2\0"+
    "\7\34\1\155\16\34\5\0\1\34\1\0\3\34\2\0"+
    "\2\34\1\156\23\34\5\0\1\34\1\0\3\34\2\0"+
    "\7\34\1\157\16\34\5\0\1\34\1\0\3\34\2\0"+
    "\16\34\1\160\7\34\5\0\1\10\1\0\3\10\2\0"+
    "\2\10\1\161\23\10\5\0\1\10\1\0\3\10\2\0"+
    "\12\10\1\162\13\10\5\0\1\10\1\0\3\10\2\0"+
    "\6\10\1\163\17\10\5\0\1\10\1\0\3\10\2\0"+
    "\12\10\1\164\13\10\5\0\1\10\1\0\3\10\2\0"+
    "\6\10\1\165\17\10\5\0\1\34\1\0\3\34\2\0"+
    "\1\166\25\34\5\0\1\34\1\0\3\34\2\0\12\34"+
    "\1\167\13\34\5\0\1\34\1\0\3\34\2\0\12\34"+
    "\1\170\13\34\5\0\1\34\1\0\3\34\2\0\14\34"+
    "\1\171\11\34\5\0\1\34\1\0\3\34\2\0\17\34"+
    "\1\172\6\34\5\0\1\34\1\0\3\34\2\0\2\34"+
    "\1\173\11\34\1\174\11\34\5\0\1\34\1\0\3\34"+
    "\2\0\22\34\1\175\3\34\5\0\1\10\1\0\3\10"+
    "\2\0\12\10\1\176\13\10\5\0\1\10\1\0\3\10"+
    "\2\0\22\10\1\177\3\10\5\0\1\10\1\0\3\10"+
    "\2\0\12\10\1\200\13\10\5\0\1\10\1\0\3\10"+
    "\2\0\22\10\1\201\3\10\5\0\1\10\1\0\3\10"+
    "\2\0\12\10\1\202\13\10\5\0\1\34\1\0\3\34"+
    "\2\0\6\34\1\203\17\34\5\0\1\34\1\0\3\34"+
    "\2\0\15\34\1\204\10\34\5\0\1\34\1\0\3\34"+
    "\2\0\15\34\1\205\10\34\5\0\1\34\1\0\3\34"+
    "\2\0\7\34\1\206\16\34\5\0\1\34\1\0\3\34"+
    "\2\0\5\34\1\207\20\34\5\0\1\34\1\0\3\34"+
    "\2\0\16\34\1\210\7\34\5\0\1\34\1\0\3\34"+
    "\2\0\7\34\1\211\16\34\5\0\1\34\1\0\3\34"+
    "\2\0\1\212\25\34\5\0\1\10\1\0\3\10\2\0"+
    "\16\10\1\213\7\10\5\0\1\10\1\0\3\10\2\0"+
    "\1\214\25\10\5\0\1\10\1\0\3\10\2\0\25\10"+
    "\1\215\5\0\1\10\1\0\3\10\2\0\1\216\25\10"+
    "\5\0\1\10\1\0\3\10\2\0\25\10\1\217\5\0"+
    "\1\34\1\0\3\34\2\0\2\34\1\220\23\34\5\0"+
    "\1\34\1\0\3\34\2\0\2\34\1\221\23\34\5\0"+
    "\1\34\1\0\3\34\2\0\2\34\1\222\23\34\5\0"+
    "\1\34\1\0\3\34\2\0\15\34\1\223\10\34\5\0"+
    "\1\34\1\0\3\34\2\0\1\224\25\34\5\0\1\34"+
    "\1\0\3\34\2\0\7\34\1\225\16\34\5\0\1\34"+
    "\1\0\3\34\2\0\15\34\1\226\10\34\5\0\1\34"+
    "\1\0\3\34\2\0\7\34\1\227\16\34\5\0\1\10"+
    "\1\0\3\10\2\0\6\10\1\230\17\10\5\0\1\10"+
    "\1\0\3\10\2\0\1\231\25\10\5\0\1\10\1\0"+
    "\3\10\2\0\6\10\1\232\17\10\5\0\1\10\1\0"+
    "\3\10\2\0\1\233\25\10\5\0\1\34\1\0\3\34"+
    "\2\0\17\34\1\234\6\34\5\0\1\34\1\0\3\34"+
    "\2\0\17\34\1\235\6\34\5\0\1\34\1\0\3\34"+
    "\2\0\17\34\1\236\6\34\5\0\1\34\1\0\3\34"+
    "\2\0\6\34\1\237\17\34\5\0\1\10\1\0\3\10"+
    "\2\0\6\10\1\240\17\10\5\0\1\10\1\0\3\10"+
    "\2\0\6\10\1\241\17\10\5\0\1\34\1\0\3\34"+
    "\2\0\5\34\1\242\20\34\5\0\1\34\1\0\3\34"+
    "\2\0\5\34\1\243\20\34\5\0\1\34\1\0\3\34"+
    "\2\0\5\34\1\244\20\34\5\0\1\34\1\0\3\34"+
    "\2\0\1\245\25\34\5\0\1\34\1\0\3\34\2\0"+
    "\1\246\25\34\5\0\1\34\1\0\3\34\2\0\1\247"+
    "\25\34\5\0\1\34\1\0\3\34\2\0\6\34\1\250"+
    "\17\34\5\0\1\34\1\0\3\34\2\0\6\34\1\251"+
    "\17\34\5\0\1\34\1\0\3\34\2\0\6\34\1\252"+
    "\17\34\1\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4488];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\1\1\11\2\1\1\11\5\1\1\11\16\1\1\0"+
    "\5\1\1\0\22\1\2\11\166\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[170];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
   


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public LexicoDasm(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public LexicoDasm(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 2270) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 23: 
          { return new Symbol(sym.EQZ, new token(yycolumn, yyline, yytext()));
          }
        case 46: break;
        case 35: 
          { return new Symbol(sym.GETINT, new token(yycolumn, yyline, yytext()));
          }
        case 47: break;
        case 31: 
          { return new Symbol(sym.MAYOR, new token(yycolumn, yyline, yytext()));
          }
        case 48: break;
        case 8: 
          { return new Symbol(sym.LT, new token(yycolumn, yyline, yytext()));
          }
        case 49: break;
        case 45: 
          { return new Symbol(sym.MENORIGUAL, new token(yycolumn, yyline, yytext()));
          }
        case 50: break;
        case 20: 
          { return new Symbol(sym.NOT, new token(yycolumn, yyline, yytext()));
          }
        case 51: break;
        case 14: 
          { return new Symbol(sym.ADD, new token(yycolumn, yyline, yytext()));
          }
        case 52: break;
        case 17: 
          { return new Symbol(sym.MOD, new token(yycolumn, yyline, yytext()));
          }
        case 53: break;
        case 29: 
          { return new Symbol(sym.PRINT, new token(yycolumn, yyline, yytext()));
          }
        case 54: break;
        case 41: 
          { return new Symbol(sym.SET_GLOBAL, new token(yycolumn, yyline, yytext()));
          }
        case 55: break;
        case 15: 
          { return new Symbol(sym.AND, new token(yycolumn, yyline, yytext()));
          }
        case 56: break;
        case 37: 
          { return new Symbol(sym.CONCAT, new token(yycolumn, yyline, yytext()));
          }
        case 57: break;
        case 36: 
          { return new Symbol(sym.GETSTR, new token(yycolumn, yyline, yytext()));
          }
        case 58: break;
        case 3: 
          { /* ignorar */
          }
        case 59: break;
        case 11: 
          { return new Symbol(sym.BR, new token(yycolumn, yyline, yytext()));
          }
        case 60: break;
        case 6: 
          { return new Symbol(sym.SIG_MENOS, new token(yycolumn, yyline, yytext()));
          }
        case 61: break;
        case 5: 
          { return new Symbol(sym.ID, new token(yycolumn, yyline, yytext()));
          }
        case 62: break;
        case 7: 
          { return new Symbol(sym.ETIQUETA, new token(yycolumn, yyline, yytext()));
          }
        case 63: break;
        case 27: 
          { return new Symbol(sym.CALL, new token(yycolumn, yyline, yytext()));
          }
        case 64: break;
        case 1: 
          { return new Symbol(sym.DECIMAL, new token(yycolumn, yyline, yytext()));
          }
        case 65: break;
        case 22: 
          { return new Symbol(sym.END, new token(yycolumn, yyline, yytext()));
          }
        case 66: break;
        case 42: 
          { return new Symbol(sym.GET_GLOBAL, new token(yycolumn, yyline, yytext()));
          }
        case 67: break;
        case 39: 
          { return new Symbol(sym.GET_LOCAL, new token(yycolumn, yyline, yytext()));
          }
        case 68: break;
        case 10: 
          { return new Symbol(sym.GT, new token(yycolumn, yyline, yytext()));
          }
        case 69: break;
        case 34: 
          { return new Symbol(sym.OUT_STR, new token(yycolumn, yyline, yytext()));
          }
        case 70: break;
        case 19: 
          { return new Symbol(sym.POT, new token(yycolumn, yyline, yytext()));
          }
        case 71: break;
        case 13: 
          { return new Symbol(sym.F_CHAR, new token(yycolumn, yyline, yytext()));
          }
        case 72: break;
        case 44: 
          { return new Symbol(sym.MAYORIGUAL, new token(yycolumn, yyline, yytext()));
          }
        case 73: break;
        case 32: 
          { return new Symbol(sym.MENOR, new token(yycolumn, yyline, yytext()));
          }
        case 74: break;
        case 40: 
          { return new Symbol(sym.NOIGUAL, new token(yycolumn, yyline, yytext()));
          }
        case 75: break;
        case 18: 
          { return new Symbol(sym.LTE, new token(yycolumn, yyline, yytext()));
          }
        case 76: break;
        case 28: 
          { return new Symbol(sym.C4LC, new token(yycolumn, yyline, yytext()));
          }
        case 77: break;
        case 38: 
          { return new Symbol(sym.SET_LOCAL, new token(yycolumn, yyline, yytext()));
          }
        case 78: break;
        case 33: 
          { return new Symbol(sym.FUNCTION, new token(yycolumn, yyline, yytext()));
          }
        case 79: break;
        case 21: 
          { return new Symbol(sym.GTE, new token(yycolumn, yyline, yytext()));
          }
        case 80: break;
        case 43: 
          { return new Symbol(sym.IGUALIGUAL, new token(yycolumn, yyline, yytext()));
          }
        case 81: break;
        case 16: 
          { return new Symbol(sym.DIV, new token(yycolumn, yyline, yytext()));
          }
        case 82: break;
        case 4: 
          { return new Symbol(sym.NUMERO, new token(yycolumn, yyline, yytext()));
          }
        case 83: break;
        case 9: 
          { return new Symbol(sym.OR, new token(yycolumn, yyline, yytext()));
          }
        case 84: break;
        case 12: 
          { return new Symbol(sym.D_CHAR, new token(yycolumn, yyline, yytext()));
          }
        case 85: break;
        case 30: 
          { return new Symbol(sym.BR_IF, new token(yycolumn, yyline, yytext()));
          }
        case 86: break;
        case 26: 
          { return new Symbol(sym.MULT, new token(yycolumn, yyline, yytext()));
          }
        case 87: break;
        case 25: 
          { return new Symbol(sym.DIFF, new token(yycolumn, yyline, yytext()));
          }
        case 88: break;
        case 2: 
          { System.err.println(yyline+","+yycolumn+"=["+yytext()+"],"+yychar);
          }
        case 89: break;
        case 24: 
          { return new Symbol(sym.R3T, new token(yycolumn, yyline, yytext()));
          }
        case 90: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new java_cup.runtime.Symbol(sym.EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}