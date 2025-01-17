/**
 * 
 */
package model.data_structures;

import java.util.Iterator;

public class Digraph {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;                // number of vertices in this digraph
	private int E;                      // number of edges in this digraph
	private Bag<Edge>[] adj;   			// adj[v] = adjacency list for vertex v
	private int[] indegree;             // indegree[v] = indegree of vertex v

	/**
	 * Initializes an empty edge-weighted digraph with {@code V} vertices and 0 edges.
	 *
	 * @param  V the number of vertices
	 * @throws IllegalArgumentException if {@code V < 0}
	 */
	public Digraph(int V) {
		if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
		this.V = V;
		this.E = 0;
		this.indegree = new int[V];
		adj = (Bag<Edge>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<Edge>();
	}

	/**
	 * Returns the number of vertices in this edge-weighted digraph.
	 *
	 * @return the number of vertices in this edge-weighted digraph
	 */
	public int V() {
		return V;
	}

	/**
	 * Returns the number of edges in this edge-weighted digraph.
	 *
	 * @return the number of edges in this edge-weighted digraph
	 */
	public int E() {
		return E;
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
	}

	/**
	 * Adds the directed edge {@code e} to this edge-weighted digraph.
	 *
	 * @param  e the edge
	 * @throws IllegalArgumentException unless endpoints of edge are between {@code 0}
	 *         and {@code V-1}
	 */
	public void addEdge(Edge e) {
		int v = e.from();
		int w = e.to();
		validateVertex(v);
		validateVertex(w);

		boolean existe = false;
		Iterator iter = adj[v].iterator();
		while (iter.hasNext() && !existe)
		{
			Edge arquito = (Edge) iter.next();
			if(arquito.to() == w)
			{
				existe = true;
				arquito.avgWeight(e.weight()); // agrega el peso de la nueva version del nodo existente.
			}
				
			
		}
		if(!existe) 
		{
			adj[v].add(e);
			indegree[w]++;
		}

		E++;
	}


	/**
	 * Returns the directed edges incident from vertex {@code v}.
	 *
	 * @param  v the vertex
	 * @return the directed edges incident from vertex {@code v} as an Iterable
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public Iterable<Edge> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	/**
	 * Returns the number of directed edges incident from vertex {@code v}.
	 * This is known as the <em>outdegree</em> of vertex {@code v}.
	 *
	 * @param  v the vertex
	 * @return the outdegree of vertex {@code v}
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public int outdegree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	/**
	 * Returns the number of directed edges incident to vertex {@code v}.
	 * This is known as the <em>indegree</em> of vertex {@code v}.
	 *
	 * @param  v the vertex
	 * @return the indegree of vertex {@code v}
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public int indegree(int v) {
		validateVertex(v);
		return indegree[v];
	}

	/**
	 * Returns all directed edges in this edge-weighted digraph.
	 * To iterate over the edges in this edge-weighted digraph, use foreach notation:
	 * {@code for (DirectedEdge e : G.edges())}.
	 *
	 * @return all edges in this edge-weighted digraph, as an iterable
	 */
	public Iterable<Edge> edges() {
		Bag<Edge> list = new Bag<Edge>();
		for (int v = 0; v < V; v++) {
			for (Edge e : adj(v)) {
				list.add(e);
			}
		}
		return list;
	} 

	/**
	 * Returns a string representation of this edge-weighted digraph.
	 *
	 * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
	 *         followed by the <em>V</em> adjacency lists of edges
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (Edge e : adj[v]) {
				s.append(e + "  ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}
}