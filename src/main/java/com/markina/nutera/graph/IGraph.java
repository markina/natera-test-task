package com.markina.nutera.graph;

import java.util.List;

/**
 * Created by mmarkina
 */
public interface IGraph<V, E> {
  Graph addVertex(V vertex);

  Graph addEdge(V from, V to, E edge);

  boolean hasVertex(V vertex);

  List<E> getPath(V from, V to);

  boolean isDirected();
}
