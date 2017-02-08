import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
//check input
public class SAP {
	private Digraph G;
	private int Flength;
	private int ancestor;
   // constructor takes a digraph (not necessarily a DAG)
   public SAP(Digraph G){
	   this.G = G;
	   this.Flength =-1;
	   this.ancestor = -1;
   }

   // length of shortest ancestral path between v and w; -1 if no such path
   public int length(int v, int w){
	   checkInput(v);
	   checkInput(w);
	   Run(v,w);
	   return Flength;
   }

   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
   public int ancestor(int v, int w){
	   checkInput(v);
	   checkInput(w);
	   Run(v,w);
	   return ancestor;
   }

   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
   public int length(Iterable<Integer> v, Iterable<Integer> w){  
	   checkInput(v);
	   checkInput(w);
	   Run(v,w);
	   return Flength;
   }

   // a common ancestor that participates in shortest ancestral path; -1 if no such path
   public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
	   checkInput(v);
	   checkInput(w);
	   Run(v,w);
	   return ancestor;
	   
   }
   private void Run(int v, int w){
	   ancestor = -1;
	   Flength =-1;
	   BreadthFirstDirectedPaths bv = new BreadthFirstDirectedPaths(G, v);
	   BreadthFirstDirectedPaths bw = new BreadthFirstDirectedPaths(G, w);
	   int length = Integer.MAX_VALUE;	   
	   for(int i =0;i<G.V();i++){
		   if(bv.hasPathTo(i)&&bw.hasPathTo(i)){
			   int length2 = bv.distTo(i)+bw.distTo(i);
			   if(length2<length){
				   length = length2;
				   ancestor = i;
			   }
		   }
	   }
	   if(length == Integer.MAX_VALUE) 
		   length =-1;
	   Flength = length;
   }
   private void Run(Iterable<Integer> v, Iterable<Integer> w){
	   ancestor = -1;
	   Flength =-1;
	   BreadthFirstDirectedPaths bv = new BreadthFirstDirectedPaths(G, v);
	   BreadthFirstDirectedPaths bw = new BreadthFirstDirectedPaths(G, w);
	   int length = Integer.MAX_VALUE;
	   
	   for(int i =0;i<G.V();i++){
		   if(bv.hasPathTo(i)&&bw.hasPathTo(i)){
			   int length2 = bv.distTo(i)+bw.distTo(i);
			   if(length2<length){
				   length = length2;
				   ancestor = i;
			   }
		   }
	   }
	   if(length == Integer.MAX_VALUE) 
		   length =-1;
	   Flength = length;
   }
   private void checkInput(int vertex) {
	      if (vertex < 0 || vertex > G.V() - 1) throw new java.lang.IndexOutOfBoundsException();      
	   }
	   
   private void checkInput(Iterable<Integer> vertex) {
	      for (Integer v : vertex) {
	         if (v < 0 || v > G.V() - 1) throw new java.lang.IndexOutOfBoundsException();   
	      }    
	   }
	   
   // do unit testing of this class
	   public static void main(String[] args) {
		   System.out.println("h");
		    Digraph G = new Digraph(6);
		    SAP sap = new SAP(G);
		    System.out.println("h");
		    G.addEdge(2, 1);
		    G.addEdge(3, 4);
		    G.addEdge(0, 4);
		    G.addEdge(1, 4);
		    G.addEdge(5, 1);
		    System.out.println(sap.length(5, 2));
		}
   }