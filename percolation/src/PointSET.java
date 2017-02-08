import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;
public class PointSET {
   private SET<Point2D> points;
   public PointSET(){
	   points = new SET<Point2D>();// construct an empty set of points 
   }
   public boolean isEmpty(){
	   return points.size()==0;
   }
   // is the set empty? 
   public int size(){
	   return points.size();// number of points in the set 
   }
   public void insert(Point2D p){
	   points.add(p);// add the point to the set (if it is not already in the set)
   }
   public boolean contains(Point2D p){
	  return points.contains(p);// does the set contain point p? 
   }
   public void draw(){
	   for(Point2D p: points)
		   p.draw();// draw all points to standard draw 
   }
   public Iterable<Point2D> range(RectHV rect){
	   Stack<Point2D> contains = new Stack<Point2D>();
	   for(Point2D p :points){
		   if(rect.contains(p))
			   contains.push(p);
	   }
	  return contains; // all points that are inside the rectangle 
   }
   public  Point2D nearest(Point2D p){
	   if(size()==0)
		   return null;
	   Point2D closest = null;
	   for(Point2D z: points){
		   if(closest == null)
			   closest = z;
		   if(z.distanceSquaredTo(p)<closest.distanceSquaredTo(p)){
			   closest =z;// a nearest neighbor in the set to point p; null if the set is empty 
		   }
	   }
	   return closest;
   }

   public static void main(String[] args){// unit testing of the methods (optional) 
}
}