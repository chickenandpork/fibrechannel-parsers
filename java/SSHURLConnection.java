/* smallfoot.org is owned by allan.clark */
package org.smallfoot.parser.ssh;

//import java.io.*;
//import java.util.*;
//import javax.activation.DataSource;
//import javax.activation.URLDataSource;
//import gnu.getopt.Getopt;
//import gnu.getopt.LongOpt;
import java.io.IOException;
import java.net.SocketException;
import java.net.URLDecoder;
import java.security.PublicKey;
import org.apache.commons.net.ssh.SSHClient;
import org.apache.commons.net.ssh.connection.ConnectionException;
import org.apache.commons.net.ssh.connection.Session;
import org.apache.commons.net.ssh.transport.TransportException;
import org.apache.commons.net.ssh.userauth.UserAuthException;
import org.apache.commons.net.ssh.SSHException;
import org.apache.commons.net.ssh.HostKeyVerifier;

/**
 * @file
 *
 * @page formats Compatible File Formats
 * @subpage SSHURLConnection
 *
 * @page SSHURLConnection SSH Query
 *
 * The SSHURLConnection batch-executes a command via SSH
 */

public class SSHURLConnection extends java.net.URLConnection
{
    protected SSHClient client;

    public SSHURLConnection(java.net.URL url)
    {
        super(url);
	client = new SSHClient();
	//client.initUserKnownHosts();
	//if (url.getPort() != client.DEFAULT_PORT)
	    

        client.addHostKeyVerifier( new HostKeyVerifier()
        {
            public boolean verify(String hostname, PublicKey key)
            {
                return true;
            }
        });

    }

    /**
     * Stubbed, because there's no change we want to accept yet.
     *
     * @param name ignored
     * @param value ignored
     */
    public void setRequestHeader(String name, String value) { }

    /**
     * Stubbed, because there's no change we want to accept yet.
     *
     * @param ignored ignored
     */
    public void setContentType(String ignored) { }

    /**
     * Stubbed, because there's nothing to send us.
     */
    public java.io.OutputStream getOutputStream()
    {
        return null;
    }


    /**
     * openConnection(java.net.URL) overrides java.net.URLStreamHandler.openConnection(URL) by wrapping a pgsql client.
     *
     * @return populated connection as org.smallfoot.dcnmsql.SSHURLConnection(Connection)
     * @param url URL to connect to
     */
    protected SSHURLConnection openConnection(java.net.URL url)
    {
        return null;
    }

    public void connect()
    {
//System.out.println("connect()");
 	try
 	{
            _connect();
 	}
	catch (Exception e) { System.err.println("Exception while communicating with "+url.getHost()); System.err.println(e); }
    }

    /**
     * @throw SocketException from client.connect()
     * @throw UserAuthException from client.authPassword(...)
     * @throw IOException from client.connect()
     */
    protected void _connect() throws IOException, SocketException, UserAuthException
    {
//System.out.println("_connect()");
	if (! client.isConnected())
        {
	    client.connect(url.getHost(),url.getPort());
            if (null != url.getUserInfo())
            {
	        String user = null, pass = null;

                String[] a = url.getUserInfo().split(":");
                if (1 <= (java.lang.reflect.Array.getLength(a)))
                    user = a[0];
                if (2 <= (java.lang.reflect.Array.getLength(a)))
                    pass = a[1];
        	/**
	         * @jvmopt <b>debug.dumpSSHAuthInfo</b>
	         * (values: true, false)
	         * can be used to dump out the User/Pass Authentication info before the client connects to the remote server.  Note that this can cause plaintext revelation of private credential info
	         *
	         * @jvmopt
	         * @code java -Ddebug.dumpSSHAuthInfo=true -jar fcparser.jar -N ...  @endcode
	         */
        	if (false != Boolean.parseBoolean(System.getProperties().getProperty("debug.dumpSSHAuthInfo")))
            System.out.println("running with: u ["+user+"] p ["+pass+"]");

 	        client.authPassword(user, pass);
	    }
	}
    }


    private Session _session = null;
    /**
     * @throw IOException from client.connect() in _connect()
     */
    protected Session _session() throws ConnectionException, IOException, TransportException
    {
//System.out.println("_session()");
	_connect();
	if (null == _session) { _session = client.startSession(); }

	return _session;
    }

    public void exec(String s) throws SSHException, IOException
    {
	_session().exec((null == s ? URLDecoder.decode(url.getPath().replaceFirst("^/","")) : s));
    }

    public void disconnect()
    {
	try
	{
 	    client.getConnection().join();
 	    if (client.isConnected()) client.disconnect();
	}
	catch (java.lang.InterruptedException ie) { System.err.println("Interrupted: "+ie.getMessage()); }
	catch (java.io.IOException ioe) { System.err.println("I/O Exception disconnecting: "+ioe.getMessage()); }
    }

    public String server() { return url.toString(); }

    public String command() { return URLDecoder.decode(url.getPath().replaceFirst("^/","")); }
}
