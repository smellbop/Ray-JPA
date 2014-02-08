package org.bpd.ray.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.bpd.ray.model.TicketCount;

@RequestScoped
public class TicketCountListProducer {
	@Inject
	private TicketCountRepository ticketCountRepo;
	
	private List<TicketCount> ticketCounts;
	
	@Produces
	@Named
	public List<TicketCount> getTicketCounts(){
		return ticketCounts;
	}
	
	@PostConstruct
	private void retrieveAllCounts(){
		ticketCounts = ticketCountRepo.findAllCounts();
	}
	
}
