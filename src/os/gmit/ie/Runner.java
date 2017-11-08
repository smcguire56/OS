package os.gmit.ie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Runner {

	static Scanner sc = new Scanner(System.in);		

	public static void main(String[] args) {
		// local variables for process
		int NumProcesses;
		String processName;
		int processBurst;
		
		// ask user number of processes
		System.out.print("Enter the amount of processes: ");
		NumProcesses = sc.nextInt();
		
		// create new array list to store them
		ArrayList<Process> processes = new ArrayList<Process>();

		
		System.out.println("=====================================================");
		System.out.println("Enter the process Name and burst time for the process");
		System.out.println("=====================================================");
		
		// ask user for process name and it's burst time for however amount of processes
		for (int i = 0; i < NumProcesses; i++) 
		{
			System.out.print("Enter process Name: ");
			processName = sc.next();	
			System.out.println("Enter process burst for " + processName + ":");
			processBurst = sc.nextInt();
			// add to the array list each process's name and burst time.
			processes.add(new Process(processName, processBurst));
		}
		
		/*
		// print what the user just inputed
		for(Process str: processes){
			System.out.println(str.toString());
		}*/
		
		//pass the processes and number of processes to the userChoice method
		userChoice(processes, NumProcesses);
		
		sc.close();
	}

	// user choice method for processing the user's option
	private static void userChoice(ArrayList<Process> processes, int NumProcesses) {
		
		// declare choice and ask user for their input
		int choice;
		System.out.println("\nPress 1 Round Robin");
		System.out.println("Press 2 First Come First Serve");
		System.out.println("Press 3 Shortest Job First");
		System.out.println("Press 4 exit");
		choice = sc.nextInt();

		// once choice is stored, do what the user wants, RR, FCFS, SJF. or EXIT.
		switch (choice) {
		case 1:
			roundRobin(processes, NumProcesses);
			break;
		case 2:
			firstComeFirstServe(processes, NumProcesses);
			break;
		case 3:
			shortestJobFirst(processes, NumProcesses);
			break;
		case 4:
			System.out.println("exit");		
			break;
		default:
			System.out.println("Try Again");
			userChoice(processes, NumProcesses);
			break;
		}
		
	}

	// round robin algorithm 
	private static void roundRobin(ArrayList<Process> processes, int numProcesses) {
		// declare all local variables and set to default value.
		int quantum;
		int waitTime = 0;
		int runTime = 0;
		int remainTime;
		float average = 0;
		boolean complete = false;
		int tempCount = 0;

		// ask user for the time quantum 
		System.out.println("Enter the time Quantum: ");
		quantum = sc.nextInt();
		
		// initialize the process by setting the remaining time to the burst time.
		for (int i = 0; i<numProcesses; i++) 
		{	
			processes.get(i).setRemainingTime(processes.get(i).getBurst());
		}
		
		// loop this until the remaining time is 0
		do {
			remainTime = 0;
			
			// loop over each process 
			for (int i = 0; i < numProcesses; i++) 
			{
				// if tempCount is 0, or isn't and remaining time is, print the process
				if (tempCount == 0 || tempCount != 0 && processes.get(i).getRemainingTime() != 0) {
					System.out.println(processes.get(i).toString() +" wait time: " + runTime);
				}
				
				// if the remaining time is greater than the quantum,
				// add the quantum to the running time and set the new remaining, wait times.
				if (processes.get(i).getRemainingTime() > quantum) {
					runTime += quantum;
					processes.get(i).setRemainingTime(processes.get(i).getRemainingTime() - quantum);				
					processes.get(i).setWaitTime(processes.get(i).getWaitTime() + 1);
				}
				// this process is finished, so add the wait time and running time.
				else if (processes.get(i).getRemainingTime() <= quantum && processes.get(i).getRemainingTime() != 0) 
				{
					waitTime += runTime - quantum * processes.get(i).getWaitTime();
					runTime += processes.get(i).getRemainingTime();
					processes.get(i).setRemainingTime(0);
				}
				
				// add up the processes remaining time and counter.
				remainTime += processes.get(i).getRemainingTime();
				tempCount++;
			}
			
			// check if the remaining time is 0, if so exit this process 
			if (remainTime == 0) 
			{
				complete = true;
			}
		} while (complete == false);
		
		// calculate the average
		average = (float)waitTime / numProcesses;
		
		// print out the total and average.
		System.out.println("\ntotal wait time: " + waitTime);
		System.out.printf("Average wait time is: %2.2f", average);
	}
	
	// FCFS algorithm
	private static void firstComeFirstServe(ArrayList<Process> processes, int numProcesses) {
		System.out.println("=====================================================");
		System.out.println("\t\tShortest Job First");
		System.out.println("=====================================================");
		// declare all local variables
		int i, totalWaitTime = 0, runTime = 0;
		float average = 0;
		
		// loop through each process
		for (i = 0; i < numProcesses; i++) {
			// just print the current process and it's wait time.
			System.out.println(processes.get(i).toString() + " wait time: " + runTime);
			// add up the runTime and total waiting time
			runTime += processes.get(i).getBurst();
			totalWaitTime += runTime;
			
			// the previous process, take away the wait time.
			if (i == (numProcesses - 1)) {
				totalWaitTime -= runTime;
			}
		}

		average = (float)totalWaitTime / numProcesses;

		System.out.printf("Average wait time is: %2.2f", average);
	}	
	
	// shortest Job
    private static void shortestJobFirst(ArrayList<Process> processes, int numProcesses) {
    	System.out.println("=====================================================");
		System.out.println("\t\tFirst Come First Serve");
		System.out.println("=====================================================");
		
		// Sort the processes and then do the same as the FCFS algorithm
		Collections.sort(processes);

		int totalWaitTime = 0, runTime = 0;
		float average = 0;
		
		// loop through each process
		for (int i = 0; i < numProcesses; i++) {
			// just print the current process and it's wait time.
			System.out.println(processes.get(i).toString() + " wait time: " + runTime);
			// add up the runTime and total waiting time
			runTime += processes.get(i).getBurst();
			totalWaitTime += runTime;
			
			// the previous process, take away the wait time.
			if (i == (numProcesses - 1)) {
				totalWaitTime -= runTime;
			}
		}

		average = (float)totalWaitTime / numProcesses;

		System.out.printf("Average wait time is: %2.2f", average);
	}


}



