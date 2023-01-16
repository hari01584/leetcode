/*
explanation: insert interval by boundary merge algorithm, the program works by using conditons to merge multiple boundary!

testcase: [[1,3],[6,9]]
[2,5] -> Works

Time & Space Complexity: O(n) & O(n): Both time and space is linear due to the array required to store!
*/
class Solution {
    class E implements Comparable<E>{
        int start;
        int end;
        
        E(int s, int e){
            start = s;
            end = e;
        }
        
        @Override
        public String toString(){
            return "{" + start + ", " + end + "}";
        }
        
        @Override
        public int compareTo(E e){
            return Integer.compare(this.start, e.start);
        }
    }
    
    public int[][] insert(int[][] intervals, int[] newInterval) {
        
        E[] ivs = new E[intervals.length];
        
        ArrayList<E> collect = new ArrayList<>();
        for(int i=0; i<ivs.length; i++){
            ivs[i] = new E(intervals[i][0], intervals[i][1]);
        }
        
        E target = new E(newInterval[0], newInterval[1]);
        boolean didMerge = false;
        for(int i=0; i<ivs.length; i++){
            E consider = ivs[i];
            // System.out.println("At "+consider);
            if(consider.end < target.start){
                // No need to worry, simply  add
                collect.add(consider);
            } else {
                // System.out.println("Maybe merge");
                // Add or merge, something
                // consider.end >= target.start <- given
                if(consider.start > target.end){
                    if(!didMerge){
                        collect.add(target);
                        didMerge = true;
                    }
                    collect.add(consider);
                } else {
                    didMerge = true;
                    
                    // System.out.println("Yes merge");
                    E last = target;
                    last.start = Math.min(last.start, consider.start);
                    last.end = Math.max(last.end, consider.end);
                    
                    if(collect.size() > 0){
                        E top = collect.get(collect.size()-1);
                        if(top.start == last.start && top.end == last.end) continue;
                    }
                    
                    collect.add(last);
                }
            }
        }
        
        if(!didMerge){
            // Didnt merge or insert, now add the element to appropriate place!
            collect.add(target);
        }
        
        int[][] ans = new int[collect.size()][2];
        int z = 0;
        for(E e : collect){
            ans[z++] = new int[]{e.start, e.end};
        }
        
        return ans;
    }
}