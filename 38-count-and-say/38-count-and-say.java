/*
explanation: count-and-say by mutating input string into output string by using algorithms to group everything together, main logic is written in function translate, also simple recursive clause is used which calls translate again and again and gives the output.

testcase:
4 -> Works

Time & Space Complexity: O(n^2) & O(n), as we can see that each recursion takes string from previous recursion and returns its mutation, in worst case of mutations we can be returned with double length string, hence time complexity becomes 2^n, also space complexity is O(n) to maintain stacks of recursion in memory!
*/

class Solution {
    public String translate(String input){
        String r = "";
        int count=1;
        char c = input.charAt(0);
        
        for(int i=1; i<input.length(); i++){
            char ch = input.charAt(i);
            if(ch != c){
                // different string, split and reset
                r += count+""+c;
                count=1;
                c = ch;
            }
            else count++;
        }
        r += count+""+c;
        
        return r;
    }
    public String countAndSay(int n) {
        if(n==1) return "1";
        
        return translate(countAndSay(n-1));
    }
}