package com.smartticket.service;

import com.smartticket.model.Ticket;
import com.smartticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket createAndAnalyzeTicket(Ticket ticket) {
        
        String desc = ticket.getDescription().toLowerCase();

        
        if (desc.contains("crash") || desc.contains("data loss") || desc.contains("emergency") || desc.contains("hack")) {
            ticket.setAiPriority("HIGH");
            ticket.setAiSuggestedResponse("CRITICAL ALERT: This case has been flagged for immediate Engineering review. SLA: 15 minutes.");
        } 
        
        else if (desc.contains("refund") || desc.contains("billing") || desc.contains("invoice") || desc.contains("payment")) {
            ticket.setAiPriority("MEDIUM");
            ticket.setAiSuggestedResponse("Routed to Finance Dept. Please verify the transaction ID in the attached logs.");
        } 
        
        else {
            ticket.setAiPriority("LOW");
            ticket.setAiSuggestedResponse("Thank you for contacting support. An agent will review your request within 24 hours.");
        }

        ticket.setStatus("PROCESSED_BY_RULE_ENGINE");
        return ticketRepository.save(ticket);
    }
    
    
    public List<Ticket> processBatch(List<Ticket> tickets) {
        for (Ticket t : tickets) {
            createAndAnalyzeTicket(t);
        }
        return tickets;
    }
} 
