package SchedulingAlgorithm;

import java.util.Arrays;
import java.util.Comparator;

public class Algorithm {
	
//	// whether the file path is valid
//	public boolean checkPath(String filePath) {
//		boolean result;
//		if()
//		
//		return result;
//	}
//	// record the data [File]
//	public String[] fileInputName(String[] name, String filePath) {
//		
//		return name;
//	}
//	public double[][] fileInputTimeArrSer(double[][] time, String filePath) {
//		
//		return time;
//	}
	
	// choose a algorithm
	public void choose() {
		System.out.println("-------Algorithm List-------");
		System.out.println("Please choose a scheduling algorithm listed below");
		System.out.println("\n\t1.First-come, first-served (FCFS)"
				+ "\n\t2. Shortest job first (SJF)"
				+ "\n\t3. Priority"
				+ "\n\t5. exit system");
	}
	
	//time按照到达时间重新排序
	public double[][] reorder(int num, double[][] time){
		Arrays.sort(time, new Comparator<double[]>() {
            @Override
            public int compare(double[] t0, double[] t1) {
                return (int) (t0[num] - t1[num]);
            }
        });
		return time;
	}
	
	public double[][] process1(double[][] time) {
		//Process 1
		time[0][2] = time[0][1];
		time[0][3] = 0;
		time[0][4] = time[0][3] + time[0][1];
		return time;
	}

	public double[][] process(int i, double[][] time) {
		//column 2: Finish Time: THIS burst [1] + LAST finish [2]
		time[i][2] = time[i][1] + time[i-1][2];
		
		//column 3: waiting time:
		   //arrive before: THIS finish [2] - THIS arrive [0]
		double temp1 = time[i-1][2];
		double temp2 = time[i][0];
		if(temp1 > temp2)
			time[i][3] = temp1 - temp2;
		   //arrive after: =0
		else
			time[i][3] = 0;
		
		//column 4: turn around time: THIS waiting [3] + THIS burst [1]
		time[i][4] = time[i][3] + time[i][1];
		return time;
	}
	//scheduling algorithm
	public double[][] FCFS(int processNum, double[][] time) {
		//based on the column 1: arrive time
		reorder(0, time);
		if(processNum == 1) {
			process1(time);
			//column 5: priority
			time[0][5] = 1;
		}
		else {
			//process 1
			process1(time);
			//other process
			for(int i=1; i<processNum; i++) {
				process(i, time);
				time[i][5] = i+1;
			}
				
		}
			
		return time;
	}
	public double[][] SRT(int processNum, double[][] time) {
		//根据到达时间找到第一个process
		reorder(0, time);
		if(processNum == 1) {
			process1(time);
			time[0][5] = 1;
		}
		else {
			//process 1
			process1(time);
			//other process
			for(int i=1; i<processNum; i++) {
				reorder(1, time);
				// column 2,3,4
				process(i, time);
				// column 5: priority
				time[i][5] = time[i-1][5]+1;
			}
		}
		
		return time;
	}
	public double[][] P(int processNum, double[][] time) {
		//based on the column 5: priority
		reorder(5, time);
		if(processNum == 1) 
			process1(time);
		else {
			//process 1
			process1(time);
			//other process
			for(int i=1; i<processNum; i++)
				process(i, time);
		}
		return time;
	}

	
	//print result
	public void print(int processNum, String[] name, double[][] time) {
		//based on the column 6: process original order
		reorder(6, time);
		//print out
		for(int i=0; i<processNum; i++) {
			System.out.print(name[i] + " ");
			for(int j=0; j<6; j++) {
				System.out.print(time[i][j]+" ");
			}
			System.out.println(" ");
		}
		
	}
	
	// system exit
	public void exit() {
		System.out.print("See you next time~ ");
	}
}
