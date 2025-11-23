package com.smartticket.controller;

import com.smartticket.model.Ticket;
import com.smartticket.service.AgentService;
import com.smartticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*") 
public class TicketController {

    @Autowired
    private AgentService agentService;
    
    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return agentService.createAndAnalyzeTicket(ticket);
    }
    
    
    @PostMapping("/batch")
    public List<Ticket> createBatch(@RequestBody List<Ticket> tickets) {
        return agentService.processBatch(tickets);
    }
}