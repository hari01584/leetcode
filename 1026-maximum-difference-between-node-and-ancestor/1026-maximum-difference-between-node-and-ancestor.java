/*
explanation: ancestral difference finder using recursion/tree traversal! The programs works by finding maximum and minimum of branches below nodes! The program works by first iterating to last layer and then step by step building maximum and minimum value for current node! Also the ancesorts would be the maximum value of difference from leftmost and rightmost subtree!

testcase: [8,3,10,1,6,null,14,null,null,4,7,13] -> Works
Explanation: firsly using dfs the tree will go to leftmost last node, and building the max, min to be 1,1 respectively, similiarly it does the same for right node (6 as) well, when we focus onto the node 3, then it will have 3 set of parameters, first is its value (root.val), then the max-min tuple from its left and right nodes, using these 3 parameters we find the maximum and minimum till that node (by finding least of root.val, left.least, right.least), similiarly to right node, we pass this to node above this, ie node 8! This program works and builds min-max value of descendants at each steps! Also an additional parameters ancestor is maintained at every step which is the maximum of difference between root.val and one of its minimum/maximum value of its descendant branches!

Time & Space Complexity: O(n) & O(n): Time and space complexity is linear due to recursion used the space required to store the maximum and minimum of each nodes while recursing!
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
    class Node { 
        int s;
        int l;
        Node(int small, int large){
            s = small;
            l = large;
        }
        
        @Override
        public String toString(){
            return "{s: "+s+", l:"+l+",}";
        }
    }
    
    int ancestral = 0;
    Node recurseMinMax(TreeNode root){
        if(root == null) return new Node(Integer.MAX_VALUE, Integer.MIN_VALUE);
        
        Node left = recurseMinMax(root.left);
        Node right = recurseMinMax(root.right);
        
        int smallest = Math.min(Math.min(left.s, right.s), root.val);
        int largest = Math.max(Math.max(left.l, right.l), root.val);
        
        int d1 = Math.abs(root.val-smallest);
        int d2 = Math.abs(root.val-largest);
        ancestral = Math.max(ancestral, Math.max(d1, d2));
        
        Node ans = new Node(smallest, largest);
        // System.out.println("Iterated over node "+root.val+" with l:"+left+" r:"+right+" ans:"+ans+" coeff: "+d1+" "+d2);
        
        return ans;
    }
    
    public int maxAncestorDiff(TreeNode root) {
        Node f = recurseMinMax(root);
        
        return ancestral;
    }
}