/*
explanation: all paths by recursive dfs! The program works by going to each node, looking its neighbours and iterating over them while maintaining a list where we insert our path if it ever reach to last node! This is dfs approach!

testcase: [[1,2],[3],[3],[]] -> Works

Time & Space Complexity: O(n!) & O(n): Each node can iterative n-1 of previous nodes, therefore the time complexity of this program will be n!, also space complexity is linear since recursive stack is used!
*/
class Solution {
    List<List<Integer>> ans = new ArrayList<>();
    int[][] graph;
    
    public void repath(int n, List<Integer> path){
        if(n == graph.length-1){
            // System.out.println("Adding to path "+Arrays.toString(path.toArray()));
            // This is the end, add it to paths!
            ans.add(new ArrayList<>(path));
        }
        // System.out.println("Crawl "+n+" with "+Arrays.toString(path.toArray()));
        int[] neigh = graph[n];
        for(int i : neigh){
            path.add(i);
            repath(i, path);
            path.remove(path.size()-1);
        }
    }
    
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.graph = graph;
        List<Integer> path = new ArrayList<>();
        path.add(0);
        repath(0, path);
        
        return ans;
    }
}