package core;

import java.util.Iterator;
import java.util.Stack;

import model.Graph;
import model.SCC;

public final class KosarajuSCC implements IGraphProcessor {
	
	private static IGraphProcessor instance;
	private SCC scc;
	
	private KosarajuSCC() {
		scc = SCC.empty();
	}
	
	public static IGraphProcessor get() {
		if(instance == null)
			instance = new KosarajuSCC();
		return instance;
	}

	@Override
	public void process(Graph graph) {
		scc.clear();
		// create a new scc
		scc.grow();
		
		int vc = graph.getVerticesCount();
		boolean[] processed = new boolean[vc];
		Stack<Integer> stack = new Stack<>();
		
		init(vc, processed);
		launch(graph, vc, stack, processed);
		
		Graph transposed = graph.transpose();
		init(vc, processed);
		tlaunch(transposed, vc, stack, processed);
		
		// have to remove the additional scc
		scc.reduce();
	}
	
	// initialize structures
	private void init(int vc, boolean[] processed) {
		for(int v = 0; v < vc; ++v)
			processed[v] = false;
	}
	
	// loop first part algorithm over all vertices
	private void launch(Graph graph, int vc, Stack<Integer> stack, boolean[] processed) {
		for(int v = 0; v < vc; ++v)
			if(!processed[v])
				process(graph, v, stack, processed);
	}
	
	// first part algorithm
	private void process(Graph graph, int vertex, Stack<Integer> stack, boolean[] processed) {
		processed[vertex] = true;
		
		// get an iterator on the adjacency of vertex
		Iterator<Integer> vAdjs = graph.getAdjacencyIt(vertex);
		
		while(vAdjs.hasNext()) {
			int adj = vAdjs.next();
			
			if(!processed[adj])
				process(graph, adj, stack, processed);
		}
		
		stack.push(vertex);
	}
	
	// loop second part algorithm over the stack content
	private void tlaunch(Graph graph, int vc, Stack<Integer> stack, boolean[] processed) {
		while(!stack.isEmpty()) {
			int vertex = stack.pop();
			
			if(!processed[vertex]) {
				tprocess(graph, vertex, processed);
				// create a new scc
				scc.grow();
			}
		}
	}
	
	// second part algorithm
	private void tprocess(Graph graph, int vertex, boolean[] processed) {
		processed[vertex] = true;
		Iterator<Integer> vAdjs = graph.getAdjacencyIt(vertex);
		
		// add vertex to the latest scc
		scc.add(vertex);
		
		while(vAdjs.hasNext()) {
			int adj = vAdjs.next();
			
			if(!processed[adj])
				tprocess(graph, adj, processed);
		}
	}
	
	public void output() {
		scc.output("Kosaraju");
	}
	
}
