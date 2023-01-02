/*
explanation: detect capital using rules! It checks the second character for the validity and compares the string against the whole of characters! Using these sets of rules we can return true or false!

testcase: "USA" -> Works

Time & Space Complexity: O(n) & O(1): Time complexity is linear since we iterate over each character once and space complexity is constant since only countable variables are defined throughout the program!
*/
class Solution {
    public boolean detectCapitalUse(String word) {
        if(word.length() <= 1) return true;
        
        char second = word.charAt(1);
        char lower = '0';
        char upper = '0';
        
        if('a' <= second && second <= 'z'){
            lower = 'a';
            upper = 'z';
        } else {
            lower = 'A';
            upper = 'Z';
        }
        
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(lower <= c && c <= upper){
                
            } else {
                if(i==0 && lower=='a') continue;
                return false;
            }
        }
        
        
        return true;
    }
}