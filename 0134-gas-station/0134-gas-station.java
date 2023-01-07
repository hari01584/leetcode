/*
explanation: gas station problem using simulation, the program works by simulating the coin pay for each index, returns when there is possible to traverl circuit!

testcase: 
[1,2,3,4,5]
[3,4,5,1,2] -> Works

Time & Space Complexity: O(n^2) & O(1): Time complexity is quadratic because two nested loops are run, also space complexity is constant due to the countable variables required!
*/
class Solution {
    public boolean simulate(int n, int[] net){
        if(net[n] < 0) return false;
        
        int pos = n;
        int sum = 0;
        for(int i=0; i<net.length; i++){
            sum+=net[(n+i)%net.length];
            if(sum < 0) return false;
        }
        
        return true;
    }
    
    public boolean simulateProper(int n, int[] gas, int[] cost){
        if(gas[n] < cost[n]) return false;
        
        int pos = n;
        int sum = 0;
        for(int i=0; i<gas.length; i++){
            int curr = (n+i)%gas.length;
            sum+=gas[curr];
            if(sum < cost[curr]){
                this.ii = curr;
                return false;
            }
            sum -= cost[curr];
        }
        
        return true;
    }
    
    int ii=0;
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // int[] net = new int[gas.length];
        
//         for(int i=0; i<net.length; i++){
//             net[i] = -cost[i] + gas[(i+1)%gas.length];
//         }
        
        // System.out.println(Arrays.toString(net));
        
        for(;ii<gas.length; ii++){
            int mans = ii;
            boolean good = simulateProper(ii, gas, cost);
            if(good) return mans;
            // if(mans == ii) break;
            if(ii < mans) ii = mans;
            // System.out.println("Starting pos "+mans+" "+good+" newindx "+ii);
        }
        
        return -1;
    }
}