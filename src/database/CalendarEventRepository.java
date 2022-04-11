package database;

import java.util.HashMap;
import java.util.TreeSet;

import model.Event;
import model.User;

public class CalendarEventRepository {

	private final HashMap<User, TreeSet<Event>> calendarEventsMap = new HashMap<>();
	private User currentUser;

	private CalendarEventRepository() {
		currentUser = UserAuthRepository.getInstance().getCurrentUser();
	}

	private static CalendarEventRepository instance = null;

	public static CalendarEventRepository getInstance() {
		if (instance == null)
			instance = new CalendarEventRepository();
		return instance;
	}

	public void insertRecord(Event newEvent) {
		if (calendarEventsMap.containsKey(currentUser))
			calendarEventsMap.get(currentUser).add(newEvent);
		else {
			TreeSet<Event> events = new TreeSet<>();
			events.add(newEvent);
			calendarEventsMap.put(currentUser, events);
		}
	}

	public boolean removeEvent(int eventId) {
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

//	public boolean removeRecord(long date) {
//		return calendarEventsMap.remove(date) == null ? false : true;
//	}

	public TreeSet<Event> getEventsOnThisDay(long date) {
		TreeSet<Event> events = new TreeSet<Event>();
		long max = 24*60*60*1000 - 60*1000;
		if(calendarEventsMap.containsKey(currentUser)) {
			for(Event event : calendarEventsMap.get(currentUser)) {
				long eventStartTime = event.getStartDateTime();
				if(eventStartTime >= date && eventStartTime <= date + max)
					events.add(event);
			}
		}
		return events;
	}

	public TreeSet<Event> getAllEvents() {
		TreeSet<Event> events = new TreeSet<>();
		if(calendarEventsMap.get(currentUser)!=null)
			events.addAll(calendarEventsMap.get(currentUser));
		return events;
	}

}
