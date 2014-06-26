package org.smallfoot.parser.zone;

import java.io.*;
import java.util.TreeMap;
import java.util.Vector;

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
        System.out.println("Aliases: "+c + " (name/WWPN tuples)");

        if (checkProperty("debug.dumpAliases"))
            for (ZPAliasEntry e: pq)
                for (String s: e.wwns)
                    System.out.println("Alias: "+s+", "+e.name());
	}
    }

}
