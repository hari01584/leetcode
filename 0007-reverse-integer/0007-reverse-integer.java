/*
explanation: reverse integer using loop and shifting digits! The program works by finding each digit of number and then adding
it to another digit (by multiplying number by 10 and adding digit), doing so for each digit we get the reversed number!

also, this uses a trick defined in https://stackoverflow.com/a/45873572 to find overflow of numbers.

testcase: 123 -> 321 Works

Time & Space Complexity: O(logn) & O(1): Time complexity is log due to traversal required for each digit, space complexity is constant due to countable extra variables required in program!
*/
class Solution {
    public int reverse(int x) {
        Boolean isneg = false;
        if (x < 0) {
            x = -x; // Convert to positive
            isneg = true;
        }
        
        int newnum = 0;
        int k = x;
        while (k > 0) {
            int dig = k%10;
            long c = newnum * 10;
            if (newnum == 0 || c/newnum == 10) {
                newnum = (int)c + dig;
            } else {
                return 0;
            }
                        
            k = k/10;
        }
        
        if (isneg) {
            newnum = -newnum;
        }
        
        return newnum;
    }
}