import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
	private WordNet wordnet;
	private int max;
	private String outcast;
   public Outcast(WordNet wordnet){
	   this.wordnet =wordnet;
	   this.outcast = null;
	   this.max = -1;
	   // constructor takes a WordNet object
   }
   public String outcast(String[] nouns){
	   int tempmax =Integer.MIN_VALUE;
	   int c =0;
	   for(int i =0;i<nouns.length;i++){
		   if (!wordnet.isNoun(nouns[i])) throw new java.lang.IllegalArgumentException("No such nouns in WordNet!");
		   for(int j=1;j<nouns.length;j++){
			    c = c+  wordnet.distance(nouns[i], nouns[j]);
		   }
		   if(c>tempmax){
			   tempmax= c;
			   outcast= nouns[i];
		   }
	   }
	   max = tempmax;
	   return outcast;
   }
   public static void main(String[] args) {
	    WordNet wordnet = new WordNet(args[0], args[1]);
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 2; t < args.length; t++) {
	        In in = new In(args[t]);
	        String[] nouns = in.readAllStrings();
	        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
	    }
	}
}
