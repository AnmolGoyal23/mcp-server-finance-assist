package com.ai.financialassistant.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class GptRequests {
 private String model;
 private List<Map<String, String>> messages;
 private String temperature;

}
