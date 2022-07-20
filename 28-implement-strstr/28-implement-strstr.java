/*
explanation: needle in a haystack using Z algorithm! It works on the principal of z pattern search algorithm (ref. https://www.geeksforgeeks.org/z-algorithm-linear-time-pattern-searching-algorithm/) where we try to create a Z array of needle$haystack string and finds the places where the value of element in Z array is equal to length of needle! When we find it we will return the estimated position of this element in original haystack by substracting length of extra characters (ie len(needle)+1)!

testcase:
"hello"
"ll" -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of Z algorithm is linear therefore our program has the same TC and SC!
*/

class Solution {
    int[] calcZ(String str){
        int[] ret = new int[str.length()];
        ret[0] = 0;
        int L = 0;
        int R = 0;
        
        for(int i=1; i<str.length(); i++){
            if(i > R){
                L = R = i;
                while(R < str.length() && str.charAt(R) == str.charAt(R-L)){
                    R++;
                }
                ret[i] = R - L;
                R--;
            }
            else{
                // Windows
                // System.out.println("Window f "+L+" "+R);
                int pcache = ret[i-L];
                if(i + pcache > R){
                    // System.out.println("Recalc windows "+str.charAt(i));
                    L = i;
                    while(R < str.length() && str.charAt(R) == str.charAt(R-L)){
                        R++;
                    }
                    ret[i] = R - L;
                    R--;
                } 
                else {
                    ret[i] = pcache;
                }
            }
            
            // System.out.println(i+" "+L+" "+R);
        }
        
        // System.out.println(Arrays.toString(ret));
        return ret;
    }
    public int strStr(String haystack, String needle) {
        int[] z = calcZ(needle+"$"+haystack);
        for(int i=0; i<z.length; i++){
            if(z[i] == needle.length()){
                return i - needle.length() - 1;
            }
        }
        return -1;
    }
}