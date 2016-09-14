package io.pivotal.carOBDDatGen.car;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class MakeModelYear {
	
	private int year;
	
	public MakeModelYear() {
		this.setYear(getRandomYear());
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public int setYear(int year) {
		this.year = year;
		return year;
	}

	public int getRandomYear(){
		Random random = new Random();
		Calendar c = new GregorianCalendar(2000+random.nextInt(16),1,1);
		return setYear(c.get(Calendar.YEAR));
		 
	}
}
