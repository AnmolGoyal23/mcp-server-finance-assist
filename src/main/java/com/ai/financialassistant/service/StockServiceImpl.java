package com.ai.financialassistant.service;

import com.ai.financialassistant.domain.StockData;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService{
  @Override
  public StockData fetchStockData(String symbol) {
    return new StockData(symbol.toUpperCase(), "1530.01", "1490.00");
  }
}
