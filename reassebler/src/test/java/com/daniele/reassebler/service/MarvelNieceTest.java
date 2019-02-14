package com.daniele.reassebler.service;

import java.io.IOException;

import com.daniele.reassebler.utils.Constants;
import com.daniele.reassebler.utils.FragmentsUtil;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class MarvelNieceTest 
    extends TestCase
{
   
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( MarvelNieceTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws IOException 
     */
    public void testgetCracking() throws IOException
    {
    	
    	String inputFilePath = Constants.DEFAULT_IN_PATH ;
    	String split_Pattern = Constants.SPLIT_PATTERN;
    	String outputFilePath = Constants.SPLIT_PATTERN ;
    	
    	final String EXPECTED_OUTPUT_FILE_PATH = "output/defragmentedFile_expected.txt" ;
    	
        String [] configs = {inputFilePath, split_Pattern, outputFilePath };
    	
    	MarvelNiece niece = new MarvelNieceImpl(configs);
    	
    	niece.getCracking();

    	assertTrue( FragmentsUtil.compareFilesbyByte(outputFilePath,EXPECTED_OUTPUT_FILE_PATH) );
    }
}
