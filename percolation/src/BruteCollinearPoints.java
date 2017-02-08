import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
	private LineSegment[] segment;
	private int count=0;
   public BruteCollinearPoints(Point[] points){
	   checkDuplicatedEntries(points);
	   segment = new LineSegment[points.length/4];
	   for (int p = 0; p < points.length - 3; p++) {
           for (int q = p + 1; q < points.length - 2; q++) {
               for (int r = q + 1; r < points.length - 1; r++) {
                   for (int s = r + 1; s < points.length; s++) {
                	   if(points[p].slopeTo(points[q])==points[p].slopeTo(points[r])&&
                	      points[p].slopeTo(points[q])==points[p].slopeTo(points[s])){
                		   segment[count] = findEndpoints(points[q],points[p],points[r],points[s]);
                		   count++;   	   
                	   }  
                	   }
                	   }
                	   }
                		   
                   }
   }// finds all line segments containing 4 points
   public int numberOfSegments(){
	   return count;
	   
   }// the number of line segments
   public LineSegment[] segments(){
	   return segment;
}// the line segments
   private LineSegment findEndpoints(Point a, Point b,Point c, Point d){
	   Point[] coll = {a,b,c,d};
	   Point min = coll[0];
	   Point max = coll[0];
	   for(int i =0;i<coll.length;i++){
		   if(min.compareTo(coll[i])>0)
			   min = coll[i]; 
		   if(max.compareTo(coll[i])<0)
			   max = coll[i];
	   }
	   return new LineSegment(min,max);
	   
   }
   private void checkDuplicatedEntries(Point[] points) {
       for (int i = 0; i < points.length - 1; i++) {
           for (int j = i + 1; j < points.length; j++) {
               if (points[i].compareTo(points[j]) == 0) {
                   throw new IllegalArgumentException("Duplicated entries in given points.");
               }
           }
       }
       }
   public static void main(String[] args) {

	    // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
   }
