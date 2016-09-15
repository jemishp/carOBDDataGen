package io.pivotal.carOBDDataGen.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import io.pivotal.carOBDDatGen.car.OldCar;

public class InitSingleOldCar {
	static final Logger logger = LogManager.getLogger(InitSingleOldCar.class); 

	
	@Before
	public void setup() throws Exception {
		//logger.debug("Starting up test");
	}
	
	@After
	public void tearDown() throws Exception {
		//logger.debug("Completed Single Car tests");
	}
	
	@Test
	public void canInitOldCar() throws Exception {
		try {
			OldCar oCar = new OldCar();
			logger.debug(oCar.toString());
			Assert.assertFalse("Not an old Car based on its Mileage!: " + oCar.getCurrentMileage(), oCar.getCurrentMileage()<=60000);
			Assert.assertEquals(12, oCar.getTankCapacity());
			Assert.assertTrue("Not an old Car based on its Years!: " + oCar.getYear(), 2000 <= oCar.getYear() && oCar.getYear() <= 2009);
		} catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());
		}

	}
}
