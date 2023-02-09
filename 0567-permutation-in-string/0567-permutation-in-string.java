/*
explanation: permutation in string using sliding window! The program works by first defining sliding window of length s1 and sliding it over s2 to check if frequencies of elements match or not, at any point if all the character in both window are same then we return true!

testcase: "ab", "eidbaooo" -> works

Time & Space Complexity: O(n) & O(n): Time and space complexity of this program is linear, due to linear loops required and storing of character in hashmap!
*/
class Solution {
    public boolean checkEquals(HashMap<Character, Integer> m1, HashMap<Character, Integer> m2){
        return m1.equals(m2);
    }
    
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) return false;
        
        int w = s1.length();
        
        // form the hasmap of target freq char!
        HashMap<Character, Integer> target = new HashMap<>();
        for(int i=0; i<w; i++){
            char c = s1.charAt(i);
            target.put(c, target.getOrDefault(c, 0)+1);
        }
        
        HashMap<Character, Integer> freq = new HashMap<>();
        for(int i=0; i<w; i++){
            char c = s2.charAt(i);
            freq.put(c, freq.getOrDefault(c, 0)+1);
        }

        // Calculate and check equality for first window!
        if(checkEquals(freq, target)){
            return true;
        }
        
        for(int i=w; i<s2.length(); i++){
            char addition = s2.charAt(i);
            char removal = s2.charAt(i-w);
            
            freq.put(addition, freq.getOrDefault(addition, 0)+1);
            freq.put(removal, freq.getOrDefault(removal, 0)-1);
            
            if(freq.getOrDefault(removal, 0) <= 0){
                freq.remove(removal);
            }
            
            if(checkEquals(freq, target)){
                return true;
            }
        }

        return false;
    }
}