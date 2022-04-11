package model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import utility.DateFormatter;

public class MyCalendar {

	GregorianCalendar calendar = new GregorianCalendar();

	public static final String[] MONTHS = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };
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

			if (i == calendar.get(Calendar.DAY_OF_MONTH)
					&& calendar.get(Calendar.MONTH) == new GregorianCalendar().get(Calendar.MONTH))
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
}
