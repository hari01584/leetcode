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
/*
explanation: balance checker by checking the max height of left and right subtree at each node, for any node if the difference in height between the two trees are greater than 1 then it is not balanced and we set a variable to false depicting the same!

testcase: [3,9,20,null,null,15,7] -> Works

Time & Space Complexity: O(n) & O(n): Since each node is iterated therefore time and space complexity is O(n)!
*/
class Solution {
    boolean isBalanced = true;
    public int maxheight(TreeNode root){
        if(root == null) return 0;
        if(!isBalanced) return 0;
        
        int mhl = maxheight(root.left);
        int mhr = maxheight(root.right);
        
        if(Math.abs(mhl - mhr) > 1) isBalanced = false;
        
        return Math.max(mhl, mhr) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        maxheight(root);
        return isBalanced;
    }
}