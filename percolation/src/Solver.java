import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
	private boolean possible;
	private SearchNode l;
	private int moves;
	private class SearchNode implements Comparable<SearchNode>{
		private Board board;
		private int moves;
		private int priority;
		private SearchNode prev;
		public SearchNode(Board board, int moves, SearchNode prev){
			this.board = board;
			this.moves = moves;
			this.priority = board.manhattan() + moves;
			this.prev = prev;
		}
		public int compareTo(SearchNode that){
			if(this.priority>that.priority)
				return 1;
			if(this.priority<that.priority)
				return -1;
			return 0;
		}
		
	}
	
	  private void in(SearchNode node, MinPQ<SearchNode> pq) {
	        for (Board nextBoard: node.board.neighbors()) {
	            if ((node.prev == null) || (!nextBoard.equals(node.prev.board))) {
	                SearchNode n1 = new SearchNode(nextBoard, node.moves + 1,node);
	                pq.insert(n1);
	            }
	        }
	    }
    public Solver(Board initial){
    	if(initial ==null)
    		throw new java.lang.NullPointerException();
    	MinPQ<SearchNode> nodes = new MinPQ<SearchNode>();
    	MinPQ<SearchNode> nodest = new MinPQ<SearchNode>();
    	SearchNode start = new SearchNode(initial,0,null);
    	SearchNode startT = new SearchNode(initial.twin(),0, null);
    	nodes.insert(start);
    	nodest.insert(startT);
    	while(true){
    		SearchNode n1 = nodes.delMin();
    		SearchNode n2 = nodest.delMin();
    		if(n1.board.isGoal()){
    			l = n1;
    			moves = l.moves;
    			possible = true;
    			break;
    		}
    		if(n2.board.isGoal()){
    			possible = false;
    			moves = -1;
    			break;
    		}
    		in(n1, nodes);
    		in(n2,nodest);
    	}
    	
    }
    public boolean isSolvable(){
    	return possible;// is the initial board solvable?
    	
    }
    public int moves(){
    	return moves;
    }
    // min number of moves to solve initial board; -1 if unsolvable
    public Iterable<Board> solution(){
    	Stack<Board> stack = new Stack<Board>();
    	if(!possible){
    		 stack = null;;
    	}
    	else{
    		SearchNode f = l;
    		while(f!=null){
    			stack.push(f.board);
    			f = f.prev;
    		}
    	}
    	return stack;// sequence of boards in a shortest solution; null if unsolvable
    }
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}