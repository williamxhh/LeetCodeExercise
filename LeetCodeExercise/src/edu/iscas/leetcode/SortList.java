package edu.iscas.leetcode;

 class ListNode {
     int val;
     ListNode next;
     ListNode(int x) {
         val = x;
         next = null;
     }
 }
 
public class SortList {
    public ListNode sortList(ListNode head) {
        int len = getListLen(head);
        return sortList(head,len);
    }
   
    private ListNode sortList(ListNode head,int len){
        if(len <= 1)
            return head;
        ListNode middle = head;
        int half = len/2;
        for(int i=0;i<half;i++){
            if((i == half-1) && (middle != null)){
                ListNode temp = middle;
                middle = middle.next;
               //这里要砍断，不然会死循环
                temp.next = null;
            }else{
                middle = middle.next;
            }
        }
        return mergeList(sortList(head,half),sortList(middle,len-half));
    }
   
    private ListNode mergeList(ListNode head1,ListNode head2){
        ListNode head = null;
        ListNode p = null;
        while((head1 != null) && (head2 != null)){
            if(head1.val <= head2.val){
                if(head == null){
                    head = p = head1;
                    head1 = head1.next;
                }else{
                    p.next = head1;
                    //要记得修改p啊，不然p老是停在原地没往前走
                    p = head1;
                    head1 = head1.next;
                }
            }else{
                if(head == null){
                    head = p = head2;
                    head2 = head2.next;
                }else{
                    p.next = head2;
                    p = head2;
                    head2 = head2.next;
                }
            }
        }
        if(head1 != null)
            p.next = head1;
        if(head2 != null)
            p.next = head2;
       
        if(head != null)
            return head;
        else{
            if(head1 != null){
                return head1;
            }else{
                return head2;
            }
        }
    }
   
    private int getListLen(ListNode head){
        int len = 0;
        while(head != null){
            len++;
            head = head.next;
        }
        return len;
    }
}
