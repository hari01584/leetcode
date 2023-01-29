/*
explanation: lfu cache using double hashmap + treemap! The program works by first recording relations between key -> value, key -> count and finally count -> keys! for every insert/delete all the cache is rebuilt and modified accordingly! 
Now the problem case in our cache is maintaining count variable and sorting count based upon recently used (tie breaker), to solve all this we use treemap of keys as counts and values as list of keys that have that current count! The program when changing count inquires first the key -> count map and then use this count value to further get and change the count of element! so for example a simple get operation for key x will first check count of x (lets assume it to be c) then change the count map to remove the key from list stored in c and add the key to list stored in c+1th key of count map!

testcase: 
["LFUCache","put","put","get","put","get","get","put","get","get","get"]
[[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
-> Works!

firstly for the first two put statement count map becomes:
# Key: 1. Value: 1 2 
 now the next get(1) bumps the key 1 and place it under count=2, so now count map becomes
# Key: 1. Value: 2 
# Key: 2. Value: 1 

see how? it means that keys having count=1 is key 2 and keys having count=2 is key 1

now for the third insertion it checks the remaining capacity and since its not enough (already two elements in map) so it purges the least recently used key having least count! ie key 2 having count 1, and after insertion operation the final map becomes
Key: 1. Value: 3 
Key: 2. Value: 1 

next get(2) mutates the map to:
Key: 2. Value: 1 3 

and next insertion of put(4) purges the least recently used key 3 and insert 4, so map now becomes:
Key: 1. Value: 4 
Key: 2. Value: 3 

next get(1) results in -1 since no element with key 1 is there

next get(3) bumps count of 3 and map becomes:
Key: 1. Value: 4 
Key: 3. Value: 3 

similiarly get(4) bumps count of 4 and finally the map ends with:
Key: 2. Value: 4 
Key: 3. Value: 3 


Time & Space Complexity: Time complexity of get and put operation is O(n) in worst linear case of least recently used (ie when count of everything is same and linear lists is iterated again and again) but aside that the average time complexity is O(1) and O(1) respectively, since maps are used and they offer O(1) complexity!
*/

class LFUCache {
    class SortedArrayList<T> extends ArrayList<T> {
        @SuppressWarnings("unchecked")
        public void insertSorted(T value) {
            add(value);
            Comparable<T> cmp = (Comparable<T>) value;
            for (int i = size()-1; i > 0 && cmp.compareTo(get(i-1)) < 0; i--)
                Collections.swap(this, i, i-1);
        }
        
    }

    
    TreeMap<Integer, ArrayList<Integer>> count_to_key
            = new TreeMap<Integer, ArrayList<Integer>>();
    HashMap<Integer, Integer> map = new HashMap<>();
    HashMap<Integer, Integer> map_count = new HashMap<>();

    int CAPACITY;
    int used;
    
    public LFUCache(int capacity) {
        CAPACITY = capacity;
        used = 0;
    }
    
    void changeCount(int k, int c, int nc){
        ArrayList<Integer> arr = count_to_key.get(c);
        arr.remove(Integer.valueOf(k));
        if(arr.size() == 0){
            count_to_key.remove(c);
        }
        else {
            count_to_key.put(c, arr);
        }
        
        createNewKeyCount(nc, k);
    }
    
    public int get(int key) {
        if(map.get(key) == null) return -1;
        
        int value = map.get(key);
        int count_of_key = map_count.get(key);
        
        // Change count
        map_count.put(key, count_of_key+1);
        
        changeCount(key, count_of_key, count_of_key+1);
        
        // System.out.println(" get log final "+key);
        // logMap();
        
        return value;
    }
    
    void createNewKeyCount(int c, int k){
        if(count_to_key.get(c) == null) count_to_key.put(c, new ArrayList<Integer>());
        
        ArrayList<Integer> arrlist = count_to_key.get(c);
        arrlist.add(k);
        
        count_to_key.put(c, arrlist);
    }
    
    void logMap(){
        for (Map.Entry<Integer, ArrayList<Integer>> entry : count_to_key.entrySet()) {
             System.out.print("Key: " + entry.getKey() + ". Value: ");
        
            for(Integer number : entry.getValue()){
                System.out.print(number+" ");
            }
            
            System.out.println();
        }
        System.out.println();
    }
    
    
    void purgeLowest(){
        int highcount = count_to_key.firstKey();
        ArrayList<Integer> arr = count_to_key.get(highcount);
        Integer key = arr.remove(0);
        if(arr.size() == 0){
            count_to_key.remove(highcount);
        }
        else {
            count_to_key.put(highcount, arr);
        }
        
        map.remove(key);
        map_count.remove(key);
        
        used--;
    }
    
    public void put(int key, int value) {
        if(CAPACITY == 0) return;
        
        if(map.get(key) == null){
            // TODO if used > capacity then delete
            if(used + 1 > CAPACITY){
                purgeLowest();
            }

            // Insert new key with counter 1
            map.put(key, value);
            map_count.put(key, 1);
            
            createNewKeyCount(1, key);
            used++;

            // logMap();
        } else {
            get(key);
            map.put(key, value);
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */