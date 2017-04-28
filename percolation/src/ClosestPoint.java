import java.util.Scanner;

import edu.princeton.cs.algs4.Point2D;

public class ClosestPoint {
	private kdTree2 tree;
	private double x;
	public ClosestPoint(kdTree2 tree, double x){
		this.tree =tree;
		this.x = x;
		Scanner scan = new Scanner(System.in);
		System.out.println("enter function vertical shift");
		double vshift = scan.nextDouble();
		System.out.println("enter function horizontal shift");
		double hshift = scan.nextDouble();
		System.out.println("enter function stretch");
		double stretch = scan.nextDouble();
		System.out.println("enter function power");
		double power = scan.nextDouble();
		double y = stretch*Math.pow((x-hshift), power) +vshift;
		Point2D p = tree.nearest(new Point2D(x,y));
		System.out.println("The nearest point in your tree is:  " + p);
		
		
	}
	public static void main(String[] args){
		kdTree2 tree = new kdTree2();
		tree.insert(new Point2D(1,2));
		tree.insert(new Point2D(1,1.5));

		ClosestPoint c = new ClosestPoint(tree, 1.0);
	}
}
