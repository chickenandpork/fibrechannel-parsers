/* smallfoot.org is owned by allan.clark */
package org.smallfoot.parser.dcnmcql;

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
 * This class provides the connection info to query a DCNM service via CIM-XML and CQL (WQL) Query
 *
 * @page formats Compatible File Formats
 * @subpage DCNMCQLURLConnection
 *
 * @page DCNMCQLURLConnection Cisco-specific objects from CIM-XML CIMOM Database (DCNM)
 *
 * The DCNMCQLURLConnection collects dcnmcql:// Cisco_DeviceAlias from a DCNM Server via CIM-XML Database
 *
 * This collector/parser chain is more successful than text-based parsers simply because
 * it avoids human-error and the error of not reading the warning to avoid putty log traces
 * and other "nearly 100% unmodified" methods of transferring content.  Additionally, this
 * is an entirely binary connection, so the user cannot accidentally edit the content.
 * Finally, as DCNM may harvest from and provide config to many fabrics at once, this is
 * typically "a broader net to cast" to collect information.
 */

public class DCNMCQLURLConnection extends CIMXMLCQLURLConnection
{
    public DCNMCQLURLConnection(String connectionInfo, String username, String password)
    {
	super (connectionInfo, username, password, "/cimv2", "CISCO_DeviceAlias", "pwwn", "name");
    }

    public DCNMCQLURLConnection(java.net.URL url)
    {
        super(url, "/cimv2", "CISCO_DeviceAlias", "pwwn", "name");
    }
}
