package org.smallfoot.parser.zone;

import java.io.*;

public abstract class ZoneParser extends Thread
{
    public abstract java.util.Enumeration<ZPZoneEntry> zoneElements();

    public abstract int zoneSize();

    public abstract java.util.Vector items(String name);

    public abstract java.util.Enumeration<ZPAliasEntry> aliasElements();

    public abstract ZPAliasEntry[] aliasArray();

    public abstract int aliasSize();

    public abstract void setReader(java.io.Reader is);
    public abstract void setDebug(boolean debug);


    java.util.Properties _prop = null;
    boolean checkProperty(String n)
    {
        if (null == _prop) _prop = System.getProperties();
        return (
                   Boolean.parseBoolean(_prop.getProperty(n))
                   || (getClass().getName().equalsIgnoreCase(_prop.getProperty(n)))
                   || (getClass().getName().replaceAll("^.*\\.","").equalsIgnoreCase(_prop.getProperty(n)))
               );
    }



    /**
     * Create a parser with a new stream, defaulting to no debug, and verbose ZP debug
     *
     * @param in InputStream that is the source of bytes to consume
     */
    public ZoneParser(java.io.InputStream in)
    {
        this(in, false);
    }


    /**
     * Create a parser with a no stream or reader, fairly uncivilized, defaulting to no debug
     */
    public ZoneParser()
    {
        this((Reader) null, false);
    }


    /**
     * Create a parser with a new stream
     *
     * @param in InputStream that is the source of bytes to consume
     * @param debugMe true for debugging, false for no debug.
     */
    public ZoneParser(java.io.InputStream in, boolean debugMe)
    {
        this(new BufferedReader(new InputStreamReader(in)), debugMe);
    }


    /**
     * Create a parser with a new reader, defaulting to no debug, and verbose ZP debug
     *
     * @param in Reader that is the source of bytes to consume
     */
    public ZoneParser(Reader in)
    {
        this(in, false);
    }


    /**
     * Create a parser, setting the debug to true or false
     *
     * @param in Reader that is the source of bytes to consume
     * @param debugMe true for debugging, false for no debug.
     */
    public ZoneParser(Reader in, boolean debugMe)
    {
        if (null != in) setReader(in);
        setDebug(debugMe);
    }



    /**
     * Create a parser, setting the debug to true or false
     *
     * @param properties additional name-value pair collection
     */
    public ZoneParser(java.util.Properties properties)
    {
        //this((Reader) null);
        _prop = properties;
        setDebug(checkProperty("debug.yystate"));
    }



    /**
     * To test/show a descendant class, with a created parser, feed in the stdin, parse what it can, and summarize the results to stdout
     *
     * @param args argc/argv from a void main() run to feed any getopt herein (currently ignored)
     */
    public void testSummary(String args[])
    {
        run();
        summarize();
    }



    /**
     * Summarize the parsing results to stdout
     */
    public void summarize()
    {
        System.out.println("Parse results for "+getClass().getName().replaceAll("^.*\\.","") + ":");
        System.out.println("Zones: "+zoneSize());
        System.out.println("Aliases: "+aliasSize() + " (names with one or more WWPNs)");

        if (0 < aliasSize())
        {
            java.util.PriorityQueue<ZPAliasEntry> pq = new java.util.PriorityQueue(aliasSize());
            for (ZPAliasEntry e: aliasArray())
                pq.add(e);
            int c = 0;
            for (ZPAliasEntry e: pq) c += e.wwns.size();
            System.out.println("Aliases: "+c + " (name/WWPN tuples)");

            if (checkProperty("debug.dumpAliases"))
                for (ZPAliasEntry e: pq)
                    for (String s: e.wwns)
                        System.out.println("Alias: "+s+", "+e.name());
        }
    }
}

