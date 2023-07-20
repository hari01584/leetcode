/*
explanation: largest consequtive sequence using hashmap! The algorithm works by first creating map of frequency and then
using this map to recursively check for next element if possible! Also the optimization is done using hashmap which stores
the count for maximum next sequence possible (see func recurmax)!
*/
class Solution {
    HashMap<Integer, Integer> nextseq;

    int recurmax(int num) {
        // Check if num exist in map
        int next_sequence_count = nextseq.getOrDefault(num, -1);
        if (next_sequence_count == -1) return 0;
        if (next_sequence_count == 0) {
            // Create cache for first time
            // This being zero means that element is not initilzed at all, find cache for next element first!
            int next_max = recurmax(num+1);
            int mymax = next_max + 1;
            nextseq.put(num, mymax);
            return mymax;
        }
        return next_sequence_count;
    }
    
    public int longestConsecutive(int[] nums) {
        int longestSubseq = 0; 

        nextseq = new HashMap<Integer, Integer>();
        for (int i=0; i<nums.length; i++) {
            nextseq.put(nums[i], 0);
        }
        
        for (int i=0; i<nums.length; i++) {
            recurmax(nums[i]);
        }
                
        // Finally read element back
        for (Map.Entry<Integer, Integer> entry : nextseq.entrySet()) {
            longestSubseq = Math.max(longestSubseq, entry.getValue());
        }
        
        return longestSubseq;
    }
}