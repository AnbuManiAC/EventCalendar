package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import database.CalendarEventRepository;
import database.UserAuthRepository;
import database.UserCalendarMappingRepository;
import model.User;
import utility.DateFormatter;

public class EventUnscheduler {
	
	private CalendarEventRepository userCalendar;

	
	public EventUnscheduler() {
		userCalendar = getUserCalendar();
	}
	
	private CalendarEventRepository getUserCalendar() {
		UserAuthRepository userAuths = UserAuthRepository.getInstance();
		UserCalendarMappingRepository userCalendars = UserCalendarMappingRepository.getInstance();
		User user = userAuths.getCurrentUser();
		return userCalendars.getUserCelendar(user);
	}

	public boolean deleteSelectedDate(String stringDateToDelete){ 

		Date dateToDelete = null;
		GregorianCalendar gcDateToDelete = new GregorianCalendar() ;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		try{
			dateToDelete= dateFormatter.parse(stringDateToDelete);
			gcDateToDelete.setTime(dateToDelete);
		}
		catch(ParseException e) {
			System.out.println(e.getMessage());
		}
		return userCalendar.removeRecord(gcDateToDelete);
	}
	
	public boolean deleteSelectedEvent(String date, int eventId) {
		
		
		Date dateOfEvent = DateFormatter.StringtoDate(date);
		GregorianCalendar dateKey = new GregorianCalendar();
		dateKey.setTime(dateOfEvent);
		return userCalendar.removeEvent(dateKey, eventId);
		
	}
	public void deleteAll(){
		userCalendar.removeAll();
	}
	
}
