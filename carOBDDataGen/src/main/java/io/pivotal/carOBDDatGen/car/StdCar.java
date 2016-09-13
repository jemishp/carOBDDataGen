package io.pivotal.carOBDDatGen.car;

import java.util.Random;

import com.google.gson.Gson;

public class StdCar {
	
	private int amountOfFuel;
	private int tankCapacity;
	private int currentMileage;
    /* We use an int to track gears that a car is in
     * 0 = Park
     * 1 - 5 Different Gears 
     * 10 = Reverse
     */
	private int currentGear;
	private int currentSpeed;
	private int currentRpm;
	/*
	 * 0 = no eBrake applied
	 * 1 = eBrake is on
	 */
	private int eBrake;
	private float engTemp;
	private float insideTemp;
	private float outsideTemp;
	private float frontLeftPsi;
	private float frontRightPsi;
	private float rearLeftPsi;
	private float rearRightPsi;
	private String make;
	private String model;
	private String vin;
	private int year;
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public StdCar (String make, String model , int year) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.vin = randomString(17);
	}

	public int getAmountOfFuel() {
		return amountOfFuel;
	}

	public void setAmountOfFuel(int amountOfFuel) {
		this.amountOfFuel = amountOfFuel;
	}

	public int getTankCapacity() {
		return tankCapacity;
	}

	public void setTankCapacity(int tankCapacity) {
		this.tankCapacity = tankCapacity;
	}

	public int getCurrentMileage() {
		return currentMileage;
	}

	public void setCurrentMileage(int currentMileage) {
		this.currentMileage = currentMileage;
	}

	public int getCurrentGear() {
		return currentGear;
	}

	public void setCurrentGear(int currentGear) {
		this.currentGear = currentGear;
	}

	public int getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public int getCurrentRpm() {
		return currentRpm;
	}

	public void setCurrentRpm(int currentRpm) {
		this.currentRpm = currentRpm;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public int getEbrake() {
		return eBrake;
	}

	public void setEbrake(int eBrake) {
		this.eBrake = eBrake;
	}

	public float getEngTemp() {
		return engTemp;
	}

	public void setEngTemp(float engTemp) {
		this.engTemp = engTemp;
	}

	public float getInsideTemp() {
		return insideTemp;
	}

	public void setInsideTemp(float insideTemp) {
		this.insideTemp = insideTemp;
	}

	public float getOutsideTemp() {
		return outsideTemp;
	}

	public void setOutsideTemp(float outsideTemp) {
		this.outsideTemp = outsideTemp;
	}

	public float getFrontLeftPsi() {
		return frontLeftPsi;
	}

	public void setFrontLeftPsi(float frontLeftPsi) {
		this.frontLeftPsi = frontLeftPsi;
	}

	public float getFrontRightPsi() {
		return frontRightPsi;
	}

	public void setFrontRightPsi(float frontRightPsi) {
		this.frontRightPsi = frontRightPsi;
	}

	public float getRearLeftPsi() {
		return rearLeftPsi;
	}

	public void setRearLeftPsi(float rearLeftPsi) {
		this.rearLeftPsi = rearLeftPsi;
	}

	public float getRearRightPsi() {
		return rearRightPsi;
	}

	public void setRearRightPsi(float rearRightPsi) {
		this.rearRightPsi = rearRightPsi;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}


	public void incrementFuelTank() {
		if (this.amountOfFuel < 15 )
			this.amountOfFuel++;
	}

	public void decrementFuelTank() {
		if (this.amountOfFuel > 0)
			this.amountOfFuel--;
	}
	
	public void incrementCurrentMileage() {
		if (this.currentMileage < 999999 )
			this.currentMileage++;
	}
	
	public void decrementCurrentMileage() {
		if ( this.currentMileage > 25 )
			this.currentMileage--;
	}
	

	public void incrementCurrentGear() {
		if ( this.currentGear == 0)
			this.currentGear++;
		else if ( this.currentGear < 5 )
			this.currentGear++;
	}
	
	public void decrementCurrentGear() {
		if ( this.currentGear > 0)
			this.currentGear--;	
	}
	
	public void putInReverse() {
		this.currentGear = 10;
	}
	
	public void getOutOfReverse() {
		this.currentGear = 1;
		
	}

	public void park() {
		this.currentGear = 0;
		this.eBrake = 1;
	}
	
	public void accelerate() {
		if ( this.currentSpeed < 135  && this.currentRpm < 8 ) {
			this.currentSpeed++;
		    this.currentRpm++;
		    this.currentMileage++;
		} else if ( this.currentSpeed == 135 || this.currentRpm == 8)
			this.currentMileage++;
	}
	
	public void decelerate(){
		if (currentSpeed > 0 && currentRpm > 1 )
			currentSpeed--;
			currentRpm--;
			currentMileage++;
	}
	
	public String randomString( int len ) 
	{
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		return sb.toString();
	}
	
	public String toString() {
		Gson gson = new Gson();
		String result = gson.toJson(this);
		return result;
	}
	
}
