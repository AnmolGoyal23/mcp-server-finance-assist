package com.ai.financialassistant.controller;

import com.ai.financialassistant.domain.StockData;
import com.ai.financialassistant.service.GptServiceImpl;
import com.ai.financialassistant.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class financialController {

  private final StockService stockService;
  private final GptServiceImpl gptServiceImpl;

  @Autowired
  public financialController(StockService stockService, GptServiceImpl gptServiceImpl) {
    this.stockService = stockService;
    this.gptServiceImpl = gptServiceImpl;
  }

  @GetMapping("/stock/{symbol}")
  public ResponseEntity<StockData> getStock(@PathVariable String symbol) {
    StockData data = stockService.fetchStockData(symbol);
    return ResponseEntity.ok(data);
  }

  @GetMapping("/stock/advice/{symbol}")
  public ResponseEntity<?> getStockAdvice(@PathVariable String symbol) {
    StockData data = stockService.fetchStockData(symbol);
    String advice = gptServiceImpl.getAdvice(data.getSymbol(), data.getCurrentPrice(), data.getPreviousClose());

    Map<String, Object> result = Map.of(
        "stock", data,
        "recommendation", advice
    );

    return ResponseEntity.ok(result);

  }
}
