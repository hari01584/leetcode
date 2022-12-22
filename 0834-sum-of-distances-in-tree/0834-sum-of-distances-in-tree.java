/*
explanation: sum distance of tree using recalculation algorithm! The program works by first calculating edge ledge for topmost (ie 0th) node and then using recalculation formula destEdgeSum = sourceSum - 2(dest. nodes) + (source nodes) - 1! Using this forumula and readjusting the edge length we can find edge distance from all nodes! (Works on principle of calculating change in nodes length (ie addition and subtraction of new nodes/ etc), when we transit from one node to another!)

testcase:
[[0,1],[0,2],[2,3],[2,4],[2,5]] -> Works
At first we calculate edgeSum for 0 (Using recursion, etc), it turns out to be 8, then using other parameters we go to each neighbours of 0 (ie 1, 2), and find new edgeSum using previous edgesSum!

Time & Space Complexity: O(n) & O(n): Time and space complexity is linear due to our use of recursions, also since no nested loops etc are used!
*/
class Solution {
    class Vertex {
        int sum;
        int nodes;
        Vertex(int s, int n){
            sum = s;
            nodes = n;
        }
        
        @Override
        public String toString(){
            return "{s:"+sum+", n:"+nodes+"}";
        }
    }
    
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    HashMap<Integer, Vertex> cumSum = new HashMap<>();
    
    public void addNeigh(int x, int y){
        List<Integer> lst = map.getOrDefault(x, new ArrayList<>());
        lst.add(y);
        map.put(x, lst);
    }
    
    public Vertex recurseSum(int n, int prev, int level){
        int sum = 0;
        int node = 0;
        List<Integer> lst = map.getOrDefault(n, new ArrayList<>());
        // System.out.println("Neighbours of "+n+" are "+Arrays.toString(lst.toArray()));
        for(Integer neig : lst){
            if(neig == prev) continue;
            Vertex v = recurseSum(neig, n, level+1);
            sum += level + v.sum;
            node += 1 + v.nodes;
        }
        Vertex res = new Vertex(sum, node);
        cumSum.put(n,res);
        return res;
    }
    
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        
        for(int[] edge : edges){
            addNeigh(edge[0], edge[1]);
            addNeigh(edge[1], edge[0]);
        }
        
        // First calculate initial distances! (From root node!) and no of child notes for each, (cumulatively!)
        Vertex edgeSum = recurseSum(0, -1, 1);
        ans[0] = edgeSum.sum;
        
        // for (Map.Entry<Integer, Vertex> entry : cumSum.entrySet()) {
        //     System.out.println(entry.getKey()+" : "+entry.getValue());
        // }

        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        
        while(stack.size()>0){
            Integer source = stack.pop();
            List<Integer> lst = map.getOrDefault(source, new ArrayList<>());
            // System.out.println("Neighbours of "+source+" are "+Arrays.toString(lst.toArray()));
            for(Integer destination : lst){
                if(ans[destination] != -1) continue;
                ans[destination] = ans[source] - (2 * cumSum.get(destination).nodes) + cumSum.get(source).nodes - 1;
                // System.out.println("Set distance of "+destination+" = "+ans[destination]);
                stack.push(destination);
                
                // Adjust vertices
                Vertex destVert = cumSum.get(destination);
                destVert.nodes = cumSum.get(source).nodes;
                cumSum.put(destination, destVert);
            }
        }
        
        return ans;
    }
}