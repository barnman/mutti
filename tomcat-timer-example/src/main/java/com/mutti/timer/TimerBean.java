package com.mutti.timer;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.mutti.google.GoogleCalendarClient;

public class TimerBean {
	
	Timer timer;
	
	private String accountId = "tssmutti@gmail.com";
	
	private File privateKeyFile = new File("C:/temp/1d9b6d3c60743136aea0d4d6a6e39d743c168da7-privatekey.p12");
	
	public TimerBean(int seconds){
		timer = new Timer();
		//start in seconds and repeat each 5 seconds
		timer.schedule(new GoogleCalendarTask(), seconds*1000, 5000);
	}
	
	class GoogleCalendarTask extends TimerTask {
		public void run() {
			GoogleCalendarClient calendarClient = new GoogleCalendarClient(accountId, privateKeyFile);
			System.out.println(calendarClient.getCalendar());
				try {
					Events events = calendarClient.getCalendar().events().list("tss mutti").execute();
					  for (Event event : events.getItems()) {
						    System.out.println(event.getSummary());
				    }

					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		}
	}

}
