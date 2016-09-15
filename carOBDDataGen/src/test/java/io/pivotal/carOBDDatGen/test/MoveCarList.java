package io.pivotal.carOBDDataGen.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.pivotal.carOBDDatGen.car.StdCar;
import io.pivotal.carOBDDatGen.mover.MoveCar;

public class MoveCarList {

	static final Logger logger = LogManager.getLogger(MoveCarList.class);
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
	public void canRun5MoveCarThreads() {
		try{
			for (int i=1; i<=5; i++){
				MoveCar m = new MoveCar("MoveCar-"+i);
				m.start();
				m.run(carList);
				logger.debug("Started Thread: " + m.getName()+ " and is in state " + m.getState());
			}
			
		}catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());			
		}
	}
}
