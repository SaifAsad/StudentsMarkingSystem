package studentsmarkingsystem;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Saif Asad
 * @param <K>
 */
public class BinaryTree<K extends Comparable<? super K>, V> {

    public enum TraversalType {

        PREORDER, INORDER, POSTORDER, LEVELORDER
    }

    protected Node<K, V> root;
    protected int size;
    private TraversalType traversalType;

    public BinaryTree() {
        root = null;
        size = 0;
        traversalType = TraversalType.INORDER;
    }

    //--------------------------------------------------------------------------
    //Rotation methods
    public void leftRotate(Node<K, V> x) {
        if (x.getLeftChild() == null) {
            return;
        }
        Node<K, V> y = x.getLeftChild();
        x.setLeftChild(y.getRightChild());
        if (y.getRightChild() != null) {
            y.getRightChild().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            root = y;
        } else {
            if (x == x.getParent().getRightChild()) {
                x.getParent().setRightChild(y);

            } else {
                x.getParent().setLeftChild(y);
            }

        }
        y.setRightChild(x);
        x.setParent(y);
    }

    public void rightRotate(Node<K, V> x) {
        if (x.getRightChild() == null) {
            return;
        }
        Node<K, V> y = x.getRightChild();
        x.setRightChild(y.getLeftChild());
        if (y.getLeftChild() != null) {
            y.getLeftChild().setParent(x);
        }
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            root = y;
        } else {
            if (x == x.getParent().getLeftChild()) {
                x.getParent().setLeftChild(y);

            } else {
                x.getParent().setRightChild(y);
            }

        }
        y.setLeftChild(x);
        x.setParent(y);
    }

    //--------------------------------------------------------------------------
    //Query and Update operations
    public Node<K, V> search(K key) {
        return binarySearch(root, key);
    }

    private Node<K, V> binarySearch(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        if (node.getKey().compareTo(key) == 0) {
            return node;
        } else {
            if (node.getKey().compareTo(key) < 0) {
                return binarySearch(node.getRightChild(), key);
            } else {
                return binarySearch(node.getLeftChild(), key);
            }
        }
    }

    public void setTraversalType(TraversalType traversalType) {
        this.traversalType = traversalType;
    }

    //--------------------------------------------------------------------------
    public Node<K, V> insert(K key, V value) {
        Node<K, V> node = new Node(key, value);
        //check if the node already exist
        Node<K, V> checkNode = search(key);
        if (checkNode != null) {
            //the node already exist => only update the value
            checkNode.setValue(value);
            return checkNode;
        } else {

            if (root == null) {
                root = node;
            } else {
                insert(node, root);
            }
            size++;
            return node;
        }
    }

    //the node will be inserted in a leaf somewhere
    private void insert(Node<K, V> node, Node<K, V> parent) {
        //compare to determine to which side we add the node
        if (node.getKey().compareTo(parent.getKey()) <= 0) {
            //what if the parent has a left child
            if (parent.getLeftChild() == null) {
                parent.setLeftChild(node);
                node.setIsLeftChild(true);
                node.setParent(parent);
            } else {
                insert(node, parent.getLeftChild());
            }

        } else {
            //what if the parent has a right child
            if (parent.getRightChild() == null) {
                parent.setRightChild(node);
                node.setIsRightChild(true);
                node.setParent(parent);
            } else {
                insert(node, parent.getRightChild());
            }

        }
    }

    //--------------------------------------------------------------------------

    private Node<K, V> getSuccessor(Node<K, V> node) {
        //leftmost child of the right child or the right most child of the
        //left child
        Node<K, V> successorParent = node;
        Node<K, V> successor = node;
        Node<K, V> current = node.getRightChild();
        //will loop untill current is null, the successor is found
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.getLeftChild();
        }
        if (successor != node.getRightChild()) {
            successorParent.setLeftChild(successor.getRightChild());
            successor.setRightChild(node.getRightChild());
        }
        return successor;
    }

    public Node<K, V> delete(Node<K, V> node) {
        //there are 4 cases in delete
        if (node.getLeftChild() == null && node.getRightChild() == null) {
            //if the node is a leaf
            if (node == root) {
                root = null;
            } else if (node.isLeftChild()) {
                //dereference the node to be ready for garbage collection
                node.getParent().setLeftChild(null);
            } else {
                node.getParent().setRightChild(null);
            }
        } else if (node.getRightChild() == null) {
            if (node == root) {
                root = node.getLeftChild();
            } else if (node.isLeftChild()) {
                node.getParent().setLeftChild(node.getLeftChild());

            } else {
                node.getParent().setRightChild(node.getLeftChild());
            }
        } else if (node.getLeftChild() == null) {
            if (node == root) {
                root = node.getRightChild();
            } else if (node.isLeftChild()) {
                node.getParent().setLeftChild(node.getRightChild());
            } else {
                node.getParent().setRightChild(node.getRightChild());
            }
            //delete a node that has 2 children
        } else {
            Node<K, V> successor = getSuccessor(node);
            if (node == root) {
                root = successor;
            } else if (node.isLeftChild()) {
                node.getParent().setLeftChild(successor);
            } else {
                node.getParent().setRightChild(successor);
            }
            successor.setLeftChild(node.getLeftChild());
        }
        node.setParent(null);
        node.setLeftChild(null);
        node.setRightChild(null);
        node.setIsRightChild(false);
        node.setIsLeftChild(false);
        size--;
        return node;
    }

    //Utility methods
    //--------------------------------------------------------------------------
    public int getSize() {
        return size;
    }

    public int getHeight() {
        return heightOfBinaryTree(root);
    }

    private int heightOfBinaryTree(Node<K, V> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(heightOfBinaryTree(node.getLeftChild()),
                    heightOfBinaryTree(node.getRightChild()));
        }
    }

    public Node<K, V> getRoot() {
        return root;
    }

    //--------------------------------------------------------------------------
    //Printing

    @Override
    public String toString() {
        if (root == null) {
            return "";

        } else {
            switch (traversalType) {
                case INORDER:
                    return root.toString(TraversalType.INORDER);
                case PREORDER:
                    return root.toString(TraversalType.PREORDER);
                case POSTORDER:
                    return root.toString(TraversalType.POSTORDER);
                case LEVELORDER:
                    return root.toString(TraversalType.LEVELORDER);
                default:
                    return "";
            }
        }
    }

    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------

    public static class Node<K extends Comparable<? super K>, V> {

        private K key;
        private V value;
        private Node<K, V> rightChild;
        private Node<K, V> leftChild;
        private Node<K, V> parent;
        private boolean isLeftChild;
        private boolean isRightChild;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            leftChild = null;
            rightChild = null;
            this.parent = null;
        }

        public boolean isLeftChild() {
            return isLeftChild;
        }

        public boolean isRightChild() {
            return isRightChild;
        }

        public void setIsLeftChild(boolean isLeftChild) {
            this.isLeftChild = isLeftChild;
            this.isRightChild = !isLeftChild;
        }

        public void setIsRightChild(boolean isRightChild) {
            this.isRightChild = isRightChild;
            this.isLeftChild = !isRightChild;
        }

        //----------------------------------------------------------------------
        //Accessors and Mutators
        public Node<K, V> getParent() {
            return this.parent;
        }

        public void setParent(Node<K, V> parent) {
            this.parent = parent;
        }

        public K getKey() {
            return this.key;
        }

        public K setKey(K key) {
            return this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node<K, V> rightChild) {
            this.rightChild = rightChild;
            if (rightChild != null) {
                rightChild.setIsRightChild(true);
                rightChild.setParent(this);

            }
        }

        public Node<K, V> getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node<K, V> leftChild) {
            this.leftChild = leftChild;
            if (leftChild != null) {
                leftChild.setIsLeftChild(true);
                leftChild.setParent(this);
            }
        }

        //----------------------------------------------------------------------

        @Override
        public String toString() {
            return key.toString();
        }

        private String toString(TraversalType traversalType) {
            String result = "";

            switch (traversalType) {
                case INORDER:
                    if (leftChild != null) {
                        result += leftChild.toString(traversalType);
                    }
                    result += value;
                    if (rightChild != null) {
                        result += rightChild.toString(traversalType);
                    }
                    break;

                case POSTORDER:
                    if (leftChild != null) {
                        result += leftChild.toString(traversalType);
                    }

                    if (rightChild != null) {
                        result += rightChild.toString(traversalType);
                    }
                    result += value;
                    break;

                case PREORDER:
                    result += value;
                    if (leftChild != null) {
                        result += leftChild.toString(traversalType);
                    }
                    if (rightChild != null) {
                        result += rightChild.toString(traversalType);
                    }
                    break;

                case LEVELORDER:
                    Queue<Node<K, V>> level = new LinkedList<>();
                    level.add(this);
                    while (!level.isEmpty()) {
                        Node<K, V> node = level.poll();
                        result += node.value;
                        if (node.leftChild != null) {
                            level.add(node.leftChild);
                        }
                        if (node.rightChild != null) {
                            level.add(node.rightChild);
                        }
                    }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        BinaryTree<String, Integer> binaryTree = new BinaryTree<>();
        Node<String, Integer> node = binaryTree.insert("N", 4);
        /*binaryTree.insert("Z");
         binaryTree.insert("L");
         binaryTree.insert("A");
         binaryTree.insert("D");
         binaryTree.insert("R");
         binaryTree.insert("U");
         binaryTree.insert("G");
         binaryTree.insert("B");
         binaryTree.insert("Y");*/

        System.out.println(binaryTree.getHeight());
        binaryTree.setTraversalType(TraversalType.INORDER);
        System.out.println(binaryTree);

    }

}
