package org.smallfoot.parser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import org.smallfoot.parser.zone.ZoneParser;

public class ParserTee extends org.apache.commons.io.input.TeeInputStream
{
    /*
     *
     *
     * The intent is to be able to write:
     * Vector<ZoneParser> v = new Vector(2,2);
     *
     * (insert a bunch of ZoneParsers into v)
     * ParserTee pt = new ParserTee (File().stream());
     *
     * for (ZoneParser zp: v.items())
     *    pt = new ParserTee (pt, zp);
     *
     * refs:
     *
     * http://stackoverflow.com/questions/5778658/java-converting-from-outputstream-to-inputstream
     * http://www.coderanch.com/t/275464//java/OutputStream-InputStream
     * http://stackoverflow.com/questions/1225909/most-efficient-way-to-create-inputstream-from-outputstream
     * http://ostermiller.org/convert_java_outputstream_inputstream.html
     * http://svn.apache.org/viewvc/commons/proper/io/trunk/src/main/java/org/apache/commons/io/input/TeeInputStream.java?view=markup
     * http://commons.apache.org/proper/commons-io/apidocs/src-html/org/apache/commons/io/input/NullInputStream.html#line.94
     */

    /**
     * Tee off the datastream and set it to sink into the given ZoneParser
     *
     * @return new OutputStream connected to the ZoneParser's input to which the tee or fork of the incoming datastream can/should be dumped
     * @param zp a ZoneParser to consume a copy of the incoming datastream
     * @throw java.io.IOException if PipedOutputStream(PipedInputStream) throws the same
     */
    public static OutputStream pipeOutputStreamTo (ZoneParser zp) throws java.io.IOException
    {
        PipedInputStream ip = new PipedInputStream();
        //zp.setReader(new BufferedReader(new InputStreamReader(ip)));
        zp.setReader(new InputStreamReader(ip));
        zp.start();
        return new PipedOutputStream(ip);
    }

    /**
     * Create a link in a chain of parser tees
     *
     * @param input the incoming datastream to read from and pass through
     * @param zp a ZoneParser to consume a copy of the incoming datastream
     * @throw java.io.IOException if PipedOutputStream(PipedInputStream) throws the same
     */
    public ParserTee (InputStream input, ZoneParser zp) throws java.io.IOException
    {
        super(input, pipeOutputStreamTo(zp), true);
    }
}
