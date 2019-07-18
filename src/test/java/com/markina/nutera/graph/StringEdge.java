package com.markina.nutera.graph;

/**
 * Created by mmarkina
 */
public class StringEdge extends Edge {
  private String name;

  public StringEdge(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
