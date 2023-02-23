/*
explanation: ipo using fastclock class! This algorithm uses fastclock (my own datastructure for solvin these kind of problems) to model the program into a clock with needle that can be forwarded to time t, for this operation, all the intermediate times are added to our choices and we can pick the best possible choice from feed function!

testcase:
2
0
[1,2,3]
[0,1,1] -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity of this algorithm is linear due to the storage required for arrays and priorityqueue!
*/

import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.*;

class Solution {
    class E {
        int profits;
        int capital;
        
        E(int p, int c){
            profits = p;
            capital = c;
        }
        
        static Comparator<E> compareByProfits() {
            return new Comparator<E>() {
                public int compare(E one, E two) {
                    return Integer.compare(two.profits, one.profits);
                }
            };
        }

        static Comparator<E> compareByCapital() {
            return new Comparator<E>() {
                public int compare(E one, E two) {
                    return Integer.compare(one.capital, two.capital);
                }
            };
        }
        
        @Override
        public String toString(){
            return "("+profits +", "+capital+")";
        }
    }
    
    class FastClock {
        int lastIndex = 0;
        PriorityQueue<E> choices;
        E[] arr;
        
        FastClock(E[] elements){
            choices = new PriorityQueue<>(E.compareByProfits());
            arr = elements.clone();
            Arrays.sort(arr, E.compareByCapital());
        }
        
        int upper_bound(int key){
            int mid, N = arr.length;

            int low = 0;
            int high = N;
            while (low < high && low != N) {
                // Find the index of the middle element
                mid = low + (high - low) / 2;

                // If key is greater than or equal
                // to arr[mid], then find in
                // right subarray
                if (key >= arr[mid].capital) {
                    low = mid + 1;
                }

                // If key is less than arr[mid]
                // then find in left subarray
                else {
                    high = mid;
                }
            }

            // If key is greater than last element which is
            // array[n-1] then upper bound
            // does not exists in the array
            if (low == N ) {
                return N;   
            }
            
            return low;
        }
        
        void forward(int time){
            int targexIndex = upper_bound(time);
            for(int i=lastIndex; i<targexIndex; i++){
                choices.add(arr[i]);
            }   
            lastIndex = targexIndex;
        }
        
        int feed(){
            // Get best element possible from priority queue and solve it!
            E best = choices.poll();
            if(best == null) return 0;
            // Remove this item and return its profit to be added to later capital!
            return best.profits;
        }
        
        @Override
        public String toString(){
            // Return state of this clock!
            return "Head: "+lastIndex+" Buffer: "+ Arrays.toString(choices.toArray());
        }
    }
    
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        E[] elements = new E[profits.length];
        for(int i=0; i<elements.length; i++){
            elements[i] = new E(profits[i], capital[i]);
        }
        
        int pfts = 0;
        
        // Our fastclock class, that manages storing retrieving all values!
        FastClock fc = new FastClock(elements);
        for(int i=0; i<k; i++){
            // First forward clock to this time and add all elements!
            fc.forward(w);
            
            // After forwarding
            // System.out.println("Forwarded: "+fc);
            
            // Get best element to consume and add profit!
            w += fc.feed();            
            
            // State
            // System.out.println("Consumed: "+fc+" new w: "+w);
        }
        
        return w;
    }
}