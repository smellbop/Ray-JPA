package org.bpd.ray.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.bpd.ray.model.Ticket;
import org.bpd.ray.model.TicketCount;

@ApplicationScoped
public class TicketCountRepository {
	@Inject
	private EntityManager em;	
	
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
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> q = cb.createQuery(Long.class);
		Root<Ticket> ticket = q.from(Ticket.class);
		
		q.select(cb.count(ticket));
		
		Predicate w1 = cb.equal(ticket.get("customer"),customer);
		Predicate w2 = cb.equal(ticket.get("internalPriority"), i);
		Predicate w3 = ticket.get("status").in("INPROG","PENDING","QUEUED");
		Predicate w4 = cb.equal(ticket.get("ticketClass"),"SR");
		
		q.where(w1,w2,w3,w4);

		return em.createQuery(q).getSingleResult();
	}

	public List<String> fetchAllCustomers(){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Ticket> ticket = q.from(Ticket.class);
		
		q.multiselect(ticket.get("customer")).distinct(true);
		
		
		return em.createQuery(q).getResultList();
	}
	
	public List<TicketCount> findAllCounts() {
		List<TicketCount> ticketCounts = new ArrayList<TicketCount>();
	
		List<String> customers = fetchAllCustomers();
		
		for(String customer : customers){
			ticketCounts.add(new TicketCount(customer,getCountsForCustomer(customer)));
		}
		
//		for(Iterator it = customers.iterator(); it.hasNext();){
//			
//		}
		
		return ticketCounts;
	}

}
