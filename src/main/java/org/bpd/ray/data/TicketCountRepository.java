package org.bpd.ray.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.bpd.ray.model.CustomerHistory;
import org.bpd.ray.model.Ticket;
import org.joda.time.LocalDate;

@ApplicationScoped
public class TicketCountRepository {
	@Inject
	EntityManager sdEm;
	
	public Long[] getCountsForCustomer(String customer){
		Long[] counts = new Long[7];
		Long sum = new Long(0);
		
		for(int i=1;i<7;i++){
			counts[i] = getCountForPriority(customer, i);
			sum += counts[i];
		}
		
		counts[0] = sum;
		
		return counts;
	}

	private Long getCountForPriority(String customer, int i) {
		CriteriaBuilder cb = sdEm.getCriteriaBuilder();
		CriteriaQuery<Long> q = cb.createQuery(Long.class);
		Root<Ticket> ticket = q.from(Ticket.class);
		
		q.select(cb.count(ticket));
		
		Predicate w1 = cb.equal(ticket.get("customer"),customer);
		Predicate w2 = cb.equal(ticket.get("internalPriority"), i);
		Predicate w3 = ticket.get("status").in("INPROG","PENDING","QUEUED");
		Predicate w4 = cb.equal(ticket.get("ticketClass"),"SR");
		
		q.where(w1,w2,w3,w4);

		return sdEm.createQuery(q).getSingleResult();
	}

	public List<String> fetchAllCustomers(){
		List<String> include = new ArrayList<String>();
		include.add("CARLISLE");
		include.add("CNR");
		include.add("DRAX");
		include.add("FES");
		include.add("MIAMI");
		include.add("MOJ");
		include.add("PREMIER");
		include.add("SERCO");
		include.add("TES");
		include.add("TFM");
	
		CriteriaBuilder cb = sdEm.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Ticket> ticket = q.from(Ticket.class);
		
		q.multiselect(ticket.get("customer")).distinct(true);
		
		q.where(cb.isNotNull(ticket.get("customer")), cb.isTrue(ticket.get("customer").in(include)));
		
		return sdEm.createQuery(q).getResultList();
	}
	
	public List<TicketCount> findAllCounts() {
		List<TicketCount> ticketCounts = new ArrayList<TicketCount>();
	
		List<String> customers = fetchAllCustomers();
		
		for(String customer : customers){
			TicketCount tc = new TicketCount(customer,getCountsForCustomer(customer));
			//Set this when table/database exists
			//tc.setUp(isItUp(customer, tc.getTotal())); 
			tc.setUp(false);
			ticketCounts.add(tc);
			
		}
		
		return ticketCounts;
	}

	private Boolean isItUp(String customer, Long tot) {
		Long yesterday = new Long(1000);
		
		CriteriaBuilder cb = sdEm.getCriteriaBuilder();
		CriteriaQuery<Long> q = cb.createQuery(Long.class);
		Root<CustomerHistory> hist = q.from(CustomerHistory.class);
		
	
		q.multiselect(hist.get("total"));
		
		//Date todayDate = new LocalDate().toDateTimeAtStartOfDay().toDate();
		Date yesterDate = new LocalDate().minusDays(1).toDateTimeAtStartOfDay().toDate();
		
		Predicate w1 = cb.equal(hist.<Date>get("date"), yesterDate);
		//Predicate w1 = cb.between(hist.<Date>get("date"), yesterDate, todayDate);
		Predicate w2 = cb.equal(hist.get("customer"), customer);
		//TODO check for null
		//Predicate w3

		q.where(w1,w2);
		
		
		try {
			yesterday = sdEm.createQuery(q).getSingleResult();
		} catch (Exception e) {
			System.out.println("No data from previous day.");
			e.printStackTrace();
		}
		
		
		
		return yesterday < tot;
	}

}
