/*
explanation: range sum bst using tree traversal and adding elements in correct bound! The program works by using traversals and finding suitable nodes to add! Also it uses basic pruning to remove some unwanted node, which works by two rules! If the current node val is less than the lowest permissible value, then we can for sure that any nodes belonging to left of this node will not contribute to answer! Similiarly if any node greater than highest permissible node is there then the right side of this node will not result in answer (Therefore we will prune it!)

testcase: 
[10,5,15,3,7,null,18] -> Works

Time & Space Complexity: O(n) & O(n): time complexity of this program is O(n) for the number of nodes in tree, also space complexity is also linear since recursion is used.
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
    
    public int sumsolve(TreeNode root, int low, int high){
        if(root == null) return 0;
        
        int sum = 0;
        if(low<=root.val && root.val<=high){
            sum += root.val;
        }
        
        if(!(root.val < low)){
            sum += sumsolve(root.left, low, high);
        }
        if(!(root.val > high)){
            sum += sumsolve(root.right, low, high);
        }
        
        return sum;
    }
    
    public int rangeSumBST(TreeNode root, int low, int high) {
        return sumsolve(root, low, high);
    }
}