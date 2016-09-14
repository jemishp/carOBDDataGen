package io.pivotal.carOBDDatGen.car;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

public class OldCar extends StdCar {

	public OldCar() {
		super();
		Random random = new Random();
		this.setCurrentMileage(60000+random.nextInt(25000));
		this.setTankCapacity(12);
	}
	
	@Override
	public int getRandomYear(){
		Random random = new Random();
		Calendar c = new GregorianCalendar(2000+random.nextInt(9),1,1);
		return c.get(Calendar.YEAR);
		 
	}

}
