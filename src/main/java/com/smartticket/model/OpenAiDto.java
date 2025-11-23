package com.smartticket.model;

import java.util.List;

// This single file handles all the JSON mapping!
public class OpenAiDto {

    // 1. The Request we send to OpenAI
    public record ChatRequest(String model, List<Message> messages) {}

    // 2. The Message structure (System/User)
    public record Message(String role, String content) {}

    // 3. The Response we get back
    public record ChatResponse(List<Choice> choices) {}

    // 4. The specific choice wrapper
    public record Choice(Message message) {}
}