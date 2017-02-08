import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.Topological;
public class HamiltonCircuit {
	private boolean marked[];
	private Digraph g;
	private int[] order;
	private boolean hasPath;
	private Topological top;
	private int c=1;
	public HamiltonCircuit(Digraph g){
		this.g =g;
		this.marked = new boolean[g.V()];
		this.top = new Topological(g);
		if(!top.hasOrder()){
			throw new IllegalArgumentException("Not Toplogical Order");
		}
		setOrder();
        hc(order[0]);
		this.hasPath = check(marked);
	}
	private void setOrder(){
		this.order =  new int[g.V()];
		int count =0;
		for(int x: top.order()){
			order[count] = x;	
			count++;
		}
	}
	public void hc(int v){
		marked[v] =true;
		for(int vertex: g.adj(v)){ 
			//System.out.println(vertex + " " + order[c]);  is the line *******************
			if(!marked[vertex]&&order[c]==vertex){
				c++;
				hc(vertex);	
			}
		}
	}
	private boolean check(boolean[] a){
		for(int i=0;i<a.length;i++){
			if(!a[i]){
				return false;
			}
		}
		return true;
	}
	public String toString(){
		if(!hasPath){
			throw new IllegalArgumentException("No path");
		}
		StringBuilder s = new StringBuilder();
		s.append("\n");
		for(int i =0;i<g.V()-1;i++){
			s.append(order[i]);
			s.append("-" );
		}
		s.append(order[order.length-1]);
		return s.toString();
	}
	public int[] order(){
		if(hasPath){
			return order;
		}
		else{
			throw new IllegalArgumentException("No path or Not DAG");
		}
	}
	public static void main(String[] args) {
		Digraph g = new Digraph(7);
	    HamiltonCircuit h = new HamiltonCircuit(g);
		System.out.println(h.check(h.marked));
		System.out.println(h);
		System.out.println("hello");


		/*for(Point p: h.hp()){
			System.out.println(p);
	    }*/
		
	}
}
