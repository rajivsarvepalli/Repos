

import java.util.Arrays;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Board {
	private final int[][] tiles;
	private final int n;
    public Board(int[][] blocks){
    	n = blocks.length;
    	tiles = clone(blocks,n);
    	// construct a board from an n-by-n array of blocks
    }
    private int[][] clone(int[][] in,int size){
    	int[][] o = new int[size][size];
    	for(int i =0; i<size;i++){
    		for(int j =0; j<size;j++){
    			o[i][j]= in[i][j];
    		}
    	}
    	return o;
    }
                                           // (where blocks[i][j] = block in row i, column j)
    public int dimension(){
    	return n;// board dimension n
    }
    public int hamming(){
    	int wrongs =0;
    	for(int i =0; i<tiles.length;i++){
    		for(int j =0; j<tiles.length;j++){
    			if(!(tiles[i][j]==j+i*n+1))
    				wrongs++;
    		}
    	}
    	return --wrongs;
    	// number of blocks out of place
    }
    private int oneD(int i,int j){
    	int I = tiles[i][j]/n;
    	int J = tiles[i][j]%n;
    	 if (J == 0) {
             I -= 1;
             J = n - 1;
         } else {
             J -= 1;
         }
         return Math.abs(i - I) + Math.abs(j - J);
    }
    public int manhattan(){
    	int totald =0; 
    	for(int i =0; i<n;i++){
    		for(int j =0; j<n;j++){
    			if (tiles[i][j] != (j + i * n  + 1) && tiles[i][j] != 0)
    				totald+=oneD(i,j);
    		
    	    }
    	}
    	return totald;
    	
    	// sum of Manhattan distances between blocks and goal
    }
    public boolean isGoal(){
    	return manhattan()==0;// is this board the goal board?
    }
    private void swap(int[][] a,int i, int j, int finali,int finalj ){
    	int swap;
    	swap = a[i][j];
    	a[i][j]=a[finali][finalj];
    	a[finali][finalj] = swap;   	
    }
    public Board twin(){
    	int[][] Twin = clone(tiles, n);
    	if (Twin[0][0] * Twin[0][1] == 0) {
            swap(Twin, 1, 0, 1, 1);
        } 
    	else {
            swap(Twin, 0, 0, 0, 1);
        }
        return new Board(Twin);
    }
    	// a board that is obtained by exchanging any pair of blocks
    public boolean equals(Object y){
    	 //check for reference equality
        if (y == this) return true;
        if (!(y instanceof Board)) return false;
        Board that = (Board) y;
        return (this.n == that.n) && (Arrays.deepEquals(this.tiles, that.tiles));

    }
    private void push(Stack<Board> boards, int i, int j, int si, int sj){
    	int[][]nearTiles = clone(tiles, n);
    	swap(nearTiles,i,j,si,sj);
    	boards.push(new Board(nearTiles));
    	
    }
    public Iterable<Board> neighbors(){// all neighboring boards
        Stack<Board> boards = new Stack<Board>();
        boolean check = false;
        for (int i = 0; i < n && !check; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    if (i > 0) 
                    	push(boards, i - 1, j, i, j);
                    if (j > 0) 
                    	push(boards, i, j - 1, i, j);
                    if (i < n - 1) 
                    	push(boards, i + 1, j, i, j);
                    if (j < n - 1)
                    	push(boards, i, j + 1, i, j);
                    check = true;
                    break;
                }
            }
        }
        return boards;
    }
    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append(n + "\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }


    public static void main(String[] args){
//      int[][] tiles = {{1, 3, 7}, {0, 2, 8}, {4, 6, 5}};
//      int[][] tiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
      int[][] blocks = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
//      int[][] tiles = {{1, 0}, {2, 3}};
      Board a = new Board(blocks);
      StdOut.println(a.dimension());
      StdOut.println(a.hamming());
      StdOut.println(a.manhattan());
      StdOut.println(a);
      StdOut.println(a.twin());
      StdOut.println(a);
      for (Board board: a.neighbors()) {
          StdOut.println(board);
      System.out.println("hi");
      }// unit tests (not graded)
}
}
