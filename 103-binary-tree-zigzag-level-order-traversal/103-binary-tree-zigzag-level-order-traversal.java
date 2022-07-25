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
explanation: zigzag order traversal using recursion and maintaining level! if level is an even multiple of 2 then we  reverse the whole elements order in list and push it to final ans, also if it isn't then we push the list as it.

testcase: [3,9,20,null,null,15,7] -> [[3],[20,9],[15,7]]
the program works by maintaining a level to list of elements map, and then reverse the elements alternatively based on level number!

Time & Space Complexity: O(n) and O(n): Since recursion is used with a treemap to store the levels and the corresponding elements present therefore time and space complexity is linear!
*/
class Solution {
    
    TreeMap<Integer, List<Integer>> map = new TreeMap<>();
    
    void zigzag(TreeNode root, int level){
        if(root == null) return;
        
        List<Integer> lst = map.get(level);
        if(lst == null) lst = new ArrayList<>();
        lst.add(root.val);
        map.put(level, lst);
        
        zigzag(root.left, level+1);
        zigzag(root.right, level+1);
    }
    
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        
        zigzag(root, 0);
        
        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
          Integer key = entry.getKey();
          List<Integer> value = entry.getValue();
            
          if(key%2!=0){
            Collections.reverse(value);
          }
          ret.add(value);
        }

        
        return ret;
    }
}