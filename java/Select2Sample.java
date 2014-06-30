package org.smallfoot.parser.boneheadbits;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import org.smallfoot.parser.ParserTee;
import org.smallfoot.parser.zone.BNAZoneParser;

public class Select2Sample
{
    static public void main (String arg[])
    {
	String work;

	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	try {
	BNAZoneParser b = new BNAZoneParser(System.getProperties());
	OutputStream outpipe = ParserTee.pipeOutputStreamTo (b);
	PrintStream outpr = new PrintStream (outpipe, true);

        while (null != (work = in.readLine()))
	if (work.startsWith(" "))
	if (!work.startsWith("       "))
	{
	    for (char i = 1; i < 8; i++)
	    {
	        String pre = String.format("x0%d",(int) i);
//System.out.println("replacing \""+pre+"\"");
		work = work.replace("\\"+pre, ""+i);   //java.lang.Character.toString(i));
	    }
	    outpr.println(work);
	}
        outpr.close();

	b.join (1000);
	b.summarize();

	}
	catch (java.io.IOException ioe) { }
	catch (java.lang.InterruptedException ie) { }
    }
}

