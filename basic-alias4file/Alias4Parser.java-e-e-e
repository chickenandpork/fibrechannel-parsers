package org.smallfoot.parser.zone;

import java.io.*;
import java.util.*;


public class Alias4Parser extends ZoneParser
{
    BufferedReader br;
    java.util.Vector<ZPFCAliasEntry> fcaliases = new java.util.Vector();
    boolean zpquiet;

/**
 * Create a parser, setting the debug to true or false
 *
 * @param in Reader that is the source of bytes to consume
 * @param debugMe (ignored, retained for API)
 * @param quiet true to squelch any error output
 */
public Alias4Parser(Reader in, boolean debugMe, boolean quiet)
{
    if (null != in) setReader(in);
    zpquiet=quiet;
}

/**
 * Create a parser, setting the debug to true or false
 *
 * @param properties additional name-value pair collection
 * @param debugMe true for debugging, false for no debug.
 * @param quiet true to squelch any error output
 */
public Alias4Parser(java.util.Properties properties, boolean debugMe, boolean quiet)
{
    zpquiet = quiet;
}



    public java.util.Enumeration<ZPZoneEntry> zoneElements() { return null; }
    public int zoneSize() { return 0; }

    public java.util.Enumeration<ZPAliasEntry> aliasElements() { return null; }
    public ZPAliasEntry[] aliasArray() { return null; }
    public int aliasSize() { return 0; }



    public void setReader(java.io.Reader is)
    {
        br = new BufferedReader(is);
    }



    public void run()
    {
	String line;

	if (null != br) 
        try
        {
            while (null != (line = br.readLine()))
            {
		String fab = null;
                String[] lb = line.split(",");
                if (lb.length <= 2)
                    ;
                else if (lb.length > 4)
                    ;
		else if (! lb[0].equalsIgnoreCase("fcid"))
		    ;
		else if (4 == lb.length) fcaliases.add(new ZPFCAliasEntry(lb[1],lb[2],lb[3]));
		else fcaliases.add(new ZPFCAliasEntry(lb[1],lb[2]));
	    }
	}
        catch (java.io.IOException ioe)
        {
            if (!zpquiet) System.out.println("I/O Error: " + ioe.getMessage());
        }
    }
}
