package org.smallfoot.parser.zone;

import java.io.*;
import java.util.TreeMap;
import java.util.Vector;

/** @file */

/**
 * @jvmopt <b>debug.verboseTokenizer</b>
 * (values: true, Alias4Parser, AliShowZoneParser, BNAZoneParser, DeviceAliasParser, NicknameParser, ShowZoneParser, VW4InvalidAddedParser)
 * can be set to either "true", or to the name of a specific
 * parser (ie ShowZoneParser) to cause the parser's tokenizing and returned tokens
 * to be displayed.  Of note: this will show when the parser sees a value it
 * doesn't like, such as an improper symbol, word, or key term.  This can be
 * helpful in diagnosing why the parser gave up and/or the calling process didn't
 * feel this parser's results were worth keeping or using.  Alias4Parser and
 * NicknameParser are not LALR-based but do give some indications by this option.
 * 
 * @jvmopt
 * @code java -Ddebug.verboseTokenizer=NicknameParser -jar fcparser.jar -N ... @endcode
 *
 * @jvmopt <b>debug.dumpAliases</b>
 * (values: true, Alias4Parser, AliShowZoneParser, BNAZoneParser, DeviceAliasParser, NicknameParser, ShowZoneParser, VW4InvalidAddedParser)
 * can be used to tell most parsers to dump out their results
 * for diagnostics.  Setting it to "true" would cause all to dump their results;
 * setting to the name of a Parser (ie ShowZoneParser, VW4InvalidAddedParser,
 * NicknameParser) would cause that individual parser to show its results.  These
 * names are the same names listed at the end of a parse attempt for
 * convenience/aide-memoire.
 *
 * @jvmopt
 * @code java -Ddebug.dumpAliases=AliShowZoneParser -jar fcparser.jar -N ...  @endcode
 *
 * @jvmopt <b>debug.verboseAddAlias</b>:
 * (values: true, Alias4Parser, AliShowZoneParser, BNAZoneParser, DeviceAliasParser, ShowZoneParser, VW4InvalidAddedParser)
 * Although not much use without looking inside the code, setting this value allows you
 * to see when the parser is storing a new alias block/set internally for retrieval by
 * the calling function.  It might help you to see where the incorrect data is coming from.
 *
 * @jvmopt <b>debug.verboseAddZone</b>:
 * (values: true, Alias4Parser, AliShowZoneParser, BNAZoneParser, DeviceAliasParser, ShowZoneParser, VW4InvalidAddedParser)
 * Although not much use without looking inside the code, setting this value allows you
 * to see when the parser is storing a new zone block internally for retrieval by the
 * calling function.  It might help you to see where the incorrect data is coming from.
 *
 * @jvmopt <b>debug.verboseAppendZone</b>:
 * (values: true, Alias4Parser, AliShowZoneParser, BNAZoneParser, DeviceAliasParser, ShowZoneParser, VW4InvalidAddedParser)
 * Although not much use without looking inside the code, setting this value allows you
 * to see when the parser is adding data to an existing zone value internally for retrieval by
 * the calling function.  It might help you to see where the incorrect data is coming from.
 *
 * @jvmopt <b>debug.yystate</b>:
 * (values: true, AliShowZoneParser, BNAZoneParser, DeviceAliasParser, ShowZoneParser, VW4InvalidAddedParser)
 * Although not much use without looking inside the code, setting this value allows you
 * to see the debug information from inside the LALR state machine itself.  This might give
 * some insight as to why some parse attempts are failing, or how the state-machine needs to
 * be extended to accommodate more situations.
 */



public abstract class ZoneParserI extends ZoneParser
{

    java.util.Vector<ZPZoneEntry> zones = new java.util.Vector();
    TreeMap<String,ZPAliasEntry> aliases = new TreeMap<String,ZPAliasEntry>();

    public java.util.Enumeration<ZPZoneEntry> zoneElements() { return zones.elements(); }

    public int zoneSize() { return zones.size(); }

    public java.util.Enumeration<ZPAliasEntry> aliasElements() { return new java.util.Vector<ZPAliasEntry>(aliases.values()).elements(); }

    public ZPAliasEntry[] aliasArray() { return aliases.values().toArray(new ZPAliasEntry[1]); }

    public int aliasSize() { return aliases.size(); }

    //public ZPAliasEntry[] aliases() { return (ZPAliasEntry[]) aliases.toArray(); }

    public abstract void setReader(java.io.Reader is);
    public abstract void setDebug(boolean debug);

    /**
     * Create a parser with a new stream, defaulting to no debug, and verbose ZP debug
     *
     * @param in InputStream that is the source of bytes to consume
     */
    public ZoneParserI(java.io.InputStream in)
    {
        this(in, false);
    }


    /**
     * Create a parser with a no stream or reader, fairly uncivilized, defaulting to no debug
     */
    public ZoneParserI()
    {
        this((Reader) null, false);
    }


    /**
     * Create a parser with a new stream
     *
     * @param in InputStream that is the source of bytes to consume
     * @param debugMe true for debugging, false for no debug.
     */
    public ZoneParserI(java.io.InputStream in, boolean debugMe)
    {
        this(new BufferedReader(new InputStreamReader(in)), debugMe);
    }


    /**
     * Create a parser with a new reader, defaulting to no debug, and verbose ZP debug
     *
     * @param in Reader that is the source of bytes to consume
     */
    public ZoneParserI(Reader in)
    {
        this(in, false);
    }


    /**
     * Create a parser, setting the debug to true or false
     *
     * @param in Reader that is the source of bytes to consume
     * @param debugMe true for debugging, false for no debug.
     */
    public ZoneParserI(Reader in, boolean debugMe)
    {
        if (null != in) setReader(in);
        setDebug(debugMe);
    }



    /**
     * Create a parser, setting the debug to true or false
     *
     * @param properties additional name-value pair collection
     * @param debugMe true for debugging, false for no debug.
     */
    public ZoneParserI(java.util.Properties properties, boolean debugMe)
    {
        this((Reader) null,debugMe);
    }



void addZone(ZoneParserVal name, ZoneParserVal list)
{
if (checkProperty("debug.verboseAddZone"))
{
System.out.println(" ZZone: " + name.sval + " -- xx");
System.out.println("name.sval: " + (null == name.sval ? "NULL" : name.sval));
System.out.println("name.obj: " + (null == name.obj ? "NULL" : name.obj.getClass().getName()));
System.out.println("list.sval: " + (null == list.sval ? "NULL" : list.sval));
System.out.println("list.obj: " + (null == list.obj ? "NULL" : list.obj.getClass().getName()));
}

    if (null != list.sval)
        addZone (name.sval, list.sval);
    else if (list.obj.getClass() == java.util.Vector.class)
        addZone (name.sval, (java.util.Vector<ZoneParserVal>) list.obj);
    else if (list.obj.getClass() == ZoneParserVal.class)
        addZone (name, (ZoneParserVal)list.obj);
}

void addZone(String name, String list)
{
if (checkProperty("debug.verboseAddZone"))
{
System.out.println("name: " + (null == name ? "NULL" : name));
System.out.println("list: " + (null == list ? "NULL" : list));
}
    zones.add(new ZPZoneEntry (name,list));
}

void addZone(String name, java.util.Vector<ZoneParserVal> list)
{
    ZPZoneEntry ze = new ZPZoneEntry (name);
    for (java.util.Enumeration<ZoneParserVal> e = list.elements(); e.hasMoreElements();)
    {
       ze.addZone(e.nextElement().sval);
    }

    zones.add(ze);
 }

void addAlias(ZoneParserVal name, ZoneParserVal list)
{
    if (null != list.sval)
        addAlias (name.sval, list.sval);
    else if (list.obj.getClass() == java.util.Vector.class)
        addAlias (name.sval, (java.util.Vector<ZoneParserVal>) list.obj);
    else if (list.obj.getClass() == ZoneParserVal.class)
        addAlias (name, (ZoneParserVal)list.obj);
}

void addAlias(String name, String wwn)
{
    name = name.replaceAll("\"","").trim();
    wwn = wwn.replaceAll("\"","");

    if (checkProperty("debug.verboseAddAlias"))
	System.out.println("add: " + (null == name ? "NULL" : name) + " --> wwn: "+wwn);

    ZPAliasEntry zpa;

    if (null != (zpa = aliases.get(name)))
	zpa.addAlias(wwn);
    else
        aliases.put(name, new ZPAliasEntry (name,wwn));
}

void addAlias(String name, java.util.Vector<ZoneParserVal> list)
{
    if (checkProperty("debug.verboseAddAlias"))
	System.out.println("add: " + (null == name ? "NULL" : name));

    name = name.replaceAll("\"","").trim();
    ZPAliasEntry ze = aliases.get(name);
    if (null == ze)
    {
	ze = new ZPAliasEntry (name);
        aliases.put(name,ze);
    }
    for (java.util.Enumeration<ZoneParserVal> e = list.elements(); e.hasMoreElements();)
    {
       ze.addAlias(e.nextElement().sval);
    }
 }

ZoneParserVal appendZoneAlphanum(ZoneParserVal list, ZoneParserVal item)
{
if (checkProperty("debug.verboseAppendZone"))
{
System.out.println("append: list.sval: " + (null == list.sval ? "NULL" : list.sval));
System.out.println("append: list.obj: " + (null == list.obj ? "NULL" : list.obj.getClass().getName()));
System.out.println("append: item.sval: " + (null == item.sval ? "NULL" : item.sval));
System.out.println("append: item.obj: " + (null == item.obj ? "NULL" : item.obj.getClass().getName()));
}
    if ( (null != list.obj)  && (list.obj.getClass().getName().equals(java.util.Vector.class.getName())) )
    {
        ((java.util.Vector) list.obj).add(item);
        return list;
    }
    else
    {
        java.util.Vector<ZoneParserVal> li = new java.util.Vector(2,2);
        ZoneParserVal retNewList = new ZoneParserVal(li);
        li.add(list);
        li.add(item);
        return retNewList;
    }
}



    /**
     * To test/show a descendant class, with a created parser, feed in the stdin, parse what it can, and summarize the results to stdout
     *
     * @param args argc/argv from a void main() run to feed any getopt herein (currently ignored)
     */
    public void testSummary(String args[])
    {
        run();
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
        System.out.println("Aliases: "+c + " (name/WWPN pairs)");

        if (checkProperty("debug.dumpAliases"))
            for (ZPAliasEntry e: pq)
                for (String s: e.wwns)
                    System.out.println("Alias: "+s+", "+e.name());
	}
    }

}
