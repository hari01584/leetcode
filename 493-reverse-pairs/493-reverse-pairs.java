/*
explanation: works on merge sort algorithm. Using merge sort we simply adds check for second condition on conquer step, since everything left and right array are parted and hence first condition automatically satisfied.

testcase:
[2,4,3,5,1] -> Works

Time & Space Complexity: O(n^2) and O(1)
time complexity is O(nlogn) since two loops are run and space complexity is O(logn) since only one variable is stored.
*/

import java.math.BigInteger;

class Solution {
    int MATCH = 0;
        private static int next(int[] arr, long target)
    {
        int start = 0, end = arr.length - 1;
   
        int ans = -1;
        while (start <= end) {
            int mid = (start + end) / 2;
   
            // Move to right side if target is
            // greater.
            if (arr[mid] <= target) {
                start = mid + 1;
            }
   
            // Move left side.
            else {
                ans = mid;
                end = mid - 1;
            }
        }
        return ans;
    }

    void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
 
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
 
        for (int j = 0; j < n2; ++j){
            int n = next(L, 2 * (long)R[j]);
            if(n == -1) break;
            int rmm = n1 - n;
            MATCH += rmm;
            
            // System.out.println(R[j]+" "+ n + " " + rmm);
        }

        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public void mergeSort(int[] nums, int l, int r){
        if(r>l){
            int m = (l + r) / 2;
            mergeSort(nums, l, m);
            mergeSort(nums, m+1, r);
            
            merge(nums, l, m, r);
        }
    }

    public int reversePairs(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        
        return MATCH;
    }
}