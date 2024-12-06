package org.personal.helpers.graph;

import java.util.*;

public class DiGraphImpl<V> implements Graph<V> {
	private final List<Vertex<V>> vertices = new ArrayList<>();

	@Override
	public Collection<Vertex<V>> vertices() {
		return this.vertices;
	}

	@Override
	public Collection<Edge<V>> edges() {
		Set<Edge<V>> edges = new HashSet<>();
		for (Vertex<V> v : this.vertices) {
			edges.addAll(((DiVertexImpl) v).getIncidentEdges());
		}
		return edges;
	}

	@Override
	public Vertex<V> getVertex(V data) {
		Vertex<V> result = null;
		for (Vertex<V> v : this.vertices) {
			if (data.equals(v.getData())) {
				result = v;
				break;
			}
		}
		return result;
	}

	@Override
	public void resetVisited() {
		for (Vertex<V> v : this.vertices) {
			v.setVisited(false);
		}
	}

	@Override
	public Collection<Edge<V>> incidentEdges(Vertex<V> vertex) {
		return ((DiVertexImpl) vertex).getIncidentEdges();
	}

	@Override
	public Vertex<V> traverse(Vertex<V> fromVertex, Edge<V> edge) {
		return edge.toVertex();
	}

	@Override
	public boolean areAdjacent(Vertex<V> v, Vertex<V> w) {
		boolean adjacent = false;
		DiVertexImpl<V> adv = (DiVertexImpl<V>) v;
		for (Edge<V> e : incidentEdges(adv)) {
			if (w.equals(traverse(adv, e))) {
				adjacent = true;
				break;
			}
		}
		return adjacent;
	}

	@Override
	public Vertex<V> insertVertex(V data) {
		Vertex<V> v = new DiVertexImpl<>(data);
		this.vertices.add(v);
		return v;
	}

	@Override
	public Edge<V> insertEdge(Vertex<V> fromVertex, Vertex<V> toVertex) {
		Edge<V> e = new EdgeImpl<>(fromVertex, toVertex, true);
		((DiVertexImpl<V>) fromVertex).addAdjacentEdge(e);
		((DiVertexImpl<V>) toVertex).incrementInDegree();
		return e;
	}

	@Override
	public V removeVertex(Vertex<V> vertex) {
		this.vertices.remove(vertex);

		for (Vertex<V> v : this.vertices) {
			List<Edge<V>> incidentEdgesList = new ArrayList<>(incidentEdges(v));
			for (Edge<V> incidentEdge : incidentEdgesList) {
				DiVertexImpl<V> adv = (DiVertexImpl<V>) traverse(v, incidentEdge);
				if (vertex.equals(adv)) {
					adv.removeAdjacentEdge(incidentEdge);
				}
			}
		}

		return vertex.getData();
	}

	@Override
	public void removeEdge(Edge<V> e) {
		((DiVertexImpl<V>)e.fromVertex()).removeAdjacentEdge(e);
		((DiVertexImpl<V>)e.toVertex()).decrementInDegree();
	}
}
