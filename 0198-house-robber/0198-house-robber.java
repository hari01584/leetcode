/*
explanation: House robber using tabulation! The program works by finding the rob or not rob maximum money calculated parameters! At every node we find the sum using formula robbed = money_here + prev_not_robbed and notrob = maximum of all prev_robbed, finding these two parameters for each element and at the end returning maximum of these 2 will give our answer!

testcase: [1,2,3,1] -> Works

Time & Space Complexity: O(n) & O(n): Time and space complexity is linear due to looping over each element and using extra array to store rob and not rob parameters for each element!
*/
class Solution {
    class E {
        int item;
        int rob;
        int notrob;
        
        E(int i, int r, int n){
            item = i;
            rob = r;
            notrob = n;
        }
        
        @Override
        public String toString(){
            return "item:"+item+", rob:"+rob+", nobrob:"+notrob;
        }
    }
    
    public E get(E[] arr, int i){
        if(i < 0) return new E(0, 0, 0);
        return arr[i];
    }
    
    public int rob(int[] nums) {
        E[] arr = new E[nums.length];
        
        for(int i=0; i<nums.length; i++){
            int itm = nums[i];
            E prev = get(arr, i-1);
            int rob_this = itm + prev.notrob;
            int not_rob_this = 0;
            for(int z=0; z<i; z++){
                not_rob_this = Math.max(not_rob_this, get(arr, z).rob);
            }
            arr[i] = new E(itm, rob_this, not_rob_this);
        }
        
        return Math.max(arr[arr.length-1].rob, arr[arr.length-1].notrob);
    }
}