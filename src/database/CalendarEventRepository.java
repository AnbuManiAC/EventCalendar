package database;


import java.util.HashMap;
import java.util.TreeSet;
import model.Event;
import model.User;

public class CalendarEventRepository {

	private final HashMap<User, TreeSet<Event>> calendarEventsMap = new HashMap<>();

	private CalendarEventRepository() {
	}

	private static CalendarEventRepository instance = null;

	static CalendarEventRepository getInstance() {
		if (instance == null)
			instance = new CalendarEventRepository();
		return instance;
	}

	void insertRecord(User currentUser, Event newEvent) {
		if (calendarEventsMap.containsKey(currentUser))
			calendarEventsMap.get(currentUser).add(newEvent);
		else {
			TreeSet<Event> events = new TreeSet<>();
			events.add(newEvent);
			calendarEventsMap.put(currentUser, events);
		}
	}

	boolean removeEvent(User currentUser, int eventId) {
		if (calendarEventsMap.containsKey(currentUser)) {
			for (Event event : calendarEventsMap.get(currentUser)) {
				if (event.getId() == eventId) {
					calendarEventsMap.get(currentUser).remove(event);
					return true;
				}
			}
		}
		return false;
	}
	TreeSet<Event> getEventsForUser(User currentUser) {
		TreeSet<Event> events = new TreeSet<>();
		if (calendarEventsMap.get(currentUser) != null)
			events.addAll(calendarEventsMap.get(currentUser));
		return events;
	}

}
