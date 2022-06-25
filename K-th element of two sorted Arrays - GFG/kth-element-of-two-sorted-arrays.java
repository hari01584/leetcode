// { Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
	public static void main(String[] args) throws IOException
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while(t-->0)
        {
            StringTokenizer stt = new StringTokenizer(br.readLine());
            
            int n = Integer.parseInt(stt.nextToken());
            int m = Integer.parseInt(stt.nextToken());
            int k = Integer.parseInt(stt.nextToken());
            int a[] = new int[(int)(n)];
            int b[] = new int[(int)(m)];
            
            
            String inputLine[] = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(inputLine[i]);
            }
            String inputLine1[] = br.readLine().trim().split(" ");
            for (int i = 0; i < m; i++) {
                b[i] = Integer.parseInt(inputLine1[i]);
            }
            
            
            Solution obj = new Solution();
            System.out.println(obj.kthElement( a, b, n, m, k));
            
        }
	}
}
// } Driver Code Ends


//User function Template for Java


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
    
    public long kthElement( int nums1[], int nums2[], int n, int m, int k) {
        int start;
        int end;
        start = Math.min(nums1[0], nums2[0]);
        end = Math.max(nums1[nums1.length-1], nums2[nums2.length-1]);
        int soln = -1;
        while(start<=end){
            int mid=(start+end)/2;
            int count = getCountBSearch(nums1, nums2, mid);
            if(count < k){
                start = mid+1;
            }
            else{
                soln = mid;
                end = mid-1;
            }
        }
        return soln;
    }
}