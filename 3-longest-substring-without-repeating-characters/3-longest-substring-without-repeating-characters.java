/*
explanation: get longest substring without repeating character using hashmaps and having a variable storing max length of string so far, the string characters is added to map sequentially and if same character is found to already exist in hashmap then it's index is recorded and it is popped off and all the subsequent elements are also removed and the length of this slice of string is considered for being a longest length string, this process continues while scanning full string and the largest value is stored.

testcase:
abcabcbb -> Works
bbbbb -> Works

Time & Space Complexity: O(n^2) and O(n): Since a maximum of two loops can be run for worst case, the time complexity is O(n^2), and since one hashmap is used to store characters the space complexity is O(n)

*/


class Solution {
    public void unsetHM(int st, int end, HashMap<Character,Integer> map, String s){
        // System.out.println("Unsetting "+st+" "+end);
        for(int i=st; i<=end; i++){
            map.remove(s.charAt(i));
        }
    }
    
    public void loadedCharSet(HashMap<Character,Integer> map){
        for (Map.Entry<Character,Integer> entry : map.entrySet())
            System.out.println("K = " + entry.getKey() +
                             ", V = " + entry.getValue());
    }
    
    int gst = 0;
    int gend = 0;
    public void saveLOG(int st, int end){
        if((end-st) > (gend - gst)){
            gend = end;
            gst = st;
        }
        
        // System.out.println(gst+" "+gend);
    }
    
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> map=new HashMap<Character,Integer>();    
        List<String> chances = new ArrayList<String>();  
        int cst = 0;
        
        for(int i=0; i<s.length(); i++){
            // map.put();
            char c = s.charAt(i);
            if(map.containsKey(c)){
                int idx = map.get(c);
                unsetHM(cst, idx, map, s);
                saveLOG(cst, i);
                cst = idx+1;
                map.put(c, i);
                
                // loadedCharSet(map);
            }
            else{
                map.put(c, i);
                // System.out.println(c+" "+i);
            }
            
        }
        
        saveLOG(cst, s.length());

        return gend-gst;
    }
}