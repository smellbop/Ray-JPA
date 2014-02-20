package org.bpd.ray.data;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

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
		try {
			ticketCounts = ticketCountRepo.findAllCounts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
