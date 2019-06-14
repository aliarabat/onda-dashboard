package com.onda.dashboard.util;

import java.util.Comparator;

import com.onda.dashboard.rest.vo.InterventionMonthVo;

public class InterventionMonthComparator implements Comparator<InterventionMonthVo> {
	@Override
	public int compare(InterventionMonthVo o1, InterventionMonthVo o2) {
		return o1.getTbf().compareTo(o2.getTbf());
	}
}
