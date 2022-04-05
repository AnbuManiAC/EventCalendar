package utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

	public static Date StringtoDate(String date) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		Date dateObj = null;
		try {
			dateObj = dateFormatter.parse(date);
		} catch (ParseException e) {
			System.out.println(e.getMessage());
		}
		return dateObj;
	}
	
}
