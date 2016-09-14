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
		logger.debug("Starting up test");
	}
	
	@After
	public void tearDown() throws Exception {
		logger.debug("Completed Single Car tests");
	}
	
	@Test
	public void testInitOldCar() throws Exception {
		OldCar oCar = new OldCar("Acura", "MDX");
		Assert.assertFalse("Not an old Car!", oCar.getCurrentMileage()<=60000);
		Assert.assertEquals(12, oCar.getTankCapacity());
		logger.debug(oCar.toString());

	}
}
