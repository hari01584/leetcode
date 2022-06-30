/*
explanation: next greater element by maintaining next greater element map! which works using a stack and maintaining a non decreasing sequence: more about solution here: https://leetcode.com/problems/next-greater-element-i/discuss/97595/Java-10-lines-linear-time-complexity-O(n)-with-explanation

testcase:
[4,1,2]
[1,3,4,2] -> Works!

Time & Space Complexity: O(n) & O(n): since a linear loop is used to maintain and find the next largest element therefore time complexity is O(n), also since maps are used to store next greatest element of all the elements in array (of size n) therefore space complexity is O(n).
*/

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] ans = new int[nums1.length];
        
        // Build greater element array
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=0; i<nums2.length; i++){
            int e = nums2[i];
            while(!stack.empty() && stack.peek() < e){
                map.put(stack.pop(), e);
            }
            stack.push(e);
        }
        while(!stack.empty()){
            map.put(stack.pop(), -1);
        }
        
        for(int i=0; i<nums1.length; i++){
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }
}