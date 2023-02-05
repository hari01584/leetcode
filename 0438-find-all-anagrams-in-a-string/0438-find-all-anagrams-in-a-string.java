/*
explanation: find all analgrams in string using hashmap and sliding window! The program works by maintaining a sliding window that checks in with each element and see if frequency map is equal to target map, if yes then we add this element window to our answer!

testcase:
"cbaebabacd"
"abc" -> Works

Time & Space Complexity: O(n) & O(n): The time and space complexity of this program is linear due to the use of hashmaps and the time required to compare two hashmaps for equals!
*/
class Solution {
    public boolean checkEquals(HashMap<Character, Integer> m1, HashMap<Character, Integer> m2){
        return m1.equals(m2);
    }
    
    public List<Integer> findAnagrams(String s, String p) {
        if(p.length() > s.length()) return new ArrayList<Integer>();
        
        int w = p.length();
        
        // form the hasmap of target freq char!
        HashMap<Character, Integer> target = new HashMap<>();
        for(int i=0; i<w; i++){
            char c = p.charAt(i);
            target.put(c, target.getOrDefault(c, 0)+1);
        }
        
        HashMap<Character, Integer> freq = new HashMap<>();
        
        for(int i=0; i<w; i++){
            char c = s.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0)+1);
        }
        
        List<Integer> ans = new ArrayList<>();
        if(checkEquals(freq, target)){
            ans.add(0);
        }
        
        for(int i=w; i<s.length(); i++){
            char addition = s.charAt(i);
            char removal = s.charAt(i-w);
            
            freq.put(addition, freq.getOrDefault(addition, 0)+1);
            freq.put(removal, freq.getOrDefault(removal, 0)-1);
            
            if(freq.getOrDefault(removal, 0) <= 0){
                freq.remove(removal);
            }
            
            if(checkEquals(freq, target)){
                ans.add(i-w+1);
            }
            
            // System.out.println("+" + addition + "-" + removal);
        }
        
        return ans;
    }
}