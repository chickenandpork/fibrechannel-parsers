package org.smallfoot.parser.zone;

public class ZPAliasEntry
{
    String name;
    java.util.Vector<String> wwns = new java.util.Vector(1,2);

    ZPAliasEntry (String name, String WWN) { this.name = name; this.wwns.add(WWN); }
    ZPAliasEntry (String name) { this.name = name; }
    void addAlias (String wwn) { wwns.add(wwn); }

    public String name() { return this.name; }
    public java.util.Enumeration<String> getWWNs() { return wwns.elements(); }
}
