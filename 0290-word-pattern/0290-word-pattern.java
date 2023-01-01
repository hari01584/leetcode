/*
explanation: word pattern by hashmap and cache! the algorithm works by iterating through each element and caching while comparing it!

testcase: "abba", "dog cat cat dog" -> Works

Time & Space Complexity: O(n^2) & O(n): Time complexity quadratic since containsValue itself takes linear time which under another linear loop gives quadratic time complexity!
*/
class Solution {
    public boolean wordPattern(String pattern, String s) {
        HashMap<Character, String> map = new HashMap<>();
        String[] arr = s.split(" ");
        if(pattern.length() != arr.length) return false;
        for(int i=0; i<pattern.length(); i++){
            char c = pattern.charAt(i);
            String str = arr[i];
            if(map.containsKey(c)){
                // Verify
                if(!map.get(c).equals(str)) return false;
            } else {
                if(map.containsValue(str)) return false;
                // Assign
                map.put(c, str);
            }
        }
        
        return true;
    }
}