/* The following code was generated by JFlex 1.6.0 */

package Gramatica_DASM;
import Estructuras_Ide.Errores;
import java.util.LinkedList;
import java_cup.runtime.*;

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.0
 * from the specification file <tt>lexico_DASM.jflex</tt>
 */
public class lexico_DASM implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  private static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  private static final int YYINITIAL = 0;

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
    "\11\0\1\35\1\35\1\36\1\35\1\35\22\0\1\35\3\0\1\4"+
    "\1\34\10\0\1\3\1\0\12\1\7\0\1\5\1\26\1\30\1\6"+
    "\1\21\1\11\1\20\1\2\1\10\2\2\1\14\1\12\1\24\1\17"+
    "\1\33\1\22\1\25\1\32\1\15\1\13\1\16\3\2\1\23\4\0"+
    "\1\27\1\0\1\5\1\26\1\30\1\6\1\21\1\11\1\20\1\2"+
    "\1\10\2\2\1\14\1\12\1\24\1\17\1\33\1\22\1\25\1\32"+
    "\1\15\1\13\1\16\3\2\1\23\12\0\1\36\252\0\2\7\115\0"+
    "\1\31\u1ea8\0\1\36\1\36\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\16\3\1\1\2\3"+
    "\1\1\1\5\1\0\2\3\1\0\4\3\1\0\1\3"+
    "\1\6\2\3\1\7\1\10\6\3\1\11\1\3\2\0"+
    "\4\3\1\12\1\13\1\14\1\15\1\16\1\17\1\0"+
    "\1\20\1\3\1\20\2\3\1\21\1\0\1\3\1\22"+
    "\2\3\1\23\1\3\1\24\1\25\1\3\1\26\1\27"+
    "\1\0\1\3\2\0\2\3\1\0\1\3\1\0\1\3"+
    "\2\30\1\3\1\31\2\32\1\0\1\33\1\0\1\3"+
    "\1\0\1\34\1\35\2\0\1\3\1\0\1\3\1\0"+
    "\2\3\4\0\1\3\1\36\3\0\1\3\2\37\1\40"+
    "\1\0\1\40\1\3\1\0\1\3\4\0\1\3\1\41"+
    "\2\0\1\41\1\0\1\3\1\0\1\3\4\0\1\3"+
    "\3\0\1\3\2\42\4\0\1\43\3\0\1\3\1\44"+
    "\1\0\1\45\1\0\1\46\1\0\2\47\1\50\1\51"+
    "\1\52";

  private static int [] zzUnpackAction() {
    int [] result = new int[171];
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
    "\0\0\0\37\0\76\0\135\0\37\0\174\0\233\0\272"+
    "\0\331\0\370\0\u0117\0\u0136\0\u0155\0\u0174\0\u0193\0\u01b2"+
    "\0\u01d1\0\u01f0\0\u020f\0\u022e\0\u024d\0\u026c\0\u028b\0\37"+
    "\0\u02aa\0\u02c9\0\u02e8\0\u0307\0\u0326\0\u0345\0\u0364\0\u0383"+
    "\0\u03a2\0\u03c1\0\u03e0\0\u03ff\0\u041e\0\135\0\u043d\0\u045c"+
    "\0\u047b\0\u049a\0\u04b9\0\u04d8\0\u04f7\0\u0516\0\u0535\0\u0554"+
    "\0\u0573\0\u0592\0\u05b1\0\u05d0\0\u05ef\0\37\0\37\0\37"+
    "\0\u02aa\0\135\0\135\0\u060e\0\37\0\u062d\0\135\0\u064c"+
    "\0\u066b\0\135\0\u068a\0\u06a9\0\135\0\u06c8\0\u06e7\0\135"+
    "\0\u0706\0\135\0\135\0\u0725\0\135\0\135\0\u0744\0\u0763"+
    "\0\u0782\0\u07a1\0\u07c0\0\u07df\0\u07fe\0\u081d\0\u083c\0\u085b"+
    "\0\37\0\135\0\u087a\0\135\0\37\0\135\0\u0899\0\135"+
    "\0\u08b8\0\u08d7\0\u08f6\0\135\0\135\0\u0915\0\u0934\0\u0953"+
    "\0\u0972\0\u0991\0\u09b0\0\u09cf\0\u09ee\0\u0a0d\0\u0a2c\0\u0a4b"+
    "\0\u0a6a\0\u0a89\0\37\0\u0aa8\0\u0ac7\0\u0ae6\0\u0b05\0\37"+
    "\0\135\0\37\0\u0b24\0\135\0\u0b43\0\u0b62\0\u0b81\0\u0ba0"+
    "\0\u0bbf\0\u0bde\0\u0bfd\0\u0c1c\0\37\0\u0c3b\0\u0c5a\0\135"+
    "\0\u0c79\0\u0c98\0\u0cb7\0\u0cd6\0\u0cf5\0\u0d14\0\u0d33\0\u0d52"+
    "\0\u0d71\0\u0d90\0\u0daf\0\u0dce\0\u0ded\0\37\0\135\0\u0e0c"+
    "\0\u0e2b\0\u0e4a\0\u0e69\0\135\0\u0e88\0\u0ea7\0\u0ec6\0\u0ee5"+
    "\0\37\0\u0f04\0\37\0\u0f23\0\37\0\u0f42\0\37\0\135"+
    "\0\37\0\37\0\37";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[171];
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
    "\1\2\1\3\1\4\1\2\1\5\1\6\1\7\1\2"+
    "\1\4\1\10\1\11\1\4\1\12\1\13\1\4\1\14"+
    "\1\15\1\16\1\17\1\4\1\20\1\21\1\22\1\2"+
    "\1\23\1\24\1\25\1\26\1\27\1\30\41\0\1\3"+
    "\1\4\1\31\1\0\2\4\1\0\17\4\1\0\1\4"+
    "\1\0\2\4\4\0\2\4\2\0\2\4\1\0\17\4"+
    "\1\0\1\4\1\0\2\4\4\0\2\4\2\0\1\4"+
    "\1\32\1\0\14\4\1\33\2\4\1\0\1\4\1\0"+
    "\2\4\4\0\2\4\2\0\2\4\1\34\1\35\16\4"+
    "\1\0\1\4\1\0\2\4\4\0\2\4\2\0\2\4"+
    "\1\0\3\4\1\36\13\4\1\0\1\4\1\0\2\4"+
    "\4\0\2\4\2\0\2\4\1\0\3\4\1\37\3\4"+
    "\1\40\7\4\1\0\1\4\1\0\2\4\4\0\2\4"+
    "\2\0\2\4\1\41\1\42\4\4\1\43\11\4\1\0"+
    "\1\4\1\0\2\4\4\0\2\4\2\0\2\4\1\0"+
    "\11\4\1\44\5\4\1\0\1\4\1\0\2\4\4\0"+
    "\2\4\2\0\2\4\1\0\6\4\1\45\6\4\1\46"+
    "\1\4\1\0\1\4\1\0\2\4\4\0\2\4\2\0"+
    "\2\4\1\0\5\4\1\47\3\4\1\50\5\4\1\0"+
    "\1\4\1\0\2\4\4\0\2\4\2\0\2\4\1\0"+
    "\12\4\1\51\1\4\1\52\2\4\1\0\1\4\1\0"+
    "\2\4\4\0\2\4\2\0\2\4\1\0\3\4\1\53"+
    "\13\4\1\0\1\4\1\0\2\4\4\0\2\4\2\0"+
    "\2\4\1\0\7\4\1\54\7\4\1\0\1\4\1\0"+
    "\2\4\4\0\2\4\2\0\2\4\1\0\11\4\1\55"+
    "\5\4\1\0\1\4\1\0\2\4\4\0\2\4\2\0"+
    "\2\4\1\0\15\4\1\56\1\4\1\0\1\4\1\0"+
    "\2\4\4\0\2\4\2\0\1\57\1\4\1\0\17\4"+
    "\1\0\1\4\1\0\2\4\20\0\1\60\3\0\1\61"+
    "\16\0\2\4\2\0\2\4\1\0\5\4\1\62\3\4"+
    "\1\63\5\4\1\0\1\4\1\0\2\4\4\0\2\4"+
    "\2\0\2\4\1\0\7\4\1\64\5\4\1\65\1\4"+
    "\1\0\1\4\1\0\2\4\11\0\1\66\2\0\1\67"+
    "\16\0\1\70\7\0\1\71\36\0\2\4\2\0\1\4"+
    "\1\72\1\0\17\4\1\0\1\4\1\0\2\4\4\0"+
    "\2\4\2\0\1\4\1\73\1\0\17\4\1\0\1\4"+
    "\1\0\2\4\14\0\1\74\4\0\1\75\21\0\2\4"+
    "\2\0\2\4\1\0\1\4\1\76\4\4\1\77\10\4"+
    "\1\0\1\4\1\0\2\4\4\0\2\4\2\0\2\4"+
    "\1\0\14\4\1\100\2\4\1\0\1\4\1\0\2\4"+
    "\4\0\2\4\2\0\2\4\1\0\4\4\1\101\12\4"+
    "\1\0\1\4\1\0\2\4\4\0\2\4\2\0\1\4"+
    "\1\102\1\0\17\4\1\0\1\4\1\0\2\4\27\0"+
    "\1\103\13\0\2\4\2\0\2\4\1\0\14\4\1\104"+
    "\2\4\1\0\1\4\1\0\2\4\4\0\2\4\2\0"+
    "\2\4\1\0\11\4\1\105\5\4\1\0\1\4\1\0"+
    "\2\4\4\0\2\4\2\0\2\4\1\0\11\4\1\106"+
    "\5\4\1\0\1\4\1\0\2\4\4\0\2\4\2\0"+
    "\1\107\1\4\1\0\17\4\1\0\1\4\1\0\2\4"+
    "\4\0\2\4\2\0\2\4\1\0\11\4\1\110\5\4"+
    "\1\0\1\4\1\0\2\4\4\0\2\4\2\0\2\4"+
    "\1\0\5\4\1\111\11\4\1\0\1\4\1\0\2\4"+
    "\4\0\2\4\2\0\2\4\1\0\13\4\1\112\3\4"+
    "\1\0\1\4\1\0\2\4\4\0\2\4\2\0\1\4"+
    "\1\113\1\0\17\4\1\0\1\4\1\0\2\4\4\0"+
    "\2\4\2\0\1\114\1\4\1\0\17\4\1\0\1\4"+
    "\1\0\2\4\4\0\2\4\2\0\2\4\1\0\5\4"+
    "\1\115\11\4\1\0\1\4\1\0\2\4\4\0\2\4"+
    "\2\0\2\4\1\0\5\4\1\116\11\4\1\0\1\4"+
    "\1\0\2\4\4\0\2\4\2\0\2\4\1\0\17\4"+
    "\1\117\1\4\1\0\2\4\4\0\2\4\2\0\2\4"+
    "\1\0\4\4\1\120\12\4\1\0\1\4\1\0\2\4"+
    "\30\0\1\121\26\0\1\122\22\0\2\4\2\0\2\4"+
    "\1\0\15\4\1\123\1\4\1\0\1\4\1\0\2\4"+
    "\4\0\2\4\2\0\2\4\1\0\5\4\1\124\11\4"+
    "\1\0\1\4\1\0\2\4\4\0\2\4\2\0\2\4"+
    "\1\125\1\126\16\4\1\0\1\4\1\0\2\4\4\0"+
    "\2\4\2\0\2\4\1\127\1\130\16\4\1\0\1\4"+
    "\1\0\2\4\14\0\1\131\26\0\2\4\2\0\2\4"+
    "\1\0\1\4\1\132\15\4\1\0\1\4\1\0\2\4"+
    "\4\0\2\4\2\0\2\4\1\0\17\4\1\0\1\133"+
    "\1\0\2\4\4\0\2\4\2\0\2\4\1\0\5\4"+
    "\1\134\11\4\1\0\1\4\1\0\2\4\24\0\1\135"+
    "\16\0\2\4\2\0\2\4\1\0\11\4\1\136\5\4"+
    "\1\0\1\4\1\0\2\4\4\0\2\4\2\0\2\4"+
    "\1\0\17\4\1\137\1\4\1\0\2\4\4\0\2\4"+
    "\2\0\2\4\1\0\4\4\1\140\12\4\1\0\1\4"+
    "\1\0\2\4\4\0\2\4\2\0\2\4\1\0\17\4"+
    "\1\141\1\4\1\0\2\4\4\0\2\4\2\0\1\4"+
    "\1\142\1\0\17\4\1\0\1\4\1\0\2\4\12\0"+
    "\2\143\27\0\2\4\2\0\2\4\1\0\4\4\1\144"+
    "\12\4\1\0\1\145\1\0\2\4\12\0\2\146\55\0"+
    "\1\147\10\0\2\4\2\0\2\4\1\146\1\150\16\4"+
    "\1\0\1\4\1\0\2\4\4\0\2\4\2\0\2\4"+
    "\1\0\17\4\1\147\1\4\1\0\2\4\27\0\1\151"+
    "\13\0\2\4\2\0\2\4\1\0\14\4\1\152\2\4"+
    "\1\0\1\4\1\0\2\4\27\0\1\153\13\0\2\4"+
    "\2\0\2\4\1\0\14\4\1\154\2\4\1\0\1\4"+
    "\1\0\2\4\4\0\2\4\2\0\2\4\1\0\5\4"+
    "\1\155\11\4\1\0\1\4\1\0\2\4\17\0\1\156"+
    "\3\0\1\157\32\0\1\160\3\0\1\161\17\0\2\4"+
    "\2\0\2\4\1\0\15\4\1\162\1\4\1\0\1\4"+
    "\1\0\2\4\14\0\1\163\51\0\1\164\26\0\1\165"+
    "\3\0\1\166\17\0\2\4\2\0\2\4\1\0\14\4"+
    "\1\167\2\4\1\0\1\4\1\0\2\4\20\0\1\170"+
    "\22\0\2\4\2\0\2\4\1\0\5\4\1\171\11\4"+
    "\1\0\1\4\1\0\2\4\20\0\1\172\12\0\1\173"+
    "\7\0\2\4\2\0\2\4\1\0\5\4\1\174\11\4"+
    "\1\0\1\175\1\0\2\4\4\0\2\4\2\0\2\4"+
    "\1\176\1\177\16\4\1\0\1\4\1\0\2\4\22\0"+
    "\1\200\33\0\1\201\41\0\1\202\33\0\1\203\23\0"+
    "\2\4\2\0\1\204\1\4\1\0\17\4\1\0\1\4"+
    "\1\0\2\4\23\0\1\205\35\0\1\206\33\0\1\207"+
    "\23\0\2\4\2\0\2\4\1\0\10\4\1\210\6\4"+
    "\1\0\1\4\1\0\2\4\12\0\2\211\27\0\2\4"+
    "\2\0\2\4\1\211\1\212\16\4\1\0\1\4\1\0"+
    "\2\4\22\0\1\213\20\0\2\4\2\0\2\4\1\0"+
    "\7\4\1\214\7\4\1\0\1\4\1\0\2\4\33\0"+
    "\1\215\25\0\1\216\47\0\1\217\25\0\1\220\20\0"+
    "\2\4\2\0\2\4\1\0\5\4\1\221\11\4\1\0"+
    "\1\4\1\0\2\4\33\0\1\222\25\0\1\223\52\0"+
    "\1\224\4\0\2\4\2\0\2\4\1\0\17\4\1\0"+
    "\1\4\1\0\1\4\1\225\27\0\1\226\13\0\2\4"+
    "\2\0\2\4\1\0\14\4\1\227\2\4\1\0\1\4"+
    "\1\0\2\4\10\0\1\230\57\0\1\231\15\0\1\232"+
    "\57\0\1\233\11\0\2\4\2\0\2\4\1\0\11\4"+
    "\1\234\5\4\1\0\1\4\1\0\2\4\10\0\1\235"+
    "\57\0\1\236\15\0\1\237\32\0\2\4\2\0\1\240"+
    "\1\4\1\0\17\4\1\0\1\4\1\0\2\4\17\0"+
    "\1\241\27\0\1\242\45\0\1\243\27\0\1\244\45\0"+
    "\1\245\27\0\1\246\45\0\1\247\23\0\2\4\2\0"+
    "\2\4\1\0\4\4\1\250\12\4\1\0\1\4\1\0"+
    "\2\4\17\0\1\251\36\0\1\252\36\0\1\253\22\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3937];
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
    "\1\0\1\11\2\1\1\11\22\1\1\11\1\0\2\1"+
    "\1\0\4\1\1\0\16\1\2\0\4\1\3\11\3\1"+
    "\1\0\1\11\5\1\1\0\13\1\1\0\1\1\2\0"+
    "\2\1\1\0\1\1\1\0\1\1\1\11\3\1\1\11"+
    "\1\1\1\0\1\1\1\0\1\1\1\0\2\1\2\0"+
    "\1\1\1\0\1\1\1\0\2\1\4\0\1\1\1\11"+
    "\3\0\1\1\1\11\1\1\1\11\1\0\2\1\1\0"+
    "\1\1\4\0\1\1\1\11\2\0\1\1\1\0\1\1"+
    "\1\0\1\1\4\0\1\1\3\0\1\1\1\11\1\1"+
    "\4\0\1\1\3\0\1\1\1\11\1\0\1\11\1\0"+
    "\1\11\1\0\1\11\1\1\3\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[171];
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
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
public static String Rut;
public LinkedList<Errores>errores_lexicos=new LinkedList<Errores>();


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public lexico_DASM(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 182) {
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
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
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
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;           
    int totalRead = 0;
    while (totalRead < requested) {
      int numRead = zzReader.read(zzBuffer, zzEndRead + totalRead, requested - totalRead);
      if (numRead == -1) {
        break;
      }
      totalRead += numRead;
    }

    if (totalRead > 0) {
      zzEndRead += totalRead;
      if (totalRead == requested) { /* possibly more input available */
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      return false;
    }

    // totalRead = 0: End of stream
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  private final void yyclose() throws java.io.IOException {
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
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  private final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  private final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  private final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  private final String yytext() {
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
  private final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  private final int yylength() {
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
  private void yypushback(int number)  {
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

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
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
          yycolumn += zzCharCount;
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

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
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
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
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
        case 1: 
          { Errores error=new Errores("Error Lexico!!","Error Lexico",(yyline+1),(yycolumn+1),yytext());
    errores_lexicos.add(error);
    System.out.println("Linea: " + (yyline+1) + " Columna: " + (yycolumn+1) + " - Error Lexico en: " + yytext());
          }
        case 43: break;
        case 2: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.numero_completo, yyline, yycolumn,yytext());
          }
        case 44: break;
        case 3: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.palabra, yyline, yycolumn,yytext());
          }
        case 45: break;
        case 4: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.dolar, yyline, yycolumn,yytext());
          }
        case 46: break;
        case 5: 
          { /* ignore white space. */
          }
        case 47: break;
        case 6: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.lt, yyline, yycolumn,yytext());
          }
        case 48: break;
        case 7: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.or, yyline, yycolumn,yytext());
          }
        case 49: break;
        case 8: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.gt, yyline, yycolumn,yytext());
          }
        case 50: break;
        case 9: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.br, yyline, yycolumn,yytext());
          }
        case 51: break;
        case 10: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.i_entero, yyline, yycolumn,yytext());
          }
        case 52: break;
        case 11: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.i_flotante, yyline, yycolumn,yytext());
          }
        case 53: break;
        case 12: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.i_caracter, yyline, yycolumn,yytext());
          }
        case 54: break;
        case 13: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.numero_decimal, yyline, yycolumn,yytext());
          }
        case 55: break;
        case 14: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.add, yyline, yycolumn,yytext());
          }
        case 56: break;
        case 15: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.and, yyline, yycolumn,yytext());
          }
        case 57: break;
        case 16: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.div, yyline, yycolumn,yytext());
          }
        case 58: break;
        case 17: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.mod, yyline, yycolumn,yytext());
          }
        case 59: break;
        case 18: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.lte, yyline, yycolumn,yytext());
          }
        case 60: break;
        case 19: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.gte, yyline, yycolumn,yytext());
          }
        case 61: break;
        case 20: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.eqz, yyline, yycolumn,yytext());
          }
        case 62: break;
        case 21: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.end, yyline, yycolumn,yytext());
          }
        case 63: break;
        case 22: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.not, yyline, yycolumn,yytext());
          }
        case 64: break;
        case 23: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.retorno, yyline, yycolumn,yytext());
          }
        case 65: break;
        case 24: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.diff, yyline, yycolumn,yytext());
          }
        case 66: break;
        case 25: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.mult, yyline, yycolumn,yytext());
          }
        case 67: break;
        case 26: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.line, yyline, yycolumn,yytext());
          }
        case 68: break;
        case 27: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.oval, yyline, yycolumn,yytext());
          }
        case 69: break;
        case 28: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.call, yyline, yycolumn,yytext());
          }
        case 70: break;
        case 29: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.calc, yyline, yycolumn,yytext());
          }
        case 71: break;
        case 30: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.br_if, yyline, yycolumn,yytext());
          }
        case 72: break;
        case 31: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.point, yyline, yycolumn,yytext());
          }
        case 73: break;
        case 32: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.print, yyline, yycolumn,yytext());
          }
        case 74: break;
        case 33: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.texto, yyline, yycolumn,yytext());
          }
        case 75: break;
        case 34: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.function, yyline, yycolumn,yytext());
          }
        case 76: break;
        case 35: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.quadrate, yyline, yycolumn,yytext());
          }
        case 77: break;
        case 36: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.tee_local, yyline, yycolumn,yytext());
          }
        case 78: break;
        case 37: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.get_local, yyline, yycolumn,yytext());
          }
        case 79: break;
        case 38: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.set_local, yyline, yycolumn,yytext());
          }
        case 80: break;
        case 39: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.principal, yyline, yycolumn,yytext());
          }
        case 81: break;
        case 40: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.tee_global, yyline, yycolumn,yytext());
          }
        case 82: break;
        case 41: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.get_global, yyline, yycolumn,yytext());
          }
        case 83: break;
        case 42: 
          { System.out.print(yytext()); return new Symbol(simbolo_DASM.set_global, yyline, yycolumn,yytext());
          }
        case 84: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { 	return null;
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
