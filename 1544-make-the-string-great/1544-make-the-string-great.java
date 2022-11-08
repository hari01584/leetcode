/*
explanation: making a string great using stack method! The program works by first having a stack with each adding each character to it, now if theres already an adjacent character to stack then we will pop the topmost and skip insertion of current char! Finally after going through and adding all the characters we will convert it into string and return the answer!

testcase:
"leEeetcode"
"abBAcC"
"s" -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of this program is linear since we iterate through each character of string and also we can store full string during execution of the program!
*/
class Solution {
    public String makeGood(String s) {
        Stack<Character> st = new Stack<>();
        
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            // Check if adjacent bad
            // System.out.println(c);
            if(!st.empty() && Math.abs(st.peek() - c) == 32){
                // Same char, pop last
                st.pop();
            }
            else {
                st.push(c);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while(!st.empty()){
            sb.insert(0, st.pop());
        }

        return sb.toString();
    }
}