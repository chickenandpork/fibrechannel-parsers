package org.smallfoot.parser.zone;

interface ZoneParser
{
    java.util.Enumeration<ZPZoneEntry> zoneElements();
    int zoneSize();
    java.util.Enumeration<ZPAliasEntry> aliasElements();
    int aliasSize();
}
