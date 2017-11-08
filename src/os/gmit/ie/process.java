package os.gmit.ie;

public class Process implements Comparable<Process> {
	// local variables
	private String name;
	private int burst;
	private int waitTime;
	private int remainingTime;

	// empty constructor
	public Process() {
		super();
	}
	
	// main constructor
	public Process(String name, int burst) {
		super();
		this.name = name;
		this.burst = burst;
	}
	
	// compare method
	@Override
	public int compareTo(Process arg0) {		
		int result_compare = arg0.burst;
		return this.burst - result_compare;		
	}

	// to string method
	@Override
	public String toString() {
		return "Process " + name + " burst: " + burst + " remainingTime: " + remainingTime; 
	}
	
	// Getters & Setters for variables: name, waitTime, burst, remainingTime.
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getWaitTime(){
		return waitTime;
	}
	
	public void setWaitTime(int waitTime){
		this.waitTime = waitTime;	
	}

	public int getBurst() {
		return burst;
	}
	
	public void setBurst(int burst){
			this.burst = burst;
	}
		
	
	public int getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}

}