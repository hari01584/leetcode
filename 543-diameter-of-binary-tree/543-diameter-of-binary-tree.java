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
explanation: diameter of binary tree by calculating longest path from left and right side. https://leetcode.com/problems/diameter-of-binary-tree/discuss/101132/Java-Solution-MaxDepth
*/
class Solution {
    int globalmx = Integer.MIN_VALUE;
    public int maxheight(TreeNode root){
        if(root == null) return 0;
        
        int mhl = maxheight(root.left);
        int mhr = maxheight(root.right);
        
        globalmx = Math.max(globalmx, mhl+mhr);
        
        return Math.max(mhl, mhr) + 1;
    }
    public int diameterOfBinaryTree(TreeNode root) {
        maxheight(root);
        return globalmx;
    }
}