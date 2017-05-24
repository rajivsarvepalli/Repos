

import java.util.Arrays;
import java.util.Stack;
public class Board {
	private final int[][] tiles;
	private final int n;
    public Board(int[][] blocks){
    	n = blocks.length;
    	tiles = clone(blocks,n);
    	// construct a board from an n-by-n array of blocks
    }
    private int[][] clone(int[][] in,int size){//copying 2d array 
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
    private int oneD(int i,int j){//use to calculate manhattan distance 
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
    public int manhattan(){//manhattan distance is the distance between two points measured along axes at right angle
    	int totald =0; //use manhattan distance to see the distance of all the blocks from the positions they should be in
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
    private void swap(int[][] a,int i, int j, int finali,int finalj ){//helper for creating a twin board and neighboring boards 
    	int swap;
    	swap = a[i][j];
    	a[i][j]=a[finali][finalj];
    	a[finali][finalj] = swap;   	
    }
    public Board twin(){//creating a twin board that swaps two non-zero spots 
    	int[][] Twin = clone(tiles, n);//this is used to test if a board is impossible 
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
        return (this.n == that.n) && (Arrays.deepEquals(this.tiles, that.tiles));//checking for equality in all ways, especially the numbers within the array using deepequals

    }
    private void push(Stack<Board> boards, int i, int j, int si, int sj){//creating a stack of boards 
    	int[][]nearTiles = clone(tiles, n);
    	swap(nearTiles,i,j,si,sj);//need swap to swap the 0 with the near by spots so utilize swap hear to help with the Iterable neighbors
    	boards.push(new Board(nearTiles));
    	
    }
    public Iterable<Board> neighbors(){// all neighboring boards; boards that have the 0 moved around to displace the surrounding blocks 
        Stack<Board> boards = new Stack<Board>();
        boolean check = false;
        for (int i = 0; i < n && !check; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {//finding zero, then adding all neighbors to the stack
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
        return boards; //return iterable stack 
    }
    public String toString() {//printing in matrix format 
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


    public static void main(String[] args){//testing 
//      int[][] tiles = {{1, 3, 7}, {0, 2, 8}, {4, 6, 5}};
//      int[][] tiles = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
      int[][] blocks = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
//      int[][] tiles = {{1, 0}, {2, 3}};
      Board a = new Board(blocks);
      System.out.println(a.dimension());
      System.out.println(a.manhattan());
      System.out.println(a);
      System.out.println(a.twin());
      System.out.println(a);
      for (Board board: a.neighbors()) {
    	  System.out.println(board);
      }// unit tests (not graded)
}
}
