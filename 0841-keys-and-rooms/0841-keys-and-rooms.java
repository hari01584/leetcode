/*
explanation: keys and rooms using discrete union set! The program works by using dsu to successively merge and collect rooms and keys to a bigger dsu set, at the end of performing this we check for any key outside same head, if there is then it means this nodeis not reachable and hence invalid!

testcase: [[1],[2],[3],[]] -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity is linear due to use of DSU!
*/
class Solution {
    class DSU{
        int n;
        int[] arr;
        DSU(int n){
            this.n = n;
            arr = new int[n];
            
            for(int i=0; i<n; i++){
                arr[i] = i;
            }
        }
        
        void union(int x, int y){
            int h1 = find(x);
            int h2 = find(y);
            
            if(h1 == h2) return;
            if(x == h1){
                arr[x] = h2;
            } else if(y == h2){
                arr[y] = h1;
            } else {
                arr[h1] = h2;
            }
        }
        
        int find(int x){
            while(arr[x] != x){
                x = arr[x];
            }
            return x;
        }
    }
    
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int[] marked = new int[rooms.size()];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        
        DSU dsu = new DSU(rooms.size());
        while(stack.size()>0){
            int i = stack.pop();
            if(marked[i] != 1) marked[i] = 1;
            else continue;
            
            List<Integer> keys = rooms.get(i);
            // Merging i with
            for(int z=0; z<keys.size(); z++){
                int k = keys.get(z);
                stack.push(k);
                // Key (or z)
                // System.out.println("Merging "+i+" with "+k);
                dsu.union(i, k);
            }
        }
        
        int thead = dsu.find(0);
        for(int i=0; i<rooms.size(); i++){
            // System.out.println(i+" "+dsu.find(i));
            if(dsu.find(i) != thead) return false;
        }
        
        return true;
    }
}