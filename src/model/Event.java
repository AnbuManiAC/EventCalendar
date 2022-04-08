package model;

public class Event implements Comparable<Event> {
	private static int nextId = 1;
	private int id;
	private String name;
	Long startDateTime;
	Long endDateTime;
	
	public Event(String name, long startDateTime, long endDateTime) {
		this.id = nextId++;
		this.name = name;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}

	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(long startDateTime) {
		this.startDateTime = startDateTime;
	}

	public long getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(long endDateTime) {
		this.endDateTime = endDateTime;
	}

	public int compareTo(Event other){
		int startDateComparator	 = this.startDateTime.compareTo(other.startDateTime);
		if(startDateComparator != 0)
			return startDateComparator;
		
		int nameCmp = this.getName().compareTo(other.getName());
		
		if(nameCmp!=0) 
			return nameCmp;
		
		int endDateTimeComparator = this.endDateTime.compareTo(other.endDateTime);
		
		return endDateTimeComparator;
	}
	
}
