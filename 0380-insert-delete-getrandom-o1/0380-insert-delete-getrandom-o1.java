/*
explanation: randomized set using map+list, the approach works by adding element to list and maintaining the structure
*/
class RandomizedSet {
    HashMap<Integer, Integer> map;
    List<Integer> list;
    Random randomGenerator;

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        randomGenerator = new Random();
    }
    
    public boolean insert(int val) {
        if(!map.containsKey(val)){
            list.add(val);
            map.put(val, list.size()-1);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean remove(int val) {
        if(map.containsKey(val)){
            int indx = map.get(val);
            map.put(list.get(list.size()-1), indx);
            Collections.swap(list, indx, list.size()-1);
            list.remove(list.size()-1);
            map.remove(val);
            return true;
        } else {
            return false;
        }
    }
    
    public int getRandom() {
        int index = randomGenerator.nextInt(list.size());
        return list.get(index);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */