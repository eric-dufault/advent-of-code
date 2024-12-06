package org.personal.helpers.graph;

import java.util.Collection;

public interface Graph<T> {
	Collection<Vertex<T>> vertices();
	Collection<Edge<T>> edges();

	Vertex<T> getVertex(T data);
	void resetVisited();

	Collection<Edge<T>> incidentEdges(Vertex<T> vertex);
	Vertex<T> traverse(Vertex<T> fromVertex, Edge<T> edge);

	boolean areAdjacent(Vertex<T> v, Vertex<T> w);

	Vertex<T> insertVertex(T data);
	Edge<T> insertEdge(Vertex<T> fromVertex, Vertex<T> toVertex);

	T removeVertex(Vertex<T> vertex);
	void removeEdge(Edge<T> e);
}
