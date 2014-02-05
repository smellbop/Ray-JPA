package org.bpd.ray.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Ticket {
	@Id
	@Column(name="TICKETID")
	private int ticketId;
	
	@NotNull
	@NotEmpty
	@Column(name="CLASS")
	private String ticketClass;
	
	@NotNull
	@NotEmpty
	private String status;
	
	@NotNull
	@NotEmpty
	@Column(name="SITEID")
	private String customer;
	
	private int internalPriority;
	
	/*-----------------------
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + internalPriority;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((ticketClass == null) ? 0 : ticketClass.hashCode());
		result = prime * result + ticketId;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (internalPriority != other.internalPriority)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (ticketClass == null) {
			if (other.ticketClass != null)
				return false;
		} else if (!ticketClass.equals(other.ticketClass))
			return false;
		if (ticketId != other.ticketId)
			return false;
		return true;
	}


	public int getTicketId() {
		return ticketId;
	}


	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}


	public String getTicketClass() {
		return ticketClass;
	}


	public void setTicketClass(String ticketClass) {
		this.ticketClass = ticketClass;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getCustomer() {
		return customer;
	}


	public void setCustomer(String customer) {
		this.customer = customer;
	}


	public int getInternalPriority() {
		return internalPriority;
	}


	public void setInternalPriority(int internalPriority) {
		this.internalPriority = internalPriority;
	}


}
