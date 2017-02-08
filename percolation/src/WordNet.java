import java.util.HashMap;
import java.util.Map;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WordNet {
	private Map<Integer, String> id2SynsetDefinition;
	private Map<String, Bag<Integer>> synset2Ids;
	private SAP sa;
   // constructor takes the name of the two input files
   public WordNet(String synsets, String hypernyms){
       id2SynsetDefinition = new HashMap<Integer,String>();
       synset2Ids = new HashMap<String, Bag<Integer>>();
       addSynsets(synsets, id2SynsetDefinition);
       Makesap(hypernyms);
	   
   }

   // returns all WordNet nouns
   public Iterable<String> nouns(){
	   return synset2Ids.keySet();
   }

   // is the word a WordNet noun?
   public boolean isNoun(String word){
	   return synset2Ids.containsKey(word);
   }

   // distance between nounA and nounB (defined below)
   public int distance(String nounA, String nounB){
	  if (!isNoun(nounA) || !isNoun(nounB)) throw new java.lang.IllegalArgumentException("No such nouns in WordNet!");
	  return sa.length(synset2Ids.get(nounA), synset2Ids.get(nounA));
   }

   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
   // in a shortest ancestral path (defined below)
   public String sap(String nounA, String nounB){
	   if (!isNoun(nounA) || !isNoun(nounB)) throw new java.lang.IllegalArgumentException("No such nouns in WordNet!");
	   int i = sa.ancestor(synset2Ids.get(nounA), synset2Ids.get(nounA));
	   return id2SynsetDefinition.get(i).split(",")[0];
   }
   private void addSynsets(String synsets, Map<Integer,String> id2SynsetDefinition){
	   In in = new In(synsets);
	   while(in.hasNextLine()){
		   String c = in.readLine();
		   String[] a = c.split(",");
		   for(int i=0;i<a.length;i++){
			   a[i] = a[i].trim();
		   }   
		   int b = Integer.parseInt(a[0]);
		   String s = a[1] + "," + a[2];
		   id2SynsetDefinition.put(b, s);
		   String synonyms[] = a[1].split(" ");
	         for (int i = 0; i < synonyms.length; i++) {
	            synonyms[i] = synonyms[i].trim();
	            Bag<Integer> bag = synset2Ids.get(synonyms[i]);
	            if (bag == null) {
	               Bag<Integer> newBag = new Bag<Integer>();
	               newBag.add(b);
	               synset2Ids.put(synonyms[i], newBag);
	            }
	            else {
	               bag.add(b);
	            }
	         }
	      }
		   
	   }
   private void Makesap(String hypernyms){
	   In in = new In(hypernyms);
	   Digraph d = new Digraph(id2SynsetDefinition.size());
	   while(in.hasNextLine()){
		   String s = in.readLine();
		   String[] a = s.split(",");
		   for(int i=0;i<a.length-1;i++){
			   a[i]=a[i].trim();
			   a[i+1]= a[i+1].trim();
			   d.addEdge(Integer.parseInt(a[0]), Integer.parseInt(a[i+1]));
		   }
	   }
		   if(!noRoot(d)) {
		         throw new java.lang.IllegalArgumentException("Not rooted DAG!");
		   }
		   sa = new SAP(d);
   }
   private boolean noRoot(Digraph d){
	   DirectedCycle dc = new DirectedCycle(d);
	      if (dc.hasCycle()) {
	         return false;
	      }
	      int r=0;
	      for(int i =0; i<d.V();i++){
	    	  if(!d.adj(i).iterator().hasNext()) r++;
	      }
	      if(r!=1){
	    	  return false;
	      }
	      return true;
   }

   // do unit testing of this class
   public static void main(String[] args) {
	      WordNet wn = new WordNet(args[0], args[1]);
	      for (String s : wn.nouns()) {
	         StdOut.println(s);
	      }
	      while (!StdIn.isEmpty()) {
	         String nounA = StdIn.readLine();
	         String nounB = StdIn.readLine();
	         int distance   = wn.distance(nounA, nounB);
	         String ancestor = wn.sap(nounA, nounB);
	         StdOut.println("length = " + distance);
	         StdOut.println("ancestor = " + ancestor);
	      }
	   }

}