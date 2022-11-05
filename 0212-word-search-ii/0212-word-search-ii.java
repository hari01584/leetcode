/*
explanation: https://leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00)
*/

class TNode {
    // 26 elements
    String word;
    TNode[] childs;
    boolean isEnd;
    
    TNode(){
        isEnd = false;
        
        childs = new TNode[26];
        for (int i = 0; i < 26; i++)
                childs[i] = null;
    }
    
    TNode addNext(char c){
        if(childs[c-'a'] == null)
            childs[c-'a'] = new TNode();
        return childs[c-'a'];
    }
}

class Solution {
    List<String> ans = new ArrayList<>();
    char[][] board;
    TNode root;
    
    int[][] checked;
    
    TNode trieMaker(String[] words){
        TNode root = new TNode();
        
        for(int i=0; i<words.length; i++){
            TNode t = root;
            
            for(int z=0; z<words[i].length(); z++){
                t = t.addNext(words[i].charAt(z));
            }
            
            t.word = words[i];
            t.isEnd = true;
        }
        
        return root;
    }
    
    public boolean outofbounds(int i, int j){
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length) return true;
        return false;
    }
    
    public void dfs(int x, int y, TNode node){
        if(outofbounds(x, y)) return;
        if(checked[x][y] == 1) return;
        
        // find
        if(node.childs[board[x][y]-'a'] == null) return;
        
        node = node.childs[board[x][y]-'a'];
        if(node.isEnd && node.word != null){
            // Found, this could be ans
            ans.add(node.word);
            node.word = null;
        }
        
                
        checked[x][y] = 1;
        
        dfs(x+1, y, node);
        dfs(x-1, y, node);
        dfs(x, y+1, node);
        dfs(x, y-1, node);
        
        checked[x][y] = 0;
    }
    
    public List<String> findWords(char[][] b, String[] words) {
        board = b;
        
        checked = new int[12][12];
        
        root = trieMaker(words);
        
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                // dfs
                dfs(i, j, root);
            }
        }
        
        
        
        return ans;
    }
}