/*
explanation: implementation of strStr using KMP (Knuth Morris Pratt) Pattern Searching algorithm! the program in general follows the implementation of https://www.youtube.com/watch?v=GTJr8OvyEVQ (Tushar Roy's Video!) comments in various places are inserted to make the code easier to understand!

testcase:
"hello"
"ll" -> Works

Time & Space Complexity: O(n) & O(n): We already know that kmp algorithm has linear time and space complexity, therefore the time and space complexity of this program is also the same!
*/

class Solution {
    public int[] preprocess(String pattern){
        // Preprocess function, ie generate the pattern lps array!
        int[] lps = new int[pattern.length()];
        boolean isLastMatch = false;
        int i = 0;
        int j = 1;
        lps[0] = 0;
        while(j < pattern.length()){
            // System.out.println(pattern.charAt(i)+" "+pattern.charAt(j));
            if(pattern.charAt(j) == pattern.charAt(i)){
                lps[j] = i+1;
                // System.out.println("set "+j+" "+lps[j]);
                j++; i++;
                isLastMatch = true;
            }
            else{
                if(isLastMatch){
                    // System.out.println("Rewind");
                    while(i>0 && pattern.charAt((i=lps[--i])) != pattern.charAt(j));
                    // System.out.println("Rew "+pattern.charAt(i)+" "+pattern.charAt(j)+" "+i+" "+j);

//                     if(pattern.charAt(j) == pattern.charAt(i)){
//                         lps[j++] = i + 1;
//                         i++;
//                     }
//                     else{
//                         lps[j++] = 0;
//                     }
                    
                    isLastMatch = false;
                }
                else lps[j++] = 0;
            }
        }
        
        // System.out.println(Arrays.toString(lps));
        
        return lps;
    }
    
    public int strStr(String haystack, String needle) {
        int[] lps = preprocess(needle);
        System.out.println(Arrays.toString(lps));
        
        int i=0;
        int j=0;
        
        boolean lastmatch = false;
        
        while(i<haystack.length()){
            // System.out.println("comparing "+haystack.charAt(i)+" with "+needle.charAt(j)+" "+i+" "+j);
            
            if(haystack.charAt(i)==needle.charAt(j)){
                i++;
                j++;
                lastmatch = true;
                
                if(j>=needle.length()){
                    // System.out.println("match found");
                    return i - needle.length();
                }
            }
            else{
                if(lastmatch){
                    j = lps[j-1];
                    while(j>0 && haystack.charAt(i)!=needle.charAt(j)){
                        j = lps[j-1];
                    }
                }
                else{
                    i++;
                }
                lastmatch = false;
            }
        }
        
        return -1;
    }
}