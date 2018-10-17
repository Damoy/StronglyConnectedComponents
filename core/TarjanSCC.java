package core;

import java.util.Iterator;
import java.util.Stack;

import model.Graph;
import model.SCC;
import utils.Constants;
import utils.IntRef;

/**
 * A Tarjan SCC algorithm implementation.<br>
 *
 * @author Damien FORNALI
 */
public final class TarjanSCC implements IGraphProcessor {
	
	private static IGraphProcessor instance;
	private SCC scc;
	
	private TarjanSCC() {
		scc = SCC.empty();
	}
	
	public static IGraphProcessor get() {
		if(instance == null)
			instance = new TarjanSCC();
		return instance;
	}

	@Override
	public void process(Graph graph) {
		int vc = graph.getVerticesCount();
		
		int[] disc = new int[vc];
		int[] lows = new int[vc];
		boolean[] processed = new boolean[vc];
		
		IntRef index = IntRef.of(0);
		Stack<Integer> stack = new Stack<>();
		
		init(vc, processed, disc, lows);
		launch(graph, stack, index, disc, lows, processed);
	}
	
	// initialize structures
	private void init(int vc, boolean[] processed, int[] disc, int[] lows) {
		scc.clear();
		
		for(int v = 0; v < vc; ++v) {
			processed[v] = false;
			lows[v] = Constants.getInitValue();
			disc[v] = Constants.getInitValue();
		}
	}
	
	// loop algorithm over all vertices
	private void launch(Graph graph, Stack<Integer> stack, IntRef indexRef, int[] disc, int[] lows, boolean[] processed) {
		for(int v = 0; v< graph.getVerticesCount(); ++v)
			if(disc[v] == Constants.getInitValue())
				process(graph, v, stack, indexRef, disc, lows, processed, scc);
	}
	
	// core algorithm
	private void process(Graph graph, int vertex, Stack<Integer> stack, IntRef indexRef, int[] disc, int[] lows, boolean[] processed, SCC scc) {
		int ir = indexRef.inc().get();
		
		disc[vertex] = ir;
		lows[vertex] = ir;
		processed[vertex] = true;
		
		stack.push(vertex);
		
		// get an iterator on the adjacency of vertex
		Iterator<Integer> vAdjs = graph.getAdjacencyIt(vertex);
		
		while(vAdjs.hasNext()) {
			int adj = vAdjs.next();
			
			if(disc[adj] == Constants.getInitValue()) {
				process(graph, adj, stack, indexRef, disc, lows, processed, scc);
				lows[vertex] = Math.min(lows[vertex], lows[adj]);
			} else if(processed[adj]) {
				lows[vertex] = Math.min(lows[vertex], disc[adj]);
			}
		}
		
		int vertexRef = 0;
		
		if(lows[vertex] == disc[vertex]) {
			// create a new scc
			scc.grow();
			
			while(stack.peek() != vertex) {
				vertexRef = stack.pop();
				processed[vertexRef] = false;
				scc.add(vertexRef);
			}
			
			
			vertexRef = stack.pop();
			processed[vertexRef] = false;
			// add vertexRef to the latest scc
			scc.add(vertexRef);
		}
	}

	@Override
	public void output() {
		scc.output("Tarjan");
	}
	
}