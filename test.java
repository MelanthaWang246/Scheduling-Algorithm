package SchedulingAlgorithm;

import java.util.Scanner;

public class test {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Algorithm algo = new Algorithm();
		
		
		// Algorithm choice
		int algoChoice = 0;
		System.out.println("Welcome to scheduling algorithm calculation system");
		
		
		//process amount
		System.out.print("First, input the process amount: ");

		int processNum = 0; 
		while(true) {
			processNum = input.nextInt();
			if(processNum > 0) {
				break;
			}
			System.out.println("Invalid input, enter again");
			continue;
		}
		String[] name = new String[processNum];
		//0: arrive time; 1: burst time; 2: finish time; 3: waiting time; 
		//4: turn around time; 5: priority; 6: process original order
		double[][] time = new double[processNum][7]; 
		for(int i=0; i<processNum; i++)
			time[i][6] = i;
		
		
		// Record the data
		System.out.println("Second, record the data");
		
		while(true) {
			System.out.println("1: record one by one in console; 5: exist system");
			System.out.print("Your choice: ");

			int recordMethod = input.nextInt();
			
			//console input data
			if(recordMethod == 1) {
				System.out.println("The last one is the priority. If FCFS or SRT, just input 0");
				for(int i=0; i<processNum; i++) {
					int a = i+1;
					System.out.print("process " + a + " name: ");
					name[i] = input.next();
					System.out.print("process " + a + " arrive time: ");
					time[i][0] = input.nextDouble();
					System.out.print("process " + a + " burst time: ");
					time[i][1] = input.nextDouble();
					System.out.print("process " + a + " priority time: ");
					time[i][5] = input.nextDouble();
				}
				break;
			}
			
			if(recordMethod == 5) {		
				algo.exit();
				break;
			}
			System.out.println("Invalid input.");
			continue;
		}
		
		//choose an algorithm and calculate the result
		while(true) {
			algo.choose();
			int choose = input.nextInt();
			//FCFS
			if(choose == 1) {
				algo.FCFS(processNum, time);
				algo.print(processNum, name, time);
			}
			//SJF
			else if(choose == 2) {
				algo.SRT(processNum, time);
				algo.print(processNum, name, time);
			}
			//Priority (P)
			else if(choose == 3) {
				algo.P(processNum, time);
				algo.print(processNum, name, time);
			}

			else if(choose == 5) {		
				algo.exit();
				break;
			}
			else {
				System.out.println("Invalid choice, input again ");
				continue;
			}
		}
		
	}

}
