package org.personal.helpers.graph;

import java.util.ArrayList;
import java.util.List;

public class DiVertexImpl<T> implements Vertex<T> {
	private final T data;
	private final List<Edge<T>> incidentEdges = new ArrayList<>();
	private int inDegree;
	private boolean visited;

	public DiVertexImpl(T data) {
		this.data = data;
	}

	@Override
	public T getData() {
		return this.data;
	}

	public List<Edge<T>> getIncidentEdges() {
		return this.incidentEdges;
	}

	public void addAdjacentEdge(Edge<T> e) {
		this.incidentEdges.add(e);
	}

	public void removeAdjacentEdge(Edge<T> e) {
		this.incidentEdges.remove(e);
	}

	public int getInDegree() {
		return this.inDegree;
	}

	public void incrementInDegree() {
		this.inDegree++;
	}

	public void decrementInDegree() {
		this.inDegree--;
	}

	public int getOutDegree() {
		return this.incidentEdges.size();
	}

	public boolean isVisited() {
		return this.visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public String toString() {
		return this.data.toString();
	}
}
