package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import exception.TimeParseException;

public class DateAndTimeFormatter {
	
	public long dateTimeToMillisecond(String date, String time) throws ParseException {
		date = date + " " + time;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		Date dateObj = null;
		dateObj = dateFormatter.parse(date);
		return dateObj.getTime();
	}
	
	public long dateToMillisecond(String date) throws ParseException {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		Date dateObj = null;
		dateObj = dateFormatter.parse(date);

		return dateObj.getTime();
	}
	
	public String millisecondToDate(long millisecond) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy  HH:mm");
		Date date = new Date(millisecond);
		return dateFormatter.format(date);
	}
	
	public Date toDate(String date) throws ParseException {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		Date dateObj = null;
		dateObj = dateFormatter.parse(date);

		return dateObj;
	}
	
	
	public LocalTime toLocalTime(String time) throws TimeParseException {
		LocalTime timeObj = null;
		try {
			timeObj	= LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
		} catch (Exception e) {
			throw new TimeParseException();
		}
		return timeObj;
	}
}
