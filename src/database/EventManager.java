package database;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TreeSet;
import java.util.stream.Collectors;
import exception.InvalidEventException;
import model.Event;
import model.User;
import util.DateAndTimeFormatter;

public class EventManager {

	CalendarEventRepository calendarEventRepository;
	static long oneDayInMillis = 24 * 60 * 60 * 1000;

	public EventManager() {
		calendarEventRepository = CalendarEventRepository.getInstance();
	}

	public Event createEvent(User user, String name, Long startDateTime, Long endDateTime)
			throws InvalidEventException {
		Event newEvent = new Event(name, startDateTime, endDateTime);
		calendarEventRepository.insertRecord(user, newEvent);
		return newEvent;
	}

	public boolean deleteEvent(User user, int eventId) {
		return calendarEventRepository.removeEvent(user, eventId);
	}

//	public boolean deleteAllEventInADay(Date date) {
//		return false;
//	}
	public TreeSet<Event> getAllEvents(User user){
		return calendarEventRepository.getEventsForUser(user);
	}

	public TreeSet<Event> filterEventsOnThisDay(TreeSet<Event> events, long date) {
		return new TreeSet<>(events
				.stream()
				.filter(event -> event.getStartDateTime() >= date && event.getStartDateTime() < date + oneDayInMillis)
				.collect(Collectors.toSet()));
	}

	public TreeSet<Event> getEventsBefore(TreeSet<Event> events, long date) {
		return new TreeSet<>(events
				.stream()
				.filter(event -> event.getStartDateTime() < date)
				.collect(Collectors.toSet()));
	}

	public TreeSet<Event> getEventsAfter(TreeSet<Event> events, long date) {
		return new TreeSet<>(events
				.stream()
				.filter(event -> event.getStartDateTime() >= date)
				.collect(Collectors.toSet()));
	}

	public TreeSet<Event> getPastEvents(TreeSet<Event> events) {
		return getEventsBefore(events, getTodayDateInMillis());
	}

	public TreeSet<Event> getFutureEvents(TreeSet<Event> events) {
		return getEventsAfter(events, getTodayDateInMillis()+oneDayInMillis);
	}
	public TreeSet<Event> getTodayEvents(TreeSet<Event> events){
		return filterEventsOnThisDay(events, getTodayDateInMillis());
	}

	private long getTodayDateInMillis() {
		GregorianCalendar calendar = new GregorianCalendar();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int date = calendar.get(Calendar.DATE);
		String todayDate = date + "-" + month + "-" + year;
		return DateAndTimeFormatter.dateToMillisecond(todayDate);
	}
}
