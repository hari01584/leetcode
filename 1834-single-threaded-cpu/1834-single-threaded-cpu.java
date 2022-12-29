/*
explanation: single threaded cpu using priority queue! The program works by maintaining priority queue and doing simulation of three operations performed on cpu! to understand whole code even better, just uncomment all the lines of System.out.print.... and see step by step output!

testcase: [[1,2],[2,4],[3,2],[4,1]] -> Works

Time & Space Complexity: O(nlogn) & O(n): Time complexity is nlogn due to array sorting and insertion in priority queue, also space complexity is linear due to the space required to store priority queue at each step!
*/
class Solution {
    class Task implements Comparable<Task>{
        int idx;
        int et;
        int pt;
        
        Task(int i, int e, int p){
            this.idx = i;
            this.et = e;
            this.pt = p;
        }
        
        @Override
        public int compareTo(Task b){
            if(this.et == b.et){
                if(this.pt == b.pt) return this.idx - b.idx;
                return this.pt - b.pt;
            }
            return this.et - b.et;
        }
        
        @Override
        public String toString(){
            return "{"+idx+", "+et+", "+pt+"}";
        }
    }
    
    
    Task[] tasks;
    
    public int bSearch(int itm){
        int start = 0;
        int end = tasks.length-1;
        
        while(start <= end){
            int mid = (start+end)/2;
            if(itm <= tasks[mid].et){
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        
        return start;
    }
    
    public int highBSearch(int itm){
        int start = 0;
        int end = tasks.length-1;
        
        while(start <= end){
            int mid = (start+end)/2;
            if(itm < tasks[mid].et){
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        
        return start;
    }
    
    public void addTasks(PriorityQueue<Task> queue, int stime, int etime){
        int ind = bSearch(stime);
        int hig = highBSearch(etime);
        for(int i=ind; i<hig; i++){
            Task addt = tasks[i];
            addt.et = -1;
            queue.add(addt);
        }
    }
    
    public int[] getOrder(int[][] tsks) {
        tasks = new Task[tsks.length];
        int mtime = Integer.MAX_VALUE;
        for(int i=0; i<tasks.length; i++){
            tasks[i] = new Task(i, tsks[i][0], tsks[i][1]);
        }
        // System.out.println(mtime);
        
        Arrays.sort(tasks);
        
        // System.out.println(Arrays.toString(tasks));
        
        PriorityQueue<Task> queue = new PriorityQueue<Task>();
        int stime = tasks[0].et;
        
        int ctime = 0;
        int iter=20;
        
        int[] ans = new int[tsks.length];
        int adex = 0;
        while(true){
            if(ctime < 0){
                ctime = Integer.MAX_VALUE;
            }
            if(adex >= ans.length) break;
            // System.out.println("Before State: "+Arrays.toString(queue.toArray())+" time:"+ctime);
            if(queue.size() == 0){
                Task task = tasks[bSearch(ctime)];
                // Empty queue, add next valid operation!
                // System.out.println("Queue empty, adding task:"+task);
                ctime = task.et;
                addTasks(queue, task.et, task.et);
            } else {
                Task task = queue.poll();
                // System.out.println("CPU Performing on task:"+task);
                
                ans[adex++] = task.idx;
                
                // Calculate new time, endtime and push all task between!
                int ind = bSearch(ctime+1);
                int hig = highBSearch(ctime+task.pt);
                // System.out.println("Adding tasks between "+ctime+" and "+(ctime+task.pt));
                for(int i=ind; i<hig; i++){
                    if(tasks[i] != task){
                        Task addt = tasks[i];
                        addt.et = -1;
                        // System.out.println("ADD "+addt);
                        queue.add(addt);
                    }
                }
                
                ctime += task.pt;
            }
            
            // System.out.println("After State: "+Arrays.toString(queue.toArray())+" time:"+ctime);
            // System.out.println();
            // if(iter<0) break;
            // iter--;
        }
        
        return ans;
    }
}