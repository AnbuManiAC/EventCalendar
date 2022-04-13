package model;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyCalendar {

	GregorianCalendar calendar = new GregorianCalendar();

	public static final String[] MONTHS = { "January", "February", "March", "April", "May", "June", "July", "August",
			"September", "October", "November", "December" };
	public static final String[] DAYS = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
			"Saturday" };

	
	public String nextMonth(GregorianCalendar calendar) {
		calendar.add(Calendar.MONTH, 1);
		return currentMonth(calendar);
	}

	public String previousMonth(GregorianCalendar calendar) {
		calendar.add(Calendar.MONTH, -1);
		return currentMonth(calendar);
	}
	
	public String currentMonth(GregorianCalendar calendar) {

		StringBuilder result = new StringBuilder();
		int monthIndex = calendar.get(Calendar.MONTH);
		int yearValue = calendar.get(Calendar.YEAR);
		GregorianCalendar calendarMonthStart = new GregorianCalendar(yearValue, monthIndex, 1);
		int dayIndex = calendarMonthStart.get(Calendar.DAY_OF_WEEK) - 1;
		int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		int width = 9;
		int length = 4;
		
		result.append(formatMonthYear(yearValue, monthIndex, 7 * (width + 1) - 1).stripTrailing());
		result.append("\n".repeat(length));
		result.append(formatWeekHeader(width).stripTrailing());
		result.append("\n".repeat(length));
		for(int i=0;i<dayIndex;i++)
        	result.append(" ".repeat(width+1));
		for (int i = 1; i <= daysInMonth; i++) {
			if (i == calendar.get(Calendar.DAY_OF_MONTH)
					&& calendar.get(Calendar.MONTH) == new GregorianCalendar().get(Calendar.MONTH)
					&& calendar.get(Calendar.YEAR) == new GregorianCalendar().get(Calendar.YEAR))
				result.append(center(" [" + i + "]",width+1));
			else if (i < 10)
				result.append(center(i + "", width + 1));
			else if (width > 2)
				result.append(center(i + "", width + 1));
			else
				result.append(left(i + "", width + 1));

			if ((i + dayIndex) % 7 == 0)
				result.append("\n".repeat(length));
		}

		return result.toString();
		
//		currentMonthView.append("\n" + MONTHS[monthIndex] + "  " + yearValue);
//		currentMonthView.append("\n\nSu  Mo  Tu  We  Th  Fr  Sa\n\n");
//
//		for (int j = 0; j < dayIndex; j++) {
//			currentMonthView.append("    ");
//		}
//		for (int i = 1; i <= daysInMonth; i++) {
//
//			if (i == calendar.get(Calendar.DAY_OF_MONTH)
//					&& calendar.get(Calendar.MONTH) == new GregorianCalendar().get(Calendar.MONTH)
//					&& calendar.get(Calendar.YEAR) == new GregorianCalendar().get(Calendar.YEAR))
//				currentMonthView.append("[" + i + "] ");
//			else if (i < 10)
//				currentMonthView.append(i + "   ");
//			else
//				currentMonthView.append(i + "  ");
//			if (((dayIndex + i) % 7 == 0) || (i == daysInMonth)) {
//				currentMonthView.append("\n\n");
//			}
//		}
//		return currentMonthView.toString();

	}

	public String getMonthView(int year, int month) {

		return getMonthView(year, month, 0, 0);
	}

	public String getMonthView(int year, int month, int width, int length) {
		StringBuilder result = new StringBuilder();
		width = Math.max(9, width);
		length = Math.max(2, length);
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
		startDayIndex = 5;
		System.out.println();
		System.out.println(startDayIndex);
        for(int i=0;i<startDayIndex;i++)
        	result.append(" ".repeat(width+1));
		for (int i = 1; i <= maxDay; i++) {
			if (i < 10)
				result.append(center(i + "", width + 1));
			else if (width > 2)
				result.append(center(i + "", width + 1));
			else
				result.append(left(i + "", width + 1));

			if ((i + startDayIndex) % 7 == 0)
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
