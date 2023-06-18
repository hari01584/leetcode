//{ Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG{
    static class FastReader{ 
        BufferedReader br; 
        StringTokenizer st; 
  
        public FastReader(){ 
            br = new BufferedReader(new InputStreamReader(System.in)); 
        } 
  
        String next(){ 
            while (st == null || !st.hasMoreElements()){ 
                try{ st = new StringTokenizer(br.readLine()); } catch (IOException  e){ e.printStackTrace(); } 
            } 
            return st.nextToken(); 
        } 
  
        String nextLine(){ 
            String str = ""; 
            try{ str = br.readLine(); } catch (IOException e) { e.printStackTrace(); } 
            return str; 
        } 

        Integer nextInt(){
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws IOException
    {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);
        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            String sentences[] = new String[n];
            int times[] = new int[n];
            for(int i = 0; i < n; i++){
                sentences[i] = sc.nextLine();
                times[i] = sc.nextInt();
            }
            AutoCompleteSystem obj = new AutoCompleteSystem(sentences, times);
            int q = sc.nextInt();
            for(int i = 0; i < q; i++){
                String query = sc.nextLine();
                StringBuilder qq = new StringBuilder();
                for(int j = 0; j < query.length(); j++){
                    char x = query.charAt(j);
                    qq.append(String.valueOf(x));
                    ArrayList<String> suggestions = obj.input(x);
                    if(x == '#')
                        continue;
                    out.print("Typed : \"" + qq.toString() + "\" , Suggestions: \n");
                    for(String y : suggestions)
                        out.print(y + "\n");
                }
            }
        }
        out.flush();
    }
}
// } Driver Code Ends


//User function Template for Java
class E implements Comparable<E> {
    String sentence;
    int pos;
    int times;
    
    E(String s, int t) {
        sentence = s;
        pos = 0;
        times = t;
    }
    
    char getChar() {
        if (pos >= sentence.length()) return '#';
        
        return sentence.charAt(pos);
    }
    
    @Override
    public int compareTo(E b) {
        int r1 = b.times - this.times;
        if (r1 != 0) return r1;
        
        return sentence.compareTo(b.sentence);
    }
    
    @Override
    public String toString() {
        return sentence + ": " + times + " at pos " + getChar();
    }
    
    public E clone() {
        E c = new E(sentence, times);
        return c;
    }
}

class AutoCompleteSystem{
    PriorityQueue<E> queue = new PriorityQueue<E>();
    PriorityQueue<E> corpus = new PriorityQueue<E>();
    String mine = "";
    
    public AutoCompleteSystem(String sentences[], int times[]){
        for (int i=0; i<sentences.length; i++) {
            queue.add(new E(sentences[i], times[i]));
            corpus.add(new E(sentences[i], times[i]));
        }
    }
    
    void updateCorpus() {
        Boolean update = false;
        PriorityQueue<E> nc = new PriorityQueue<>();
        while (corpus.size() > 0) {
            E e = corpus.poll();
            if (e.sentence.equals(mine)) {
                e.times++;
                update = true;
            }
            nc.add(e.clone());
        }
        
        if (!update) {
            nc.add(new E(mine, 1));
        }
        
        mine = "";
        
        corpus = new PriorityQueue<>(nc);
    }

    ArrayList<String> input(char c){
        if (c == '#') {
            updateCorpus();
            
            // Finish search, start new one!
            queue = new PriorityQueue<>();
            for (E e : corpus) {
                if (e.sentence.equals(mine)) {
                    e.times++;
                }
                queue.add(e.clone());
            }
            return new ArrayList<>();
        }
        
        mine = mine + "" + c;
        
        PriorityQueue<E> filter = new PriorityQueue<>();
        // Filter on basis of this char
        while (queue.size() > 0) {
            E e = queue.poll();
            if (e.getChar() == c) {
                // Good element, also increase pos
                e.pos++;
                filter.add(e);
            }
        }
        
        queue = new PriorityQueue<>(filter);
        
        ArrayList<String> ans = new ArrayList<>();

        int t = 3;
        while (t > 0 && filter.size() > 0) {
            ans.add(filter.poll().sentence);
            t--; 
        }
        
        return ans;
    }
}