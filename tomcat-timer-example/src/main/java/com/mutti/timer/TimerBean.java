package com.mutti.timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerBean {
	
	Timer timer;
	
	public TimerBean(int seconds){
		timer = new Timer();
		//start in seconds and repeat each 5 seconds
		timer.schedule(new GoogleCalendarTask(), seconds*1000, 5000);
	}
	
	class GoogleCalendarTask extends TimerTask {
		public void run() {
			System.out.println("Retrieving data from google calendar!");
			//timer.cancel();	
		}
	}

}
