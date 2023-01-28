/*
explanation: data set stream using treeset! The program works by finding the lower and higher keys of new elements to be added and comparing it with current ones to find if to add or merge! Using some rules we can automatically add the element to our ranges!

testcase: 
["SummaryRanges","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals","addNum","getIntervals"]
[[],[1],[],[3],[],[7],[],[2],[],[6],[]] -> Works

Time & Space Complexity: O(n) & O(n): Worst case time complexity would be in case of iteration and getIntervals, also space complexity is also linear due to storage required to maintain ranges!
*/
class E implements Comparable<E>{
    int start;
    int end;

    E(int s, int e){
        start = s;
        end = e;
    }

    public int compareTo(E e){
        if(e.end == -1) return Integer.compare(this.start, e.start);
        
        if(this.start == e.start) return Integer.compare(this.end, e.end);
        return Integer.compare(this.start, e.start);
    }

    @Override
    public String toString(){
        return "{"+start+", "+end+"}";
    }
}

class SummaryRanges {
    TreeSet<E> set;
    
    public SummaryRanges() {
        set = new TreeSet<E>();
    }
    
    public boolean isInside(int val, E inv){
        if(inv == null) return false;
        if(inv.start <= val && val <= inv.end) return true;
        return false;
    }
    
    public void addNum(int value) {
        if(set.size()==0){
            set.add(new E(value, value));
            return;
        }
        E dum = new E(value, -1);
        E lower = set.lower(dum);
        E high = set.higher(dum);
        
        // System.out.println("At "+value+", Lower: "+lower+", higher:"+high);

        if(isInside(value, lower)){
            // Inside lower
            // System.out.println("Inside lower, merged!");
            return;
        } else if(isInside(value, high)){
            // System.out.println("Inside higher, merged!");
            return;
        }
        
        boolean lmerge = false;
        boolean rmerge = false;
        // Check for boundary points now
        if(lower != null && lower.end == value-1){
            // Boundary point left found! (Can be merged with left)
            // System.out.println("Left boundary merge possible!");
            lmerge = true;
        }
        if(high != null && high.start == value+1){
            // Boundary point right found! (Can be merged with right)
            // System.out.println("Right boundary merge possible");
            rmerge = true;
        }
        
        if(lmerge && rmerge){
            // Combine both boundaries
            lower.end = high.end;
            set.remove(high);
        } else if(lmerge){
            lower.end = value;
        } else if(rmerge){
            high.start = value;
        } else {
            set.add(new E(value, value));
        }
    }
    
    public int[][] getIntervals() {
        int[][] ans = new int[set.size()][2];
        int i=0;
        for(E e : set){
            ans[i][0] = e.start;
            ans[i++][1] = e.end;
        }
        
        return ans;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(value);
 * int[][] param_2 = obj.getIntervals();
 */