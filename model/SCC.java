package model;

import java.util.ArrayList;
import java.util.List;


/**
 * A class to represent the strongly connected components.
 * 
 * 
 * @author Damien FORNALI
 */
public final class SCC {
	
	// all the components
	private List<List<Integer>> components;
	// used to output result
	private StringBuilder sb;
	
	private int ptr;
	
	private SCC() {
		ptr = -1;
		components = new ArrayList<>();
		sb = new StringBuilder();
	}
	
	public static SCC empty() {
		return new SCC();
	}
	
	// Add a new component
	public void grow() {
		components.add(new ArrayList<>());
		++ptr;
	}
	
	// Removes the last component
	public void reduce() {
		components.remove(components.get(ptr));
		--ptr;
	}
	
	// Add a vertex to the current component
	public void add(int vertex) {
		components.get(ptr).add(vertex);
	}
	
	// Reset the SCC
	public void clear() {
		components.clear();
		sb.setLength(0);
		ptr = -1;
	}
	
	// Output all the data
	public void output(String title) {
		sb.append(">> ");
		sb.append(title);
		sb.append("\n");
		sb.append("SCC: [");
		
		int size = components.size();
		List<Integer> ref;
		
		for(int i = 0; i < size - 1; ++i) {
			sb.append("[");
			
			ref = components.get(i);
			int csize = ref.size();
			
			for(int j = 0; j < csize - 1; ++j) {
				sb.append(ref.get(j));
				sb.append(",");
			}
			
			sb.append(ref.get(csize - 1));
			sb.append("]");
			
			if(i + 1 < size) {
				sb.append(", ");
			}
		}
		
		ref = components.get(size - 1);
		int csize = ref.size();

		if(csize > 0) {
			sb.append("[");
			
			for(int j = 0; j < csize - 1; ++j) {
				sb.append(ref.get(j));
				sb.append(",");
			}
			
			sb.append(ref.get(csize - 1));
			sb.append("]");
			
			sb.append("]\n");
		}
		
		System.out.print(sb.toString());
		
		sizeOutput(); // debug
	}
	
	// Output the components sizes
	private void sizeOutput() {
		sb.setLength(0);
		
		sb.append("SCC: [");
		
		int size = components.size();
		
		for(int i = 0; i < size - 1; ++i) {
			sb.append("[");
			sb.append(components.get(i).size());
			sb.append("]");
			
			if(i + 1 < size) {
				sb.append(", ");
			}
		}
		
		sb.append("[");
		sb.append(components.get(size - 1).size());
		sb.append("]");
		
		sb.append("]\n");
		
		System.out.print(sb.toString());
	}
}
