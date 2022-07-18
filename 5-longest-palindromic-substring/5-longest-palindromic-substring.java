/*
explanation: longest palindrome finder using expand string algorithm! the algorithm works by picking each index in string and expanding string from that position! so assuming it to be the center/centers of palindrome then the max length of expansion is recorded and the cut string is subsequently returned.

testcase: babad -> Works

Time & Space Complexity: O(n^2) & O(1): Time complexity is quadratic because expand string function takes linear time, which under another linear loop makes it quadratic time complexity! Also space complexity is constant due to the use of countable variables in program!
*/

class Solution {
    public int expandString(String s, int i, int j){
        while(i >= 0 && j<s.length() && s.charAt(i)==s.charAt(j)){
            i--; j++;
        }
        return j - i -1;
    }
    public String longestPalindrome(String s) {
        int start = 0;
        int end = 0;
        
        for(int i=0; i<s.length(); i++){
            int exp1 = expandString(s, i, i);
            int exp2 = expandString(s, i, i+1);
            
            int exp = Math.max(exp1, exp2);
            if((end - start)+1 < exp){
                start = i - ((exp-1)/2);
                end = i + (exp/2);
            }
        }
        return s.substring(start, end+1);
    }
}