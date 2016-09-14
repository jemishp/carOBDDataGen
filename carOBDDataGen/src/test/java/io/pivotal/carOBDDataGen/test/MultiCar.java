package io.pivotal.carOBDDataGen.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import io.pivotal.carOBDDatGen.car.OldCar;
import io.pivotal.carOBDDatGen.car.StdCar;

public class MultiCar {
	static final Logger logger = LogManager.getLogger(InitSingleStdCar.class);
	List<StdCar> carList = new ArrayList<StdCar>() ;
	
	
	@Test
	public void testAccessingCarList() throws Exception {
		try {
			logger.debug("list Size is: " + carList.size());
			
		} catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());			
		}
	}
	
	@Test
	public void testInitCars() throws Exception {
		try {
			//List<StdCar> carList = new ArrayList<StdCar>() ;
			Random rnd = new Random();
			int a = rnd.nextInt(100);
			logger.debug("Will generate " + a + " StdCar Objects");
			for (int i=0; i<a; i++) {
				StdCar car = new StdCar();
				carList.add(car);
				logger.debug((car.toString()));
			}
			Assert.assertEquals(a, carList.size());
			int c = rnd.nextInt(200);
			logger.debug("Will generate " + a + " OldCar Objects");
			for (int b=0; b<c; b++) {
				OldCar oCar = new OldCar();
				carList.add(oCar);
				logger.debug((oCar.toString()));

			}
			Assert.assertEquals(a+c, carList.size());

		} catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());
		}			
		
	}
	


}
