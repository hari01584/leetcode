/*
explanation: 3sum using binary addition algorithm! The program works by first fixing first index 'i', and then applying
binary search styled addition algorithm on remaining sorted array, which finds the element which can be summed to target.

testcase: [-1,0,1,2,-1,-4] -> Works

Time & Space Complexity: O(n^2) & O(n): Time complexity is quadratic due to nested loops whereas space complexity is linear due to answer array stored.
*/
class Solution {
    class R {
        int left;
        int right;
        R(int l, int r) {
            left = l;
            right = r;
        }
    }
    
    // Assumption, array is sorted in ascending order
    R binaryAddition(int left, int right, int target, int[] nums) {
        // Find initial sum of these numbers        
        while (left < right) {
            int sum = nums[left] + nums[right];

            // Check sum
            if (sum == target) {
                // Yay, our answer! return this!
                return new R(left, right);
            }
            else if (sum < target) {
                // Sum is less than our target, ie increase left pointer
                left++;
            } else {
                // Sum is greater than target, decrease left pointer
                right--;
            }
        }
        
        // If we reach here, ie no answer found! No two elements addition can result in target
        // return null
        return null;
    }
    
    // Helper function to quickly create java list out of three elements!
    List<Integer> wrapList(int a, int b, int c) {
        List<Integer> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        return list;
    }
    
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();

        HashMap<Integer, Integer> map = new HashMap<>();
        // 3 Sum using HashMap Optimized approach!
        // First sort array
        Arrays.sort(nums);
        // System.out.println(Arrays.toString(nums));
        // Keeping pointer a as fixed, try binary search on b and c to find
        // pointers for elements!
        int i = 0;
        while (i < nums.length) {
            // Keeping i constant, do binary search between j = i+1..and end - 1
            int j = i + 1; // Starting j
            int k = nums.length - 1; // Starting k
            while (j < k) {
                // Perform and get binary search addition of two elements
                R result = binaryAddition(j, k, -nums[i], nums);
                // If this is not null, then we got our two elements! with index left and right!
                if (result != null) {
                    j = result.left;
                    k = result.right;
                    ans.add(wrapList(nums[i], nums[j], nums[k]));
                    
                    // change indices!
                    while (++j < nums.length && nums[j] == nums[j-1]);
                    while (--k >= 0 && nums[k] == nums[k+1]);
                } else {
                    break;
                }
            }
            
            // Increase i
            while (++i < nums.length && nums[i] == nums[i-1]);
        }
        
        return ans;
    }
}