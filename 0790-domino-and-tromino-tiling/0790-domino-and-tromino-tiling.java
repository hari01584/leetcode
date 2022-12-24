/*
explanation: finding recursive pattern and coding it as such.
*/
class Solution {
    public int numTilings(int n) {
        int[] arr = new int[Math.max(n+1, 3)];
        arr[0] = 1;
        arr[1] = 1;
        arr[2] = 2;
        
        if(n<=2) return (int)arr[n];
        
        for(int i=3; i<=n; i++){
            arr[i] = (int)(2*arr[i-1]%(1e9+7)) + (int)(arr[i-3]%(1e9+7));
            arr[i] %= 1e9+7;
        }
        
        return (int)(arr[n]%(1e9+7));
    }
}