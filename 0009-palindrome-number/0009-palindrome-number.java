/*
explanation: largest palindrome by reversing number, the algorithm works by taking a number, reversing it and finally compare this new number with reversed number!

testcase: 121 -> Works

Time & Space Complexity: O(logn) & O(1): Time complexity is logn due to number divided by 10 each iteration, also space complexity is linear due to countable extra variables required.
*/
class Solution {
    public boolean isPalindrome(int x) {
        if (x<0) return false;
        
        int rev = 0;
        int k = x;
        while (k > 0) {
            int digit = k%10;
            rev = (rev * 10) + digit;
            k = k/10;
        }
        
        return x == rev;
    }
}