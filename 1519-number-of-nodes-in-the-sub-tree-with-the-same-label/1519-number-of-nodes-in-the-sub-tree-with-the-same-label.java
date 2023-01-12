/*
explanation: number of nodes in the sub tree using recursion algorithm, the program works by returning a recursive hashmap which maintains the frequency of characters of label at each node, using this information we assign the duplicate node value at each point, and return.

testcase: 
7
[[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]]
"abaeddd" -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity is linear since recursion is used!
*/
class Solution {
    HashMap<Integer, List<Integer>> tre;
    int[] visited;
    int MAX_NODE;
    String label;
    int[] ans;
    
    public void pair(int a, int b){
        List<Integer> list = tre.getOrDefault(a, new ArrayList<>());
        list.add(b);
        tre.put(a, list);
    }
    
    public HashMap<Character, Integer> recurcount(int n){
        if(n > MAX_NODE) return new HashMap<Character, Integer>();
        if(visited[n] == 1) return new HashMap<Character, Integer>();
        visited[n] = 1;
        
        HashMap<Character, Integer> hashmap = new HashMap<Character, Integer>();
        hashmap.put(label.charAt(n), hashmap.getOrDefault(label.charAt(n), 0)+1);
            
        List<Integer> list = tre.getOrDefault(n, new ArrayList<>());
        for(Integer ig : list){
            HashMap<Character, Integer> next = recurcount(ig);
            next.forEach((key, value) -> hashmap.merge(key, value, (v1,v2) -> v1+v2));
        }

        ans[n] = hashmap.getOrDefault(label.charAt(n), 0);
        // System.out.println("At "+n);
        // for (Map.Entry<Character, Integer> entry : hashmap.entrySet()) {
        //     System.out.println(entry.getKey()+" : "+entry.getValue());
        // }
        
        return hashmap;
    }

    public int[] countSubTrees(int n, int[][] edges, String labels) {
        List<HashMap<Integer, Integer>> list = new ArrayList<>();
        tre = new HashMap<>();
        MAX_NODE = n;
        visited = new int[n];
        label = labels;
        
        for(int[] e : edges){
            pair(e[0], e[1]);
            pair(e[1], e[0]);
        }

        ans = new int[n];
        
        HashMap<Character, Integer> map = recurcount(0);
        
//         for(int i=0; i<ans.length; i++){
//             ans[i] = map.getOrDefault(labels.charAt(i), 0);
//         }
        
        return ans;
    }
}