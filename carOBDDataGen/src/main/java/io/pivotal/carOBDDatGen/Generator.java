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

import io.pivotal.carOBDDatGen.car.OldCar;
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
	static String uri;
	static List<MoveCar> m = new ArrayList<MoveCar>();
	static List<CarStatReporter> r = new ArrayList<CarStatReporter>();
	
	public static List<StdCar> genList() {
		Random rnd = new Random();
		int a = rnd.nextInt(1000);
		for (int i=0; i<a; i++) {
			StdCar car = new StdCar();
			carList.add(car);
		}
		for (int i=0; i<(a*100/25); i++) {
			OldCar ocar = new OldCar();
			carList.add(ocar);
		}
		logger.debug(String.format("Generated List of %d cars",carList.size()));
		return carList;
	}
	
	public static void runGen() {
		logger.info("Starting up Generator");
		m.clear();
		r.clear();
		try{
			uri = System.getenv("POST-URI");
			if (uri == null) {
				uri = "http://localhost:9000";
				logger.warn(String.format("No POST-URI Specified. Using %s instead", uri));
			}
			logger.debug(String.format("Received POST-URI: %s ", uri));
			carList = genList();
			
			
			for (int i=0; i<5; i++){
				m.add(new MoveCar("MoveCar-"+i, true));
				m.get(i).start();
				logger.debug(String.format("Started Thread: %s and is in state %s ", m.get(i).getName(), m.get(i).getState()));				
			}
			for (int i=0; i <5; i ++) {
				r.add(new CarStatReporter("Reporter-"+i, uri, true));
				r.get(i).start();
				logger.debug(String.format("Started Thread: %s and is in state %s ", r.get(i).getName(), r.get(i).getState()));
			}
			logger.debug("Start Moving Cars in List");
			for (int a = 0; a < m.size(); a++) {
				final int i=a;
				Thread t = new Thread(new Runnable() {
					@Override
					public void run() {
							//m.get(i).start();
							m.get(i).run(carList);
							logger.info(String.format("Stopped: %s move=%s and is in state %s " , m.get(i).getName(), m.get(i).getMove(), m.get(i).getState()));
					}
				});
				t.start();
			}
			for (int z=0; z< r.size();z++) {
				final int b=z;
				Thread reporter = new Thread(new Runnable() {
					@Override
					public void run() {
						r.get(b).run(carList);
						logger.info(String.format("Stopped: %s report=%s and is in state %s " , r.get(b).getName(), r.get(b).getReport(), r.get(b).getState()));
					}
				});
				reporter.start();				
			}


			
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
	
	@RequestMapping(value="/startgen")
	public String gen() {
		String test = "test";
		logger.debug(String.format("gen Called %s", test));
		runGen();
		return "Generator Completed";
	}
	
	@RequestMapping(value="/stopgen")
	public String stopGen() {
		logger.debug("stopGen Called ");
		for (int a=0; a<m.size(); a++) {
			m.get(a).requestStop();
			//m.get(a).move=false;
			logger.debug(String.format("Interrupted: %s " , m.get(a).getName()));
		}
		for (int b=0;b<r.size();b++) {
			r.get(b).requestStop();
			logger.debug(String.format("Interrupted:  %s ", r.get(b).getName()));
		}

		return "Generator Stopped";
	}
}
