package com.markina.nutera.graph;

import java.util.*;

/**
 * Created by mmarkina
 */
public class Graph<V, E> implements IGraph<V, E> {
  private final boolean directed;
  private final Map<V, Map<V, E>> vertices = new IdentityHashMap<>();

  public Graph(boolean directed) {
    this.directed = directed;
  }

  @Override
  public Graph<V, E> addEdge(V vertexFrom, V vertexTo, E edge) {
    if (isDirected()) {
      addDirectedEdge(vertexFrom, vertexTo, edge);
    } else {
      addDirectedEdge(vertexFrom, vertexTo, edge);
      addDirectedEdge(vertexTo, vertexFrom, edge);
    }
    return this;
  }

  @Override
  public boolean isDirected() {
    return directed;
  }

  @Override
  public boolean hasVertex(V vertex) {
    return vertices.keySet().contains(vertex);
  }

  @Override
  public Graph<V, E> addVertex(V vertex) {
    if (hasVertex(vertex)) {
      throw new IllegalArgumentException(String.format("Vertex %s already contains in graph", vertex));
    }
    vertices.put(vertex, new IdentityHashMap<>());
    return this;
  }

  @Override
  public List<E> getPath(V from, V to) {
    checkExists(from);
    checkExists(to);
    if (from == to) {
      return Collections.emptyList();
    }

    Map<V, V> parent = new IdentityHashMap<>();

    Queue<V> queue = new ArrayDeque<>();
    queue.add(from);

    while (!queue.isEmpty()) {
      V currentVertex = queue.poll();
      for (V childVertex : vertices.get(currentVertex).keySet()) {
        if (parent.containsKey(childVertex)) {
          continue;
        }
        parent.put(childVertex, currentVertex);
        if (childVertex == to) {
          return retrievePath(parent, from, to);
        }
        queue.add(childVertex);
      }
    }
    return null;
  }

  private void addDirectedEdge(V from, V to, E edge) {
    checkExists(from);
    checkExists(to);
    Map<V, E> existsVertices = vertices.get(from);
    if (existsVertices.containsKey(to)) {
      throw new IllegalArgumentException(String.format("Edge from %s to %s already exists", from, to));
    }
    existsVertices.put(to, edge);
  }

  private void checkExists(V vertex) {
    if (!vertices.containsKey(vertex)) {
      throw new IllegalArgumentException(String.format("Vertex %s isn't contained in graph", vertex));
    }
  }

  private List<E> retrievePath(Map<V, V> parent, V startVertex, V endVertex) {
    V currentVertex = endVertex;
    List<E> result = new ArrayList<>();
    while (currentVertex != startVertex) {
      result.add(vertices.get(parent.get(currentVertex)).get(currentVertex));
      currentVertex = parent.get(currentVertex);
    }
    Collections.reverse(result);
    return result;
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Graph{\n");
    stringBuilder.append("directed=").append(isDirected()).append("\n");
    vertices.forEach((fromVertex, toVertices) -> {
      if (toVertices.isEmpty()) {
        stringBuilder.append(fromVertex).append("\n");
        return;
      }
      if (isDirected()) {
        toVertices.forEach((toVertex, edge) ->
          stringBuilder.append(fromVertex).append(" -> ").append(toVertex).append(" (").append(edge).append(")").append("\n"));
      } else {
        toVertices.forEach((toVertex, edge) -> {
          if (System.identityHashCode(fromVertex) < System.identityHashCode(toVertex)) {
            stringBuilder.append(fromVertex).append(" <-> ").append(toVertex).append(" (").append(edge).append(")").append("\n");
          }
        });
      }
    });
    stringBuilder.append("}");
    return stringBuilder.toString();
  }
}
