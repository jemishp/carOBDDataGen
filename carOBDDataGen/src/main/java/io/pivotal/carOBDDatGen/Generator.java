package io.pivotal.carOBDDatGen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.carOBDDatGen.car.StdCar;
import io.pivotal.carOBDDatGen.mover.MoveCar;
import io.pivotal.carOBDDatGen.reporter.CarStatReporter;


@RestController
@ComponentScan
@RequestMapping(value="/")
public class Generator {
	@Autowired
	ServletContext context;
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
	
	public static void runGen() {
		logger.info("Starting up Generator");
		try{
			carList = genList();
			for (int i=1; i<=5; i++){
				MoveCar m = new MoveCar("MoveCar-"+i);
				m.start();
				m.run(carList);
				logger.debug(String.format("Started Thread: %s and is in state %s " , m.getName(), m.getState()));
			}
			CarStatReporter reporter = new CarStatReporter("Reporter-1","https://10.68.92.100:8080");
			reporter.start();
			reporter.run(carList);
			
		}catch (Exception e) {
			//fails
			logger.debug("Exception Occured: " , e );
		}
		
	}
	
	@RequestMapping(value="/greeting")
	public String greeting() {
		logger.debug(String.format("%s is Alive", getClass().getName()));
		return "Generator is Alive";
	}
	
	@RequestMapping(value="/gen")
	public String gen() {
		String test = "test";
		logger.debug(String.format("gen Called %s", test));
		runGen();
		return "Generator Completed";
	}
}
