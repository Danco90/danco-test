package com.daniele.reassebler.usecase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.daniele.reassebler.utils.FragmentsUtil;

public class LineDefragmentationUsecase {
	
	
	/**
	 * 
	 * @param fragments
	 * @return
	 */
	public List<String> defragLine(List<String> fragments){
		final List<String> defragmented = new ArrayList<>(fragments);
		
		final List<String> fixedFragments = new ArrayList<>(fragments);
		fragments.forEach( 
					iFrag -> {					
							Map<String, String> overlapPairs =  locatePairWithMaxOverlapMatches3(iFrag, fixedFragments);
							if(overlapPairs.size() > 0) {
								String  merged = mergeOverlapPair(iFrag, overlapPairs.get(iFrag));
								
								int index = defragmented.indexOf(iFrag);
								if(index!=-1) 
								{
								  defragmented.set(index, merged);
								  defragmented.remove(overlapPairs.get(iFrag));
								}
							}
					});
       //Recoursively defragmentation of the fragments left
		if(defragmented.size()>1) {
			return defragLine(defragmented);
		}
		return defragmented;
	}
	

	private Map<String, String> locatePairWithMaxOverlapMatches3(String iFrag, List<String> fragments) {
		Map<String, Integer> pairsFullMap    = new HashMap<>();
		Map<String, String>  pairsReducedMap = new HashMap<>(); 
		
		final List<String> fixedFragments = new ArrayList<>(fragments);
		fixedFragments.forEach( 
				jFrag -> { 
						if(!iFrag.equals(jFrag) && iFrag.length() > 1 ){ 
							pairsFullMap.put(jFrag, getMaxOverlap(iFrag, jFrag) );
						}
				});
		
		if(pairsFullMap.size() > 0) {
			//Reduce pairs
			Map.Entry<String, Integer> maxEntry = pairsFullMap.entrySet().stream()
													.max(Map.Entry.comparingByValue()).get();
			
			List<String> overlaps = pairsFullMap.entrySet().stream()
													.filter( e -> e == maxEntry)
													.map(Map.Entry::getKey)
													.collect(Collectors.toList());
			String maxOverlappingFragment = FragmentsUtil.getRandomRes(overlaps);
			
			pairsReducedMap.put(iFrag, maxOverlappingFragment );
		}
		
		return pairsReducedMap;
	
	}
	
	private int getMaxOverlap(final String frag1, final String frag2) {
		final int f1len = frag1.length();
	    final int f2len = frag2.length();
	    final int maxlen = Integer.min(f1len, f2len);
	    // from the longest overlap to the shortest possible.
	    for (int len = maxlen; len > 0; len--) {
	         // sliding window into f1 from 0
	        for (int toffset = 0; toffset + len <= f1len; toffset++) {
	             // sliding window into f2 from 0
	            for (int ooffset = 0; ooffset + len <= f2len; ooffset++) {
	            	
	        		if (frag1.regionMatches(toffset, frag2, ooffset, len)) {
	                		System.out.println("Found region matches frag1='"+frag1+"', toffset=" + toffset + "; frag2='"+frag2+"', " + ooffset + ", " + len + ")");	                    
	                		return len;
	                }
	             }
	         }
	     }
	   return 0;
	}

	private String mergeOverlapPair(String f1, String f2) {
		String s1 = f1.length() > f2.length() ? f1 : f2 ;
		String s2 = s1.equals(f1) ? f2 : f1; 
		
		String overlap = getOverlapString(s1, s2);
		if( overlap.equals(s2))  
		{   return s1;
			
		}else if(!containsForbiddenIncludedOverlap(s1, overlap)
					&& containsTailOverlap(s1, overlap)) {
			return s1.substring(0, s1.indexOf(overlap)) + s2;
			
		}else if(!containsForbiddenIncludedOverlap(s1, overlap)
				&& containsHeadOverlap(s1, overlap) && !s1.equals(overlap)) {
			return s2 + s1.substring(overlap.length());
			
		}else {
			return s1;
		}

	}
	

	private String getOverlapString(String s1, String s2) {
		final int f1len = s1.length();
	    final int f2len = s2.length();
	    final int maxlen = Integer.min(f1len, f2len);
	    String overlap = "";
	     // from the longest overlap to the shortest possible.
	     for (int len = maxlen; len > 0; len--) {
	         // sliding window into f1 from 0
	         for (int toffset = 0; toffset + len <= f1len; toffset++) {
	             // sliding window into f2 from 0
	             for (int ooffset = 0; ooffset + len <= f2len; ooffset++) {
	                 if (s1.regionMatches(toffset, s2, ooffset, len)) {
	                	 	System.out.println("Found region match fi="+s1+" toffset=" + toffset + "; f2="+s2+", " + ooffset + ", " + len + ")");
	                    overlap= s1.substring(toffset, toffset + len);
	                		if(validateOverlap(s1, s2, overlap)) {
	                			 return overlap;
	                		}
	                 }
	             }
	         }
	     }
     return overlap;
	}
	
	
	private boolean validateOverlap(String s1, String s2, String overlap) {
		return (!containsForbiddenIncludedOverlap(s1, overlap)
        		    && (s1.contains(s2) || overlap.equals(s2)   
  				|| containsTailOverlap(s1, overlap)
  				|| containsHeadOverlap(s1, overlap) )) ;	 
	}

	
	
	private boolean containsTailOverlap(String longerString, String overlap) {
		return longerString.indexOf(overlap)>0;
	}
	
	private boolean containsHeadOverlap(String longerString, String overlap) {
		return longerString.indexOf(overlap)==0 ;
	}
	
	private boolean containsForbiddenIncludedOverlap(String longerString, String overlap) {
		int last = longerString.length() - 1;
		int overlapStart = longerString.indexOf(overlap);
		int overlapEnd = overlapStart + overlap.length() - 1;
		
		return overlapStart > 0 && overlapEnd < last;
	}

}
