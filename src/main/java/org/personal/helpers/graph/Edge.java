package org.personal.helpers.graph;

public interface Edge<V> {
	Vertex<V> fromVertex();
	Vertex<V> toVertex();

	boolean isUniDirectional();
}
