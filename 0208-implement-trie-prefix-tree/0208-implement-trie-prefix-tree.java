/*
explanation: trie prefix tree data structure using array node childs and end of word boolean variable! Trie datastructure explanation and how it works: https://www.geeksforgeeks.org/trie-insert-and-search/

testcase: ["Trie","insert","search","search","startsWith","insert","search"]
[[],["apple"],["apple"],["app"],["app"],["app"],["app"]] -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity are both linear since we iterate over n word characters and each word takes space in memory!
*/

class TNode {
    TNode[] childs;
    boolean isEndOfWord;
        
    TNode(){
        childs = new TNode[26];
        isEndOfWord = false;
    }
    
    public TNode setOrReturn(char c){
        TNode child = childs[c-'a'];
        if(child == null){
            childs[c-'a'] = new TNode();
        }
        return childs[c-'a'];
    }
}

class Trie {
    TNode root;
    
    public Trie() {
        // Init head pointer
        root = new TNode();
    }
    
    public void insert(String word) {
        TNode t = root;
        for(int i=0; i<word.length(); i++){
            // System.out.println("Inserting "+word.charAt(i));
            
            char c = word.charAt(i);
            t = t.setOrReturn(c);
        }
        
        // Last node reached, set end=true
        t.isEndOfWord = true;
    }
    
    public boolean search(String word) {
        TNode t = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(t.childs[c-'a'] == null) return false;
            t = t.childs[c-'a'];
        }

        return t.isEndOfWord;
    }
    
    public boolean startsWith(String word) {
        TNode t = root;
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(t.childs[c-'a'] == null) return false;
            t = t.childs[c-'a'];
        }
        
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */