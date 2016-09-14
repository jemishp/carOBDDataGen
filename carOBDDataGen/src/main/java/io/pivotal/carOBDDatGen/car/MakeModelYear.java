package io.pivotal.carOBDDatGen.car;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class MakeModelYear {
	
	private int year;
	
	public MakeModelYear() {
		this.year=getRandomYear();
	}

	public int getRandomYear(){
		Random random = new Random();
		Calendar c = new GregorianCalendar(2000+random.nextInt(16),1,1);
		return year = c.get(Calendar.YEAR);
		 
	}
}
