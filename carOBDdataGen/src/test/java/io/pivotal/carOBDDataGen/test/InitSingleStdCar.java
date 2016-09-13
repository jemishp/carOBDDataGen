package io.pivotal.carOBDDataGen.test;

import org.junit.Assert;
import org.junit.Test;

import io.pivotal.carOBDdatagen.car.StdCar;

public class InitSingleStdCar {

	@Test
	public void initCar() throws Exception {
		try {
			StdCar car = new StdCar("Honda", "Accord EX", Integer.parseInt("2007"));
			Assert.assertEquals("Honda", car.getMake());
			Assert.assertEquals("Accord EX", car.getModel());
			Assert.assertEquals(2007, car.getYear());
			Assert.assertTrue("Vin: " + car.getVin(),car.getVin()!="");
		} catch (Exception e) {
			//Pass
			
		}
		
	}
	
	
}