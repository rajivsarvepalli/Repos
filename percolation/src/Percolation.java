import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
	private boolean check[][];
	private WeightedQuickUnionUF uf;
	private int max;
	private int getN;
	public Percolation(int n){
		 if(n<1) throw new IllegalArgumentException("N is must grater than 0");
		  getN =n;
		  max =n*n+1;
		  uf = new WeightedQuickUnionUF(max+1);
		  check = new boolean[n][n];
		   // create n-by-n grid, with all sites blocked
	   }
	   public void open(int row, int col){
		   if(row<1||row>getN||col<1||col>getN) throw new IllegalArgumentException("Row and Colum must be in bounds");
		   //need to union bottom row and top row open sites to virtual sites and create exceptions for outbounds in the isOpen if statements
		   check[row-1][col-1] =true;
		   if(row-2>-1 && isOpen(row-1,col))
			   	uf.union(changeToUf(row,col),changeToUf(row-1,col));
		   if(row<getN && isOpen(row+1,col))
			   	uf.union(changeToUf(row,col),changeToUf(row+1,col));
		   if(col-2>0 && isOpen(row,col-1))
			   	uf.union(changeToUf(row,col),changeToUf(row,col-1));
		   if(col<getN && isOpen(row,col+1))
			   uf.union(changeToUf(row,col), changeToUf(row,col+1));
		   if(row==1)
			   uf.union(0,changeToUf(row,col));
		   else if(row==getN)
			   uf.union(max, changeToUf(row,col));
	   }// open site (row, col) if it is not open already
	   public boolean isOpen(int row, int col){
		   if(row<1||row>getN||col<1||col>getN) throw new IllegalArgumentException("Row and Colum must be in bounds");
		   return check[row-1][col-1];
	   }// is site (row, col) open?
	   public boolean isFull(int row, int col){
		   if(row<1||row>getN||col<1||col>getN) throw new IllegalArgumentException("Row and Colum must be in bounds");
		   return uf.connected(0,changeToUf(row,col));
		   // is site (row, col) full?
	   }
	   public boolean percolates(){
		   return uf.connected(0,max);// does the system percolate?
	   }
	   private int changeToUf(int row, int col){
		   
		   return getN*(row-1)+col;
	   }

	   public static void main(String[] args) {  // test client (optional)
		   System.out.println("hello");
		   Percolation object = new Percolation(6);
		   object.open(1,1);
		   object.open(1,1);
		   object.open(1,1);
		   object.open(2,1);
		   object.open(3,1);
		   object.open(4,1);
		   object.open(5,1);
		   object.open(6,1);
		   
		   System.out.println(object.percolates());
	} 
}