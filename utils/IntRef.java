package utils;

public final class IntRef {
	
	private Integer data;
	
	private IntRef(int value) {
		this.data = new Integer(value);
	}
	
	public static IntRef of(int value) {
		return new IntRef(value);
	}
	
	public IntRef inc() {
		data = new Integer(data.intValue() + 1);
		return this;
	}
	
	public IntRef dec() {
		data = new Integer(data.intValue() - 1);
		return this;
	}
	
	public int get() {
		return data.intValue();
	}
}
