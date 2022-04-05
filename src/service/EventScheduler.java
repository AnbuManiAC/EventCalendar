package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.GregorianCalendar;

import database.CalendarEventRepository;
import database.UserAuthRepository;
import database.UserCalendarMappingRepository;
import model.Event;
import model.TimeSlot;
import model.User;

public class EventScheduler {
	
	private CalendarEventRepository getUserCalendar() {
		UserAuthRepository userAuths = UserAuthRepository.getInstance();
		UserCalendarMappingRepository userCalendars = UserCalendarMappingRepository.getInstance();
		User user = userAuths.getCurrentUser();
		return userCalendars.getUserCelendar(user);
	}
	
	public void createEvent(String name,String inputDate,String startTime,String endTime){
		CalendarEventRepository userCalendar = getUserCalendar();
		LocalTime start = LocalTime.parse(startTime);
		LocalTime end  = LocalTime.parse(endTime);
		Date date;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(inputDate);
			Event newEvent = new Event(name, date, new TimeSlot(start, end));  
			GregorianCalendar gcDate = new GregorianCalendar();
			gcDate.setTime(newEvent.getDate());
			userCalendar.insertRecord(gcDate, newEvent);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	 
	}

//	public  boolean checkEventCollision(Event event){  
//		boolean collision=false;
//		CalendarEventRepository userCalendar = getUserCalendar();
//		for(GregorianCalendar key : userCalendar){
//			
//			if(key.getTime().equals(event.getDate()))
//			{
//				for(Event e : calendarEventsMap.get(key)){
//					TimeSlot ts = e.getTimeSlot();
//					if(ts.getStartTime().equals(event.getTimeSlot().getStartTime()) )
//					{
//						collision=true;
//					}
//				}
//			}
//		}
//		return collision;
//	}
	
}
