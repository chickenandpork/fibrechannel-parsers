package org.smallfoot.parser.zone;

class ZPZoneEntry
{
    String name;
    java.util.Vector<String> members = new java.util.Vector(2,2);

    ZPZoneEntry (String name, String member)
    {
        this.name = name;
        this.members.add(member);
    }
    ZPZoneEntry (String name)
    {
        this.name = name;
    }
    void addZone(String z)
    {
        members.add(z);
    }
}
