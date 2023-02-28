/*
explanation: find duplicate subtrees using sets and node label! The algorithm works by giving label to each node and each subtree, a string is formed which represents all subtrees including node for a given tree, this string is matched against other previously found strings and if match occours then add it to our list!

testcase: [1,2,3,4,null,2,4,null,null,4] -> Works

Time & Space Complexity: O(n^2) & O(n^2): Time complexity is quadratic due to string addition operation performed at each step, (to calculate hash), also space complexity is quadratic due to space required to store all the previous hashes!
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
    class E {
        TreeNode node;
        String hash;
        E(TreeNode n, String h){
            node = n;
            hash = h;
        }
        
        @Override
        public int hashCode() {
            return hash.hashCode();
        }
        
        @Override
        public boolean equals(Object o) {
            if(!(o instanceof E)) 
                return false;
            E e = (E)o;
            return this.hash.equals(e.hash);
        }
    }
    
    HashMap<String, TreeNode> map;
    HashSet<E> ans;
    public String recurtree(TreeNode root){
        if(root == null) return "#";
        
        String lkey = recurtree(root.left);
        String rkey = recurtree(root.right);

        String hashkey = root.val + "/" + lkey + "/" + rkey;
        if(map.containsKey(hashkey)){
            ans.add(new E(root, hashkey));
        } else map.put(hashkey, root);
        
        // System.out.println("At root val: "+root.val+" hashkey: "+hashkey);
        
        return hashkey;
    }
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        map = new HashMap<>();
        ans = new HashSet<>();
        recurtree(root);
        
        List<TreeNode> anslist = new ArrayList<TreeNode>();
        for(E e: ans){
            // System.out.println("Element node val: "+e.node.val+", hc="+e.hashCode()+", hash="+e.hash);
            anslist.add(e.node);
        }
        // anslist.addAll(ans);
        
        return anslist;
    }
}