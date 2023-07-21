/*
explanation: pascal triangle using arraylist! the algorithm works by creating layer by layer list using previous layers
and adding them to answer!

testcase:  numRows = 5 -> Works

Time & Space Complexity: O(n^2) & O(n^2): Both time and space complexity of this algorithm is quadratic due to the iteration
performed and the list storage space required to store answer.
*/
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>(Arrays.asList(1)));
        if (numRows == 1) return ans; // for first row only
        
        for (int i=1; i<numRows; i++) {
            List<Integer> previousList = ans.get(ans.size() - 1); // For later on addition
            
            // List for this layer!
            List<Integer> mylist = new ArrayList<>();

            // for each rows, we first add starting padding 1
            mylist.add(1);
            
            // In between, implement logic to do addition and calculate this layer!
            for (int z=1; z<previousList.size(); z++) {
                // The current element is equal to sum of two elements of previous list
                mylist.add(previousList.get(z) + previousList.get(z - 1));
            }
            
            // add ending padding 1
            mylist.add(1);
            
            // Finally add to our answer this layer
            ans.add(mylist);
        }
        
        
        return ans;
    }
}