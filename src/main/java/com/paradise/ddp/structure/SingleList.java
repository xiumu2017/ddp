package com.paradise.ddp.structure;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Paradise
 */
public class SingleList {
    private int size;

    public int getSize() {
        return this.size;
    }

    private Node head;

    private Node tail;

    public static class Node {
        private Node next;
        private String name;

        Node(String name) {
            this.name = name;
        }

        Node getNext() {
            return next;
        }

        void setNext(Node next) {
            this.next = next;
        }

        String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public SingleList(String head) {
        this.head = new Node(head);
        this.tail = this.head;
        this.size = 1;
    }

    public SingleList() {
        this.size = 0;
    }

    public void add(String node) {
        if (this.tail == null) {
            this.head = new Node(node);
            this.tail = this.head;
            this.size = 1;
        } else {
            Node node1 = new Node(node);
            node1.setNext(null);
            this.tail.setNext(node1);
            this.tail = node1;
            this.size++;
        }
    }

    public void add(Node target, Node node) {
        Node next = target.getNext();
        node.setNext(next);
        target.setNext(node);
        this.size++;
    }

    public void remove(Node node) {
        if (this.head == node) {
            this.head = this.head.next;
            this.size--;
        }
        Node index = this.head;
        while (index.getNext() != null) {
            if (index.getNext() == node) {
                if (index.getNext().getNext() != null) {
                    index.setNext(index.getNext().getNext());
                    this.size--;
                }
            }
            index = index.getNext();
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node node = this.head;
        while (node != null) {
            stringBuilder.append("[").append(node.getName()).append("]");
            node = node.next;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        SingleList singleList = new SingleList();
        for (int i = 0; i < 3; i++) {
            singleList.add("QQ" + i);
        }
        System.out.println(singleList.size);
        System.out.println(singleList.toString());
    }
}
