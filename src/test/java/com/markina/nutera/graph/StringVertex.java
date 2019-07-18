package com.markina.nutera.graph;

/**
 * Created by mmarkina
 */
public class StringVertex extends Vertex {
  private String name;

  public StringVertex(String name) {
    this.name = name;
  }

  public void setName(String name) {
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
