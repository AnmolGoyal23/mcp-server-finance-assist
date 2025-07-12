package com.ai.financialassistant.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StockData {
  private String symbol;
  private String currentPrice;
  private String previousClose;
}
