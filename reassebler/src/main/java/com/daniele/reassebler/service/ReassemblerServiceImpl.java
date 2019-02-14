package com.daniele.reassebler.service;

import java.util.List;
import java.util.stream.Collectors;

import com.daniele.reassebler.usecase.LineDefragmentationUsecase;

public class ReassemblerServiceImpl implements ReassemblerService {
	
	private LineDefragmentationUsecase lineDefragmentUsecase;
	
	public ReassemblerServiceImpl() {
		this.lineDefragmentUsecase = new LineDefragmentationUsecase();
	}

	public String reassemble(List<String> fragments) {
		
		List<String> defragmentedList = lineDefragmentUsecase.defragLine(fragments);
		
		System.out.println("\n::reassemble() result: defragmentedList="+defragmentedList);
		
		return defragmentedList.stream().map(Object::toString).collect(Collectors.joining(" "));
	
	}

}
