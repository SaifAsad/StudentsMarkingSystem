
package studentsmarkingsystem;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Saif Asad
 */
public class BinarySearchTree {

    public enum TraversalType {

        PREORDER, INORDER, POSTORDER, LEVELORDER
    }

    protected Node root;
    protected int size;
    private TraversalType traversalType;

    public BinarySearchTree() {
        root = null;
        size = 0;
        traversalType = TraversalType.INORDER;
    }

    //--------------------------------------------------------------------------
    public void setTraversalType(TraversalType traversalType) {
        this.traversalType = traversalType;
    }
    //--------------------------------------------------------------------------
    public Node getRoot(){
        return root;
    }
    //--------------------------------------------------------------------------
    public Node insert(String name, int mark) {
        Node node = new Node(name, mark);
        if (root == null) {
            root = node;
        } else {
            insert(node, root);
        }
        size++;
        return node;
    }
    //--------------------------------------------------------------------------
    //the node will be inserted as a leaf somewhere
    private void insert(Node node, Node parent) {
        //compare to determine to which side we add the node
        if (node.getName().compareTo(parent.getName()) <= 0) {
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
    private Node getSuccessor(Node node) {
        //left most child of the right child or the right most child of the left child
        Node successorParent = node;
        Node successor = node;
        Node current = node.getRightChild();
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
    //--------------------------------------------------------------------------
    public Node delete(Node node) {
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
            Node successor = getSuccessor(node);
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
    //--------------------------------------------------------------------------
    public int getSize() {
        return size;
    }
    //--------------------------------------------------------------------------
    public int getHeight() {
        return heightOfBinaryTree(root);
    }
    //--------------------------------------------------------------------------
    private int heightOfBinaryTree(Node node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + Math.max(heightOfBinaryTree(node.getLeftChild()), 
                    heightOfBinaryTree(node.getRightChild()));
        }
    }
    //--------------------------------------------------------------------------
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
    public static class Node {

        private String name;
        private int mark;
        private Node rightChild;
        private Node leftChild;
        private Node parent;
        private boolean isLeftChild;
        private boolean isRightChild;
        
        public Node(String value, int mark) {
            this.name = value;
            this.mark = mark;
            leftChild = null;
            rightChild = null;
            this.parent = null;
        }
        //----------------------------------------------------------------------
        public boolean isLeftChild() {
            return isLeftChild;
        }
        //----------------------------------------------------------------------   
        public boolean isRightChild() {
            return isRightChild;
        }
        //----------------------------------------------------------------------
        public void setIsLeftChild(boolean isLeftChild) {
            this.isLeftChild = isLeftChild;
            this.isRightChild = !isLeftChild;
        }
        //----------------------------------------------------------------------
        public void setIsRightChild(boolean isRightChild) {
            this.isRightChild = isRightChild;
            this.isLeftChild = !isRightChild;
        }
        //----------------------------------------------------------------------
        public Node getParent() {
            return this.parent;
        }
        //----------------------------------------------------------------------
        public void setParent(Node parent) {
            this.parent = parent;
        }
        //----------------------------------------------------------------------
        public String getName() {
            return name;
        }
        public int getMark(){
            return mark;
        }
        //----------------------------------------------------------------------
        public void setName(String name) {
            this.name = name;
        }
        
        public void setMark(int mark){
            this.mark = mark;
        }
        //----------------------------------------------------------------------
        public Node getRightChild() {
            return rightChild;
        }
        //----------------------------------------------------------------------
        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
            if (rightChild != null) {
                rightChild.setIsRightChild(true);
                rightChild.setParent(this);

            }
        }
        //----------------------------------------------------------------------
        public Node getLeftChild() {
            return leftChild;
        }
        //----------------------------------------------------------------------
        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
            if (leftChild != null) {
                leftChild.setIsLeftChild(true);
                leftChild.setParent(this);
            }
        }
        //----------------------------------------------------------------------
        @Override
        public String toString() {
            return name + " : " + mark;
        }
        //----------------------------------------------------------------------
        private String toString(TraversalType traversalType) {
            String result = "";

            switch (traversalType) {
                case INORDER:
                    if (leftChild != null) {
                        result += leftChild.toString(traversalType);
                    }
                    result += name + " : " + mark + "\n";
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
                    result += name + " : " + mark + "\n";
                    break;

                case PREORDER:
                    result += name + " : " + mark + "\n";
                    if (leftChild != null) {
                        result += leftChild.toString(traversalType);
                    }
                    if (rightChild != null) {
                        result += rightChild.toString(traversalType);
                    }
                    break;

                case LEVELORDER:
                    Queue<Node> level = new LinkedList<>();
                    level.add(this);
                    while (!level.isEmpty()) {
                        Node node = level.poll();
                        result += node.name + " : " + node.mark + "\n";
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
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert("N", 1);
        binarySearchTree.insert("Z", 2);
        binarySearchTree.insert("L", 3);
        binarySearchTree.insert("A", 4);
        binarySearchTree.insert("D", 5);
        binarySearchTree.insert("R", 6);
        binarySearchTree.insert("U", 7);
        binarySearchTree.insert("G", 8);
        binarySearchTree.insert("B", 9);
        binarySearchTree.insert("Y", 10);

        System.out.println(binarySearchTree.getHeight());
        binarySearchTree.setTraversalType(TraversalType.LEVELORDER);
        System.out.println(binarySearchTree);
    }

}
