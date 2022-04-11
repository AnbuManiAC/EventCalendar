package service;

import java.util.Date;
import java.util.TreeSet;

import database.CalendarEventRepository;
import model.Event;

public class EventManager {
	
	CalendarEventRepository calendarEventRepository;
	
	public EventManager() {
		calendarEventRepository = CalendarEventRepository.getInstance();
	}
	
	public void createEvent(String name, Long startDateTime, Long endDateTime) {
		Event newEvent = new Event(name, startDateTime, endDateTime);
		calendarEventRepository.insertRecord(newEvent);
		
	}
	public boolean deleteEvent(int eventId) {
		return calendarEventRepository.removeEvent(eventId);
	}
	public boolean deleteAllEventInADay(Date date) {
		
		return false;
	}
}
