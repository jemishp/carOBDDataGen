package io.pivotal.carOBDDataGen.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import io.pivotal.carOBDDatGen.car.StdCar;


public class InitSingleStdCar {
	static final Logger logger = LogManager.getLogger(InitSingleStdCar.class);

	@Before
	public void setup() throws Exception {
		logger.debug("Starting up test");
	}
	
	@After
	public void tearDown() throws Exception {
		logger.debug("Completed Single Car tests");
	}
	
	@Test
	public void testInitCar() throws Exception {
		try {
			StdCar car = new StdCar("Honda", "Accord EX", Integer.parseInt("2007"));
			Assert.assertEquals("Honda", car.getMake());
			Assert.assertEquals("Accord EX", car.getModel());
			Assert.assertEquals(2007, car.getYear());
			Assert.assertTrue("Vin: " + car.getVin(),car.getVin()!="");
			logger.debug((car.toString()));
		} catch (Exception e) {
			//Pass
			logger.debug("Exception Occured", e);
		}			
		
	}
	
	@Test
	public void testParkCar() throws Exception {
		try{
			StdCar car = new StdCar("Honda", "Accord EX", Integer.parseInt("2007"));
			car.park();
			logger.debug((car.toString()));
			Assert.assertEquals(1,car.getEbrake());
			Assert.assertEquals(0,car.getCurrentGear());
		} catch (Exception e) {
			//Pass
			logger.debug("Exception Occured", e);
		}
	}
	
	@Test
	public void testChangeGear() throws Exception {
		try {
			StdCar car = new StdCar("Honda", "Civic", Integer.parseInt("2010"));
			Assert.assertEquals(0,  car.getCurrentGear());
			Assert.assertEquals(0,  car.getCurrentRpm());
			car.incrementCurrentGear();
			Assert.assertEquals(1,  car.getCurrentGear());
			car.incrementCurrentGear();
			Assert.assertEquals(2,  car.getCurrentGear());
			car.incrementCurrentGear();
			Assert.assertEquals(3,  car.getCurrentGear());
			car.incrementCurrentGear();
			Assert.assertEquals(4,  car.getCurrentGear());
			car.incrementCurrentGear();
			Assert.assertEquals(5,  car.getCurrentGear());
			//Checking that it stays at 5
			car.incrementCurrentGear();
			Assert.assertEquals(5,  car.getCurrentGear());
			//Check case for reverse
			car.putInReverse();
			Assert.assertEquals(10,  car.getCurrentGear());
		} catch (Exception e) {
		//pass
		logger.debug("Exception oeccured", e);
		}
	}	
	
	@Test
	public void testChangeSpeed() throws Exception {
		//changing rpms is tested here so no need for a seperate test
		try {
			StdCar car = new StdCar("Honda", "Civic EX", Integer.parseInt("2010"));
			Assert.assertEquals(0,  car.getCurrentMileage());
			Assert.assertEquals(0, car.getCurrentSpeed());
			//Assumption is when we accelerate we change speed , rpm and mileage
			car.accelerate();
			Assert.assertEquals(1, car.getCurrentMileage());
			Assert.assertEquals(1,  car.getCurrentSpeed());
			Assert.assertEquals(1, car.getCurrentRpm());
			car.setCurrentSpeed(35);
			car.setCurrentRpm(4);
			car.accelerate();
			car.accelerate();
			// testing Decelerate test 
			//Making Sure Mileage still goes up
			Assert.assertEquals(3,  car.getCurrentMileage());
			car.decelerate();
			Assert.assertEquals(37,  car.getCurrentSpeed());
			Assert.assertEquals(5, car.getCurrentRpm());
			Assert.assertEquals(4,  car.getCurrentMileage());
			
			
		}catch (Exception e) {
			//pass
			logger.debug("Exception oeccured", e);
			}
		}
	@Ignore
	@Test
	public void testChangeRpm() throws Exception {
		try {
			StdCar car = new StdCar("Honda", "Civic", Integer.parseInt("2010"));
			Assert.assertEquals(0,  car.getCurrentGear());
			Assert.assertEquals(0,  car.getCurrentMileage());
			Assert.assertEquals(0,  car.getCurrentRpm());
			Assert.assertEquals(0, car.getCurrentSpeed());
		} catch (Exception e) {
			//pass
			logger.debug("Exception oeccured", e);
			}
		}
	
	@Ignore
	@Test
	public void testChangeMileage() throws Exception {
		try {
			StdCar car = new StdCar("Honda", "Civic", Integer.parseInt("2010"));
			Assert.assertEquals(0,  car.getCurrentGear());
			Assert.assertEquals(0,  car.getCurrentMileage());
			Assert.assertEquals(0,  car.getCurrentRpm());
			Assert.assertEquals(0, car.getCurrentSpeed());
		} catch (Exception e) {
			//pass
			logger.debug("Exception oeccured", e);
			}
		} 
	@Ignore
	@Test
	public void testChangeFuel() throws Exception {
		try {
			StdCar car = new StdCar("Honda", "Civic", Integer.parseInt("2010"));
			Assert.assertEquals(0,  car.getCurrentGear());
			Assert.assertEquals(0,  car.getCurrentMileage());
			Assert.assertEquals(0,  car.getCurrentRpm());
			Assert.assertEquals(0, car.getCurrentSpeed());
		} catch (Exception e) {
			//pass
			logger.debug("Exception oeccured", e);
			}
		}
}