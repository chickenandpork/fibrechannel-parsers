package org.smallfoot.parser.zone;

public class ZPAliasEntry
{
    String name;
    public enum DeviceType { DEV_UNKNOWN, DEV_SERVER, DEV_SWITCH, DEV_STORAGE, DEV_TAPE, DEV_VIRTUALIZER, DEV_OTHER };
    java.util.Vector<String> wwns = new java.util.Vector(1,2);

    ZPAliasEntry (String name, String WWN) { this.name = name; this.wwns.add(WWN); }
    ZPAliasEntry (String name) { this.name = name; }
    void addAlias (String wwn) { wwns.add(wwn); }

    public String name() { return this.name; }
    public java.util.Enumeration<String> getWWNs() { return wwns.elements(); }
    public String[] getWWNArray() { return wwns.toArray(new String[1]); }
}
