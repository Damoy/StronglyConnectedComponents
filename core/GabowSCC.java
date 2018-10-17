package core;

import java.util.Iterator;
import java.util.Stack;

import model.Graph;
import model.SCC;
import utils.Constants;
import utils.IntRef;

/**
 * A "Path-based strong component" (Gabow) SCC implementation.<br>
 *
 * @author Damien FORNALI
 */
public final class GabowSCC implements IGraphProcessor {
	
	private static IGraphProcessor instance;
	private SCC scc;
	
	private GabowSCC() {
		scc = SCC.empty();
	}
	
	public static IGraphProcessor get() {
		if(instance == null)
			instance = new GabowSCC();
		return instance;
	}

	@Override
	public void process(Graph graph) {
		int vc = graph.getVerticesCount();
		int[] disc = new int[vc];
		boolean[] processed = new boolean[vc];
		
		IntRef index = IntRef.of(0);
		
		Stack<Integer> S = new Stack<>();
		Stack<Integer> B = new Stack<>();
		
		init(vc, disc, processed);
		launch(graph, vc, S, B, index, disc, processed);
	}
	
	// initialize structures
	private void init(int vc, int[] disc, boolean[] processed) {
		scc.clear();
		
		for(int v = 0; v < vc; ++v) {
			disc[v] = Constants.getInitValue();
			processed[v] = false;
		}
	}
	
	// loop algorithm over all vertices
	private void launch(Graph graph, int vc, Stack<Integer> S, Stack<Integer> B, IntRef indexRef, int[] disc, boolean[] processed) {
		scc.clear();
		
		for(int v = 0; v < vc; ++v)
			if(disc[v] == Constants.getInitValue())
				process(graph, v, vc, S, B, indexRef, disc, processed);
		
	}
	
	// core algorithm
	private void process(Graph graph, int vertex, int vc, Stack<Integer> S, Stack<Integer> B, IntRef indexRef, int[] disc, boolean[] processed) {
		disc[vertex] = indexRef.inc().get();
		S.push(vertex);
		B.push(vertex);
		
		// get an iterator on the adjacency of vertex
		Iterator<Integer> vAdjs = graph.getAdjacencyIt(vertex);
		
		while(vAdjs.hasNext()) {
			int adj = vAdjs.next();
			
			if(disc[adj] == Constants.getInitValue()) {
				process(graph, adj, vc, S, B, indexRef, disc, processed);
			} else if(!processed[adj]){
				while(disc[adj] < disc[B.peek()])
					B.pop();
			}
		}
		
		if(vertex == B.peek()) {
			// create a new scc
			scc.grow();
			
			int w;
			while(S.peek() != vertex) {
				w = S.pop();
				scc.add(w);
				processed[w] = true;
			}
			
			w = S.pop();
			// add w to the latest component
			scc.add(w);
			processed[w] = true;
			B.pop();
		}
	}

	@Override
	public void output() {
		scc.output("Gabow");
	}
	
}