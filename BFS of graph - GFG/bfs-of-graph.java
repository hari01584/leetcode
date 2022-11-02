//{ Driver Code Starts
// Initial Template for Java
import java.util.*;
import java.lang.*;
import java.io.*;
class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String[] s = br.readLine().trim().split(" ");
            int V = Integer.parseInt(s[0]);
            int E = Integer.parseInt(s[1]);
            ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) adj.add(i, new ArrayList<Integer>());
            for (int i = 0; i < E; i++) {
                String[] S = br.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                adj.get(u).add(v);
                // adj.get(v).add(u);
            }
            Solution obj = new Solution();
            ArrayList<Integer> ans = obj.bfsOfGraph(V, adj);
            for (int i = 0; i < ans.size(); i++)
                System.out.print(ans.get(i) + " ");
            System.out.println();
        }
    }
}

// } Driver Code Ends

/*
explanation: simple bfs traversal using visited array and using queue! the program works by simply popping the top element from queue
checking if its visited, if not then adding it to our ans and its all childrens to end of queue, repeating this many times we get
our bfs traversal array!

testcase:
5 4
0 1 
0 2
0 3 
2 4 -> Works

Time & Space Complexity: time complexity of the program is linear since each node is iterated once, also space complexity is constant
since the max amount of node stored is only for single level!
*/

class Solution {
    // Function to return Breadth First Traversal of given graph.
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        ArrayList<Integer> ans = new ArrayList<>();
        
        int[] visited = new int[V];
        
        // String result = "";
        // for(int i = 0; i < adj.size(); i++){
        //   for(int j = 0; j < adj.get(i).size(); j++){
        //       result += adj.get(i).get(j);
        //   }
        //   // System.out.println();
        //   result += "N\n";
        // }
        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(0);
        while(que.size() > 0){
            int e = que.poll();
            if(visited[e] == 1) continue;
            
            // Add this element to our ans and set visit to 1
            ans.add(e);
            visited[e] = 1;
            
            // Explore neighbours of this node
            for(int i=0; i<adj.get(e).size(); i++){
                que.offer(adj.get(e).get(i));
            }
            // System.out.println(e);
        }

        // System.out.println(result);
        
        return ans;
    }
}