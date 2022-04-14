package service;

import database.EventManager;
import exception.InvalidEventException;
import model.Event;
import model.RecurrenceType;
import model.User;

public class RecurringEventManager {

	private static long oneDayInMillis;
	EventManager eventManager;
	User user;
	Event baseEvent;
	int count;
	RecurrenceType type;

	public RecurringEventManager(User user, Event baseEvent, int count, RecurrenceType type) {
		oneDayInMillis = 24 * 60 * 60 * 1000;
		eventManager = new EventManager();
		this.user = user;
		this.baseEvent = baseEvent;
		this.count = count;
		this.type = type;
	}

	public void createEvent() throws InvalidEventException {
		for (int i = 1; i <= count; i++) {
			long offset = i * oneDayInMillis * type.getOffset();
			eventManager.createEvent(user, baseEvent.getName(), baseEvent.getStartDateTime() + offset,
					baseEvent.getEndDateTime() + offset);
		}
	}
}
