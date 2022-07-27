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
explanation: easy same tree checker! the program works on the algorithm of iterating over each nodes by left and right and checking for each node in the copy subtree if their values match!

testcase: 1, 2, 3 and 1, 2, 3 -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of program is linear since it iterates over each nodes and compares it's values! also recursion stack store the elements iterated therefore space complexity is also linear!
*/
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null) return q == null ? true : false;
        if(q == null) return p == null ? true : false;
        
        if(p.val != q.val) return false;
        
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}