package com.renshihan.settlement.enums;

import lombok.Getter;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public enum TradeOrderType {


  MARKET(1, "market"),
  LIMIT(2, "limit"),
  LIMIT_MAKER(3, "limit-maker"),
  STOP_LIMIT(4, "stop-limit")
  ;

  @Getter
  private final Integer type;
  @Getter
  private final String desc;

  TradeOrderType(Integer type, String desc) {
    this.type = type;
    this.desc = desc;
  }

  private static final Set<TradeOrderType> LIMIT_TYPES = EnumSet.of(LIMIT, LIMIT_MAKER);

  private static final Map<Integer, TradeOrderType> MAPPING = new TreeMap<>();

  static {
    for (TradeOrderType tradeOrderType : values()) {
      MAPPING.put(tradeOrderType.getType(), tradeOrderType);
    }
  }

  public boolean isLimit(){
    return LIMIT_TYPES.contains(this);
  }


  public boolean isStopLimit(){
    return STOP_LIMIT.equals(this);
  }

}
