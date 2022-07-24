/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/*
explanation: lowest common ancestor by tracing path of p and q to root and then finding the closest intersection of element in two list!

testcase: [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4 -> Works
in this case, for example after executing generateParents function, the returned path is 3, 5 for p and 3, 5, 2, 4 for q! now reversing p and q and traversing each elements to find the element that also exists in q we can get the shortest path!

Time & Space Complexity: O(n^2) & O(n): Since last checking and intersection method might need to iterate over all the elements and likewise check over all the elements therefore the worst time complexity is quadratic, also since lists are used to store the traced path therefore space complexity is linear!
*/
class Solution {
    List<TreeNode> path = new ArrayList<>();
    List<TreeNode> ans = new ArrayList<>();
    boolean isSolved = false;
    void generateParents(TreeNode root, TreeNode target){
        if(root == null) return;
        if(isSolved) return;
        
        path.add(root);
        if(root == target){
            // Log and save and return nodes!
            ans = new ArrayList<>(path);
            isSolved = true;
        }

        generateParents(root.left, target);
        generateParents(root.right, target);
        path.remove(path.size()-1);
    }
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        generateParents(root, p);
        List<TreeNode> p_path = new ArrayList<>(ans);
        isSolved = false;
        ans.clear();
        
        generateParents(root, q);
        List<TreeNode> q_path = new ArrayList<>(ans);
        isSolved = false;
        
        Collections.reverse(p_path);
        Collections.reverse(q_path);
        
        // System.out.println("p_path ");
        for (TreeNode element : p_path) {
            if(q_path.contains(element)) return element;
        }

        return root;
    }
}