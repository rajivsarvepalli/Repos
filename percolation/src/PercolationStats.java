import edu.princeton.cs.algs4.StdOut;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
	private Percolation obj;
	private int getTrials;
	private double array[];
	private int getN;
	   public PercolationStats(int n, int trials){
		   if(n<1||trials<1) throw new IllegalArgumentException("Invalid n or trials");
		   getTrials =trials; 
		   getN=n;
		   array = new double[trials];
		   for(int i=0; i<trials;i++)
			   	array[i]= percThresh();
		   // perform trials independent experiments on an n-by-n grid
	   }
	   public double mean(){
		   return StdStats.mean(array);
		   }
		   // sample mean of percolation threshold
	   public double stddev(){
		   if(getTrials==1)
			   return Double.NaN;
		   else
			   return StdStats.stddev(array);
		   // sample standard deviation of percolation threshold
	   }
	   public double confidenceLo() {
		   return mean()-1.96/(Math.sqrt(getTrials*1.0));
		   // low  endpoint of 95% confidence interval
	   }
	   public double confidenceHi() {
		   return mean()+1.96/(Math.sqrt(getTrials*1.0));
		   // high endpoint of 95% confidence interval
	   }
	   private double percThresh() {
		    obj = new Percolation(getN);
		    int row, col;
		    int counter=0;
		    while (!obj.percolates()) {
		    	row = 1+StdRandom.uniform(getN);
		        col = 1+StdRandom.uniform(getN);
		    /*  do {
		        row = 1+StdRandom.uniform(getN);
		        col = 1+StdRandom.uniform(getN);
		      } while (obj.isOpen(row,col));*/
		    	if(!obj.isOpen(row,col)){
		           counter++;
		           obj.open(row, col);
		    	}
		    	
		    }
		    return (double)(counter)/(getN*getN);
		  }

	   public static void main(String[] args){
		    int n = Integer.parseInt(args[0]);
	        int trials = Integer.parseInt(args[1]);
		    PercolationStats s = new PercolationStats(n,trials);
		    System.out.println("The mean is: " + s.mean());
		    StdOut.println("The condfidence low is: " + s.confidenceLo());
		    StdOut.println("The confidence hi is: " + s.confidenceHi());
		    StdOut.println("the stddev is " + s.stddev());
		   // test client (described below)
	   }
	}

