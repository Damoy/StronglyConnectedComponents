package app;

import core.GabowSCC;
import core.KosarajuSCC;
import core.TarjanSCC;
import model.Graph;
import utils.GraphBuilder;
import utils.Timer;

@SuppressWarnings("unused")
public final class Main {
	
	public static void main(String[] args) {
		tutorial();
		courseGraph();
//		benchDensity05();
//		benchDensity125();
//		benchDensity20();
	}
	
	// tutorial
	private static void tutorial() {
		// choose your graph sizes
		int verticesCount = 2000;
		int edgesCount = (int) (verticesCount * 1.5);
		
		// generate your graph, edges disposition is random
		Graph graph = GraphBuilder.get().generate(verticesCount, edgesCount);
		
		// start the timer to know processing time
		Timer.start();
		
		// choose your algorithm and launch execution
		TarjanSCC.get().process(graph);
		// KosarajuSCC.get().process(graph);
		// GabowSCC.get().process(graph);
		
		// stop the timer
		Timer.end();
		
		// display the results
		TarjanSCC.get().output();
		// KosarajuSCC.get().output();
		// GabowSCC.get().output();
		
		// display the processing time
		Timer.output(Timer.MILLISECONDS);
	}
	
	// graph saw during course
	private static void courseGraph() {
		Graph graph = Graph.of(8); // 8 vertices
		
		graph.addEdge(0, 1); // a -> b
		graph.addEdge(1, 4); // b -> e
		graph.addEdge(4, 0); // e -> a
		graph.addEdge(1, 2); // b -> c
		graph.addEdge(2, 3); // c -> d
		graph.addEdge(2, 6); // c -> g
		graph.addEdge(3, 2); // d -> c
		graph.addEdge(3, 7); // d -> h
		graph.addEdge(7, 3); // h -> d
		graph.addEdge(7, 6); // h -> g
		graph.addEdge(5, 6); // f -> g
		graph.addEdge(6, 5); // g -> f
		
		Timer.start();
		TarjanSCC.get().process(graph);
		Timer.end();
		TarjanSCC.get().output();
		Timer.output();
		
		Timer.start();
		KosarajuSCC.get().process(graph);
		Timer.end();
		KosarajuSCC.get().output();
		Timer.output();
		
		Timer.start();
		GabowSCC.get().process(graph);
		Timer.end();
		GabowSCC.get().output();
		Timer.output();
	}
	
	// report performances part, density of 0.5
	private static void benchDensity05() {
		GraphBuilder gb = GraphBuilder.get();
		Graph graph1 = gb.generate(1000, 500);
		Graph graph2 = gb.generate(2000, 1000);
		Graph graph3 = gb.generate(4000, 2000);
		Graph graph4 = gb.generate(8000, 4000);
		Graph graph5 = gb.generate(16000, 8000);
		Graph graph6 = gb.generate(32000, 16000);
		Graph graph7 = gb.generate(64000, 32000);
		Graph graph8 = gb.generate(128000, 64000);
		Graph graph9 = gb.generate(256000, 128000);
		Graph graph10 = gb.generate(512000, 256000);
		Graph graph11 = gb.generate(1024000, 512000);
		Graph graph12 = gb.generate(2048000, 1024000);
		Graph graph13 = gb.generate(4096000, 2048000);
		
		// tarjan
		System.out.println(">> Tarjan");
		tarjanBench(graph1);
		tarjanBench(graph2);
		tarjanBench(graph3);
		tarjanBench(graph4);
		tarjanBench(graph5);
		tarjanBench(graph6);
		tarjanBench(graph7);
		tarjanBench(graph8);
		tarjanBench(graph9);
		tarjanBench(graph10);
		tarjanBench(graph11);
		tarjanBench(graph12);
		tarjanBench(graph13);
		
		// kosaraju
		System.out.println(">> Kosaraju");
		kosarajuBench(graph1);
		kosarajuBench(graph2);
		kosarajuBench(graph3);
		kosarajuBench(graph4);
		kosarajuBench(graph5);
		kosarajuBench(graph6);
		kosarajuBench(graph7);
		kosarajuBench(graph8);
		kosarajuBench(graph9);
		kosarajuBench(graph10);
		kosarajuBench(graph11);
		kosarajuBench(graph12);
		kosarajuBench(graph13);
		
		// gabow
		System.out.println(">> Gabow");
		gabowBench(graph1);
		gabowBench(graph2);
		gabowBench(graph3);
		gabowBench(graph4);
		gabowBench(graph5);
		gabowBench(graph6);
		gabowBench(graph7);
		gabowBench(graph8);
		gabowBench(graph9);
		gabowBench(graph10);
		gabowBench(graph11);
		gabowBench(graph12);
		gabowBench(graph13);
	}
		

	// report performances part, density of 1.25
	private static void benchDensity125() {
		GraphBuilder gb = GraphBuilder.get();
		Graph graph1 = gb.generate(1000, 1250);
		Graph graph2 = gb.generate(1500, 1563);
		Graph graph3 = gb.generate(1563, 1954);
		Graph graph4 = gb.generate(1954, 2443);
		Graph graph5 = gb.generate(2443, 3054);
		Graph graph6 = gb.generate(3054, 3818);
		Graph graph7 = gb.generate(3818, 4773);
		Graph graph8 = gb.generate(4773, 5967);
		Graph graph9 = gb.generate(5967, 7459);
		Graph graph10 = gb.generate(7459, 9324);
		Graph graph11 = gb.generate(9324, 11655);
		Graph graph12 = gb.generate(11655, 14569);
		Graph graph13 = gb.generate(14569, 18212);
		Graph graph14 = gb.generate(18212, 22765);
		Graph graph15 = gb.generate(22765, 28456);
		Graph graph16 = gb.generate(28456, 35570);
		Graph graph17 = gb.generate(35570, 44463);
		Graph graph18 = gb.generate(44463, 55579);
		Graph graph19 = gb.generate(55579, 69474);
		Graph graph20 = gb.generate(69474, 86843);
		
		// tarjan
		System.out.println(">> Tarjan");
		tarjanBench(graph1);
		tarjanBench(graph2);
		tarjanBench(graph3);
		tarjanBench(graph4);
		tarjanBench(graph5);
		tarjanBench(graph6);
		tarjanBench(graph7);
		tarjanBench(graph8);
		tarjanBench(graph9);
		tarjanBench(graph10);
		tarjanBench(graph11);
		tarjanBench(graph12);
		tarjanBench(graph13);
		tarjanBench(graph14);
		tarjanBench(graph15);
		tarjanBench(graph16);
		tarjanBench(graph17);
		tarjanBench(graph18);
		tarjanBench(graph19);
		tarjanBench(graph20);
		
		// kosaraju
		System.out.println(">> Kosaraju");
		kosarajuBench(graph1);
		kosarajuBench(graph2);
		kosarajuBench(graph3);
		kosarajuBench(graph4);
		kosarajuBench(graph5);
		kosarajuBench(graph6);
		kosarajuBench(graph7);
		kosarajuBench(graph8);
		kosarajuBench(graph9);
		kosarajuBench(graph10);
		kosarajuBench(graph11);
		kosarajuBench(graph12);
		kosarajuBench(graph13);
		kosarajuBench(graph14);
		kosarajuBench(graph15);
		kosarajuBench(graph16);
		kosarajuBench(graph17);
		kosarajuBench(graph18);
		kosarajuBench(graph19);
		kosarajuBench(graph20);
		
		// gabow
		System.out.println(">> Gabow");
		gabowBench(graph1);
		gabowBench(graph2);
		gabowBench(graph3);
		gabowBench(graph4);
		gabowBench(graph5);
		gabowBench(graph6);
		gabowBench(graph7);
		gabowBench(graph8);
		gabowBench(graph9);
		gabowBench(graph10);
		gabowBench(graph11);
		gabowBench(graph12);
		gabowBench(graph13);
		gabowBench(graph14);
		gabowBench(graph15);
		gabowBench(graph16);
		gabowBench(graph17);
		gabowBench(graph18);
		gabowBench(graph19);
		gabowBench(graph20);
	}
	
	// report performances part, density of 2.0
	private static void benchDensity20() {
		GraphBuilder gb = GraphBuilder.get();
		Graph graph1 = gb.generate(10, 20);
		Graph graph2 = gb.generate(20, 40);
		Graph graph3 = gb.generate(40, 80);
		Graph graph4 = gb.generate(80, 160);
		Graph graph5 = gb.generate(160, 320);
		Graph graph6 = gb.generate(320, 640);
		Graph graph7 = gb.generate(640, 1280);
		Graph graph8 = gb.generate(1280, 2560);
		Graph graph9 = gb.generate(2560, 5120);
		Graph graph10 = gb.generate(5120, 10240);
		
		// tarjan
		System.out.println(">> Tarjan");
		tarjanBench(graph1);
		tarjanBench(graph2);
		tarjanBench(graph3);
		tarjanBench(graph4);
		tarjanBench(graph5);
		tarjanBench(graph6);
		tarjanBench(graph7);
		tarjanBench(graph8);
		tarjanBench(graph9);
		tarjanBench(graph10);
		
		
		// kosaraju
		System.out.println(">> Kosaraju");
		kosarajuBench(graph1);
		kosarajuBench(graph2);
		kosarajuBench(graph3);
		kosarajuBench(graph4);
		kosarajuBench(graph5);
		kosarajuBench(graph6);
		kosarajuBench(graph7);
		kosarajuBench(graph8);
		kosarajuBench(graph9);
		kosarajuBench(graph10);
		
		// gabow
		System.out.println(">> Gabow");
		gabowBench(graph1);
		gabowBench(graph2);
		gabowBench(graph3);
		gabowBench(graph4);
		gabowBench(graph5);
		gabowBench(graph6);
		gabowBench(graph7);
		gabowBench(graph8);
		gabowBench(graph9);
		gabowBench(graph10);
	}
	
	private static void tarjanBench(Graph graph) {
		Timer.start();
		TarjanSCC.get().process(graph);
		Timer.end();
		// results printing
		//TarjanSCC.get().output();
		Timer.output();
	}
	
	private static void kosarajuBench(Graph graph) {
		Timer.start();
		KosarajuSCC.get().process(graph);
		Timer.end();
		//KosarajuSCC.get().output();
		Timer.output();
	}
	
	private static void gabowBench(Graph graph) {
		Timer.start();
		GabowSCC.get().process(graph);
		Timer.end();
		//GabowSCC.get().output();
		Timer.output();
	}
	

}
