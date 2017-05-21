import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class takes an input array list and outputs a 
 * scatter plot, the line of best fit and the equation of that line.
 * It also calculates the min, max, mean, and standard deviation of the two array list.
 *
 * @author Nasi Robinson
 */

public class Regression {
	public static void main( String [] args){
		ArrayList<Double> list1 = new ArrayList<Double>();
		ArrayList<Double> list2 = new ArrayList<Double>();
		double x = 0;
		double y = 0;
		double minX;
		double maxX;
		double minY;
		double maxY;
		double meanX;
		double meanY;
		double sdX;
		double sdY;
		double a;
		double b;
		try {
			Scanner sc = new Scanner(new File("/Users/SwagGeneral/Downloads/cricket.txt"));
			while(sc.hasNext()){
				double chirp = sc.nextDouble();
				double temp = sc.nextDouble();
				list1.add(chirp);
				list2.add(temp);
				//System.out.println(chirp + " " + temp);	
			}
			maxX = max(list1);
			maxY = max(list2);
			minX = min(list1);
			minY = min(list2);
			meanX = mean(list1);
			meanY = mean(list2);
			sdX= sd( list1, meanX);
			sdY= sd(list2, meanY);
			double summation = summation(list1,list2);
			double correlation = ((summation) - (list1.size() * meanX * meanY)) / ((list1.size() - 1) * sdX *sdY);
			
			a = correlation * (sdY/sdX);
			b = meanY - (a * meanX);
			
		StdDraw.setXscale(minX,maxX);
		StdDraw.setYscale(minY,maxY);
		StdDraw.setPenRadius(0.01);
		drawLine( a , b, minX, maxX);
		
		System.out.println("y= " + a + "x + " + b );
		for ( int i=0; i < list1.size() ; i++){
			x=list1.get(i);
			y=list2.get(i);
			StdDraw.point(x, y);
		}
		
			
		} catch (FileNotFoundException e) {
			// TODO Auto-Generated catch block
			System.out.println("File not Found");
			//e.printStackTrace();
	
		}
	}
	
	// This method takes an input arraylist and returns the maximum value in the list

	public static double max(ArrayList<Double> list ){
		double max = 0;
		for ( int i=0; i < list.size() ; i++){
			if (list.get(i)> max)
				max = list.get(i);
		}
		return max;
	}
	// This method takes an array list and returns the minimum value in the list

	public static double min(ArrayList<Double> list ){
		double min = 1000000000;
		for ( int i=0; i < list.size() ; i++){	
		if (list.get(i)<min)
				min = list.get(i);
		}
		return min;
	}
	// This method takes an array list and returns the mean value in the list

	public static double mean(ArrayList<Double> list) {
		double mean = 0;
		double temp = 0;
		for ( int i=0; i < list.size() ; i++){
			mean = list.get(i) + temp;
			temp = mean;
		}
		mean = mean / list.size();
		return mean;
	}
	// This method takes then input arraylist and mean of that list and returns the Standard deviation of that arraylist using
	//the mean given.

	public static double sd(ArrayList<Double> list, double mean){
		double sd = 0;
		double temp = 0;
		for ( int i=0; i < list.size() ; i++){
			temp =(Math.pow(list.get(i) - mean,2)) + temp; 
		}
		//System.out.println(temp);
		sd = Math.sqrt(temp/(list.size()-1));
		//System.out.println(sd);
		return sd;
	}
	
	// In this method you input 2 array list and it returns the summation product of the list.

	public static double summation(ArrayList<Double> list, ArrayList<Double> list2){
		double temp = 0;
		double temp2 = 0;
		for ( int i=0; i < list.size() ; i++){
		temp = (list.get(i) * list2.get(i)) + temp2;
		temp2=temp;
	}
	return temp2;
	}
	// Input a , b , xmax, and xmin value and this method draws a line using those values.

	public static void drawLine(double a, double b, double xmin, double xmax) { 
		double y1 = (xmin * a) + b;
		double y2 = (xmax * a) + b; 
		StdDraw.line(xmin, y1, xmax, y2);
		}
	}


	


