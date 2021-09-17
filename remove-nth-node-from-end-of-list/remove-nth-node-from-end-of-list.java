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
Explanation: Recursive remove from nth, the recursive method goes from head to tail of list, when encountered the last element will return the index of removal (r, or n in removeNthFromEnd fxn), Once this element is returned, the subsequent calls (in stack) will reduce it by one and pass it on to next underlying instance, the moment this is reduced to 0, We will get the element whose next just points to the element needed to be removed, Hence using simple deletion algorithm the specific node can be removed.!

Special case, If the element be removed turns out to be the first element in the input, ie for the case with input [1, 5, 7, 8] with n=4, ie element to be removed is 1, then the recursion algorithm doesn't remove it, as there's no element prior to 1 pointing to it, but this could easily be corrected and fixed because in that case the value of returned variable (a), would always be 0, thus if returned recusion function value is 0, we return next of node;

Testcase:
Assume removeNth(n, r) repr. with f(n, r) returning variable a;
[1, 2, 3] n=2 -> (n, a) =
3 2
2 1
1 0
returned a=-1

[1, 2, 3], n=3 ->
3 3
2 2
1 1
returned a=0

[1, 2, 3], n=1 ->
3 1
2 0
1 -1
returned a=-2
*/
class Solution {
    public int removeNth(ListNode n, int r){
        if(n==null) return r;
        
        int a = removeNth(n.next, r);
        if(a==0){
            n.next = n.next.next;
        }
        return a-1;
    }
    
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode h = head;
        int r = removeNth(head, n);
        if(r==0) h=h.next;
        return h;
    };
}