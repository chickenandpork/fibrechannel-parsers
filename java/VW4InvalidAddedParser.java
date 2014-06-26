//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package org.smallfoot.parser.zone;



//#line 2 "vw4invalid.y"
import java.lang.Math;
import java.io.*;

/**
 * @page formats Compatible File Formats
 * @subpage VW4InvalidAdded
 *
 * @page VW4InvalidAdded VirtualWisdom4 Validation Errors
 *
 * The VW4InvalidAddedParser interprets the error content of a VirtualWisdom4 Entity validation exception
 *
 * a VirtualWisdom4 Validation error message is typically a long set of multi-line messages such as:
 * @verbatim
Location: [Line : 1234 , Column : 7]
Invalid added child entities : 20c2000dec123456
Entity: Cisco-000dec-123456:194 : hba @endverbatim
 *
 * The result is a list of nicknames, all of which say that the WWPN has a nickname of "none" -- for example, as if the NicknameParser had read content such as:
 *
 * @verbatim
20c2000dec123456,"none" @endverbatim
 *
 * This format has limited facility: it's intended for cases where an error message is used to exclude entities from an import file.  In such case, the same import would be used to generate a list of WWPN/alias combinations, then the "exclusion" list would be read using this parser.
 */

//#line 43 "VW4InvalidAddedParser.java"




public class VW4InvalidAddedParser
             extends ZoneParserI
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 3000;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:ZoneParserVal
String   yytext;//user variable to return contextual strings
ZoneParserVal yyval; //used to return semantic vals from action routines
ZoneParserVal yylval;//the 'lval' (result) I got from yylex()
ZoneParserVal valstk[] = new ZoneParserVal[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new ZoneParserVal();
  yylval=new ZoneParserVal();
  valptr=-1;
}
final void val_push(ZoneParserVal val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    ZoneParserVal[] newstack = new ZoneParserVal[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final ZoneParserVal val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final ZoneParserVal val_peek(int relative)
{
  return valstk[valptr-relative];
}
final ZoneParserVal dup_yyval(ZoneParserVal val)
{
  return val;
}
//#### end semantic value section ####
public final static short LOCATION=257;
public final static short LINE=258;
public final static short COLUMN=259;
public final static short INVALID=260;
public final static short ADDED=261;
public final static short CHILD=262;
public final static short ENTITIES=263;
public final static short WWPN=264;
public final static short ENTITY=265;
public final static short ALPHANUM=266;
public final static short HBA=267;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    0,    0,    0,
};
final static short yylen[] = {                            2,
    0,   12,    7,    6,    8,
};
final static short yydefred[] = {                         1,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    4,    0,    3,    0,
    0,    5,    0,    0,    0,    2,
};
final static short yydgoto[] = {                          1,
};
final static short yysindex[] = {                         0,
 -257,  -54, -256,  -52,  -84, -253, -255, -248, -251,  -45,
  -44,  -43, -265, -250, -247,  -40,    0,  -25,    0, -246,
 -239,    0,  -36, -243,  -69,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
};
final static int YYTABLESIZE=24;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                          2,
   16,   17,    3,    5,    6,    7,    8,    4,    9,   11,
   10,   12,   13,   14,   15,   18,   19,   20,   21,   23,
   22,   24,   25,   26,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                        257,
  266,  267,  260,   58,  261,   58,   91,  265,  262,  258,
  266,  263,   58,   58,   58,  266,  264,   58,   44,  259,
  267,   58,  266,   93,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=267;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,"','",
null,null,null,null,null,null,null,null,null,null,null,null,null,"':'",null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,"LOCATION","LINE","COLUMN","INVALID","ADDED",
"CHILD","ENTITIES","WWPN","ENTITY","ALPHANUM","HBA",
};
final static String yyrule[] = {
"$accept : input",
"input :",
"input : input LOCATION ':' '[' LINE ':' ALPHANUM ',' COLUMN ':' ALPHANUM ']'",
"input : input INVALID ADDED CHILD ENTITIES ':' WWPN",
"input : input ENTITY ':' ALPHANUM ':' HBA",
"input : input ENTITY ':' ALPHANUM ':' ALPHANUM ':' HBA",
};

//#line 59 "vw4invalid.y"

    String ins;
    java.io.StreamTokenizer st;
    boolean zpquiet = true;

    void yyerror(String s)
    {
        if (!zpquiet) System.out.println("parser error: "+s);
    }

    boolean verboseStringtokenizer = checkProperty("debug.verboseTokenizer");
    boolean newline;
    String manualPushBackALPHANUM = null;

    int yylex()
    {
        String s;
        int tok;
        Double d;
//System.out.print("yylex ");
	if (null != manualPushBackALPHANUM)
	{
	    st.sval = manualPushBackALPHANUM;
	    //if (verboseStringtokenizer) System.out.println("token#: "+java.io.StreamTokenizer.TT_WORD);
	    if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_WORD:ALPHANUM \"" + st.sval + "\"");
	    manualPushBackALPHANUM = null;
	    return ALPHANUM;
	}
	/* else */

	try {
        tok = st.nextToken();
	} catch (java.io.IOException ioe) { if (verboseStringtokenizer) System.out.println("I/O Exception so returning from parser"); return 0; }
	if (verboseStringtokenizer) System.out.println("(@75) token#: "+tok);
	switch(tok)
	{
	    case java.io.StreamTokenizer.TT_EOF:
                return 0;
	
	    case java.io.StreamTokenizer.TT_EOL:
		if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_EOL");
                newline=true;
		return '\n';

            case java.io.StreamTokenizer.TT_WORD:
                if (verboseStringtokenizer) System.out.println("checking java.io.StreamTokenizer.TT_WORD:ALPHANUM \"" + st.sval + "\"");
                if (st.sval.equalsIgnoreCase("location"))
                {
                    if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_WORD:LOCATION \"" + st.sval + "\"");
                    return LOCATION;
                }

/* %token LINE */
                if (st.sval.equalsIgnoreCase("line"))
                {
                    if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_WORD:LINE \"" + st.sval + "\"");
                    return LINE;
                }

/* %token COLUMN */
                if (st.sval.equalsIgnoreCase("column"))
                {
                    if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_WORD:COLUMN \"" + st.sval + "\"");
                    return COLUMN;
                }

/* %token INVALID */
                if (st.sval.equalsIgnoreCase("invalid"))
                {
                    if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_WORD:INVALID \"" + st.sval + "\"");
                    return INVALID;
                }

/* %token ADDED */
                if (st.sval.equalsIgnoreCase("added"))
                {
                    if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_WORD:ADDED \"" + st.sval + "\"");
                    return ADDED;
                }

/* %token CHILD */
                if (st.sval.equalsIgnoreCase("child"))
                {
                    if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_WORD:CHILD \"" + st.sval + "\"");
                    return CHILD;
                }

/* %token ENTITIES */
                if (st.sval.equalsIgnoreCase("entities"))
                {
                    if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_WORD:ENTITIES \"" + st.sval + "\"");
                    return ENTITIES;
                }

/* %token ENTITY */
                if (st.sval.equalsIgnoreCase("entity"))
                {
                    if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_WORD:ENTITY \"" + st.sval + "\"");
                    return ENTITY;
                }

/* %token HBA */
                if (st.sval.equalsIgnoreCase("hba"))
                {
                    if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_WORD:HBA \"" + st.sval + "\"");
                    return HBA;
                }

/* %token WWPN */
                if (st.sval.matches("[0-9a-fA-F]{16}"))
                {
                    if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_WORD:WWPN \"" + st.sval + "\"");
		    yylval = new ZoneParserVal(st.sval.toLowerCase());
                    return WWPN;
                }

/* %token ALPHANUM */
		yylval = new ZoneParserVal(st.sval);
		return ALPHANUM;

	    case java.io.StreamTokenizer.TT_NUMBER:
	        if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_NUMBER \"" + st.sval + "\" as \"" + st.nval + "\"");
                newline=false;
		yylval = new ZoneParserVal(st.nval);
		return ALPHANUM;

            case ';':
            case ':':
            case '[':
            case ']':
            case ',':
                if (verboseStringtokenizer) System.out.println("returning anticipated char "+ tok + " \"" + (char) tok + "\"");
                newline=false;
                return tok;
	}

	// bogusity
	if (verboseStringtokenizer) System.out.println("returning bogus char "+ tok + " \"" + (char) tok + "\"");
	return '\n';
    }


public void parse()
{
 try
 {
 yyparse();
 }
 catch (Exception e)
 {
 }
}


public void setReader(Reader in)
{
  //nothing to do
 st = new java.io.StreamTokenizer(in);
 // reset the parser to effect a parseNumbers(false) which doesn't exist
 st.resetSyntax();
 st.wordChars('a', 'z');
 st.wordChars('A', 'Z');
 //st.wordChars(128 + 32, 255);
 st.ordinaryChars((int) 1, (int) 7);
 st.whitespaceChars(8, ' ');

 // ok, dots, numbers, things accepted as WWNs or names are generally "words"
 st.wordChars((int) '.', (int) '.');
 st.wordChars((int) '0', (int) '0');
 st.wordChars((int) '1', (int) '9');
 //st.wordChars((int) ':', (int) ':');
 st.wordChars((int) '_', (int) '_');
 st.wordChars((int) '-', (int) '-');

  /*
   * It's ordinary, and returned so that we can use as a separator. 
   * Later, it's possible to just treat as a separator to be a bit more
   * accepting of "xxx xxx xxx" as "xxx; xxx; xxx"
   */
 //st.ordinaryChar((int) ';');

 st.eolIsSignificant(false);

 newline=false;
}


public void run()
{
  if (0 != yyparse())
    try
    {
	/* if the parse is incomplete, drain the channel */
	while (java.io.StreamTokenizer.TT_EOF != st.nextToken()) ;
    }
    catch (IOException e) { debug(getClass().getName()+" I.O Error: "+e.getMessage()); }
}


public void setDebug(boolean debug) { yydebug=debug; }
public VW4InvalidAddedParser(InputStream in, boolean debugMe) { super(in, debugMe); }
public VW4InvalidAddedParser(InputStream in) { super(in); }
public VW4InvalidAddedParser(java.util.Properties in, boolean debugMe) { super(in, debugMe); }


public static void main(String args[])
{
    String p = System.getProperties().getProperty("debug.yystate");

    VW4InvalidAddedParser aszp = new VW4InvalidAddedParser(System.in, (null != p) && ((p.equalsIgnoreCase("VW4InvalidAddedParser")) || (p.equalsIgnoreCase("true"))));
    aszp.testSummary(args);
}
//#line 412 "VW4InvalidAddedParser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 3:
//#line 53 "vw4invalid.y"
{ addAlias (new ZoneParserVal("none"), val_peek(0)); yyval = val_peek(6);}
break;
//#line 565 "VW4InvalidAddedParser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
//## The -Jnorun option was used ##
//## end of method run() ########################################



//## Constructors ###############################################
//## The -Jnoconstruct option was used ##
//###############################################################



}
//################### END OF CLASS ##############################
