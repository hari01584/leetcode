/*
explanation: minimum number of arrows by simulation approach! The program works by using iterative loops and simulation to solve the given probem, It uses the following strategies of overlapping to calculate minimum arrows required!
There are two types of overlapping possible: Partial, Full
1. In partial overlapping, we take the previous balloon dimensionsa and skip the current one, because if there is partial overlapping then we can simply use the end point of prev balloon to see if it is greater than current ballon start, here it is partial overlap!
2. In full overlap, we choose the smaller interval to be our main and skip current one, because if any arrow were to fall here then it would be same as falling into the full interval!

testcase: [[10,16],[2,8],[1,6],[7,12]] -> Works

Time & Space Complexity: O(nlogn) & O(n): Time complexity is nlogn due to the sorting needed, also space complexity is linear due to storage required for arrays!
*/
class Solution {
    class B {
        int start;
        int end;
        B(int s, int e){
            start = s;
            end = e;
        }
        
        @Override
        public String toString(){
            return "{"+start+", "+end+"}";
        }
    }
    
    public int findMinArrowShots(int[][] points) {
        B[] baloons = new B[points.length];
        int i=0;
        for(int[] tp : points){
            baloons[i++] = new B(tp[0], tp[1]);
        }
        
        Arrays.sort(baloons, new Comparator<B>(){
           public int compare(B a, B b){
               if(a.start == b.start) return Integer.compare(a.end, b.end);
               return Integer.compare(a.start, b.start);
           }
        });
        
        int ans = 1;
        B prev = baloons[0];
        for(i=1; i<baloons.length; i++){
            B b = baloons[i];
            // Check how many we can skip using each overriding cases!
            // Case 1: Partial over lap, ie 1,6 and 2,8 or Complete overlap, ie 1,6 and 2,4
            if(b.start <= prev.end){
                // Complete overlap happened!
                if(b.end <= prev.end){
                    // System.out.println("Complete overlap b/w "+b+" and "+prev);
                    prev = b;
                    continue;
                }
                
                // System.out.println("Partial overlap b/w "+b+" and "+prev);
                // Partial overlap happened!
                continue;
            }
            
            ans++;
            prev = b;
        }
        
        // System.out.println(Arrays.toString(baloons));
        
        return ans;
    }
}