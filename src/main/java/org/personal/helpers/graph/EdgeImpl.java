package org.personal.helpers.graph;

public class EdgeImpl<T> implements Edge<T> {
	private final Vertex<T> fromVertex;
	private final Vertex<T> toVertex;
	private final boolean uniDirectional;

	public EdgeImpl(Vertex<T> fromVertex, Vertex<T> toVertex, boolean uniDirectional) {
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
		this.uniDirectional = uniDirectional;
	}

	@Override
	public Vertex<T> fromVertex() {
		return this.fromVertex;
	}

	@Override
	public Vertex<T> toVertex() {
		return this.toVertex;
	}

	@Override
	public boolean isUniDirectional() {
		return this.uniDirectional;
	}
}
