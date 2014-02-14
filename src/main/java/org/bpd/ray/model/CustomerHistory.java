package org.bpd.ray.model;

import java.util.Date;

import javax.enterprise.inject.Default;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class CustomerHistory {
	@Id
	@GeneratedValue
	private Long historyId;
	
	@NotNull
	@Size(min=1,max=25)
	private String customer;
	
	@NotNull
	private Long total;
	
	@Temporal(TemporalType.DATE)
	@Column(columnDefinition="DATE DEFAULT CURRENT_DATE")
	private Date date;
}
