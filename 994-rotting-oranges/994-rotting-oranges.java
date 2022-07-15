/*
explanation: rotten oranges using two stacks and bfs traversal! the program works by maintaining an active and a current stack, we store location of all the rotten oranges in active stack and loop over each orange to find its fresh neighbours, if found we add this location to current stack which after all iteration will be pushed back into active stack! (better understanding in testcase)

testcase:
[[2,1,1],[1,1,0],[0,1,1]] -> works
here first we calculate initial rotten oranges location and push them to our active stack (named stack in program), if there are no rotten oranges in whole board or all the cells are empty then we return -1 and 0 respectively once past this stage we go on to our looping part!

in this testcase, stack will contain [0, 0] position of rotten oranges (the first orange). in loop we will check for the four neighbours of this element and check if they are healthy orange, if they are then we will push it to current stack (neworange in program), similiarly we will calculate all neighbours of each rotten orange in stack and after pushing everything to neworange we finally will transfer(not copy) all elements from neworange to stack while also marking the orange to be rotten! and finally increase mins counter. now the loop will work until both stack and neworanges become empty (ie no more oranges that can be affected) and hence will give our answer!

Time & Space Complexity: O(n^2) & O(n^2): Both time and space complexity is quadractic since bfs is used and we can be storing maximum of n^2 oranges in our stack!
*/

class Solution {
    int[][] grid;
    
    public int getElement(int x, int y){
        if(x < 0 || y < 0 || x >= grid.length || y >= grid[0].length) return -1;
        else return grid[x][y];
    }
    
    public int pack(int x, int y){
        return ((x & 0xffff) << 16) | (y & 0xffff);
    }
    
    public int[] unpack(int e){
        int[] u = new int[2];
        u[0] = (e >> 16) & 0xffff;
        u[1] = e & 0xffff;
        
        return u;
    }
    
    public int[] getNeigh(int i, int j){
        int[] inr = new int[4];
        
        inr[0] = getElement(i-1, j);  // top
        inr[1] = getElement(i+1, j);  // down
        inr[2] = getElement(i, j-1);  // left
        inr[3] = getElement(i, j+1);  // right
        
        return inr;
    }
    
    public void logStack(Stack<Integer> stack){
        Iterator<Integer> iter = stack.iterator();
        while (iter.hasNext()){
            System.out.println(Arrays.toString(unpack(iter.next())));
        }
    }
    
    public int orangesRotting(int[][] grid) {
        this.grid = grid;
        boolean isAllEmpty = true;
        
        Stack<Integer> stack = new Stack<>();
        // for starters push all the rotten orange, ie initial board config
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 2){
                    stack.push(pack(i, j));
                }
                if(grid[i][j] != 0){
                    isAllEmpty = false;
                }
            }
        }
        
        if(isAllEmpty) return 0;
        if(stack.size() == 0) return -1;  // No rotten oranges
        
        Stack<Integer> neworange = new Stack<>();
        int mins = -1;
        while(!stack.empty() || !neworange.empty()){
            while(!stack.empty()){
                int[] u = unpack(stack.pop());
                int i = u[0];
                int j = u[1];
                
                // System.out.println(i+" "+j);
                int[] n = getNeigh(i, j);
                if(n[0] == 1) neworange.push(pack(i-1, j));
                if(n[1] == 1) neworange.push(pack(i+1, j));
                if(n[2] == 1) neworange.push(pack(i, j-1));
                if(n[3] == 1) neworange.push(pack(i, j+1));
            }
            
            // System.out.println();
            
            while(!neworange.empty()){
                Integer i = neworange.pop();
                int[] u = unpack(i);
                grid[u[0]][u[1]] = 2;
                stack.push(i);
            }
            
            mins++;
        }
        
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j] == 1){
                    return -1; // left oranges
                }
            }
        }


        return mins;
    }
}