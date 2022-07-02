/*
explanation: lazy approach lrucache ref- https://leetcode.com/problems/lru-cache/discuss/45939/Laziest-implementation%3A-Java's-LinkedHashMap-takes-care-of-everything
*/

class LRUCache {
    
    LinkedHashMap<Integer, Integer> map;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<>();
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            Integer value = map.remove(key);
            map.put(key, value);
            return value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            map.remove(key);
        }
        else if(map.size() + 1 > capacity){
            map.remove(map.keySet().iterator().next());
        }
        map.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */