/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/*
Explaination: contrary to the mentioned algorithm to solve this problem, we used recursion + looping to do this, First using recursion we are reading elements from backwards and using loops we are SHIFTING (by repeated swapping) the larger element to its appropriate place! Once this happens we exit the loop and then do the same for prior element in linkedlist!

Testcase:
[4,2,1,3]
Using recursion we loop over and check conditions for following:
3
1 3 (No swapping since 1<3)
2 1 3 (Swap 2 as it is larger than 1, so it becomes 1 2 3)
4 1 2 3 (Swap 4 again as it is larger than 1)-> 1 4 2 3, Check condition again, next number of swapped element is still larger than itself, so swap again -> 1 2 4 3, do this again and we get final sorted array 1 2 3 4!!
*/
class Solution {
    public void swapValue(ListNode a1, ListNode a2){
        int c = a2.val;
        a2.val = a1.val;
        a1.val = c;
    }
    
    public void insertionRecr(ListNode head){
        if(head.next==null) return;
        insertionRecr(head.next);
        
        ListNode origin = head;
        while(head!=null){
            if(origin.val>head.val){
                swapValue(origin, head);
                origin = head;
            }            
            head = head.next;
        }
    }
    
    public ListNode insertionSortList(ListNode head) {
        insertionRecr(head);
        return head;
    }
}