package io.pivotal.carOBDDatGen.car;

import java.util.Random;

public class OldCar extends StdCar {

	public OldCar() {
		super();
		Random random = new Random();
		this.setCurrentMileage(60000+random.nextInt(25000));
		this.setTankCapacity(12);
	}

}
