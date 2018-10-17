package core;

import model.Graph;

/**
 * Simple graph processor.<br>
 * Processes a graph and output the results.<br>
 *
 * 
 * @author Damien FORNALI
 */
public interface IGraphProcessor {
	
	/**
	 * Launch the processor execution.<br>
	 * @param graph to process
	 */
	public void process(Graph graph);
	
	/**
	 * Outputs the processing results.
	 */
	public void output();
}
