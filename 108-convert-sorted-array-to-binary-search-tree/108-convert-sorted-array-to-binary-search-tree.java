/*
explanation: construct height balanced binary search tree using simple recursion! The program works by simply choosing the middle element of array partition each time and setting it as root node, while all the elements smaller than this value will go toward its left and elements greater will go toward right! Iterating this over and over again we automatically get a height-balanced bst tree!

testcase:
[-10,-3,0,5,9] -> Works
Here first we pick 0 to be our root node, and recursively construct left subtree from array partition -10, -3 and right subtree from 5, 9, recursively each part will give another subtree which will be assigned to our tree with root node val 0 and hence returned! The tree will be automatically height balanced as we take median element each time of parition, and hence distributing left and right evenly.

Time & Space Complexity: O(n) & O(n): Time and space complexity is both linear since we iterate over tree once each time! also recursion stack makes the space complexity linear.
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int[] nums;
    public TreeNode construct(int start, int end){
        if(start > end) return null;
        
        int mid = (start+end)/2;
        
        TreeNode n = new TreeNode(nums[mid]);
        n.left = construct(start, mid-1);
        n.right = construct(mid+1, end);
        
        return n;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        this.nums = nums;
        return construct(0, nums.length-1);
    }
}