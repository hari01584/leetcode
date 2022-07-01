/*
explanation: as observed by pattern, the maximum decibinary number needed is the maximum of each number in string n, so the function breaks each digits, converts it into integer and find the maximum of everything!

testcase:
32 -> Works

Time & Space Complexity: O(n) and O(1): A linear loop is used therefore time complexity is O(n), also constant amount of variables are used therefore space complexity is O(1)
*/

class Solution {
    public int minPartitions(String n) {
        int dbn = 0;
        
        for(int i=0; i<n.length(); i++){
            int c = n.charAt(i) - '0';
            dbn = Math.max(dbn, c);
        }
        
        return dbn;
    }
}