package org.personal.helpers.graph;

import java.util.Collection;

public interface Graph<V> {
	Collection<Vertex<V>> vertices();
	Collection<Edge<V>> edges();

	Vertex<V> getVertex(V data);
	void resetVisited();

	Collection<Edge<V>> incidentEdges(Vertex<V> vertex);
	Vertex<V> traverse(Vertex<V> fromVertex, Edge<V> edge);

	boolean areAdjacent(Vertex<V> v, Vertex<V> w);

	Vertex<V> insertVertex(V data);
	Edge<V> insertEdge(Vertex<V> fromVertex, Vertex<V> toVertex);

	V removeVertex(Vertex<V> vertex);
	void removeEdge(Edge<V> e);
}
