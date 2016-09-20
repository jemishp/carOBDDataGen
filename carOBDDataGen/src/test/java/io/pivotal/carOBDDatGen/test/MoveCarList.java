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
	public void canStartStop5MoveCarThreads() {
		try{
			final List<MoveCar> m = new ArrayList<MoveCar>();
			for (int i=0; i<5; i++){
				m.add(new MoveCar("MoveCar-"+i, true));
				m.get(i).start();
				//m.run(carList);
				logger.debug("Started Thread: " + m.get(i).getName()+ " and is in state " + m.get(i).getState());				
			}
			logger.debug("Start Moving Cars in List");
			for (int a = 0; a < m.size(); a++) {
				final int i=a;
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
							//m.get(i).start();
							m.get(i).run(carList);
							logger.debug("Started: " + m.get(i).getName() + " move =" + m.get(i).getMove());
							System.out.println("Started: " + m.get(i).getName());
						

					}
				});
				t.start();
			}
			Thread.sleep(20000);
			logger.debug("Thread " + Thread.currentThread().getName() + " Sleeping");
			logger.debug("Interrupting Now");
			for (int a=0; a<5; a++) {
				m.get(a).move=false;
				logger.debug("Interrupted: " + m.get(a).getName());
			}
			
		}catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
			Assert.fail("Exception Occured: " +  e.toString());			
		}
	}
}
