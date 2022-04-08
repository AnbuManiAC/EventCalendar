package service;

import java.time.LocalTime;
import java.util.Date;
import java.util.GregorianCalendar;

import database.CalendarEventRepository;
import database.UserAuthRepository;
import database.UserCalendarMappingRepository;
import model.Event;
import model.User;

public class EventManager {
	
	CalendarEventRepository calendarEventRepository;
	GregorianCalendar gcDate;
	
	public EventManager() {
		calendarEventRepository = getCalendar();
		gcDate = new GregorianCalendar();
	}
	
	CalendarEventRepository getCalendar() {
		UserAuthRepository userAuthenticator = UserAuthRepository.getInstance();
		UserCalendarMappingRepository userCalendars = UserCalendarMappingRepository.getInstance();
		User user = userAuthenticator.getCurrentUser();
		return userCalendars.getUserCalendar(user);
	}
	public void createEvent(String name, Long startDateTime, Long endDateTime) {
		Event newEvent = new Event(name, startDateTime, endDateTime);
		calendarEventRepository.insertRecord(gcDate, newEvent);
		
	}
	public boolean deleteEvent(Date date, int eventId) {
		gcDate.setTime(date);
		return calendarEventRepository.removeEvent(gcDate, eventId);
	}
	public boolean deleteAllEventInADay(Date date) {
		gcDate.setTime(date);
		return calendarEventRepository.removeRecord(gcDate);
	}
}
