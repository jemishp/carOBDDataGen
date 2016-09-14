package io.pivotal.carOBDDatGen.car;

import java.util.Random;

public class OldCar extends StdCar {

	public OldCar(String make, String model, int year) {
		super(make, model, year);
		Random random = new Random();
		this.setCurrentMileage(60000+random.nextInt(25000));
		this.setTankCapacity(12);
	}

}
