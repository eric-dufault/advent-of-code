package org.personal.helpers.graph;

public interface Vertex<T> {
	T getData();

	boolean isVisited();
	void setVisited(boolean visited);
}
