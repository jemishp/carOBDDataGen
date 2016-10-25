package io.pivotal.carOBDDatGen.reporter;

import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.pivotal.carOBDDatGen.car.StdCar;

public class CarStatReporter extends Thread{

	static final Logger logger = LogManager.getLogger(CarStatReporter.class);
	static String uri;
	public boolean report;
	
	public CarStatReporter(String s, String uri, boolean report) {
		super(s);
		this.uri=uri;
		this.setReport(report);
	}
	
	public boolean getReport() {
		return report;
	}

	public void setReport(boolean report) {
		this.report = report;
	}

	public void sendReport(StdCar c, String uri) {
		try {
			Thread.sleep(1000);
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost request = new HttpPost(uri);
			StringEntity strTemp = new StringEntity(c.toString());
			request.setEntity(strTemp);
			request.setHeader("Content-Type", "application/json");
			logger.info(String.format("Sending: %s", strTemp));
			HttpResponse response = httpClient.execute(request);
			logger.info(String.format("Response: %s", response));			
		} catch (HttpHostConnectException e) {
			logger.debug(String.format("Could not connect to SCDF Host:  %s",uri) ,e );
			throw new RuntimeException(String.format("Could not connect to SCDF Host:  %s",uri),e);
		} catch (Exception e) {
			logger.debug("Exception Occured: " , e);
		}
	}
	
	public void run(List<StdCar> list) {
		try {
			Random rnd = new Random();
			logger.info(String.format("Thread Name: %s ", getName()));
			while (report) {
				Thread.sleep(1000);
				int a = rnd.nextInt(1+3);
				switch (a) {
				case 1:
					//Full Report on all cars in list
					Thread.sleep(1000);
					logger.info(
							String.format("Thread Name %s :Working on all %d cars in list ", getName(), list.size()));
					for (int i = 0; i < list.size(); i++) {
						sendReport(list.get(i), uri);
					}
					break;
				case 2:
					//Full Report on first N cars in list
					Thread.sleep(1000);
					int n = rnd.nextInt(1 + list.size());
					logger.info(String.format("Thread Name %s Working on First %d cars in List of size %d ", getName(),
							n, list.size()));
					for (int i = 0; i < n; i++) {
						sendReport(list.get(i), uri);
					}
					break;
				case 3:
					//Full report on last N cars in list
					Thread.sleep(1000);
					int n1 = rnd.nextInt(1 + list.size());
					if (n1 == list.size()) { n1=n1-1;}
					logger.info(String.format("Thread Name %s :Working on Last %d cars in List of size %d ", getName(),
							n1, list.size()));
					for (int i = n1; i >= 0; i--) {
						sendReport(list.get(i), uri);
					}
					break;
				}
			}

		} catch (InterruptedException e) {
			logger.debug(String.format("Thread %s was interrupted!", this.getName()));
			//return;
			throw new RuntimeException(String.format("Thread %s was interrupted!", this.getName()), e);
			
		}catch (Exception e) {
			logger.debug("Exception Occured: " , e);
		}
	}
	
	public void requestStop() {
		this.report=false;
		this.interrupt();
	}
}
