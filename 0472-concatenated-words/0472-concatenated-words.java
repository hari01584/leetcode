/*
explanation: concatenated words using recursive greedy! The program works by first building hashmap of string which tells if string is present in array or not, then it recursively iterates over each string and checks using greedy algo the maximum no of substring that can be formed, if this is >1 then we add the string to our answer array!

testcase: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"] -> Works

*/
class Solution {
    HashMap<String, Boolean> map = new HashMap<>();
    
    public int dyfindword(int n, String base, Integer[] dp){
        if(n == base.length()) return 0;
        if(dp[n] != null) return dp[n];
        
        // System.out.println("At "+n);
        int ans = -1;
        for(int i=n+1; i<=base.length(); i++){
            String sb = base.substring(n, i);
            // System.out.println("Checking for "+sb);
            if(!map.containsKey(sb)) continue;
            // System.out.println("Good, contains, next one:");
            // Contains, ie this part is good, go on to next one!
            int further = dyfindword(i, base, dp);
            if(further > -1) return dp[n] = 1 + further;
        }
        
        // System.out.println()
        return dp[n] = -1;
    }
    
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        for(String s : words){
            map.put(s, true);
        }
        
        List<String> ans = new ArrayList<>();
        
        for(String word : words){
            Integer[] dp = new Integer[word.length()+1];
            if(dyfindword(0, word, dp) > 1) ans.add(word);
        }
        // String word = "cat";
        // Integer[] dp = new Integer[word.length()+1];
        // System.out.println("Finding for word "+word+", res:"+dyfindword(0, word, dp));
        
        return ans;
    }
}