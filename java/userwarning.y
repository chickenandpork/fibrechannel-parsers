%{
import java.lang.Math;
import java.io.*;

/**
 * @page formats Compatible File Formats
 * @subpage Caveat
 *
 * @page Caveat Read this if you want this to work
 *
 * The parsers in this project work on a text stream -- the less butchered and corrupted that text stream is, the better the chance that they'll work.
 *
 * I spend a lot of time trying to fake around "--MORE--" and various <a hre="http://en.wikipedia.org/wiki/ANSI_escape_code">ANSI Escape Sequences</a> and to be honest, I'm really quite frustrated trying to get users asking for help to read a few rules:
 * <ol>
 * <li>non-interactive.  As in "not a log of my typing".  think "batch mode".
 * <li>Every parser says "treat like binary", and "don't edit", and "in particular, the use of a putty log [is bad]".  Make sure you've seen that part.
 * <li>this means "plink.exe" not "putty.exe".  Seriously.  If you don't know why, see the first rule, and check out the difference in these tools.  It's OK, I'll wait for you.  Here's a hint: it tends to lead to the next one
 * <li>No ANSI escape sequences.  See also "non-interative", above.  Interactive terminals include "hit [ENTER] to continue" and some screen-formatting with ANSI escape sequences.
 * <li>I literally spend more time un-butchering ANSI escape sequences than I spend writing code.  Quit it.  Seriously.  Read the bits above.  If I suspect that reading of the bits above has not occurred, I will send you to this page.
 * <li>please, for the love of all things holy, please read the bits above
 * </ol>
 *
 * This parser detects when the rules above have not been read.
 */

%}

/* YACC Declarations */
%token PUTTYNAME
%token PUTTYSEPARATOR
%token ESC
%token CSI	/* Control Sequence Indicator */
%token NUM
%token COLON

/* Grammar follows */
%%
input: /* empty string */
 | input PUTTYSEPARATOR PUTTYNAME ALPHA NUM NUM NUM COLON NUM COLON NUM PUTTYSEPARATOR { puttyWarning(); $$ = $1; }
 | input ALPHA { $$ = $1; }
 | input ESC CSI ALPHA { $$ = $1; }
 | input ESC CSI NUM ALPHA { $$ = $1; }
 | input ESC CSI NUM NUM ALPHA { $$ = $1; }
 | input ESC CSI NUM NUM NUM ALPHA { $$ = $1; }
 | input NUM { $$ = $1; }
 | input COLON { $$ = $1; }
 | input CSI { $$ = $1; }
 ;


%%

    boolean puttyWarning = false;
    boolean ansiWarning = false;

    void puttyWarning()
    {
        System.out.println("DO NOT, DO NOT, DO NOT use interactive methods such as Putty - the text\n stream is corrupted by screen-management control characters which means\n the parser has a lower accuracy for which you will only blame the parser\n in the end");
	puttyWarning = true;
    }

    void ansiWarning()
    {
        System.out.println("ANSI control-characters detected in the stream.  DO NOT, DO NOT, DO NOT use\n interactive methods to collect text for parsing: the text stream is\n corrupted by screen-management control characters.  In a garbage-in,\n garbage-out world wherein you get what you give, you are giving corrupted\n garbage to the parser.  Expect failure");
	ansiWarning = true;
    }

    void yyerror(String s)
    {
        if (!zpquiet) System.out.println("parser error: "+s);
    }

    java.io.StreamTokenizer st;
    boolean zpquiet = true;

    boolean verboseStringtokenizer = checkProperty("debug.verboseTokenizer");
    boolean newline = true;

    int yylex()
    {
        String s;
        int tok;
        Double d;

	try {
        tok = st.nextToken();
	} catch (java.io.IOException ioe) { return 0; }
	if (verboseStringtokenizer) System.out.println("token#: "+tok+": status: "+st.toString());
	switch(tok)
	{
	    case java.io.StreamTokenizer.TT_EOF:
	    //System.out.println("returning java.io.StreamTokenizer.TT_EOF");
            if (!newline)
            {
                newline=true;
		st.pushBack();
		if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_EOL in lieu of TT_EOF");
                return '\n'; //So we look like classic YACC example
            }
            else
                return 0;
	
/*
	    case java.io.StreamTokenizer.TT_EOL:
		if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_EOL");
                newline=true;
		return '\n';
*/

	    case java.io.StreamTokenizer.TT_WORD:
	        if (st.sval.equalsIgnoreCase("=~=~=~=~=~=~=~=~=~=~=~="))
		{
		    if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_WORD:PuttySeparator \"" + st.sval + "\"");
		    return PUTTYSEPARATOR;
		}
	        else if (st.sval.equalsIgnoreCase("PuTTY"))
		{
		    if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_WORD:PuttyName \"" + st.sval + "\"");
		    return PUTTYNAME;
		}
		if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_WORD:ALPHA \"" + st.sval + "\"");
		yylval = new ZoneParserVal(st.sval);
		return ALPHA;

	    case java.io.StreamTokenizer.TT_NUMBER:
	        if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_NUMBER \"" + st.sval + "\" as \"" + st.nval + "\"");
		yylval = new ZoneParserVal(tok);
		return NUM;

	    case ':':
	        if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_WORD:COLON \"" + st.sval + "\" as \"" + st.nval + "\"");
		yylval = new ZoneParserVal(st.sval);
		return COLON;

	    case '\033':
	        if (verboseStringtokenizer) System.out.println("returning anticipated char ESC \"" + (char) tok + "\"");
		return ESC;

	    case '[':
	        if (verboseStringtokenizer) System.out.println("returning anticipated char CSI \"" + (char) tok + "\"");
		return CSI;
	}

	if ( (tok >= '0') && (tok <= '9') )
	{
	    if (verboseStringtokenizer) System.out.println("returning java.io.StreamTokenizer.TT_NUMbER \"" + st.sval + "\" as \"" + st.nval + "\"");
	    yylval = new ZoneParserVal(tok);
	    return NUM;
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
/*
 st.resetSyntax();
 st.eolIsSignificant(false);
 st.wordChars('a', 'z');
 st.wordChars('A', 'Z');
 //st.wordChars(128 + 32, 255);
 st.whitespaceChars(' ', ' ');

 // ok, dots, numbers, things accepted as WWNs or names are generally "words"
*/
 st.wordChars((int) '#', (int) '#');
 //st.numberChars((int) '0', (int) '0');
 //st.numberChars((int) '1', (int) '9');
 //st.wordChars((int) ':', (int) ':');
 st.wordChars((int) '_', (int) '_');
 st.wordChars((int) '-', (int) '-');
 st.wordChars((int) '(', (int) ')');
 st.wordChars((int) ',', (int) ',');
 st.wordChars((int) '.', (int) '.');
 st.wordChars((int) ']', (int) ']');

 // bloody putty header -- big indicator that "reading the instructions" can
 // be challenging: "DO NOT USE PUTTY" and -- yet -- a putty header (=~=~=~...).
 // How did that happen?  Friggin magic.  And Ninjas.
 st.wordChars((int) '=', (int) '=');
 st.wordChars((int) '~', (int) '~');

 //st.ordinaryChar((int) ';');
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

public UserWarningParser(InputStream in, boolean debugMe) { super(in, debugMe); }
public UserWarningParser(java.util.Properties in) { super(in); }


public static void main(String args[])
{
    String p = System.getProperties().getProperty("debug.yystate");

    UserWarningParser aszp = new UserWarningParser(System.in, (null != p) && ((p.equalsIgnoreCase("UserWarningParser")) || (p.equalsIgnoreCase("true"))));
    aszp.testSummary(args);
}
