import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.util.PriorityQueue;
import java.awt.*;
import javax.swing.*;

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
	
	  private void in(SearchNode node, PriorityQueue<SearchNode> pq) {
	        for (Board nextBoard: node.board.neighbors()) {
	            if ((node.prev == null) || (!nextBoard.equals(node.prev.board))) {
	                SearchNode n1 = new SearchNode(nextBoard, node.moves + 1,node);
	                pq.add(n1);
	            }
	        }
	    }
    public Solver(Board initial){
    	if(initial ==null)
    		throw new java.lang.NullPointerException();
    	PriorityQueue<SearchNode> nodes = new PriorityQueue<SearchNode>();
    	PriorityQueue<SearchNode> nodest = new PriorityQueue<SearchNode>();
    	SearchNode start = new SearchNode(initial,0,null);
    	SearchNode startT = new SearchNode(initial.twin(),0, null);
    	nodes.add(start);
    	nodest.add(startT);
    	while(true){
    		SearchNode n1 = nodes.remove();
    		SearchNode n2 = nodest.remove();
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
        try {
		  		JFrame frame = new JFrame("My Drawing");
				ImageCanvas canvas = new ImageCanvas();           
				frame.add(canvas);
				canvas.setSize(600,400);
				frame.pack();
				frame.setVisible(true);
            System.out.print("Enter the file name with extension : ");//file should be entered same as Image in ImageCanvas, use text files with 8puzzles
            Scanner in = new Scanner(System.in);
            File file = new File(in.nextLine());
            in = new Scanner(file);
            int n = in.nextInt();
            int[][] blocks = new int[n][n];
            while(in.hasNextInt()){
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    blocks[i][j] = in.nextInt();
            }
            Board initial = new Board(blocks);

            // solve the puzzle
            Solver solver = new Solver(initial);

            // print solution to standard output
            if (!solver.isSolvable())
                System.out.println("No solution possible");
            else {
                System.out.println("Minimum number of moves = " + solver.moves());
                for (Board board : solver.solution())
                    System.out.println(board);
            }
        }

       catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}