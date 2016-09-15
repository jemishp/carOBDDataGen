package io.pivotal.carOBDDatGen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.pivotal.carOBDDatGen.car.StdCar;
import io.pivotal.carOBDDatGen.mover.MoveCar;
import io.pivotal.carOBDDatGen.reporter.CarStatReporter;


public class Generator {
	static final Logger logger = LogManager.getLogger(Generator.class);
	static List<StdCar> carList = new ArrayList<StdCar>() ;
	
	public static List<StdCar> genList() {
		Random rnd = new Random();
		int a = rnd.nextInt(100);
		for (int i=0; i<a; i++) {
			StdCar car = new StdCar();
			carList.add(car);
		}
		logger.debug(String.format("Generated List of %d cars",carList.size()));
		return carList;
	}
	
	public static void main(String[] args) {
		logger.info("Starting up Generator");
		try{
			carList = genList();
			for (int i=1; i<=5; i++){
				MoveCar m = new MoveCar("MoveCar-"+i);
				m.start();
				m.run(carList);
				logger.debug(String.format("Started Thread: %s and is in state %s " , m.getName(), m.getState()));
			}
			CarStatReporter reporter = new CarStatReporter("Reporter-1");
			reporter.start();
			reporter.run(carList);
			
		}catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
		}
		
	}
}
