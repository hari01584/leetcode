/*
explanation: Works on hashmap, first all the numbers are mapped to their index in a hashmap, then we loop over each number and find the target number to be found. if the target number exists in hashmap we can just call it over and return it as an int array!

testcase:
[2,7,11,15]
9 -> Works

Time & Space Complexity: O(n) and O(n)
Since linear loops are used with hashmaps therefore time complexity is O(n). In space complexity since hashmaps are used to store indices, it is again O(n)
*/
class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            map.put(nums[i], i);
        }
        
        for(int i=0; i<nums.length; i++){
            int e = nums[i];
            int n = target - e;
            Integer value = map.get(n);
            if (value != null && value != i) {
                return new int[]{i, value};
            }
        }

        return null;
    }
}