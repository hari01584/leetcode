/*
explanation: subarray sum equal k using count hashmaps! The algorithm works by calculating prefix sum array
and using it to systematically scan for subarrays, the principle behind is that we subtract k from our current
prefix sum and find if it has occoured any previously, if yes then we add counts of appearance of previous element

testcase: [1,1,1], k=2

Time & Space Complexity: O(n) & O(n): Time complexity is linear since only one iteration is performed, space complexity
is linear due to storing of elements!
*/
class Solution {
    class CountMap<K> {
        HashMap<K, Integer> base;
        
        CountMap() {
            base = new HashMap<>();
        }
        
        void increase(K key) {
            // Increase count for this item!
            Integer count = base.getOrDefault(key, 0);
            base.put(key, count+1);
        }
        
        void decrease(K key) {
            // Increase count for this item!
            Integer count = base.getOrDefault(key, 0);
            if (count <= 0) {
                base.remove(key);
            } else {
                base.put(key, count-1);
            }
        }
        
        int find(K key) {
            return base.getOrDefault(key, 0);
        }
    }

    public int subarraySum(int[] nums, int k) {
        int ans = 0;

        // Create countmap for storing previous counts!
        CountMap<Integer> mycounts = new CountMap();

        mycounts.increase(0); // Add 0 to count for case when whole subarray should be considered for sum equals k
        mycounts.increase(nums[0]);
        
        if (nums[0] == k) {
            ans++; // Edge case, first element contributes to count
        }

        // Iterate over each element
        for (int i=1; i<nums.length; i++) {
            // Find prefix sum array
            nums[i] += nums[i-1];
                        
            // Check using this sum, if any previous element can form subarray with sum equals k
            int arrays_can_be_formed = mycounts.find(nums[i] - k);
            ans += arrays_can_be_formed;
            
            mycounts.increase(nums[i]);
        }
        
        return ans;
    }
}