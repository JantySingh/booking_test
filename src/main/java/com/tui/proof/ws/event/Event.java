package com.tui.proof.ws.event;

import org.springframework.context.ApplicationEvent;

import java.util.HashMap;
import java.util.Map;


/**
 * Author: Janty
 */
public abstract class Event extends ApplicationEvent {
  private final Map<String, Object> headerMaps;

  /**
   *
   * @param source
   */
  public Event(Object source) {
    super(source);
    this.headerMaps = new HashMap<>();
  }

  /**
   *
   * @param k
   * @param v
   */
  protected void addHeader(String k, Object v){
    headerMaps.put(k, v);
  }

  /**
   *
   * @return
   */
  public Map<String, Object> getHeaders(){
    return new HashMap<>(headerMaps);
  }
}
