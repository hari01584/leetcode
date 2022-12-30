class Solution {
    class E {
        int indx;
        int itm;
        E(int i, int t){
            indx = i;
            itm = t;
        }
    }
    public int sumSubarrayMins(int[] arr) {
        Stack<E> stack = new Stack<>();
        E[] items = new E[arr.length];
        
        int[] nextsmaller = new int[arr.length];
        for(int i=0; i<arr.length; i++){
            int itm = arr[i];
            while(stack.size() > 0 && (stack.peek()).itm > itm){
                E e = stack.pop();
                nextsmaller[e.indx] = i;
            }
            stack.push(new E(i, itm));
        }
        while(stack.size() > 0){
            nextsmaller[stack.pop().indx] = arr.length;
        }
        
        int[] prevsmaller = new int[arr.length];
        for(int i=arr.length-1; i>=0; i--){
            int itm = arr[i];
            while(stack.size() > 0 && (stack.peek()).itm >= itm){
                E e = stack.pop();
                prevsmaller[e.indx] = i;
            }
            stack.push(new E(i, itm));
        }
        while(stack.size() > 0){
            prevsmaller[stack.pop().indx] = -1;
        }
        
        // System.out.println(Arrays.toString(nextsmaller));
        // System.out.println(Arrays.toString(prevsmaller));

        long sum = 0;
        int module = (int)(1e9+7);
        for(int i=0; i<arr.length; i++){
            // if(sum < 0) System.out.println("Low");
            
            int d1;
            int d2;
            if(prevsmaller[i] == -1){
                d1 = i+1;
            } else {
                d1 = i - prevsmaller[i];
            }
            
            if(nextsmaller[i] == arr.length){
                d2 = arr.length-i;
            } else {
                d2 = nextsmaller[i]-i;
            }

            sum += ((long)d1*d2*arr[i])%module;
            sum%=module;
            
            // System.out.println("Element "+arr[i]+" "+d1+" "+d2);
        }
        
        return (int)(sum%module);
    }
}