import java.util.Arrays;
import java.util.ArrayList; 

public class FastCollinearPoints {
	private ArrayList<Point> collinear = new ArrayList<>();
	private ArrayList<LineSegment> lines = new ArrayList<>();
    public FastCollinearPoints(Point[] points){
      if (points == null)
         throw new NullPointerException();
     /* for(Point p: points)
   	   System.out.print(p+ " ");
       System.out.println();*/
       for(int g= 0;g<points.length;g++){
    	   Arrays.sort(points,points[0].slopeOrder());
    	   for(Point j: points)	
    		   System.out.print(j + " ");
    	   System.out.println();
    	   for(int i =1; i<points.length;i++){
    		   if(points[g].slopeTo(points[i-1])==points[g].slopeTo(points[i])){
    		    collinear.add(points[g]);
    		    collinear.add(points[i-1]);
    		   	collinear.add(points[i]);
    		   }   
    	   }
    	   if(collinear.size()>3){
    		   Point[] coll = new Point[collinear.size()];
    		   coll = collinear.toArray(coll);
    		   LineSegment l = findEndpoints(coll);
    		   if(!lines.contains(l));
    		   	lines.add(l);
    		   
    	   }
       }
	   
	   
   }// finds all line segments containing 4 or more points
    private LineSegment findEndpoints(Point[] coll){
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
   public int numberOfSegments(){
	   return lines.size();
   }// the number of line segments
   public LineSegment[] segments(){
	   LineSegment[] line = new LineSegment[lines.size()];
	   line = lines.toArray(line);
	   return line;
   }// the line segments
   public static void main(String[] args){
	   Point p1 = new Point(3,4);
	   Point p2 = new Point(4,4);
	   Point p3 = new Point(6,4);
	   Point p4 = new Point(1,4);
	   Point[] array ={p1, p2,p3,p4,};
	   FastCollinearPoints points = new FastCollinearPoints(array);
	   System.out.println("hello");
	   for(LineSegment p: points.segments()){
		   System.out.print(p + " ");		   
	   }
	   System.out.println("hello");
   }
}
