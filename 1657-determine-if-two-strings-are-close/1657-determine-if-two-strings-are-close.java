/*
explanation: determine two strings are close by logic and mathematic operators! First of all the first thing to notice is that there is no change of overall characters frequency, ie if there is 4 times character a, then for the words to be close there must be 4 times some other character in word2, therefore we simply create frequency hashmaps, put value frequency in list, sort and compare them to find if two strings are close or not!

testcase:
"abc"
"bca" -> Works

Time & Space Complexity: O(n) & O(n): is linear since one time loops are used, also space complexity is also linear because of constant amount of space required!
*/
class Solution {
    
    public boolean closeStrings(String word1, String word2) {
        if(word1.length() != word2.length()) return false;
        
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();

        for(int i=0; i<word1.length(); i++){
            char c = word1.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        
        for(int i=0; i<word2.length(); i++){
            char c = word2.charAt(i);
            map2.put(c, map2.getOrDefault(c, 0)+1);
        }
        
        if (!map.keySet().equals(map2.keySet())) {
            return false;
        }
        
        List<Integer> list = new ArrayList<Integer>(map.values());
        List<Integer> list2 = new ArrayList<Integer>(map2.values());
        
        Collections.sort(list);
        Collections.sort(list2);


        return list.equals(list2);
    }
}