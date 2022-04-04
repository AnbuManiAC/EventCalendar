package model;

import java.time.LocalTime;

public class TimeSlot {
	LocalTime startTime;
	LocalTime endTime;
	
	public TimeSlot(LocalTime start, LocalTime end) {
		this.startTime = start;
		this.endTime = end;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
}
