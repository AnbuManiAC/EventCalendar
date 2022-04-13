package service;

import java.util.TreeSet;
import exception.InvalidEventException;
import model.Event;
import model.RecurrenceType;

public class RecurringEventGenerator {

	private static long oneDayInMillis = 24*60*60*1000;
	Event baseEvent;
	int count;
	RecurrenceType type;

	public RecurringEventGenerator(Event baseEvent, int count, RecurrenceType type) {
		this.baseEvent = baseEvent;
		this.count = count;
		this.type = type;
	}

	public TreeSet<Event> getAllEvents() throws InvalidEventException {
		TreeSet<Event> events = new TreeSet<>();
		for (int i = 1; i <= count; i++) {
			long offset = i * oneDayInMillis * type.getOffset();
			events.add(new Event(baseEvent.getName(), baseEvent.getStartDateTime() + offset,
					baseEvent.getEndDateTime() + offset));
		}
		return events;
	}
}
