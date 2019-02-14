package com.daniele.reassebler.usecase;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class DefragmentationUsecaseTest 
    extends TestCase
{
   
    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( DefragmentationUsecaseTest.class );
    }

    /**
     * Rigourous Test :-)
     * @throws IOException 
     */
    public void testDefragment2() throws IOException
    {

    	LineDefragmentationUsecase usecase = new LineDefragmentationUsecase();
    	
    	List<String> fragments = Arrays.asList("O draconia", "conian devil! Oh la", "h lame sa", "saint!"); 
       //O draconian devil! Oh lame saint!
    	List<String> expected = Arrays.asList("O draconian devil! Oh lame saint!"); 
   
    	List<String> defragmentedList = usecase.defragLine(fragments);
    	
    	System.out.println("defragmentedList.size="+defragmentedList.size());
    	
    	assertEquals(expected, defragmentedList);

    }
}
