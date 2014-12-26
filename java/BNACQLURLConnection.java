/* smallfoot.org is owned by allan.clark */
package org.smallfoot.parser.bnacql;

import java.io.*;
import java.net.MalformedURLException;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Properties;
import javax.activation.DataSource;
import javax.activation.URLDataSource;
import javax.cim.CIMDataType;
import javax.cim.CIMInstance;
import javax.cim.CIMObjectPath;
import javax.cim.CIMProperty;
import javax.security.auth.Subject;
import javax.wbem.client.PasswordCredential;
import javax.wbem.client.UserPrincipal;
import javax.wbem.client.WBEMClient;
import javax.wbem.client.WBEMClientFactory;
import javax.wbem.WBEMException;
import org.sblim.cimclient.discovery.Discoverer;
import org.sblim.cimclient.discovery.DiscovererFactory;
import org.sblim.cimclient.discovery.WBEMServiceAdvertisement;
//import org.sblim.cimclient.samples.Jsr48CimSample;
import org.smallfoot.parser.FCParser;
import org.smallfoot.parser.cimcql.CIMXMLCQLURLConnection;

/**
 * @file
 *
 * This class provides the connection info to query a BNA / CMCNE / HPNA service via CIM-XML and CQL (WQL) Query
 *
 * @page formats Compatible File Formats
 * @subpage BNACQLURLConnection
 *
 * @page BNACQLURLConnection Brocade objects from CIM-XML CIMOM Database (BNA, CMCNE, HPNA) (alpha)
 *
 * The BNACQLURLConnection collects bnacql:// Brocade_Alias from a BNA Server via CIM-XML Database
 *
 * This collector/parser chain is more successful than text-based parsers simply because
 * it avoids human-error and the error of not reading the warning to avoid putty log traces
 * and other "nearly 100% unmodified" methods of transferring content.  Additionally, this
 * is an entirely binary connection, so the user cannot accidentally edit the content.
 * Finally, as BNA may harvest from and provide config to many fabrics at once, this is
 * typically "a broader net to cast" to collect information.
 *
 * http://www-01.ibm.com/support/docview.wss?uid=nas7f530c1afd86f53d8862577c40012b9de
 *
 *    1. On the system of IBM Systems Director, initiate "cimcli" command to check for Brocade switch zone members.
 *    
 *    (1) Query switch zones:
 *    cimcli [-s] -l [Brocade SMI Agent IP]:[Brocade SMI Agent port] -u [Brocade SMI Agent userID] -p [Brocade SMI Agent password] -n root/brocade1 ni Brocade_Zone
 *    Parameter "-s" specifies to connect over HTTPS.
 *    
 *    For example:
 *    
 *    >cimcli -l 9.12.28.140:5988 -u userID -p password -n root/brocade1 ni Brocade_Zone
 *    Brocade_Zone.InstanceID="false;HMC_Managed_Zone;100000051ECAA229"
 *    Brocade_Zone.InstanceID="false;IVM_Managed_Zone;100000051ECAA229"
 *    Brocade_Zone.InstanceID="false;VMC_Test_Infrastructure_Support_Zone;100000051ECAA229"
 *    Brocade_Zone.InstanceID="true;HMC_Managed_Zone;100000051ECAA229"
 *    Brocade_Zone.InstanceID="true;IVM_Managed_Zone;100000051ECAA229"
 *    Brocade_Zone.InstanceID="true;VMC_Test_Infrastructure_Support_Zone;100000051ECAA229"
 *    
 *    (2) For each returned Brocade_Zone path in step (1) that contains "InstanceID="true", query its zone members:
 *    cimcli [-s] -l [Brocade SMI Agent IP]:[Brocade SMI Agent port] -u [Brocade SMI Agent userID] -p [Brocade SMI Agent password] -n root/brocade1
 *      an [Brocade_Zone path] -ac Brocade_ZoneMembershipSettingDataInZone -rc Brocade_ZoneMembershipSettingData
 *
 *    Parameter "-s" specifies to connect over HTTPS.
 *    Attention: In Brocade_Zone path, add slash before quotation marks, like this: Brocade_Zone.InstanceID=\ "true;HMC_Managed_Zone;100000051ECAA229\ "
 *    
 *    For example:
 *    
 *    >cimcli -l 9.12.28.140:5988 -u userID -p password -n root/brocade1 an Brocade_Zone.InstanceID=\"true;HMC_Managed_Zone;100000051ECAA229\"
 *              -ac Brocade_ZoneMembershipSettingDataInZone -rc Brocade_ZoneMembershipSettingData
 *    //9.12.28.140/root/brocade1:Brocade_ZoneMembershipSettingData.InstanceID="true;10000000C9C0B372;100000051ECAA229"
 *    //9.12.28.140/root/brocade1:Brocade_ZoneMembershipSettingData.InstanceID="true;10000000C99DDD04;100000051ECAA229"
 *    //9.12.28.140/root/brocade1:Brocade_ZoneMembershipSettingData.InstanceID="true;10000000C99C1628;100000051ECAA229"
 *    
 *    (3) In Step (2), if it returns no zone members for a certain zone, which does not match the switch zone configurations, that zone could include offline zone members.
 */

public class BNACQLURLConnection extends CIMXMLCQLURLConnection
{
    public BNACQLURLConnection(String connectionInfo, String username, String password)
    {
	super (connectionInfo, username, password, "/root/brocade1", "brocade_alias", "member", "alias_name");
    }

    public BNACQLURLConnection(java.net.URL url)
    {
        super(url, "/root/brocade1", "brocade_alias", "member", "alias_name");
	//setQuery(String.format("select %s,%s from %s ", propWWPN, propName, className) /* HERE */ );
    }
}
