/*
explanation: median of two sorted array by calculating frequencies of each element in min..max across two arrays and performing a binary search, note this solution takes different approach in even or odd case.

testcase:
[1, 3], [2] -> Works
Here first program searches min and max by comparing 4 indices (starting 2 and end 2 of both arrays) and therefore sets min=1 and max=3, now a bsearch is performed in interval 1 to 3 and all the elements less than x is counted and compared to total number of elements! When the loop ends it gives a solution for the median element.

Time and Space Complexity: O(log(m+n)) and O(1): Since binear searching over maximum m+n elements are performed therefore time complexity is O(log(m+n)), also since constant number of variables are used therefore space complexity is O(1)
*/

class Solution {
    public int bsearch(int[] arr, int x){
        int start=0;
        int end=arr.length-1;
        while(start<=end){
            int mid = (start+end)/2;
            if(arr[mid] > x){
                end = mid - 1;
            }
            else{
                start = mid + 1;
            }
        }
        return start;
    }
    
    public int getCountBSearch(int[] nums1, int[] nums2, int e){
        int count=0;
        count += bsearch(nums1, e);
        count += bsearch(nums2, e);
        return count;
    }
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int l1 = nums1.length;
        int l2 = nums2.length;
        
        int start = -1;
        int end = -1;
        if(l1==0){
            start = nums2[0];
            end = nums2[nums2.length-1];
        }
        else if(l2==0){
            start = nums1[0];
            end = nums1[nums1.length-1];
        }
        else{
            start = Math.min(nums1[0], nums2[0]);
            end = Math.max(nums1[nums1.length-1], nums2[nums2.length-1]);
        }
        int soln = -1;

        int l = l1+l2;
        if(l%2==0){
            // Is even, proceed with caution
             while(start <= end){
                int mid = (start+end)/2;
                int count = getCountBSearch(nums1, nums2, mid);
                if(count < (l/2) + 1){
                    start = mid+1;
                }
                else{
                    soln = mid;
                    end = mid-1;
                }
            }
            
            System.out.println(soln+" ");
            if(l1==0){
                start = nums2[0];
                end = nums2[nums2.length-1];
            }
            else if(l2==0){
                start = nums1[0];
                end = nums1[nums1.length-1];
            }
            else{
                start = Math.min(nums1[0], nums2[0]);
                end = Math.max(nums1[nums1.length-1], nums2[nums2.length-1]);
            }
            int nsol = -1;
            int limiupe = l - ((l/2) + 1);
            while(start <= end){
                int mid = (start+end)/2;
                int count = getCountBSearch(nums1, nums2, mid);
                if(count > limiupe){
                    nsol = mid;
                    end = mid-1;
                }
                else{
                    start = mid+1;
                }
            }

            return (soln + nsol)/2.0;
        }
        else{
            // Odd, easy asf
            while(start <= end){
                int mid = (start+end)/2;
                int count = getCountBSearch(nums1, nums2, mid);
                System.out.println(mid+" => "+count);
                if(count < (l/2) + 1){
                    start = mid+1;
                }
                else{
                    soln = mid;
                    end = mid-1;
                }
            }
        }

        return soln;
    }
}