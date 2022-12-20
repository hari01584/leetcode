/*
explanation: leaf similar trees using dfs! The program works by first iterating both trees and storing leaf nodes into arraylist in order, then it simply compares the generated listnode arraylist and check if they are equal!

testcase: [1, 2, 3] -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of this program is linear since whole tree traversal is required, also space comelexity is linear due to storing of leaf nodes!
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
    public void traverse(TreeNode root, ArrayList<Integer> arr){
        if(root == null) return;
        if(root.left == null && root.right == null) arr.add(root.val);
        
        traverse(root.left, arr);
        traverse(root.right, arr);
    }
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        
        traverse(root1, arr1);
        traverse(root2, arr2);
        
        return arr1.equals(arr2);
    }
}