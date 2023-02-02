/*
explanation: verify alien dictionary using word sorting, the program works by writing into comparator and checking if the string is sorted step by step, ie word[i] > word[i-1], this happens for each iteration until we return our value.

testcase:
["hello","leetcode"]
"hlabcdefgijkmnopqrstuvwxyz" -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of this algorithm is linear, due to the use of custom comparator and linear loops required throughout the program!
*/ 
class Solution {
    class Word implements Comparable<Word>{
        String word;
        HashMap<Character, Integer> order;
        
        Word(String w, HashMap<Character, Integer> o){
            word = w;
            order = o;
        }
        
        @Override
        public String toString(){
            return "{" + word + " " + order + "}";
        }
        
        public int compareTo(Word o){
            // if(this.word.equals(o.word)) return this.word.length() - o.word.length();
            
            for(int i=0; i<Math.min(this.word.length(), o.word.length()); i++){
                int l1 = order.get(this.word.charAt(i));
                int l2 = order.get(o.word.charAt(i));
                
                if(l1 == l2) continue;
                return l1 - l2;
            }
            
            return this.word.length() - o.word.length();
        }
        
    }
    
    public boolean isAlienSorted(String[] words, String order) {
        HashMap<Character, Integer> ord = new HashMap<>();
        
        for(int i=0; i<order.length(); i++){
            ord.put(order.charAt(i), i);
        }
        
        Word[] wds = new Word[words.length];
        for(int i=0; i<wds.length; i++){
            wds[i] = new Word(words[i], ord);
        }
        
        for (int i = 0; i < wds.length - 1; i++) {
            if (wds[i].compareTo(wds[i + 1]) > 0)
                return false;
        }
        return true;
    }
}