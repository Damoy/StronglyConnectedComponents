package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A graph has a number of vertices and also
 * contains all the vertices adjacencies.
 * 
 * 
 * @author Damien FORNALI
 */
public final class Graph {

	private List<List<Integer>> adj;
	private int verticesCount;
	
	private Graph(int verticesCount) {
		this.verticesCount = verticesCount;
		
		adj = new ArrayList<>(verticesCount);
		
		for(int i = 0; i < verticesCount; ++i)
			adj.add(new ArrayList<>());
	}
	
	public static Graph of(int verticesCount) {
		return new Graph(verticesCount);
	}
	
	/**
	 * Add an edge from vertex to adjVertex.
	 * @param vertex the edge source
	 * @param adjVertex the edge destination
	 */
	public void addEdge(int vertex, int adjVertex) {
		adj.get(vertex).add(adjVertex);
	}

	public int getVerticesCount() {
		return verticesCount;
	}
	
	/**
	 * Get an iterator on vertex adjacency.
	 */
	public Iterator<Integer> getAdjacencyIt(int vertex){
		return adj.get(vertex).iterator();
	}
	
	public List<Integer> getAdjacency(int vertex){
		return adj.get(vertex);
	}
	
	public boolean edgeExists(int vertex, int adjVertex) {
		return adj.get(vertex).contains(adjVertex);
	}
	
	/**
	 * Transpose the graph.
	 * @return a new transposed graph
	 */
	public Graph transpose() {
		Graph transposed = Graph.of(verticesCount);
		
		for(int v = 0; v < verticesCount; ++v) {
			Iterator<Integer> vAdjs = getAdjacencyIt(v);
			
			while(vAdjs.hasNext()) {
				transposed.addEdge(vAdjs.next(), v);
			}
		}
		
		return transposed;
	}
	
	public Graph output() {
		StringBuilder sb = new StringBuilder();
		sb.append("-- Graph --\n");
		
		List<Integer> ref;
		
		for(int i = 0; i < verticesCount; ++i) {
			ref = adj.get(i);
			
			sb.append(i + ": ");
			
			for(int j = 0; j < ref.size(); ++j) {
				sb.append(ref.get(j));
				
				if(j != ref.size() - 1)
					sb.append(",");
				else
					sb.append("\n");
			}
		}
		
		sb.append("-----------\n");
		System.out.println(sb.toString());
		return this;
	}
	
}
