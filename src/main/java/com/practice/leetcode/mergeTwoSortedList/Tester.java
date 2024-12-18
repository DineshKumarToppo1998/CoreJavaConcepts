package com.practice.leetcode.mergeTwoSortedList;

public class Tester {

    public static void transverseList(ListNode head){
        ListNode current = head;

        while(current != null){
            System.out.print(current.val + "->");
            current = current.next;
        }

        System.out.println("null");
    }

    public static int getListLength(ListNode head){
        int length = 0;

        while (head != null){
            length++;
            head = head.next;
        }
        return length;

    }

    public static ListNode insertAtFirst(ListNode head , int value){
        return new ListNode(value,head);
    }

    public static ListNode insertAtEnd(ListNode head, int value) {
        // If list is empty, return a new node
        if (head == null) {
            return new ListNode(value);
        }

        // Traverse to the last node
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }

        // Add new node at the end
        current.next = new ListNode(value);
        return head;
    }


    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);


        transverseList(node1);
        System.out.println(getListLength(node1));


        ListNode listNode = insertAtFirst(node1, 10);
        transverseList(listNode);

        ListNode listNode1 = insertAtFirst(listNode, 50);
        transverseList(listNode1);

        ListNode listNode2 = insertAtEnd(listNode1,40);
        transverseList(listNode2);
    }
}
