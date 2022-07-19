/*
explanation: string to integer conversion using the algorithm to parse the string character by character and building up the solution step-by-step! for each character it multiplies the number by 10 and adds the character-converted-digit! also maintains a negative flag that multiplies the number by -1 if negative and returns it!

testcase: 42
for 42, we take 4, multiply initial number (0) by 10 (=10) and sums it! therefore new number is 4!

for next 2, we multiply initial by 10, ie 4*10 = 40 and adds 2 in the number, therefore giving 42!

Time & Space Complexity: O(n) & O(1): time complexityi is linear since we need to loop over each character to get answer, also space complexity is constant since countable variables are used in program!
*/

class Solution {
    public int myAtoi(String s) {
        s = s.trim();
        
        if(s.length() == 0) return 0;

        int i=0;
        boolean isNegative = false;
        if(s.charAt(i) == '+' || s.charAt(i) == '-'){
            if(s.charAt(i) == '-') isNegative = true;
            i++;
        }
        
        long num = 0;
        for(;i<s.length();i++){
            char c = s.charAt(i);
            if(c<'0'||c>'9') break;
            
            num = num*10 + c-'0';
            if(num > Integer.MAX_VALUE) return isNegative ? Integer.MIN_VALUE:Integer.MAX_VALUE;
        }
        
        if(isNegative) num *= -1;
        return (int)num;
    }
}