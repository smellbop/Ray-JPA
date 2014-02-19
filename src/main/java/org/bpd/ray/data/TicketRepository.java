package org.bpd.ray.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.bpd.ray.model.Ticket;

@ApplicationScoped
public class TicketRepository {
	@Inject
	EntityManager sdEm;

	public List<Ticket> findAllOrderedById() {
		CriteriaBuilder cb = sdEm.getCriteriaBuilder();
		CriteriaQuery<Ticket> criteria = cb.createQuery(Ticket.class);
		Root<Ticket> ticket = criteria.from(Ticket.class);
		
		criteria.select(ticket).orderBy(cb.asc(ticket.get("ticketId")));
		return sdEm.createQuery(criteria).getResultList();
	}

}
