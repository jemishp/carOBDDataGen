package io.pivotal.carOBDDatGen.mover;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.pivotal.carOBDDatGen.car.StdCar;


public class MoveCar extends Thread{
	
	static final Logger logger = LogManager.getLogger(MoveCar.class);
	public volatile boolean move;
/*	private List<StdCar> list;

*/
	public MoveCar(String s, boolean move) {
		super(s);
		this.move=move;
	}

	public boolean getMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}
	
 
	public void run(List<StdCar> list) {
		try {
			Random rnd = new Random();
			logger.info(String.format("Thread Name: %s", getName()));
			while (move) {
				Thread.sleep(1000);
				int a = rnd.nextInt(1+3);
				switch (a) {
				case 1:
					//Take random actions on all cars in list
					Thread.sleep(1000);
					logger.info(
							String.format("Thread Name %s :Working on all %d cars in list", getName(), list.size()));
					for (int i = 0; i < list.size(); i++) {
						int actionTaken = list.get(i).takeRandomAction();
						logger.debug(String.format("Thread Name %s :Action Taken on car %s : %d", getName(),
								list.get(i).getVin(), actionTaken));
					}
					break;
				case 2:
					//Take Random actions on first N cars in list
					Thread.sleep(1000);					
					int n = rnd.nextInt(1 + list.size());
					logger.info(String.format("Thread Name %s :Working on First %d cars in List of size %d ", getName(),
							n, list.size()));
					for (int i = 0; i < n; i++) {
						int actionTaken = list.get(i).takeRandomAction();
						logger.debug(String.format("Thread Name %s :Action Taken on car %s : %d ", getName(),
								list.get(i).getVin(), actionTaken));
					}
					break;
				case 3:
					//take random actions on last N cars in list
					Thread.sleep(1000);
					int n1 = rnd.nextInt(1 + list.size());
					if (n1 == list.size()) { n1=n1-1;}
					logger.info(String.format("Thread Name %s :Working on Last %d cars in List of size %d ", getName(),
							n1, list.size()));
					for (int i = n1; i >= 0; i--) {
						int actionTaken = list.get(i).takeRandomAction();
						logger.debug(String.format("Thread Name %s :Action Taken on car %s : %d", getName(),
								list.get(i).getVin(), actionTaken));
					}
					break;
				}
			}
			
		} catch (InterruptedException e) {
			logger.debug(String.format("Thread %s was interrupted!", this.getName()));
			//return;
			throw new RuntimeException(String.format("Thread %s was interrupted!", this.getName()), e);
		}
/*		} catch (Exception e) {
			logger.debug("Exception Occured: " , e);
		}*/
	}

	public void requestStop() {
		this.move=false;
		this.interrupt();
	}

}
