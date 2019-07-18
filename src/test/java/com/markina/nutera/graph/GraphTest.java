package com.markina.nutera.graph;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * Created by mmarkina
 */
public class GraphTest {
  @Test
  public void isDirected() {
    Graph directedGraph = new Graph(true);
    assertTrue(directedGraph.isDirected());

    Graph undirectedGraph = new Graph(false);
    assertFalse(undirectedGraph.isDirected());
  }

  @Test
  public void directedVerticesWithoutEdges() {
    StringVertex v1 = new StringVertex("v1");
    StringVertex v2 = new StringVertex("v2");
    Graph<StringVertex, StringEdge<StringVertex>> graph = new Graph<>(true);
    graph
      .addVertex(v1)
      .addVertex(v2);

    assertNull("Path doesn't exists", graph.getPath(v1, v2));
    assertNull("Path doesn't exists", graph.getPath(v2, v1));
  }

  @Test
  public void undirectedVerticesWithoutEdges() {
    StringVertex v1 = new StringVertex("v1");
    StringVertex v2 = new StringVertex("v2");
    Graph<StringVertex, StringEdge<StringVertex>> graph = new Graph<>(false);
    graph
      .addVertex(v1)
      .addVertex(v2);

    assertNull("Path doesn't exists", graph.getPath(v1, v2));
    assertNull("Path doesn't exists", graph.getPath(v2, v1));
  }

  @Test(expected = IllegalArgumentException.class)
  public void directedVertexNotExists() {
    StringVertex v1 = new StringVertex("v1");
    StringVertex v2 = new StringVertex("v2");

    Graph<StringVertex, StringEdge<StringVertex>> directedGraph = new Graph<>(true);
    directedGraph.getPath(v1, v2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void undirectedVertexNotExists() {
    StringVertex v1 = new StringVertex("v1");
    StringVertex v2 = new StringVertex("v2");

    Graph<StringVertex, StringEdge<StringVertex>> undirectedGraph = new Graph<>(true);
    undirectedGraph.getPath(v1, v2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void directedVertexAlreadyExists() {
    Graph<StringVertex, StringEdge<StringVertex>> graph = new Graph<>(true);
    StringVertex v1 = new StringVertex("v1");
    graph.addVertex(v1);
    graph.addVertex(v1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void undirectedVertexAlreadyExists() {
    Graph<StringVertex, StringEdge<StringVertex>> graph = new Graph<>(false);
    StringVertex v1 = new StringVertex("v1");
    graph.addVertex(v1);
    graph.addVertex(v1);
  }

  @Test
  public void directedSimplePath() {
    StringVertex v1 = new StringVertex("v1");
    StringVertex v2 = new StringVertex("v2");
    StringEdge<StringVertex> edgeV1V2 = new StringEdge<>(v1, v2, "e12");
    Graph<StringVertex, StringEdge<StringVertex>> graph = new Graph<>(true);
    graph
      .addVertex(v1)
      .addVertex(v2)
      .addEdge(edgeV1V2);

    assertEquals("Invalid path", Collections.singletonList(edgeV1V2), graph.getPath(v1, v2));
    assertNull("Path doesn't exists", graph.getPath(v2, v1));
  }

  @Test
  public void undirectedSimplePath() {
    StringVertex v1 = new StringVertex("v1");
    StringVertex v2 = new StringVertex("v2");
    StringEdge<StringVertex> edgeV1V2 = new StringEdge<>(v1, v2, "e12");
    Graph<StringVertex, StringEdge<StringVertex>> graph = new Graph<>(false);
    graph
      .addVertex(v1)
      .addVertex(v2)
      .addEdge(edgeV1V2);

    assertEquals("Invalid path", Collections.singletonList(edgeV1V2), graph.getPath(v1, v2));
    assertEquals("Invalid path", Collections.singletonList(edgeV1V2), graph.getPath(v1, v2));
  }

  @Test
  public void directedSimplePath3() {
    StringVertex v1 = new StringVertex("v1");
    StringVertex v2 = new StringVertex("v2");
    StringVertex v3 = new StringVertex("v3");
    StringEdge<StringVertex> edgeV1V2 = new StringEdge<>(v1, v2, "e12");
    StringEdge<StringVertex> edgeV2V3 = new StringEdge<>(v2, v3, "e23");
    Graph<StringVertex, StringEdge<StringVertex>> graph = new Graph<>(true);
    graph
      .addVertex(v1)
      .addVertex(v2)
      .addEdge(edgeV1V2)
      .addVertex(v3)
      .addEdge(edgeV2V3);

    assertEquals("Invalid path", Collections.singletonList(edgeV1V2), graph.getPath(v1, v2));
    assertEquals("Invalid path", Collections.singletonList(edgeV2V3), graph.getPath(v2, v3));
    assertEquals("Invalid path", Arrays.asList(edgeV1V2, edgeV2V3), graph.getPath(v1, v3));
    assertNull("Path doesn't exists", graph.getPath(v2, v1));
    assertNull("Path doesn't exists", graph.getPath(v3, v2));
    assertNull("Path doesn't exists", graph.getPath(v3, v1));
  }

  @Test
  public void undirectedSimplePath3() {
    StringVertex v1 = new StringVertex("v1");
    StringVertex v2 = new StringVertex("v2");
    StringVertex v3 = new StringVertex("v3");
    StringEdge<StringVertex> edgeV1V2 = new StringEdge<>(v1, v2, "e12");
    StringEdge<StringVertex> edgeV2V3 = new StringEdge<>(v2, v3, "e23");
    Graph<StringVertex, StringEdge<StringVertex>> graph = new Graph<>(false);
    graph
      .addVertex(v1)
      .addVertex(v2)
      .addEdge(edgeV1V2)
      .addVertex(v3)
      .addEdge(edgeV2V3);

    assertEquals("Invalid path", Collections.singletonList(edgeV1V2), graph.getPath(v1, v2));
    assertEquals("Invalid path", Collections.singletonList(edgeV2V3), graph.getPath(v2, v3));
    assertEquals("Invalid path", Arrays.asList(edgeV1V2, edgeV2V3), graph.getPath(v1, v3));
    assertEquals("Invalid path", Collections.singletonList(edgeV1V2), graph.getPath(v2, v1));
    assertEquals("Invalid path", Collections.singletonList(edgeV2V3), graph.getPath(v3, v2));
    assertEquals("Invalid path", Arrays.asList(edgeV2V3, edgeV1V2), graph.getPath(v3, v1));
  }

  @Test
  public void getPathForSameVertex() {
    StringVertex v1 = new StringVertex("v1");
    Graph<StringVertex, StringEdge<StringVertex>> graph = new Graph<>(true);
    graph
      .addVertex(v1);

    assertEquals("Invalid path", Collections.emptyList(), graph.getPath(v1, v1));
  }

  @Test
  public void directedBigTest() {
    StringVertex[] vertices = new StringVertex[6];
    Graph<StringVertex, StringEdge<StringVertex>> graph = new Graph<>(true);
    for (int i = 0; i < 6; i++) {
      vertices[i] = new StringVertex("v" + i);
      graph.addVertex(vertices[i]);
    }
    StringEdge<StringVertex> e01 = new StringEdge<>(vertices[0], vertices[1], "e01");
    StringEdge<StringVertex> e12 = new StringEdge<>(vertices[1], vertices[2], "e12");
    StringEdge<StringVertex> e23 = new StringEdge<>(vertices[2], vertices[3], "e23");
    StringEdge<StringVertex> e34 = new StringEdge<>(vertices[3], vertices[4], "e34");
    StringEdge<StringVertex> e43 = new StringEdge<>(vertices[4], vertices[3], "e43");
    StringEdge<StringVertex> e41 = new StringEdge<>(vertices[4], vertices[1], "e41");
    StringEdge<StringVertex> e52 = new StringEdge<>(vertices[5], vertices[2], "e52");
    StringEdge<StringVertex> e53 = new StringEdge<>(vertices[5], vertices[3], "e53");

    graph
      .addEdge(e01)
      .addEdge(e12)
      .addEdge(e23)
      .addEdge(e34)
      .addEdge(e43)
      .addEdge(e41)
      .addEdge(e52)
      .addEdge(e53);

    assertEquals("Invalid path", Arrays.asList(e01, e12), graph.getPath(vertices[0], vertices[2]));
    assertEquals("Invalid path", Arrays.asList(e01, e12, e23), graph.getPath(vertices[0], vertices[3]));
    assertEquals("Invalid path", Arrays.asList(e01, e12, e23, e34), graph.getPath(vertices[0], vertices[4]));
    assertEquals("Invalid path", Arrays.asList(e12, e23, e34), graph.getPath(vertices[1], vertices[4]));
    assertEquals("Invalid path", Arrays.asList(e53, e34, e41), graph.getPath(vertices[5], vertices[1]));

    assertNull(graph.getPath(vertices[5], vertices[0]));
    for (int i = 0; i < 5; i++) {
      assertNull(graph.getPath(vertices[i], vertices[5]));
    }
  }

  @Test
  public void undirectedBigTest() {
    StringVertex[] vertices = new StringVertex[6];
    Graph<StringVertex, StringEdge<StringVertex>> graph = new Graph<>(false);
    for (int i = 0; i < 6; i++) {
      vertices[i] = new StringVertex("v" + i);
      graph.addVertex(vertices[i]);
    }
    StringEdge<StringVertex> e01 = new StringEdge<>(vertices[0], vertices[1], "e01");
    StringEdge<StringVertex> e12 = new StringEdge<>(vertices[1], vertices[2], "e12");
    StringEdge<StringVertex> e23 = new StringEdge<>(vertices[2], vertices[3], "e23");
    StringEdge<StringVertex> e34 = new StringEdge<>(vertices[3], vertices[4], "e34");
    StringEdge<StringVertex> e14 = new StringEdge<>(vertices[1], vertices[4], "e14");
    StringEdge<StringVertex> e25 = new StringEdge<>(vertices[2], vertices[5], "e25");
    StringEdge<StringVertex> e35 = new StringEdge<>(vertices[3], vertices[5], "e35");

    graph
      .addEdge(e01)
      .addEdge(e12)
      .addEdge(e23)
      .addEdge(e34)
      .addEdge(e14)
      .addEdge(e25)
      .addEdge(e35);

    assertEquals("Invalid path", Arrays.asList(e01, e12), graph.getPath(vertices[0], vertices[2]));
    assertTrue("Invalid path", Arrays.asList(e01, e12, e23).equals(graph.getPath(vertices[0], vertices[3]))
      || Arrays.asList(e01, e14, e34).equals(graph.getPath(vertices[0], vertices[3])));
    assertEquals("Invalid path", Arrays.asList(e01, e14), graph.getPath(vertices[0], vertices[4]));
    assertEquals("Invalid path", Collections.singletonList(e14), graph.getPath(vertices[1], vertices[4]));
    assertEquals("Invalid path", Arrays.asList(e25, e12), graph.getPath(vertices[5], vertices[1]));
    assertEquals("Invalid path", Arrays.asList(e25, e12, e01), graph.getPath(vertices[5], vertices[0]));
  }

  @Test
  public void simpleEdge() {
    StringVertex v1 = new StringVertex("v1");
    StringVertex v2 = new StringVertex("v2");

    Edge<StringVertex> edge = new Edge<>(v1, v2);
    Graph<StringVertex, Edge<StringVertex>> graph = new Graph<StringVertex, Edge<StringVertex>>(true)
      .addVertex(v1)
      .addVertex(v2)
      .addEdge(edge);

    assertEquals("Invalid path", Collections.singletonList(edge), graph.getPath(v1, v2));
  }
}
