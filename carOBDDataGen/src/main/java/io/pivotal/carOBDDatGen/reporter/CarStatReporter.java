package io.pivotal.carOBDDatGen.reporter;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.pivotal.carOBDDatGen.car.StdCar;

public class CarStatReporter extends Thread{

	static final Logger logger = LogManager.getLogger(CarStatReporter.class);
	
	public CarStatReporter(String s) {
		super(s);
	}
	
	public void run(List<StdCar> list) {
		try {
			Random rnd = new Random();
			int a = rnd.nextInt(1+3);
			logger.info("Thread Name: %s%n", getName());
			//Currently we are printing to console
			switch(a) {
				case 1:
					//Full Report on all cars in list
					logger.info("Working on all %d cars in list %n", list.size());
					for (int i =0; i < list.size(); i ++){
						logger.info(list.get(i).toString());
					}
					break;
				case 2:	
					//Full Report on first N cars in list
					int n = rnd.nextInt(list.size());
					logger.info("Working on First %d cars in List %n", n);
					for (int i =0; i < n; i ++){
						logger.info(list.get(i).toString());
					}
					break;
				case 3:	
					//Full report on last N cars in list	
					int n1 = rnd.nextInt(list.size());
					logger.info("Working on Last %d cars in List %n", n1);
					for (int i = n1; i >= 0; i --){
						logger.info(list.get(i).toString());
					}
					break;
			}
			
		} catch (Exception e) {
			logger.debug("Exception Occured: " , e);
		}
	}
	
}