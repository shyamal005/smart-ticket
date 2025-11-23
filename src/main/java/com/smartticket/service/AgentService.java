package com.smartticket.service;

import com.smartticket.model.OpenAiDto.*;
import com.smartticket.model.Ticket;
import com.smartticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class AgentService {

    @Autowired
    private TicketRepository ticketRepository;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}") // We load the URL from config now
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public Ticket createAndAnalyzeTicket(Ticket ticket) {
        // 1. System Prompt (The Persona)
        String systemPrompt = """
            You are a helpful customer support AI.
            Analyze the user's complaint.
            Determine priority (HIGH, MEDIUM, LOW).
            Draft a short, professional response.
            
            Strictly format your answer as:
            PRIORITY|RESPONSE
            """;

        String userMessage = "Customer Issue: " + ticket.getDescription();

        // 2. Build Request (Same format as OpenAI!)
        ChatRequest request = new ChatRequest(
                model,
                List.of(new Message("system", systemPrompt), new Message("user", userMessage))
        );

        // 3. Headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);

        try {
            // 4. Call the API (Groq)
            ChatResponse response = restTemplate.postForObject(
                    apiUrl,
                    entity,
                    ChatResponse.class
            );

            if (response != null && !response.choices().isEmpty()) {
                String content = response.choices().get(0).message().content();
                String[] parts = content.split("\\|", 2);

                if (parts.length == 2) {
                    ticket.setAiPriority(parts[0].trim());
                    ticket.setAiSuggestedResponse(parts[1].trim());
                } else {
                    ticket.setAiPriority("MEDIUM");
                    ticket.setAiSuggestedResponse(content);
                }
            }
            ticket.setStatus("PROCESSED_BY_LLAMA");

        } catch (Exception e) {
            System.err.println("AI Error: " + e.getMessage());
            ticket.setAiPriority("UNKNOWN");
            ticket.setAiSuggestedResponse("AI Service unavailable.");
            ticket.setStatus("AI_ERROR");
        }

        return ticketRepository.save(ticket);
    }
    
    public List<Ticket> processBatch(List<Ticket> tickets) {
        for (Ticket t : tickets) {
            createAndAnalyzeTicket(t);
        }
        return tickets;
    }
}