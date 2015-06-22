package ar.edu.unq.desapp.groupb.model.test;

import junit.framework.TestCase;

import org.joda.time.DateTime;

import ar.edu.unq.desapp.groupb.model.Event;

public class EventTest extends TestCase{
	
	public void testStringToDateTime(){
		DateTime dateTime = new DateTime(2015, 06, 21, 0, 0);
    	
		Event event = new Event();
		
		DateTime generateDateTime = event.stringToDateTime("21/06/2015");
		
		assertTrue(dateTime.equals(generateDateTime));
	}
}
