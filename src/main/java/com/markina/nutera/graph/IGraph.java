package com.markina.nutera.graph;

import java.util.List;

/**
 * Created by mmarkina
 */
public interface IGraph {
  Graph addVertex(Vertex vertex);

  Graph addEdge(Vertex from, Vertex to, Edge edge);

  boolean hasVertex(Vertex vertex);

  List<Edge> getPath(Vertex from, Vertex to);

  boolean isDirected();
}
