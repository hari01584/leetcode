/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* mergeTwoLists(ListNode* l1, ListNode* l2) {
        // Handle empty conditions for l1 and l2
        if(l1==NULL && l2==NULL) return NULL;
        else if(l1==NULL) return l2;
        else if(l2==NULL) return l1;
        
        ListNode *f = NULL;
        if(l1->val < l2->val){
            f = l1;
            l1 = l1->next;
        }
        else{
            f = l2;
            l2 = l2->next;
        }
        ListNode *start = f;
        
        while(l1!=NULL && l2!=NULL){
            if(l1->val < l2->val){
                f->next = l1;
                l1=l1->next;
            }
            else{
                f->next = l2;
                l2=l2->next;
            }
            f = f->next;
        }
        if(l1==NULL){
            while(l2!=NULL){ f->next = l2; f = f->next; l2=l2->next; }
        }
        else{
            while(l1!=NULL){ f->next = l1; f = f->next; l1=l1->next; }
        }
        
        f->next = NULL;
        return start;
    }
};