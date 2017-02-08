import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int  totalItems;
	private class Node{
		private Node next;
		private Node before;
		private Item item;
		Node(Item item){
			this.item =item;
			this.next = null;
			this.before = null;
		
		}	
	}
	private class Iterato implements Iterator<Item>{
		private Node current;
		public Iterato(){
			this.current = first;
		}
		public boolean hasNext(){
			return current!=null;
		}
		public Item next(){
			if(hasNext()){
				Node node =current;
				current =current.next;
				return node.item;
			}
			else{
				throw new NoSuchElementException();
			}
		}
		public void remove(){
			throw new UnsupportedOperationException();
		}
	}
   public Deque(){
	   this.first =null;
	   this.last =null;
	   totalItems =0;// construct an empty deque
   }
   public boolean isEmpty() {
	   return size()==0;// is the deque empty?
   }
   public int size(){
	   return this.totalItems;// return the number of items on the deque
   }
   public void addFirst(Item item){// add the item to the front
	   if(item ==null)
		   throw new NullPointerException();
	   if(isEmpty()){
		   Node node = new Node(item);
		   this.first = node;
		   this.last = first;		   
	   }
	   else{
		   Node node = new Node(item);
		   node.next =this.first;
		   this.first.before = node;
		   this.first = node;
		   
	   }
	   this.totalItems++;
   }
   public void addLast(Item item){// add the item to the end
	   if(item ==null)
		   throw new NullPointerException();
	   if(isEmpty()){
		   Node node = new Node(item);
		   this.first = node;
		   this.last = first;	
	   }
	   else{
		   Node node = new Node(item);
		   this.last.next = node;
		   node.before =this.last;		   
		   this.last = node; 
	   }
	   this.totalItems++;
   }
   public Item removeFirst(){
	   Node node = this.first;
	   if(totalItems==0){				
		   throw new NoSuchElementException();
	   }
	   if(totalItems==1){
		   this.first =null;
		   this.last =null;// remove and return the item from the front
	   }
	   else{
		   this.first.next.before =null;
		   this.first = first.next;
	   }
	   totalItems--;
	   node.next =null;
	   return node.item;
   }
   public Item removeLast(){// remove and return the item from the end
	   Node node = this.last;
	   if(totalItems==0){				
		   throw new NoSuchElementException();
	   }
	   if(totalItems==1){
		   this.first =null;
		   this.last =null;
	   }
	   else{
		   this.last.before.next =null;
		   this.last = last.before;
	   }
	   totalItems--;
	   node.next =null;
	   return node.item;
	   
   }
   public Iterator<Item> iterator(){
	   return new Iterato();// return an iterator over items in order from front to end
   }
   public static void main(String[] args){
	    Deque<Integer> d = new Deque<Integer>();        
        System.out.println("size: " + d.size());        
        d.addLast(5);
	   // unit testing
        for(int x:d){
        	System.out.print(x + " ");
        }
        System.out.println();
        System.out.println(d.size());
   }
}