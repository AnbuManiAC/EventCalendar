package model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyCalendar {

	GregorianCalendar calendar;
	private CalendarSize calendarSizePreference;

	public static final String[] MONTHS = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };
	public static final String[] DAYS = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
			"Saturday" };

	public MyCalendar() {
		calendar = new GregorianCalendar();
		calendarSizePreference = CalendarSize.MEDIUM;
	}

	public CalendarSize getCalendarSizePreference() {
		return this.calendarSizePreference;
	}

	public void setCalendarSizePreference(CalendarSize calendarSizePreference) {
		this.calendarSizePreference = calendarSizePreference;
	}

	public enum CalendarSize {
		SMALL(2, 1), MEDIUM(3, 2), LARGE(9, 4);

		private int width;
		private int length;

		CalendarSize(int width, int length) {
			this.width = width;
			this.length = length;
		}

		public int getWidth() {
			return width;
		}

		public int getLength() {
			return length;
		}
	}

	public String nextMonth(GregorianCalendar calendar) {
		calendar.add(Calendar.MONTH, 1);
		return currentMonth(calendar);
	}

	public String previousMonth(GregorianCalendar calendar) {
		calendar.add(Calendar.MONTH, -1);
		return currentMonth(calendar);
	}
	
	public String currentMonth(GregorianCalendar calendar) {
		return currentMonth(calendar, calendarSizePreference);
	}

	public String currentMonth(GregorianCalendar calendar, CalendarSize calendarSize) {
		
		int width = calendarSize.getWidth();
		int length = calendarSize.length;

		StringBuilder monthlyCalendar = new StringBuilder();
		int monthIndex = calendar.get(Calendar.MONTH);
		int yearValue = calendar.get(Calendar.YEAR);
		GregorianCalendar calendarMonthStart = new GregorianCalendar(yearValue, monthIndex, 1);
		int dayIndex = calendarMonthStart.get(Calendar.DAY_OF_WEEK) - 1;
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		monthlyCalendar.append(formatMonthYear(yearValue, monthIndex, 7 * (width + 1) - 1).stripTrailing());
		monthlyCalendar.append("\n".repeat(length));
		monthlyCalendar.append(formatWeekHeader(width).stripTrailing());
		monthlyCalendar.append("\n".repeat(length));
		monthlyCalendar.append(formatMonthDays(dayIndex, daysInMonth, width, length));

		return monthlyCalendar.toString();

	}


	private String formatMonthDays(int dayIndex, int daysInMonth, int width, int length) {
		
		StringBuilder monthDays = new StringBuilder();
		for (int i = 0; i < dayIndex; i++)
			monthDays.append(" ".repeat(width + 1));
		for (int i = 1; i <= daysInMonth; i++) {
			if (i == calendar.get(Calendar.DAY_OF_MONTH)
					&& calendar.get(Calendar.MONTH) == new GregorianCalendar().get(Calendar.MONTH)
					&& calendar.get(Calendar.YEAR) == new GregorianCalendar().get(Calendar.YEAR))
				monthDays.append(center(" [" + i + "]", width + 1));
			else if (i < 10)
				monthDays.append(center(i + "", width + 1));
			else if (width > 2)
				monthDays.append(center(i + "", width + 1));
			else
				monthDays.append(left(i + "", width + 1));

			if ((i + dayIndex) % 7 == 0)
				monthDays.append("\n".repeat(length));
		}
		return monthDays.toString();
	}

	private String formatMonthYear(int year, int month, int width) {
		StringBuilder monthYear = new StringBuilder();
		monthYear.append(MONTHS[month] + " " + year);
		return center(monthYear.toString(), width);

	}	

	private String formatWeekHeader(int width) {
		StringBuilder weekHeader = new StringBuilder();
		int word, space;
		if (width <= 2) {
			word = 2;
			space = width - 1;
			for (int i = 0; i < DAYS.length; i++) {
				weekHeader.append(DAYS[i].substring(0, word) + " ".repeat(space));
			}
		} else if (width < 9) {
			word = 3;
			space = width - 2;
			for (int i = 0; i < DAYS.length; i++) {
				weekHeader.append(DAYS[i].substring(0, word) + " ".repeat(space));
			}
		} else {
			word = 0;
			space = 0;
			for (int i = 0; i < DAYS.length; i++) {
				weekHeader.append(" " + center(DAYS[i], width));
			}
		}

		return weekHeader.toString();
	}

	private String center(String str, int width) {
		return String.format("%-" + width + "s",
				String.format("%" + (str.length() + (width - str.length() + 1) / 2) + "s", str));
	}

	private String left(String str, int width) {
		return String.format("%s" + " ".repeat(width - str.length()), str);
	}
}
