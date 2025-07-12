package com.ai.financialassistant.service;

import com.ai.financialassistant.domain.StockData;

public interface StockService {

  StockData fetchStockData(String symbol);
}
