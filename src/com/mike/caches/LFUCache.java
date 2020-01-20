package com.mike.caches;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    public static class Node {
        public String key;
        public String value;
        public int frequency;

        public Node next;
        public Node previous;
    }

    private Map<String, Node> store = new HashMap<>();
    private int capacity = 0;
    private Node head;
    private static final int MAX = 10;

    private void exchangeNodes(final Node valueNode, final Node nextNode) {
        //TODO
        if (valueNode.frequency == nextNode.frequency) {
            return;
        } else {
            nextNode.next = valueNode;
            valueNode.next = nextNode.next;
            nextNode.previous = valueNode.previous;
            valueNode.previous = nextNode;
        }
    }

    private void addToHead(Node node) {
        if (head == null) {
            head = node;
            capacity++;
            node.next = null;
        } else {
            if (this.capacity == MAX) {
                Node toDelete = head;
                node.next = toDelete.next;
                node.next.previous = node;
                head = node;
                
            } else {
                Node next = head.next;

                head = node;
                node.next = next;
                next.previous = node;
                capacity++;
            }
        }
    }

    public void put(String key, String value) {
        Node valueNode = store.get(key);

        if (valueNode == null) {
            Node node = new Node();
            node.frequency = 1;
            node.key = key;
            node.value = value;

            addToHead(node);
        } else {
            Node nextNode = valueNode.next;

            if (nextNode == null) {
                valueNode.frequency++;
            } else {
                exchangeNodes(valueNode, nextNode);
            }
        }
    }

    // closest to tail then it is more frequently used
    public String get(String key) {
        Node valueNode = store.get(key);

        if (valueNode == null) {
            return "-1";
        } else {
            String value = valueNode.value;

            Node nextNode = valueNode.next;
            if (nextNode != null && nextNode.frequency == valueNode.frequency) {
                exchangeNodes(valueNode, nextNode);
            } else if (nextNode != null) {
                valueNode.frequency = valueNode.frequency + 1;
            }

            return value;
        }
    }

    public static void main(String[] args) {

    }
}
