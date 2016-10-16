package com.congun.web.model;

import java.util.Comparator;

public class MappingReqSort implements Comparator<ContractorRequirement> {


	@Override
	public int compare(ContractorRequirement o1, ContractorRequirement o2) {
		
		if(o1.createdTime.after(o2.createdTime)){
			return -1;
		}else if(o1.createdTime.before(o2.createdTime)){
			return 1;
		}else{
			return 0;
		}
		
	}

}
