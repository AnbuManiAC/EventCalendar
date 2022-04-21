package database;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import model.Event;
import model.User;

public class CalendarEventRepository {

	private final Map<User, TreeSet<Event>> userEventsMap = new HashMap<>();

	private CalendarEventRepository() {
	}

	private static CalendarEventRepository instance = null;

	static CalendarEventRepository getInstance() {
		if (instance == null)
			instance = new CalendarEventRepository();
		return instance;
	}

	void insertRecord(User currentUser, Event newEvent) {
		if (userEventsMap.containsKey(currentUser))
			userEventsMap.get(currentUser).add(newEvent);
		else {
			TreeSet<Event> events = new TreeSet<>();
			events.add(newEvent);
			userEventsMap.put(currentUser, events);
		}
	}

	boolean removeEvent(User currentUser, int eventId) {
		Event event = getEventById(currentUser, eventId);
		if(event!=null) {
			userEventsMap.get(currentUser).remove(event);
			return true;
		}
		return false;
	}
	TreeSet<Event> getEventsForUser(User currentUser) {
		TreeSet<Event> events = new TreeSet<>();
		if (userEventsMap.get(currentUser) != null)
			events.addAll(userEventsMap.get(currentUser));
		return events;
	}
	Event getEventById(User currentUser, int eventId) {
		if(userEventsMap.containsKey(currentUser)) {
			for(Event event:userEventsMap.get(currentUser)) {
				if(event.getId() == eventId) 
					return event;
			}
		}
		return null;
	}

}
