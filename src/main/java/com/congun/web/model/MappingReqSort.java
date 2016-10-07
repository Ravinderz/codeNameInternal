package com.congun.web.model;

import java.util.Comparator;

public class MappingReqSort implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		long reqId1 = ((ContractorRequirement) o1).requirementId;
		long reqId2 = ((ContractorRequirement) o2).requirementId;
		if (reqId1 < reqId2)
			return -1;
		else if (reqId1 > reqId2)
			return 1;
		else
			return 0;

	}

}
