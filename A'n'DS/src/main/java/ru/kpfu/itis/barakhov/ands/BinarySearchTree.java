package ru.kpfu.itis.barakhov.ands;

import java.util.List;

public class BinarySearchTree {

    private Node head;

    public BinarySearchTree(int seed) {
       head = new Node(seed);
    }

    public boolean add(int data) {
        return addAsChild(head, data);
    }

    private boolean addAsChild(Node parent, int data) {
        if (data < parent.getVal()) {
            if (parent.getLeftChild() == null) parent.setLeftChild(new Node(parent, data));
            else addAsChild(parent.getLeftChild(), data);
        } else if (data > parent.getVal()){
            if (parent.getRightChild() == null) parent.setRightChild(new Node(parent, data));
            else addAsChild(parent.getRightChild(), data);
        } else return false;
        return true;
    }


    //WARNING! Removes all child of removing node
    public boolean remove(int data) {
        return removeAsChild(head, data);
    }

    private boolean removeAsChild(Node parent, int data) {
        if (data < parent.getVal()) {
            if (parent.getLeftChild() == null) return false;
            else {
                if (parent.getLeftChild().getVal() == data) parent.setLeftChild(null);
                else removeAsChild(parent.getLeftChild(), data);
            }
        } else if (data > parent.getVal()){
            if (parent.getRightChild() == null) return false;
            else {
                if (parent.getRightChild().getVal() == data) parent.setRightChild(null);
                else removeAsChild(parent.getRightChild(), data);
            }
        } else throw new IllegalStateException("Trying to delete tree's root");
        return true;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }
}
