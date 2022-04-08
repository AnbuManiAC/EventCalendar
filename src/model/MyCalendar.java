package model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import utility.DateFormatter;

public class MyCalendar{
	// dd/MM/yyyy -> {Event1, Event2[name, date, time]}
//	public static GregorianCalendar gCalendar = new GregorianCalendar();
	public static final String[] MONTHS = { "", "January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December" };
	public static final int[] MONTH_DAY_COUNT = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	public static final int[] LEAP_MONTH_DAY_COUNT = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	public static final String[] DAYS = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
			"Saturday" };

	public String getNextMonth(GregorianCalendar calendar) {
		calendar.add(Calendar.MONTH, 1);
		String nextMonth = getCurrentMonth(calendar);
		return nextMonth;
	}

	public String getPreviousMonth(GregorianCalendar calendar) {
		calendar.add(Calendar.MONTH, -1);
		String previousMonth = getCurrentMonth(calendar);
		return previousMonth;
	}

	public String getCurrentMonth(GregorianCalendar calendar) {

		int monthIndex = calendar.get(Calendar.MONTH);
		int yearValue = calendar.get(Calendar.YEAR);

		GregorianCalendar calMonthStart = new GregorianCalendar(yearValue, monthIndex, 1);
		int dayIndex = calMonthStart.get(Calendar.DAY_OF_WEEK) - 1;
		// String d = dayNames[dayIndex];
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		System.out.println("\n" + MONTHS[monthIndex] + "  " + yearValue);
		System.out.println("\nSu  Mo  Tu  We  Th  Fr  Sa\n");

		for (int j = 0; j < dayIndex; j++) {
			System.out.print("    ");
		}
		for (int i = 1; i <= daysInMonth; i++) {
			String dayS;
			if (i < 10) {
				dayS = "0" + i;
			} else {
				dayS = String.valueOf(i);
			}
			int monthIndx = calendar.get(Calendar.MONTH) + 1;
			String monthS;
			if (monthIndx < 10) {
				monthS = "0" + monthIndx;
			} else {
				monthS = String.valueOf(monthIndx);
			}
			String yearS = String.valueOf(calendar.get(Calendar.YEAR));
			String dateOfi = dayS + "-" + monthS + "-" + yearS;
			Date date = DateFormatter.StringtoDate(dateOfi);
			GregorianCalendar key = new GregorianCalendar();
			key.setTime(date);

			if (i == calendar.get(Calendar.DAY_OF_MONTH))
				System.out.print("[" + i + "] ");

//			else if(calendarEventsMap.containsKey(key))
//				System.out.print("{"+i+"} "); 
			else if (i < 10)
				System.out.print(i + "   ");
			else
				System.out.print(i + "  ");
			if (((dayIndex + i) % 7 == 0) || (i == daysInMonth)) {
				System.out.println("\n");
			}
		}
		return MONTHS[monthIndex];

	}

	public String getNextDay(GregorianCalendar calendar) {
		calendar.add(Calendar.DAY_OF_WEEK, 1);
		String nextDay = getCurrentDay(calendar);
		return nextDay;
	}

	public String getPreviousDay(GregorianCalendar calendar) {
		calendar.add(Calendar.DAY_OF_WEEK, -1);
		String previousDay = getCurrentDay(calendar);
		return previousDay;
	}

	public String getCurrentDay(GregorianCalendar calendar) {
		String today = DAYS[calendar.get(Calendar.DAY_OF_WEEK) - 1];
		String monthShort = MONTHS[calendar.get(Calendar.MONTH)];
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		int year = calendar.get(Calendar.YEAR);

		System.out.println(today + ", " + monthShort + " " + dayOfMonth + ", " + year);

		String calendarString = gcToString(calendar);
//		getEventsOnThisDay(calendarString);

		String x = "";
		return x;
	}

	public GregorianCalendar stringToGC(String dateString) {

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(DateFormatter.StringtoDate(dateString));
		return calendar;
	}

	public String gcToString(GregorianCalendar calendar) {

		String day;
		int tempDay = calendar.get(Calendar.DAY_OF_MONTH);
		if (tempDay < 10) {
			day = "0" + tempDay;
		} else {
			day = String.valueOf(tempDay);
		}

		String month;
		int tempMonth = calendar.get(Calendar.MONTH) + 1;
		if (tempMonth < 10) {
			month = "0" + tempMonth;
		} else {
			month = String.valueOf(tempMonth);
		}

		String year = String.valueOf(calendar.get(Calendar.YEAR));

		return day + "-" + month + "-" + year;

	}

	public String getMonthView(int year, int month) {

		return getMonthView(year, month, 0, 0);
	}

	public String getMonthView(int year, int month, int width, int length) {
		StringBuilder result = new StringBuilder();
		width = Math.max(9, width);
		length = Math.max(4, length);
		result.append(formatMonthYear(year, month, 7 * (width + 1) - 1).stripTrailing());
		result.append("\n".repeat(length));
		result.append(formatWeekHeader(width).stripTrailing());
		result.append("\n".repeat(length));

		result.append(formatMonthDays(year, month, width, length));
		System.out.println(result);

		return "";
	}

	private String formatMonthDays(int year, int month, int width, int length) {
		int day = 1;
		month -= month;
		GregorianCalendar monthDetailProvider = new GregorianCalendar(year, month, day);
		int startDayIndex = monthDetailProvider.get(Calendar.DAY_OF_WEEK) - 1;
		StringBuilder result = new StringBuilder();

		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day);
		int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
//        int startDay = calendar.getTime().getDay();
		printMonth(new GregorianCalendar());
		System.out.println(startDayIndex);
//        for(int i=0;i<startDayIndex;i++)
//        	result.append(" ".repeat(width/2));
		for (int i = 1; i <= maxDay; i++) {
			if (i < 10)
				result.append(center(i + "", width + 1));
			else if (width > 2)
				result.append(center(i + "", width + 1));
			else
				result.append(left(i + "", width + 1));

			if ((i + startDayIndex + 1) % 7 == 0)
				result.append("\n".repeat(length));
		}

		return result.toString();
	}

	private String formatMonthYear(int year, int month, int width) {
		StringBuilder result = new StringBuilder();
		result.append(MONTHS[month] + " " + year);
		return center(result.toString(), width);

	}

	private String formatWeekHeader(int width) {
		StringBuilder result = new StringBuilder();
		int word, space;
		if (width <= 2) {
			word = 2;
			space = width - 1;
			for (int i = 0; i < DAYS.length; i++) {
				result.append(DAYS[i].substring(0, word) + " ".repeat(space));
			}
		} else if (width < 9) {
			word = 3;
			space = width - 2;
			for (int i = 0; i < DAYS.length; i++) {
				result.append(DAYS[i].substring(0, word) + " ".repeat(space));
			}
		} else {
			word = 0;
			space = 0;
			for (int i = 0; i < DAYS.length; i++) {
				result.append(" " + center(DAYS[i], width));
			}
		}

		return result.toString();
	}

	private String center(String str, int width) {
		return String.format("%-" + width + "s",
				String.format("%" + (str.length() + (width - str.length() + 1) / 2) + "s", str));
	}

	private String left(String str, int width) {
		return String.format("%s" + " ".repeat(width - str.length()), str);
	}

	public String printMonth(GregorianCalendar calendar) {

		int monthIndex = calendar.get(Calendar.MONTH); // retrieves the index of the month from 0-11
		int yearValue = calendar.get(Calendar.YEAR); // retrieves the year value from the calendar parameter

		GregorianCalendar calMonthStart = new GregorianCalendar(yearValue, monthIndex, 1);
		int dayIndex = calMonthStart.get(Calendar.DAY_OF_WEEK) - 1; // 1st day of month index
		String d = DAYS[dayIndex]; // 1st day of month name
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);// getting the days in a month

		// Printing the Month value and the Year value
		System.out.println(MONTHS[monthIndex + 1] + "  " + yearValue);
		System.out.println("Su  Mo  Tu  We  Th  Fr  Sa");

		for (int j = 0; j < dayIndex; j++) // j< first day of week
		{
			System.out.print("    ");
		}
		for (int i = 1; i <= daysInMonth; i++) {
			String dayS;
			if (i < 10) {
				dayS = "0" + i;
			} else {
				dayS = String.valueOf(i);
			}
			int monthIndx = calendar.get(Calendar.MONTH);
			String monthS;
			if (monthIndx < 10) {
				monthS = "0" + monthIndx;
			} else {
				monthS = String.valueOf(monthIndx);
			}
			String yearS = String.valueOf(calendar.get(Calendar.YEAR));
			String dateOfi = dayS + "-" + monthS + "-" + yearS;
			GregorianCalendar key = new GregorianCalendar();
			key = stringToGC(dateOfi);

			if (i == calendar.get(Calendar.DAY_OF_MONTH))
				System.out.print("[" + i + "] ");

			else if (i < 10)
				System.out.print(i + "   ");
			else
				System.out.print(i + "  ");
			if (((dayIndex + i) % 7 == 0) || (i == daysInMonth)) {
				System.out.println("\n");
			}
		}
		String x = "";
		return x;
	}
}
