/*
explanation: lowest common ancestor of bst by comparing value of root node with the p and q nodes (nodes to find lca of), we do this by either descending down the tree if both of the values are either greater or smaller than the root node value (in that case we are sure both values will lie on same side, hence the current node is def. not the lca and we descend down the tree on the side of common node and run the function recursively again), if however at any point we find that p and q lie on different side of children of root then we return this root as the lca!

testcase:
[6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8 -> Works, here we go step by step down the tree, initially the recursive algorithm runs on node 6, it checks for root value and p & q, since here p and q are on different side of root node val (ie p - value 2 is < 6, thf. on left side of root and q - val 8 is > 6 so on right side) therefore we return this value as our final answer.

Time & Space Complexity: O(logn) & O(logn): Since here we recursively choose to descend a specific child node and hence reducing the tree to iterate by 2 per descend therefore time complexity is logn, also since recursion is used and we only go to one cross section height of tree therefore space complexity is also logn

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int l = root.val - p.val;
        int r = root.val - q.val;
        if(Math.abs(l) > 0 && Math.abs(r) > 0 && (l ^ r) >=0){
            return l > 0 ? lowestCommonAncestor(root.left, p, q) : lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }
}