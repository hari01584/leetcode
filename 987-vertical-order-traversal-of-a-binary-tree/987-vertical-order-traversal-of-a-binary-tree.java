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
explanation: vertical order traversal by using the concepts of bottom/top view of binary trees! the program works by maintaining a map of horizontal distance to list of sorted integer data! for each element in preorder iteration, if there is already something in list then it simply adds the new element and sort it! please note that level data are ignored in program! Also a custom comparator is used for comparing between different levels/data (ie tiebreaker)!

testcase:
[3,9,20,null,null,15,7] -> Works

Time & Space Complexity: O(n) & O(n): Time complexity is linear due to the recursion/visiting each node, also Since a hashmap is created with max n elements therefore space complexity is also linear!

*/
class Solution {
    class Element{
        int data;
        int level;
        public Element(int data, int level){
            this.data = data;
            this.level = level;
        }
        
        public int getData(){
            return this.data;
        }
    }
    
    public class ElementComparer implements Comparator<Element> {
        @Override
        public int compare(Element x, Element y) {
            int lv = x.level - y.level;
            if(lv != 0) return lv;
            else return x.data - y.data;
        }
    }
    
    TreeMap<Integer, ArrayList<Element>> map = new TreeMap<>();
    
    void treetraversal(TreeNode root, int level, int hd){
        if(root == null) return;
        
        ArrayList<Element> arr = map.get(hd);
        if(arr == null) arr = new ArrayList<Element>();
        
        arr.add(new Element(root.val, level));
        Collections.sort(arr, new ElementComparer());
        
        map.put(hd, arr);
        
        treetraversal(root.left, level+1, hd-1);
        treetraversal(root.right, level+1, hd+1);
    }
    
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        treetraversal(root, 0, 0);
        
        List<List<Integer>> ret = new ArrayList<>();
        for(Map.Entry<Integer, ArrayList<Element>> entry : map.entrySet()) {
          Integer key = entry.getKey();
          List<Element> value = entry.getValue();
            
          List<Integer> data = value.stream()
                  .map(Element::getData)
                  .collect(Collectors.toList());

          ret.add(data);
        }
        return ret;
    }
}