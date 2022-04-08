package model;

public enum CalendarSize {
	SMALL(2,1),
	MEDIUM(5,3),
	LARGE(10,4);
	
	public final int length;
	public final int width;
	
	private CalendarSize(int width, int length) {
		this.width = width;
		this.length = length;
	}
}
