package com.daniele.reassebler.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FragmentsUtil {
	
	public static String getRandomRes(List<String> commons) {
		int randomIndex = new Random().nextInt(commons.size());
		String randomValue = commons.get(randomIndex);
		
		return randomValue;
	}
	
	public static String getLongest(String s1, String s2) {
		if(s2.length() > s1.length()) {
			 return s2;
		}else { return s1; }	
	}
	
	//reading bytes and compare
    public static boolean compareFilesbyByte(String file1, String file2) throws IOException
    {
        File f1=new File(file1);
        File f2=new File(file2);
        try( FileInputStream fis1 = new FileInputStream (f1);
        		 FileInputStream fis2 = new FileInputStream (f2) )
        {
	        if (f1.length()==f2.length())
	            {
	                int n=0;
	                byte[] b1;
	                byte[] b2;
	                while ((n = fis1.available()) > 0) {
	                    if (n>80) n=80;
	                    b1 = new byte[n];
	                    b2 = new byte[n];
	                    fis1.read(b1);
	                    fis2.read(b2);
	                    if (Arrays.equals(b1,b2)==false)
	                        {
	                            System.out.println(file1 + " :\n\n " + new String(b1));
	                            System.out.println();
	                            System.out.println(file2 + " : \n\n" + new String(b2));
	                            return false;
	                        }
	                }
	            }
	        else return false;  // length is not matched. 
        }
        return true;
    }

}
