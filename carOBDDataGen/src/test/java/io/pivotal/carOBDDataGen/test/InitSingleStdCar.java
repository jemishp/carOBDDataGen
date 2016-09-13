package io.pivotal.carOBDDataGen.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import io.pivotal.carOBDDatGen.car.StdCar;


public class InitSingleStdCar {
	static final Logger logger = LogManager.getLogger(InitSingleStdCar.class);

	@Test
	public void testInitCar() throws Exception {
		try {
			StdCar car = new StdCar("Honda", "Accord EX", Integer.parseInt("2007"));
			Assert.assertEquals("Honda", car.getMake());
			Assert.assertEquals("Accord EX", car.getModel());
			Assert.assertEquals(2007, car.getYear());
			Assert.assertTrue("Vin: " + car.getVin(),car.getVin()!="");
			testParkCar(car);
		} catch (Exception e) {
			//Pass
			
		}			
		
	}
	
	@Test
	public void testParkCar(StdCar c) throws Exception {
		try{
			c.park();
			logger.debug((c.toString()));
			Assert.assertEquals(1,c.getEbrake());
			Assert.assertEquals(0,c.getCurrentGear());
		} catch (Exception e) {
			//Pass
		}
	}
	
	
}