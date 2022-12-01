/*
explanation: determine if string halves are equal by counting vowels from 0 to mid, and mid to end and hence finding for equality.

testcase: "book" -> Works

Time & Space Complexity: O(n) & O(1): Time complexity of this program is linear since we iterate over each character once, and space complexity is constant since countable variables are used!
*/

class Solution {
    public int countVowel(String s, int start, int end){
        int voe=0;
        for(int i=start; i<end; i++){
            char c = s.charAt(i);
            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'){
                voe++;
            }
        }
        return voe;
    }
    
    public boolean halvesAreAlike(String s) {
        return countVowel(s, 0, s.length()/2) == countVowel(s, (s.length()/2), s.length());
    }
}