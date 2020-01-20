package com.mike.caches;

import java.util.HashMap;
import java.util.Map;

import lombok.ToString;

public class LRUCache {

    @ToString
    private static class Node {
        public Node prev;
        public Node next;
        public int value;
        public String key;
    }

    private Node head = null; //signals an old node
    private Node tail = null; //signals the recently used

    private final Map<String, Node> store;

    private int size = 0;
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        store = new HashMap<>();
    }

    private void addNodeToTail(Node node) {
        if (tail == null) {
            //first node
            head = tail = node;
            node.prev = null;
            node.next = null;
        } else {
            node.prev = tail;
            node.next = null;
            tail.next = node;
            tail = node;
        }
        size++;
    }

    private void deleteNode(Node node) {
        if (head == node) {
            head = node.next;
            if (head != null) {
                head.prev = null;
            } else {
                head = tail = null;
            }
        } else if (node.prev != null && node.next != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        } else {
            if (tail.prev == null) {
                head = tail = null;
            } else {
                tail.prev = tail.next;
            }
        }

        size--;
    }

    public int get(String key) {
        Node current = store.get(key);

        if (current == null) {
            return -1;
        } else {
            // node exists

            //1. delete node
            deleteNode(current);

            //2. add node to tail
            addNodeToTail(current);

            return current.value;
        }
    }

    public void put(String key, int value) {
        Node node = store.get(key);

        if (node == null) {
            //does not exist
            node = new Node();
            node.key = key;
            node.value = value;
            store.put(key, node);
            addNodeToTail(node);
        } else {
            //node exists
            deleteNode(node);

            node.value = value;
            addNodeToTail(node);
        }

        if (size > this.capacity) {
            String keyToDelete = head.key;
            deleteNode(head);
            store.remove(keyToDelete);
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put("a", 1);
        cache.put("b", 2);
        System.out.println("a=" + cache.get("a"));
        System.out.println("b=" + cache.get("b"));
        System.out.println("---");
        cache.put("c", 3);
        System.out.println("a=" + cache.get("a"));
        System.out.println("b=" + cache.get("b"));
        System.out.println("c=" + cache.get("c"));
        System.out.println("---");
        cache.put("b", 10);
        System.out.println("a=" + cache.get("a"));
        System.out.println("b=" + cache.get("b"));
        System.out.println("c=" + cache.get("c"));
    }
}
