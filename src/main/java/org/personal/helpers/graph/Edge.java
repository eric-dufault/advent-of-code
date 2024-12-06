package org.personal.helpers.graph;

public interface Edge<T> {
	Vertex<T> fromVertex();
	Vertex<T> toVertex();

	boolean isUniDirectional();
}
