package com.aquent.serviceimpl;

import java.util.Comparator;
import java.util.Date;

import com.aquent.model.Pizza;

public class SortByDescendingOrder implements Comparator<Pizza>{
	@Override
    public int compare(Pizza p1, Pizza p2) {
    	return new Date(p2.getTime() * 1000).compareTo(new Date(p1.getTime() * 1000));
    }

}
