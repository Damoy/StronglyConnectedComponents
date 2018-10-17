package utils;

import java.util.Random;

import model.Graph;

public final class GraphBuilder {
	
	private static GraphBuilder instance;
	private static Random seed;
	
	private GraphBuilder(){
		seed = new Random();
	}
	
	public static GraphBuilder get() {
		if(instance == null)
			instance = new GraphBuilder();
		return instance;
	}
	
	public Graph generate(int verticesCount, int edgesCount) {
		pln(">> Generating graph, number of vertices: " + verticesCount + ", number of edges: " + edgesCount + ".");
		// pln("");
		
		Graph generation = Graph.of(verticesCount);
		
		int vc1 = verticesCount - 1;
		
		for(int i = 0; i < edgesCount; ++i) {
			int v1 = irand(0, vc1);
			int v2 = irand(0, vc1);
			
			if(v1 != v2 && !generation.edgeExists(v1, v2)) {
				generation.addEdge(v1, v2);
				//pln("\tEdge " + (i + 1) + " generated: " + v1 + " to " + v2 + ".");
			} else if(edgesCount < verticesCount){
				--i;
			}
		}
		
// 		pln("\n>> Graph generation success.");
		pln(">> Graph generation success.");
		
		return generation;
	}
	
	private int irand(int min, int max){
		return seed.nextInt((max - min) + 1) + min;
	}
	
	private static <T> void pln(T o) {
		System.out.println(o.toString());
	}
}
