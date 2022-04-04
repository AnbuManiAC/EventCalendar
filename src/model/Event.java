package model;

import java.time.LocalTime;
import java.util.Date;

public class Event implements Comparable<Event> {
	private static int nextId = 1;
	private int id;
	private String name;
	private Date date;
	private TimeSlot timeSlot;
	
	public Event(String name, Date date, TimeSlot timeSlot) {
		this.id = nextId++;
		this.name = name;
		this.date = date;
		this.timeSlot = timeSlot;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public TimeSlot getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	public int compareTo(Event other){
		int dateComparator	 = this.date.compareTo(other.date);
		if(dateComparator != 0)
			return dateComparator;
						
		LocalTime thisStartTime = this.getTimeSlot().getStartTime();
		LocalTime otherStartTime = other.getTimeSlot().getStartTime();
		
		int startTimeCmp = thisStartTime.compareTo(otherStartTime);
		if(startTimeCmp!=0)
			return startTimeCmp;
		String thisName = this.getName();
		String otherName = other.getName();
		
		int nameCmp = thisName.compareTo(otherName);
		
		if(nameCmp!=0) 
			return nameCmp;
		
		LocalTime thisEndTime = this.getTimeSlot().getEndTime();
		LocalTime otherEndTime = other.getTimeSlot().getEndTime();
		
		int endTimeCmp = thisEndTime.compareTo(otherEndTime);
		
		return endTimeCmp;
	}

	
	
}
