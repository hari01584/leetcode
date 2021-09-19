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
Explaination: simple merge sort linkedlist algorithm, reference and some code help taken from https://www.geeksforgeeks.org/merge-sort-for-linked-list/

Works by diving the problem into small chunks, bisects the linkedlist across its middle and sorts the two seperate part until we reach a bigger sorted linkedlist!
Testcases:
Note: here + means the LinkedList Merge function, which merges two sorted linkedlist to produce single one.

[4,2,1,6,8,5,3] -> Sort([4 2 1 6])+Sort([8 5 3])
= Sort([4 2 1]) + Sort([6]) + ...
= Sort([4 2]) + Sort([1]) + ....
= Sort([4]) + Sort([2]) + .....
= Sort([2 4]) + Sort([1]) + .....
    ....        .....   + .......
= [1,2,3,4,5,6,8]
*/
class Solution {
    public ListNode middle(ListNode head){
        if(head==null) return null;
        
        ListNode f = head;
        while(f.next!=null && f.next.next!=null){
            head = head.next;
            f = f.next.next;
        }
        return head;
    }
    

    public ListNode merge(ListNode left, ListNode right){
        if(left==null) return right;
        if(right==null) return left;
        ListNode n = null;
        ListNode str = null;

        while(left!=null || right!=null){
            ListNode e;
            if(left==null){
                e = right;
                right=right.next;
            }
            else if(right==null){
                e=left;
                left = left.next;
            }
            else{
                if(left.val<right.val){
                    e = left;
                    left = left.next;
                }
                else{
                    e = right;
                    right = right.next;
                }
            }
            if(n==null){
                n = e;
                str = n;
            }
            n.next = e;
            n=n.next;
        }
        n.next = null;
        return str;
    }
    
    public ListNode recrSort(ListNode head){
        if(head==null || head.next == null) return head;
        
        ListNode mid = middle(head);

        ListNode nextMid = mid.next;
        mid.next = null; 
        
        ListNode l = recrSort(head);
        ListNode r = recrSort(nextMid);
        
        System.out.println(l.val + " " + r.val);
        return merge(l, r);
    }
    
    public ListNode sortList(ListNode head) {
        ListNode str = recrSort(head);
        
        return str;
    }
}