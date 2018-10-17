package utils;

public final class Timer {
	
	public final static byte NANOSECONDS = 0;
	public final static byte MILLISECONDS = 1;
	
	private static long start = 0L;
	private static long nstart = 0L;
	private static long end = 0L;
	private static long nend = 0L;
	private static byte flag = MILLISECONDS;
	
	private Timer() {}
	
	public static void start() {
		start = System.currentTimeMillis();
		nstart = System.nanoTime();
	}
	
	public static void end() {
		end = System.currentTimeMillis();
		nend = System.nanoTime();
	}
	
	public static void output() {
		long diff = end - start;
		flag = MILLISECONDS;
		
		if(diff < 1) {
			diff = nend - nstart;
			flag = NANOSECONDS;
		}

		System.out.print("Time: " + diff);
		
		if(flag == MILLISECONDS) System.out.println(" ms.");
		else System.out.println(" ns.");
		
		start = 0L;
		end = 0L;
		nstart = 0L;
		nend = 0L;
	}
	
	public static void output(byte granularity) {
		if(granularity == MILLISECONDS) outputMs();
		else outputNs();
	}
	
	private static void outputMs() {
		long diff = end - start;
		flag = MILLISECONDS;

		System.out.print("Time: " + diff);
		System.out.println(" ms.");
		
		start = 0L;
		end = 0L;
	}
	
	private static void outputNs() {
		long diff = nend - nstart;
		flag = NANOSECONDS;

		System.out.print("Time: " + diff);
		System.out.println(" ns.");
		
		nstart = 0L;
		nend = 0L;
	}
	
}
