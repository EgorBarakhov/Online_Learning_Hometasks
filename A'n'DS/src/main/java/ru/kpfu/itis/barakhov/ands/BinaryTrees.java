package ru.kpfu.itis.barakhov.ands;

import java.util.ArrayList;
import java.util.List;

public class BinaryTrees {

    private BinaryTrees(){

    }

    public static boolean DFS(BinarySearchTree binaryTree, int data) {
        return DFSByNode(binaryTree.getHead(), data);
    }

    private static boolean DFSByNode(Node parent, int data) {
        if (parent != null) {
            if (parent.getVal() == data) return true;
            boolean left = DFSByNode(parent.getLeftChild(), data);
            boolean right = DFSByNode(parent.getRightChild(), data);
            return left || right;
        }
        return false;
    }

    public static boolean BFS(BinarySearchTree binaryTree, int data) {
        Node parent = binaryTree.getHead();
        if (data == parent.getVal()) return true;
        List<Node> queue = new ArrayList<>();
        if (parent.getLeftChild() != null) queue.add(parent.getLeftChild());
        if (parent.getRightChild() != null) queue.add(parent.getRightChild());
        return BFSByQueue(queue, data);
    }

    private static boolean BFSByQueue(List<Node> queue, int data) {
        Node currentNode = null;
        try {
            currentNode = queue.get(0);
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
        if (data == currentNode.getVal()) return true;
        if (currentNode.getLeftChild() != null) queue.add(currentNode.getLeftChild());
        if (currentNode.getRightChild() != null) queue.add(currentNode.getRightChild());
        queue.remove(0);
        return false;
    }
}
