package org.smallfoot.parser.zone;

public class ZPFCAliasEntry
{
    String fabric = null;
    String fcid;
    String name;

    ZPFCAliasEntry (String fcid, String name, String fabric) { this.fcid = fcid; this.name = name; this.fabric = fabric; }
    ZPFCAliasEntry (String fcid, String name) { this.fcid = fcid; this.name = name; }

    public String fabric() { return this.fabric; }
    public String fcid() { return this.fcid; }
    public String name() { return this.name; }

    static public boolean isFCID(String fcid)
    {
	if (null == fcid)
	    return false;
	if (fcid.matches("^0x(|[0-9a-fA-F])[0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F]$"))
	    return true; 
	else
	    return (fcid.matches("^(|[0-9a-fA-F])[0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F]$"));
    }
}
