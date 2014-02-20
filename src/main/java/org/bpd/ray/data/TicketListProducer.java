package org.bpd.ray.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.bpd.ray.model.Ticket;

@RequestScoped
public class TicketListProducer {
	@Inject
	private TicketRepository ticketRepository;
	
	private List<Ticket> tickets;
	
	@Produces
	@Named
	public List<Ticket> getTickets(){
		return tickets;
	}
	
	public void onTicketListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Ticket ticket){
		retrieveAllTicketsOrderedById();
	}

	@PostConstruct
	private void retrieveAllTicketsOrderedById() {
		try {
			tickets = ticketRepository.findAllOrderedById();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
