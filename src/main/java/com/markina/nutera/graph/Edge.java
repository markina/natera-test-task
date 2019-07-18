package com.markina.nutera.graph;

/**
 * Created by mmarkina
 */
public class Edge<V> {
  private V from;
  private V to;

  public Edge(V from, V to) {
    this.from = from;
    this.to = to;
  }

  public V getFrom() {
    return from;
  }

  public V getTo() {
    return to;
  }

  @Override
  public String toString() {
    return "Edge{" +
      "from=" + from +
      ", to=" + to +
      '}';
  }
}
