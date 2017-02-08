import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
	private final int xcord;
	private final int ycord;
   public Point(int x, int y){// constructs the point (x, y)
	   this.xcord =x;
	   this.ycord =y;
   }
   public   void draw(){
	   StdDraw.point(xcord, ycord);// draws this point
   }
   public   void drawTo(Point that){
	   StdDraw.line(this.xcord, this.ycord, that.xcord, that.ycord);// draws the line segment from this point to that point
   }
   public String toString(){ // string representation
	   return "(" + xcord + ", " + ycord + ")";
   }   
   public int compareTo(Point that){
	   if (this.ycord == that.ycord && this.xcord == that.xcord) return 0;
       if (this.ycord < that.ycord || (this.ycord == that.ycord && this.xcord < that.xcord)) return -1;
       return 1;// compare two points by y-coordinates, breaking ties by x-coordinates
   }
   public double slopeTo(Point that){
	        if (this.ycord - that.ycord == 0) {
	            if (this.xcord - that.xcord == 0) {
	                return Double.NEGATIVE_INFINITY;
	            }
	            return +0;
	        } else if (this.xcord - that.xcord == 0) {
	            return Double.POSITIVE_INFINITY;
	        }
	        return (that.ycord - this.ycord) / (double) (that.xcord - this.xcord);
	    }
   public Comparator<Point> slopeOrder(){// compare two points by slopes they make with this point
	   return new Comparator<Point>(){
	   @Override
	   public int compare(Point point1, Point point2){
		   double diff = slopeTo(point1)-slopeTo(point2);
		   if(diff>0){
			   return 1;
		   }
		   if(diff<0){
		   return 1;
		   }
		   else{
			   return 0;
		   }
	   }
	   };
   }
   }