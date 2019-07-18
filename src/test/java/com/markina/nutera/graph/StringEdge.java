package com.markina.nutera.graph;

/**
 * Created by mmarkina
 */
public class StringEdge<V> extends Edge<V> {
  private String name;

  public StringEdge(V from, V to, String name) {
    super(from, to);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }
}
