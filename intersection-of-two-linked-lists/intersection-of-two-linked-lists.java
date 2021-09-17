/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
/*
inspiration: https://www.geeksforgeeks.org/write-a-function-to-get-the-intersection-point-of-two-linked-lists/

Explanation: Counts the difference in length of two lists, then set the larger list pointer ahead by difference{abs(l1-l2)} of the two lists, after this uniformly iterate over the two list and check for matching pointer addresses!

Testcases:
[4,1,8,4,5]
[5,6,1,8,4,5]
Where the 8 in 3rd pos from first list is common element

Works, Initially the code loops over both list seperately and stores length n1, n2 (5 and 6 here), then it move forward the pointer of larger list (here list 2) to match the length of shorter one, therefore pointer headA points to 4 and headB points to 6, then it uniformly traverse towards the end of list while looking for matching pointer address. 4->1->8->4->5 and 6->1->8->4->5, breaks on first occurrence. 
*/
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode hA = headA;
        ListNode hB = headB;
        int n1, n2;
        for(n1=1; (hA=hA.next)!=null; n1++);
        for(n2=1; (hB=hB.next)!=null; n2++);
        
        while(n1>n2){
            headA=headA.next;
            n1--;
        }
        while(n2>n1){
            headB=headB.next;
            n2--;
        }
        
        for(int i=0; i<n1; i++){
            if(headA == headB)
                return headA;
            headA=headA.next;
            headB=headB.next;
        }
        return null;
    }
}