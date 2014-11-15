package org.smallfoot.parser.zone;

import java.io.*;
import java.util.*;


public class SwappedNicknameParser extends NicknameParser
{
    /**
     * Create a parser, setting the debug to true or false
     *
     * @param properties additional name-value pair collection
     */
    public SwappedNicknameParser(java.util.Properties properties)
    {
        super (properties, 1, 0);
    }
}
