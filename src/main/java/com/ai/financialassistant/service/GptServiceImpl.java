package com.ai.financialassistant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GptServiceImpl {

  private final RestTemplate restTemplate = new RestTemplate();
  private final ObjectMapper objectMapper = new ObjectMapper();

  public String getAdvice(String stockName, String price, String prevClose) {
    String prompt = String.format(
        "Act like a financial advisor. I want to invest in %s. Current price is %s and previous close was %s. Should I buy, sell, or hold?",
        stockName, price, prevClose
    );

    // Ollama request payload
    Map<String, Object> requestBody = Map.of(
        "model", "llama3:8b",
        "prompt", prompt,
        "stream", false
    );

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

    try {
      ResponseEntity<String> response = restTemplate.postForEntity(
          "http://localhost:11434/api/generate",
          request,
          String.class
      );

      JsonNode root = objectMapper.readTree(response.getBody());
      return root.get("response").asText();

    } catch (Exception e) {
      return "Error communicating with LLaMA: " + e.getMessage();
    }
  }
}
