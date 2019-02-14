package com.daniele.reassebler.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.daniele.reassebler.utils.Constants;

public class MarvelNieceImpl implements MarvelNiece{
	
	private ReassemblerService service;
	
	private String [] configs;

	public MarvelNieceImpl(String [] configs) {
		this.configs = configs;
		this.service =  new ReassemblerServiceImpl();
	}

	public void getCracking() throws IOException {
		 System.out.println( "Alright then, Uncle ... " );
		 
		 String filePathIn   = configs.length >= 1 ? configs[0] : Constants.DEFAULT_IN_PATH;
		 String splitPattern = configs.length >= 2 ? configs[1] : Constants.SPLIT_PATTERN;
		 String filePathOut  = configs.length >= 3 ? configs[2] : Constants.DEFAULT_OUT_PATH;
		 	
		 try ( BufferedReader reader = new BufferedReader(new FileReader(filePathIn)); 
			   BufferedWriter writer = new BufferedWriter(new FileWriter(filePathOut)))
		 {
			String line;
			while((line = reader.readLine()) != null){
				List<String> fragmentlist = new ArrayList<>();
				System.out.println( "Attempting to fix the line : '" + line + "' ...\n");
				
				String[] fragments = line.split(splitPattern);
				fragmentlist.addAll(Arrays.asList(fragments)); 
				
				System.out.println("fragmentlist="+fragmentlist);
				
				String defragmentedLine = service.reassemble(fragmentlist);
				
				System.out.println("\nHere is your fixed line : '" + defragmentedLine + "'");
				
				writer.write(defragmentedLine);
			}
			
		 }
		System.out.println( "I'm done, Uncle! Here is your fixed page : Please, 4-eyes check and let me know know. " );
	}

}
