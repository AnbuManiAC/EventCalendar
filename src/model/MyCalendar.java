package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TreeSet;

import utility.DateFormatter;

public class MyCalendar extends GregorianCalendar {
	//dd/MM/yyyy -> {Event1, Event2[name, date, time]}
//	public static GregorianCalendar gCalendar = new GregorianCalendar();
	static final String[] monthNames = {"January","February","March","April","May","June","July","August",
			"September","October","November","December"};
	static final String[] dayNames = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

	
	public String getNextMonth(GregorianCalendar calendar) {
		calendar.add(MyCalendar.MONTH, 1);
		String nextMonth= getCurrentMonth(calendar);
		return nextMonth;
		}
	
	public String getPreviousMonth(GregorianCalendar calendar) {
		calendar.add(MyCalendar.MONTH, -1);
		String previousMonth= getCurrentMonth(calendar);
		return previousMonth;
	}	

     
	public String getCurrentMonth(GregorianCalendar calendar){

		int monthIndex= calendar.get(MyCalendar.MONTH);
		int yearValue= calendar.get(MyCalendar.YEAR);

		GregorianCalendar  calMonthStart = new GregorianCalendar(yearValue, monthIndex, 1); 
		int dayIndex = calMonthStart.get(MyCalendar.DAY_OF_WEEK)-1;
		//String d = dayNames[dayIndex];
		int daysInMonth=calendar.getActualMaximum(MyCalendar.DAY_OF_MONTH);
		
		System.out.println("\n"+monthNames[monthIndex]+"  "+ yearValue);
		System.out.println("\nSu  Mo  Tu  We  Th  Fr  Sa\n");   

		for(int j=0; j<dayIndex;j++)  
		{
			System.out.print("    ");  
		}
		for(int i=1; i<=daysInMonth; i++)  
		{	
			String dayS;
			if(i<10){dayS="0"+i;}else{dayS=String.valueOf(i);}
			int monthIndx= calendar.get(MyCalendar.MONTH)+1;
			String monthS;
			if( monthIndx<10){monthS="0"+monthIndx ;}else{monthS=String.valueOf( monthIndx);}
			String yearS= String.valueOf(calendar.get(MyCalendar.YEAR));
			String dateOfi= dayS+"-"+monthS+"-"+yearS;
			Date date = DateFormatter.StringtoDate(dateOfi);
			GregorianCalendar key=new GregorianCalendar();
			key.setTime(date);
			
			if(i == calendar.get(MyCalendar.DAY_OF_MONTH))
				System.out.print("["+i+"] "); 
			
			
//			else if(calendarEventsMap.containsKey(key))
//				System.out.print("{"+i+"} "); 
			else if(i<10)
				System.out.print(i+"   "); 
			else
				System.out.print(i+"  ");
			if( ((dayIndex+ i)%7==0) || (i==daysInMonth) ){System.out.println("\n");}
		}
		return monthNames[monthIndex];
	
	} 

	public String getNextDay(GregorianCalendar calendar) {     	
		calendar.add(MyCalendar.DAY_OF_WEEK, 1);
		String nextDay= getCurrentDay(calendar);
		return nextDay;
	}
 
	public String getPreviousDay(GregorianCalendar calendar) {    
		calendar.add(MyCalendar.DAY_OF_WEEK, -1);
		String previousDay= getCurrentDay(calendar);
		return previousDay;
	}    
      
	public String getCurrentDay(GregorianCalendar calendar){
		String today  = dayNames[calendar.get(MyCalendar.DAY_OF_WEEK)-1];
		String monthShort  = monthNames[calendar.get(MyCalendar.MONTH)];
		int dayOfMonth = calendar.get(MyCalendar.DAY_OF_MONTH);
		int year = calendar.get(MyCalendar.YEAR);

		System.out.println(today+", "+monthShort+" "+ dayOfMonth+", "+year);

		String calendarString = gcToString(calendar);
//		getEventsOnThisDay(calendarString);
		
		String x="";
		return x;    	
	}


	public GregorianCalendar stringToGC(String string){
		
		Date date = null;
		GregorianCalendar cal = new GregorianCalendar();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		try{
			date = dateFormatter.parse(string);
			cal.setTime(date);
		}
		catch(ParseException e) {
			System.out.println(e);
		}
		return cal;
	}

	public String gcToString(GregorianCalendar calendar){
		
		String dayS;
		int dayI=calendar.get(MyCalendar.DAY_OF_MONTH);
		if(dayI<10){dayS="0"+dayI;}
		else{dayS=String.valueOf(dayI);}

		String monthS;
		int monthI=calendar.get(MyCalendar.MONTH)+1;
		if(monthI<10){monthS="0"+monthI;}
		else{monthS=String.valueOf(monthI);}		
		
		String yearS;
		int yearI=calendar.get(MyCalendar.YEAR);
		yearS=String.valueOf(yearI);
		
		  String date=monthS+"-"+dayS+"-"+yearS;
		  return date;
	}


}
