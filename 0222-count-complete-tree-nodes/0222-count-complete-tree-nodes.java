/*
explanation: count complete tree nodes by recursive strategy, the program works by first calculating count of nodes of the tree and optimizing it by using the left==right condition! If true then the program returns the nodes at that level (which is equal to 2 raised to power n, minus 1!)

testcase: [1,2,3,4,5,6] -> Works

Time & Space Complexity: O(n) and O(n), time and space complexity of this program is linear since we use recursion and have to iterate to each nodes once!
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
    int lastNodes = 0;
    
    public int countNodes(TreeNode root){
        if(root == null){
            return 0;
        }
        
        int left = 0;
        TreeNode cur = root;
        while(cur != null){
            left+=1;
            cur = cur.left;
        }
        
        int right = 0;
        cur = root;
        while(cur != null){
            right+=1;
            cur = cur.right;
        }
        
        if(left == right){
            return (int)Math.pow(2, left) - 1;
        }
        
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}