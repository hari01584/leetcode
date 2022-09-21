/*
explanation: twosum bst calculator by tree traversal and finding the other number! The algorithm works by first traversing the bst node by node and checking for the existence of number k - node.val in the bst, if this exist then we can say that these two nodes sum equals to our given number k.

testcase:
[5,3,6,2,4,null,7]
9 -> Works

This works by traversing the tree in preorder, it first goes to 5 and checks the other number for which the sum be equal to 9, which is 9-5=4, now the program checks for the existence of this number 4 which as we can see exist in given bst, therefore it returns true.

Time & Space Complexity: the time and space complexity of given program is O(nlogn) and O(n) respectively, the time complexity is O(nlogn) due to two parts of algorithm, first the traversal and another the searching, the traversal takes linear time while searching for a node in given bst takes logn time, therefore time complexity becomes O(nlogn), also space complexity is O(n) due to recursive stack that needs to be stored in the memory.
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
    TreeNode superoot;
    public boolean isExist(TreeNode root, int n, TreeNode ign){
        if(root == null) return false;
        if(root.val == n && root != ign){
            return true;
        }

        if(n < root.val){
            return isExist(root.left, n, ign);
        } else {
            return isExist(root.right, n, ign);
        }
    }
    
    public boolean twosum(TreeNode root, int k){
        if(root == null) return false;
        int s = k - root.val;
        System.out.println(root.val + " " + s);
        if(isExist(superoot, s, root)) return true;
        return twosum(root.left, k) || twosum(root.right, k);
    }
    
    public boolean findTarget(TreeNode root, int k) {
        superoot = root;
        return twosum(root, k);
    }
}