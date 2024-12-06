package org.personal.helpers.graph;

import java.util.*;

public class DiGraphImpl<T> implements Graph<T> {
	private final List<Vertex<T>> vertices = new ArrayList<>();

	@Override
	public Collection<Vertex<T>> vertices() {
		return this.vertices;
	}

	@Override
	public Collection<Edge<T>> edges() {
		Set<Edge<T>> edges = new HashSet<>();
		for (Vertex<T> v : this.vertices) {
			edges.addAll(((DiVertexImpl) v).getIncidentEdges());
		}
		return edges;
	}

	@Override
	public Vertex<T> getVertex(T data) {
		Vertex<T> result = null;
		for (Vertex<T> v : this.vertices) {
			if (data.equals(v.getData())) {
				result = v;
				break;
			}
		}
		return result;
	}

	@Override
	public void resetVisited() {
		for (Vertex<T> v : this.vertices) {
			v.setVisited(false);
		}
	}

	@Override
	public Collection<Edge<T>> incidentEdges(Vertex<T> vertex) {
		return ((DiVertexImpl) vertex).getIncidentEdges();
	}

	@Override
	public Vertex<T> traverse(Vertex<T> fromVertex, Edge<T> edge) {
		return edge.toVertex();
	}

	@Override
	public boolean areAdjacent(Vertex<T> v, Vertex<T> w) {
		boolean adjacent = false;
		DiVertexImpl<T> adv = (DiVertexImpl<T>) v;
		for (Edge<T> e : incidentEdges(adv)) {
			if (w.equals(traverse(adv, e))) {
				adjacent = true;
				break;
			}
		}
		return adjacent;
	}

	@Override
	public Vertex<T> insertVertex(T data) {
		Vertex<T> v = new DiVertexImpl<>(data);
		this.vertices.add(v);
		return v;
	}

	@Override
	public Edge<T> insertEdge(Vertex<T> fromVertex, Vertex<T> toVertex) {
		Edge<T> e = new EdgeImpl<>(fromVertex, toVertex, true);
		((DiVertexImpl<T>) fromVertex).addAdjacentEdge(e);
		((DiVertexImpl<T>) toVertex).incrementInDegree();
		return e;
	}

	@Override
	public T removeVertex(Vertex<T> vertex) {
		this.vertices.remove(vertex);

		for (Vertex<T> v : this.vertices) {
			List<Edge<T>> incidentEdgesList = new ArrayList<>(incidentEdges(v));
			for (Edge<T> incidentEdge : incidentEdgesList) {
				DiVertexImpl<T> adv = (DiVertexImpl<T>) traverse(v, incidentEdge);
				if (vertex.equals(adv)) {
					adv.removeAdjacentEdge(incidentEdge);
				}
			}
		}

		return vertex.getData();
	}

	@Override
	public void removeEdge(Edge<T> e) {
		((DiVertexImpl<T>)e.fromVertex()).removeAdjacentEdge(e);
		((DiVertexImpl<T>)e.toVertex()).decrementInDegree();
	}
}
