/*
explanation: zigzag pattern using mathematics, the program works by defining two variables j1 and j2 (jump1 and jump2) and uses the variable to find level jumping each level and traverse as such, the formulas for j1 and j2 will be j1 = 2n - 2 - 2l and j2 = 2l, The traversal works by first jumping first by j1 elements and then by j2 elements.

testcase:
"PAYPALISHIRING", 3 -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity is linear, time complexity is linear due to traversal of each character of string once, also space complexity is linear due to the storage of new string!
*/
class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1) return s;
        
        StringBuilder sb = new StringBuilder();
        // int[][] matri= new int[numsRows][]
        for(int l=0; l<Math.min(numRows, s.length()); l++){
            int j1 = 2*numRows - 2 - 2*l;
            int j2 = 2*l;
            
            int index = l;
            sb.append(s.charAt(index));
            while(index < s.length()){
                // Forward loop
                if(index + j1 >= s.length()){
                    break;
                }
                
                index += j1;
                if(j1 != 0)
                    sb.append(s.charAt(index));
                
                if(index + j2 >= s.length()){
                    break;
                }
                
                index += j2;
                if(j2 != 0)
                    sb.append(s.charAt(index));
            }
        }
        
        return sb.toString();
    }
}