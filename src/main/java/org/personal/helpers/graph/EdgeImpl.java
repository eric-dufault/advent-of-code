package org.personal.helpers.graph;

public class EdgeImpl<V> implements Edge<V> {
	private final Vertex<V> fromVertex;
	private final Vertex<V> toVertex;
	private final boolean uniDirectional;

	public EdgeImpl(Vertex<V> fromVertex, Vertex<V> toVertex, boolean uniDirectional) {
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
		this.uniDirectional = uniDirectional;
	}

	@Override
	public Vertex<V> fromVertex() {
		return this.fromVertex;
	}

	@Override
	public Vertex<V> toVertex() {
		return this.toVertex;
	}

	@Override
	public boolean isUniDirectional() {
		return this.uniDirectional;
	}
}
