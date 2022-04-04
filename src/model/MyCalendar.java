package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TreeMap;
import java.util.TreeSet;

public class MyCalendar extends GregorianCalendar {
	//dd/MM/yyyy -> {Event1, Event2[name, date, time]}
	private TreeMap<GregorianCalendar, TreeSet<Event>> calendarEventsMap = new TreeMap<>();
	public static GregorianCalendar gCalendar = new GregorianCalendar();
	static final String[] monthNames = {"January","February","March","April","May","June","July","August",
			"September","October","November","December"};
	static final String[] dayNames = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

	
	public String getNextMonth(GregorianCalendar calendar) {
		calendar.add(MyCalendar.MONTH, 1);
		String nextMonth= printMonth(calendar);
		return nextMonth;
	}

 
	public String getPreviousMonth(GregorianCalendar calendar) {
		calendar.add(MyCalendar.MONTH, -1);
		String previousMonth= printMonth(calendar);
		return previousMonth;
	}	

     
	public String printMonth(GregorianCalendar calendar){

		int monthIndex= calendar.get(MyCalendar.MONTH);
		int yearValue= calendar.get(MyCalendar.YEAR);

		GregorianCalendar  calMonthStart = new GregorianCalendar(yearValue, monthIndex, 1); 
		int dayIndex = calMonthStart.get(MyCalendar.DAY_OF_WEEK)-1;
		//String d = dayNames[dayIndex];
		int daysInMonth=calendar.getActualMaximum(MyCalendar.DAY_OF_MONTH);
		
		System.out.println("\n"+monthNames[monthIndex]+"  "+ yearValue);
		System.out.println("\nSu  Mo  Tu  We  Th  Fr  Sa");   

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
			GregorianCalendar key=new GregorianCalendar();
			key= stringToGC(dateOfi);
			
			if(i == calendar.get(MyCalendar.DAY_OF_MONTH) &&  gCalendar.get(MyCalendar.MONTH)==calendar.get(MyCalendar.MONTH))
				System.out.print("["+i+"] "); 
			
			
			else if(calendarEventsMap.containsKey(key))
				System.out.print("{"+i+"} "); 
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
		String nextDay= printDay(calendar);
		return nextDay;
	}
 
	public String getPreviousDay(GregorianCalendar calendar) {    
		calendar.add(MyCalendar.DAY_OF_WEEK, -1);
		String previousDay= printDay(calendar);
		return previousDay;
	}    
      
	public String printDay(GregorianCalendar calendar){
		String today  = dayNames[calendar.get(MyCalendar.DAY_OF_WEEK)-1];
		String monthShort  = monthNames[calendar.get(MyCalendar.MONTH)];
		int dayOfMonth = calendar.get(MyCalendar.DAY_OF_MONTH);
		int year = calendar.get(MyCalendar.YEAR);

		System.out.println(today+", "+monthShort+" "+ dayOfMonth+", "+year);

		String calendarString = gcToString(calendar);
		getEventsOnThisDay(calendarString);
		
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
		
		  String x=monthS+"/"+dayS+"/"+yearS;
		  return x;
	}

	public String getEventsOnThisDay(String date){ 
		String s = "";

		String title="";
		LocalTime startT,endT;
		Date dummyDate = null;
		GregorianCalendar c = new GregorianCalendar();
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

		try{
			dummyDate = dateFormatter.parse(date);
			c.setTime(dummyDate);
		}
		catch(ParseException e) {
			System.out.println(e.getMessage());
		}
		TreeSet<Event> events = new TreeSet<>();
		
		if (calendarEventsMap.containsKey(c))
		{
			events = calendarEventsMap.get(c);
			if (!events.equals(null))
				for (Event e : events){
					//	s += e.toString() + "\n";
					title=e.getName();
					TimeSlot eventTiming = e.getTimeSlot();
					startT=eventTiming.getStartTime();
					endT=eventTiming.getEndTime();
					System.out.println(title+" "+startT+" - "+endT+"\n");
				}
		}
		else 
			System.out.println("No Events Scheduled");

		return s;
	}

	public String printAllEvents(){
		String s = "";
		String nameOfDay="", monthName="", startT="",endT="",title="";
		int year;
		int dayOfMonth;
		for(GregorianCalendar key : calendarEventsMap.keySet()){

			for (Event event : calendarEventsMap.get(key)){
				year=key.get(MyCalendar.YEAR);
				nameOfDay=dayNames[key.get(MyCalendar.DAY_OF_WEEK)-1];
				monthName=monthNames[key.get(MyCalendar.MONTH)];
				dayOfMonth=key.get(MyCalendar.DAY_OF_MONTH);
				TimeSlot eventTiming = event.getTimeSlot();
				startT = eventTiming.getStartTime().toString();
				endT = eventTiming.getEndTime().toString(); 
				title = event.getName();
				System.out.println(year+" "+nameOfDay+" "+monthName+" "+dayOfMonth+" "+startT+" - "+endT+" "+title);
			}
		}
		return s;
	}
	
	public void deleteAll(){
		calendarEventsMap.clear();;
	}
	
	public void deleteSelected(String dateStringToDelete){ 

		Date dummyDate = null;
		GregorianCalendar key = new GregorianCalendar() ;

		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		try{
			dummyDate= dateFormatter.parse(dateStringToDelete);
			key.setTime(dummyDate);
		}
		catch(ParseException e) {
			System.out.println(e.getMessage());
		}

		calendarEventsMap.remove(key);
	}
	
	public void createEvent(String name,String inputDate,String startTime,String endTime){
		LocalTime start = LocalTime.parse(startTime);
		LocalTime end  = LocalTime.parse(endTime);
		Date date;
		try {
			date = new SimpleDateFormat("dd-MM-yyyy").parse(inputDate);
			Event newEvent = new Event(name, date, new TimeSlot(start, end));  
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(newEvent.getDate());
			

//			if(!checkEventCollision(newEvent)){

				if(calendarEventsMap.containsKey(c)){
					calendarEventsMap.get(c).add(newEvent);  
				} else{
					TreeSet<Event> events = new TreeSet<>(); 
					events.add(newEvent);
					calendarEventsMap.put(c, events);
				}

//			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	 
	}

	public  boolean checkEventCollision(Event event){  
		boolean collision=false;
		for(GregorianCalendar key : calendarEventsMap.keySet()){
			
			if(key.getTime().equals(event.getDate()))
			{
				for(Event e : calendarEventsMap.get(key)){
					TimeSlot ts = e.getTimeSlot();
					if(ts.getStartTime().equals(event.getTimeSlot().getStartTime()) )
					{
						collision=true;
					}
				}
			}
		}
		return collision;
	}
}
