package org.personal.helpers.graph;

public interface Vertex<V> {
	V getData();

	boolean isVisited();
	void setVisited(boolean visited);
}
