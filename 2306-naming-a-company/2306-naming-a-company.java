/*
explanation: Using hashmaps to create count array which stores the reference to first character and its jinxed with other 26 to find all counts! And then using them to generate final answer!

testcase: ["coffee","donuts","time","toffee"] -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity is linear due to single n loop!
*/
class Solution {
    class C {
        int[] counts;
        C(){
            counts = new int[26];
        }
        
        void plusplus(char c){
            counts[c-'a']++;
        }
        
        int getcnt(char c){
            return counts[c-'a'];
        }
        
        @Override
        public String toString(){
            return Arrays.toString(counts);
        }
    }
    
    public long distinctNames(String[] ideas) {
        long ans = 0;
        HashMap<String, Boolean> _isthere = new HashMap<>();
        
        for(int i=0; i<ideas.length; i++){
            _isthere.put(ideas[i], true);
        }
        
        HashMap<Character, C> valid = new HashMap<>();
        for(int i=0; i<ideas.length; i++){
            String st = ideas[i];
            char first = st.charAt(0);
            String subst = st.substring(1, st.length());
            
            C curr = valid.getOrDefault(first, new C());
            
            for(int z=0; z<26; z++){
                char t = (char)(z + 'a');
                // Build the map!
                if(t == first) continue;
                
                // Check if jinxed with this character will hashmap contain?
                String nst = t + subst;
                if(!_isthere.containsKey(nst)){
                    // Good add entry, ie plusplus
                    curr.plusplus(t);
                }
            }
            
            valid.put(first, curr);
        }
        
        for(Map.Entry<Character, C> entry : valid.entrySet()){
            char c = entry.getKey();
            C val = entry.getValue();
            
            for(int z=0; z<26; z++){
                char t = (char)(z + 'a');
                if(c == t) continue;
                
                int c1 = val.getcnt(t);
                if(c1 > 0){
                    // Check reference for another
                    C oth = valid.getOrDefault(t, new C());
                    int c2 = oth.getcnt(c);
                    ans+=c1*c2;
                }
            }
        }
        
        return ans;
    }
}