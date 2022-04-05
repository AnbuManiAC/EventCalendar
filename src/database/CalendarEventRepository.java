package database;


import java.util.GregorianCalendar;
import java.util.TreeMap;
import java.util.TreeSet;

import model.Event;


public class CalendarEventRepository {
	private TreeMap<GregorianCalendar, TreeSet<Event>> calendarEventsMap = new TreeMap<>();

	CalendarEventRepository() {
		
	}
	
	public void insertRecord(GregorianCalendar eventDate, 	Event newEvent) {
		if(calendarEventsMap.containsKey(eventDate))
			calendarEventsMap.get(eventDate).add(newEvent);  
		else{
			TreeSet<Event> events = new TreeSet<>(); 
			events.add(newEvent);
			calendarEventsMap.put(eventDate, events);
		}
	}
	
	public boolean removeEvent(GregorianCalendar eventDate, int eventId) {
		for(GregorianCalendar date : calendarEventsMap.keySet()) {
			if(date.compareTo(eventDate)==0) {
				TreeSet<Event> events = calendarEventsMap.get(eventDate);
				for(Event event: events) {
					if(event.getId()==eventId) {
						events.remove(event);
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean removeRecord(GregorianCalendar eventDate) {
		return calendarEventsMap.remove(eventDate)==null? false:true;
	}

	public void removeAll() {
		calendarEventsMap.clear();	
	}
	
	public TreeSet<Event> getEventsOnThisDay(GregorianCalendar date){ 
		return calendarEventsMap.get(date);
	}

	public TreeSet<Event> getAllEvents(){
		
		TreeSet<Event> allEvents = new TreeSet<Event>();

		for(GregorianCalendar key : calendarEventsMap.keySet()) {
			allEvents.addAll(calendarEventsMap.get(key));
		}
		return allEvents;
	}

	
	public boolean checkDateforEvent(GregorianCalendar gc) {
		if(calendarEventsMap.containsKey(gc))
			return true;
		return false;
	}
	
}
