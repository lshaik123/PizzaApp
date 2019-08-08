package com.aquent.model;

public class Pizza {

	private String order;
	private long time;

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

//	@Override
//	public int compare(Pizza o1, Pizza o2) {
//		return new Date(o1.getTime()).compareTo(new Date(o2.getTime()));
//	}
//
//	@Override
//	public String toString() {
//		return "Pizza [order=" + order + ", time=" + time + "]";
//	}

}
