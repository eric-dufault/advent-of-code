package org.personal.helpers.graph;

import java.util.ArrayList;
import java.util.List;

public class DiVertexImpl<V> implements Vertex<V> {
	private final V data;
	private final List<Edge<V>> incidentEdges = new ArrayList<>();
	private int inDegree;
	private boolean visited;

	public DiVertexImpl(V data) {
		this.data = data;
	}

	@Override
	public V getData() {
		return this.data;
	}

	public List<Edge<V>> getIncidentEdges() {
		return this.incidentEdges;
	}

	public void addAdjacentEdge(Edge<V> e) {
		this.incidentEdges.add(e);
	}

	public void removeAdjacentEdge(Edge<V> e) {
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
