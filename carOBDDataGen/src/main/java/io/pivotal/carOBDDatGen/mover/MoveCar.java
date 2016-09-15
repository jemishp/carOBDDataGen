package io.pivotal.carOBDDatGen.mover;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.pivotal.carOBDDatGen.car.StdCar;


public class MoveCar extends Thread{
	
	static final Logger logger = LogManager.getLogger(MoveCar.class);
/*	private List<StdCar> list;

*/
	public MoveCar(String s) {
		super(s);
	}

/*	public List<StdCar> getList() {
		return list;
	}

	public void setList(List<StdCar> list) {
		this.list = list;
	}*/
	
	public void run(List<StdCar> list) {
		try {
			Random rnd = new Random();
			int a = rnd.nextInt(1+3);
			logger.info(String.format("Thread Name: %s", getName()));
			
			switch(a) {
				case 1:
					//Take random actions on all cars in list
					logger.info(String.format("Working on all %d cars in list", list.size()));
					for (int i =0; i < list.size(); i ++){
						int actionTaken=list.get(i).takeRandomAction();
						logger.debug(String.format("Action Taken on car %s : %d", list.get(i).getVin(), actionTaken));
					}
					break;
				case 2:	
					//Take Random actions on first N cars in list
					int n = rnd.nextInt(list.size());
					logger.info(String.format("Working on First %d cars in List ", n));
					for (int i =0; i < n; i ++){
						int actionTaken=list.get(i).takeRandomAction();
						logger.debug(String.format("Action Taken on car %s : %d ", list.get(i).getVin(), actionTaken));
					}
					break;
				case 3:	
					//take random actions on last N cars in list
					
					int n1 = rnd.nextInt(list.size());
					logger.info(String.format("Working on Last %d cars in List ", n1));
					for (int i = n1; i >= 0; i --){
						int actionTaken=list.get(i).takeRandomAction();
						logger.debug(String.format("Action Taken on car %s : %d", list.get(i).getVin(), actionTaken));
					}
					break;
			}
			
		} catch (Exception e) {
			logger.debug("Exception Occured: " , e);
		}
	}
}
