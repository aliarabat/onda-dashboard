package com.onda.dashboard.util;

import java.util.Comparator;

import com.onda.dashboard.model.InterventionDay;

public class InterventionDayComparator implements Comparator<InterventionDay> {
    @Override
    public int compare(InterventionDay o1, InterventionDay o2) {
        return o1.getCallIntervention().compareTo(o2.getCallIntervention());
    }
}
