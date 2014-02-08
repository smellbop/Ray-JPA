package org.bpd.ray.model;

public class TicketCount {
	private String customer;
	private Long total;
	private Long p1,p2,p3,p4,p5,p6;
	
	
	public TicketCount(String customer, Long pri[]) {
		super();
		this.customer = customer;
		this.total = pri[0];
		this.p1 = pri[1];
		this.p2 = pri[2];
		this.p3 = pri[3];
		this.p4 = pri[4];
		this.p5 = pri[5];
		this.p6 = pri[6];
	}
	

	
	
	public String getCustomer() {
		return customer;
	}
	public Long getTotal() {
		return total;
	}
	public Long getP1() {
		return p1;
	}
	public Long getP2() {
		return p2;
	}
	public Long getP3() {
		return p3;
	}
	public Long getP4() {
		return p4;
	}
	public Long getP5() {
		return p5;
	}
	public Long getP6() {
		return p6;
	}
	
	
	
}
