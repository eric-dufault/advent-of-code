package org.personal.helpers.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {

	public static <T> List<T> dfsSort(Graph<T> g) {
		List<T> sorted = new ArrayList<>();
		Stack<Vertex<T>> stack = new Stack<>();

		for (Vertex<T> v : g.vertices()) {
			if (!v.isVisited())
				dfsSort(v, g, stack);
		}

		while (!stack.empty()) {
			sorted.add(stack.pop().getData());
		}

		return sorted;
	}

	private static <T> void dfsSort(Vertex<T> v, Graph<T> g, Stack<Vertex<T>> stack) {
		v.setVisited(true);

		Collection<Edge<T>> incidentEdges = g.incidentEdges(v);
		for (Edge<T> incidentEdge : incidentEdges) {
			Vertex<T> m = g.traverse(v, incidentEdge);
			if (!m.isVisited()) {
				dfsSort(m, g, stack);
			}
		}

		stack.push(v);
	}

}
