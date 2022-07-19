/*
explanation: longest common prefix using binary searching and common string utility! It works by diving string halfly each time and checking if our string upto mid index can act as common string for all the other string!

testcase: ["flower","flow","flight"] -> Works

Time & Space Complexity: O(nlogn) & O(1): Since at each iteration we half the string therefore time complexity is logn, also since a linear time complexity function isCommon is used inside the loop therefore time complexity is O(nlogn), also since countable variables are used therefore space complexity is O(1)!
*/

class Solution {
    public boolean isCommon(String[] strs, int count){
        String s = strs[0].substring(0, count);
        // System.out.println(" "+s);
        for(int i=0; i<strs.length; i++){
            if(!strs[i].startsWith(s)) return false;
        }
        return true;
    }
    public String longestCommonPrefix(String[] strs) {
        String small = strs[0];
        for(int i=1; i<strs.length; i++){
            String s = strs[i];
            if(s.length() < small.length()) small=s;
        }
        
        int start = 0;
        int end = small.length();
        while(start <= end){
            int mid = (start+end)/2;
            if(isCommon(strs, mid)){
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        return small.substring(0, (start+end)/2);
    }
}