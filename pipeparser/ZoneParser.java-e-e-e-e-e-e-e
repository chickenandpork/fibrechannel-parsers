package org.smallfoot.parser.zone;

public abstract class ZoneParser extends Thread
{
    public abstract java.util.Enumeration<ZPZoneEntry> zoneElements();

    public abstract int zoneSize();

    public abstract java.util.Enumeration<ZPAliasEntry> aliasElements();

    public abstract ZPAliasEntry[] aliasArray(); public abstract

    int aliasSize();

    public abstract void setReader(java.io.Reader is);

    java.util.Properties _prop = null;
    boolean checkProperty(String n)
    {
        if (null == _prop) _prop = System.getProperties();
        //return ( (null != _prop.getProperty(n)) && (false != Boolean.parseBoolean(_prop.getProperty(n))) );
        return (
            Boolean.parseBoolean(_prop.getProperty(n))
         || (getClass().getName().equalsIgnoreCase(_prop.getProperty(n)))
         || (getClass().getName().replaceAll("^.*\\.","").equalsIgnoreCase(_prop.getProperty(n)))
            );
    }
}
