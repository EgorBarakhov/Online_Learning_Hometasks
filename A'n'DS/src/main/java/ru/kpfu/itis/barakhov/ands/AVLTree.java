package ru.kpfu.itis.barakhov.ands;

public class AVLTree {
    Node root;

    public AVLTree(int seed) {
        root = new Node(seed);
    }

    public AVLTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    private int balanceFactor(Node node) {
        return node.getLeftChild().getHeight() - node.getRightChild().getHeight();
    }

    private void fixHeight(Node node) {
        int hl = node.getLeftChild().getHeight();
        int hr = node.getRightChild().getHeight();
        node.setHeight(Math.max(hl, hr) + 1);
    }

    private Node rotateRight(Node p) {
        Node q = p.getLeftChild();
        p.setLeftChild(q.getRightChild());
        q.setRightChild(p);
        fixHeight(p);
        fixHeight(q);
        return q;
    }

    private Node rotateLeft(Node q) {
        Node p = q.getRightChild();
        q.setRightChild(p.getLeftChild());
        p.setLeftChild(q);
        fixHeight(q);
        fixHeight(p);
        return p;
    }

    private Node balance(Node p) {
        fixHeight(p);
        if (balanceFactor(p) == 2) {
            if (balanceFactor(p.getRightChild()) < 0) p.setRightChild(rotateRight(p.getRightChild()));
            return rotateLeft(p);
        }
        if (balanceFactor(p) == -2) {
            if (balanceFactor(p.getLeftChild()) > 0) p.setLeftChild(rotateLeft(p.getLeftChild()));
            return rotateRight(p);
        }
        return p;
    }

    private Node insert(Node parent, int key) {
        if (parent == null) return new Node(parent.getParent(), key);
        if (key < parent.getVal()) parent.setLeftChild(insert(parent.getLeftChild(), key));
        else parent.setRightChild(insert(parent.getRightChild(), key));
        return balance(parent);
    }

    private Node findMin(Node p) {
        return p.getLeftChild() == null ? p : findMin(p.getLeftChild());
    }

    private Node removeMin(Node p) {
        if (p.getLeftChild() == null) return p.getRightChild();
        p.setLeftChild(removeMin(p.getLeftChild()));
        return balance(p);
    }

    public Node remove(Node parent, int key) {
        if (parent == null) return null;
        if (key < parent.getVal()) parent.setLeftChild(remove(parent.getLeftChild(), key));
        else if (key > parent.getVal()) parent.setRightChild(remove(parent.getRightChild(), key));
        else {
            Node q = parent.getLeftChild();
            Node p = parent.getRightChild();
            if (p == null) return q;
            Node min = findMin(p);
            min.setRightChild(removeMin(p));
            min.setLeftChild(q);
            return balance(min);
        }
        return balance(parent);
    }
}
