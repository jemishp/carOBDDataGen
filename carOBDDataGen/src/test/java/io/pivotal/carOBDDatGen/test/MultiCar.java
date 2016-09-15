package io.pivotal.carOBDDatGen.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.pivotal.carOBDDatGen.car.OldCar;
import io.pivotal.carOBDDatGen.car.StdCar;

public class MultiCar {
	static final Logger logger = LogManager.getLogger(MultiCar.class);
	List<StdCar> carList = new ArrayList<StdCar>() ;
	
	@Before
	public void setup() throws Exception {
		Random rnd = new Random();
		int a = rnd.nextInt(100);
		for (int i=0; i<a; i++) {
			StdCar car = new StdCar();
			carList.add(car);
		}
	
	}
	
	@After
	public void tearDown() throws Exception {
		carList.clear();
	}
	
	
	@Test
	public void canAccessingCarList() throws Exception {
		try {
			logger.debug("list Size is: " + carList.size());
			
		} catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());			
		}
	}
	
	@Test
	public void canInitCars() throws Exception {
		try {
			//List<StdCar> carList = new ArrayList<StdCar>() ;
			Random rnd = new Random();
			int a = rnd.nextInt(100);
			int oldSize = carList.size();
			logger.debug("Will generate " + a + " StdCar Objects");
			for (int i=0; i<a; i++) {
				StdCar car = new StdCar();
				carList.add(car);
				logger.debug((car.toString()));
			}
			Assert.assertEquals(a+oldSize, carList.size());
			int c = rnd.nextInt(200);
			logger.debug("Will generate " + a + " OldCar Objects");
			for (int b=0; b<c; b++) {
				OldCar oCar = new OldCar();
				carList.add(oCar);
				logger.debug((oCar.toString()));

			}
			Assert.assertEquals(a+oldSize+c, carList.size());

		} catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());
		}			
		
	}
	
	@Test
	public void canTakeActionOnMultipleCars() throws Exception {
		try {
			Random rnd = new Random();
			if (carList.size()==0) {
				StdCar car = new StdCar();
				carList.add(car);
			}
			int a = rnd.nextInt(carList.size());
			for (int i=0; i < a; i++) {
				logger.debug(i + " " + carList.get(i).getMake());
				logger.debug(i + " " + carList.get(i).getModel());
				logger.debug(i + " " + carList.get(i).getVin());
				int curSpeed = carList.get(i).getCurrentSpeed();
				carList.get(i).accelerate();
				Assert.assertEquals(curSpeed+1,carList.get(i).getCurrentSpeed());
			}
			
		} catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());			
		}
	}
	
	@Test
	public void canTakeMultipleActionsOnMultipleCars() throws Exception {
		try {
			Random rnd = new Random();
			int a = rnd.nextInt(carList.size());
			int randAction = rnd.nextInt(5);
			for (int i=0; i < a; i++) {
				logger.debug(i + " " + carList.get(i).getMake());
				logger.debug(i + " " + carList.get(i).getModel());
				logger.debug(i + " " + carList.get(i).getVin());
				int curSpeed = carList.get(i).getCurrentSpeed();
				switch (randAction) {
				case 0:
					//Park
					Assert.assertEquals(0,carList.get(i).getEbrake());
					carList.get(i).park();
					Assert.assertEquals(1,carList.get(i).getEbrake());
					break;
				case 1:
					//Accelerate
					Assert.assertEquals(curSpeed,carList.get(i).getCurrentSpeed());
					carList.get(i).accelerate();
					Assert.assertEquals(curSpeed+1,carList.get(i).getCurrentSpeed());
					break;
				case 2:
					//Decelerate 
					Assert.assertEquals(curSpeed,carList.get(i).getCurrentSpeed());
					if (curSpeed>0) {
						carList.get(i).decelerate();
						Assert.assertEquals(curSpeed-1,carList.get(i).getCurrentSpeed());
					}
					break;
				case 3:
					//Change Gears
					int curGear = carList.get(i).getCurrentGear();
					carList.get(i).incrementCurrentGear();
					Assert.assertEquals(curGear+1,carList.get(i).getCurrentGear());
					break;
				case 4:
					//Put in Reverse
					Assert.assertNotEquals(10, carList.get(i).getCurrentGear());
					carList.get(i).putInReverse();
					Assert.assertEquals(10, carList.get(i).getCurrentGear());
					break;
				}
			}
			
		} catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());			
		}
	}
	
	@Test
	public void canTakeRandomActionsOnMultipleCarsRandomly() throws Exception {
		try {
			Random rnd = new Random();
			int a = rnd.nextInt(carList.size());
			for (int i=0; i < a; i++) {
				int actionTaken = carList.get(i).takeRandomAction();
				Assert.assertTrue("Took an incorrect Action " + actionTaken, actionTaken >=0 && actionTaken <=9 );
			}
			
		} catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());			
		}
	}

}
