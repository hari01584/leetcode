/*
Explaination: Finds height of any given node in a binary tree, At every steps of recursion it checks for the maximum length of the two branches (left and right) and append 1 to each of them, When nothing is remained in tree, it output the max height(left, right) + 1 and checks this value for balance of a tree.

Testcase:
[3,9,20,null,null,15,7] -> Works
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
    public boolean isBalance = true;
    
    public int recurHeight(TreeNode node){
        if(node==null) return -1;
        
        TreeNode left = node.left;
        TreeNode right = node.right;
        
        int hl = recurHeight(left);
        int hr = recurHeight(right);
        
        return Math.max(hl, hr) + 1;
    }
    
    public boolean isNodeBalance(TreeNode node){
        if(node == null) return true;

        if(Math.abs(recurHeight(node.left) - recurHeight(node.right)) <= 1){
            return true;
        }
        
        return false;
    }
    
    public void nodeBalance(TreeNode node){
        if(node == null) return;
        
        nodeBalance(node.left);
        nodeBalance(node.right);
        
        if(isNodeBalance(node) == false){
            isBalance = false;
        }
    }
    
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        
        nodeBalance(root);

        return isBalance;
    }
}