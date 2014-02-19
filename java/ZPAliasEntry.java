package org.smallfoot.parser.zone;

public class ZPAliasEntry implements java.lang.Comparable<ZPAliasEntry>
{
    String name;
    public enum DeviceType { DEV_UNKNOWN, DEV_SERVER, DEV_SWITCH, DEV_STORAGE, DEV_TAPE, DEV_VIRTUALIZER, DEV_OTHER };
    java.util.TreeSet<String> wwns = new java.util.TreeSet();

    ZPAliasEntry (String name, String WWN)
    {
        this.name = name;
        this.wwns.add(WWN.toLowerCase());
    }
    ZPAliasEntry (String name)
    {
        this.name = name;
    }
    void addAlias (String wwn)
    {
        wwns.add(wwn.toLowerCase());
    }

    public String name()
    {
        return this.name;
    }
    public java.util.Iterator<String> getWWNs()
    {
        return wwns.iterator();
    }
    public String[] getWWNArray()
    {
        return wwns.toArray(new String[1]);
    }

    public int compareTo (ZPAliasEntry y)
    {
        return name.compareTo(y.name);
    }
}
