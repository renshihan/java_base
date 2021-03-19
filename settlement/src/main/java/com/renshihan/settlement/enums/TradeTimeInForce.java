package com.renshihan.settlement.enums;

import lombok.Getter;

public enum TradeTimeInForce {
  GTC(1),
  IOC(2),
  FOK(3),
  ;

  @Getter
  private final Integer type;

  TradeTimeInForce(Integer type) {
    this.type = type;
  }
}
