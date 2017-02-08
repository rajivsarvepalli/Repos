import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;

public class kdTree2 {
	private Node root;
	private int size;
	private Node closest;
	private StringBuffer s;
	private class Node{
		private Point2D key;
		private Point2D val;
		private Node left;
		private Node right;
		private boolean isVert;
		public Node(Point2D key,Node right, Node left, boolean isVert, Point2D val){
			this.key =key;
			this.isVert = isVert;
			this.left =left;
			this.right =right;
			this.val = val;
		}
	}
	public kdTree2(){
		root = null;
		size =0;
	}
	public String toString() {
        s = new StringBuffer();
        s.append("\n");
        s.append("This is the root, and the first left and right branch if not null: \n");
        s.append("   Top of Tree: \n");
        if(root!=null){
        	s.append("   " +root.key);
        	s.append("\n");
        }
        if(root.left!=null){
        	s.append("   //      \\\\ \n");
        	s.append(root.left.key);
        }
        if(root.right!=null)
        	s.append(root.right.key);
        
        return s.toString();		
	}
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size==0;
	}
	public void insert(Point2D p){
			root = insert(p,p,root,true);	
	}
	private Node insert(Point2D key, Point2D value, Node node, boolean isVert){
		if(node ==null){
			size++;
			return new Node(key,null,null,isVert,value);
		}
		if(node.key.x()==key.x()&&node.key.y()==key.y()){
			node.val = value;
		}
		else if ((node.isVert && node.key.x() >= key.x()) || (!node.isVert && node.key.y() >= key.y())){
			 node.left = insert(key, value,node.left,!isVert);
		 }
		 else{
			 node.right = insert(key, value, node.right,!isVert);
		 }
		return node;
	}
	public boolean contains(Point2D p){
		return c(p);
	}
	private boolean c(Point2D p){//contains not working 
		Node node = root;
        while (node != null) {
            if (node.key.x() == p.x() && node.key.y() == p.y()) {
                return true;
            } else if ((node.isVert && node.key.x() >= p.x()) || (!node.isVert && node.key.y() >= p.y())) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
	}
	public Point2D nearest(Point2D p){
		closest =root;
		return nearest(p,root);
	}
	private Point2D nearest(Point2D p, Node n1){
		/*if(n1.right!=null)
			System.out.println(n1.right.key);*/
		if(size ==0){
			return null;
		}
		if(closest.key.distanceSquaredTo(p) >n1.key.distanceSquaredTo(p)){
			closest = n1;
		}
		if((n1.isVert && n1.key.x() >= p.x()) || (!n1.isVert && n1.key.y() >= p.y())){
			if(n1.left!=null){
				nearest(p,n1.left);
			}
		}
		else{
			if(n1.right!=null){
				nearest(p,n1.right);
			}
		}
		return closest.key;
	}
	public Iterable<Point2D> range(RectHV rect){
		Stack<Point2D> points = new Stack<Point2D>();
		addPoints(rect,points,root);
		return points;
	}
	private Stack<Point2D> addPoints(RectHV rect, Stack<Point2D> points, Node n1){
		if(size==0)
			return null;
		if(rect.contains(n1.key)){
			points.push(n1.key);
		}
		if((n1.isVert && n1.key.x() >= rect.xmax()) || (!n1.isVert && n1.key.y() >= rect.ymax())){
			if(n1.left!=null)
				addPoints(rect,points,n1.left);
		}
		else if((n1.isVert&&n1.key.x()<=rect.xmax()&&n1.key.x()>=rect.xmin())||(!n1.isVert&&n1.key.y()<=rect.ymax()&&n1.key.y()>=rect.ymin())){
			 addMiddlePoints(rect,points,n1);
		}
		else {
			if(n1.right!=null)
				addPoints(rect,points,n1.right);
		}
		return points;
	}
	private Stack<Point2D> addMiddlePoints(RectHV rect, Stack<Point2D> points, Node n1){//do both sides right and left of the tree
		if(size==0)
			return null;
		if(n1.right!=null){
		checkRight(n1.right,points,rect);
		}
		if(n1.left!=null){
		checkLeft(n1.left,points,rect);
		}
		return points;
	}
	private void checkRight(Node n1,Stack<Point2D> points,RectHV rect){
		if(rect.contains(n1.key))
			points.push(n1.key);
		if(n1.left!=null){
			checkLeft(n1.left,points,rect);
		}
		if((n1.isVert && n1.key.x() > rect.xmax()) || (!n1.isVert && n1.key.y() >rect.ymax())){
			return;
		}
		else{
			if(n1.right!=null){
				checkRight(n1.right,points,rect);
			}
			else{
				return;
			}
		}
		
	}
	private void checkLeft(Node n1,Stack<Point2D> points,RectHV rect){
		if(rect.contains(n1.key)){
			points.push(n1.key);
	     }
		if(n1.right!=null){
			checkRight(n1.right,points,rect);
		}
		if((n1.isVert && n1.key.x() < rect.xmin()) || (!n1.isVert && n1.key.y() < rect.ymin())){
			return;
	    }
		else{
			if(n1.left!=null){
				checkLeft(n1.left,points,rect);
			}
			else{
				return;
			}
		}
	}
	public void draw(){
		
	}
	public static void main(String[] args){
		Point2D p1 = new Point2D(6,6);
		Point2D p2 = new Point2D(6,6);
		Point2D p3 = new Point2D(7,8);
		Point2D p4 = new Point2D(7,7);
		Point2D p8 = new Point2D(5,8);
		Point2D p7 = new Point2D(3,3);
		Point2D p10 = new Point2D(11,11);
		kdTree2 tree = new kdTree2();
	   tree.insert(p1);
	   tree.insert(p4);
	   tree.insert(p3);
	   tree.insert(p2);
	   tree.insert(p10);
	   tree.insert(p7);
	   tree.insert(p8);
	   System.out.println(tree.root.left.right.key);
		System.out.println(tree.contains(p8));
		System.out.println(tree);//contains doesn't work
	 //  RectHV rect = new RectHV(3, 3, 10, 10);
	  /* for(Point2D p:tree.range(rect)){
		   System.out.println(p);
	   }*/
	}
}

