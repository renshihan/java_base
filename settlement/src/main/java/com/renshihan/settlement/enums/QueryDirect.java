package com.renshihan.settlement.enums;

import com.google.common.collect.Lists;

import java.util.List;

public enum QueryDirect {

  PREV("prev") {
    @Override
    public <T> List<T> desc(List<T> data) {
      return Lists.reverse(data);
    }
  }, NEXT("next");

  private final String value;


  QueryDirect(String v) {
    this.value = v;
  }

  public String getValue() {
    return value;
  }

  public static QueryDirect find(String value) {
    for (QueryDirect e : QueryDirect.values()) {
      if (e.getValue().equals(value)) {
        return e;
      }
    }
    return NEXT;
  }

  public <T> List<T> desc(List<T> data) {
    return data;
  }
}
