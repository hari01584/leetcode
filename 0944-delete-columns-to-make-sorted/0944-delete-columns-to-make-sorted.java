/*
explanation: min deletion by looping over each string and checking the order of characters! The program works by storing previous characters and using it for next iterations!

testcase: ["a", "b"]

Time & Space Complexity: O(n^2) & O(n): Since two loops are used thf. tc is quadratic and because an array of length size is required therefore sc. is linear@
*/
class Solution {
    public int minDeletionSize(String[] strs) {
        int cols = strs[0].length();
        char[] row = new char[cols];
        int ans = 0;
        for(String str : strs){
            // Check each columns in order!
            for(int i=0; i<cols; i++){
                char cur = str.charAt(i);
                char prev = row[i];
                if(row[i] == '0') continue;
                if(cur < prev){
                    // Bad case!
                    ans++;
                    row[i] = '0';
                } else {
                    row[i] = cur;
                }
            }
        }
        return ans;
    }
}