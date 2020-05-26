package ru.kpfu.itis.barakhov.ands;

public class Node {
    private Node    parent;
    private Node    leftChild;
    private Node    rightChild;
    private int     val;
    private int     height;             // for AVL

    public Node(int val) {
        this(null, val);
        this.height = 1;
    }

    public Node(Node parent, int val) {
        this.parent = parent;
        this.val = val;
        leftChild = rightChild = null;
        this.height = parent.getHeight() + 1;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}