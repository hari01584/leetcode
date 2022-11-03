/*
explanation: longest palindrome by using the principals of greedy and finding words that are in palindrome! The alogirthm doesn't deal with sequence of words but rather finds words that would be in our final palindrome string and thus adds their length to ans! Works by mirrioring string and checking if they are also contained in words list. More explanation with testcase!

testcase: ["lc","cl","gg"] -> Works
It works on principal that initially we have palindromic string of length 0 : ""
We first create frequency map, from words to count number!
now for each word, we reverse it and checks for its instance in word list! The minimum of these counts will be number of words that would be in palindromic string! So for the word "lc", we check the count of word "cl" (because for lc to exist in an palindromic string, there must be cl), now assuming there could be multiple "lc" and "cl", therefore since each "lc" needs one "cl" that's why we will take minimum of counts of "lc" and "cl"! This number will be represent the number of words of "lc" in palindromic string, multiplied by 2 will be the length of characters in palindrome, which, will simply be added to answer!

Also for already palindrome words gg, we can say that if even counts of this word satisfy each other, like for example a palindromic string can contain even number of gg word (or 2 "gg" or 4 "gg") without any problems, so we simply divide the count of word and find the even divided word and simply multiply by 4 for representing character count! Also for special case where there is one palindrome word left (here in this case, for single gg), we can simply at end add 2 to answer since this word can be added in middle of palindrome!

Time & Space Complexity: O(n) & O(n): Since we iterate once only (Without nest) and space is required to maintain word map therefore time and space complexity of program is linear!
*/

class Solution {
    String reverseWord(String key){
        return key.charAt(1) + "" + key.charAt(0);
    }
    public int longestPalindrome(String[] words) {
        // build hash cache
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<words.length; i++){
            map.put(words[i], 1+map.getOrDefault(words[i], 0));
        }
        
        int ans = 0;
        boolean addExtra = false;
        
        // loop over each element and try to add it into our virtual palindrome!
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            
            if(key.charAt(0) == key.charAt(1)){
                // The word itself is palindrome, ie aa, bb, gg
                int ev = value / 2;
                ans += 4 * ev;
                
                if(value%2 != 0) addExtra = true;
            } else {
                // If key is ab, then reverse it and find the max occurence of (ba), the minimum of these counts will be added somewhere in our palindrome string
                // Multiplied by 2 representing two characters added to palindrome for each word! (Since word is 2 charactered)
                ans += 2 * Math.min(value, map.getOrDefault(reverseWord(key), 0));
            }
        }

        if(addExtra) ans+=2;

        
        return ans;
    }
}