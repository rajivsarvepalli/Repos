import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item array[];
	private int n;
	private int arraySize;
   public RandomizedQueue(){
	   this.n=0;
	   this.arraySize =1;
	   array = (Item[])new Object[arraySize];
	   // construct an empty randomized queue
   }
   public boolean isEmpty(){
	   return size()==0;// is the queue empty?
   }
   public int size(){
	   return n;// return the number of items on the queue
   }
   private void resize(int capacity){
	   Item[] newarray = (Item[]) new Object[arraySize];
	   for(int i =0; i<n;i++){
		   newarray[i]= array[i]; 
	   }
	   array=newarray;
   }
   public void enqueue(Item item){
	   if (item == null) throw new java.lang.NullPointerException();
	   if(n==arraySize){
		   arraySize*=2;
		   resize(arraySize);
	   }
	   array[n++]=item;
	   // add the item
   }
   public Item dequeue(){//fix deque
	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   int i =StdRandom.uniform(n);
	   Item remove = array[i];//why do u need item ret?
       array[i] = array[--n];
       array[n] = null;
	   if (arraySize / 4 > n) {
		   arraySize/=2;
		   resize(arraySize);
       }	   	   
	   return remove;// remove and return a random item
   }
   public Item sample(){
	   if (isEmpty()) throw new java.util.NoSuchElementException();
	   return array[StdRandom.uniform(n)];
	   
	   // return (but do not remove) a random item
   }
   public Iterator<Item> iterator(){
	   return new Iterato();// return an independent iterator over items in random order
   }
   private class Iterato implements Iterator<Item>{
	   private int current=0;
	   private int[] indx = new int[n];
	   public boolean hasNext(){
		   return current <n;
	   }
	   public Item next(){
		   if(!hasNext()){
			   throw new java.util.NoSuchElementException();
		   }
		   if (current == 0) {
               for (int i = 0; i < n; i++)
                   indx[i] = i;
               StdRandom.shuffle(indx);
           }
		   return array[indx[current++]];  
	   }
	   public void remove(){
		   throw new java.lang.UnsupportedOperationException();
	   }
   }
   public static void main(String[] args){
	   RandomizedQueue<Integer> rq1 = new RandomizedQueue<Integer>();
	   rq1.enqueue(5);
	   rq1.enqueue(7);
	   rq1.enqueue(6);
	   rq1.enqueue(6);
	   rq1.enqueue(6);
	   rq1.enqueue(6);
	   rq1.dequeue();
	   rq1.dequeue();
	   rq1.dequeue();
	   rq1.dequeue();
	   rq1.dequeue();
	   	   for(int x:rq1){
		   System.out.print(x + " ");// unit testing
	   }
	   	   System.out.println();
	   System.out.println(rq1.arraySize);
	   System.out.println(rq1.size());
   }
}