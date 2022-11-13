/*
explanation: reverse string using strip and insert method, the program works by first splitting the string on space character, then it iterates over the array backwards and insert each valid word (not '') to the final ans string (with space), doing so we finally remove the last redundant space and return the answer.\

testcase: "the sky is blue" -> Works

Time & Space Complexity: Time complexity of this program is linear since we iterate and split on the string, also the space complexity is also linear since we store an answer variable with max word length equal to given string s.
*/
class Solution {
    public String reverseWords(String s) {
        s = s.strip();
        
        String ans = "";
        String[] strs = s.split(" ");
        for(int i=strs.length-1; i>=0; i--){
            String st = strs[i];
            if(st == "") continue;
            ans += st + " ";
        }
        
        return ans.substring(0,ans.length()-1);
    }
}