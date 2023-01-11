/*
explanation: minimum time to collect apple using recursion + carry back method, the program works by using the recusion algorithm to calculate the cost(time) to collect all apple and adds them all, if there is any child node with non zero cost (ie apple in branch), then the current node is also added to path and a +2 is added to cost! This is backtracked and 2 is substracted to final answer to get the results!

testcase:
7
[[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]]
[false,false,true,false,true,true,false]
-> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity is linear due to recursion used!
*/
class Solution {
    HashMap<Integer, List<Integer>> tre;
    int MAX_NODE;
    List<Boolean> apple;
    int[] visited;
    
    public int recurmin(int n){
        if(visited[n] == 1) return 0;
        visited[n] = 1;
        
        if(n > MAX_NODE) return 0;
        
        int cost = 0;
        
        if(apple.get(n) == true){
            cost += 2;
        }
        
        List<Integer> list = tre.getOrDefault(n, new ArrayList<>());
        
        boolean included = false;
        for(Integer ig : list){
            int childcost = recurmin(ig);
            if(childcost != 0) included = true;
            cost += childcost;
        }
        
        if(included){
            if(!apple.get(n))
                cost += 2;
        }
        
        // System.out.println("At "+n+" cost "+cost+" isapple? "+apple.get(n));
        return cost;
    }
    
    public void pair(int a, int b){
        List<Integer> list = tre.getOrDefault(a, new ArrayList<>());
        list.add(b);
        tre.put(a, list);
    }
    
    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
        MAX_NODE = n;
        tre = new HashMap<>();
        apple = hasApple;
        visited = new int[n];
        
        for(int[] e : edges){
            pair(e[0], e[1]);
            pair(e[1], e[0]);
        }
        
        return Math.max(0, recurmin(0)-2);
    }
}